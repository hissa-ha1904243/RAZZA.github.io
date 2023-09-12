package com.example.razzashoppingapp.view.screens.forgotPassword_screens

import androidx.activity.ComponentActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Phone
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.razzashoppingapp.view.navigation.NavScreen
import com.example.razzashoppingapp.view.screens.welcome_screens.Back
import com.example.razzashoppingapp.R
import com.example.razzashoppingapp.viewmodel.AuthViewModel

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun ForgotPassScreen2(navController: NavController,
) {
    val authViewModel =
        viewModel<AuthViewModel>(viewModelStoreOwner = LocalContext.current as ComponentActivity)

    var verificationCode = remember { mutableStateOf("") }
    val keyboardController = LocalSoftwareKeyboardController.current
    Back(navController)
    Column(
        Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,verticalArrangement = Arrangement.spacedBy(40.dp)
    ) {
        Spacer(modifier = Modifier.height(50.dp))
        ForgotPassImage2()
        Text("Verification Code?", fontSize = 30.sp, color=Color(0xFFB5A9A9))
        //Code
        CodeTextField(verificationCode,"Code")
        /*Text("Enter your code (00:30)\n" +
                "Not Recevied? RESEND")*/
        Spacer(modifier = Modifier.height(20.dp))
        Continue2("Continue",navController)
    }

}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun CodeTextField(
    verificationCode: MutableState<String>,
    text:String
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    OutlinedTextField(
        value = verificationCode.value,
        onValueChange = { verificationCode.value = it },
        singleLine = true,
        modifier = Modifier
            .background(color = Color(0xFFF4F3EF))
            .border(15.dp, color = Color(0xFFF4F3EF), RectangleShape),
        shape = RoundedCornerShape(corner = CornerSize(15.dp)),
        label = { Text("$text", color = Color(0xFFB5A9A9)) },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        keyboardActions = KeyboardActions(
            onDone = {
                keyboardController?.hide()
            })

    )
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun PhoneNumberTextField(
    numberState: MutableState<String>,
    text:String
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    OutlinedTextField(
        value = numberState.value,
        onValueChange = { numberState.value = it },
        singleLine = true,
        modifier = Modifier
            .background(color = Color(0xFFF4F3EF))
            .border(15.dp, color = Color(0xFFF4F3EF), RectangleShape),
        shape = RoundedCornerShape(corner = CornerSize(15.dp)),
        label = { Text("$text", color = Color(0xFFB5A9A9)) },
        leadingIcon = {
                Icon(
                    imageVector = Icons.Filled.Phone,
                    contentDescription = "phone icon",
                    Modifier,
                    tint = Color(0xFF745D5D)
                )

            },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        keyboardActions = KeyboardActions(
            onDone = {
                keyboardController?.hide()
            })
    )
}

@Composable
fun Continue2(text: String,navController: NavController) {
    Surface(
        Modifier
            .height(60.dp)
            .width(250.dp),
        color = Color(0xFF745D5D),
        shape = RoundedCornerShape(corner = CornerSize(8.dp)),
        elevation = 5.dp
    ) {
        Row(
            Modifier.clickable{navController.navigate(NavScreen.ForgotPass3.route)},
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,

            ) {
            Text("$text", fontSize = 30.sp, color = Color(0xFFF4F3EF))

        }
    }

}

@Composable
fun ForgotPassImage2() {
    Surface(
        Modifier
            .size(300.dp)
            .padding(5.dp),
        shape = RectangleShape,
    ) {
        Image(painter = painterResource(id = R.drawable.forgotpass2), contentDescription = "image")
    }
}
/*
@Preview(showBackground = true)
@Composable
fun PassPreview2() {
    RazzaTheme {
        val navController = rememberNavController()
        NavGraph(navController = navController)
        ForgotPassScreen2(navController)
    }
}*/