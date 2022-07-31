package com.example.freshproducts.data.api

import com.example.freshproducts.domain.model.Fresh
import retrofit2.http.GET

interface ApiService {
    @GET("plants.json")
    suspend fun getFresh(): List<Fresh>





}