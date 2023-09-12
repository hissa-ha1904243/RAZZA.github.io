package com.example.razzashoppingapp.view.screens.forgotPassword_screens

import androidx.activity.ComponentActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.razzashoppingapp.view.navigation.NavScreen
import com.example.razzashoppingapp.view.screens.welcome_screens.Back
import com.example.razzashoppingapp.view.screens.welcome_screens.PasswordFeild
import com.example.razzashoppingapp.R
import com.example.razzashoppingapp.viewmodel.AuthViewModel

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun ForgotPassScreen3(navController: NavController,
) {
    val authViewModel =
        viewModel<AuthViewModel>(viewModelStoreOwner = LocalContext.current as ComponentActivity)

    val keyboardController = LocalSoftwareKeyboardController.current
    //new password:
    var newPassword = remember { mutableStateOf("") }
    var newpassVisibility = remember { mutableStateOf(false) }
    val newpassVisibilityIcon = if (newpassVisibility.value)
        painterResource(id = R.drawable.view_pass)
    else painterResource(id = R.drawable.hide_pass)
    //confirm new password:
    var confirmNewPass = remember { mutableStateOf("") }
    var confirmNewPassVisibility = remember { mutableStateOf(false) }
    val confirmVisibilityIcon = if (confirmNewPassVisibility.value)
        painterResource(id = R.drawable.view_pass)
    else painterResource(id = R.drawable.hide_pass)
    Back(navController)
        Column(
            Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            Spacer(modifier = Modifier.height(50.dp))
            ForgotPassImage3()
            Text("Password Recovery?", fontSize = 30.sp, color = Color(0xFFB5A9A9))
            //new Password
            Spacer(Modifier.height(7.dp))
           PasswordFeild(PasswordState =newPassword , PassVisibilityState =newpassVisibility , label = "New Password")
            Spacer(modifier = Modifier.height(10.dp))
            //Confirm new Password
           PasswordFeild(PasswordState = confirmNewPass, PassVisibilityState =confirmNewPassVisibility , label = "Confirm Password")
            Spacer(modifier = Modifier.height(80.dp))
            Continue3("Log In", navController)
        }
    }

@Composable
fun Continue3(text: String,navController: NavController) {
    Surface(
        Modifier
            .height(60.dp)
            .width(250.dp),
        color = Color(0xFF745D5D),
        shape = RoundedCornerShape(corner = CornerSize(8.dp)),
        elevation = 5.dp
    ) {
        Row(
            Modifier.clickable{navController.navigate(NavScreen.Home.route)},
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,

            ) {
            Text("$text", fontSize = 30.sp, color = Color(0xFFF4F3EF))

        }
    }

}

@Composable
fun ForgotPassImage3() {
    Surface(
        Modifier
            .size(300.dp)
            .padding(5.dp),
        shape = RectangleShape,
    ) {
        Image(painter = painterResource(id = R.drawable.forgotpass3), contentDescription = "image")
    }
}

/*@Preview(showBackground = true)
@Composable
fun PassPreview3() {
    RazzaTheme {
        val navController = rememberNavController()
        NavGraph(navController = navController)
        ForgotPassScreen3(navController)
    }
}*/