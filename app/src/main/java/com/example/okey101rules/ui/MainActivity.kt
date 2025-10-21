package com.example.okey101rules.ui

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import android.view.View
import android.view.animation.OvershootInterpolator
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.okey101rules.R
import com.example.okey101rules.database.DatabasePopulator
import com.example.okey101rules.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    
    private lateinit var binding: ActivityMainBinding
    
    @Inject
    lateinit var databasePopulator: DatabasePopulator
    
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        // Hide ActionBar completely
        supportActionBar?.hide()
        
        // Populate database on first launch
        CoroutineScope(Dispatchers.IO).launch {
            databasePopulator.populateIfNeeded()
        }
        
        setupNavigation()
        setupCustomToolbar()
        setupBottomNavigationAnimations()
    }
    
    private fun setupNavigation() {
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        
        // Setup Custom Bottom Navigation with Emojis
        setupCustomBottomNavigation(navController)
    }
    
    private fun setupCustomBottomNavigation(navController: androidx.navigation.NavController) {
        // Set click listeners for each tab
        binding.homeFragment.setOnClickListener {
            setSelectedTab(R.id.homeFragment)
            animateBottomNavItem(R.id.homeFragment, true)
            navController.navigate(R.id.homeFragment)
        }
        
        binding.generalInfoFragment.setOnClickListener {
            setSelectedTab(R.id.generalInfoFragment)
            animateBottomNavItem(R.id.generalInfoFragment, true)
            navController.navigate(R.id.generalInfoFragment)
        }
        
        binding.faqFragment.setOnClickListener {
            setSelectedTab(R.id.faqFragment)
            animateBottomNavItem(R.id.faqFragment, true)
            navController.navigate(R.id.faqFragment)
        }
        
        binding.strategyFragment.setOnClickListener {
            setSelectedTab(R.id.strategyFragment)
            animateBottomNavItem(R.id.strategyFragment, true)
            navController.navigate(R.id.strategyFragment)
        }
        
        binding.burakFragment.setOnClickListener {
            setSelectedTab(R.id.burakFragment)
            animateBottomNavItem(R.id.burakFragment, true)
            navController.navigate(R.id.burakFragment)
        }
        
        // Set initial selected tab
        setSelectedTab(R.id.homeFragment)
    }
    
    private fun setSelectedTab(selectedTabId: Int) {
        // Reset all tabs to unselected state
        binding.homeFragment.setBackgroundColor(android.graphics.Color.TRANSPARENT)
        binding.generalInfoFragment.setBackgroundColor(android.graphics.Color.TRANSPARENT)
        binding.faqFragment.setBackgroundColor(android.graphics.Color.TRANSPARENT)
        binding.strategyFragment.setBackgroundColor(android.graphics.Color.TRANSPARENT)
        binding.burakFragment.setBackgroundColor(android.graphics.Color.TRANSPARENT)
        
        // Set selected tab background
        val selectedTab = when (selectedTabId) {
            R.id.homeFragment -> binding.homeFragment
            R.id.generalInfoFragment -> binding.generalInfoFragment
            R.id.faqFragment -> binding.faqFragment
            R.id.strategyFragment -> binding.strategyFragment
            R.id.burakFragment -> binding.burakFragment
            else -> null
        }
        
        selectedTab?.setBackgroundColor(android.graphics.Color.parseColor("#30FFFFFF"))
    }
    
    private fun setupCustomToolbar() {
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        
        // Setup modern back button
        setupModernBackButton()
        
        // Start the animated gradient background
        val background = resources.getDrawable(R.drawable.animated_gradient, null) as? AnimationDrawable
        background?.start()
        
        // Add subtle pulsing animation to the title
        binding.actionbarTitle.animate()
            .scaleX(1.03f)
            .scaleY(1.03f)
            .setDuration(3000)
            .withEndAction {
                binding.actionbarTitle.animate()
                    .scaleX(1.0f)
                    .scaleY(1.0f)
                    .setDuration(3000)
                    .start()
            }
            .start()
    }
    
    private fun setupModernBackButton() {
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        
        // Show/hide back button based on navigation state
        navController.addOnDestinationChangedListener { _, destination, _ ->
            val isTopLevelDestination = destination.id in setOf(
                R.id.homeFragment,
                R.id.generalInfoFragment,
                R.id.faqFragment,
                R.id.strategyFragment,
                R.id.burakFragment
            )
            binding.backButton.visibility = if (isTopLevelDestination) android.view.View.GONE else android.view.View.VISIBLE
        }
        
        // Set click listener for modern back button
        binding.backButton.setOnClickListener {
            // Add enhanced press animation
            binding.backButton.startAnimation(android.view.animation.AnimationUtils.loadAnimation(this, R.anim.back_button_press))
            
            // Add haptic feedback
            binding.backButton.performHapticFeedback(android.view.HapticFeedbackConstants.VIRTUAL_KEY)
            
            // Navigate back
            navController.navigateUp()
            
            // Add enhanced release animation after a short delay
            android.os.Handler(android.os.Looper.getMainLooper()).postDelayed({
                binding.backButton.startAnimation(android.view.animation.AnimationUtils.loadAnimation(this, R.anim.back_button_release))
            }, 80)
        }
        
        // Add continuous subtle animation
        addContinuousAnimation()
    }
    
    private fun refreshHomeFragment() {
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        
        // Get current destination
        val currentDestination = navController.currentDestination
        
        // If we're not on home fragment, navigate to it first
        if (currentDestination?.id != R.id.homeFragment) {
            navController.navigate(R.id.homeFragment)
        }
        
        // Find home fragment and refresh it
        val homeFragment = navHostFragment.childFragmentManager
            .fragments.firstOrNull { it is com.example.okey101rules.ui.home.HomeFragment } 
            as? com.example.okey101rules.ui.home.HomeFragment
        
        // Check if there's an active search - if so, don't clear it
        val hasActiveSearch = homeFragment?.hasActiveSearch() ?: false
        homeFragment?.refreshFragment(clearSearch = !hasActiveSearch)
    }
    
    private fun setupBottomNavigationAnimations() {
        // Animation setup is now handled in setupCustomBottomNavigation
    }
    
    private var lastHomeTabClickTime = 0L
    
    private fun animateBottomNavItem(itemId: Int, isSelected: Boolean) {
        val view = when (itemId) {
            R.id.homeFragment -> binding.homeFragment
            R.id.generalInfoFragment -> binding.generalInfoFragment
            R.id.faqFragment -> binding.faqFragment
            R.id.strategyFragment -> binding.strategyFragment
            R.id.burakFragment -> binding.burakFragment
            else -> null
        }
        
        view?.let { v ->
            val animatorSet = AnimatorSet()
            
            if (isSelected) {
                // Modern selected animation with emoji bounce effect
                val scaleX = ObjectAnimator.ofFloat(v, "scaleX", 1.0f, 1.3f, 1.0f)
                val scaleY = ObjectAnimator.ofFloat(v, "scaleY", 1.0f, 1.3f, 1.0f)
                val alpha = ObjectAnimator.ofFloat(v, "alpha", 0.6f, 1.0f)
                val rotation = ObjectAnimator.ofFloat(v, "rotation", 0f, 5f, -5f, 0f)
                
                scaleX.duration = 400
                scaleY.duration = 400
                alpha.duration = 300
                rotation.duration = 500
                
                animatorSet.playTogether(scaleX, scaleY, alpha, rotation)
                animatorSet.interpolator = OvershootInterpolator(2.0f)
                
                // Add glow effect
                addGlowEffect(v)
            } else {
                // Modern press animation with ripple effect
                val scaleX = ObjectAnimator.ofFloat(v, "scaleX", 1.0f, 0.9f, 1.0f)
                val scaleY = ObjectAnimator.ofFloat(v, "scaleY", 1.0f, 0.9f, 1.0f)
                val alpha = ObjectAnimator.ofFloat(v, "alpha", 1.0f, 0.8f, 1.0f)
                
                scaleX.duration = 200
                scaleY.duration = 200
                alpha.duration = 150
                
                animatorSet.playTogether(scaleX, scaleY, alpha)
                animatorSet.interpolator = OvershootInterpolator(1.5f)
            }
            
            animatorSet.start()
        }
    }
    
    private fun addGlowEffect(view: View) {
        // Add subtle glow effect to selected item
        val glowAnimation = ObjectAnimator.ofFloat(view, "elevation", 0f, 8f, 0f)
        glowAnimation.duration = 600
        glowAnimation.interpolator = OvershootInterpolator(1.0f)
        glowAnimation.start()
    }
    
    private fun addContinuousAnimation() {
        // Add subtle pulsing animation to back button when visible
        val pulseAnimation = android.view.animation.AnimationUtils.loadAnimation(this, R.anim.back_button_press)
        pulseAnimation.duration = 2000
        pulseAnimation.repeatCount = android.view.animation.Animation.INFINITE
        pulseAnimation.repeatMode = android.view.animation.Animation.REVERSE
        
        // Start animation only when button is visible
        binding.backButton.visibility.let { visibility ->
            if (visibility == android.view.View.VISIBLE) {
                binding.backButton.startAnimation(pulseAnimation)
            }
        }
    }
    
    override fun onSupportNavigateUp(): Boolean {
        // Disable default back button behavior
        return false
    }
}
