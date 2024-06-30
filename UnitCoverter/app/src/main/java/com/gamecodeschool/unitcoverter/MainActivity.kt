package com.gamecodeschool.unitcoverter

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.widget.doOnTextChanged

class MainActivity : AppCompatActivity() {
    private lateinit var valueTextView: TextView
    private lateinit var fromUnitView: Spinner
    private lateinit var toUnitView: Spinner
    private lateinit var resultView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        valueTextView = findViewById(R.id.valueTextView)
        fromUnitView = findViewById(R.id.fromUnitSpinner)
        toUnitView = findViewById(R.id.toUnitView)
        resultView = findViewById(R.id.resultView)
        setUpFromUnitOptions()

        fromUnitView.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long,
                ) {
                    val fromUnit = ConversionUnit.from(parent?.selectedItem.toString())
                    setUpToUnitOptions(fromUnit)
                    calculate()
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                }
            }
        toUnitView.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long,
                ) {
                    calculate()
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                }
            }

        valueTextView.doOnTextChanged { _, _, _, _ -> calculate() }
    }

    private fun setUpFromUnitOptions() {
        val list = ArrayList<String>()
        ConversionUnit.entries.forEach {
            val label = ConversionUnit.getLabelForUnit(it)
            list.add(label)
        }
        val adapter = ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, list)
        fromUnitView.adapter = adapter
    }

    fun setUpToUnitOptions(unit: ConversionUnit) {
        val list = ArrayList<String>()
        ConversionUnit.getConversionUnitsFor(unit).forEach {
            val label = ConversionUnit.getLabelForUnit(it)
            list.add(label)
        }
        val adapter = ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, list)
        toUnitView.adapter = adapter
    }

    fun calculate() {
        val fromUnit = ConversionUnit.from(fromUnitView.selectedItem.toString())
        val toUnit = ConversionUnit.from(toUnitView.selectedItem.toString())
        val value = valueTextView.text.toString().toDouble()
        resultView.text = Converter.convert(value, fromUnit, toUnit).toString() + toUnit.label
    }
}
