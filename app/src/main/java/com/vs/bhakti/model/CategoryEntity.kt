package com.vs.bhakti.model

import androidx.room.Entity
import androidx.room.PrimaryKey

// CategoryEntity.kt
@Entity(tableName = "categories")
data class CategoryEntity(
    @PrimaryKey val id: String,
    val name: String
)

