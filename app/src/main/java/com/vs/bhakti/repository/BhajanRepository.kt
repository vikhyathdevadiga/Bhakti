package com.vs.bhakti.repository

import android.content.Context
import com.google.firebase.firestore.FirebaseFirestore
import com.vs.bhakti.data.dao.BhajanDao
import com.vs.bhakti.data.database.BhajanDatabase
import com.vs.bhakti.model.Bhajan
import com.vs.bhakti.model.BhajanEntity
import com.vs.bhakti.model.Category
import com.vs.bhakti.model.CategoryEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

//class BhajanRepository {
//
//    private val firestore = FirebaseFirestore.getInstance()
//    suspend fun getCategories(): List<Category> = withContext(Dispatchers.IO) {
//        try {
//            val querySnapshot = firestore.collection("categories").get().await()
//            return@withContext querySnapshot.documents.map {
//                Category(
//                    id = it.id,
//                    name = it.getString("name") ?: ""
//                )
//            }
//        } catch (e: Exception) {
//            return@withContext emptyList()
//        }
//    }
//
//    suspend fun getBhajansByCategory(categoryId: String): List<Bhajan> = withContext(Dispatchers.IO) {
//        try {
//            val querySnapshot = firestore.collection("bhajans")
//                .whereEqualTo("categoryId", categoryId)
//                .get().await()
//
//            return@withContext querySnapshot.documents.map {
//                Bhajan(
//                    categoryId = it.getString("categoryId") ?: "",
//                    title = it.getString("title") ?: "",
//                    lyrics = it.getString("lyrics") ?: ""
//                )
//            }
//        } catch (e: Exception) {
//            // Handle exception (e.g., log or throw a custom exception)
//            return@withContext emptyList()
//        }
//    }
//}

// BhajanRepository.kt
class BhajanRepository(private val context: Context) {

    private val firestore = FirebaseFirestore.getInstance()
    private val bhajanDao: BhajanDao = BhajanDatabase.getDatabase(context).bhajanDao()

    suspend fun getCategories(): List<Category> = withContext(Dispatchers.IO) {
        try {
            val localCategories = bhajanDao.getAllCategories()
            return@withContext if (localCategories.isNotEmpty()) {
                localCategories.map { Category(it.id, it.name) }
            } else {
                val remoteCategories = firestore.collection("categories").get().await()
                val categories = remoteCategories.documents.map {
                    Category(it.id, it.getString("name") ?: "")
                }
                bhajanDao.insertCategories(categories.map { CategoryEntity(it.id, it.name) })
                categories
            }
        } catch (e: Exception) {
            return@withContext emptyList()
        }
    }

    suspend fun getBhajansByCategory(categoryId: String): List<Bhajan> = withContext(Dispatchers.IO) {
        try {
            val localBhajans = bhajanDao.getBhajansByCategory(categoryId)
            return@withContext if (localBhajans.isNotEmpty()) {
                localBhajans.map { Bhajan(it.categoryId, it.title, it.lyrics) }
            } else {
                val remoteBhajans = firestore.collection("bhajans")
                    .whereEqualTo("categoryId", categoryId)
                    .get().await()

                val bhajans = remoteBhajans.documents.map {
                    Bhajan(
                        it.getString("categoryId") ?: "",
                        it.getString("title") ?: "",
                        it.getString("lyrics") ?: ""
                    )
                }

                bhajanDao.insertBhajans(
                    bhajans.map {
                        BhajanEntity(
                            categoryId = it.categoryId,
                            title = it.title,
                            lyrics = it.lyrics
                        )
                    }
                )

                bhajans
            }
        } catch (e: Exception) {
            return@withContext emptyList()
        }
    }
}
