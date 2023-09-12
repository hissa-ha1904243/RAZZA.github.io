package com.example.razzashoppingapp.view.screens.admin_screens


import android.annotation.SuppressLint
import androidx.activity.ComponentActivity
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import com.example.razzashoppingapp.TopBar
import com.example.razzashoppingapp.room.entity.Order
import com.example.razzashoppingapp.room.entity.OrderItem
import com.example.razzashoppingapp.room.entity.Product
import com.example.razzashoppingapp.viewmodel.AuthViewModel
import com.example.razzashoppingapp.viewmodel.RazzaViewModel

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun Dash2(navController: NavHostController,
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
        }

    ) {

//        val productsRepository = ProductRepository()
//        val orderedProducts = productsRepository.getProducts(LocalContext.current)
//        order2layout(orderedProducts, navController)
        val currentOrder: Order by razzaViewModel.order.collectAsState()
        val orderedItems: List<OrderItem> by razzaViewModel.orderItemsFB.collectAsState()
        val products: List<Product> by razzaViewModel.productsFB.collectAsState()
        val itemsOftheOrder = remember { mutableStateListOf<Product>()}
        // val noduplicates = remember { mutableStateListOf<Product>()}


        for(o in orderedItems)outer@{
            if(o.orderId==currentOrder.id){
                for(p in products){
                    if(p.id==o.pid){
                        itemsOftheOrder.add(p)
                    }

                }
            }

        }
        // itemsOftheOrder.distinct()
        dash2layout(itemsOftheOrder.distinct(), navController)


    }

}

@Composable
fun dash2layout(
    productModels: List<Product>, navController: NavHostController,
    razzaViewModel: RazzaViewModel = viewModel(LocalContext.current as ComponentActivity)
) {
    val currentOrder: Order by razzaViewModel.order.collectAsState()

    Column(
        modifier = Modifier
            .padding(vertical = 10.dp)
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
            text = "ORDER ${currentOrder.id}",
            color = Color.Black,
            fontSize = 27.sp,
            textAlign = TextAlign.Center
        )
        Divider(color = Color(0xFF8D8D8D), thickness = 1.dp, modifier = Modifier.padding(20.dp))
        Row(Modifier.padding(5.dp), horizontalArrangement = Arrangement.SpaceEvenly) {
            Text("Date Submitted: ${currentOrder.date}", fontSize = 17.sp)
            Spacer(modifier = Modifier.width(90.dp))
            Text("${currentOrder.status}", fontWeight = FontWeight.Bold)
        }

        //Spacer(modifier = Modifier.height(10.dp))
        Button(
            colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF745D5D)),
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier
                .width(230.dp)
                .height(80.dp).padding(20.dp),

            onClick = {navController.navigate(NavScreen.UpdateStatus.route) }) {
            Text(text = "Update Status", color = Color.White, fontSize = 20.sp)
        }

        //Divider(color = Color(0xFF8D8D8D), thickness = 1.dp, modifier = Modifier.padding(20.dp))

        Box(Modifier.border(BorderStroke(1.4.dp, color = Color(0xFFB5A9A9))))
        {
            dashproductsOrdered(productModels, navController)
        }

    }
}

@SuppressLint("SuspiciousIndentation")
@Composable
fun dashproductsOrdered(
    productModels: List<Product>, navController: NavHostController,
    razzaViewModel: RazzaViewModel = viewModel(LocalContext.current as ComponentActivity)) {

    val currentOrder: Order by razzaViewModel.order.collectAsState()

    if (productModels.isEmpty()) {
        Text("Loading products failed.")
    } else {
        LazyColumn {
            items(productModels) {
                DashOrderCard(it, navController)
            }
        }
    }


}



@Composable
fun DashOrderCard(productModel: Product, navController: NavHostController,
                  razzaViewModel: RazzaViewModel = viewModel(LocalContext.current as ComponentActivity)
) {
    var isExpanded = remember { mutableStateOf(false) }

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
            val surfaceColor by animateColorAsState(targetValue = if (isExpanded.value) MaterialTheme.colors.primary else MaterialTheme.colors.surface)
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
                        .clickable { isExpanded.value = !isExpanded.value }
                ) {
                    Text(
                        text = "Description: ${productModel.description}",
                        //modifier = Modifier.padding(4.dp),
                        maxLines = if (isExpanded.value) Int.MAX_VALUE else 1,
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
    Divider(Modifier.fillMaxWidth())
}

