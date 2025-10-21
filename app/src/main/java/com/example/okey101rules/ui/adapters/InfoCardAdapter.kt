package com.example.okey101rules.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.okey101rules.R
import com.example.okey101rules.data.InfoCard
import com.example.okey101rules.databinding.ItemInfoCardBinding

class InfoCardAdapter : ListAdapter<InfoCard, InfoCardAdapter.InfoCardViewHolder>(InfoCardDiffCallback()) {

    private var onItemClickListener: ((InfoCard) -> Unit)? = null

    fun setOnItemClickListener(listener: (InfoCard) -> Unit) {
        onItemClickListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InfoCardViewHolder {
        val binding = ItemInfoCardBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return InfoCardViewHolder(binding)
    }

    override fun onBindViewHolder(holder: InfoCardViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class InfoCardViewHolder(
        private val binding: ItemInfoCardBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(infoCard: InfoCard) {
            binding.textViewTitle.text = infoCard.title
            binding.textViewDescription.text = infoCard.description
            
            // Her bilgi kartı için uygun emoji seç ve ikon container'da göster
            val emoji = getEmojiForInfoCard(infoCard.title)
            binding.textViewEmoji.text = emoji
            
            // Click listener ekle
            binding.root.setOnClickListener {
                onItemClickListener?.invoke(infoCard)
            }
        }
        
        private fun getEmojiForInfoCard(title: String): String {
            return when (title) {
                "Oyun Ekipmanı" -> "🎲"
                "Oyuncu Sayısı" -> "👥"
                "Taş Renkleri" -> "🌈"
                "Okey Sistemi" -> "🃏"
                "Oyun Süresi" -> "⏰"
                "Puanlama" -> "📊"
                "Kurallar" -> "📋"
                "Strateji" -> "🧠"
                "İpuçları" -> "💡"
                "Tarihçe" -> "📚"
                else -> "ℹ️"
            }
        }
    }

    class InfoCardDiffCallback : DiffUtil.ItemCallback<InfoCard>() {
        override fun areItemsTheSame(oldItem: InfoCard, newItem: InfoCard): Boolean {
            return oldItem.title == newItem.title
        }

        override fun areContentsTheSame(oldItem: InfoCard, newItem: InfoCard): Boolean {
            return oldItem == newItem
        }
    }
}
