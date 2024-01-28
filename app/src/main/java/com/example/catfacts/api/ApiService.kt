package com.example.catfacts.api

import com.example.catfacts.models.CatFacts
import retrofit2.http.GET

interface ApiService {
    @GET("/facts")
    fun getAllCatFacts(): retrofit2.Call<ArrayList<CatFacts>>
}