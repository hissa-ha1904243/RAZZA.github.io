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
import com.example.razzashoppingapp.view.navigation.NavScreen
import com.example.razzashoppingapp.viewmodel.RazzaViewModel
import com.example.razzashoppingapp.BottomBar
import com.example.razzashoppingapp.TopBar
import com.example.razzashoppingapp.room.entity.Category
import com.example.razzashoppingapp.viewmodel.AuthViewModel

@OptIn(ExperimentalComposeUiApi::class)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun AddCategoryScreen(navController: NavHostController) {
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
            text = "ADD CATEGORY",
            color = Color.Black,
            fontSize = 27.sp,
            textAlign = TextAlign.Center
        )
        Divider(color = Color(0xFF8D8D8D), thickness = 1.dp, modifier = Modifier.padding(20.dp))

        InfoFields(navController)


    }


}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun InfoFields(navController: NavHostController, razzaViewModel: RazzaViewModel = viewModel(LocalContext.current as ComponentActivity)){
    val keyboardController = LocalSoftwareKeyboardController.current

    //var id by remember { mutableStateOf(0) }
    var name by remember { mutableStateOf("") }

    Column(Modifier.padding(10.dp)) {
        Spacer(modifier = Modifier.height(100.dp))

        //Category name
        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Category Name", color = Color(0xFFB5A9A9)) },
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

        Spacer(modifier = Modifier.height(50.dp))
        Button(
            colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF745D5D)),
            shape = RoundedCornerShape(5.dp),
            modifier = Modifier.width(180.dp).height(55.dp),
            onClick = {
                val category = Category(name = name)
                razzaViewModel.addNewCategory(category)
                navController.navigate(NavScreen.Home.route)
            }) {
            Text(text = "Add Category", color = Color.White , fontSize = 20.sp)

        }
    }

}