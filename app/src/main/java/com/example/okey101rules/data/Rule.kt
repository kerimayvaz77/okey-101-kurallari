package com.example.okey101rules.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "rules")
data class Rule(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val category: String,
    @ColumnInfo(name = "sub_category") val subCategory: String,
    val title: String,
    val description: String,
    val keywords: String
)
