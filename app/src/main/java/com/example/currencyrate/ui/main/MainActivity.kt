package com.example.currencyrate.ui.main

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.currencyrate.R
import com.example.currencyrate.ui.main.vm.MainViewModel
import com.example.currencyrate.utils.Resource
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        lifecycleScope.launch {
            mainViewModel.flow
                .collect {
                    when (it) {
                        is Resource.Failure -> {}
                        is Resource.Loading -> {}
                        is Resource.Success -> {
                            it.data.forEach { currency ->
                                Log.e("RRR", "${currency.CcyNm_UZ}")
                            }
                        }
                    }
                }
        }

    }
}