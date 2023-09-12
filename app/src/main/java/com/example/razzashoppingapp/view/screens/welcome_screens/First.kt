package com.example.razzashoppingapp.view.screens.welcome_screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.razzashoppingapp.view.navigation.NavScreen
import com.example.razzashoppingapp.R


@Composable
fun FirstPage(navController: NavController,
) {

    Column(
        modifier = Modifier
            .background(Color(0xFFB5A9A9))
            .fillMaxSize()


    ) {
        Image(
            painter = painterResource(id = R.drawable.firstpage),
            contentDescription = "Online Shopping Picture",
            modifier = Modifier
                //.padding(vertical = 200.dp, horizontal = 100.dp)
                .size(400.dp)

        )

        Text(
            text = "“My wardrobe is full, I don't need to go shopping”",
            color = Color.White,
            fontSize = 26.sp,
            fontStyle = FontStyle.Italic,
            fontWeight = FontWeight.ExtraBold,
            fontFamily = FontFamily.Serif,
            textAlign = TextAlign.Center
        )
        Text(
            text = "-Said no one ever",
            modifier = Modifier.padding(horizontal = 123.dp),
            color = Color.White,
            fontSize = 18.sp,
            fontFamily = FontFamily.Serif,
            //textAlign = TextAlign.Right
        )
        Row(
            modifier = Modifier
                .padding(vertical = 100.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
        ) {
            Button(
                colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFFF4F3EF)),
                shape = RoundedCornerShape(2.dp),
                modifier = Modifier
                    .width(130.dp)
                    .height(55.dp),
                onClick = { navController.navigate(NavScreen.Register.route) }) {
                Text(text = "Register ", color = Color.Black, fontSize = 20.sp)

            }
            Button(
                colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFFE5DFDB)),
                shape = RoundedCornerShape(2.dp),
                modifier = Modifier
                    .width(130.dp)
                    .height(55.dp),
                onClick = { navController.navigate(NavScreen.LogIn.route) }) {
                Text(text = "Log In ", color = Color.White, fontSize = 20.sp)

            }
        }
    }
}
