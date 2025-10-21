package com.example.okey101rules.ui.strategy

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.example.okey101rules.databinding.FragmentStrategyBinding
import com.example.okey101rules.ui.adapters.StrategyAdapter
import com.example.okey101rules.ui.dialogs.StrategyDetailDialog
import com.example.okey101rules.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class StrategyFragment : Fragment() {

    private var _binding: FragmentStrategyBinding? = null
    private val binding get() = _binding!!
    
    private val viewModel: StrategyViewModel by viewModels()
    private lateinit var strategyAdapter: StrategyAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentStrategyBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        setupRecyclerView()
        observeViewModel()
        
        viewModel.loadStrategies()
    }

    private fun setupRecyclerView() {
        strategyAdapter = StrategyAdapter()
        
        // Click listener ekle
        strategyAdapter.setOnItemClickListener { strategy ->
            showStrategyDetailDialog(strategy)
        }
        
        binding.recyclerViewStrategy.apply {
            val spanCount = resources.getInteger(R.integer.grid_column_count)
            layoutManager = GridLayoutManager(context, spanCount)
            adapter = strategyAdapter
        }
    }

    private fun observeViewModel() {
        viewModel.strategies.observe(viewLifecycleOwner) { strategies ->
            strategyAdapter.submitList(strategies)
        }
        
        viewModel.isLoading.observe(viewLifecycleOwner) { isLoading ->
            binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
        }
    }

    private fun showStrategyDetailDialog(strategy: com.example.okey101rules.data.Strategy) {
        StrategyDetailDialog.show(
            requireContext(),
            strategy
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
