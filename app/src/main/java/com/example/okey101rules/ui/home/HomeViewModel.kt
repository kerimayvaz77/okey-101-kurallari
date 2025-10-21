package com.example.okey101rules.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import com.example.okey101rules.data.Rule
import com.example.okey101rules.data.RuleRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: RuleRepository,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    val categories: LiveData<List<String>> = repository.getAllCategories()
    
    // Make searchQuery observable from Fragment
    val currentSearchQuery: LiveData<String> = savedStateHandle.getLiveData("search_query", "")
    
    val searchResults: LiveData<List<Rule>> = currentSearchQuery.switchMap { query ->
        if (query.isBlank()) {
            MutableLiveData(emptyList())
        } else {
            repository.searchRules(query)
        }
    }
    
    fun searchRules(query: String) {
        savedStateHandle.set("search_query", query)
    }
    
    fun clearSearch() {
        savedStateHandle.set("search_query", "")
    }
    
    fun getCurrentSearchQuery(): String? {
        return currentSearchQuery.value
    }
    
    fun hasActiveSearch(): Boolean {
        return !currentSearchQuery.value.isNullOrBlank()
    }
}
