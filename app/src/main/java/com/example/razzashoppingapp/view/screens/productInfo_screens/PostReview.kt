package com.example.razzashoppingapp.view.screens.productInfo_screens

import android.annotation.SuppressLint
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.razzashoppingapp.BottomBar
import com.example.razzashoppingapp.R
import com.example.razzashoppingapp.view.navigation.NavScreen
import com.example.razzashoppingapp.TopBar
import com.example.razzashoppingapp.room.entity.Product
import com.example.razzashoppingapp.room.entity.Review
import com.example.razzashoppingapp.room.entity.User
import com.example.razzashoppingapp.viewmodel.AuthViewModel
import com.example.razzashoppingapp.viewmodel.RazzaViewModel

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun PostReviewsScreen(
    navController: NavHostController,
    razzaViewModel: RazzaViewModel = viewModel(LocalContext.current as ComponentActivity)

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
        val reviews: List<Review> by razzaViewModel.reviewsFB.collectAsState()
        val users: List<User> by razzaViewModel.usersFB.collectAsState()
        PostReviewsScreenContent(reviews, users, navController)
    }
}

@OptIn(ExperimentalFoundationApi::class, ExperimentalComposeUiApi::class)
@Composable
fun PostReviewsScreenContent(
    reviews: List<Review>, users: List<User>, navController: NavHostController,
    razzaViewModel: RazzaViewModel = viewModel(LocalContext.current as ComponentActivity)
) {

    Column() {
        val currentProduct: Product by razzaViewModel.product.collectAsState()
        var count=0
        var avgRate=0.0
        for(r in reviews){
            if(r.pid==currentProduct.id){
                count++
                avgRate=(avgRate+r.rating)
            }
        }
        avgRate=(avgRate/count)
        if (reviews.isEmpty()) {
            Text("There is no Reviews for this product!")
        } else {
            var countItems: Int = reviews.size
            Box(
                Modifier
                    .height(700.dp)
                    .fillMaxWidth()
                    .weight(1f)
            )
            {
                Surface(
                    Modifier
                        .height(700.dp)
                        .fillMaxWidth()
                ) {

                    LazyColumn(
                        modifier = Modifier
                            .padding(8.dp)
                        //.weight(0.5f)
                    ) {
                        stickyHeader {
                            Text(
                                text = "${currentProduct.title}",
                                color = Color(0xFF8D8D8D),
                                modifier = Modifier
                                    .background(Color.White)
                                    .padding(10.dp, 10.dp, 10.dp, 1.dp)
                                    .fillMaxWidth(),
                                fontSize = 15.sp,
                                textAlign = TextAlign.Center,
                                style = MaterialTheme.typography.body2,
                            )
                            Divider(
                                color = Color(0xFFCACACA),
                                thickness = 1.dp,
                                modifier = Modifier.padding(20.dp)
                            )
                            Text(
                                text = "${avgRate}",
                                color = Color.Black,
                                modifier = Modifier
                                    .background(Color.White)
                                    .padding(10.dp, 10.dp, 10.dp, 1.dp)
                                    .fillMaxWidth(),
                                fontSize = 30.sp,
                                textAlign = TextAlign.Center,
                                style = MaterialTheme.typography.subtitle1,
                                fontWeight = FontWeight.ExtraBold
                            )
                            Text(
                                text = "Based on ${count} reviews",
                                color = Color(0xFF8D8D8D),
                                modifier = Modifier
                                    .background(Color.White)
                                    .padding(10.dp, 10.dp, 10.dp, 1.dp)
                                    .fillMaxWidth(),
                                fontSize = 15.sp,
                                textAlign = TextAlign.Center,
                                style = MaterialTheme.typography.body2,
                            )
                            Text(
                                text = "Excellent",
                                color = Color(0xFF8D8D8D),
                                modifier = Modifier
                                    .background(Color.White)
                                    .padding(30.dp, 10.dp, 10.dp, 1.dp)
                                    .fillMaxWidth(),
                                fontSize = 15.sp,
                                textAlign = TextAlign.Left,
                                style = MaterialTheme.typography.body2,
                            )
                            Text(
                                text = "Average",
                                color = Color(0xFF8D8D8D),
                                modifier = Modifier
                                    .background(Color.White)
                                    .padding(30.dp, 10.dp, 10.dp, 1.dp)
                                    .fillMaxWidth(),
                                fontSize = 15.sp,
                                textAlign = TextAlign.Left,
                                style = MaterialTheme.typography.body2,
                            )
                            Text(
                                text = "Poor",
                                color = Color(0xFF8D8D8D),
                                modifier = Modifier
                                    .background(Color.White)
                                    .padding(30.dp, 10.dp, 10.dp, 1.dp)
                                    .fillMaxWidth(),
                                fontSize = 15.sp,
                                textAlign = TextAlign.Left,
                                style = MaterialTheme.typography.body2,
                            )
                            Divider(
                                color = Color(0xFFCACACA),
                                thickness = 1.dp,
                                modifier = Modifier.padding(20.dp)
                            )
                        }
                        items(reviews) { review ->
                            if(review.pid==currentProduct.id){
                                PostReviewCard(review,users, navController)
                            }

                        }

                    }
                }

            }
            Column(modifier = Modifier.height(220.dp)) {
                var expanded by remember { mutableStateOf(false) }
                val rating = listOf("1", "2", "3", "4")
                var selectedText by remember { mutableStateOf("") }
                var textfieldSize by remember { mutableStateOf(Size.Zero) }
                val icon = if (expanded)
                    Icons.Filled.KeyboardArrowUp
                else
                    Icons.Filled.KeyboardArrowDown
                var reviewState = remember { mutableStateOf("") }
                val keyboardController = LocalSoftwareKeyboardController.current
                Row() {
                    TextField(
                        value = reviewState.value,
                        onValueChange = { reviewState.value = it },
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
                            onValueChange = { selectedText = it },
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
                val authViewModel =
                    viewModel<AuthViewModel>(viewModelStoreOwner = LocalContext.current as ComponentActivity)

                val currentUser: User by razzaViewModel.user.collectAsState()
                Box(modifier = Modifier.fillMaxWidth()) {
                    Button(
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = Color(
                                0xFF745D5D
                            )
                        ),
                        shape = RoundedCornerShape(5.dp),
                        modifier = Modifier
                            .width(200.dp)
                            .height(60.dp)
                            .padding(0.dp)
                            .align(Alignment.Center),
                        onClick = {

                            val review = Review(text = reviewState.value, rating = selectedText.toInt(), pid = currentProduct.id, uEmail = authViewModel.user?.email.toString())
                            razzaViewModel.addReview(review)
                            reviewState.value = ""
                        }) {
                        Text(text = "Post Review", color = Color.White, fontSize = 15.sp)
                    }
                }

            }
        }
    }

}
@Composable
fun PostReviewCard(review: Review, users:List<User>,navController: NavHostController,razzaViewModel: RazzaViewModel = viewModel(LocalContext.current as ComponentActivity)) {
    Card(
        backgroundColor = Color.White,
        modifier = Modifier
            .fillMaxWidth(),
    ) {
        val currentUser: User by razzaViewModel.user.collectAsState()

        Row(
            modifier = Modifier.padding(15.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.black_profile),
                contentDescription = "profile pic",
                modifier = Modifier.size(50.dp)
            )
            Spacer(modifier = Modifier.padding(10.dp))
            //val userName= razzaViewModel.getUser(review.uid).

//            val user: Job =razzaViewModel.getUser(review.uid)

            var username = "";
            for (user in users) {
                Log.d("MSG", "${user.id}${review.uEmail}")
                //razzaViewModel.setCurrentUser(user)
                //val currentUser: User by razzaViewModel.user.collectAsState()
                if (user.email == review.uEmail) {
                    username = user.username.toString()
                }

            }

            val authViewModel =
                viewModel<AuthViewModel>(viewModelStoreOwner = LocalContext.current as ComponentActivity)

            var isExpanded by remember { mutableStateOf(false) }
            val surfaceColor by animateColorAsState(targetValue = if (isExpanded) MaterialTheme.colors.primary else MaterialTheme.colors.surface)
            Column {
                Row() {
                    Text(
                        text = username,
                        color = Color(0xFF413232),
                        style = MaterialTheme.typography.subtitle2
                    )
                    Spacer(modifier = Modifier.width(100.dp))
                    if(review.uEmail==authViewModel.user?.email) {
                        Image(
                            painter = painterResource(id = R.drawable.edit),
                            contentDescription = "Edit Review",
                            modifier = Modifier
                                .size(30.dp)
                                .padding(1.dp)
                                .clickable {
                                    razzaViewModel.setCurrentReview(review)
                                    navController.navigate(NavScreen.UpdateReview.route)
                                }
                        )
                        Image(
                            painter = painterResource(id = R.drawable.delete),
                            contentDescription = "Remove Review",
                            modifier = Modifier
                                .size(30.dp)
                                .padding(1.dp)
                                .clickable {
                                    razzaViewModel.deleteReview(review)

                                }
                        )
                    }
                    Spacer(modifier = Modifier.width(50.dp))
                    Row() {
                        Text(
                            text = "${review.rating}★",
                            //modifier = Modifier.padding(4.dp),
                            maxLines = if (isExpanded) Int.MAX_VALUE else 1,
                            overflow = TextOverflow.Ellipsis,
                            style = MaterialTheme.typography.body2,
                            color = Color(0xFF8D8D8D)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(4.dp))
                Surface(
                    shape = MaterialTheme.shapes.medium,
                    color = Color.White,
                    modifier = Modifier
                        .animateContentSize()
                        .padding(1.dp)
                        .clickable { isExpanded = !isExpanded }
                ) {


                    Text(
                        text = "${review.text}",
                        //modifier = Modifier.padding(4.dp),
                        maxLines = if (isExpanded) Int.MAX_VALUE else 1,
                        overflow = TextOverflow.Ellipsis,
                        style = MaterialTheme.typography.body2,
                        color = Color(0xFF8D8D8D)
                    )


                }

                Spacer(modifier = Modifier.width(70.dp))

            }
            Spacer(modifier = Modifier.height(40.dp))


        }
    }


}

