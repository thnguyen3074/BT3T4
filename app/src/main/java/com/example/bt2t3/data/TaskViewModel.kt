package com.example.bt2t3.data

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.State
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class TaskViewModel : ViewModel() {

    private val _tasks = MutableStateFlow<List<Task>>(emptyList())
    val tasks: StateFlow<List<Task>> = _tasks.asStateFlow()

    fun fetchTask() {
        viewModelScope.launch {
            try {
                val response = withContext(Dispatchers.IO) {
                    RetrofitInstance.apiService.getTasks()
                }
                if (response.isSuccess && response.data != null) {
                    _tasks.value = response.data
                } else {
                    println("Lỗi API: ${response.message}")
                }
            } catch (e: Exception) {
                println("Lỗi khi lấy dữ liệu từ API: ${e.message}")
            }
        }
    }
}
