package com.example.okey101rules.ui.info

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.example.okey101rules.databinding.FragmentGeneralInfoBinding
import com.example.okey101rules.ui.adapters.InfoCardAdapter
import com.example.okey101rules.ui.dialogs.InfoDetailDialog
import com.example.okey101rules.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GeneralInfoFragment : Fragment() {

    private var _binding: FragmentGeneralInfoBinding? = null
    private val binding get() = _binding!!
    
    private val viewModel: GeneralInfoViewModel by viewModels()
    private lateinit var infoAdapter: InfoCardAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGeneralInfoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        setupRecyclerView()
        observeViewModel()
        
        viewModel.loadInfoCards()
    }

    private fun setupRecyclerView() {
        infoAdapter = InfoCardAdapter()
        
        // Click listener ekle
        infoAdapter.setOnItemClickListener { infoCard ->
            showInfoDetailDialog(infoCard)
        }
        
        binding.recyclerViewInfo.apply {
            val spanCount = resources.getInteger(R.integer.grid_column_count)
            layoutManager = GridLayoutManager(context, spanCount)
            adapter = infoAdapter
        }
    }

    private fun observeViewModel() {
        viewModel.infoCards.observe(viewLifecycleOwner) { cards ->
            infoAdapter.submitList(cards)
        }
        
        viewModel.isLoading.observe(viewLifecycleOwner) { isLoading ->
            binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
        }
    }

    private fun showInfoDetailDialog(infoCard: com.example.okey101rules.data.InfoCard) {
        InfoDetailDialog.show(
            requireContext(),
            infoCard
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
