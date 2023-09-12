package com.example.razzashoppingapp.view.screens.userInfo_screens

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
import com.example.razzashoppingapp.room.entity.Address
import com.example.razzashoppingapp.room.entity.Product
import com.example.razzashoppingapp.room.entity.User
import com.example.razzashoppingapp.view.navigation.NavScreen
import com.example.razzashoppingapp.view.screens.admin_screens.addFields
import com.example.razzashoppingapp.viewmodel.AuthViewModel
import com.example.razzashoppingapp.viewmodel.RazzaViewModel

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun AddAddressScreen(navController:NavHostController) {
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
                text = "ADD ADDRESS",
                color = Color.Black,
                fontSize = 27.sp,
                textAlign = TextAlign.Center
            )
            Divider(color = Color(0xFF8D8D8D), thickness = 1.dp, modifier = Modifier.padding(20.dp))

            AddAddressFields(navController)


        }
    }
}
@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun AddAddressFields(navController: NavHostController,
                     razzaViewModel: RazzaViewModel = viewModel(LocalContext.current as ComponentActivity)
) {
    val keyboardController = LocalSoftwareKeyboardController.current

    var addName by remember { mutableStateOf("") }
    var addCity by remember { mutableStateOf("") }
    var zoneNo by remember { mutableStateOf(0) }
    var streetNo by remember { mutableStateOf(0) }
    var buildingNo by remember { mutableStateOf(0) }

    val currentUser: User by razzaViewModel.user.collectAsState()


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(30.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    )
    {

        Spacer(modifier = Modifier.height(15.dp))

        OutlinedTextField(
            value = addName,
            onValueChange = { addName = it },
            label = { Text("Address Name", color = Color(0xFFB5A9A9)) },
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

        Spacer(modifier = Modifier.height(10.dp))
        OutlinedTextField(
            value = addCity,
            onValueChange = { addCity = it },
            label = { Text("City", color = Color(0xFFB5A9A9)) },
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
        Spacer(modifier = Modifier.height(10.dp))
        OutlinedTextField(
            value = zoneNo.toString(),
            onValueChange = { zoneNo = it.toInt() },
            label = { Text("Zone", color = Color(0xFFB5A9A9)) },
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
        Spacer(modifier = Modifier.height(10.dp))
        OutlinedTextField(
            value = streetNo.toString(),
            onValueChange = { streetNo = it.toInt() },
            label = { Text("street Number", color = Color(0xFFB5A9A9)) },
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
        Spacer(modifier = Modifier.height(10.dp))
        OutlinedTextField(
            value = buildingNo.toString(),
            onValueChange = { buildingNo = it.toInt() },
            label = { Text("building Numder", color = Color(0xFFB5A9A9)) },
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
        val authViewModel =
            viewModel<AuthViewModel>(viewModelStoreOwner = LocalContext.current as ComponentActivity)

        Button(
            colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF745D5D)),
            shape = RoundedCornerShape(5.dp),
            modifier = Modifier.width(180.dp).height(55.dp),
            onClick = {

                val theAddress = Address(name= addName, city =addCity, userEmail = authViewModel.user?.email.toString(), zoneNo = zoneNo, streetNo = streetNo, buildingNo = buildingNo)
                razzaViewModel.addAddress(theAddress)
                navController.navigate(NavScreen.Addresses.route)
            }) {
            Text(text = "Add Address", color = Color.White , fontSize = 18.sp)

        }

    }





}
