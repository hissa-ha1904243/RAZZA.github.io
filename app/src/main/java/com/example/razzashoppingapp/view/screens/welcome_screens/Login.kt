package com.example.razzashoppingapp.view.screens.welcome_screens

import android.annotation.SuppressLint
import android.util.Log
import android.widget.Toast
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
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
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
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.razzashoppingapp.view.navigation.NavScreen
import com.example.razzashoppingapp.R
import com.example.razzashoppingapp.room.entity.Product
import com.example.razzashoppingapp.room.entity.User
import com.example.razzashoppingapp.viewmodel.AuthViewModel
import com.example.razzashoppingapp.viewmodel.RazzaViewModel
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


@Composable
fun LogInScreen(navController: NavController,razzaViewModel: RazzaViewModel = viewModel(LocalContext.current as ComponentActivity)) {
    //The States:
    var logInEmail = remember { mutableStateOf("") }
    var logInPassword = remember { mutableStateOf("") }
    var logInPassVisibility = remember { mutableStateOf(false) }
    //

    val context = LocalContext.current
    val authViewModel: AuthViewModel =
        viewModel(viewModelStoreOwner = context as ComponentActivity)

    val auth = Firebase.auth

    if (authViewModel.user?.email != null) {
        Log.v("User", auth.currentUser.toString())
        navController.navigate(NavScreen.Profile.route)
    }


    BackToMain(navController)
    Spacer(modifier = Modifier.height(10.dp))
    Column(
        Modifier.fillMaxSize(), verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(60.dp))

        Logo()
        Spacer(modifier = Modifier.height(40.dp))

        val users: List<User> by razzaViewModel.usersFB.collectAsState()
        Spacer(modifier = Modifier.height(20.dp))
        content(logInEmail, logInPassword,
            logInPassVisibility,users, navController)


    }
}
@Composable
fun BackToMain(navController: NavController) {
    val context = LocalContext.current
    val authViewModel: AuthViewModel =
        viewModel(viewModelStoreOwner = context as ComponentActivity)
    val auth = Firebase.auth

    Box(Modifier.clickable {
        navController.navigate(NavScreen.First.route)
    }, Alignment.TopStart) {
        Icon(
            painter = painterResource(id = R.drawable.back),
            contentDescription = "back arrow",
            Modifier
                .size(70.dp)
                .padding(10.dp),
            tint = Color(0xFFB5A9A9)
        )

    }
}

@Composable
fun Back(navController: NavController) {
    val context = LocalContext.current
    val authViewModel: AuthViewModel =
        viewModel(viewModelStoreOwner = context as ComponentActivity)

    val auth = Firebase.auth

    if (authViewModel.user?.email != null) {
        Log.v("User", auth.currentUser.toString())
        navController.navigate(NavScreen.Profile.route)
    }


    Box(Modifier.clickable {
        navController.popBackStack()
    }, Alignment.TopStart) {
        Icon(
            painter = painterResource(id = R.drawable.back),
            contentDescription = "back arrow",
            Modifier
                .size(70.dp)
                .padding(10.dp),
            tint = Color(0xFFB5A9A9)
        )

    }
}

@Composable
fun Logo() {



    Surface(
        Modifier
            .size(220.dp)
            .padding(10.dp),
        shape = RectangleShape,
    ) {
        Image(painter = painterResource(id = R.drawable.logo), contentDescription = "Logo")
    }
}


@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun UsernameField(
    UsernameState: MutableState<String>
) {



    val keyboardController = LocalSoftwareKeyboardController.current
    OutlinedTextField(
        value = UsernameState.value,
        onValueChange = { UsernameState.value = it },
        label = { Text("Username", color = Color(0xFFB5A9A9)) },
        singleLine = true,
        modifier = Modifier
            .background(color = Color(0xFFF4F3EF))
            .border(15.dp, color = Color(0xFFF4F3EF), RectangleShape),
        shape = RoundedCornerShape(corner = CornerSize(15.dp)),
        leadingIcon = {
            IconButton(onClick = {}) {
                Icon(
                    imageVector = Icons.Filled.Person,
                    contentDescription = "person icon",
                    Modifier,
                    tint = Color(0xFF745D5D)
                )
            }
        },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
        keyboardActions = KeyboardActions(
            onDone = {
                keyboardController?.hide()
            })
    )
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun PasswordFeild(
    PasswordState: MutableState<String>,
    PassVisibilityState: MutableState<Boolean>,
    label: String
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    var visibilityIcon = if (PassVisibilityState.value)
        painterResource(id = R.drawable.view_pass)
    else painterResource(id = R.drawable.hide_pass)
    OutlinedTextField(
        value = PasswordState.value,
        onValueChange = { PasswordState.value = it },
        label = { Text("$label", color = Color(0xFFB5A9A9)) },
        singleLine = true,
        modifier = Modifier
            .background(color = Color(0xFFF4F3EF))
            .border(15.dp, color = Color(0xFFF4F3EF), RectangleShape),
        shape = RoundedCornerShape(corner = CornerSize(15.dp)),
        leadingIcon = {
            IconButton(onClick = {}) {
                Icon(
                    imageVector = Icons.Filled.Lock,
                    contentDescription = "lock icon",
                    Modifier,
                    tint = Color(0xFF745D5D)
                )
            }
        }, trailingIcon = {
            IconButton(onClick = { PassVisibilityState.value = !PassVisibilityState.value }) {
                Icon(
                    painter = visibilityIcon,
                    contentDescription = "visibility icon",
                    Modifier,
                    tint = Color(0xFF745D5D)
                )
            }
        },
        visualTransformation = if (PassVisibilityState.value) VisualTransformation.None else PasswordVisualTransformation(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        keyboardActions = KeyboardActions(
            onDone = {
                keyboardController?.hide()
            })
    )
}



@SuppressLint("SuspiciousIndentation")
@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun content(EmailState: MutableState<String>,
            PasswordState: MutableState<String>,
            PassVisibilityState: MutableState<Boolean>,
            users:List<User>,
            navController: NavController,
            razzaViewModel: RazzaViewModel = viewModel(LocalContext.current as ComponentActivity),
){
    val context = LocalContext.current
    val authViewModel: AuthViewModel =
        viewModel(viewModelStoreOwner = context as ComponentActivity)

    val auth = Firebase.auth

    if (authViewModel.user?.email != null) {
        Log.v("User", auth.currentUser.toString())
        navController.navigate(NavScreen.Profile.route)
    }



    val keyboardController = LocalSoftwareKeyboardController.current
        OutlinedTextField(
            value = EmailState.value,
            onValueChange = { EmailState.value = it },
            label = { Text("Email", color = Color(0xFFB5A9A9)) },
            singleLine = true,
            modifier = Modifier
                .background(color = Color(0xFFF4F3EF))
                .border(15.dp, color = Color(0xFFF4F3EF), RectangleShape),
            shape = RoundedCornerShape(corner = CornerSize(15.dp)),
            leadingIcon = {
                IconButton(onClick = {}) {
                    Icon(
                        imageVector = Icons.Filled.Person,
                        contentDescription = "person icon",
                        Modifier,
                        tint = Color(0xFF745D5D)
                    )
                }
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            keyboardActions = KeyboardActions(
                onDone = {
                    keyboardController?.hide()
                })
        )
    Spacer(modifier = Modifier.height(20.dp))
    var visibilityIcon = if (PassVisibilityState.value)
        painterResource(id = R.drawable.view_pass)
    else painterResource(id = R.drawable.hide_pass)
    OutlinedTextField(
        value = PasswordState.value,
        onValueChange = { PasswordState.value = it },
        label = { Text("Password", color = Color(0xFFB5A9A9)) },
        singleLine = true,
        modifier = Modifier
            .background(color = Color(0xFFF4F3EF))
            .border(15.dp, color = Color(0xFFF4F3EF), RectangleShape),
        shape = RoundedCornerShape(corner = CornerSize(15.dp)),
        leadingIcon = {
            IconButton(onClick = {}) {
                Icon(
                    imageVector = Icons.Filled.Lock,
                    contentDescription = "lock icon",
                    Modifier,
                    tint = Color(0xFF745D5D)
                )
            }
        }, trailingIcon = {
            IconButton(onClick = { PassVisibilityState.value = !PassVisibilityState.value }) {
                Icon(
                    painter = visibilityIcon,
                    contentDescription = "visibility icon",
                    Modifier,
                    tint = Color(0xFF745D5D)
                )
            }
        },
        visualTransformation = if (PassVisibilityState.value) VisualTransformation.None else PasswordVisualTransformation(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        keyboardActions = KeyboardActions(
            onDone = {
                keyboardController?.hide()
            })
    )
    Row(Modifier.clickable { navController.navigate(NavScreen.ForgotPass1.route) }) {
        Text("Forgot Password?", color = Color(0xFF745D5D))
    }

    Spacer(modifier = Modifier.height(130.dp))
    val mContext = LocalContext.current
    var valid = true

    Button(
        colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF745D5D)),
        shape = RoundedCornerShape(5.dp),
        modifier = Modifier
            .width(180.dp)
            .height(55.dp),
        onClick = {
//            if(EmailState.value.isEmpty() and PasswordState.value.isEmpty()){
//                Toast.makeText(mContext, "Email and Password Fields are Empty", Toast.LENGTH_SHORT).show()
//            }
//            if(EmailState.value.isNotEmpty() and PasswordState.value.isEmpty()){
//                Toast.makeText(mContext, "Password Field is Empty", Toast.LENGTH_SHORT).show()
//            }
//            if(EmailState.value.isEmpty() and PasswordState.value.isNotEmpty()){
//                Toast.makeText(mContext, "Username Field is Empty", Toast.LENGTH_SHORT).show()
//            }
//            if(EmailState.value.isNotEmpty() and PasswordState.value.isNotEmpty()) {
//                    razzaViewModel.signInWithEmailAndPassWord(EmailState.value, PasswordState.value)
//
////                for (user in users) {
////                    if (user.username.equals(UsernameState.value)
////                        and user.password.equals(PasswordState.value)
////                    ) {
////                        valid = true
////                        razzaViewModel.setCurrentUser(user)
////                       // navController.navigate(NavScreen.Home.route)
////                        break
////                    }
////                    else {
////                        valid = false
////                    }
////
////                }
//
//
//                if (valid == false)
//                {
//                    Toast.makeText(mContext, "Invalid Username or Password", Toast.LENGTH_LONG).show()
//                }
//                else if (valid == true) {
//                    navController.navigate(NavScreen.Home.route)
//                }
//            }
            if (EmailState.value.isNotEmpty() and PasswordState.value.isNotEmpty()) {
                authViewModel.signInWithEmailAndPassWord(EmailState.value, PasswordState.value)
                //auth.signInWithEmailAndPassword(EmailState.value, PasswordState.value)
                for (user in users) {
                    if (user.email.equals(EmailState.value)
                        and user.password.equals(PasswordState.value)
                    ) {
                        razzaViewModel.setCurrentUser(user)
                        break
                    }

                }
                if(authViewModel.user!=null)

                       navController.navigate(NavScreen.Profile.route)

            } else
                Toast.makeText(mContext,
                     "Please enter email and password.",
                    Toast.LENGTH_SHORT
                ).show()
        },

    )

            {
        Text(text = "Login ", color = Color.White, fontSize = 20.sp)
    }

}