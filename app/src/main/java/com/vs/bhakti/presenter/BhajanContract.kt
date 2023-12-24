package com.vs.bhakti.presenter

import com.vs.bhakti.model.Bhajan
import com.vs.bhakti.model.Category

// BhajanContract.kt
interface BhajanContract {
    interface View {
        fun showCategories(categories: List<Category>)
        fun showBhajans(bhajans: List<Bhajan>)
        fun showBhajanDetails(title: String, lyrics: String)
    }

    interface Presenter {
        suspend fun start()
        suspend fun categorySelected(categoryId: String)
        fun bhajanSelected(bhajan: Bhajan)
        suspend fun refreshData()
    }
}
