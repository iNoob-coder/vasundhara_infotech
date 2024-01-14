package com.example.assessment.activity.activity

import android.app.DatePickerDialog
import android.icu.text.SimpleDateFormat
import android.icu.util.Calendar
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Patterns
import android.widget.Toast
import com.example.assessment.activity.util.Preference
import com.example.assessment.databinding.ActivityRegistrationFormBinding
import java.util.Locale

class RegistrationForm : AppCompatActivity() {

    private lateinit var binding: ActivityRegistrationFormBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistrationFormBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //set user detail
        setUserDetails()

        binding.dob.setOnClickListener {
            showDatePicker()
        }
        binding.dob.keyListener = null

        binding.name.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                // Clear the error when the user types
                binding.name.error = null
            }

            override fun afterTextChanged(s: Editable?) {}
        })

        binding.btnSubmit.setOnClickListener {
            // Clear previous errors
            clearErrors()

            if (binding.name.text!!.isEmpty()) {
                binding.name.error = "Please enter name"
            } else if (!isNameValid(binding.name.text.toString())) {
                binding.name.error = "Please enter correct name"
            } else if (binding.email.text!!.isEmpty()) {
                binding.email.error = "Please enter email"
            } else if (!isEmailValid(binding.email.text.toString())) {
                binding.email.error = "Please enter correct email"
            } else if (binding.dob.text!!.isEmpty()) {
                binding.dob.error = "Please enter dob"
            } else if (binding.phone.text!!.isEmpty()) {
                binding.phone.error = "Please enter phone number"
            } else if (!isPhoneNumberValid(binding.phone.text.toString())) {
                binding.phone.error = "Please enter correct phone number"
            } else if (binding.address.text!!.isEmpty()) {
                binding.address.error = "Please enter address"
            } else {
                saveUserDetails()
                Toast.makeText(this, "All inputs are correct", Toast.LENGTH_SHORT).show()
            }
        }

    }

    private fun isNameValid(name: String): Boolean {
        val regex = Regex("^[a-zA-Z][a-zA-Z ]*\$")
        return name.matches(regex)
    }

    private fun isEmailValid(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    private fun isPhoneNumberValid(phoneNumber: String): Boolean {
        if (binding.phone.text!!.length != 10) return false

        val regex = Regex("^[6-9]\\d{9}$")
        return regex.matches(phoneNumber)
    }

    private fun showDatePicker() {
        val calendar = Calendar.getInstance()

        val datePickerDialog = DatePickerDialog(
            this,
            { _, year, month, dayOfMonth ->
                val selectedDate = Calendar.getInstance()
                selectedDate.set(year, month, dayOfMonth)

                val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
                binding.dob.setText(dateFormat.format(selectedDate.time))
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )

        datePickerDialog.datePicker.maxDate = System.currentTimeMillis() - 1000

        datePickerDialog.show()
    }

    private fun clearErrors() {
        binding.name.error = null
        binding.email.error = null
        binding.dob.error = null
        binding.phone.error = null
        binding.address.error = null
    }

    private fun saveUserDetails() {
        Preference.saveUserDetails(
            this,
            binding.name.text.toString(),
            binding.email.text.toString(),
            binding.dob.text.toString(),
            binding.phone.text.toString(),
            binding.address.text.toString()
        )
    }

    private fun setUserDetails() {
        binding.name.text =
            Editable.Factory.getInstance().newEditable(Preference.getName(this))
        binding.email.text =
            Editable.Factory.getInstance().newEditable(Preference.getEmail(this))
        binding.dob.text =
            Editable.Factory.getInstance().newEditable(Preference.getDob(this))
        binding.phone.text =
            Editable.Factory.getInstance().newEditable(Preference.getPhone(this))
        binding.address.text = Editable.Factory.getInstance()
            .newEditable(Preference.getAddress(this))
    }

}