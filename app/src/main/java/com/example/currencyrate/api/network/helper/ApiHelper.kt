package com.example.currencyrate.api.network.helper

import com.example.currencyrate.api.response.CurrencyResponse
import retrofit2.Response

interface ApiHelper {
    suspend fun getCurrency(): Response<CurrencyResponse>
}