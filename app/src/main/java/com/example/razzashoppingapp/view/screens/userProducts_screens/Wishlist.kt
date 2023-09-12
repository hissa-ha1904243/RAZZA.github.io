package com.example.razzashoppingapp.view.screens.userProducts_screens

import android.annotation.SuppressLint
import androidx.activity.ComponentActivity
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.example.razzashoppingapp.BottomBar
import com.example.razzashoppingapp.TopBar
import com.example.razzashoppingapp.room.entity.Item
import com.example.razzashoppingapp.room.entity.Product
import com.example.razzashoppingapp.room.entity.User
import com.example.razzashoppingapp.room.entity.WishListItem
import com.example.razzashoppingapp.viewmodel.AuthViewModel
import com.example.razzashoppingapp.viewmodel.RazzaViewModel

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun WishListScreen(navController: NavHostController,
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
        //States:


//        val productRepository = ProductRepository()
//        val getAllData = productRepository.getProducts(LocalContext.current)

//        LazyColumn(){
//
//            items(items = getAllData){ product ->
//                ProductCard(product = product, navController = navController)
//            }
//        }
        //val products = ProductRepository.getProducts(LocalContext.current)
        //var _products = rememberSaveable { mutableListOf(products) }


        val wishListItems: List<WishListItem> by razzaViewModel.wishlistItemsFB.collectAsState()
        ProductScreenContents(wishListItems, navController)
//        for(item in wishListItems ){
//        var p= razzaViewModel.getProduct(item.pid)
//
//
//                WishlistCard(it, navController)
//
//        }

    }


}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ProductScreenContents(wishListItems: List<WishListItem>, navController: NavHostController) {
    val authViewModel =
        viewModel<AuthViewModel>(viewModelStoreOwner = LocalContext.current as ComponentActivity)
    var countItems: Int = 0
    if (wishListItems.isEmpty()) {
        Text("Your Wish List is Empty!")
    } else {
        for(i in wishListItems) {
            if(i.userEmail == authViewModel.user?.email )
                countItems += 1
        }
        LazyColumn( modifier = Modifier
            .padding(8.dp)) {
            stickyHeader {
                Text(
                    text = "$countItems items",
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
            items(wishListItems) {
                WishlistCard(it, navController)
            }

        }
    }
}

@SuppressLint("SuspiciousIndentation")
@Composable
fun WishlistCard(wishListItem: WishListItem, navController: NavHostController,
                 razzaViewModel: RazzaViewModel = viewModel(LocalContext.current as ComponentActivity)
) {
    val products: List<Product> by razzaViewModel.productsFB.collectAsState()
    val currentUser: User by razzaViewModel.user.collectAsState()
    val authViewModel =
        viewModel<AuthViewModel>(viewModelStoreOwner = LocalContext.current as ComponentActivity)

    for(p in products) {
        if(p.id==wishListItem.pid && authViewModel.user?.email==wishListItem.userEmail){

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
                            painter = rememberAsyncImagePainter(p.image),
                            contentDescription = "Product Image",

                            modifier = Modifier
                                .size(80.dp)
                                .align(Alignment.CenterVertically)
                        )
                        Spacer(modifier = Modifier.padding(20.dp))
                        //val surfaceColor by animateColorAsState(targetValue = if (isExpanded) MaterialTheme.colors.primary else MaterialTheme.colors.surface)
                        Column {
                            Text(
                                text = p.title,
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
                                    text = "Description: ${p.description}",
                                    //modifier = Modifier.padding(4.dp),
                                    maxLines = if (isExpanded.value) Int.MAX_VALUE else 1,
                                    overflow = TextOverflow.Ellipsis,
                                    style = MaterialTheme.typography.body2,
                                    color = Color(0xFF8D8D8D)
                                )
                            }
                            Spacer(modifier = Modifier.height(60.dp))
                            Text(
                                text = "${p.price} QAR",
                                style = MaterialTheme.typography.body2,
                                color = Color(0xFF413232)
                            )
                        }
                    }
                }//card end

                Spacer(modifier = Modifier.height(8.dp))
        val shoppingCartItems: List<Item> by razzaViewModel.cartItemsFB.collectAsState()
        val currentUser: User by razzaViewModel.user.collectAsState()
                Row {
                    Spacer(modifier = Modifier.padding(10.dp))
                    TextButton(
                        colors = ButtonDefaults.buttonColors(backgroundColor = Color.White),
                        //shape = RoundedCornerShape(5.dp),
                        modifier = Modifier
                            .width(150.dp)
                            .height(40.dp),
                        onClick = {

                            var flag: Boolean = true
                            for (i in shoppingCartItems) {
                                if (i.productId == p.id && i.userEmail == authViewModel.user?.email) {
                                    flag = false
                                }
                            }
                            if (flag) {
                                var currentCartItem =
                                    Item(userEmail = authViewModel.user?.email.toString(), productId = p.id, subtotal = p.price, quantity = 1)
                                razzaViewModel.addCartItem(currentCartItem)
                            } }) {
                        Text(
                            text = "Add to Cart",
                            color = Color(0xFF8D8D8D),
                            fontSize = 12.sp,
                            textAlign = TextAlign.Center,
                            style = MaterialTheme.typography.body2,
                        )

                    }
                    Spacer(modifier = Modifier.padding(10.dp))
                    Text(
                        text = ".",
                        color = Color(0xFF8D8D8D),
                        style = MaterialTheme.typography.body2,
                    )
                    Spacer(modifier = Modifier.padding(20.dp))
                    TextButton(
                        colors = ButtonDefaults.buttonColors(backgroundColor = Color.White),
                        //shape = RoundedCornerShape(5.dp),
                        modifier = Modifier
                            .width(150.dp)
                            .height(40.dp),
                        onClick = { razzaViewModel.deleteWishListItem(wishListItem) }) {
                        Text(
                            text = "Remove from Wishlist",
                            color = Color(0xFF8D8D8D),
                            fontSize = 12.sp,
                            textAlign = TextAlign.Center,
                            style = MaterialTheme.typography.body2,
                        )

                    }

                }


            }
        }
    }
