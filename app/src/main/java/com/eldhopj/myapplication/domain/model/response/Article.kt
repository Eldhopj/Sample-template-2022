package com.eldhopj.myapplication.domain.model.response

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

/**
 * Article
 *
 * @property source
 * @property author
 * @property title
 * @property description
 * @property url
 * @property urlToImage
 * @property publishedAt
 * @property content
 * @constructor Create empty Article
 */
@Keep
data class Article(
    @SerializedName("source")
    val source: Source? = null,
    @SerializedName("author")
    val author: String? = null, // Estadão Conteúdo
    @SerializedName("title")
    val title: String? = null, // Elon Musk é escolhido como a Pessoa do Ano pela revista 'Time'
    @SerializedName("description")
    val description: String? = null, // Revista americana elegeu o bilionário pela ambição em salvar o planeta com eletrificação dos veículos e viagens para Marte
    @SerializedName("url")
    val url: String? = null, // https://www.terra.com.br/noticias/tecnologia/elon-musk-e-escolhido-como-a-pessoa-do-ano-pela-revista-time,efd3369e0117077744ca4710ca75c7f5iqdk0md5.html
    @SerializedName("urlToImage")
    val urlToImage: String? = null, // https://p2.trrsf.com/image/fget/cf/1200/628/middle/s1.trrsf.com/atm/3/core/_img/terra-logo-white-bg-v3.jpg
    @SerializedName("publishedAt")
    val publishedAt: String? = null, // 2021-12-14T14:03:50Z
    @SerializedName("content")
    val content: String? = null // O bilionário Elon Musk, fundador da montadora de veículos elétricos Tesla, foi escolhido como a Pessoa do Ano em tradicional ranking da revista Time, revelado nesta segunda-feira, 13.De acordo com … [+2519 chars]
)
