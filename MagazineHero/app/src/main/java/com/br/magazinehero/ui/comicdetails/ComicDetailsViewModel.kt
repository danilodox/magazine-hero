package com.br.moviefy.ui.moviedetails


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.br.magazinehero.data.model.Comic
import com.br.magazinehero.data.model.ComicsResponse
import com.br.magazinehero.data.repository.ComicRepository
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class ComicDetailsViewModel (private val repository: ComicRepository) : ViewModel() {

    val mComicsDetailsLiveData = MutableLiveData<ComicsResponse>()
    val loadingDetailsLiveData = MutableLiveData<Boolean>()
    val errorComicDetailsLiveData = MutableLiveData<Boolean>()


    fun getComic(id : Int){
        loadingDetailsLiveData.value = true
        errorComicDetailsLiveData.value = false


        viewModelScope.launch {
            try {

                this@ComicDetailsViewModel.mComicsDetailsLiveData.value = repository.getComicDetails(id)

            } catch (e : Exception){
                errorComicDetailsLiveData.value = true
            }
            loadingDetailsLiveData.value = false

        }
    }





    fun getShareText(): String {
        var shareText = "${mComicsDetailsLiveData.value?.data!!.results[0].title}\n\n"
        shareText += mComicsDetailsLiveData.value?.data!!.results[0].description
        return shareText
    }


}