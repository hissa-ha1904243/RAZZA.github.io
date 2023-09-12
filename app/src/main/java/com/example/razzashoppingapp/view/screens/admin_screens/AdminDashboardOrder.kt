package com.example.razzashoppingapp.view.screens.admin_screens

import android.annotation.SuppressLint
import androidx.activity.ComponentActivity
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.example.razzashoppingapp.BottomBar
import com.example.razzashoppingapp.view.navigation.NavScreen
import com.example.razzashoppingapp.Repositories.OrderRepository
import com.example.razzashoppingapp.Repositories.ProductRepository
import com.example.razzashoppingapp.TopBar
import com.example.razzashoppingapp.json_classes.ProductModel
import com.example.razzashoppingapp.viewmodel.AuthViewModel

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun DashboardOrderScreen(navController: NavHostController, id:Int
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
        val orderRepository = OrderRepository()
        val orders = orderRepository.getOrders(LocalContext.current)
        val productsRepository = ProductRepository()
        val products = productsRepository.getProducts(LocalContext.current)
        dashboardOrder(products, navController, id)
    }


}

@Composable
fun dashboardOrder(productModels: List<ProductModel>, navController: NavHostController, id : Int){

    Column(
        modifier = Modifier
            .padding(top = 20.dp, bottom = 5.dp)
            .fillMaxSize()
            .background(color = Color(0xFFF5F5F5)),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = "RAZZA",
            color = Color(0xFFB5A9A9),
            fontSize = 20.sp,
            textAlign = TextAlign.Center
        )


        Text(
            text = "ADMIN DASHBOARD",
            color = Color.Black,
            fontSize = 27.sp,
            textAlign = TextAlign.Center
        )
        Divider(color = Color(0xFF8D8D8D), thickness = 1.dp, modifier = Modifier.padding(20.dp))

        Column(modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xFFF5F5F5))) {
        Card(
            Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp, vertical = 10.dp)
                .height(60.dp)
                .clickable {
                    navController.navigate(
                        NavScreen.MyOrder2.route
                    )
                },backgroundColor = Color(0xFF745D5D),
            shape = RoundedCornerShape(corner = CornerSize(30.dp)), elevation = 10.dp){
            Row(horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically){
                Text(
                    text = "Order ${id}",
                    color = Color.White,
                    style = MaterialTheme.typography.subtitle2,
                    fontSize = 23.sp,
                    fontWeight = FontWeight.Bold
                )
            }

        }

            Text(
                text = "Total: 300 QR",
                color = Color.Black,
                modifier = Modifier
                    .background(Color.White)
                    .fillMaxWidth()
                    .height(50.dp)
                    .padding(start = 25.dp, top = 15.dp),
                style = MaterialTheme.typography.subtitle2,
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold,
            )
            Spacer(modifier = Modifier.height(30.dp))
            orderProducts(productModels = productModels, navController = navController)
            Spacer(modifier = Modifier.height(30.dp))

            Row(modifier = Modifier.padding(start = 25.dp)) {
                Text(text = "ORDER STATUS")
                Spacer(modifier = Modifier.width(30.dp))

                Button(colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFFF5F5F5)),
                shape = RoundedCornerShape(5.dp),
                modifier = Modifier.width(100.dp).height(30.dp),
                onClick = { navController.navigate(NavScreen.Home.route)}) {
                        Text(text = "Edit ", color = Color(0xFF8D8D8D), fontSize = 10.sp)
                    }
            }
        }
    }

}

@Composable
fun orderProducts(productModels: List<ProductModel>, navController: NavHostController) {

    if (productModels.isEmpty()) {
        Text("Loading products failed.")
    } else {
        LazyColumn(modifier = Modifier.height(330.dp)) {
            items(productModels) { item ->
                AdminProductCard(item, navController)
            }
        }

    }
}

@Composable
fun AdminProductCard(productModel: ProductModel, navController: NavHostController) {
    Card(
        backgroundColor = Color.White,
        modifier = Modifier
            .fillMaxWidth(),
    ) {

        Row(
            modifier = Modifier.padding(15.dp)
        ) {
            Image(
                painter = rememberAsyncImagePainter(productModel.image),
                contentDescription = "Product Image",

                modifier = Modifier
                    .size(80.dp)
                    .align(Alignment.CenterVertically)
            )
            Spacer(modifier = Modifier.padding(10.dp))
            var isExpanded by remember { mutableStateOf(false) }
            val surfaceColor by animateColorAsState(targetValue = if (isExpanded) MaterialTheme.colors.primary else MaterialTheme.colors.surface)
            Column {
                Text(
                    text = productModel.title,
                    color = Color(0xFF413232),
                    style = MaterialTheme.typography.subtitle2
                )

                Spacer(modifier = Modifier.height(4.dp))
                Surface(
                    shape = MaterialTheme.shapes.medium,
                    color = Color.White,
                    modifier = Modifier
                        .animateContentSize()
                        .padding(1.dp)
                        .clickable { isExpanded = !isExpanded }
                ) {
                    Text(
                        text = "Description: ${productModel.description}",
                        //modifier = Modifier.padding(4.dp),
                        maxLines = if (isExpanded) Int.MAX_VALUE else 1,
                        overflow = TextOverflow.Ellipsis,
                        style = MaterialTheme.typography.body2,
                        color = Color(0xFF8D8D8D)
                    )
                }
                Row(Modifier.padding(2.dp), horizontalArrangement = Arrangement.Start) {

                }
                Spacer(modifier = Modifier.height(40.dp))
                Text(
                    text = "${productModel.price} QAR",
                    style = MaterialTheme.typography.body2,
                    color = Color(0xFF413232)
                )

            }
        }
    }
}

