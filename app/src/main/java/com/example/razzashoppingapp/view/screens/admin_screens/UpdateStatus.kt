package com.example.razzashoppingapp.view.screens.admin_screens

import android.annotation.SuppressLint
import androidx.activity.ComponentActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.razzashoppingapp.BottomBar
import com.example.razzashoppingapp.TopBar
import com.example.razzashoppingapp.R
import com.example.razzashoppingapp.room.entity.Order
import com.example.razzashoppingapp.view.navigation.NavScreen
import com.example.razzashoppingapp.viewmodel.AuthViewModel
import com.example.razzashoppingapp.viewmodel.RazzaViewModel


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun UpdateStatus(navController: NavHostController,
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

    }

    UpdateStatusLayout(navController)
   // thepicture()


}
@Composable
fun UpdateStatusLayout(navController:NavHostController,
                       razzaViewModel: RazzaViewModel = viewModel(LocalContext.current as ComponentActivity)
){
    var selectedText by remember { mutableStateOf("") }
    val currentOrder: Order by razzaViewModel.order.collectAsState()
    Column(
        modifier = Modifier
            .padding(vertical = 22.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(40.dp))

        Text(
            text = "RAZZA",
            color = Color(0xFFB5A9A9),
            fontSize = 20.sp,
            textAlign = TextAlign.Center
        )


        Text(
            text = "Update Order Status",
            color = Color.Black,
            fontSize = 27.sp,
            textAlign = TextAlign.Center
        )
        Divider(color = Color(0xFF8D8D8D), thickness = 1.dp, modifier = Modifier.padding(20.dp))
        Column(
            modifier = Modifier
                .padding(vertical = 22.dp, horizontal = 10.dp)
                .fillMaxSize(),
        ) {
            Spacer(modifier = Modifier.height(15.dp))
            Text(
                text = "Order Number:${currentOrder.id} ",
                color = Color(0xFF745D5D),
                fontSize = 22.sp,
                textAlign = TextAlign.Left

            )
            Spacer(modifier = Modifier.height(20.dp))
            Row() {
                var expanded by remember { mutableStateOf(false) }
                val status = listOf("processing", "shipped", "delivered")

                var textfieldSize by remember { mutableStateOf(Size.Zero) }
                val icon = if (expanded)
                    Icons.Filled.KeyboardArrowUp
                else
                    Icons.Filled.KeyboardArrowDown
                Text(
                    text = "Order Status: ",
                    color = Color(0xFF745D5D),
                    fontSize = 22.sp,
                    textAlign = TextAlign.Left
                )

                OutlinedTextField(
                    value = selectedText,
                    onValueChange = { selectedText = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .onGloballyPositioned { coordinates ->
                            textfieldSize = coordinates.size.toSize()
                        },
                    label = { Text("STATUS") },
                    trailingIcon = {
                        Icon(icon, "arrow",
                            Modifier.clickable { expanded = !expanded })
                    }
                )
                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false },
                    modifier = Modifier
                        .width(with(LocalDensity.current) { textfieldSize.width.toDp() })
                ) {
                    status.forEach { status ->
                        DropdownMenuItem(onClick = {
                            selectedText = status
                            expanded = false
                        }) {
                            Text(text = status)
                        }
                    }
                }
            }
            Box(Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                Button(
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF745D5D)),
                    shape = RoundedCornerShape(10.dp),
                    modifier = Modifier
                        .width(230.dp)
                        .height(80.dp)
                        .padding(20.dp),

                    onClick = {
                        currentOrder.status= selectedText
                        navController.navigate(NavScreen.Dash2.route)
                    }) {
                    Text(text = "SAVE", color = Color.White, fontSize = 20.sp)

                }
            }
            Spacer(modifier = Modifier.height(20.dp))


        }
    }
    when (selectedText) {
        "processing" -> {
            Column() {
                Spacer(modifier = Modifier.height(250.dp))
                Image(
                    painter = painterResource(R.drawable.processing),
                    contentDescription = "Processing",
                    modifier = Modifier
                        .fillMaxSize()

                )
            }
        }
        "shipped" -> {
            Column() {
                Spacer(modifier = Modifier.height(250.dp))
                Image(
                    painter = painterResource(id = R.drawable.shipped),
                    contentDescription = "Shipped",
                    modifier = Modifier
                        .fillMaxSize()

                )
            }
        }
        "delivered" -> {
            Column() {
                Spacer(modifier = Modifier.height(250.dp))
                Image(
                    painter = painterResource(id = R.drawable.delivered),
                    contentDescription = "Delivered",
                    modifier = Modifier
                        .fillMaxSize()

                )
            }
        }
        else -> null
    }
}

//@Composable
//fun thepicture(){
//    val state: String
//    state = "shipped"
//
//}