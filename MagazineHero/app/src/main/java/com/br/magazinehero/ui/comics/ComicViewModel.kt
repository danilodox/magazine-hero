package com.br.magazinehero.ui.comics

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.br.magazinehero.data.model.ComicsResponse
import com.br.magazinehero.data.repository.ComicRepository
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class ComicViewModel(private val repository: ComicRepository) : ViewModel() {
    val mComicsLiveData : MutableLiveData<ComicsResponse> = MutableLiveData()
    val loadingLiveData: MutableLiveData<Boolean> = MutableLiveData()
    val errorComicsLiveData: MutableLiveData<Boolean> = MutableLiveData()


    fun getComics(){
        loadingLiveData.value = true
        errorComicsLiveData.value = false

        viewModelScope.launch {

            try {

                mComicsLiveData.value = repository.getAllComics()

            } catch (e : Exception){
                errorComicsLiveData.value = true
            }
            loadingLiveData.value = false

        }
    }









}