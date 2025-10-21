package com.example.okey101rules.ui.settings

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.OvershootInterpolator
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.okey101rules.databinding.FragmentSettingsBinding
import kotlinx.coroutines.*
import java.util.*
import javax.mail.*
import javax.mail.internet.*

class SettingsFragment : Fragment() {

    private var _binding: FragmentSettingsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSettingsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        setupAnimations()
        setupCardAnimations()
        setupSocialMediaLinks()
        setupReportError()
    }

    private fun setupReportError() {
        binding.sendErrorButton.setOnClickListener {
            sendErrorReport()
        }
    }

    private fun sendErrorReport() {
        val errorText = binding.errorDescription.text.toString().trim()
        
        if (errorText.isEmpty()) {
            Toast.makeText(requireContext(), "Lütfen hata açıklaması girin!", Toast.LENGTH_SHORT).show()
            return
        }
        
        // Gönder butonunu devre dışı bırak
        binding.sendErrorButton.isEnabled = false
        binding.sendErrorButton.text = "Gönderiliyor..."
        
        // Arka planda mail gönder
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val success = sendEmailDirectly(errorText)
                
                withContext(Dispatchers.Main) {
                    binding.sendErrorButton.isEnabled = true
                    binding.sendErrorButton.text = "Gönder"
                    
                    if (success) {
                        Toast.makeText(requireContext(), "✅ Mesajınız başarıyla gönderildi!", Toast.LENGTH_LONG).show()
                        binding.errorDescription.text?.clear()
                    } else {
                        Toast.makeText(requireContext(), "❌ Mail gönderilemedi. Lütfen tekrar deneyin.", Toast.LENGTH_LONG).show()
                    }
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    binding.sendErrorButton.isEnabled = true
                    binding.sendErrorButton.text = "Gönder"
                    Toast.makeText(requireContext(), "Bir hata oluştu: ${e.message}", Toast.LENGTH_LONG).show()
                }
            }
        }
    }
    
    private suspend fun sendEmailDirectly(errorText: String): Boolean {
        return withContext(Dispatchers.IO) {
            try {
                val props = Properties().apply {
                    put("mail.smtp.host", "smtp.gmail.com")
                    put("mail.smtp.port", "587")
                    put("mail.smtp.auth", "true")
                    put("mail.smtp.starttls.enable", "true")
                    put("mail.smtp.ssl.trust", "smtp.gmail.com")
                }
                
                val session = Session.getInstance(props, object : Authenticator() {
                    override fun getPasswordAuthentication(): PasswordAuthentication {
                        // Gmail App Password kullanın (2FA aktifse)
                        // Gmail → Hesap → Güvenlik → Uygulama şifreleri → Mail şifresi oluştur
                        return PasswordAuthentication("kerimayvaz7@gmail.com", "sfdv igcz dtqd awbj")
                    }
                })
                
                val message = MimeMessage(session).apply {
                    setFrom(InternetAddress("kerimayvaz7@gmail.com"))
                    setRecipients(Message.RecipientType.TO, arrayOf(InternetAddress("kerimayvaz7@gmail.com")))
                    subject = "🀄 OKEY 101 KURALLARI - Hata Bildirimi"
                    setText("""
                        Merhaba Kerim,
                        
                        Uygulamada karşılaştığım hatayı bildirmek istiyorum:
                        
                        Hata Açıklaması:
                        $errorText
                        
                        Cihaz Bilgileri:
                        - Android Sürümü: ${android.os.Build.VERSION.RELEASE}
                        - Cihaz Modeli: ${android.os.Build.MODEL}
                        - Uygulama Versiyonu: 2.0
                        
                        Teşekkürler!
                    """.trimIndent())
                }
                
                Transport.send(message)
                true
            } catch (e: Exception) {
                e.printStackTrace()
                false
            }
        }
    }
    

    private fun setupSocialMediaLinks() {
        // Instagram link
        binding.instagramRow.setOnClickListener {
            openInstagramProfile()
        }
        
        binding.instagramLink.setOnClickListener {
            openInstagramProfile()
        }

        // GitHub link
        binding.githubRow.setOnClickListener {
            openGitHubProfile()
        }
        
        binding.githubLink.setOnClickListener {
            openGitHubProfile()
        }
    }

    private fun openInstagramProfile() {
        try {
            val instagramUrl = getString(com.example.okey101rules.R.string.instagram_url)
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(instagramUrl))
            startActivity(intent)
        } catch (e: Exception) {
            // Fallback to web browser if Instagram app is not installed
            val webUrl = "https://www.instagram.com/kerimayvaz0"
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(webUrl))
            startActivity(intent)
        }
    }

    private fun openGitHubProfile() {
        try {
            val githubUrl = getString(com.example.okey101rules.R.string.github_url)
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(githubUrl))
            startActivity(intent)
        } catch (e: Exception) {
            // Fallback to web browser
            val webUrl = "https://github.com/kerimayvaz77"
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(webUrl))
            startActivity(intent)
        }
    }

    private fun setupAnimations() {
        // Floating emoji animation
        val emojiAnimation = ObjectAnimator.ofFloat(binding.settingsEmoji, "rotation", 0f, 360f)
        emojiAnimation.duration = 3000
        emojiAnimation.repeatCount = ObjectAnimator.INFINITE
        emojiAnimation.repeatMode = ObjectAnimator.RESTART
        emojiAnimation.interpolator = OvershootInterpolator(0.5f)
        emojiAnimation.start()

        // Subtle pulsing animation for emoji
        val pulseAnimation = ObjectAnimator.ofFloat(binding.settingsEmoji, "scaleX", 1.0f, 1.1f, 1.0f)
        pulseAnimation.duration = 2000
        pulseAnimation.repeatCount = ObjectAnimator.INFINITE
        pulseAnimation.repeatMode = ObjectAnimator.REVERSE
        pulseAnimation.start()

        val pulseAnimationY = ObjectAnimator.ofFloat(binding.settingsEmoji, "scaleY", 1.0f, 1.1f, 1.0f)
        pulseAnimationY.duration = 2000
        pulseAnimationY.repeatCount = ObjectAnimator.INFINITE
        pulseAnimationY.repeatMode = ObjectAnimator.REVERSE
        pulseAnimationY.start()
    }

    private fun setupCardAnimations() {
        // Staggered card entrance animations
        val cards = listOf(
            binding.programInfoCard,
            binding.personalInfoCard,
            binding.reportErrorCard,
            binding.appDescriptionCard
        )

        cards.forEachIndexed { index, card ->
            card.alpha = 0f
            card.translationY = 100f
            
            val animatorSet = AnimatorSet()
            
            val alphaAnim = ObjectAnimator.ofFloat(card, "alpha", 0f, 1f)
            val translateAnim = ObjectAnimator.ofFloat(card, "translationY", 100f, 0f)
            val scaleAnim = ObjectAnimator.ofFloat(card, "scaleX", 0.8f, 1.0f)
            val scaleAnimY = ObjectAnimator.ofFloat(card, "scaleY", 0.8f, 1.0f)
            
            alphaAnim.duration = 600
            translateAnim.duration = 600
            scaleAnim.duration = 600
            scaleAnimY.duration = 600
            
            translateAnim.interpolator = OvershootInterpolator(1.2f)
            scaleAnim.interpolator = OvershootInterpolator(1.2f)
            scaleAnimY.interpolator = OvershootInterpolator(1.2f)
            
            animatorSet.playTogether(alphaAnim, translateAnim, scaleAnim, scaleAnimY)
            animatorSet.startDelay = (index * 200).toLong()
            animatorSet.start()
        }

        // Row animations
        val rows = listOf(
            binding.appNameRow,
            binding.versionRow,
            binding.developerRow,
            binding.releaseDateRow,
            binding.platformRow,
            binding.nameRow,
            binding.roleRow,
            binding.contactRow,
            binding.instagramRow,
            binding.githubRow
        )

        rows.forEachIndexed { index, row ->
            row.alpha = 0f
            row.translationX = -50f
            
            val rowAnimatorSet = AnimatorSet()
            
            val rowAlphaAnim = ObjectAnimator.ofFloat(row, "alpha", 0f, 1f)
            val rowTranslateAnim = ObjectAnimator.ofFloat(row, "translationX", -50f, 0f)
            
            rowAlphaAnim.duration = 400
            rowTranslateAnim.duration = 400
            rowTranslateAnim.interpolator = OvershootInterpolator(1.0f)
            
            rowAnimatorSet.playTogether(rowAlphaAnim, rowTranslateAnim)
            rowAnimatorSet.startDelay = (1000 + index * 100).toLong()
            rowAnimatorSet.start()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
