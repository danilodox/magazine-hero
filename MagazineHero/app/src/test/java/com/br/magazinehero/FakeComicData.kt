package com.br.magazinehero

import com.br.magazinehero.data.model.*


object FakeComicData {

    const val IS_LOADING = true
    const val NOT_LOADING = false


    val PRICE = Price(
        "isPrice",
        2.2f
    )
    val THUMBNAIL = Thumbnail(
        "path",
    "string"
    )

    val RESULTS = Comic(
        82967,
        "Marvel Previews (2017)",
        "ASDADADSA",
        PRICE,
        THUMBNAIL,
        ""


    )


    val DATA = Data(
        0,
        20,
        49775,
        20,
        listOf<RESULTS>

    )

    val COMIC_RESPONSE = ComicsResponse(
        200,
        "84ea36e3b72a2724931e692d5e743185566499ca",
        DATA

        )







}