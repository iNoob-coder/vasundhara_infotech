package com.example.assessment.activity.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.example.assessment.R
import com.example.assessment.databinding.ActivityEditTextAssessmentBinding

class EditTextAssessment : AppCompatActivity() {

    private lateinit var binding: ActivityEditTextAssessmentBinding
    private lateinit var linearLayoutInputs: LinearLayout
    private lateinit var tvResult: TextView
    private var editTextCount = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditTextAssessmentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        linearLayoutInputs = binding.linearLayoutInputs  // Initialize linearLayoutInputs
        tvResult = findViewById(R.id.tvResult)
        val btnAddInput = findViewById<ImageView>(R.id.btnAddInput)
        val btnSum = findViewById<ImageView>(R.id.btnSum)

        btnAddInput.setOnClickListener { addNewEditText() }
        btnSum.setOnClickListener { calculateSum() }
    }

    private fun addNewEditText() {
        val newEditText = EditText(this)
        newEditText.layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        newEditText.hint = "Enter value"
        newEditText.id = editTextCount++
        linearLayoutInputs.addView(newEditText)
    }

    private fun calculateSum() {
        var sum = 0
        for (i in 0 until linearLayoutInputs.childCount) {
            val child = linearLayoutInputs.getChildAt(i)
            if (child is EditText) {
                val value = child.text.toString().toIntOrNull() ?: 0
                sum += value
            }
        }
        tvResult.text = "Sum: $sum"
    }
}