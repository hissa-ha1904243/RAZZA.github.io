package com.example.razzashoppingapp.view.screens.productInfo_screens

import android.annotation.SuppressLint
import androidx.activity.ComponentActivity
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.razzashoppingapp.BottomBar
import com.example.razzashoppingapp.TopBar
import com.example.razzashoppingapp.room.entity.Review
import com.example.razzashoppingapp.view.navigation.NavScreen
import com.example.razzashoppingapp.viewmodel.AuthViewModel
import com.example.razzashoppingapp.viewmodel.RazzaViewModel

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun UpdateReview(
    navController: NavHostController,
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
                text = "UPDATE YOUR REVIEW",
                color = Color.Black,
                fontSize = 27.sp,
                textAlign = TextAlign.Center
            )
            Divider(color = Color(0xFF8D8D8D), thickness = 1.dp, modifier = Modifier.padding(20.dp))
Spacer(modifier = Modifier.height(60.dp))
            UpdateReviewFields(navController)

        }
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun UpdateReviewFields(navController: NavHostController,
                       razzaViewModel: RazzaViewModel = viewModel(LocalContext.current as ComponentActivity)
)
{
    val currentReview: Review by razzaViewModel.review.collectAsState()
    var review=currentReview.text
    var rate=currentReview.rating

    Column(modifier = Modifier.height(220.dp), verticalArrangement = Arrangement.Center,
    horizontalAlignment = Alignment.CenterHorizontally) {
        var expanded by remember { mutableStateOf(false) }
        val rating = listOf("1", "2", "3", "4")
        var selectedText by remember { mutableStateOf("${rate}") }
        var textfieldSize by remember { mutableStateOf(Size.Zero) }
        val icon = if (expanded)
            Icons.Filled.KeyboardArrowUp
        else
            Icons.Filled.KeyboardArrowDown
        val keyboardController = LocalSoftwareKeyboardController.current
        Row() {
            TextField(
                value = review,
                onValueChange = { razzaViewModel.updateReviewText(it)},
                enabled = true,
                singleLine = false,
                modifier = Modifier.width(290.dp),
                label = { Text("Your Review") },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Done
                ),
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Color(
                        0xFFF4F3EF
                    )
                ),
                keyboardActions = KeyboardActions(
                    onDone = {
                        keyboardController?.hide()
                    })
            )
            Row() {

                OutlinedTextField(
                    value = selectedText,
                    onValueChange = { selectedText = it
                                    razzaViewModel.updateReviewRate(it.toInt())},
                    modifier = Modifier
                        .fillMaxWidth()
                        .onGloballyPositioned { coordinates ->
                            textfieldSize = coordinates.size.toSize()
                        },
                    label = { Text("★") },
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
                    rating.forEach { num ->
                        DropdownMenuItem(onClick = {
                            selectedText = num
                            expanded = false
                        }) {
                            Text(text = num)
                        }
                    }
                }
            }
        }
        Spacer(Modifier.height(50.dp))
        Button(
            colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF745D5D)),
            shape = RoundedCornerShape(5.dp),
            modifier = Modifier
                .width(180.dp)
                .height(55.dp),
            onClick = {
                razzaViewModel.updateReview(currentReview)
                navController.navigate(NavScreen.PostReview.route)

            }) {
            Text(text = "Update Review", color = Color.White , fontSize = 18.sp)

        }


    }
}
