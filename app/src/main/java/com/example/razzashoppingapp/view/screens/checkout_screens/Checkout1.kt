package com.example.razzashoppingapp.view.screens.checkout_screens

import android.annotation.SuppressLint
import androidx.activity.ComponentActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.razzashoppingapp.R
import com.example.razzashoppingapp.view.navigation.NavScreen
import com.example.razzashoppingapp.TopBar
import com.example.razzashoppingapp.room.entity.Address
import com.example.razzashoppingapp.view.screens.userInfo_screens.AddressList
import com.example.razzashoppingapp.viewmodel.AuthViewModel
import com.example.razzashoppingapp.viewmodel.RazzaViewModel

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun CheckOut1(navController:NavHostController,
              razzaViewModel: RazzaViewModel = viewModel(LocalContext.current as ComponentActivity)

) {
    val authViewModel =
        viewModel<AuthViewModel>(viewModelStoreOwner = LocalContext.current as ComponentActivity)

    //navController: NavHostController
    Scaffold(
         /*bottomBar = {
            BottomBar(navController = navController)
        },*/

          topBar = {
            TopBar(navController = navController)
        },

    )
    {




    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(vertical = 5.dp, horizontal = 10.dp),




    ) {
        Spacer(modifier = Modifier.height(40.dp))
        Image(
            painter = painterResource(id = R.drawable.checkout1),
            contentDescription = "Checkout",
            modifier = Modifier
                .fillMaxWidth()

        )
        Spacer(modifier = Modifier.height(40.dp))

        Text(
            text = "Shipping Details",
            color = Color(0xFF413232),
            fontSize = 26.sp,
            fontWeight = FontWeight.Medium,

            )
        Divider(color = Color(0xFF8D8D8D), thickness = 1.dp, modifier = Modifier.padding(10.dp))
        Spacer(modifier = Modifier.height(0.dp))
        Text(
            text = "Shipping To:  ",
            color = Color(0xFF8D8D8D),
            fontSize = 16.sp,
        )
        val addresses: List<Address> by razzaViewModel.addressesFB.collectAsState()

        AddressList(navController,addresses)
        Spacer(modifier = Modifier.height(0.dp))
        Divider(color = Color(0xFF8D8D8D), thickness = 1.dp, modifier = Modifier.padding(10.dp))
    }

    Column(modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {
        Spacer(modifier =Modifier.height(200.dp) )
        Button(
            colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF745D5D)),
            shape = RoundedCornerShape(5.dp),
            modifier = Modifier
                .width(270.dp)
                .height(55.dp),

            onClick = { navController.navigate(NavScreen.Checkout2.route)}) {
            Text(text = "Continue to Checkout ", color = Color.White, fontSize = 22.sp)
        }
    }
}}