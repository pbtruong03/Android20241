package com.pbtruong03.formnhapthongtin

import android.app.DatePickerDialog
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val editTextMSSV = findViewById<EditText>(R.id.editTextMSSV)
        val editTextName = findViewById<EditText>(R.id.editTextName)
        val radioGroupGender = findViewById<RadioGroup>(R.id.radioGroupGender)
        val editTextEmail = findViewById<EditText>(R.id.editTextEmail)
        val editTextPhone = findViewById<EditText>(R.id.editTextPhone)
        val buttonShowDatePicker = findViewById<Button>(R.id.buttonShowDatePicker)
        val textViewSelectedDate = findViewById<TextView>(R.id.textViewSelectedDate)
        val checkBoxSports = findViewById<CheckBox>(R.id.checkBoxSports)
        val checkBoxMovies = findViewById<CheckBox>(R.id.checkBoxMovies)
        val checkBoxMusic = findViewById<CheckBox>(R.id.checkBoxMusic)
        val checkBoxAgree = findViewById<CheckBox>(R.id.checkBoxAgree)
        val buttonSubmit = findViewById<Button>(R.id.buttonSubmit)

        // Date picker
        buttonShowDatePicker.setOnClickListener {
            val datePicker = DatePickerDialog(this, { _, year, month, dayOfMonth ->
                textViewSelectedDate.text = "$dayOfMonth/${month + 1}/$year"
            }, 2000, 0, 1)
            datePicker.show()
        }

        // Submit button
        buttonSubmit.setOnClickListener {
            if (validateForm(editTextMSSV, editTextName, radioGroupGender, editTextEmail, editTextPhone, checkBoxAgree)) {
                Toast.makeText(this, "Form submitted successfully!", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Please complete all required fields.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun validateForm(
        editTextMSSV: EditText, editTextName: EditText, radioGroupGender: RadioGroup,
        editTextEmail: EditText, editTextPhone: EditText, checkBoxAgree: CheckBox
    ): Boolean {
        if (editTextMSSV.text.isEmpty() || editTextName.text.isEmpty() ||
            radioGroupGender.checkedRadioButtonId == -1 ||
            editTextEmail.text.isEmpty() || editTextPhone.text.isEmpty() ||
            !checkBoxAgree.isChecked
        ) {
            return false
        }
        return true
    }
}