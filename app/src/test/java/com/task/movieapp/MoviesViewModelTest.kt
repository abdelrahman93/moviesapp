package com.task.movieapp

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.task.movieapp.data.Loader
import com.task.movieapp.data.model.MoviesItem
import com.task.movieapp.data.model.MoviesList
import com.task.movieapp.domain.usecase.movies.MovieUseCase
import com.task.movieapp.ui.movies.MoviesViewModel
import com.task.movieapp.ui.movies.MoviesViewState
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.ArgumentCaptor
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

/**
 * Created by Abdelrahman Sherif on 4/4/2021.
 */
class MoviesViewModelTest {

    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()

    @Mock
    lateinit var movieUseCase: MovieUseCase

    @Mock
    lateinit var stateObserver: Observer<BaseViewState>

    private lateinit var viewModel: MoviesViewModel


    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        viewModel = MoviesViewModel(movieUseCase, Schedulers.trampoline(), Schedulers.trampoline())
    }


    @Test
    fun `getMoviesList() when success then return viewState successMoviesList`() {
        //arrange
        val _moviesList = listOf(
            MoviesItem(
                cast = listOf("MO", "Cv"),
                title = "Inception",
                rating = 3,
                genres = listOf("Rom", "SCI")
            )

        )

        val moviesResponse = MoviesList(movies = _moviesList)
        val responseObservable = Observable.just(moviesResponse)

        Mockito.`when`(movieUseCase.getMoviesList(".json")).thenReturn(responseObservable)

        //act
        viewModel.internalState.observeForever(stateObserver)
        viewModel.getMoviesList(".json")

        Mockito.verify(movieUseCase, Mockito.times(1)).getMoviesList(".json")

        val argumentCaptor = ArgumentCaptor.forClass(BaseViewState::class.java)
        val expectedInitLoader = Loader.Progress(true)
        val expectedDefaultState =
            MoviesViewState.successMoviesList(moviesResponse)
        val expectedFinalLoader = Loader.Progress(false)

        argumentCaptor.run {
            Mockito.verify(stateObserver, Mockito.times(3)).onChanged(capture())
            val (initLoader, viewState, finalLoader) = allValues

            //assert
            Assert.assertEquals(expectedInitLoader, initLoader)
            Assert.assertEquals(expectedDefaultState, viewState)
            Assert.assertEquals(expectedFinalLoader, finalLoader)

        }

    }


}