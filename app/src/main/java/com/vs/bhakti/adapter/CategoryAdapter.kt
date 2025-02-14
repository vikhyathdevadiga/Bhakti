package com.vs.bhakti.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.vs.bhakti.R
import com.vs.bhakti.model.Category

class CategoryAdapter(private val categories: List<Category>, private val clickListener: (String) -> Unit) :
    RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val categoryNameTextView: TextView = itemView.findViewById(R.id.titleTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_category, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val category = categories[position]
        holder.categoryNameTextView.text = category.name
        holder.itemView.setOnClickListener {
            clickListener.invoke(category.id)
        }
    }

    override fun getItemCount(): Int = categories.size
}
