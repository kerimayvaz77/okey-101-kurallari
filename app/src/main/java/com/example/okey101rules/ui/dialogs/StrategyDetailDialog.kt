package com.example.okey101rules.ui.dialogs

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ObjectAnimator
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.view.WindowManager
import android.view.animation.DecelerateInterpolator
import androidx.core.content.ContextCompat
import com.example.okey101rules.R
import com.example.okey101rules.data.Strategy
import com.example.okey101rules.databinding.DialogStrategyDetailBinding

class StrategyDetailDialog(
    context: Context,
    private val strategy: Strategy
) : Dialog(context, R.style.DialogTheme) {

    private lateinit var binding: DialogStrategyDetailBinding
    private var isAnimating = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        // Dialog tema ayarları
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window?.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )
        
        // Dialog arka planını şeffaf yap
        window?.setBackgroundDrawableResource(android.R.color.transparent)
        
        // Layout'u bağla
        binding = DialogStrategyDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        setupViews()
        setupClickListeners()
        
        // Dialog boyutlarını ayarla
        window?.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )
    }

    private fun setupViews() {
        // Başlık ve açıklama ayarla
        val emoji = getEmojiForStrategy(strategy.title, strategy.category)
        binding.textViewDetailTitle.text = "$emoji ${strategy.title}"
        binding.textViewDetailDescription.text = getDetailedDescription(strategy.title, strategy.description)
        
        // Emoji ayarla
        binding.textViewDetailEmoji.text = emoji
        
        // İlk durumda görünmez yap
        binding.backgroundOverlay.alpha = 0f
        binding.dialogCard.alpha = 0f
        binding.dialogCard.scaleX = 0.8f
        binding.dialogCard.scaleY = 0.8f
    }

    private fun setupClickListeners() {
        // Arka plana tıklama
        binding.backgroundOverlay.setOnClickListener {
            dismissWithAnimation()
        }
        
        // Çıkış butonu
        binding.buttonExit.setOnClickListener {
            dismissWithAnimation()
        }
    }

    override fun show() {
        super.show()
        showWithAnimation()
    }

    private fun showWithAnimation() {
        if (isAnimating) return
        isAnimating = true
        
        // Arka plan fade in
        val backgroundAnimator = ObjectAnimator.ofFloat(binding.backgroundOverlay, "alpha", 0f, 1f)
        backgroundAnimator.duration = 300
        backgroundAnimator.interpolator = DecelerateInterpolator()
        
        // Kart animasyonu
        val scaleXAnimator = ObjectAnimator.ofFloat(binding.dialogCard, "scaleX", 0.8f, 1f)
        val scaleYAnimator = ObjectAnimator.ofFloat(binding.dialogCard, "scaleY", 0.8f, 1f)
        val alphaAnimator = ObjectAnimator.ofFloat(binding.dialogCard, "alpha", 0f, 1f)
        
        scaleXAnimator.duration = 300
        scaleYAnimator.duration = 300
        alphaAnimator.duration = 300
        
        scaleXAnimator.interpolator = DecelerateInterpolator()
        scaleYAnimator.interpolator = DecelerateInterpolator()
        alphaAnimator.interpolator = DecelerateInterpolator()
        
        // Animasyonları başlat
        backgroundAnimator.start()
        scaleXAnimator.start()
        scaleYAnimator.start()
        alphaAnimator.start()
        
        // Animasyon bitince flag'i sıfırla
        alphaAnimator.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator) {
                isAnimating = false
            }
        })
    }

    private fun dismissWithAnimation() {
        if (isAnimating) return
        isAnimating = true
        
        // Arka plan fade out
        val backgroundAnimator = ObjectAnimator.ofFloat(binding.backgroundOverlay, "alpha", 1f, 0f)
        backgroundAnimator.duration = 250
        
        // Kart animasyonu
        val scaleXAnimator = ObjectAnimator.ofFloat(binding.dialogCard, "scaleX", 1f, 0.8f)
        val scaleYAnimator = ObjectAnimator.ofFloat(binding.dialogCard, "scaleY", 1f, 0.8f)
        val alphaAnimator = ObjectAnimator.ofFloat(binding.dialogCard, "alpha", 1f, 0f)
        
        scaleXAnimator.duration = 250
        scaleYAnimator.duration = 250
        alphaAnimator.duration = 250
        
        // Animasyonları başlat
        backgroundAnimator.start()
        scaleXAnimator.start()
        scaleYAnimator.start()
        alphaAnimator.start()
        
        // Animasyon bitince dialog'u kapat
        alphaAnimator.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator) {
                isAnimating = false
                dismiss()
            }
        })
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

    private fun getDetailedDescription(title: String, description: String): String {
        return when {
            title.contains("El Açma") -> """
                🃏 El Açma Stratejisi:
                
                • El açmadan önce taşlarınızı kontrol edin
                • En az 5 çift olduğundan emin olun
                • Per yapma fırsatlarını değerlendirin
                • Risk almadan önce düşünün
                • El açma zamanını iyi hesaplayın
                • Rakibin durumunu göz önünde bulundurun
                
                El açma oyunun en kritik anlarından biridir. Doğru zamanda açmak çok önemlidir.
            """.trimIndent()
            
            title.contains("Okey") -> """
                🀄 Okey Kullanım Stratejisi:
                
                • Okey'i mümkün olduğunca geç kullanın
                • Okey çok değerli bir taştır
                • Gerçek okey ile sahte okey'i ayırt edin
                • Okey ile per yapma fırsatlarını kaçırmayın
                • Okey ile çift yapma fırsatlarını değerlendirin
                • Okey'i stratejik olarak kullanın
                
                Okey taşı oyunun en güçlü silahıdır. Doğru kullanımı oyunu kazanmanızı sağlar.
            """.trimIndent()
            
            title.contains("Rakip") -> """
                👁️ Rakip Takip Stratejisi:
                
                • Rakip oyuncuların açtığı taşları takip edin
                • Hangi taşları istediğini analiz edin
                • Rakibin elini tahmin etmeye çalışın
                • Stratejinizi buna göre belirleyin
                • Rakibin oyun tarzını öğrenin
                • Rakibin zayıflıklarını tespit edin
                
                Rakip analizi oyunu kazanmanın en önemli faktörlerinden biridir.
            """.trimIndent()
            
            title.contains("Per") -> """
                🎯 Per Yapma Teknikleri:
                
                • 3 veya daha fazla art arda taşları gruplayın
                • Aynı renkte taşları bir arada tutun
                • Per yapma fırsatlarını kaçırmayın
                • Per ile oyunu hızla bitirin
                • Per yapma zamanını iyi hesaplayın
                • Per ile çiftleri birleştirin
                
                Per yapmak oyunu kazanmanın en etkili yollarından biridir.
            """.trimIndent()
            
            title.contains("Çift") -> """
                👥 Çift Stratejisi:
                
                • Minimum 5 çift gereklidir
                • Çiftleri farklı renklerde yapın
                • Çift yapma fırsatlarını değerlendirin
                • Çiftler oyunu bitirmek için zorunludur
                • Çiftleri per ile birleştirin
                • Çift yapma zamanını iyi hesaplayın
                
                Çiftler oyunun temel kurallarından biridir ve mutlaka yapılmalıdır.
            """.trimIndent()
            
            title.contains("Taş") -> """
                🎲 Taş Saklama Stratejisi:
                
                • Değerli taşları saklayın
                • Rakibin ihtiyacı olan taşları saklayın
                • Taşları organize edin
                • Gereksiz taşları erken atın
                • Taşları renk ve sayıya göre düzenleyin
                • Taş saklama zamanını iyi hesaplayın
                
                Taş saklama stratejisi oyunu kazanmanın önemli bir parçasıdır.
            """.trimIndent()
            
            title.contains("Puan") -> """
                📊 Puan Yönetimi:
                
                • Puanları takip edin
                • En az puan toplamaya odaklanın
                • Puan hesaplamalarını doğru yapın
                • Puan risklerini değerlendirin
                • Puan stratejisini belirleyin
                • Puan yönetimini öğrenin
                
                Puan yönetimi oyunu kazanmanın temel stratejilerinden biridir.
            """.trimIndent()
            
            title.contains("Risk") -> """
                ⚠️ Risk Yönetimi:
                
                • Riskleri değerlendirin
                • Risk almadan önce düşünün
                • Risk-reward analizi yapın
                • Riskleri minimize edin
                • Risk stratejisini belirleyin
                • Risk yönetimini öğrenin
                
                Risk yönetimi oyunu kazanmanın önemli bir parçasıdır.
            """.trimIndent()
            
            title.contains("Zaman") -> """
                ⏰ Zaman Yönetimi:
                
                • Zamanı iyi kullanın
                • Acele etmeyin
                • Sabırlı olun
                • Zaman stratejisini belirleyin
                • Zaman yönetimini öğrenin
                • Zamanı doğru değerlendirin
                
                Zaman yönetimi oyunu kazanmanın önemli bir parçasıdır.
            """.trimIndent()
            
            title.contains("Sabır") -> """
                🧘 Sabır Stratejisi:
                
                • Sabırlı olun
                • Acele etmeyin
                • Doğru zamanı bekleyin
                • Sabır stratejisini belirleyin
                • Sabır yönetimini öğrenin
                • Sabırla oyunu kazanın
                
                Sabır oyunu kazanmanın en önemli faktörlerinden biridir.
            """.trimIndent()
            
            title.contains("Analiz") -> """
                🔍 Analiz Stratejisi:
                
                • Durumu analiz edin
                • Rakibi analiz edin
                • Taşları analiz edin
                • Analiz stratejisini belirleyin
                • Analiz yönetimini öğrenin
                • Analizle oyunu kazanın
                
                Analiz oyunu kazanmanın en önemli faktörlerinden biridir.
            """.trimIndent()
            
            title.contains("Plan") -> """
                📋 Planlama Stratejisi:
                
                • Oyunu planlayın
                • Stratejinizi belirleyin
                • Planınızı uygulayın
                • Plan stratejisini belirleyin
                • Plan yönetimini öğrenin
                • Planla oyunu kazanın
                
                Planlama oyunu kazanmanın en önemli faktörlerinden biridir.
            """.trimIndent()
            
            title.contains("Hız") -> """
                ⚡ Hız Stratejisi:
                
                • Hızlı karar verin
                • Hızlı hareket edin
                • Hız stratejisini belirleyin
                • Hız yönetimini öğrenin
                • Hızla oyunu kazanın
                • Hızı doğru kullanın
                
                Hız oyunu kazanmanın önemli faktörlerinden biridir.
            """.trimIndent()
            
            title.contains("Dikkat") -> """
                👀 Dikkat Stratejisi:
                
                • Dikkatli olun
                • Detayları gözden kaçırmayın
                • Dikkat stratejisini belirleyin
                • Dikkat yönetimini öğrenin
                • Dikkatle oyunu kazanın
                • Dikkati doğru kullanın
                
                Dikkat oyunu kazanmanın önemli faktörlerinden biridir.
            """.trimIndent()
            
            else -> description
        }
    }

    companion object {
        fun show(
            context: Context,
            strategy: Strategy
        ) {
            StrategyDetailDialog(context, strategy).show()
        }
    }
}
