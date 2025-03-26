package com.example.bt2t3.ui.screens

import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.bt2t3.R
import com.example.bt2t3.data.Task
import com.google.gson.Gson

@Composable
fun DetailScreen(navController: NavController,  taskJson: String) {
    val task = remember { Gson().fromJson(Uri.decode(taskJson), Task::class.java) }
    Scaffold(
        topBar = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp, vertical = 10.dp)
                    .padding(top = 10.dp)
            ) {
                IconButton(
                    onClick = { navController.popBackStack() },
                    modifier = Modifier.align(Alignment.CenterStart)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.img_11),
                        contentDescription = "Back",
                        modifier = Modifier.size(40.dp)
                    )
                }

                Text(
                    text = "Detail",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF2196F3),
                    modifier = Modifier.align(Alignment.Center)
                )

                IconButton(
                    onClick = { navController.popBackStack() },
                    modifier = Modifier.align(Alignment.CenterEnd)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.img_12),
                        contentDescription = "Back",
                        modifier = Modifier.size(40.dp)
                    )
                }
            }
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .padding(paddingValues)
                .padding(16.dp)
                .fillMaxSize()
        ) {
            // Tiêu đề và mô tả
            item {
                Text(
                    text = task.title,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = task.description ?: "",
                    fontSize = 16.sp,
                )
                Spacer(modifier = Modifier.height(12.dp))
            }

            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color(0xFFE1BBC1), RoundedCornerShape(10.dp))
                        .padding(12.dp)
                        .height(60.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {                             Icon(
                                painter = painterResource(id = R.drawable.img_13),
                                contentDescription = "Category",
                                modifier = Modifier.size(28.dp)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Column {
                                Text(text = "Category", fontSize = 14.sp)
                                Text(
                                    text = task.category,
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }
                        Row(verticalAlignment = Alignment.CenterVertically) {                             Icon(
                                painter = painterResource(id = R.drawable.img_14),
                                contentDescription = "Status",
                                modifier = Modifier.size(28.dp)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Column {
                                Text(text = "Status", fontSize = 14.sp)
                                Text(
                                    text = task.status,
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }
                        Row(verticalAlignment = Alignment.CenterVertically) {                             Icon(
                                painter = painterResource(id = R.drawable.img_15),
                                contentDescription = "Priority",
                                modifier = Modifier.size(28.dp)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Column {
                                Text(text = "Priority", fontSize = 14.sp)
                                Text(
                                    text = task.priority,
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))
            }

            item {
                Text(text = "Subtasks", fontSize = 18.sp, fontWeight = FontWeight.Bold)
            }
            items(task.subtasks) { subtask ->
                val isChecked = remember { mutableStateOf(subtask.isCompleted) }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 6.dp)
                        .background(Color(0xFFE6E6E6), RoundedCornerShape(10.dp))
                        .padding(12.dp) ,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Checkbox(
                        checked = isChecked.value,
                        onCheckedChange = { isChecked.value = it },
                        colors = CheckboxDefaults.colors(checkedColor = Color.Black),
                        modifier = Modifier.size(24.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(text = subtask.title, fontSize = 16.sp)
                }
            }

            // Attachments
            item {
                Spacer(modifier = Modifier.height(16.dp))
                Text(text = "Attachments", fontSize = 18.sp, fontWeight = FontWeight.Bold)
            }
            items(task.attachments) { attachment ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 6.dp)
                        .background(Color(0xFFE6E6E6), RoundedCornerShape(10.dp))
                        .padding(12.dp)
                        .clickable { /* TODO: Open file */ },
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.img_16),
                        contentDescription = "Attachment",
                        modifier = Modifier.size(28.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(text = attachment.fileName, fontSize = 16.sp)
                }
            }
        }
    }
}