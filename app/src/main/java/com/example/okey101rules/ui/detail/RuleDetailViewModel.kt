package com.example.okey101rules.ui.detail

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
class RuleDetailViewModel @Inject constructor(
    private val repository: RuleRepository
) : ViewModel() {
    
    private val _rule = MutableLiveData<Rule?>()
    val rule: LiveData<Rule?> = _rule
    
    fun loadRule(ruleId: Int) {
        viewModelScope.launch {
            val rule = repository.getRuleById(ruleId)
            _rule.value = rule
        }
    }
}
