package com.example.xio_ev_solutions

import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface ChatbotApiService {
    @Headers(
        "Content-Type: application/json",
        "Authorization: Bearer YOUR_API_KEY"
    )
    @POST("completions")
    fun getChatbotResponse(@Body requestBody: JsonObject): Call<JsonObject>
}
