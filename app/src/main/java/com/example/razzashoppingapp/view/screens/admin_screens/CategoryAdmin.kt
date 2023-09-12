package com.example.razzashoppingapp.view.screens.productInfo_screens

import android.annotation.SuppressLint
import androidx.activity.ComponentActivity
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.razzashoppingapp.view.navigation.NavScreen
import com.example.razzashoppingapp.viewmodel.RazzaViewModel
import com.example.razzashoppingapp.BottomBar
import com.example.razzashoppingapp.TopBar
import com.example.razzashoppingapp.room.entity.Category
import com.example.razzashoppingapp.viewmodel.AuthViewModel

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun CategoryAdminScreen(navController: NavHostController, razzaViewModel: RazzaViewModel = viewModel(LocalContext.current as ComponentActivity)
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
//        val categoryRepository = CategoryRepository()
//        val categories = categoryRepository.getCategories(LocalContext.current)
        val categories: List<Category> by razzaViewModel.categoriesFB.collectAsState()
        CategoryAdminLayout(categories,navController)

    }
}


@Composable
fun CategoryAdminLayout(categories: List<Category>, navController: NavHostController) {

    addCategory(navController)
        Column(
            modifier = Modifier
                .padding(vertical = 20.dp)
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
                text = "CATEGORIES",
                color = Color.Black,
                fontSize = 27.sp,
                textAlign = TextAlign.Center
            )
            Divider(color = Color(0xFF8D8D8D), thickness = 1.dp, modifier = Modifier.padding(20.dp))


    }

    Column() {
        Spacer(modifier = Modifier.height(130.dp))
        MyAdminCategories(categories, navController)
    }

}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MyAdminCategories(categories: List<Category>, navController: NavHostController){

    if (categories.isEmpty()) {
        Text("Loading Categories failed.")
    } else {
        LazyColumn {
            items(categories) { item ->
                AdminCategoryCard(item, navController)
            }
        }


    }
    }



@Composable
fun addCategory(navController: NavHostController) {
    Box(modifier = Modifier.fillMaxSize()) {

        FloatingActionButton(
            modifier = Modifier
                .padding(horizontal = 8.dp, vertical = 20.dp)
                .size(50.dp),
            onClick = { navController.navigate(NavScreen.AddCategory.route) },
            backgroundColor = Color.LightGray
        )

        {
            Icon(imageVector = Icons.Filled.Add, contentDescription = "Add")
        }

    }

}

@Composable
fun AdminCategoryCard(category: Category, navController: NavHostController,
                 razzaViewModel: RazzaViewModel = viewModel(LocalContext.current as ComponentActivity)
) {
    Card() {
        Column(
            modifier = Modifier
                .padding(vertical = 20.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(5.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row() {
                FloatingActionButton(
                    modifier = Modifier
                        .padding(horizontal = 8.dp, vertical = 20.dp)
                        .size(30.dp),
                    onClick = {razzaViewModel.deleteCategory(category)},
                    backgroundColor = Color.LightGray
                )

                {
                    Icon(imageVector = Icons.Filled.Delete, contentDescription = "Add")
                }


                Button(
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF745D5D)),
                    shape = RoundedCornerShape(5.dp),
                    modifier = Modifier
                        .width(290.dp)
                        .height(70.dp),
                    onClick = { razzaViewModel.setCurrentCategory(category)
                        navController.navigate(NavScreen.AllProducts.route) })
                {
                    Text(text = "${category.name}", color = Color.White, fontSize = 22.sp)
                }
            }



        }
    }
}
