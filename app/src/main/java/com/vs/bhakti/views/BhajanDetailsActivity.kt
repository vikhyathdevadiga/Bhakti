package com.vs.bhakti.views

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.vs.bhakti.R

// BhajanDetailsActivity.kt
class BhajanDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bhajan_details)

        val titleTextView: TextView = findViewById(R.id.bhajanDetailsTitleTextView)
        val lyricsTextView: TextView = findViewById(R.id.bhajanDetailsLyricsTextView)

        val title = intent.getStringExtra("title")
        val lyrics = intent.getStringExtra("lyrics")

        titleTextView.text = title
        lyricsTextView.text = lyrics?.replace("\\n", "\n")
    }
}
