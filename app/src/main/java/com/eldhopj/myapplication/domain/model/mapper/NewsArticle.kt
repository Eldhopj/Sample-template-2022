package com.eldhopj.myapplication.domain.model.mapper

import androidx.annotation.Keep

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
data class NewsArticle(
    val author: String? = "", // Estadão Conteúdo
    val title: String? = "", // Elon Musk é escolhido como a Pessoa do Ano pela revista 'Time'
    val description: String? = "", // Revista americana elegeu o bilionário pela ambição em salvar o planeta com eletrificação dos veículos e viagens para Marte
    val urlToImage: String? = "", // https://p2.trrsf.com/image/fget/cf/1200/628/middle/s1.trrsf.com/atm/3/core/_img/terra-logo-white-bg-v3.jpg
    val publishedAt: String? = "", // 2021-12-14T14:03:50Z
    val content: String? = "" // O bilionário Elon Musk, fundador da montadora de veículos elétricos Tesla, foi escolhido como a Pessoa do Ano em tradicional ranking da revista Time, revelado nesta segunda-feira, 13.De acordo com … [+2519 chars]
)
