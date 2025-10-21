package com.example.okey101rules.ui.faq

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.example.okey101rules.databinding.FragmentFaqBinding
import com.example.okey101rules.ui.adapters.FaqAdapter
import com.example.okey101rules.ui.dialogs.FaqDetailDialog
import com.example.okey101rules.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FaqFragment : Fragment() {

    private var _binding: FragmentFaqBinding? = null
    private val binding get() = _binding!!
    
    private val viewModel: FaqViewModel by viewModels()
    private lateinit var faqAdapter: FaqAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFaqBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        setupRecyclerView()
        observeViewModel()
        
        viewModel.loadFaqs()
    }

    private fun setupRecyclerView() {
        faqAdapter = FaqAdapter()
        
        // Click listener ekle
        faqAdapter.setOnItemClickListener { faq ->
            showFaqDetailDialog(faq)
        }
        
        binding.recyclerViewFaq.apply {
            val spanCount = resources.getInteger(R.integer.grid_column_count)
            layoutManager = GridLayoutManager(context, spanCount)
            adapter = faqAdapter
        }
    }

    private fun observeViewModel() {
        viewModel.faqs.observe(viewLifecycleOwner) { faqs ->
            faqAdapter.submitList(faqs)
        }
        
        viewModel.isLoading.observe(viewLifecycleOwner) { isLoading ->
            binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
        }
    }

    private fun showFaqDetailDialog(faq: com.example.okey101rules.data.Faq) {
        FaqDetailDialog.show(
            requireContext(),
            faq
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
