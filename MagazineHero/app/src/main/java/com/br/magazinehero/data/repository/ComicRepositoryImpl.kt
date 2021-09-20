package com.br.magazinehero.data.repository

import com.br.magazinehero.data.model.Comic
import com.br.magazinehero.data.model.ComicsResponse
import com.br.magazinehero.data.network.RetrofitService

class ComicRepositoryImpl(private val apiService: RetrofitService): ComicRepository {


    override suspend fun getAllComics(): ComicsResponse? {
        return try {
            val listOfComics = apiService.service.getAllComics()
            val listOfRares = getRaresComics(listOfComics.data.results.size)
            for(i in 0 until listOfRares.size){
                listOfComics.data.results[listOfRares[i]].isRare = "Rare"
            }
            listOfComics

        } catch (e : Exception) { null }
    }

    override suspend fun getComicDetails(id: Int): ComicsResponse? {
        return try {
            apiService.service.getComicDetails(id)
        } catch (e : Exception) { null }
    }


    fun getRaresComics(n : Int) : ArrayList<Int>{
        var numberOfrares = ((n * 12) / 100)
        var listOfRandom = ArrayList<Int>()
        var stop = false
        while (true){

            var numberRandom = (0..n).random()

            listOfRandom.add(numberRandom)
            if (listOfRandom.size > 1) {
                listOfRandom.remove(0)
            }

            if (listOfRandom.size >= numberOfrares && (listOfRandom[0] != listOfRandom[1])){
                stop = true
            }
            if (stop) break

        }

        return listOfRandom

    }

}