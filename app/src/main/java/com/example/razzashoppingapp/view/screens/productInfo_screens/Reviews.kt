package com.example.razzashoppingapp.view.screens.productInfo_screens

import android.annotation.SuppressLint
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.razzashoppingapp.BottomBar
import com.example.razzashoppingapp.R
import com.example.razzashoppingapp.TopBar
import com.example.razzashoppingapp.room.entity.Product
import com.example.razzashoppingapp.room.entity.Review
import com.example.razzashoppingapp.room.entity.User
import com.example.razzashoppingapp.viewmodel.AuthViewModel
import com.example.razzashoppingapp.viewmodel.RazzaViewModel

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ReviewsScreen(navController: NavHostController,
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
        //val reviewRepository = ReviewRepository()
        //val cardReviews = reviewRepository.getReviews(LocalContext.current)
        //  var _cardproducts = rememberSaveable { mutableListOf(cardproducts) }
        //ReviewsScreenContent(cardReviews, navController)
        val reviews: List<Review> by razzaViewModel.reviewsFB.collectAsState()
        val users: List<User> by razzaViewModel.usersFB.collectAsState()

        ReviewsScreenContent(reviews,users,navController)
    }


}

@SuppressLint("SuspiciousIndentation")
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ReviewsScreenContent(reviews: List<Review>,users:List<User>, navController: NavHostController,
                         razzaViewModel: RazzaViewModel = viewModel(LocalContext.current as ComponentActivity)
) {
    Column(){
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
                Divider(color = Color(0xFFCACACA), thickness = 1.dp, modifier = Modifier.padding(20.dp))

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
                Divider(color = Color(0xFFCACACA), thickness = 1.dp, modifier = Modifier.padding(20.dp))


            LazyColumn( modifier = Modifier
                .padding(8.dp)
                .weight(0.5f)) {
                items(reviews) { review ->
                    if(review.pid==currentProduct.id){
                        ReviewCard(review,users, navController)
                    }

                }

            }

        }
       }
}

@Composable
fun ReviewCard(review: Review, users:List<User>,navController: NavHostController,razzaViewModel: RazzaViewModel = viewModel(LocalContext.current as ComponentActivity)){
    Card(
        backgroundColor = Color.White,
        modifier = Modifier
            .fillMaxWidth(),
    ) {

        Row(modifier = Modifier.padding(15.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.black_profile),
                contentDescription ="profile pic",
            modifier = Modifier.size(50.dp))
            Spacer(modifier = Modifier.padding(10.dp))
            //val userName= razzaViewModel.getUser(review.uid).

//            val user: Job =razzaViewModel.getUser(review.uid)

            var username="";
            for(user in users){
                Log.d("MSG","${user.email}${review.uEmail}")
                //razzaViewModel.setCurrentUser(user)
                //val currentUser: User by razzaViewModel.user.collectAsState()
                if(user.email==review.uEmail){
                    username=user.username.toString()
                }

            }



            var isExpanded by remember { mutableStateOf(false) }
            val surfaceColor by animateColorAsState(targetValue = if (isExpanded) MaterialTheme.colors.primary else MaterialTheme.colors.surface)
            Column {
                Row() {
                    Text(
                        text = username,
                        color = Color(0xFF413232),
                        style = MaterialTheme.typography.subtitle2
                    )
                    Spacer(modifier = Modifier.width(180.dp))
                    Row(){
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
}//Card end
