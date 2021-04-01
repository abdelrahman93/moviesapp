package com.task.movieapp.domain.usecase.movies

import com.task.movieapp.domain.repository.Repository
import io.reactivex.Observable
import javax.inject.Inject

/**
 * Created by Abdelrahman Sherif on 3/30/2021.
 */
class MovieUseCaseImp @Inject

constructor(val repository: Repository) : MovieUseCase {

}