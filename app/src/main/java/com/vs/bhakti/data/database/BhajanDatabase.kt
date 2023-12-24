package com.vs.bhakti.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.vs.bhakti.data.dao.BhajanDao
import com.vs.bhakti.model.BhajanEntity
import com.vs.bhakti.model.CategoryEntity

// BhajanDatabase.kt
@Database(entities = [CategoryEntity::class, BhajanEntity::class], version = 1, exportSchema = false)
abstract class BhajanDatabase : RoomDatabase() {

    abstract fun bhajanDao(): BhajanDao

    companion object {
        @Volatile
        private var INSTANCE: BhajanDatabase? = null

        fun getDatabase(context: Context): BhajanDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    BhajanDatabase::class.java,
                    "bhajan_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
