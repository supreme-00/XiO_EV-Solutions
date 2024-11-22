package com.example.xio_ev_solutions

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class PasswordActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_password)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val submit_button= findViewById<Button>(R.id.submit_button)
        submit_button.setOnClickListener {
            Intent(
                this,
                LoginActivity::class.java
            ).also { startActivity(it) }
        }
        val terms = findViewById<CheckBox>(R.id.terms)
        terms.setOnClickListener{
            Intent(
                this,
                TermsConditionsActivity::class.java).also { startActivity(it) }
        }
    }
}