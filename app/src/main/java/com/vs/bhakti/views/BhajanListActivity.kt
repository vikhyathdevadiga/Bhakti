package com.vs.bhakti.views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.vs.bhakti.R
import com.vs.bhakti.adapter.BhajanListAdapter
import com.vs.bhakti.adapter.CategoryAdapter
import com.vs.bhakti.model.Bhajan
import com.vs.bhakti.model.Category
import com.vs.bhakti.presenter.BhajanContract
import com.vs.bhakti.presenter.BhajanPresenter
import com.vs.bhakti.repository.BhajanRepository
import kotlinx.coroutines.launch

class BhajanListActivity : AppCompatActivity(), BhajanContract.View {

    private lateinit var presenter: BhajanContract.Presenter
    private lateinit var categoryRecyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bhajan_list)

        presenter = BhajanPresenter(this, BhajanRepository(this))
        categoryRecyclerView = findViewById(R.id.categoryRecyclerView)

        lifecycleScope.launch {
            intent.getStringExtra("categoryId")?.let { presenter.categorySelected(it) }
        }
    }

    override fun showCategories(categories: List<Category>) {
    }

    override fun showBhajans(bhajans: List<Bhajan>) {
        val adapter = BhajanListAdapter(bhajans) { title, lyrics ->
            showBhajanDetails(title, lyrics)
        }
        categoryRecyclerView.adapter = adapter
        categoryRecyclerView.layoutManager = LinearLayoutManager(this)
    }

    override fun showBhajanDetails(title: String, lyrics: String) {
        val intent = Intent(this, BhajanDetailsActivity::class.java)
        intent.putExtra("title", title)
        intent.putExtra("lyrics", lyrics)
        startActivity(intent)
    }
}