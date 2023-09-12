package com.example.razzashoppingapp.view.screens.admin_screens

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.content.Context
import android.content.Intent
import android.widget.DatePicker
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat.startActivity
import androidx.navigation.NavHostController
import com.example.razzashoppingapp.BottomBar
import com.example.razzashoppingapp.TopBar
import com.example.razzashoppingapp.view.navigation.NavScreen
import java.util.*

public var publicDateFrom: String = ""
public var publicDateTo: String = ""

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ReportsScreen(navController: NavHostController
) {
    Scaffold(
        bottomBar = {
            BottomBar(navController = navController)
        },

        topBar = {
            TopBar(navController = navController)
        },

        )
    {
    }
    showDatePicker(LocalContext.current, navController)
}

@Composable
fun showDatePicker(context: Context, navController: NavHostController) {
    val year: Int;
    val month: Int;
    val day: Int;

    val calender = Calendar.getInstance()
    year = calender.get(Calendar.YEAR)
    month = calender.get(Calendar.MONTH)
    day = calender.get(Calendar.DAY_OF_MONTH)

    calender.time = Date()
    val dateFrom = remember {mutableStateOf("")}
    val dateTo = remember {mutableStateOf("") }

    val datePickerDialogFrom = DatePickerDialog(
        context,
        { _: DatePicker, year: Int, month: Int, dayOfMonth: Int ->
            dateFrom.value = "$dayOfMonth-${month + 1}-$year"
        }, year, month, day
    )
    val datePickerDialogTo = DatePickerDialog(
        context,
        {_:DatePicker, year: Int, month: Int, dayOfMonth: Int ->
            dateTo.value = "$dayOfMonth-${month + 1}-$year"
        }, year, month, day
    )

    Column(
        modifier = Modifier
            .padding(vertical = 80.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "RAZZA",
            color = Color(0xFFB5A9A9),
            fontSize = 20.sp,
            textAlign = TextAlign.Center
        )
        Text(
            text = "ADMIN REPORTS",
            color = Color.Black,
            fontSize = 27.sp,
            textAlign = TextAlign.Center
        )
        Divider(color = Color(0xFF8D8D8D), thickness = 1.dp, modifier = Modifier.padding(20.dp))
        Column(
            modifier = Modifier
                .padding(vertical = 15.dp, horizontal = 30.dp)
                .fillMaxSize(),
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = "REQUEST A REPORT",
                color = Color(0xFF745D5D),
                fontSize = 18.sp,
                textAlign = TextAlign.Left,
                fontWeight = FontWeight.SemiBold
            )
            Divider(color = Color.White, thickness = 1.dp, modifier = Modifier.padding(20.dp))
            Text(
                text = "Pick a date: ",
                color = Color(0xFF745D5D),
                fontSize = 16.sp,
                textAlign = TextAlign.Left,
                fontWeight = FontWeight.SemiBold
            )
            Column(
                modifier = Modifier
                    .padding(vertical = 30.dp, horizontal = 20.dp)
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.Start
            ) {
                Row(modifier = Modifier.padding(horizontal = 15.dp)) {
                    Text(
                        text = "From : ",
                        color = Color(0xFF745D5D),
                        fontSize = 16.sp,
                        textAlign = TextAlign.Left
                    )
                    Spacer(modifier = Modifier.padding(10.dp))

                    Button(
                        colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF745D5D)),
                        shape = RoundedCornerShape(20.dp),
                        modifier = Modifier
                            .width(200.dp)
                            .height(40.dp),

                        onClick = { datePickerDialogFrom.show() }) {
                        Text(
                            text = " DATE ${dateFrom.value}",
                            color = Color.White,
                            fontSize = 11.sp
                        )

                    }
                    Text(text = " ${dateFrom.value} ", color = Color.White, fontSize = 11.sp)
                }
                Divider(color = Color.White, thickness = 1.dp, modifier = Modifier.padding(5.dp))
                Row(modifier = Modifier.padding(horizontal = 15.dp)) {
                    Text(
                        text = "To : ",
                        color = Color(0xFF745D5D),
                        fontSize = 16.sp,
                        textAlign = TextAlign.Left
                    )
                    Spacer(modifier = Modifier.padding(20.dp))

                    Button(
                        colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF745D5D)),
                        shape = RoundedCornerShape(20.dp),
                        modifier = Modifier
                            .width(200.dp)
                            .height(40.dp),

                        onClick = { datePickerDialogTo.show() }) {
                        Text(text = " DATE ${dateTo.value} ", color = Color.White, fontSize = 11.sp)

                    }
                    Text(text = " ${dateTo.value} ", color = Color.White, fontSize = 11.sp)
                }
                Divider(color = Color.White, thickness = 1.dp, modifier = Modifier.padding(20.dp))
                Button(
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFFB5A9A9)),
                    shape = RoundedCornerShape(20.dp),
                    modifier = Modifier
                        .width(200.dp)
                        .height(40.dp)
                        .align(alignment = Alignment.CenterHorizontally),
                    onClick = {
                        publicDateFrom = dateFrom.value
                        publicDateTo = dateTo.value
                        navController.navigate(route = NavScreen.ReportsRequested.route)
                    }) {
                    Text(text = "Search", color = Color.White, fontSize = 11.sp)

                }
            }
        }
    }
}









