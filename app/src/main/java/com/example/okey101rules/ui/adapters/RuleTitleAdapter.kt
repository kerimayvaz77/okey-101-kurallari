package com.example.okey101rules.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.okey101rules.R
import com.example.okey101rules.data.Rule
import com.example.okey101rules.databinding.ItemRuleTitleBinding

class RuleTitleAdapter(
    private val onRuleClick: (Rule) -> Unit
) : ListAdapter<Rule, RuleTitleAdapter.RuleViewHolder>(RuleDiffCallback()) {
    
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RuleViewHolder {
        val binding = ItemRuleTitleBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return RuleViewHolder(binding)
    }
    
    override fun onBindViewHolder(holder: RuleViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
    
    inner class RuleViewHolder(
        private val binding: ItemRuleTitleBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        
        fun bind(rule: Rule) {
            binding.textViewRuleTitle.text = "🀄 ${rule.title}"
            binding.textViewRuleSubCategory.text = rule.subCategory
            
            // Hanini Kuralları için kırmızı renk
            if (rule.category == "HANİNİ KURALLARI") {
                binding.textViewRuleTitle.setTextColor(
                    ContextCompat.getColor(binding.root.context, R.color.red_500)
                )
                binding.textViewRuleSubCategory.setTextColor(
                    ContextCompat.getColor(binding.root.context, R.color.red_600)
                )
            } else {
                binding.textViewRuleTitle.setTextColor(
                    ContextCompat.getColor(binding.root.context, R.color.text_primary)
                )
                binding.textViewRuleSubCategory.setTextColor(
                    ContextCompat.getColor(binding.root.context, R.color.text_secondary)
                )
            }
            
            binding.root.setOnClickListener {
                onRuleClick(rule)
            }
        }
    }
    
    class RuleDiffCallback : DiffUtil.ItemCallback<Rule>() {
        override fun areItemsTheSame(oldItem: Rule, newItem: Rule): Boolean {
            return oldItem.id == newItem.id
        }
        
        override fun areContentsTheSame(oldItem: Rule, newItem: Rule): Boolean {
            return oldItem == newItem
        }
    }
}
