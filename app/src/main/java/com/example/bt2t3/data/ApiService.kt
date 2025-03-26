package com.example.bt2t3.data

import retrofit2.http.GET

interface ApiService {
    @GET("researchUTH/tasks")
    suspend fun getTasks(): ApiResponse<List<Task>>
}
