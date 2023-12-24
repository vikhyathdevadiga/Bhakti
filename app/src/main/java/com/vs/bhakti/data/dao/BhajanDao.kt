package com.vs.bhakti.data.dao
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.vs.bhakti.model.BhajanEntity
import com.vs.bhakti.model.CategoryEntity

@Dao
interface BhajanDao {

    @Query("SELECT * FROM categories")
    fun getAllCategories(): List<CategoryEntity>

    @Query("SELECT * FROM bhajans WHERE categoryId = :categoryId")
    fun getBhajansByCategory(categoryId: String): List<BhajanEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCategories(categories: List<CategoryEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertBhajans(bhajans: List<BhajanEntity>)
}