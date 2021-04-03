package com.task.movieapp.di.module

import com.task.movieapp.domain.repository.Repository
import com.task.movieapp.domain.usecase.movies.MovieUseCase
import com.task.movieapp.domain.usecase.movies.MovieUseCaseImp
import dagger.Module
import dagger.Provides

@Module
class UsecaseModule {

    @Provides
    fun provideMovieUseCase(repository: Repository): MovieUseCase {
        return MovieUseCaseImp(repository = repository)
    }

}