package com.example.okey101rules.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.okey101rules.R
import com.example.okey101rules.data.Rule
import com.example.okey101rules.databinding.ItemSearchResultBinding

class SearchResultAdapter(
    private val onRuleClick: (Rule) -> Unit
) : ListAdapter<Rule, SearchResultAdapter.SearchResultViewHolder>(SearchResultDiffCallback()) {
    
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchResultViewHolder {
        val binding = ItemSearchResultBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return SearchResultViewHolder(binding)
    }
    
    override fun onBindViewHolder(holder: SearchResultViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
    
    inner class SearchResultViewHolder(
        private val binding: ItemSearchResultBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        
        fun bind(rule: Rule) {
            binding.textViewRuleTitle.text = "🀄 ${rule.title}"
            binding.textViewRuleCategory.text = "${rule.category} - ${rule.subCategory}"
            
            // Show preview of description
            val preview = if (rule.description.length > 100) {
                rule.description.substring(0, 100) + "..."
            } else {
                rule.description
            }
            binding.textViewRulePreview.text = preview
            
            // Hanini Kuralları için kırmızı renk
            if (rule.category == "HANİNİ KURALLARI") {
                binding.textViewRuleTitle.setTextColor(
                    ContextCompat.getColor(binding.root.context, R.color.red_500)
                )
                binding.textViewRuleCategory.setTextColor(
                    ContextCompat.getColor(binding.root.context, R.color.red_600)
                )
                binding.textViewRulePreview.setTextColor(
                    ContextCompat.getColor(binding.root.context, R.color.red_700)
                )
            } else {
                binding.textViewRuleTitle.setTextColor(
                    ContextCompat.getColor(binding.root.context, R.color.text_primary)
                )
                binding.textViewRuleCategory.setTextColor(
                    ContextCompat.getColor(binding.root.context, R.color.text_secondary)
                )
                binding.textViewRulePreview.setTextColor(
                    ContextCompat.getColor(binding.root.context, R.color.text_secondary)
                )
            }
            
            binding.root.setOnClickListener {
                onRuleClick(rule)
            }
        }
    }
    
    class SearchResultDiffCallback : DiffUtil.ItemCallback<Rule>() {
        override fun areItemsTheSame(oldItem: Rule, newItem: Rule): Boolean {
            return oldItem.id == newItem.id
        }
        
        override fun areContentsTheSame(oldItem: Rule, newItem: Rule): Boolean {
            return oldItem == newItem
        }
    }
}
