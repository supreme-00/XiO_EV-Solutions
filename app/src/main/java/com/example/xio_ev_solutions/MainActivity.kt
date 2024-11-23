package com.example.xio_ev_solutions

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.widget.Button
import android.widget.EditText

data class EVProblem(val problem: String, val solution: String, val reference: String)

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var searchBar: EditText
    private lateinit var adapter: EVProblemAdapter
    private lateinit var navigateButton: Button
    private val problemsList = mutableListOf(
        EVProblem(
            "Limited Driving Range",
            "Plan trips, use regenerative braking, maintain efficient driving habits.",
            "https://ev-database.org/ev-problems-range"
        ),
        EVProblem(
            "Slow Charging Times",
            "Use fast chargers, install a Level 2 charger at home, maintain charging equipment.",
            "https://pluginamerica.org/ev-charging-guide"
        ),
        EVProblem(
            "Battery Degradation",
            "Avoid full charges and deep discharges, park in shade, perform regular checks.",
            "https://evcentral.com.au/battery-health-tips"
        ),
        EVProblem(
            "High Repair Costs",
            "Choose extended warranties, visit specialized repair centers, maintain regularly.",
            "https://evrepaircosts.com"
        ),
        EVProblem(
            "Lack of Charging Infrastructure",
            "Use apps to locate stations, advocate for more infrastructure, consider hybrids.",
            "https://chargepoint.com/charging-network"
        )
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        searchBar = findViewById(R.id.searchBar)
        navigateButton = findViewById(R.id.navigateButton)

        // Set up RecyclerView
        adapter = EVProblemAdapter(problemsList) { referenceUrl ->
            openWebPage(referenceUrl)
        }
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        // Search bar functionality
        searchBar.addTextChangedListener(object : android.text.TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val query = s.toString().lowercase()
                val filteredList = problemsList.filter {
                    it.problem.lowercase().contains(query)
                }
                adapter.updateList(filteredList)
            }

            override fun afterTextChanged(s: android.text.Editable?) {}
        })

        // Navigate button functionality
        navigateButton.setOnClickListener {
            val intent = Intent(this, ChatbotActivity::class.java)
            startActivity(intent)
        }
    }

    private fun openWebPage(url: String) {
        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        startActivity(browserIntent)
    }
}
