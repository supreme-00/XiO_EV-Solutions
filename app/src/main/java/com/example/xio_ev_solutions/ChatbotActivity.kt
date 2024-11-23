package com.example.xio_ev_solutions

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ChatbotActivity : AppCompatActivity() {

    private lateinit var userInput: EditText
    private lateinit var chatLog: TextView
    private lateinit var sendButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chatbot)

        userInput = findViewById(R.id.userInput)
        chatLog = findViewById(R.id.chatLog)
        sendButton = findViewById(R.id.sendButton)

        sendButton.setOnClickListener {
            val userMessage = userInput.text.toString().trim()
            if (userMessage.isNotEmpty()) {
                updateChat("You: $userMessage")
                userInput.text.clear()
                sendToChatbot(userMessage)
            }
        }
    }

    private fun updateChat(message: String) {
        chatLog.append("$message\n\n")
    }

    private fun sendToChatbot(message: String) {
        // Create JSON object for the request body
        val jsonBody = JsonObject()
        jsonBody.addProperty("model", "text-davinci-003")
        jsonBody.addProperty("prompt", message)
        jsonBody.addProperty("max_tokens", 150)
        jsonBody.addProperty("temperature", 0.7)

        // Send the request using Retrofit
        val call = RetrofitClient.api.getChatbotResponse(jsonBody)
        call.enqueue(object : Callback<JsonObject> {
            override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
                if (response.isSuccessful) {
                    val botResponse = response.body()?.getAsJsonArray("choices")
                        ?.get(0)?.asJsonObject?.get("text")?.asString
                    updateChat("Chatbot: ${botResponse ?: "No response"}")
                } else {
                    updateChat("Chatbot: Something went wrong. Please try again.")
                }
            }

            override fun onFailure(call: Call<JsonObject>, t: Throwable) {
                updateChat("Chatbot: Sorry, I couldn't connect to the server.")
            }
        })
    }
}
