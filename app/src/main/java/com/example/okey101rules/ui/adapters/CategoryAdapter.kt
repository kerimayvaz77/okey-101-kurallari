package com.example.okey101rules.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.okey101rules.R
import com.example.okey101rules.databinding.ItemCategoryBinding

class CategoryAdapter(
    private val onCategoryClick: (String) -> Unit
) : ListAdapter<String, CategoryAdapter.CategoryViewHolder>(CategoryDiffCallback()) {
    
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val binding = ItemCategoryBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return CategoryViewHolder(binding)
    }
    
    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
    
    override fun submitList(list: List<String>?) {
        // Hanini Kuralları'nı en üste taşı
        val sortedList = list?.sortedWith { a, b ->
            when {
                a == "HANİNİ KURALLARI" -> -1
                b == "HANİNİ KURALLARI" -> 1
                else -> a.compareTo(b)
            }
        }
        super.submitList(sortedList)
    }
    
    inner class CategoryViewHolder(
        private val binding: ItemCategoryBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        
        fun bind(category: String) {
            binding.textViewCategoryName.text = category
            
            // Her kategori için uygun emoji seç ve ikon container'da göster
            val emoji = getEmojiForCategory(category)
            binding.textViewCategoryIcon.text = emoji
            
            // Hanini Kuralları için kırmızı renk
            if (category == "HANİNİ KURALLARI") {
                binding.textViewCategoryName.setTextColor(
                    ContextCompat.getColor(binding.root.context, R.color.red_500)
                )
            } else {
                binding.textViewCategoryName.setTextColor(
                    ContextCompat.getColor(binding.root.context, R.color.text_primary)
                )
            }
            
            binding.root.setOnClickListener {
                onCategoryClick(category)
            }
        }
        
        private fun getEmojiForCategory(category: String): String {
            return when (category) {
                "Genel Kurallar" -> "📋"
                "Oyuna Başlarken" -> "🎯"
                "Gösterge ve Okey" -> "🎲"
                "El Açma ve Perler" -> "🃏"
                "Çiftler" -> "👥"
                "İşleme Kuralları" -> "⚙️"
                "Oyunu Bitirme" -> "🏁"
                "Puanlama ve Cezalar" -> "📊"
                "101 Ceza Kuralları" -> "⚠️"
                "Özel Durumlar" -> "🔍"
                "HANİNİ KURALLARI" -> "🚨"
                else -> "🀄"
            }
        }
    }
    
    class CategoryDiffCallback : DiffUtil.ItemCallback<String>() {
        override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }
        
        override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }
    }
}
