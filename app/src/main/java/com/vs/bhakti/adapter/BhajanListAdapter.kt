package com.vs.bhakti.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.vs.bhakti.R
import com.vs.bhakti.model.Bhajan


class BhajanListAdapter(private val bhajanList: List<Bhajan>, private val clickListener: (String, String) -> Unit) :

    RecyclerView.Adapter<BhajanListAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val categoryNameTextView: TextView = itemView.findViewById(R.id.titleTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_category, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val bhajan = bhajanList[position]
        holder.categoryNameTextView.text = bhajan.title
        holder.itemView.setOnClickListener {
            clickListener.invoke(bhajan.title, bhajan.lyrics)
        }
    }

    override fun getItemCount(): Int = bhajanList.size
}
