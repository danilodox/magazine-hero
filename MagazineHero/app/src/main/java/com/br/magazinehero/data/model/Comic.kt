package com.br.magazinehero.data.model

data class Comic (
    val id: Int,
    val title: String,
    val description: String,
    val prices: List<Price>,
    val thumbnail: Thumbnail,
    var isRare: String = ""

    )