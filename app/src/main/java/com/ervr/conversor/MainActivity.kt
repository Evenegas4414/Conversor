package com.ervr.conversor

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private lateinit var spinnerCurrencyIn: Spinner
    private lateinit var spinnerCurrencyOut: Spinner
    private lateinit var etAmount: EditText
    private lateinit var btnConvert: Button
    private lateinit var btnReset: Button
    private lateinit var tvResult: TextView

    private val currencies = arrayOf("USD", "EUR", "GBP")
    private val conversionRates = mapOf(
        "USD" to 1.0, "EUR" to 0.85, "GBP" to 0.72
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        spinnerCurrencyIn = findViewById(R.id.spinnerCurrencyIn)
        spinnerCurrencyOut = findViewById(R.id.spinnerCurrencyOut)
        etAmount = findViewById(R.id.etAmount)
        btnConvert = findViewById(R.id.btnConvert)
        btnReset = findViewById(R.id.btnReset)
        tvResult = findViewById(R.id.tvResult)

        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, currencies)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerCurrencyIn.adapter = adapter
        spinnerCurrencyOut.adapter = adapter

        btnConvert.setOnClickListener {
            convertCurrency()
        }

        btnReset.setOnClickListener {
            resetFields()
        }
    }

    private fun convertCurrency() {
        val currencyIn = spinnerCurrencyIn.selectedItem.toString()
        val currencyOut = spinnerCurrencyOut.selectedItem.toString()
        val amount = etAmount.text.toString().toDoubleOrNull()

        if (amount != null) {
            val rateIn = conversionRates[currencyIn]
            val rateOut = conversionRates[currencyOut]

            if (rateIn != null && rateOut != null) {
                val convertedAmount = amount * (rateOut / rateIn)
                tvResult.text = "Valor convertido: $convertedAmount $currencyOut"
            } else {
                tvResult.text = "No se encontró la tasa de conversión"
            }
        } else {
            tvResult.text = "Ingrese un monto válido"
        }
    }

    private fun resetFields() {
        spinnerCurrencyIn.setSelection(0)
        spinnerCurrencyOut.setSelection(0)
        etAmount.text.clear()
        tvResult.text = ""
    }
}
