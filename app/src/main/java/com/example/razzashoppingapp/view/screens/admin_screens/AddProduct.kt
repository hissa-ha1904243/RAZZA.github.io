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
import com.example.razzashoppingapp.view.navigation.NavScreen
import com.example.razzashoppingapp.TopBar
import com.example.razzashoppingapp.room.entity.Product
import com.example.razzashoppingapp.viewmodel.AuthViewModel
import com.example.razzashoppingapp.viewmodel.RazzaViewModel


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun AddProductScreen(navController: NavHostController
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
                text = "ADD PRODUCT",
                color = Color.Black,
                fontSize = 27.sp,
                textAlign = TextAlign.Center
            )
            Divider(color = Color(0xFF8D8D8D), thickness = 1.dp, modifier = Modifier.padding(20.dp))

            addFields(navController)


        }
    }
}

    @OptIn(ExperimentalComposeUiApi::class)
    @Composable
    fun addFields(navController: NavHostController,
                  razzaViewModel: RazzaViewModel = viewModel(LocalContext.current as ComponentActivity))
    {

        val keyboardController = LocalSoftwareKeyboardController.current

        var productName by remember { mutableStateOf("") }
        var description by remember { mutableStateOf("") }
        var price by remember { mutableStateOf(0.0) }
        var category by remember { mutableStateOf(0) }
        var image by remember { mutableStateOf("")}



        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        )
        {

                //Spacer(modifier = Modifier.height(0.dp))

                OutlinedTextField(
                    value = productName,
                    onValueChange = { productName = it },
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
                    value = description,
                    onValueChange = { description = it },
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
                    value = price.toString(),
                    onValueChange = { price = it.toDouble() },
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
//            OutlinedTextField(
//                value = category.toString(),
//                onValueChange = { category = it.toInt() },
//                label = { Text("Category", color = Color(0xFFB5A9A9)) },
//                singleLine = true,
//                modifier = Modifier
//                    .background(color = Color(0xFFF4F3EF))
//                    .border(15.dp, color = Color(0xFFF4F3EF), RectangleShape),
//                shape = RoundedCornerShape(corner = CornerSize(15.dp)),
//                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
//                keyboardActions = KeyboardActions(
//                    onDone = {
//                        keyboardController?.hide()
//                    })
//            )
            //Spacer(modifier = Modifier.height(30.dp))
            OutlinedTextField(
                value = image,
                onValueChange = { image = it },
                label = { Text("Image", color = Color(0xFFB5A9A9)) },
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

            Button(
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF745D5D)),
                    shape = RoundedCornerShape(5.dp),
                    modifier = Modifier.width(180.dp).height(55.dp),
                    onClick = {
                        var currentCatagory=razzaViewModel.category.value
                        val product = Product(title = productName, price = price.toDouble(), description = description, category = currentCatagory.id, image = image)
                        razzaViewModel.addNewProduct(product)
                        navController.navigate(NavScreen.AllProducts.route)
                    }) {
                    Text(text = "Add Product", color = Color.White , fontSize = 18.sp)

                }

            }




    }
 
    

