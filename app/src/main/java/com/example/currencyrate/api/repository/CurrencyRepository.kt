package com.example.currencyrate.api.repository

import com.example.currencyrate.api.network.helper.ApiHelper
import com.example.currencyrate.api.network.helper.ApiHelperImp
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CurrencyRepository @Inject constructor(
    private val apiHelper: ApiHelper
){
    suspend fun getCurrency() = flow {emit(apiHelper.getCurrency())}
}