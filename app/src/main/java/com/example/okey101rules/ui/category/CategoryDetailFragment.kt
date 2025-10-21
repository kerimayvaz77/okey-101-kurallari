package com.example.okey101rules.ui.category

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.okey101rules.R
import com.example.okey101rules.databinding.FragmentCategoryDetailBinding
import com.example.okey101rules.ui.adapters.RuleTitleAdapter
import com.example.okey101rules.ui.category.CategoryDetailFragmentArgs
import com.example.okey101rules.ui.category.CategoryDetailFragmentDirections
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CategoryDetailFragment : Fragment() {
    
    private var _binding: FragmentCategoryDetailBinding? = null
    private val binding get() = _binding!!
    
    private val viewModel: CategoryViewModel by viewModels()
    private val args: CategoryDetailFragmentArgs by navArgs()
    private lateinit var ruleAdapter: RuleTitleAdapter
    
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCategoryDetailBinding.inflate(inflater, container, false)
        return binding.root
    }
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        setupRecyclerView()
        observeViewModel()
        
        // Set category title with emoji
        binding.textViewCategoryTitle.text = "🀄 ${args.category}"
        
        // Hanini Kuralları için kırmızı renk
        if (args.category == "HANİNİ KURALLARI") {
            binding.textViewCategoryTitle.setTextColor(
                ContextCompat.getColor(requireContext(), R.color.red_500)
            )
        }
        
        // Load rules for this category
        viewModel.loadRulesForCategory(args.category)
    }
    
    private fun setupRecyclerView() {
        ruleAdapter = RuleTitleAdapter { rule ->
            val action = CategoryDetailFragmentDirections.actionCategoryDetailFragmentToRuleDetailFragment(rule.id, rule.title)
            findNavController().navigate(action)
        }
        
        binding.recyclerViewRules.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = ruleAdapter
        }
    }
    
    private fun observeViewModel() {
        viewModel.rules.observe(viewLifecycleOwner) { rules ->
            ruleAdapter.submitList(rules)
            
            // Show empty state if no rules
            if (rules.isEmpty()) {
                binding.layoutEmptyState.root.visibility = View.VISIBLE
                binding.recyclerViewRules.visibility = View.GONE
            } else {
                binding.layoutEmptyState.root.visibility = View.GONE
                binding.recyclerViewRules.visibility = View.VISIBLE
            }
        }
    }
    
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
