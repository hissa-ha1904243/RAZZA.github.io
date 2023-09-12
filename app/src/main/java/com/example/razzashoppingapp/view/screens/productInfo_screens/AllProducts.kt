package com.example.razzashoppingapp.view.screens.productInfo_screens

import android.annotation.SuppressLint
import androidx.activity.ComponentActivity
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.razzashoppingapp.BottomBar
import com.example.razzashoppingapp.TopBar
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.razzashoppingapp.room.entity.*
import com.example.razzashoppingapp.view.navigation.NavScreen
import com.example.razzashoppingapp.viewmodel.AuthViewModel
import com.example.razzashoppingapp.viewmodel.RazzaViewModel
import kotlinx.coroutines.flow.asStateFlow

@OptIn(ExperimentalComposeUiApi::class)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun AllProductsScreen(navController: NavHostController,
                      razzaViewModel: RazzaViewModel = viewModel(LocalContext.current as ComponentActivity)) {
    val authViewModel =
        viewModel<AuthViewModel>(viewModelStoreOwner = LocalContext.current as ComponentActivity)

    //Searchbar
    var searchname = remember { mutableStateOf("") }
    val keyboardController = LocalSoftwareKeyboardController.current
    val api_products by razzaViewModel.productsFB.collectAsState()
    Scaffold(
        bottomBar = {
            BottomBar(navController = navController)
        },

        topBar = {
            TopBar(navController = navController)
        },

        )
    {
        val currentCategory: Category by razzaViewModel.category.collectAsState()
        val searched = remember { mutableStateListOf<Product>()}

        Column(modifier = Modifier.fillMaxSize()) {
            Box(
                modifier = Modifier
                    .background(Color.White)
                    .fillMaxWidth()
                    .weight(1.5f)
            ) {
                Row(modifier = Modifier.fillMaxSize()) {

                    Column(verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally) {
                        Searchbar(searched,searchname)
                        Row(modifier = Modifier,verticalAlignment = Alignment.CenterVertically)
                        {
                            Spacer(modifier = Modifier.width(10.dp))
                            Filter()
                        }
                    }

                    Surface(
                        modifier = Modifier
                            .fillMaxHeight()
                            .weight(1f)
                    ) {}
                }

            }
            Box(
                modifier = Modifier
                    .background(Color.Transparent)
                    .fillMaxWidth()
                    .weight(8f)
            ) {


                ProductsScreenContents(searched,currentCategory,navController,api_products)

            }
        }
    }
}

@SuppressLint("SuspiciousIndentation")
@OptIn(ExperimentalComposeUiApi::class)
@Composable

fun Searchbar(
    searched: SnapshotStateList<Product>,
    searchname: MutableState<String>,
    razzaViewModel: RazzaViewModel = viewModel(LocalContext.current as ComponentActivity)
){
    val keyboardController = LocalSoftwareKeyboardController.current
    val products: List<Product> by razzaViewModel.productsFB.collectAsState()

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp),
        elevation = AppBarDefaults.TopAppBarElevation,
        color = Color.White,
    ){
        TextField(
            modifier = Modifier
                .fillMaxWidth(),
            value = searchname.value,
            onValueChange = { searchname.value = it },

            label = {
                Text(
                    modifier = Modifier.alpha(ContentAlpha.medium),
                    text = "Search",
                    color = Color(0xFF745D5D)
                )
            },
            textStyle = TextStyle(
                fontSize = MaterialTheme.typography.subtitle1.fontSize
            ),

            singleLine = true,
            leadingIcon = {
                IconButton(
                    modifier = Modifier.alpha(ContentAlpha.medium),
                    onClick = { })
                {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Search Icon",
                        tint = Color(0xFF745D5D)
                    )
                }
            },

            trailingIcon = {
                IconButton(
                    modifier = Modifier
                        .alpha(ContentAlpha.medium),
                    onClick = { if (searchname.value.isNotEmpty()){
                        searchname.value = ""
                                searched.clear()

                    } }

                )
                {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = "Close",
                        tint = Color(0xFF745D5D)

                    )
                }
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Search
            ),

            keyboardActions = KeyboardActions(
                onSearch = {
                    keyboardController?.hide()

                    for (product in products) {
                        if (product.title.lowercase().contains(searchname.value.lowercase())) {
                            searched.add(product)
                        }
                    }}),



            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.White,
                cursorColor = Color.White.copy(alpha = ContentAlpha.medium)
            ),
        )


    }

}




@Composable
fun ProductsScreenContents(
    searched: SnapshotStateList<Product>,
    currentCategory:Category,
    navController: NavController,
    products: List<Product>, razzaViewModel: RazzaViewModel = viewModel(LocalContext.current as ComponentActivity)) {

    if (searched.isNotEmpty()) {
        LazyColumn {
            items(searched)
            {
               // for (product in searched)
                    if (it.category == currentCategory.id) {
                        ProductCard(it, navController)
                    }
            }
        }
    }

    else {
        LazyColumn {
            items(products)
            {
               // for (product in products)
                    if (it.category == currentCategory.id) {
                        ProductCard(it, navController)
                    }
            }
        }

    }

}

@Composable
fun Filter(
    razzaViewModel: RazzaViewModel = viewModel(LocalContext.current as ComponentActivity)){
    Spacer(modifier = Modifier.width(5.dp))
    Text(text = "Filter Cost:", color = Color(0xFF745D5D), fontSize = 10.sp)
    Spacer(modifier = Modifier.width(5.dp))

    Button(
            onClick = {
                razzaViewModel.orderProductDes()

            },
        Modifier
            .height(38.dp)
            .width(107.dp)
            .padding(3.dp),
        border = BorderStroke(1.dp, Color(0xFF745D5D)),
            colors = ButtonDefaults.outlinedButtonColors(
                contentColor = Color(0xFF745D5D),
                backgroundColor = Color.White),

        shape = RoundedCornerShape(7.dp)
        ) {
            Text(text = "From Higher", color = Color(0xFF745D5D), fontSize = 10.sp)

        }
        Button(
            onClick = {
               razzaViewModel.orderProductAsc()
            },
            Modifier
                .height(38.dp)
                .width(105.dp)
                .padding(3.dp),
            border = BorderStroke(1.dp, Color(0xFF745D5D)),
            colors = ButtonDefaults.outlinedButtonColors(
                contentColor = Color(0xFF745D5D),
                backgroundColor = Color.White),
            shape = RoundedCornerShape(7.dp)
        ) {
            Text(text = "From Lower", color = Color(0xFF745D5D), fontSize = 10.sp)
        }

}
@Composable
fun ProductCard(product: Product, navController : NavController,
                razzaViewModel: RazzaViewModel = viewModel(LocalContext.current as ComponentActivity)
) {
    var isExpanded = remember { mutableStateOf(false) }

    Card(
        backgroundColor = Color.White,
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                razzaViewModel.setCurrentProduct(product)
                navController.navigate(route = NavScreen.SingleProduct.route)
            }
    ) {

        Row(
            modifier = Modifier.padding(15.dp)
        ) {
            Image(
                painter = rememberAsyncImagePainter(product.image),
                contentDescription = "Product Image",

                modifier = Modifier
                    .size(80.dp)
                    .align(Alignment.CenterVertically),

                )
            Spacer(modifier = Modifier.padding(20.dp))
            //val surfaceColor by animateColorAsState(targetValue = if (isExpanded) MaterialTheme.colors.primary else MaterialTheme.colors.surface)
            Column {
                TextButton(
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color.White),
                    onClick = { razzaViewModel.setCurrentProduct(product)
                        navController.navigate(route = NavScreen.SingleProduct.route) }
                ) {
                    Text(
                        text = product.title,
                        color = Color(0xFF413232),
                        style = MaterialTheme.typography.subtitle2
                    )
                }

                Spacer(modifier = Modifier.height(4.dp))
                Spacer(modifier = Modifier.height(60.dp))
                Text(
                    text = "${product.price} QAR",
                    style = MaterialTheme.typography.body2,
                    color = Color(0xFF413232)
                )
            }
        }
    }//card end

    Spacer(modifier = Modifier.height(8.dp))
    val shoppingCartItems: List<Item> by razzaViewModel.cartItemsFB.collectAsState()
    val wishlistItems: List<WishListItem> by razzaViewModel.wishlistItemsFB.collectAsState()

    Row {
        val currentUser: User by razzaViewModel.user.collectAsState()
        val authViewModel =
            viewModel<AuthViewModel>(viewModelStoreOwner = LocalContext.current as ComponentActivity)

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
                    if (i.productId == product.id && i.userEmail == authViewModel.user?.email ) {
                        flag = false
                    }
                }
                if (flag) {
                    var currentCartItem =
                        Item(userEmail = authViewModel.user?.email.toString(), productId = product.id, subtotal = product.price, quantity = 1)
                    razzaViewModel.addCartItem(currentCartItem)
                }
            }) {
            Text(
                text = "Add to Cart ",
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
            onClick = {
                var flag: Boolean = true
                for (i in wishlistItems) {
                    if (i.pid == product.id && i.userEmail == authViewModel.user?.email) {
                        flag = false
                    }
                }
                if (flag) {
                    var currentWishListItem =
                        WishListItem(userEmail = authViewModel.user?.email.toString(), pid = product.id)
                    razzaViewModel.addWishListItem(currentWishListItem)
                }

            }) {
            Text(
                text = "Add to Wishlist ",
                color = Color(0xFF8D8D8D),
                fontSize = 12.sp,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.body2,
            )

        }

    }


}


