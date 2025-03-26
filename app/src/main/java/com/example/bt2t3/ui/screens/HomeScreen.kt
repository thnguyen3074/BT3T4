package com.example.bt2t3.ui.screens

import android.net.Uri
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.BottomAppBar
import androidx.compose.material.icons.Icons
import com.example.bt2t3.R
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.Icon
import androidx.compose.foundation.layout.width
import androidx.compose.ui.res.painterResource
import androidx.compose.material3.IconButton
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.bt2t3.data.Task
import com.example.bt2t3.data.TaskViewModel
import com.google.gson.Gson

@Composable
fun HomeScreen(navController: NavController, taskViewModel: TaskViewModel = viewModel()) {
    val tasks by taskViewModel.tasks.collectAsState()

    LaunchedEffect(Unit) {
        taskViewModel.fetchTask()
    }

    Scaffold(
        topBar = {TopBar(navController)},
        bottomBar = {
            BottomBar(
                onHomeClick = { /* Handle Home */ },
                onCalendarClick = { /* Handle Calendar */ },
                onAddClick = { /* Handle Add */ },
                onNotesClick = { /* Handle Notes */ },
                onSettingsClick = { /* Handle Settings */ }
            )
        },
        floatingActionButton = {
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                FloatingActionButton(
                    onClick = { /* Handle Add Click */ },
                    containerColor = Color(0xFF2196F3),
                    shape = CircleShape,
                    modifier = Modifier.offset(y = 70.dp).size(55.dp)// Đẩy FAB xuống để nằm đúng vị trí
                ) {
                    Icon(Icons.Default.Add, contentDescription = "Add", tint = Color.White)
                }
            }
        },
        floatingActionButtonPosition = FabPosition.Center
    )  { paddingValues ->
        Box(
            modifier = Modifier
                .padding(paddingValues)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp),
            ) {
                if (tasks.isNotEmpty()) {
                    TaskList(navController)
                } else {
                    NoTasksView()
                }
            }
        }
    }
}



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(navController: NavController) {
    TopAppBar(
        title = {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = painterResource(id = R.drawable.img),
                    contentDescription = "Logo",
                    modifier = Modifier
                        .size(width = 68.dp, height = 66.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Column {
                    Text(
                        text = "SmartTasks",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF2196F3)
                    )
                    Text(
                        text = "A simple and efficient to-do app",
                        fontSize = 12.sp,
                        color = Color(0xFF3991D8)
                    )
                }
            }
        },
        actions = {
            IconButton(
                onClick = { /* Xử lý sự kiện khi nhấn */ },
                Modifier.align(Alignment.CenterVertically)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.img_5), // Thay bằng ảnh của bạn
                    contentDescription = "Notifications",
                    modifier = Modifier.size(24.dp) // Điều chỉnh kích thước ảnh
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.White)
    )
}

@Composable
fun BottomBar(
    onHomeClick: () -> Unit,
    onCalendarClick: () -> Unit,
    onAddClick: () -> Unit,
    onNotesClick: () -> Unit,
    onSettingsClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp, vertical = 10.dp) // Căn chỉnh lề cho phù hợp
    ) {
        // Bóng đổ ngoài bằng Box chứa Shadow
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(59.dp)
                .shadow(
                    elevation = 25.dp,
                    shape = RoundedCornerShape(30.dp), // Bo góc theo thiết kế
                    clip = false // Quan trọng: Không clip để bóng xuất hiện ra ngoài
                )
                .background(Color.White, shape = RoundedCornerShape(30.dp)) // Màu nền
        )

        BottomAppBar(
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .clip(RoundedCornerShape(30.dp)) // Bo góc nhưng không làm mất bóng
                .background(Color(0x12FFFFFF)), // Nền trong suốt nhẹ
            containerColor = Color.Transparent, // Tránh màu nền mặc định
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(
                    onClick = onHomeClick,
                    modifier = Modifier.size(48.dp) // Giữ kích thước cố định
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.img_6),
                        contentDescription = "Home",
                        tint = Color(0xFF2196F3),
                        modifier = Modifier.size(24.dp) // Cố định kích thước icon
                    )
                }

                IconButton(
                    onClick = onCalendarClick,
                    modifier = Modifier.size(48.dp)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.img_7),
                        contentDescription = "Calendar",
                        tint = Color.Gray,
                        modifier = Modifier.size(24.dp)
                    )
                }

                Spacer(modifier = Modifier.width(40.dp)) // Khoảng trống cho FAB

                IconButton(
                    onClick = onNotesClick,
                    modifier = Modifier.size(48.dp)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.img_8),
                        contentDescription = "Notes",
                        tint = Color.Gray,
                        modifier = Modifier.size(24.dp)
                    )
                }

                IconButton(
                    onClick = onSettingsClick,
                    modifier = Modifier.size(48.dp)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.img_9),
                        contentDescription = "Settings",
                        tint = Color.Gray,
                        modifier = Modifier.size(24.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun TaskList(navController: NavController) {
    val viewModel: TaskViewModel = viewModel()
    val tasks by viewModel.tasks.collectAsState()

    val colors = mapOf(
        "Completed" to Color(0xFFE1BBC1), // Màu đỏ
        "In Progress" to Color(0x4D8D9C0B), // Màu vàng
        "Pending" to Color(0xFFB7E9FF) // Màu xanh
    )

    LazyColumn(modifier = Modifier.padding(vertical = 10.dp)) {
        itemsIndexed(tasks) { index, task ->
            val backgroundColor = colors[task.status] ?: Color.LightGray

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(backgroundColor, shape = RoundedCornerShape(10.dp))
                    .padding(16.dp)
                    .clickable {
                        val taskJson = Uri.encode(Gson().toJson(task))
                        navController.navigate("DetailScreen/$taskJson")
                    }
            ) {
                Column {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Checkbox(
                            checked = task.subtasks?.all { it.isCompleted } == true,
                            onCheckedChange = { /* Thêm logic cập nhật trạng thái */ },
                            colors = CheckboxDefaults.colors(checkedColor = Color.Black)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = task.title,
                                fontWeight = FontWeight.Bold,
                                fontSize = 18.sp,
                                color = Color.Black
                            )
                            task.description?.let {
                                Text(
                                    text = it,
                                    fontSize = 16.sp,
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "Status: ${task.status}",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                        Text(
                            text = "${task.dueDate}",
                            fontSize = 16.sp,
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

@Composable
fun NoTasksView() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .width(350.dp)
            .height(255.dp)
            .background(Color(0x4DBBBBBB), shape = RoundedCornerShape(10.dp)),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.img_10),
                contentDescription = "No Data",
                modifier = Modifier.size(112.dp)
            )
            Spacer(modifier = Modifier.height(22.dp))
            Text(
                text = "No Tasks Yet!",
                style = TextStyle(
                    color = Color.Black,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                ),
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Stay productive—add something to do",
                style = TextStyle(
                    color = Color.Black,
                    fontSize = 16.sp,
                ),
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    com.example.bt2t3.ui.screens.HomeScreen(navController = rememberNavController())
}