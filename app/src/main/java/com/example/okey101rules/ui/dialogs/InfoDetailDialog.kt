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
import com.example.okey101rules.data.InfoCard
import com.example.okey101rules.databinding.DialogInfoDetailBinding

class InfoDetailDialog(
    context: Context,
    private val infoCard: InfoCard
) : Dialog(context, R.style.DialogTheme) {

    private lateinit var binding: DialogInfoDetailBinding
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
        binding = DialogInfoDetailBinding.inflate(layoutInflater)
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
        val emoji = getEmojiForInfoCard(infoCard.title)
        binding.textViewDetailTitle.text = "$emoji ${infoCard.title}"
        binding.textViewDetailDescription.text = getDetailedDescription(infoCard.title)
        
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

    private fun getDetailedDescription(title: String): String {
        return when (title) {
            "Oyun Ekipmanı" -> """
                🎲 Okey 101 oyunu için gerekli ekipmanlar:
                
                • 106 adet taş (4 farklı renkte)
                • Kırmızı, mavi, sarı ve siyah renkler
                • Her renkte 1-13 arası sayılar
                • 2 adet sahte okey taşı
                • Istaka (taş tutucu)
                • Puan cetveli
                • Oyuncu sayısına göre masa
                
                Bu ekipmanlar olmadan oyun oynanamaz. Taşların kaliteli ve dayanıklı olması önemlidir.
            """.trimIndent()
            
            "Oyuncu Sayısı" -> """
                👥 Okey 101 oyunu oyuncu sayısı kuralları:
                
                • Standart oyun 4 oyuncu ile oynanır
                • Saat yönünün tersine sıra takip edilir
                • Her oyuncuya 14 taş dağıtılır
                • İlk oyuncuya 15 taş dağıtılır
                • Kalan taşlar kapalı olarak ortada kalır
                • Gösterge taşı açık olarak ortaya konur
                
                Oyuncu sayısı değişirse kurallar da değişir. 3 oyuncu ile oynanabilir ama ideal değildir.
            """.trimIndent()
            
            "Taş Renkleri" -> """
                🌈 Okey 101'de kullanılan taş renkleri:
                
                • Kırmızı 🔴 - En sıcak renk
                • Mavi 🔵 - En soğuk renk  
                • Sarı 🟡 - En parlak renk
                • Siyah ⚫ - En koyu renk
                
                Her renkte 1'den 13'e kadar sayılar bulunur.
                Aynı renkteki taşlar seri oluşturabilir.
                Farklı renklerdeki aynı sayılar grup oluşturabilir.
                
                Renklerin görsel olarak ayırt edilebilir olması çok önemlidir.
            """.trimIndent()
            
            "Okey Sistemi" -> """
                🃏 Okey sistemi nasıl çalışır:
                
                • Gösterge taşı belirlenir
                • Gösterge taşının bir üstü okey olur
                • Okey taşı herhangi bir taşın yerine geçebilir
                • Sahte okey taşları da vardır
                • Okey taşları seri ve grup oluşturmada kullanılır
                • Okey taşı ile el açılabilir
                
                Örnek: Gösterge 5 ise, 6 okey olur.
                Okey sistemi oyunun en önemli parçasıdır.
            """.trimIndent()
            
            "Oyun Süresi" -> """
                ⏰ Okey 101 oyun süresi kuralları:
                
                • Oyun 3-11 tur arasında oynanır
                • Ortalama oyun süresi 30-60 dakikadır
                • Tur sayısı oyun başında belirlenir
                • En az puan toplayan oyuncu kazanır
                • Her tur sonunda puanlar toplanır
                • Toplam puan en az olan kazanır
                
                Hızlı oyun için 3-5 tur, uzun oyun için 7-11 tur tercih edilir.
                Süre kısıtlaması yoktur, oyuncuların hızına bağlıdır.
            """.trimIndent()
            
            "Puanlama" -> """
                📊 Okey 101 puanlama sistemi:
                
                • Açık taşların toplamı puan olur
                • En az puan toplayan oyuncu kazanır
                • Kapalı taşlar puan sayılmaz
                • Okey taşları 0 puan değerindedir
                • Sahte okey taşları 50 puan değerindedir
                • Her tur sonunda puanlar kaydedilir
                
                Örnek: Elinde 5, 7, 9 taşları varsa toplam 21 puan.
                Puanlama sistemi adil rekabet sağlar.
            """.trimIndent()
            
            "Kurallar" -> """
                📋 Okey 101 temel kuralları:
                
                • El açma için en az 1 seri veya grup gerekir
                • Seri: Aynı renkte ardışık sayılar (5-6-7)
                • Grup: Farklı renklerde aynı sayılar (5-5-5)
                • Per yapma zorunludur
                • Çift yapma serbesttir
                • Okey taşı ile el açılabilir
                
                Kurallara uymak oyunun adil olmasını sağlar.
                Her oyuncu kuralları bilmek zorundadır.
            """.trimIndent()
            
            "Strateji" -> """
                🧠 Okey 101 strateji ipuçları:
                
                • Taşları organize edin
                • Per fırsatlarını kaçırmayın
                • Rakibinizi gözlemleyin
                • Risk almayın, sabırlı olun
                • Okey taşlarını doğru kullanın
                • El açma zamanını iyi hesaplayın
                
                Strateji oyunun %70'ini oluşturur.
                Deneyim kazandıkça stratejiniz gelişir.
            """.trimIndent()
            
            "İpuçları" -> """
                💡 Okey 101 için pratik ipuçları:
                
                • Taşları renk ve sayıya göre düzenleyin
                • Rakibinizin attığı taşları takip edin
                • Per yapma fırsatlarını değerlendirin
                • Acele etmeyin, sabırlı olun
                • Okey taşlarını sona saklayın
                • El açma zamanını iyi hesaplayın
                
                Bu ipuçları ile oyununuzu geliştirebilirsiniz.
                Pratik yapmak en iyi öğrenme yöntemidir.
            """.trimIndent()
            
            "Tarihçe" -> """
                📚 Okey 101'in tarihçesi:
                
                • Türkiye'de çok popüler bir oyundur
                • Rummy oyunundan türemiştir
                • 20. yüzyılda yaygınlaşmıştır
                • Aile oyunu geleneği vardır
                • Kahvehanelerde sık oynanır
                • Turnuvalar düzenlenir
                
                Okey, Türk kültürünün önemli bir parçasıdır.
                Nesilden nesile aktarılan bir oyundur.
            """.trimIndent()
            
            else -> infoCard.description
        }
    }

    companion object {
        fun show(
            context: Context,
            infoCard: InfoCard
        ) {
            InfoDetailDialog(context, infoCard).show()
        }
    }
}
