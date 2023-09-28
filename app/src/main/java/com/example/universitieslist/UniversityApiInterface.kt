package com.example.universitieslist

import retrofit2.Call
import retrofit2.http.GET

interface UniversityApiInterface {

    @GET("search")
    fun getUniversities():Call<List<University>>
}