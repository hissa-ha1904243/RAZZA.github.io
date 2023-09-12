package com.example.razzashoppingapp.view.screens.productInfo_screens

import android.annotation.SuppressLint
import androidx.activity.ComponentActivity
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.example.razzashoppingapp.BottomBar
import com.example.razzashoppingapp.R
import com.example.razzashoppingapp.TopBar
import com.example.razzashoppingapp.room.entity.Item
import com.example.razzashoppingapp.room.entity.Product
import com.example.razzashoppingapp.room.entity.User
import com.example.razzashoppingapp.room.entity.WishListItem
import com.example.razzashoppingapp.view.navigation.NavScreen
import com.example.razzashoppingapp.viewmodel.AuthViewModel
import com.example.razzashoppingapp.viewmodel.RazzaViewModel


@SuppressLint("UnusedMaterialScaffoldPaddingParameter", "SuspiciousIndentation")
@Composable
fun SingleProductScreen(navController: NavHostController,
                        razzaViewModel: RazzaViewModel = viewModel(LocalContext.current as ComponentActivity),
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
        val wishListItems: List<WishListItem> by razzaViewModel.wishlistItemsFB.collectAsState()
        val shoppingCartItems: List<Item> by razzaViewModel.cartItemsFB.collectAsState()

        val authViewModel =
            viewModel<AuthViewModel>(viewModelStoreOwner = LocalContext.current as ComponentActivity)

        var isExpanded = remember { mutableStateOf(false) }
        val currentProduct: Product by razzaViewModel.product.collectAsState()
        val quantityState = remember { mutableStateOf(1) }
        Column(modifier = Modifier.fillMaxSize()){
            Box(modifier = Modifier
                .weight(3f)
                .fillMaxWidth()
                .background(Color.White)
            ){
                val currentUser: User by razzaViewModel.user.collectAsState()

                Column {
                    Row() {

                        Box(modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 10.dp)
                            .weight(0.5f),
                            contentAlignment = Alignment.CenterEnd){
                            Image(
                                painter = painterResource(id = R.drawable.heart),
                                contentDescription = "Wishlist Button",
                                modifier = Modifier
                                    .size(35.dp)
                                    .clickable {
                                        var flag: Boolean = true
                                        for (i in wishListItems) {
                                            if (i.pid == currentProduct.id && i.userEmail == authViewModel.user?.email ) {
                                                flag = false
                                            }
                                        }
                                        if (flag) {
                                            var currentWishListItem =
                                                WishListItem(userEmail = authViewModel.user?.email.toString(), pid = currentProduct.id)
                                            razzaViewModel.addWishListItem(currentWishListItem)
                                        }
                                    }
                            )
                        }
                    }

                    Box(modifier = Modifier
                        .padding(horizontal = 20.dp, vertical = 20.dp)
                        .weight(5f)
                        .fillMaxWidth()
                        .background(Color.White)
                    ){
                        Image(painter = rememberAsyncImagePainter(currentProduct.image),
                            contentDescription ="Product Image",
                            modifier = Modifier
                                .size(200.dp)
                                .align(alignment = Alignment.Center))
                    }
                    Box(modifier = Modifier
                        .weight(0.5f)
                        .fillMaxWidth()
                        .background(Color.White),
                        contentAlignment = Alignment.Center
                    ){

                        Row() {

                            Image(
                                painter = painterResource(id = R.drawable.cart2),
                                contentDescription = "Cart",
                                modifier = Modifier
                                    .size(35.dp)
                                    .clickable {
                                        var flag: Boolean = true
                                        for (i in shoppingCartItems) {
                                            if (i.productId == currentProduct.id && i.userEmail == authViewModel.user?.email) {
                                                flag = false
                                            }
                                        }
                                        if (flag) {
                                            var currentCartItem =
                                                Item(userEmail = authViewModel.user?.email.toString(), productId = currentProduct.id, subtotal = currentProduct.price, quantity = 1)
                                            razzaViewModel.addCartItem(currentCartItem)
                                        }
                                    }
                            )
                            Image(
                                painter = painterResource(id = R.drawable.add),
                                contentDescription = "Add",
                                modifier = Modifier
                                    .size(35.dp)
                                    .clickable {
                                        quantityState.value = quantityState.value + 1
                                    }
                            )
                            Text(text = "${quantityState.value}",
                                modifier = Modifier.size(40.dp),
                                fontSize = 24.sp,
                                textAlign = TextAlign.Center)
                            Image(
                                painter = painterResource(id = R.drawable.minus),
                                contentDescription = "Minus",
                                modifier = Modifier
                                    .size(35.dp)
                                    .clickable {
                                        if (quantityState.value > 1)
                                            quantityState.value = quantityState.value - 1
                                        else
                                            quantityState.value = 1
                                    }
                            )
                        }
                    }
                }
            }

            Box(modifier = Modifier
                .weight(1f)
                .fillMaxSize()
                .background(Color.White)
                .verticalScroll(rememberScrollState())

            ){
                Row(modifier = Modifier.fillMaxSize()
                ){
                    Box(modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight()
                        .background(Color.White),
                    ){
                        Column(modifier = Modifier.fillMaxSize()
                        ) {
                            Text(text = currentProduct.title,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(15.dp),
                                fontSize = 20.sp,
                                textAlign = TextAlign.Left,
                                )


                            Text(text = (currentProduct.price*quantityState.value).toString(),
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(10.dp),
                                fontSize = 24.sp,
                                textAlign = TextAlign.Left,
                                fontWeight = FontWeight.Bold)
                        }
                    }

                }
            }
            Box(modifier = Modifier
                .weight(1.125f)
                .fillMaxWidth()
                .background(Color.White)
                .height(80.dp)
                .verticalScroll(rememberScrollState())

            ){
                Column(modifier = Modifier.fillMaxSize()) {
                    Divider(
                        color = Color(0xFFCACACA),
                        thickness = 1.dp,
                        modifier = Modifier.padding(20.dp)
                    )
                    Surface(
                        shape = MaterialTheme.shapes.medium,
                        color = Color.White,
                        modifier = Modifier
                            .animateContentSize()
                            .padding(1.dp)
                            .clickable { isExpanded.value = !isExpanded.value }
                    ) {
                        Text(
                            text = "DESCRIPTION \n\n${currentProduct.description}",
                            maxLines = if (isExpanded.value) Int.MAX_VALUE else 1,
                            overflow = TextOverflow.Ellipsis,
                            style = MaterialTheme.typography.body2,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(10.dp),
                            fontSize = 15.sp,
                            textAlign = TextAlign.Left,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                    Divider(
                        color = Color(0xFFCACACA),
                        thickness = 1.dp,
                        modifier = Modifier.padding(20.dp)
                    )
                    TextButton(
                        colors = ButtonDefaults.buttonColors(backgroundColor = Color.White),
                        onClick = {
                            razzaViewModel.setCurrentProduct(currentProduct)
                            navController.navigate(NavScreen.Reviews.route)
                        }
                    ) {
                        Text(
                            text = "REVIEWS",
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(10.dp),
                            fontSize = 15.sp,
                            textAlign = TextAlign.Left,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                    Spacer(modifier = Modifier.height(60.dp))

                }
            }
        }
    }
}