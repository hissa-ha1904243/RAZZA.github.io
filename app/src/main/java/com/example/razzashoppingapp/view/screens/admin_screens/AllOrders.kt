package com.example.razzashoppingapp.view.screens.admin_screens


import android.annotation.SuppressLint
import androidx.activity.ComponentActivity
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import com.example.razzashoppingapp.view.navigation.NavScreen
import com.example.razzashoppingapp.TopBar
import com.example.razzashoppingapp.room.entity.Order
import com.example.razzashoppingapp.room.entity.User
import com.example.razzashoppingapp.viewmodel.AuthViewModel
import com.example.razzashoppingapp.viewmodel.RazzaViewModel

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun AllOrders(navController: NavHostController,
              razzaViewModel: RazzaViewModel = viewModel(LocalContext.current as ComponentActivity)
) {
    val authViewModel =
        viewModel<AuthViewModel>(viewModelStoreOwner = LocalContext.current as ComponentActivity)

    Scaffold(
        bottomBar = {
            BottomBar(navController = navController)
        },

        topBar = {
            TopBar(navController = navController)
        },

        )
    {

        val Orderstatus = remember { mutableStateListOf<Order>() }
        val orders: List<Order> by razzaViewModel.ordersFB.collectAsState()
        Adminorderlayout(Orderstatus,orders, navController)


    }
}
@Composable
fun Adminorderlayout(status: SnapshotStateList<Order>,
                     orders: List<Order>, navController: NavHostController,
                     razzaViewModel: RazzaViewModel = viewModel(LocalContext.current as ComponentActivity)
) {
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
            text = "All ORDERS",
            color = Color.Black,
            fontSize = 27.sp,
            textAlign = TextAlign.Center
        )
        Divider(color = Color(0xFF8D8D8D), thickness = 1.dp, modifier = Modifier.padding(20.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 5.dp),
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
                    for(o in orders)
                        if(o.status == "processing"){
                            status.add(o)
                        }
                }) {
                Text(text = "PROCESSING ", color = Color.White, fontSize = 11.sp)

            }

            Button(
                colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF745D5D)),
                shape = RoundedCornerShape(20.dp),
                modifier = Modifier
                    .width(115.dp)
                    .height(40.dp),

                onClick = {
                    status.clear()
                    for(o in orders)
                        if(o.status == "shipped"){
                            status.add(o)
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
                    for(o in orders)
                        if(o.status == "delivered"){
                            status.add(o)
                        }
                }) {
                Text(text = "DELIVERED ", color = Color.White, fontSize = 11.sp)

            }
        }
        AllOrders(status,orders, navController)
    }


}
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun AllOrders(status: SnapshotStateList<Order>,orders: List<Order>, navController: NavHostController,
             razzaViewModel: RazzaViewModel = viewModel(LocalContext.current as ComponentActivity)

){
    var count=0
    for(o in orders){
            count++
        }


    Column(){
        if (orders.isEmpty()) {
            Text("Loading orders failed.")
        } else {
            LazyColumn( modifier = Modifier
                .padding(8.dp)
                .weight(0.5f)) {

                if(status.isNotEmpty()){
                    items(status) {
                        AdminAllOrdersCard(it, navController)
                    }
                }
                else if (status.isEmpty()) {
                    items(orders) {
                        AdminAllOrdersCard(it, navController)
                    }
                }


            }

        }

    }}
@Composable
fun AdminAllOrdersCard(order: Order, navController : NavHostController,
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
                        NavScreen.MyOrder2.route
                    )
                },
            backgroundColor = Color.White,
            shape = RoundedCornerShape(corner = CornerSize(30.dp)),
            elevation = 10.dp
        ) {
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
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





























