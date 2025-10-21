package com.example.okey101rules.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.okey101rules.data.Strategy
import com.example.okey101rules.databinding.ItemStrategyBinding

class StrategyAdapter : ListAdapter<Strategy, StrategyAdapter.StrategyViewHolder>(StrategyDiffCallback()) {

    private var onItemClickListener: ((Strategy) -> Unit)? = null

    fun setOnItemClickListener(listener: (Strategy) -> Unit) {
        onItemClickListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StrategyViewHolder {
        val binding = ItemStrategyBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return StrategyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: StrategyViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class StrategyViewHolder(
        private val binding: ItemStrategyBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(strategy: Strategy) {
            binding.textViewTitle.text = strategy.title
            binding.textViewDescription.text = strategy.description
            
            // Her strateji için uygun emoji seç ve ikon container'da göster
            val emoji = getEmojiForStrategy(strategy.title, strategy.category)
            binding.textViewEmoji.text = emoji
            
            // Click listener ekle
            binding.root.setOnClickListener {
                onItemClickListener?.invoke(strategy)
            }
        }
        
        private fun getEmojiForStrategy(title: String, category: String): String {
            return when {
                title.contains("El Açma") -> "🃏"
                title.contains("Okey") -> "🀄"
                title.contains("Rakip") -> "👁️"
                title.contains("Takip") -> "👁️"
                title.contains("Per") -> "🎯"
                title.contains("Çift") -> "👥"
                title.contains("Taş") -> "🎲"
                title.contains("Puan") -> "📊"
                title.contains("Risk") -> "⚠️"
                title.contains("Zaman") -> "⏰"
                title.contains("Sabır") -> "🧘"
                title.contains("Analiz") -> "🔍"
                title.contains("Plan") -> "📋"
                title.contains("Hız") -> "⚡"
                title.contains("Dikkat") -> "👀"
                category.contains("Genel") -> "🎯"
                category.contains("Okey") -> "🀄"
                category.contains("Takip") -> "👁️"
                else -> "🧠"
            }
        }
    }

    class StrategyDiffCallback : DiffUtil.ItemCallback<Strategy>() {
        override fun areItemsTheSame(oldItem: Strategy, newItem: Strategy): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Strategy, newItem: Strategy): Boolean {
            return oldItem == newItem
        }
    }
}
