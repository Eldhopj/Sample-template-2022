package com.eldhopj.myapplication.domain.mapper

import com.eldhopj.myapplication.domain.model.mapper.News
import com.eldhopj.myapplication.domain.model.mapper.NewsArticle
import com.eldhopj.myapplication.domain.model.response.NewsResponse

fun NewsResponse.toMapper(): News {
    return News(
        totalResults = totalResults?.toString() ?: "0",
        articles = articles?.map {
            NewsArticle(
                author = it.author,
                title = it.title,
                description = it.description,
                urlToImage = it.urlToImage,
                publishedAt = it.publishedAt,
                content = it.content,
            )
        } ?: emptyList()
    )
}
