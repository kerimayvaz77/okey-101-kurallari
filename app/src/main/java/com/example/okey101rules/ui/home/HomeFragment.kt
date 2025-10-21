package com.example.okey101rules.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.appcompat.widget.SearchView
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.okey101rules.databinding.FragmentHomeBinding
import com.example.okey101rules.ui.adapters.CategoryAdapter
import com.example.okey101rules.ui.adapters.SearchResultAdapter
import com.example.okey101rules.ui.home.HomeFragmentDirections
import com.example.okey101rules.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {
    
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    
    private val viewModel: HomeViewModel by viewModels()
    private lateinit var categoryAdapter: CategoryAdapter
    private lateinit var searchResultAdapter: SearchResultAdapter
    
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        setupRecyclerView()
        setupSearchView()
        observeViewModel()

        // Ensure SearchView is expanded and focusable for input
        binding.searchView.isIconified = false
        binding.searchView.requestFocus()
    }
    
    private fun setupRecyclerView() {
        categoryAdapter = CategoryAdapter { category ->
            val action = HomeFragmentDirections.actionHomeFragmentToCategoryDetailFragment(category)
            findNavController().navigate(action)
        }
        
        searchResultAdapter = SearchResultAdapter { rule ->
            val action = HomeFragmentDirections.actionHomeFragmentToRuleDetailFragment(rule.id, rule.title)
            findNavController().navigate(action)
        }
        
        binding.recyclerViewCategories.apply {
            val spanCount = resources.getInteger(R.integer.grid_column_count)
            layoutManager = GridLayoutManager(context, spanCount)
            adapter = categoryAdapter
        }
    }
    
    private fun setupSearchView() {
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            private var debounceRunnable: Runnable? = null
            private val debounceDelayMs = 300L

            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let {
                    val trimmed = it.trim()
                    if (trimmed.isNotEmpty()) {
                        viewModel.searchRules(trimmed)
                    }
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                debounceRunnable?.let { binding.searchView.removeCallbacks(it) }
                val r = Runnable {
                    val q = newText?.trim().orEmpty()
                    if (q.isNotEmpty()) {
                        viewModel.searchRules(q)
                    } else {
                        viewModel.clearSearch()
                    }
                }
                debounceRunnable = r
                binding.searchView.postDelayed(r, debounceDelayMs)
                return true
            }
        })
        
        // Handle close button click
        binding.searchView.setOnCloseListener {
            // Clear search with animation
            clearSearchWithAnimation()
            true
        }
    }
    
    private fun observeViewModel() {
        viewModel.categories.observe(viewLifecycleOwner) { categories ->
            // Only show categories if we don't have an active search
            if (viewModel.getCurrentSearchQuery().isNullOrBlank()) {
                binding.recyclerViewCategories.adapter = categoryAdapter
                categoryAdapter.submitList(categories)
            }
        }
        
        // Observe search query from SavedStateHandle - THIS IS KEY!
        viewModel.currentSearchQuery.observe(viewLifecycleOwner) { query ->
            if (query.isNotEmpty()) {
                // Update SearchView without triggering listeners
                binding.searchView.setQuery(query, false)
                // Switch to search mode
                binding.recyclerViewCategories.adapter = searchResultAdapter
                // Show search results header with animation
                binding.searchResultsHeader.visibility = View.VISIBLE
                binding.searchResultsHeader.alpha = 0f
                binding.searchResultsHeader.animate()
                    .alpha(1f)
                    .setDuration(200)
                    .start()
            } else {
                // Clear search view and switch to category mode
                binding.searchView.setQuery("", false)
                binding.recyclerViewCategories.adapter = categoryAdapter
                // Hide search results header
                binding.searchResultsHeader.visibility = View.GONE
                // Load categories when switching back to category mode
                viewModel.categories.value?.let { categories ->
                    categoryAdapter.submitList(categories)
                }
            }
        }
        
        viewModel.searchResults.observe(viewLifecycleOwner) { results ->
            // Always submit results to search adapter
            searchResultAdapter.submitList(results)
            // Update search results count
            binding.searchResultsCount.text = "${results.size} sonuç"
        }
    }
    
    private fun clearSearchWithAnimation() {
        // Clear search query - observer will handle UI updates
        viewModel.clearSearch()
    }
    
    fun refreshFragment(clearSearch: Boolean = true) {
        // This method is called from MainActivity when navigating back to home
        // Optionally clear search state
        if (clearSearch) {
            viewModel.clearSearch()
        }
        
        // Only proceed if binding is available (view is created)
        if (_binding != null) {
            // Complete refresh animation - make it feel like starting fresh
            binding.recyclerViewCategories.alpha = 0.3f
            binding.recyclerViewCategories.scaleX = 0.95f
            binding.recyclerViewCategories.scaleY = 0.95f
            
            binding.recyclerViewCategories.animate()
                .alpha(1.0f)
                .scaleX(1.0f)
                .scaleY(1.0f)
                .setDuration(400)
                .start()
            
            // Hide search results header with animation
            binding.searchResultsHeader.animate()
                .alpha(0f)
                .setDuration(200)
                .withEndAction {
                    binding.searchResultsHeader.visibility = View.GONE
                    binding.searchResultsHeader.alpha = 1f
                }
                .start()
            
            // Add a strong bounce effect to the search container
            binding.searchView.parent.let { parent ->
                if (parent is View) {
                    parent.scaleX = 0.95f
                    parent.scaleY = 0.95f
                    parent.animate()
                        .scaleX(1.0f)
                        .scaleY(1.0f)
                        .setDuration(300)
                        .start()
                }
            }
            
            // Scroll to top to simulate fresh start
            binding.recyclerViewCategories.post {
                binding.recyclerViewCategories.scrollToPosition(0)
            }
        }
    }
    
    fun hasActiveSearch(): Boolean {
        return viewModel.hasActiveSearch()
    }
    
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
