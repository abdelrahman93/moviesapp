package com.task.movieapp.utilities

import android.content.Context
import android.util.Log
import com.task.movieapp.data.model.flicker.PhotoItem
import java.io.IOException


fun PhotoItem.urlFlickerBuilder(): String {
    val url =
        """https://farm${this.farm}.static.flickr.com/${this.server}/${this.id}_${this.secret}.jpg"""
    Log.i("TAG", "urlFlickerBuilder: $url")
    return url
}

fun getJsonDataFromAsset(context: Context): String? {
    val jsonString: String
    try {
        jsonString = context.assets.open("movies.json").bufferedReader().use { it.readText() }
    } catch (ioException: IOException) {
        ioException.printStackTrace()
        return null
    }
    return jsonString
}
