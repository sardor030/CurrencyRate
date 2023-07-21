package com.example.currencyrate.api.network.helper

import com.example.currencyrate.api.network.ApiService
import com.example.currencyrate.api.response.CurrencyResponse
import retrofit2.Response
import javax.inject.Inject

class ApiHelperImp @Inject constructor(
    private val apiService: ApiService
): ApiHelper {
    override suspend fun getCurrency(): Response<CurrencyResponse> {
        return apiService.getAllCurrency()
    }

}