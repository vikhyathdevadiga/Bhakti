package com.vs.bhakti.model

import androidx.room.Entity
import androidx.room.PrimaryKey

// BhajanEntity.kt
@Entity(tableName = "bhajans")
data class BhajanEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val categoryId: String,
    val title: String,
    val lyrics: String
)
