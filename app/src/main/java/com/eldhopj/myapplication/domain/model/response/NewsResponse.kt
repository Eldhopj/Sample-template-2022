package com.eldhopj.myapplication.domain.model.response

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

/**
 * News
 *
 * @property status
 * @property totalResults
 * @property articles
 * @constructor Create empty News
 */
@Keep
data class NewsResponse(
    @SerializedName("status")
    val status: String? = null, // ok
    @SerializedName("totalResults")
    val totalResults: Int? = null, // 14437
    @SerializedName("articles")
    val articles: List<Article>? = null
)
