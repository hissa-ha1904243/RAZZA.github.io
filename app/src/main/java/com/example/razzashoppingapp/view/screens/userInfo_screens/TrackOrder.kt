package com.example.razzashoppingapp.view.screens.userInfo_screens

import android.annotation.SuppressLint
import androidx.activity.ComponentActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.razzashoppingapp.BottomBar
import com.example.razzashoppingapp.TopBar
import com.example.razzashoppingapp.R
import com.example.razzashoppingapp.room.entity.Order
import com.example.razzashoppingapp.viewmodel.AuthViewModel
import com.example.razzashoppingapp.viewmodel.RazzaViewModel


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun TrackOrder(navController: NavHostController,
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

    }
    trackLayout()
//    picture()
}
@Composable
fun trackLayout(razzaViewModel: RazzaViewModel = viewModel(LocalContext.current as ComponentActivity)){
    val currentOrder: Order by razzaViewModel.order.collectAsState()

    Column(
        modifier = Modifier
            .padding(vertical = 22.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(40.dp))

        Text(
            text = "RAZZA",
            color = Color(0xFFB5A9A9),
            fontSize = 20.sp,
            textAlign = TextAlign.Center
        )


        Text(
            text = "TRACK YOUR ORDER",
            color = Color.Black,
            fontSize = 27.sp,
            textAlign = TextAlign.Center
        )
        Divider(color = Color(0xFF8D8D8D), thickness = 1.dp, modifier = Modifier.padding(20.dp))
        Column(
            modifier = Modifier
                .padding(vertical = 22.dp, horizontal = 10.dp)
                .fillMaxSize(),
        ) {
            Spacer(modifier = Modifier.height(15.dp))
            Text(
                text = "Order Number: ${currentOrder.id} ",
                color = Color(0xFF745D5D),
                fontSize = 22.sp,
                textAlign = TextAlign.Left

                )
            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = "Order Status: ${currentOrder.status}",
                color = Color(0xFF745D5D),
                fontSize = 22.sp,
                textAlign = TextAlign.Left
            )
            Spacer(modifier = Modifier.height(20.dp))

        }
    }
    when (currentOrder.status) {
        "processing" -> {
            Column() {
                Spacer(modifier = Modifier.height(250.dp))
                Image(
                    painter = painterResource(R.drawable.processing),
                    contentDescription = "Processing",
                    modifier = Modifier
                        .fillMaxSize()

                )
            }
        }
        "shipped" -> {
            Column() {
                Spacer(modifier = Modifier.height(250.dp))
                Image(
                    painter = painterResource(id = R.drawable.shipped),
                    contentDescription = "Shipped",
                    modifier = Modifier
                        .fillMaxSize()

                )
            }
        }
        "delivered" -> {
            Column() {
                Spacer(modifier = Modifier.height(250.dp))
                Image(
                    painter = painterResource(id = R.drawable.delivered),
                    contentDescription = "Delivered",
                    modifier = Modifier
                        .fillMaxSize()

                )
            }
        }
        else -> null
    }
}

//@Composable
//fun picture(){
//val state: String
//state = "shipped"
//
//}