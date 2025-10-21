package com.example.okey101rules.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.okey101rules.data.Faq
import com.example.okey101rules.databinding.ItemFaqBinding

class FaqAdapter : ListAdapter<Faq, FaqAdapter.FaqViewHolder>(FaqDiffCallback()) {

    private var onItemClickListener: ((Faq) -> Unit)? = null

    fun setOnItemClickListener(listener: (Faq) -> Unit) {
        onItemClickListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FaqViewHolder {
        val binding = ItemFaqBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return FaqViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FaqViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class FaqViewHolder(
        private val binding: ItemFaqBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(faq: Faq) {
            binding.textViewQuestion.text = faq.question
            binding.textViewAnswer.text = faq.answer
            
            // Her soru için uygun emoji seç ve ikon container'da göster
            val emoji = getEmojiForFaq(faq.question)
            binding.textViewEmoji.text = emoji
            
            // Click listener ekle
            binding.root.setOnClickListener {
                onItemClickListener?.invoke(faq)
            }
        }
        
        private fun getEmojiForFaq(question: String): String {
            return when {
                question.contains("Gösterge") -> "🎲"
                question.contains("çift") -> "👥"
                question.contains("12-13-1") -> "❌"
                question.contains("Okey") -> "🃏"
                question.contains("Per") -> "🀄"
                question.contains("Taş") -> "🎯"
                question.contains("Puan") -> "📊"
                question.contains("Kural") -> "📋"
                question.contains("Nasıl") -> "❓"
                question.contains("Ne") -> "🤔"
                else -> "❓"
            }
        }
    }

    class FaqDiffCallback : DiffUtil.ItemCallback<Faq>() {
        override fun areItemsTheSame(oldItem: Faq, newItem: Faq): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Faq, newItem: Faq): Boolean {
            return oldItem == newItem
        }
    }
}
