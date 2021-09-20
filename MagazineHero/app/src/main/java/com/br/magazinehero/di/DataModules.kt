package com.br.magazinehero.di

import com.br.magazinehero.data.network.RetrofitService
import com.br.magazinehero.data.network.interceptor.AuthInterceptor
import com.br.magazinehero.data.repository.ComicRepository
import com.br.magazinehero.data.repository.ComicRepositoryImpl
import org.koin.dsl.module

val dataModule = module {

    single { AuthInterceptor() }

    factory { RetrofitService() }


    factory <ComicRepository> { ComicRepositoryImpl (get()) }




}