package com.example.razzashoppingapp.view.screens.forgotPassword_screens

import android.util.Log
import android.widget.Toast
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
import com.example.razzashoppingapp.view.screens.welcome_screens.Back
import com.example.razzashoppingapp.view.screens.welcome_screens.EmailField
import com.example.razzashoppingapp.R
import com.example.razzashoppingapp.room.entity.User
import com.example.razzashoppingapp.view.navigation.NavScreen
import com.example.razzashoppingapp.viewmodel.AuthViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun ForgotPassScreen1(navController: NavController,
) {
    val context = LocalContext.current
    val authViewModel: AuthViewModel =
        viewModel(viewModelStoreOwner = context as ComponentActivity)

    val auth = Firebase.auth

    if (authViewModel.user?.email != null) {
        Log.v("User", auth.currentUser.toString())
        navController.navigate(NavScreen.Profile.route)
    }
    var forgotPassEmail = remember { mutableStateOf("") }
    val keyboardController = LocalSoftwareKeyboardController.current
    Back(navController)
    Column(
        Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(40.dp)
    ) {
        Spacer(modifier = Modifier.height(50.dp))
        ForgotPassImage1()
        Text("Forgot Your Password?", fontSize = 30.sp, color = Color(0xFFB5A9A9))
        //Email
        EmailField(registerEmail = forgotPassEmail)
        Spacer(modifier = Modifier.height(30.dp))
        Button(
            colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF745D5D)),
            shape = RoundedCornerShape(5.dp),
            modifier = Modifier
                .width(180.dp)
                .height(55.dp),
            onClick = {
                FirebaseAuth.getInstance().sendPasswordResetEmail(forgotPassEmail.value)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            Toast.makeText(context, "check your email", Toast.LENGTH_LONG).show()
                        } else {
                            Toast.makeText(context, "email invalid!", Toast.LENGTH_LONG).show()
                        }
                        // authViewModel.resetPassword(forgotPassEmail.value)

                    }
            }) {
            Text(text = "Send", color = Color.White, fontSize = 20.sp)
        }

    }

}

@Composable
fun Continue1(text: String, navController: NavController) {
    Surface(
        Modifier
            .height(60.dp)
            .width(250.dp),
        color = Color(0xFF745D5D),
        shape = RoundedCornerShape(corner = CornerSize(8.dp)),
        elevation = 5.dp
    ) {
        Row(
            Modifier.clickable {
                navController.navigate(NavScreen.ForgotPass2.route)
                               },
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,

            ) {
            Text("$text", fontSize = 30.sp, color = Color(0xFFF4F3EF))

        }
    }

}

@Composable
fun ForgotPassImage1() {
    Surface(
        Modifier
            .size(300.dp)
            .padding(5.dp),
        shape = RectangleShape,
    ) {
        Image(painter = painterResource(id = R.drawable.forgotpass1), contentDescription = "image")
    }
}
/*
@Preview(showBackground = true)
@Composable
fun PassPreview1() {
    RazzaTheme {
        val navController = rememberNavController()
        NavGraph(navController = navController)
        ForgotPassScreen1(navController)
    }
}*/