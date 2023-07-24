package com.example.currencyrate.ui.main.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.currencyrate.api.repository.CurrencyRepository
import com.example.currencyrate.api.response.CurrencyResponse
import com.example.currencyrate.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor(
    private val currencyRepository: CurrencyRepository
) : ViewModel() {

    private val _flow = MutableStateFlow<Resource<List<CurrencyResponse>>>(Resource.Loading())
    val flow get() = _flow

    init {
        getCurrency()
    }

    private fun getCurrency() {
        viewModelScope.launch {
            currencyRepository.getCurrency()
                .catch {
                    _flow.emit(Resource.Failure(it))
                }.collect {
                    _flow.emit(Resource.Success(it))
                }
        }
    }

}