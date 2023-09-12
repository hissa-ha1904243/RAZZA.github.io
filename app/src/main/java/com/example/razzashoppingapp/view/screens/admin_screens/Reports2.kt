package com.example.razzashoppingapp.view.screens.admin_screens


import android.annotation.SuppressLint
import androidx.activity.ComponentActivity
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.razzashoppingapp.BottomBar
import com.example.razzashoppingapp.TopBar
import com.example.razzashoppingapp.room.entity.Order
import com.example.razzashoppingapp.view.navigation.NavScreen
import com.example.razzashoppingapp.viewmodel.RazzaViewModel
import java.text.SimpleDateFormat
import java.util.*

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun Reports2(navController: NavHostController,
             razzaViewModel: RazzaViewModel = viewModel(LocalContext.current as ComponentActivity)

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
        val orders: List<Order> by razzaViewModel.ordersFB.collectAsState()
        val adminStatus = remember { mutableStateListOf<Order>() }
        val filteredOrders: MutableList<Order> = mutableListOf()

        val sdf = SimpleDateFormat("dd-MM-yyyy")
        val strDateFrom: Date = sdf.parse(publicDateFrom)
        val strDateTo: Date = sdf.parse(publicDateTo)
        for(order in orders){
            val strOrderDate: Date = sdf.parse(order.date)
            if (strOrderDate.after(strDateFrom) && strOrderDate.before(strDateTo))
                filteredOrders.add(order)
        }

        Reports2Layout(adminStatus, orderModels = filteredOrders , navController = navController, "Requested Reports")
    }
}

@Composable
fun Reports2Layout(status: SnapshotStateList<Order>, orderModels: MutableList<Order>,
                   navController: NavHostController, pageTitle: String) {
    Column(
        modifier = Modifier
            .padding(vertical = 22.dp)
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
            text = "${pageTitle}\n(${publicDateFrom} to ${publicDateTo})",
            color = Color.Black,
            fontSize = 27.sp,
            textAlign = TextAlign.Center
        )
        Divider(color = Color(0xFF8D8D8D), thickness = 1.dp, modifier = Modifier.padding(20.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 5.dp)
                .horizontalScroll(rememberScrollState()),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            Button(
                colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF745D5D)),
                shape = RoundedCornerShape(20.dp),
                modifier = Modifier
                    .width(115.dp)
                    .height(40.dp),

                onClick = {
                    status.clear()
                    for(order in orderModels)
                        if(order.status == "processing" || order.status == "shipped" || order.status == "delivered"){
                            status.add(order)
                        }
                }) {
                Text(text = "All", color = Color.White, fontSize = 11.sp)
            }
            Button(
                colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF745D5D)),
                shape = RoundedCornerShape(20.dp),
                modifier = Modifier
                    .width(115.dp)
                    .height(40.dp),

                onClick = {
                    status.clear()
                    for(order in orderModels)
                        if(order.status == "processing"){
                            status.add(order)
                        }
                }) {
                Text(text = "Processing", color = Color.White, fontSize = 11.sp)
            }
            Button(
                colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF745D5D)),
                shape = RoundedCornerShape(20.dp),
                modifier = Modifier
                    .width(115.dp)
                    .height(40.dp),

                onClick = {
                    status.clear()
                    for(order in orderModels)
                        if(order.status == "shipped"){
                            status.add(order)
                        }
                }) {
                Text(text = "Shipped ", color = Color.White, fontSize = 11.sp)
            }
            Button(
                colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF745D5D)),
                shape = RoundedCornerShape(20.dp),
                modifier = Modifier
                    .width(115.dp)
                    .height(40.dp),

                onClick = {
                    status.clear()
                    for(order in orderModels)
                        if(order.status == "delivered"){
                            status.add(order)
                        }
                }) {
                Text(text = "Delivered", color = Color.White, fontSize = 11.sp)
            }
        }

        Orders(status,orderModels, navController)
    }
}

@OptIn(ExperimentalFoundationApi::class)

@Composable
fun Orders(status: SnapshotStateList<Order>, orders: MutableList<Order>, navController: NavHostController){
    Column(){
        if (orders.isEmpty()) {
            Text("Loading orders failed.")
        } else {
            var countItems:Int = status.size
            var total: Double = 0.0
            if(countItems == 0)
                countItems = orders.size
            LazyColumn( modifier = Modifier
                .padding(8.dp)
                .weight(0.5f)) {
                stickyHeader {
                    if(status.isNotEmpty()){
                        for(order in status) {
                            total += order.total
                        }
                    }else if (status.isEmpty()) {
                        for(order in orders) {
                            total += order.total
                        }
                    }
                    Text(
                        text = "Total of $countItems orders\n Subtotal of $total QAR",
                        color = Color(0xFF8D8D8D),
                        modifier = Modifier
                            .background(Color.White)
                            .height(40.dp)
                            .fillMaxWidth(),
                        fontSize = 15.sp,
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.body2,

                        )
                }
                if(status.isNotEmpty()){
                    items(status) {
                        AdminsOrderCard(it, navController)
                        total += it.total
                    }
                }else if (status.isEmpty()) {
                    items(orders) {
                        AdminsOrderCard(it, navController)
                        total += it.total
                    }
                }
            }
        }
//        Text(text = "${total}",
//            color = Color.Black,
//            fontSize = 22.sp,
//            textAlign = TextAlign.Center)
    }
}

@Composable
fun AdminsOrderCard(order: Order, navController : NavHostController,
                    razzaViewModel: RazzaViewModel = viewModel(LocalContext.current as ComponentActivity)
){
    Card(
        Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .height(60.dp)
            .clickable {
                razzaViewModel.setCurrentOrder(order)
                navController.navigate(
                    NavScreen.Dash2.route
                )
            },backgroundColor = Color.White, shape = RoundedCornerShape(corner = CornerSize(30.dp)), elevation = 10.dp){
        Row(horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically){
            Text(
                text = "Order ${order.id}",
                color = Color(0xFF745D5D),
                style = MaterialTheme.typography.subtitle2,
                fontSize = 23.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}
