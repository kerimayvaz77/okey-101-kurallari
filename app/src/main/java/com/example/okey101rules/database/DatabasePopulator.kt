package com.example.okey101rules.database

import android.content.Context
import android.content.SharedPreferences
import com.example.okey101rules.data.Rule
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DatabasePopulator @Inject constructor(
    @ApplicationContext private val context: Context,
    private val ruleDao: RuleDao
) {
    
    private val prefs: SharedPreferences = context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
    private val gson = Gson()
    
    suspend fun populateIfNeeded() = withContext(Dispatchers.IO) {
        val isPopulated = prefs.getBoolean("database_populated", false)
        
        if (!isPopulated) {
            try {
                val rules = loadRulesFromAssets()
                ruleDao.insertAll(rules)
                prefs.edit().putBoolean("database_populated", true).apply()
            } catch (e: Exception) {
                // Log error or handle gracefully
                e.printStackTrace()
            }
        }
    }
    
    private fun loadRulesFromAssets(): List<Rule> {
        val jsonString = context.assets.open("rules.json").bufferedReader().use { it.readText() }
        val listType = object : TypeToken<List<Rule>>() {}.type
        return gson.fromJson(jsonString, listType)
    }
    
    suspend fun resetDatabase() = withContext(Dispatchers.IO) {
        prefs.edit().putBoolean("database_populated", false).apply()
        populateIfNeeded()
    }
}
