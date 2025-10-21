package com.example.okey101rules.ui.category

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.okey101rules.data.Rule
import com.example.okey101rules.data.RuleRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoryViewModel @Inject constructor(
    private val repository: RuleRepository
) : ViewModel() {
    
    private val _rules = MutableLiveData<List<Rule>>()
    val rules: LiveData<List<Rule>> = _rules
    
    fun loadRulesForCategory(category: String) {
        viewModelScope.launch {
            repository.getRulesByCategory(category).observeForever { rulesList ->
                _rules.value = rulesList
            }
        }
    }
}
