package com.example.xio_ev_solutions

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.android.material.bottomnavigation.BottomNavigationView

data class EVProblem(val problem: String, val solution: String, val reference: String)

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bottomNav = findViewById<BottomNavigationView>(R.id.bottomNav)

        bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_food -> {
                    Toast.makeText(this, "Food Clicked", Toast.LENGTH_SHORT).show()
                    true
                }
                R.id.nav_card -> {
                    Toast.makeText(this, "Card Clicked", Toast.LENGTH_SHORT).show()
                    true
                }
                R.id.nav_reorder -> {
                    Toast.makeText(this, "Reorder Clicked", Toast.LENGTH_SHORT).show()
                    true
                }
                else -> false
            }
        }
    }
}