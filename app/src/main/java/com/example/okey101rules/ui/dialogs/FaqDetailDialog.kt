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
import com.example.okey101rules.data.Faq
import com.example.okey101rules.databinding.DialogFaqDetailBinding

class FaqDetailDialog(
    context: Context,
    private val faq: Faq
) : Dialog(context, R.style.DialogTheme) {

    private lateinit var binding: DialogFaqDetailBinding
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
        binding = DialogFaqDetailBinding.inflate(layoutInflater)
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
        // Soru ve cevap ayarla
        val emoji = getEmojiForFaq(faq.question)
        binding.textViewDetailQuestion.text = "$emoji ${faq.question}"
        binding.textViewDetailAnswer.text = getDetailedAnswer(faq.question, faq.answer)
        
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
            question.contains("Kaç") -> "🔢"
            question.contains("Var") -> "✅"
            question.contains("Yok") -> "❌"
            else -> "❓"
        }
    }

    private fun getDetailedAnswer(question: String, answer: String): String {
        return when {
            question.contains("Gösterge") -> """
                🎲 Gösterge hakkında detaylı bilgi:
                
                • Hayır, 101 oyununda göstergenin herhangi bir fonksiyonu yoktur
                • Gösterge sadece okey belirlemek için kullanılır
                • Oyun sırasında gösterge ile ilgili özel kurallar yoktur
                • Gösterge sadece okey taşını belirlemek için açılır
                • Gösterge taşı oyunun başında belirlenir
                • Gösterge taşının bir üstü okey olur
                
                Gösterge sistemi sadece okey belirleme için kullanılır, oyunun kendisinde rol oynamaz.
            """.trimIndent()
            
            question.contains("çift") -> """
                👥 Çift açma kuralları:
                
                • 101 oyununda minimum 5 çift gerekir
                • Çiftler aynı renk ve sayıda olmalıdır
                • Çiftler farklı renklerde olabilir
                • Çiftler oyunu bitirmek için zorunludur
                • Çiftler per ile birlikte açılabilir
                • Çiftler okey taşı ile yapılabilir
                
                Çiftler oyunun temel kurallarından biridir ve mutlaka açılmalıdır.
            """.trimIndent()
            
            question.contains("12-13-1") -> """
                ❌ Geçersiz diziler:
                
                • Hayır, olmaz. 101 oyununda geçerli değildir
                • Sadece art arda gelen sayılar geçerlidir
                • 1-2-3 veya 11-12-13 gibi sıralı diziler olur
                • 12-13-1 gibi döngüsel diziler geçersizdir
                • Her seri aynı renkte olmalıdır
                • En az 3 taş gerekir
                
                Seriler sadece art arda gelen sayılardan oluşabilir, döngüsel değil.
            """.trimIndent()
            
            question.contains("Okey") -> """
                🃏 Okey taşı kullanımı:
                
                • Okey herhangi bir taşın yerine geçebilir
                • Gerçek okey ve sahte okey vardır
                • Okey ile per yapılabilir
                • Okey ile çift yapılabilir
                • Okey taşı seri oluşturmada kullanılır
                • Okey taşı grup oluşturmada kullanılır
                
                Okey taşı oyunun en önemli parçasıdır ve stratejik olarak kullanılmalıdır.
            """.trimIndent()
            
            question.contains("Per") -> """
                🀄 Per yapma kuralları:
                
                • 3 veya daha fazla art arda gelen taşlar
                • Aynı renkte olmalıdır
                • En az 3 taş gerekir
                • Per ile oyunu bitirebilirsiniz
                • Per okey taşı ile yapılabilir
                • Per çiftlerle birlikte açılabilir
                
                Per yapmak oyunu kazanmanın en etkili yollarından biridir.
            """.trimIndent()
            
            question.contains("Taş") -> """
                🎯 Taş sayısı ve dağılımı:
                
                • Toplam 106 taş vardır
                • 4 farklı renkte 1-13 arası
                • Her sayıdan her renkte 2 adet
                • 2 adet sahte okey taşı
                • Her oyuncuya 14 taş dağıtılır
                • İlk oyuncuya 15 taş dağıtılır
                
                Taş dağılımı oyunun adil olmasını sağlar.
            """.trimIndent()
            
            question.contains("Puan") -> """
                📊 Puanlama sistemi:
                
                • Açık taşların toplamı puan olur
                • En az puan toplayan oyuncu kazanır
                • Kapalı taşlar puan sayılmaz
                • Okey taşları 0 puan değerindedir
                • Sahte okey taşları 50 puan değerindedir
                • Her tur sonunda puanlar kaydedilir
                
                Puanlama sistemi oyunun rekabetçi olmasını sağlar.
            """.trimIndent()
            
            question.contains("Kural") -> """
                📋 Temel kurallar:
                
                • El açma için en az 1 seri veya grup gerekir
                • Seri: Aynı renkte ardışık sayılar (5-6-7)
                • Grup: Farklı renklerde aynı sayılar (5-5-5)
                • Per yapma zorunludur
                • Çift yapma serbesttir
                • Okey taşı ile el açılabilir
                
                Kurallara uymak oyunun adil olmasını sağlar.
            """.trimIndent()
            
            else -> answer
        }
    }

    companion object {
        fun show(
            context: Context,
            faq: Faq
        ) {
            FaqDetailDialog(context, faq).show()
        }
    }
}
