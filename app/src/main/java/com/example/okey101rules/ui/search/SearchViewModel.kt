package com.example.okey101rules.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import com.example.okey101rules.data.Rule
import com.example.okey101rules.data.RuleRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val repository: RuleRepository
) : ViewModel() {

    private val queryLiveData = MutableLiveData<String>()

    val searchResults: LiveData<List<Rule>> = queryLiveData.switchMap { q ->
        repository.searchRules(q)
    }

    fun setQuery(query: String) {
        queryLiveData.value = query
    }
}
