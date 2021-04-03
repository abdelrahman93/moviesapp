package com.task.movieapp.domain.remote

import com.task.movieapp.data.model.ApiUrl
import com.task.movieapp.data.model.flicker.FlickerMoviesResponse
import io.reactivex.Observable
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Query


interface FlickerAPI {

    @POST(ApiUrl.FLICKER_URL)
    fun getMoviePhotos(
        @Query("method") method:String,
        @Query("api_key") api_key:String,
        @Query("format") format:String,
        @Query("nojsoncallback") nojsoncallback:Int,
        @Query("page") page:Int,
        @Query("per_page") perPage:Int,
        @Query("text") text:String
    ): Observable<FlickerMoviesResponse>

}

