package com.br.magazinehero.data.repository

import com.br.magazinehero.data.model.Comic
import com.br.magazinehero.data.model.ComicsResponse

interface ComicRepository {

    suspend fun getAllComics(): ComicsResponse?

    suspend fun getComicDetails(id : Int): ComicsResponse?
}