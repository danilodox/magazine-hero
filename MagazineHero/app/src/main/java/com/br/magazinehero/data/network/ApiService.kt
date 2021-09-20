package com.br.magazinehero.data.network

import com.br.magazinehero.data.model.Comic
import com.br.magazinehero.data.model.ComicsResponse
import com.br.magazinehero.data.model.Data
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {


    @GET("comics")
    suspend fun getAllComics(@Query("offset") offset: Int = 0): ComicsResponse


    @GET("comics/{id}")
    suspend fun getComicDetails(@Path("id") id: Int): ComicsResponse

}