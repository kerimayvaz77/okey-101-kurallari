package com.example.okey101rules.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.okey101rules.data.Rule

@Dao
interface RuleDao {
    
    @Query("SELECT DISTINCT category FROM rules ORDER BY category ASC")
    fun getAllCategories(): LiveData<List<String>>
    
    @Query("SELECT * FROM rules WHERE category = :category ORDER BY title ASC")
    fun getRulesByCategory(category: String): LiveData<List<Rule>>
    
    @Query("SELECT * FROM rules WHERE title LIKE '%' || :query || '%' OR description LIKE '%' || :query || '%' OR keywords LIKE '%' || :query || '%' ORDER BY title ASC")
    fun searchRules(query: String): LiveData<List<Rule>>
    
    @Query("SELECT * FROM rules WHERE id = :ruleId")
    suspend fun getRuleById(ruleId: Int): Rule?
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(rules: List<Rule>)
    
    @Query("SELECT COUNT(*) FROM rules")
    suspend fun getRuleCount(): Int
}
