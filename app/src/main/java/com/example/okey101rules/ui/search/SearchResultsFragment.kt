package com.example.okey101rules.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.appcompat.widget.SearchView
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.okey101rules.databinding.FragmentSearchResultsBinding
import com.example.okey101rules.ui.adapters.SearchResultAdapter
import com.example.okey101rules.ui.search.SearchResultsFragmentArgs
import com.example.okey101rules.ui.search.SearchResultsFragmentDirections
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchResultsFragment : Fragment() {
    
    private var _binding: FragmentSearchResultsBinding? = null
    private val binding get() = _binding!!
    
    private val viewModel: SearchViewModel by viewModels()
    private val args: SearchResultsFragmentArgs by navArgs()
    private lateinit var searchAdapter: SearchResultAdapter
    
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchResultsBinding.inflate(inflater, container, false)
        return binding.root
    }
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        setupRecyclerView()
        observeViewModel()

        // Initialize query from Safe Args
        val initialQuery = args.searchQuery
        if (initialQuery.isNotBlank()) {
            binding.searchView.setQuery(initialQuery, false)
            viewModel.setQuery(initialQuery)
        }

        // Ensure SearchView is expanded and focusable
        binding.searchView.isIconified = false
        binding.searchView.requestFocus()

        // Debounced text change listener
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            private var debounceRunnable: Runnable? = null
            private val debounceDelayMs = 300L

            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let { viewModel.setQuery(it) }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                debounceRunnable?.let { binding.searchView.removeCallbacks(it) }
                val r = Runnable {
                    val q = newText?.trim().orEmpty()
                    if (q.isNotBlank()) {
                        viewModel.setQuery(q)
                    } else {
                        viewModel.setQuery("")
                    }
                }
                debounceRunnable = r
                binding.searchView.postDelayed(r, debounceDelayMs)
                return true
            }
        })
    }
    
    private fun setupRecyclerView() {
        searchAdapter = SearchResultAdapter { rule ->
            val action = SearchResultsFragmentDirections.actionSearchResultsFragmentToRuleDetailFragment(rule.id, rule.title)
            findNavController().navigate(action)
        }
        
        binding.recyclerViewSearchResults.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = searchAdapter
        }
    }
    
    private fun observeViewModel() {
        viewModel.searchResults.observe(viewLifecycleOwner) { rules ->
            searchAdapter.submitList(rules)
            
            // Show empty state if no results
            if (rules.isEmpty()) {
                binding.layoutEmptyState.root.visibility = View.VISIBLE
                binding.recyclerViewSearchResults.visibility = View.GONE
            } else {
                binding.layoutEmptyState.root.visibility = View.GONE
                binding.recyclerViewSearchResults.visibility = View.VISIBLE
            }
        }
    }
    
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
