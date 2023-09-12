package com.example.razzashoppingapp.view.screens.admin_screens

import android.annotation.SuppressLint
import androidx.activity.ComponentActivity
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.razzashoppingapp.BottomBar
import com.example.razzashoppingapp.R
import com.example.razzashoppingapp.view.navigation.NavScreen
import com.example.razzashoppingapp.TopBar
import com.example.razzashoppingapp.room.entity.User
import com.example.razzashoppingapp.viewmodel.AuthViewModel
import com.example.razzashoppingapp.viewmodel.RazzaViewModel

@OptIn(ExperimentalFoundationApi::class)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun AdminProfileScreen(navController : NavHostController,
                  razzaViewModel: RazzaViewModel = viewModel(LocalContext.current as ComponentActivity)
) {
    val authViewModel =
        viewModel<AuthViewModel>(viewModelStoreOwner = LocalContext.current as ComponentActivity)

    val currentUser: User by razzaViewModel.user.collectAsState()

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
            text = "MY PROFILE",
            color = Color.Black,
            fontSize = 27.sp,
            textAlign = TextAlign.Center
        )
        Divider(color = Color(0xFF8D8D8D), thickness = 1.dp, modifier = Modifier.padding(20.dp))

    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(30.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    )
    {
        //Spacer(modifier = Modifier.height(40.dp))
        /*Text(text = "Razza", style = MaterialTheme.typography.h5, color = Color(0xFF745D5D))
        Text(text = "My Profile", style = MaterialTheme.typography.h4)*/

        Column(
            modifier = Modifier
                .height(450.dp)
                .width(300.dp)
                .padding(16.dp),
            verticalArrangement = Arrangement.Bottom,
        )
        {

                Image(
                    painter = painterResource(id = R.drawable.profile_pic),
                    contentDescription = "Profile Pic",
                    modifier = Modifier.size(200.dp).align(Alignment.CenterHorizontally)
                )
                Text(
                    text = "Email: ${authViewModel.user?.email}",
                    style = MaterialTheme.typography.h6,
                    color = Color(0xFF745D5D)
                )

        }
        Column(
            modifier = Modifier
                .height(150.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF745D5D)),
                onClick = {
                    razzaViewModel.clearData()
                    authViewModel.signOut()
                    navController.navigate(NavScreen.LogIn.route)

                }) {
                Box(
                    modifier = Modifier
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                        .width(170.dp)
                        .height(16.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Logout",
                        color = Color.White,
                        fontWeight = FontWeight.Normal
                    )
                }
            }
            Button(colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF745D5D)),
                onClick = { navController.navigate(NavScreen.AllOrders.route)}) {
                Box(
                    modifier = Modifier
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                        .width(170.dp)
                        .height(16.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "All orders",
                        color = Color.White,
                        fontWeight = FontWeight.Normal
                    )
                }
            }

        }
        Row(
            modifier = Modifier
                .height(70.dp)
                .width(235.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(
                colors = ButtonDefaults.buttonColors(backgroundColor = Color.LightGray),
                onClick = { navController.navigate(NavScreen.Reports.route) }
            ) {
                Box(
                    modifier = Modifier
                        .padding(horizontal = 3.dp, vertical = 8.dp)
                        .width(70.dp)
                        .height(30.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "REPORTS", color = Color.Black,
                        fontSize = 10.sp
                    )
                }
            }
                Button(colors = ButtonDefaults.buttonColors(backgroundColor = Color.LightGray),
                    onClick = {navController.navigate(NavScreen.Dashboard.route) }) {
                    Box(
                        modifier = Modifier
                            .padding(horizontal = 3.dp, vertical = 8.dp)
                            .width(80.dp)
                            .height(30.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "DASHBOARD", color = Color.Black,
                            fontSize = 10.sp
                        )
                    }
                }

                    }
                }

            }



