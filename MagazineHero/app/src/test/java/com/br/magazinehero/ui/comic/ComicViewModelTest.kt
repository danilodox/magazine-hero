package com.br.magazinehero.ui.comic

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.br.magazinehero.FakeComicData
import com.br.magazinehero.data.model.ComicsResponse
import com.br.magazinehero.data.repository.ComicRepository
import com.br.magazinehero.ui.comics.ComicViewModel
import io.mockk.coEvery
import io.mockk.coVerifyOrder
import io.mockk.confirmVerified
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.lang.Exception

class ComicViewModelTest {
    @get:Rule
    val rule = InstantTaskExecutorRule()

    private val testDispatcher = TestCoroutineDispatcher()
    private val repository = mockk<ComicRepository>()
    private val mComicsLiveDataObserver = mockk<Observer<ComicsResponse>>(relaxed = true)
    private val loadingLiveDataObserver = mockk<Observer<Boolean>>(relaxed = true)

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun cleanUp() {
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }

    @Test
    fun `call comics with success then set comicsLiveData and show loading`() {

        val viewModel = instantiateViewModel()
        coEvery { repository.getAllComics() } throws Exception()

        viewModel.getComics()

        coVerifyOrder {
            loadingLiveDataObserver.onChanged(FakeComicData.IS_LOADING)
            repository.getAllComics()
            loadingLiveDataObserver.onChanged(FakeComicData.NOT_LOADING)
        }
        confirmVerified(loadingLiveDataObserver)
        confirmVerified(repository)
        confirmVerified(loadingLiveDataObserver)


    }

    private fun instantiateViewModel(): ComicViewModel {
        val viewModel =
            ComicViewModel(repository)
        viewModel.mComicsLiveData.observeForever(mComicsLiveDataObserver)
        viewModel.loadingLiveData.observeForever(loadingLiveDataObserver)
        return viewModel
    }
}