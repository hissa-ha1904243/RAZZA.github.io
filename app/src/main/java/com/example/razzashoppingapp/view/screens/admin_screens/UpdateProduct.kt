package com.example.razzashoppingapp.view.screens.admin_screens

import android.annotation.SuppressLint
import androidx.activity.ComponentActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.razzashoppingapp.BottomBar
import com.example.razzashoppingapp.TopBar
import com.example.razzashoppingapp.room.entity.Product
import com.example.razzashoppingapp.view.navigation.NavScreen
import com.example.razzashoppingapp.viewmodel.AuthViewModel
import com.example.razzashoppingapp.viewmodel.RazzaViewModel


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun UpdateProductScreen(navController: NavHostController,
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
        Column(
            modifier = Modifier
                .padding(vertical = 22.dp)
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
                text = "UPDATE PRODUCT",
                color = Color.Black,
                fontSize = 27.sp,
                textAlign = TextAlign.Center
            )
            Divider(color = Color(0xFF8D8D8D), thickness = 1.dp, modifier = Modifier.padding(20.dp))

            updateFields(navController)


        }
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun updateFields(navController: NavHostController,
              razzaViewModel: RazzaViewModel = viewModel(LocalContext.current as ComponentActivity))
{
    val currentProduct: Product by razzaViewModel.product.collectAsState()


    val keyboardController = LocalSoftwareKeyboardController.current

    var currentTitle = currentProduct.title
    var currentDesc = currentProduct.description
    var currentPrice = currentProduct.price
    var currentCategory = currentProduct.category
    var currentImage = currentProduct.image

//    var productName by remember { mutableStateOf(currentProduct.title) }
//    var description by remember { mutableStateOf(currentProduct.description) }
//    var price by remember { mutableStateOf(currentProduct.price) }
//    var category by remember { mutableStateOf(currentProduct.category) }
//    var image by remember { mutableStateOf(currentProduct.image)}



    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(30.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    )
    {

        Spacer(modifier = Modifier.height(15.dp))

        OutlinedTextField(
            value = currentTitle,
            onValueChange = { razzaViewModel.updateTitle(it) },
            label = { Text("Product Name", color = Color(0xFFB5A9A9)) },
            singleLine = true,
            modifier = Modifier
                .background(color = Color(0xFFF4F3EF))
                .border(15.dp, color = Color(0xFFF4F3EF), RectangleShape),
            shape = RoundedCornerShape(corner = CornerSize(15.dp)),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            keyboardActions = KeyboardActions(
                onDone = {
                    keyboardController?.hide()
                })
        )

        Spacer(modifier = Modifier.height(30.dp))
        OutlinedTextField(
            value = currentDesc,
            onValueChange = { razzaViewModel.updateDesc(it) },
            label = { Text("Product Description", color = Color(0xFFB5A9A9)) },
            singleLine = true,
            modifier = Modifier
                .background(color = Color(0xFFF4F3EF))
                .border(15.dp, color = Color(0xFFF4F3EF), RectangleShape),
            shape = RoundedCornerShape(corner = CornerSize(15.dp)),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            keyboardActions = KeyboardActions(
                onDone = {
                    keyboardController?.hide()
                })
        )
        Spacer(modifier = Modifier.height(30.dp))
        OutlinedTextField(
            value = currentPrice.toString(),
            onValueChange = { razzaViewModel.updatePrice(it.toDouble()) },
            label = { Text("Product Price", color = Color(0xFFB5A9A9)) },
            singleLine = true,
            modifier = Modifier
                .background(color = Color(0xFFF4F3EF))
                .border(15.dp, color = Color(0xFFF4F3EF), RectangleShape),
            shape = RoundedCornerShape(corner = CornerSize(15.dp)),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            keyboardActions = KeyboardActions(
                onDone = {
                    keyboardController?.hide()
                })
        )
        Spacer(modifier = Modifier.height(30.dp))
//        OutlinedTextField(
//            value = currentCategory.toString(),
//            onValueChange = { razzaViewModel.updateCategory(it.toInt()) },
//            label = { Text("Category", color = Color(0xFFB5A9A9)) },
//            singleLine = true,
//            modifier = Modifier
//                .background(color = Color(0xFFF4F3EF))
//                .border(15.dp, color = Color(0xFFF4F3EF), RectangleShape),
//            shape = RoundedCornerShape(corner = CornerSize(15.dp)),
//            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
//            keyboardActions = KeyboardActions(
//                onDone = {
//                    keyboardController?.hide()
//                })
//        )
//        Spacer(modifier = Modifier.height(30.dp))
        OutlinedTextField(
            value = currentImage,
            onValueChange = { razzaViewModel.updateImage(it) },
            label = { Text("Image", color = Color(0xFFB5A9A9)) },
            singleLine = true,
            modifier = Modifier
                .background(color = Color(0xFFF4F3EF))
                .border(15.dp, color = Color(0xFFF4F3EF), RectangleShape),
            shape = RoundedCornerShape(corner = CornerSize(15.dp)),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            keyboardActions = KeyboardActions(
                onDone = {
                    keyboardController?.hide()
                })
        )
        Spacer(modifier = Modifier.height(30.dp))

        Button(
            colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF745D5D)),
            shape = RoundedCornerShape(5.dp),
            modifier = Modifier.width(180.dp).height(55.dp),
            onClick = {
                razzaViewModel.updateProduct(currentProduct)
                navController.navigate(NavScreen.AllProducts.route)
            }) {
            Text(text = "Update Product", color = Color.White , fontSize = 18.sp)

        }

    }




}


 
    

