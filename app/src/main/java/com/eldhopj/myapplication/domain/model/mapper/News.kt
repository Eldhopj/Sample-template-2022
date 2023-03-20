package com.eldhopj.myapplication.domain.model.mapper

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

/**
 * News
 *
 * @property totalResults
 * @property articles
 * @constructor Create empty News
 */
@Keep
data class News(
    val totalResults: String? = "0", // 14437
    val articles: List<NewsArticle> = emptyList()
)
