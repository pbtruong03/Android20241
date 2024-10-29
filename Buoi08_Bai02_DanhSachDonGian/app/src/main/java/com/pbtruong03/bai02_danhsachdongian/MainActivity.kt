package com.pbtruong03.bai02_danhsachdongian

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import androidx.activity.ComponentActivity

class MainActivity : ComponentActivity() {

    private lateinit var editTextNumber: EditText
    private lateinit var radioGroup: RadioGroup
    private lateinit var buttonShow: Button
    private lateinit var listView: ListView
    private lateinit var textViewError: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editTextNumber = findViewById(R.id.editTextNumber)
        radioGroup = findViewById(R.id.radioGroup)
        buttonShow = findViewById(R.id.buttonShow)
        listView = findViewById(R.id.listView)
        textViewError = findViewById(R.id.textViewError)

        buttonShow.setOnClickListener {
            showNumbers()
        }
    }

    private fun showNumbers() {
        val inputText = editTextNumber.text.toString()
        val number = inputText.toIntOrNull()

        if (number == null || number < 0) {
            textViewError.text = "Please enter a valid positive integer"
            textViewError.visibility = View.VISIBLE
            listView.adapter = null
            return
        } else {
            textViewError.visibility = View.GONE
        }

        val numbersList = when (radioGroup.checkedRadioButtonId) {
            R.id.radioEven -> (0..number).filter { it % 2 == 0 }
            R.id.radioOdd -> (1..number).filter { it % 2 != 0 }
            R.id.radioSquare -> (0..number).filter { Math.sqrt(it.toDouble()).toInt().let { sqrt -> sqrt * sqrt == it } }
            else -> listOf()
        }

        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, numbersList)
        listView.adapter = adapter
    }
}
