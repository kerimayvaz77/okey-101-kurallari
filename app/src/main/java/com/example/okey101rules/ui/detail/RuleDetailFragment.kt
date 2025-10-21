package com.example.okey101rules.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.okey101rules.R
import com.example.okey101rules.databinding.FragmentRuleDetailBinding
import com.example.okey101rules.ui.detail.RuleDetailFragmentArgs
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RuleDetailFragment : Fragment() {
    
    private var _binding: FragmentRuleDetailBinding? = null
    private val binding get() = _binding!!
    
    private val viewModel: RuleDetailViewModel by viewModels()
    private val args: RuleDetailFragmentArgs by navArgs()
    
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRuleDetailBinding.inflate(inflater, container, false)
        return binding.root
    }
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        observeViewModel()
        viewModel.loadRule(args.ruleId)
    }
    
    private fun observeViewModel() {
        viewModel.rule.observe(viewLifecycleOwner) { rule ->
            rule?.let {
                binding.textViewRuleTitle.text = it.title
                binding.textViewRuleDescription.text = it.description
                binding.textViewRuleCategory.text = "${it.category} - ${it.subCategory}"
                
                // Hanini Kuralları için kırmızı renk
                if (it.category == "HANİNİ KURALLARI") {
                    binding.textViewRuleTitle.setTextColor(
                        ContextCompat.getColor(requireContext(), R.color.red_500)
                    )
                    binding.textViewRuleCategory.setTextColor(
                        ContextCompat.getColor(requireContext(), R.color.red_600)
                    )
                    binding.textViewRuleDescription.setTextColor(
                        ContextCompat.getColor(requireContext(), R.color.red_700)
                    )
                } else {
                    binding.textViewRuleTitle.setTextColor(
                        ContextCompat.getColor(requireContext(), R.color.text_primary)
                    )
                    binding.textViewRuleCategory.setTextColor(
                        ContextCompat.getColor(requireContext(), R.color.text_secondary)
                    )
                    binding.textViewRuleDescription.setTextColor(
                        ContextCompat.getColor(requireContext(), R.color.text_primary)
                    )
                }
            }
        }
    }
    
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
