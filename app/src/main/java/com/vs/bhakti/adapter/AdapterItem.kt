package com.vs.bhakti.adapter

import com.vs.bhakti.model.Bhajan
import com.vs.bhakti.model.Category

sealed class AdapterItem

data class CategoryItem(val category: Category) : AdapterItem()

data class BhajanItem(val bhajan: Bhajan) : AdapterItem()
