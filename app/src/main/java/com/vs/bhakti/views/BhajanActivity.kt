package com.vs.bhakti.views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.vs.bhakti.R
import com.vs.bhakti.adapter.CategoryAdapter
import com.vs.bhakti.presenter.BhajanContract
import com.vs.bhakti.model.Bhajan
import com.vs.bhakti.model.Category
import com.vs.bhakti.presenter.BhajanPresenter
import com.vs.bhakti.repository.BhajanRepository
import kotlinx.coroutines.launch

class BhajanActivity : AppCompatActivity(), BhajanContract.View {

    private lateinit var presenter: BhajanContract.Presenter
    private lateinit var categoryRecyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bhajan)

        categoryRecyclerView = findViewById(R.id.categoryRecyclerView)

        // Initialize presenter with this view
        presenter = BhajanPresenter(this, BhajanRepository(this))

        // Start presenter to fetch initial data
        lifecycleScope.launch {
            presenter.start()
        }
    }

    override fun showCategories(categories: List<Category>) {
        val adapter = CategoryAdapter(categories) { categoryId ->
            val intent = Intent(this, BhajanListActivity::class.java)
            intent.putExtra("categoryId", categoryId)
            startActivity(intent)
        }
        categoryRecyclerView.adapter = adapter
        categoryRecyclerView.layoutManager = LinearLayoutManager(this)
    }

    override fun showBhajans(bhajans: List<Bhajan>) {
    }

    override fun showBhajanDetails(title: String, lyrics: String) {
    }
}
