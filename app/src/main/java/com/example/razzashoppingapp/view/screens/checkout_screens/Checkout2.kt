package com.example.razzashoppingapp.view.screens.checkout_screens

import android.annotation.SuppressLint
import android.os.Build
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.razzashoppingapp.TopBar
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.KeyboardType
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.razzashoppingapp.R
import com.example.razzashoppingapp.room.entity.Order
import com.example.razzashoppingapp.room.entity.ShoppingCart
import com.example.razzashoppingapp.room.entity.User
import com.example.razzashoppingapp.view.navigation.NavScreen
import com.example.razzashoppingapp.viewmodel.AuthViewModel
import com.example.razzashoppingapp.viewmodel.RazzaViewModel
import java.time.LocalDate
import java.time.Period


@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalComposeUiApi::class)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter", "SuspiciousIndentation")
@Composable
fun Checkout2(navController: NavHostController,
              razzaViewModel: RazzaViewModel = viewModel(LocalContext.current as ComponentActivity)

) {
    val authViewModel =
        viewModel<AuthViewModel>(viewModelStoreOwner = LocalContext.current as ComponentActivity)

//navController: NavHostController
Scaffold(
/* bottomBar = {
    BottomBar(navController = navController)
},
*/
topBar = {
    TopBar(navController = navController)
},

)
{

    val currentUser: User by razzaViewModel.user.collectAsState()
    val currentCart: ShoppingCart by razzaViewModel.cart.collectAsState()
    val total = currentCart.total
    val date = LocalDate.now()
    var period = Period.of(0, 0, 7)
    var modifiedDate = date.plus(period)



    Column(
modifier = Modifier
    .fillMaxSize()
    .background(Color.White)
    .padding(vertical = 5.dp, horizontal = 10.dp),


//.fillMaxSize()


) {
    Spacer(modifier = Modifier.height(40.dp))
    Image(
        painter = painterResource(id = R.drawable.checkout2),
        contentDescription = "Checkout",
        modifier = Modifier
            .fillMaxWidth()

    )
    Spacer(modifier = Modifier.height(40.dp))

    Text(
        text = "Payment Details",
        color = Color(0xFF413232),
        fontSize = 26.sp,
        fontWeight = FontWeight.Medium,

    )
    Divider(color = Color(0xFF8D8D8D), thickness = 1.dp, modifier = Modifier.padding(10.dp))
    Spacer(modifier = Modifier.height(10.dp))

    var name by remember { mutableStateOf("") }
    var cardno by remember { mutableStateOf("") }
    var expdate by remember { mutableStateOf("") }
    var cvv by remember { mutableStateOf("") }
    val mContext = LocalContext.current

    Spacer(modifier = Modifier.height(30.dp))

    Text(
        text = "Card Details:",
        color = Color(0xFF413232),
        fontSize = 20.sp,
        fontWeight = FontWeight.Medium
    )
    Spacer(modifier = Modifier.height(10.dp))
     Column(modifier = Modifier,
    verticalArrangement = Arrangement.Center,
    horizontalAlignment = Alignment.CenterHorizontally) {


    val keyboardController = LocalSoftwareKeyboardController.current
    OutlinedTextField(
        value = name,
        onValueChange = { name = it },
        singleLine = true,
        modifier = Modifier
            .background(color = Color.White)
            .height(70.dp)
            .width(350.dp),

        shape = RoundedCornerShape(corner = CornerSize(0.dp)),
        label = { Text("Card Holder Name", color = Color(0xFFB5A9A9)) },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
        keyboardActions = KeyboardActions(
            onDone = {
                keyboardController?.hide()
            })

    )

    // val keyboardController = LocalSoftwareKeyboardController.current
    OutlinedTextField(
        value = cardno,
        onValueChange = { cardno = it },
        singleLine = true,
        modifier = Modifier
            .background(color = Color.White)
            .height(70.dp)
            .width(350.dp),
        shape = RoundedCornerShape(corner = CornerSize(0.dp)),
        label = { Text("Card Number:", color = Color(0xFFB5A9A9)) },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        keyboardActions = KeyboardActions(
            onDone = {
                keyboardController?.hide()
            })

    )

    Row() {
        // val keyboardController = LocalSoftwareKeyboardController.current
        OutlinedTextField(
            value = expdate,
            onValueChange = { expdate = it },
            singleLine = true,
            modifier = Modifier
                .background(color = Color.White)
                .height(70.dp)
                .width(175.dp),
            shape = RoundedCornerShape(corner = CornerSize(0.dp)),
            label = { Text("Expiry Date:", color = Color(0xFFB5A9A9)) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            keyboardActions = KeyboardActions(
                onDone = {
                    keyboardController?.hide()
                })

        )


        // val keyboardController = LocalSoftwareKeyboardController.current
        OutlinedTextField(
            value = cvv,
            onValueChange = { cvv = it },
            singleLine = true,
            modifier = Modifier
                .background(color = Color.White)
                .height(70.dp)
                .width(175.dp),
            shape = RoundedCornerShape(corner = CornerSize(0.dp)),
            label = { Text("CVV:", color = Color(0xFFB5A9A9)) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            keyboardActions = KeyboardActions(
                onDone = {
                    keyboardController?.hide()
                })


        )
    }
    Spacer(modifier = Modifier.height(10.dp))
}

    Spacer(modifier = Modifier.height(5.dp))
    Divider(color = Color(0xFF8D8D8D), thickness = 1.dp, modifier = Modifier.padding(10.dp))
    Spacer(modifier = Modifier.height(10.dp))

    Text(
        text = "Total: ${currentCart.total} ",
        color = Color.Black,
        fontSize = 20.sp,
        fontWeight = FontWeight.Medium,
    )
    Spacer(modifier = Modifier.height(20.dp))
        val authViewModel =
            viewModel<AuthViewModel>(viewModelStoreOwner = LocalContext.current as ComponentActivity)

    Column(modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally){
        Button(
            colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF745D5D)),
            shape = RoundedCornerShape(5.dp),
            modifier = Modifier
                .width(210.dp)
                .height(50.dp),

            onClick = {
                    if(name.isEmpty() or cardno.isEmpty() or
                        expdate.isEmpty() or cvv.isEmpty()){
                    Toast.makeText(mContext, "Fields are Empty", Toast.LENGTH_SHORT).show()
                }
                else{
                    val order = Order(userEmail = authViewModel.user?.email.toString(), total = total, status = "Processing", date = modifiedDate.toString())
                        razzaViewModel.addNewOrder(order)
                        razzaViewModel.setCurrentOrder(order)
                navController.navigate(NavScreen.Checkout3.route) }}) {
            Text(text = "Checkout ", color = Color.White, fontSize = 22.sp)
        }
    }
}
}}