package com.example.okey101rules.data

import androidx.lifecycle.LiveData
import com.example.okey101rules.database.RuleDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RuleRepository @Inject constructor(
    private val ruleDao: RuleDao
) {
    
    fun getAllCategories(): LiveData<List<String>> = ruleDao.getAllCategories()
    
    fun getRulesByCategory(category: String): LiveData<List<Rule>> = 
        ruleDao.getRulesByCategory(category)
    
    fun searchRules(query: String): LiveData<List<Rule>> = ruleDao.searchRules(query)
    
    suspend fun getRuleById(ruleId: Int): Rule? = withContext(Dispatchers.IO) {
        ruleDao.getRuleById(ruleId)
    }
    
    suspend fun insertAllRules(rules: List<Rule>) = withContext(Dispatchers.IO) {
        ruleDao.insertAll(rules)
    }
    
    suspend fun getRuleCount(): Int = withContext(Dispatchers.IO) {
        ruleDao.getRuleCount()
    }
}
