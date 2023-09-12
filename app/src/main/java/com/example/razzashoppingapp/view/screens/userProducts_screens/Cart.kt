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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.example.razzashoppingapp.view.navigation.NavScreen
import com.example.razzashoppingapp.BottomBar
import com.example.razzashoppingapp.TopBar
import com.example.razzashoppingapp.room.entity.*
import com.example.razzashoppingapp.view.screens.userInfo_screens.RoundButton
import com.example.razzashoppingapp.viewmodel.AuthViewModel
import com.example.razzashoppingapp.viewmodel.RazzaViewModel

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun CartScreen(navController: NavHostController,
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
//        var isExpanded = remember { mutableStateOf(false) }
//        val quantityState = remember { mutableStateOf(1) }
//        val productRepository = ProductRepository()
//        val cardproducts = productRepository.getProducts(LocalContext.current)
//        CartScreenContents(cardproducts, navController, isExpanded, quantityState)
        val cartItems: List<Item> by razzaViewModel.cartItemsFB.collectAsState()
        CartScreenContents(cartItems, navController)
    }
}

@SuppressLint("SuspiciousIndentation")
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CartScreenContents(cartItems: List<Item>, navController: NavHostController,
                       razzaViewModel: RazzaViewModel = viewModel(LocalContext.current as ComponentActivity)
) {
    var subTotal by remember { mutableStateOf(0.0) }
    val authViewModel =
        viewModel<AuthViewModel>(viewModelStoreOwner = LocalContext.current as ComponentActivity)
    var countItems: Int = 0
            Column() {
var subTotal = remember{ mutableStateOf(0.0)}
        Column() {
            if (cartItems.isEmpty()) {
                Text("Your Cart is Empty!.")
                subTotal.value=0.0
            } else {
                for(i in cartItems) {
                    if(i.userEmail == authViewModel.user?.email )
                        countItems += 1
                }
                LazyColumn(
                    modifier = Modifier
                        .padding(8.dp)
                        .weight(0.5f)
                ) {
                    stickyHeader {
                        Text(
                            text = "MY CART \n $countItems item",
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
                    items(cartItems) {
                        CartCard(it,navController,subTotal)
            }

        }
    }
            //
            Summery(navController = navController, cartItems =cartItems , subTotal = subTotal)

}
    }
}

@Composable
fun Summery(navController: NavHostController,cartItems: List<Item>,subTotal:MutableState<Double>,
            razzaViewModel: RazzaViewModel = viewModel(LocalContext.current as ComponentActivity)
) {

    val currentUser: User by razzaViewModel.user.collectAsState()

    Column {
        Text(
            "ORDER SUMMARY",
            color = Color(0xFF413232),
            fontSize = 25.sp,
            fontWeight = FontWeight.Bold
        )
        Row(Modifier.padding(2.dp)) {
            Text("Subtotal", color = Color(0xFF413232), fontSize = 20.sp)
            Spacer(Modifier.width(60.dp))
            Row() {
                Text("${subTotal.value}QAR", color = Color(0xFF413232), fontSize = 20.sp)
            }
        }
        Row(Modifier.padding(2.dp)) {
            Text("Shipping Fee", color = Color(0xFF413232), fontSize = 20.sp)
            Spacer(Modifier.width(40.dp))
            Row() {
                Text("30QAR", color = Color(0xFF413232), fontSize = 20.sp)
            }
        }
        Divider(color = Color(0xFF8D8D8D), thickness = 1.dp, modifier = Modifier.padding(5.dp))
        Row(Modifier.padding(2.dp)) {
            Text(text = "Total", color = Color(0xFF413232), fontSize = 20.sp)
            Spacer(Modifier.width(90.dp))
            Row() {
                Text("${subTotal.value+30}QAR", color = Color(0xFF413232), fontSize = 20.sp)
            }
        }
        Box(Modifier.fillMaxWidth(), Alignment.Center) {
            Button(
                colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF745D5D)),
                shape = RoundedCornerShape(5.dp),
                modifier = Modifier
                    .width(300.dp)
                    .height(55.dp),
                onClick = {
                    val cart = ShoppingCart(userId = currentUser.id, total = subTotal.value+30)
                    //razzaViewModel.addCart(cart)
                    razzaViewModel.setCurrentCart(cart)
                    navController.navigate(NavScreen.Checkout1.route) }) {
                Text(text = "Continue To Checkout ", color = Color.White, fontSize = 20.sp)

            }
        }
        Spacer(modifier = Modifier.height(70.dp))
    }
}

@Composable
fun CartCard(cartItem: Item, navController: NavHostController,subTotal:MutableState<Double>,
             razzaViewModel: RazzaViewModel = viewModel(LocalContext.current as ComponentActivity)
) {
    val products: List<Product> by razzaViewModel.productsFB.collectAsState()
    val currentUser: User by razzaViewModel.user.collectAsState()
    val authViewModel =
        viewModel<AuthViewModel>(viewModelStoreOwner = LocalContext.current as ComponentActivity)

    for(p in products){
        if(p.id==cartItem.productId && authViewModel.user?.email==cartItem.userEmail){

            var isExpanded = remember { mutableStateOf(false) }
    Card(
        backgroundColor = Color.White,
        modifier = Modifier
            .fillMaxWidth(),
    ) {

        Row(modifier = Modifier.padding(15.dp)
        ) {
            Image(
                painter = rememberAsyncImagePainter(p.image), contentDescription = "Product Image",

                modifier = Modifier
                    .size(80.dp)
                    .align(Alignment.CenterVertically)
            )
            Spacer(modifier = Modifier.padding(10.dp))
            var isExpanded by remember { mutableStateOf(false) }
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
                        .clickable { isExpanded = !isExpanded }
                ) {
                    Text(
                        text = "Description: ${p.description}",
                        //modifier = Modifier.padding(4.dp),
                        maxLines = if (isExpanded) Int.MAX_VALUE else 1,
                        overflow = TextOverflow.Ellipsis,
                        style = MaterialTheme.typography.body2,
                        color = Color(0xFF8D8D8D)
                    )
                }
                //The Quantity
                val quantityState = remember { mutableStateOf(cartItem.quantity) }
                Row(Modifier.padding(2.dp), horizontalArrangement = Arrangement.Start) {
                    Text(
                        text = "Quantity",
                        Modifier
                            .align(Alignment.CenterVertically)
                    )
                    Spacer(modifier = Modifier.width(70.dp))
                    Row(
                        Modifier.padding(horizontal = 3.dp),//horizontal padding is left and right
                        horizontalArrangement = Arrangement.End
                    ) {
                        RoundButton(imageVector = Icons.Default.Delete, onClick = {
                            if (quantityState.value > 1)
                                quantityState.value = quantityState.value - 1
                            else
                                quantityState.value = 1
                        })
                        Text(
                            "${quantityState.value}",
                            Modifier
                                .align(Alignment.CenterVertically)
                                .padding(start = 9.dp, end = 9.dp)
                        )
                        RoundButton(
                            imageVector = Icons.Default.Add,
                            onClick = { quantityState.value = quantityState.value + 1 })

                    }
                }
                Spacer(modifier = Modifier.height(40.dp))
                subTotal.value=+(p.price*quantityState.value)
                Text(
                    text = "${p.price*quantityState.value} QAR",
                    style = MaterialTheme.typography.body2,
                    color = Color(0xFF413232)
                )

            }
        }
    }//Card end

    //Spacer(modifier = Modifier.height(8.dp))
    Divider(Modifier.fillMaxWidth())
    Row {
        Spacer(modifier = Modifier.padding(10.dp))
        TextButton(
            colors = ButtonDefaults.buttonColors(backgroundColor = Color.White),
            //shape = RoundedCornerShape(5.dp),
            modifier = Modifier
                .width(150.dp)
                .height(40.dp), onClick = {
                razzaViewModel.deleteCartItem(cartItem)
                subTotal.value = subTotal.value-cartItem.subtotal

    } ){

            Text(text = "Remove Item",
                color = Color(0xFF8D8D8D) ,
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
        val wishListItems: List<WishListItem> by razzaViewModel.wishlistItemsFB.collectAsState()

        TextButton(
            colors = ButtonDefaults.buttonColors(backgroundColor = Color.White),
            //shape = RoundedCornerShape(5.dp),
            modifier = Modifier
                .width(150.dp)
                .height(40.dp),
            onClick = {
                var flag: Boolean = true
                for (i in wishListItems) {
                    if (i.pid == p.id && i.userEmail == authViewModel.user?.email) {
                        flag = false
                    }
                }
                if (flag) {
                    var currentWishListItem =
                        WishListItem(userEmail = authViewModel.user?.email.toString(), pid = p.id)
                    razzaViewModel.addWishListItem(currentWishListItem)
                }}) {
            Text(text = "Move to Wishlist ",
                color = Color(0xFF8D8D8D) ,
                fontSize = 12.sp,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.body2,
            )

        }

    }

Divider(Modifier.fillMaxWidth())
}}}

@Composable
fun RoundButton(
    modifier: Modifier = Modifier,
    imageVector: ImageVector,
    onClick: () -> Unit,
    elevation: Dp = 4.dp
) {
    Card(
        modifier
            .padding(2.dp)
            .clickable(onClick = onClick),
        shape = CircleShape,
        backgroundColor = MaterialTheme.colors.background,
        elevation = elevation
    ) {
        Icon(imageVector = imageVector, contentDescription = "+ or - icon", tint = Color.Black)
    }
}


/*@Preview(showBackground = true)
@Composable
fun CartPreview() {
    RazzaTheme {
        val navController = rememberNavController()
        NavGraph(navController = navController)
        val cardproducts = ProductRepository.getProducts(LocalContext.current)
        var _cardproducts = rememberSaveable { mutableListOf(cardproducts) }
        CartScreenContents(cardproducts, navController)
    }
}*/


