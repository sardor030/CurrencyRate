package com.example.currencyrate.api.network

import com.example.currencyrate.api.response.CurrencyResponse
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("arkhiv-kursov-valyut/json/")
    suspend fun getAllCurrency(): List<CurrencyResponse>
}