package com.example.currencyrate.api.repository

import com.example.currencyrate.api.network.helper.ApiHelperImp
import javax.inject.Inject

class CurrencyRepository @Inject constructor(
    private val apiHelperImp: ApiHelperImp
){
    suspend fun getCurrency() = apiHelperImp.getCurrency()
}