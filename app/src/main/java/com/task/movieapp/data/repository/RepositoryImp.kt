package com.task.movieapp.data.repository

import com.task.movieapp.domain.remote.FlickerAPI
import com.task.movieapp.domain.repository.Repository
import javax.inject.Inject

class RepositoryImp @Inject constructor(var flickerAPI: FlickerAPI) : Repository {


}