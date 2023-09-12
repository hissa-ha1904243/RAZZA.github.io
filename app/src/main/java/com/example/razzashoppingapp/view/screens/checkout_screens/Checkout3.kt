package com.example.razzashoppingapp.view.screens.checkout_screens

import android.annotation.SuppressLint
import android.os.Build
import androidx.activity.ComponentActivity
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.Image
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.razzashoppingapp.R
import com.example.razzashoppingapp.view.navigation.NavScreen
import com.example.razzashoppingapp.TopBar
import com.example.razzashoppingapp.room.entity.Item
import com.example.razzashoppingapp.room.entity.Order
import com.example.razzashoppingapp.room.entity.ShoppingCart
import com.example.razzashoppingapp.room.entity.User
import com.example.razzashoppingapp.viewmodel.AuthViewModel
import com.example.razzashoppingapp.viewmodel.RazzaViewModel
import java.time.LocalDate
import java.time.Period


@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalComposeUiApi::class)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun Checkout3(navController:NavHostController,
              razzaViewModel: RazzaViewModel = viewModel(LocalContext.current as ComponentActivity)
) {
    val authViewModel =
        viewModel<AuthViewModel>(viewModelStoreOwner = LocalContext.current as ComponentActivity)

    val currentOrder: Order by razzaViewModel.order.collectAsState()
    var date = LocalDate.now()
    var period = Period.of(0, 0, 7)
    var modifiedDate = date.plus(period)


//navController: NavHostController
    Scaffold(
/* bottomBar = {
    BottomBar(navController = navController)
},
*/
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
                painter = painterResource(id = R.drawable.checkout3),
                contentDescription = "Checkout",
                modifier = Modifier
                    .fillMaxWidth()

            )
            Spacer(modifier = Modifier.height(40.dp))

            Text(
                text = "Payment Successful",
                color = Color(0xFF413232),
                fontSize = 28.sp,
                fontWeight = FontWeight.Medium,

                )
            Divider(color = Color(0xFF8D8D8D), thickness = 1.dp, modifier = Modifier.padding(10.dp))
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = "Order Details: ",
                color = Color(0xFF413232),
                fontSize = 28.sp,
                fontWeight = FontWeight.SemiBold,
            )
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = "Order Date: ${date}",
                color = Color(0xFF413232),
                fontSize = 18.sp,
                fontWeight = FontWeight.Normal,

                )

            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = "Order Number: ${currentOrder.id}",
                color = Color(0xFF413232),
                fontSize = 18.sp,
                fontWeight = FontWeight.Normal,

                )

            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = "Estimated Delivery Date: ${modifiedDate}",
                color = Color(0xFF413232),
                fontSize = 18.sp,
                fontWeight = FontWeight.Normal,

                )

            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = "Total: ${currentOrder.total}",
                color = Color(0xFF413232),
                fontSize = 18.sp,
                fontWeight = FontWeight.Normal,

                )

            Spacer(modifier = Modifier.height(30.dp))
            Divider(color = Color(0xFF8D8D8D), thickness = 1.dp, modifier = Modifier.padding(10.dp))

            Spacer(modifier = Modifier.height(30.dp))
            Text(
                text = "Thank You For Ordering from RAZZA ",
                color = Color(0xFF413232),
                fontSize = 23.sp,
                fontWeight = FontWeight.Normal,

                )
            val shoppingCartItems: List<Item> by razzaViewModel.cartItemsFB.collectAsState()

            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Button(
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF745D5D)),
                    shape = RoundedCornerShape(5.dp),
                    modifier = Modifier
                        .width(270.dp)
                        .height(55.dp),

                    onClick = {
                        navController.navigate(NavScreen.Home.route)
                    }) {
                    Text(text = "Back To Homepage ", color = Color.White, fontSize = 22.sp)
                    for (i in shoppingCartItems) {
                        if (i.userEmail == authViewModel.user?.email) {
                            razzaViewModel.deleteCartItem(i)

                        }
                    }
                }
            }
        }
    }
}
