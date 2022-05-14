package com.eldhopj.myapplication.domain.model

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

/**
 * Source
 *
 * @property id
 * @property name
 * @constructor Create empty Source
 */
@Keep
data class Source(
    @SerializedName("id")
    val id: Any? = null, // null
    @SerializedName("name")
    val name: String? = null // Terra.com.br
)
