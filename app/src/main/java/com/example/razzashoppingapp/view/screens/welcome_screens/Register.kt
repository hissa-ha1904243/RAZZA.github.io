package com.example.razzashoppingapp.view.screens.welcome_screens

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
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
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.razzashoppingapp.R
import com.example.razzashoppingapp.room.entity.User
import com.example.razzashoppingapp.view.navigation.NavScreen
import com.example.razzashoppingapp.viewmodel.AuthViewModel
import com.example.razzashoppingapp.viewmodel.RazzaViewModel
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await


@Composable
fun RegisterScreen(navController: NavController,
                   razzaViewModel: RazzaViewModel = viewModel(LocalContext.current as ComponentActivity)
) {

    val users: List<User> by razzaViewModel.usersFB.collectAsState()

    //The States:
    var registerUsername = remember { mutableStateOf("") }
    var registerEmail = remember { mutableStateOf("") }
    var registerPassword = remember { mutableStateOf("") }
    var passVisibility = remember { mutableStateOf(false) }
    var registerConfirmPass = remember { mutableStateOf("") }
    var confirmPassVisibility = remember { mutableStateOf(false) }
    var phoneNumber= remember { mutableStateOf("")}
    //
    BackToMain(navController)
    Column(
        Modifier.fillMaxSize(), verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(60.dp))
        Logo()
        RegisterFields(
            registerUsername,
            registerEmail,
            registerPassword,
            passVisibility,
            registerConfirmPass,
            confirmPassVisibility,
            phoneNumber,
            navController,
            users
        )
//        EnterButton(navController, "Register")
    }
}


@SuppressLint("SuspiciousIndentation")
@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun RegisterFields(
    registerUsername: MutableState<String>,
    registerEmail: MutableState<String>,
    registerPassword: MutableState<String>,
    passVisibility: MutableState<Boolean>,
    registerConfirmPass: MutableState<String>,
    confirmPassVisibility: MutableState<Boolean>,
    phoneNumber: MutableState<String>,
    navController: NavController,
    users:List<User>,
    razzaViewModel: RazzaViewModel = viewModel(LocalContext.current as ComponentActivity)

) {

    val authViewModel: AuthViewModel =
        viewModel(viewModelStoreOwner = LocalContext.current as ComponentActivity)
    val passVisibilityIcon = if (passVisibility.value)
        painterResource(id = R.drawable.view_pass)
    else painterResource(id = R.drawable.hide_pass)
    val confirmVisibilityIcon = if (confirmPassVisibility.value)
        painterResource(id = R.drawable.view_pass)
    else painterResource(id = R.drawable.hide_pass)



    Column(Modifier.padding(10.dp)) {
        //Register Username
//        UsernameField(UsernameState = registerUsername)
        val keyboardController = LocalSoftwareKeyboardController.current
        OutlinedTextField(
            value = registerUsername.value,
            onValueChange = { registerUsername.value = it },
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

        Spacer(modifier = Modifier.height(10.dp))

        //Register Email
//        EmailField(registerEmail)
        OutlinedTextField(
            value = registerEmail.value,
            onValueChange = { registerEmail.value = it },
            singleLine = true,
            modifier = Modifier
                .background(color = Color(0xFFF4F3EF))
                .border(15.dp, color = Color(0xFFF4F3EF), RectangleShape),
            shape = RoundedCornerShape(corner = CornerSize(15.dp)),
            label = { Text("Email", color = Color(0xFFB5A9A9)) },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Filled.Email,
                    contentDescription = "email icon",
                    Modifier,
                    tint = Color(0xFF745D5D)
                )

            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            keyboardActions = KeyboardActions(
                onDone = {
                    keyboardController?.hide()
                })
        )

        Spacer(modifier = Modifier.height(10.dp))
        //Phone Number
//        PhoneNumberTextField(phoneNumber,"Phone Number")
        OutlinedTextField(
            value = phoneNumber.value,
            onValueChange = { phoneNumber.value = it },
            singleLine = true,
            modifier = Modifier
                .background(color = Color(0xFFF4F3EF))
                .border(15.dp, color = Color(0xFFF4F3EF), RectangleShape),
            shape = RoundedCornerShape(corner = CornerSize(15.dp)),
            label = { Text("Phone Number", color = Color(0xFFB5A9A9)) },
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
        Spacer(modifier = Modifier.height(10.dp))
        //Register Password
//        PasswordFeild(
//            PasswordState = registerPassword,
//            PassVisibilityState = passVisibility,
//            label = "Password"
//        )
        OutlinedTextField(
            value = registerPassword.value,
            onValueChange = { registerPassword.value = it },
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
                IconButton(onClick = { passVisibility.value = !passVisibility.value }) {
                    Icon(
                        painter = passVisibilityIcon,
                        contentDescription = "visibility icon",
                        Modifier,
                        tint = Color(0xFF745D5D)
                    )
                }
            },
            visualTransformation = if (passVisibility.value) VisualTransformation.None else PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            keyboardActions = KeyboardActions(
                onDone = {
                    keyboardController?.hide()
                })
        )
        Spacer(modifier = Modifier.height(10.dp))
        //Register Confirm Password
//        PasswordFeild(
//            PasswordState = registerConfirmPass,
//            PassVisibilityState = confirmPassVisibility,
//            label = "Confirm Password"
//        )
        OutlinedTextField(
            value = registerConfirmPass.value,
            onValueChange = { registerConfirmPass.value = it },
            label = { Text("Confirm Password", color = Color(0xFFB5A9A9)) },
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
                IconButton(onClick = { confirmPassVisibility.value = !confirmPassVisibility.value }) {
                    Icon(
                        painter = confirmVisibilityIcon,
                        contentDescription = "visibility icon",
                        Modifier,
                        tint = Color(0xFF745D5D)
                    )
                }
            },
            visualTransformation = if (confirmPassVisibility.value) VisualTransformation.None else PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            keyboardActions = KeyboardActions(
                onDone = {
                    keyboardController?.hide()
                })
        )

    }
    Spacer(modifier = Modifier.height(30.dp))

    val mContext = LocalContext.current
    var valid = true
    var empty = true

    val context = LocalContext.current
//    val authViewModel: AuthViewModel =
//        viewModel(viewModelStoreOwner = context as ComponentActivity)
//
//    if (authViewModel.user?.email != null) {
//        navController.navigate(NavScreen.Profile.route)
//    }

    val auth = Firebase.auth
    Button(
        colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF745D5D)),
        shape = RoundedCornerShape(5.dp),
        modifier = Modifier
            .width(180.dp)
            .height(55.dp),
        onClick = {
            if(registerUsername.value.isEmpty()
                || registerEmail.value.isEmpty()
                || phoneNumber.value.isEmpty()
                || registerPassword.value.isEmpty()
                || registerConfirmPass.value.isEmpty()
            ) {
                Toast.makeText(mContext, "Required Field is Empty", Toast.LENGTH_LONG).show()
                valid = false
                empty = true
            }

            else if(registerPassword.value != registerConfirmPass.value) {
                Toast.makeText(
                    mContext,
                    "Confirm Password Field does not match Password field",
                    Toast.LENGTH_LONG
                ).show()
                valid = false
            }


            else {
                for (user in users) {
                    if (user.username == registerUsername.value
                        || user.email == registerEmail.value
                        || user.phone == phoneNumber.value
                    ) {
                        valid = false
                        empty = false
                        break
                    } else {
                        valid = true
                        empty = false
                    }

                }



                if (valid == false && empty == false) {
                    Toast.makeText(mContext, "This user already exists.", Toast.LENGTH_LONG).show()
                } else if (valid == true && empty == false) {
                    val user = User(
                        registerUsername.value,
                        registerPassword.value,
                        registerEmail.value,
                        phoneNumber.value
                    )
                    authViewModel.createUserEmailPassword(registerEmail.value, registerPassword.value)

//                    razzaViewModel.setCurrentUser(user)
                  //  auth.createUserWithEmailAndPassword(registerEmail.value, registerPassword.value)
                    if(authViewModel.user!=null)
                        razzaViewModel.addUser(user)
                    razzaViewModel.setCurrentUser(user)
                    navController.navigate(NavScreen.Profile.route)
                }
            }

        }) {
        Text(text = "Register", color = Color.White, fontSize = 20.sp)
    }


}


@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun EmailField(
    registerEmail: MutableState<String>
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    OutlinedTextField(
        value = registerEmail.value,
        onValueChange = { registerEmail.value = it },
        singleLine = true,
        modifier = Modifier
            .background(color = Color(0xFFF4F3EF))
            .border(15.dp, color = Color(0xFFF4F3EF), RectangleShape),
        shape = RoundedCornerShape(corner = CornerSize(15.dp)),
        label = { Text("Email", color = Color(0xFFB5A9A9)) },
        leadingIcon = {
            Icon(
                imageVector = Icons.Filled.Email,
                contentDescription = "email icon",
                Modifier,
                tint = Color(0xFF745D5D)
            )

        },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
        keyboardActions = KeyboardActions(
            onDone = {
                keyboardController?.hide()
            })
    )
}

@Composable
fun EnterButton(navController: NavController, label: String) {
    Button(
        colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF745D5D)),
        shape = RoundedCornerShape(5.dp),
        modifier = Modifier
            .width(180.dp)
            .height(55.dp),
        onClick = { navController.navigate(NavScreen.Home.route) }) {
        Text(text = "$label ", color = Color.White, fontSize = 20.sp)
    }
}

