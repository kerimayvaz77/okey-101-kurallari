package com.example.okey101rules.ui.burak

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class BurakViewModel : ViewModel() {
    
    private val _wins = MutableLiveData<Int>().apply { value = 0 }
    val wins: LiveData<Int> = _wins
    
    private val _losses = MutableLiveData<Int>().apply { value = 0 }
    val losses: LiveData<Int> = _losses
    
    fun incrementWins() {
        _wins.value = (_wins.value ?: 0) + 1
    }
    
    fun incrementLosses() {
        _losses.value = (_losses.value ?: 0) + 1
    }
    
    fun resetStats() {
        _wins.value = 0
        _losses.value = 0
    }
    
    fun setStats(wins: Int, losses: Int) {
        _wins.value = wins
        _losses.value = losses
    }
}
