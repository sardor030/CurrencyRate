package com.example.currencyrate.ui.main

import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import com.airbnb.lottie.LottieAnimationView
import com.example.currencyrate.R
import com.example.currencyrate.ui.main.lists.CurrencyAdapter
import com.example.currencyrate.ui.main.vm.MainViewModel
import com.example.currencyrate.utils.Resource
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val mainViewModel: MainViewModel by viewModels()

    private var rvCbu: RecyclerView? = null
    private var adapter: CurrencyAdapter? = null
    private var loading: LottieAnimationView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initViews()

        initObserve()
    }

    private fun initObserve() {
        lifecycleScope.launch {
            mainViewModel.flow
                .collect { it ->
                    when (it) {
                        is Resource.Failure -> {
                            Toast.makeText(this@MainActivity, "Network error!", Toast.LENGTH_SHORT)
                                .show()
                        }

                        is Resource.Loading -> {
                            loading?.visibility = View.VISIBLE
                        }

                        is Resource.Success -> {
                            loading?.visibility = View.GONE
                            adapter = CurrencyAdapter(it.data) { currency ->
                                openCalculatorDialog(currency.Rate)
                            }
                            rvCbu?.adapter = adapter
                        }
                    }
                }
        }
    }

    @SuppressLint("SetTextI18n")
    private fun openCalculatorDialog(rate: String) {
        val dialog = AlertDialog.Builder(this@MainActivity).create()
        val view = layoutInflater.inflate(R.layout.dialog_calculator, null)

        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        val btnCalculate = view.findViewById<Button>(R.id.btn_calculation)
        val btnClose = view.findViewById<Button>(R.id.btn_close)
        val etAmount = view.findViewById<EditText>(R.id.et_amount)
        val tvResult = view.findViewById<TextView>(R.id.tv_result)

        btnCalculate.setOnClickListener {

            if (etAmount.text.isNotEmpty()) {
                val amount = rate.toDouble() * etAmount.text.toString().toDouble()
                tvResult.text = "$amount so`m"
            } else {
                Toast.makeText(this, "fill the input!", Toast.LENGTH_SHORT).show()
                tvResult.text = "0.0"
            }
        }
        dialog.setView(view)
        dialog.show()

        btnClose.setOnClickListener {
            dialog.dismiss()
        }
    }

    private fun initViews() {
        rvCbu = findViewById(R.id.rv_main)
        loading = findViewById(R.id.loading)
    }
}