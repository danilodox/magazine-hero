package com.br.magazinehero.di

import com.br.magazinehero.ui.comics.ComicViewModel
import com.br.moviefy.ui.moviedetails.ComicDetailsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val uiModule = module {
    viewModel { ComicViewModel( get()) }
    viewModel { ComicDetailsViewModel( get()) }

}