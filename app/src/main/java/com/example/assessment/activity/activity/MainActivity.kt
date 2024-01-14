package com.example.assessment.activity.activity

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.assessment.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.editTextTask.setOnClickListener {
            startActivity(Intent(this@MainActivity, EditTextAssessment::class.java))
        }

        binding.registrationForm.setOnClickListener {
            startActivity(Intent(this@MainActivity, RegistrationForm::class.java))
        }

        binding.stickyNotes.setOnClickListener {
            startActivity(Intent(this@MainActivity, StickyNotes::class.java))
        }

        binding.doc.setOnClickListener {
            val docUrl = "https://docs.google.com/document/d/1NMrwG9Tl2t7Ji8VROQxieIpCQNf8jL938Y7xknlfQ3w/edit?usp=sharing"

            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(docUrl))
            startActivity(intent)
        }

    }
}
