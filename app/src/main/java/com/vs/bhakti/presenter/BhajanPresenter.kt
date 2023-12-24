package com.vs.bhakti.presenter
import com.vs.bhakti.model.Bhajan
import com.vs.bhakti.model.Category
import com.vs.bhakti.repository.BhajanRepository

class BhajanPresenter(
    private val view: BhajanContract.View,
    private val repository: BhajanRepository
) : BhajanContract.Presenter {

    private var categories = emptyList<Category>()

    override suspend fun start() {
        // Launch a coroutine using lifecycleScope
        // Fetch categories on start
        categories = repository.getCategories()
        view.showCategories(categories)
    }

    override suspend fun categorySelected(categoryId: String) {
        // Launch a coroutine using lifecycleScope
        // Fetch and display bhajans based on selected category
        val bhajans = repository.getBhajansByCategory(categoryId)
        view.showBhajans(bhajans)
    }

    override fun bhajanSelected(bhajan: Bhajan) {
        // Display details of the selected bhajan
        view.showBhajanDetails(bhajan.title, bhajan.lyrics)
    }

    override suspend fun refreshData() {
        // Launch a coroutine using lifecycleScope
        // Refresh data
        // repository.refreshData()
        // Update the categories after refreshing
        categories = repository.getCategories()
        view.showCategories(categories)
    }
}
