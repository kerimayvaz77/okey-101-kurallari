package com.example.okey101rules.ui.burak

import android.animation.AnimatorInflater
import android.animation.ObjectAnimator
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.okey101rules.R
import com.example.okey101rules.databinding.FragmentBurakBinding

class BurakFragment : Fragment() {
    
    private var _binding: FragmentBurakBinding? = null
    private val binding get() = _binding!!
    
    private lateinit var viewModel: BurakViewModel
    private lateinit var sharedPreferences: SharedPreferences
    
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBurakBinding.inflate(inflater, container, false)
        return binding.root
    }
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        sharedPreferences = requireContext().getSharedPreferences("burak_stats", Context.MODE_PRIVATE)
        viewModel = ViewModelProvider(this)[BurakViewModel::class.java]
        
        setupUI()
        observeViewModel()
        loadStats()
    }
    
    private fun setupUI() {
        // Start entrance animations
        startEntranceAnimations()
        
        binding.buttonWin.setOnClickListener {
            animateButtonPress(binding.buttonWin)
            viewModel.incrementWins()
            saveStats()
        }
        
        binding.buttonLose.setOnClickListener {
            animateButtonPress(binding.buttonLose)
            viewModel.incrementLosses()
            saveStats()
        }
        
        binding.buttonReset.setOnClickListener {
            animateButtonPress(binding.buttonReset)
            viewModel.resetStats()
            saveStats()
        }
    }
    
    private fun startEntranceAnimations() {
        // Animate cards appearing
        binding.imageViewBurak.startAnimation(AnimationUtils.loadAnimation(context, R.anim.card_appear))
        
        // Delay for stats card
        binding.root.postDelayed({
            binding.textMood.startAnimation(AnimationUtils.loadAnimation(context, R.anim.card_appear))
        }, 200)
    }
    
    private fun animateButtonPress(button: View) {
        val scaleDown = ObjectAnimator.ofFloat(button, "scaleX", 1f, 0.95f)
        val scaleDownY = ObjectAnimator.ofFloat(button, "scaleY", 1f, 0.95f)
        val scaleUp = ObjectAnimator.ofFloat(button, "scaleX", 0.95f, 1f)
        val scaleUpY = ObjectAnimator.ofFloat(button, "scaleY", 0.95f, 1f)
        
        scaleDown.duration = 100
        scaleDownY.duration = 100
        scaleUp.duration = 100
        scaleUpY.duration = 100
        
        scaleDown.start()
        scaleDownY.start()
        scaleUp.startDelay = 100
        scaleUpY.startDelay = 100
        scaleUp.start()
        scaleUpY.start()
    }
    
    private fun observeViewModel() {
        viewModel.wins.observe(viewLifecycleOwner) { wins ->
            binding.textWins.text = wins.toString()
            updateUI()
        }
        
        viewModel.losses.observe(viewLifecycleOwner) { losses ->
            binding.textLosses.text = losses.toString()
            updateUI()
        }
    }
    
    private fun loadStats() {
        val wins = sharedPreferences.getInt("wins", 0)
        val losses = sharedPreferences.getInt("losses", 0)
        viewModel.setStats(wins, losses)
    }
    
    private fun saveStats() {
        sharedPreferences.edit()
            .putInt("wins", viewModel.wins.value ?: 0)
            .putInt("losses", viewModel.losses.value ?: 0)
            .apply()
    }
    
    private fun updateUI() {
        val wins = viewModel.wins.value ?: 0
        val losses = viewModel.losses.value ?: 0
        
        when {
            wins > losses -> {
                // Kazanma durumu - mutlu görünüm
                binding.root.setBackgroundColor(resources.getColor(android.R.color.holo_green_light, null))
                binding.textMood.text = "🎉 BURAK İLE YENDİN! 🎉"
                binding.textMood.setTextColor(resources.getColor(android.R.color.holo_green_dark, null))
                animateCelebration()
            }
            losses > wins -> {
                // Yenilme durumu - üzgün görünüm
                binding.root.setBackgroundColor(resources.getColor(android.R.color.holo_red_light, null))
                binding.textMood.text = "😢 BURAK İLE YENİLDİN, ŞAŞIRILMADIK DURUM! 😢"
                binding.textMood.setTextColor(resources.getColor(android.R.color.holo_red_dark, null))
                animateSadness()
            }
            else -> {
                // Beraberlik durumu - nötr görünüm
                binding.root.setBackgroundColor(resources.getColor(android.R.color.holo_blue_light, null))
                binding.textMood.text = "⚖️ BERABERE! ⚖️"
                binding.textMood.setTextColor(resources.getColor(android.R.color.holo_blue_dark, null))
                animateNeutral()
            }
        }
    }
    
    private fun animateCelebration() {
        // Spin animation for celebration
        binding.textMood.startAnimation(AnimationUtils.loadAnimation(context, R.anim.spin))
    }
    
    private fun animateSadness() {
        // Shake animation for sadness
        val shake = ObjectAnimator.ofFloat(binding.textMood, "translationX", 0f, 25f, -25f, 25f, -25f, 15f, -15f, 6f, -6f, 0f)
        shake.duration = 500
        shake.start()
    }
    
    private fun animateNeutral() {
        // Gentle pulse animation
        val pulse = ObjectAnimator.ofFloat(binding.textMood, "alpha", 1f, 0.7f, 1f)
        pulse.duration = 1000
        pulse.repeatCount = 1
        pulse.start()
    }
    
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
