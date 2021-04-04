package com.task.movieapp

import com.google.gson.annotations.SerializedName
import com.nhaarman.mockitokotlin2.any
import com.task.movieapp.data.model.MoviesItem
import com.task.movieapp.data.model.MoviesList
import com.task.movieapp.data.model.flicker.FlickerMoviesResponse
import com.task.movieapp.data.model.flicker.PhotoItem
import com.task.movieapp.data.model.flicker.Photos
import com.task.movieapp.domain.repository.Repository
import com.task.movieapp.domain.usecase.movies.MovieUseCase
import com.task.movieapp.domain.usecase.movies.MovieUseCaseImp
import io.reactivex.Flowable.just
import io.reactivex.Observable
import io.reactivex.observers.TestObserver
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

/**
 * Created by Abdelrahman Sherif on 4/4/2021.
 */

class MoviesUseCaseTest {

    @Mock
    lateinit var repository: Repository


    private lateinit var movieUseCase: MovieUseCase

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        movieUseCase = MovieUseCaseImp(repository)
    }

    @Test
    fun `getMoviesPhotosList when success from flicker then return list`() {
        //arrange
        val responseBody =
            FlickerMoviesResponse(photos = Photos())
        val responseObservable = Observable.just(responseBody)

        Mockito.`when`(repository.getMoviePhotos(any())).thenReturn(responseObservable)

        //act
        val testObserverResponse = TestObserver<FlickerMoviesResponse>()
        repository.getMoviePhotos( "Inception")
            .subscribeWith(testObserverResponse)
        testObserverResponse.assertValue(responseBody)
        println(responseBody.toString())

        val testObserverUsecase = TestObserver<FlickerMoviesResponse>()
        movieUseCase.getMoviePhotos("Inception").subscribeWith(testObserverUsecase)


        testObserverUsecase.assertNoErrors()
        testObserverUsecase.assertValue(responseBody)
    }

}