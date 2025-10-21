package com.example.okey101rules.di

import android.content.Context
import androidx.room.Room
import com.example.okey101rules.database.AppDatabase
import com.example.okey101rules.database.RuleDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    
    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            AppDatabase::class.java,
            "okey101_rules_db"
        )
        .fallbackToDestructiveMigration()
        .build()
    }
    
    @Provides
    fun provideRuleDao(database: AppDatabase): RuleDao {
        return database.ruleDao()
    }
}
