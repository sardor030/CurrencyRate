package com.example.currencyrate.ui.main.lists

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.currencyrate.R
import com.example.currencyrate.api.response.CurrencyResponse

class CurrencyAdapter(
    private var list: List<CurrencyResponse>,
    val OnClick: (currency: CurrencyResponse) -> Unit
) :
    RecyclerView.Adapter<CurrencyAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        @SuppressLint("SetTextI18n")
        fun onBind(currency: CurrencyResponse) {
            val tvName: TextView = itemView.findViewById(R.id.tv_name)
            val tvRate: TextView = itemView.findViewById(R.id.tv_rate)


            itemView.apply {
                tvName.text = currency.Nominal + currency.CcyNm_UZ
                tvRate.text = "${currency.Rate} so`m"
            }
            itemView.setOnClickListener {
                OnClick(currency)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_currency, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(list[position])
    }

    override fun getItemCount(): Int = list.size


}