package com.example.razzashoppingapp.view.navigation

import android.annotation.SuppressLint
import android.os.Build
import androidx.activity.ComponentActivity
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.razzashoppingapp.room.entity.Product
import com.example.razzashoppingapp.room.entity.User
import com.example.razzashoppingapp.view.screens.checkout_screens.Checkout2
import com.example.razzashoppingapp.view.screens.forgotPassword_screens.ForgotPassScreen1
import com.example.razzashoppingapp.view.screens.forgotPassword_screens.ForgotPassScreen2
import com.example.razzashoppingapp.view.screens.forgotPassword_screens.ForgotPassScreen3
import com.example.razzashoppingapp.view.screens.userProducts_screens.CartScreen
import com.example.razzashoppingapp.view.screens.userProducts_screens.WishListScreen
import com.example.razzashoppingapp.view.screens.welcome_screens.FirstPage
import com.example.razzashoppingapp.view.screens.welcome_screens.LogInScreen
import com.example.razzashoppingapp.view.screens.welcome_screens.RegisterScreen
import com.example.razzashoppingapp.view.screens.admin_screens.*
import com.example.razzashoppingapp.view.screens.checkout_screens.CheckOut1
import com.example.razzashoppingapp.view.screens.checkout_screens.Checkout3
import com.example.razzashoppingapp.view.screens.productInfo_screens.*
import com.example.razzashoppingapp.view.screens.userInfo_screens.*
import com.example.razzashoppingapp.viewmodel.AuthViewModel
import com.example.razzashoppingapp.viewmodel.RazzaViewModel
import com.google.firebase.auth.FirebaseAuth



@RequiresApi(Build.VERSION_CODES.O)
@SuppressLint("SuspiciousIndentation")
@Composable
fun NavGraph(navController: NavHostController,
             razzaViewModel: RazzaViewModel = viewModel(LocalContext.current as ComponentActivity),
) {
    val currentUser: User by razzaViewModel.user.collectAsState()
    val authViewModel: AuthViewModel =
        viewModel(viewModelStoreOwner = LocalContext.current as ComponentActivity)
    NavHost(
        navController = navController,
//        startDestination = NavScreen.First.route,
        startDestination = if (FirebaseAuth.getInstance().currentUser == null) NavScreen.First.route else NavScreen.Profile.route

    ) {


        composable(route = NavScreen.First.route) {
            FirstPage(navController)
        }
        composable(route = NavScreen.LogIn.route) {
            LogInScreen(navController)
        }
        composable(route = NavScreen.Register.route) {
            RegisterScreen(navController)
        }
        composable(route = NavScreen.ForgotPass1.route) {
            ForgotPassScreen1(navController)
        }
        composable(route = NavScreen.ForgotPass2.route) {
            ForgotPassScreen2(navController)
        }
        composable(route = NavScreen.ForgotPass3.route) {
            ForgotPassScreen3(navController)
        }

        composable(route = NavScreen.AllProducts.route) {
            if (authViewModel.user?.email == "admin@gmail.com")
                AllProductsAdmin(navController)
            else
                AllProductsScreen(navController)
        }
        composable(route = NavScreen.SingleProduct.route) {
            if (authViewModel.user?.email == "admin@gmail.com")
                SingleProductAdminScreen(navController)
            else
                SingleProductScreen(navController)
        }
        composable(route = NavScreen.Home.route) {
            if (authViewModel.user?.email == "admin@gmail.com")
                CategoryAdminScreen(navController)
            else
                CategoryScreen(navController)
        }

        composable(route = NavScreen.Profile.route) {
            if (authViewModel.user?.email == "admin@gmail.com")
                AdminProfileScreen(navController)
            else
            ProfileScreen(navController)
        }
        composable(route = NavScreen.Addresses.route) {
            Addresses(navController)
        }
        composable(route = NavScreen.Wishlist.route) {
            WishListScreen(navController)
        }
        composable(route = NavScreen.Cart.route) {
            CartScreen(navController)
        }
        /*composable(route = NavScreen.Back.route){
            navController.popBackStack()
        }*/
        composable(route = NavScreen.Checkout1.route) {
            CheckOut1(navController)
        }
        composable(route = NavScreen.Checkout2.route) {
            Checkout2(navController)
        }
        composable(route = NavScreen.Checkout3.route) {
            Checkout3(navController)
        }
        composable(route = NavScreen.Reviews.route) {
            ReviewsScreen(navController)
        }
        composable(route = NavScreen.MyOrder1.route)
        {
            MyOrders1(navController)
        }

        composable(route = NavScreen.AddProduct.route) {
            AddProductScreen(navController)
        }
        composable(route = NavScreen.AddCategory.route) {
            AddCategoryScreen(navController)
        }
        composable(route = NavScreen.MyOrder2.route) {
            MyOrders2(navController)
        }
        composable(route = NavScreen.Dashboard.route) {
            DashboardScreen(navController)
        }
//        composable(route = NavScreen.DashboardOrder.route) {
//            DashboardOrderScreen(navController)
//        }
        composable(
            route = NavScreen.DashboardOrder.route,
            arguments = listOf(navArgument("id") {
                type = NavType.IntType
            })
        ) {
            val id = it.arguments?.getInt("id")//,0)

            if (id != null) {
                DashboardOrderScreen(navController, id)
            }
        }
        composable(route = NavScreen.TrackOrder.route) {
            TrackOrder(navController)
        }
        composable(route = NavScreen.PostReview.route) {
            PostReviewsScreen(navController = navController)
        }
        composable(route = NavScreen.UpdateProduct.route) {
            UpdateProductScreen(navController = navController)
        }
        composable(route = NavScreen.UpdateReview.route) {
            UpdateReview(navController = navController)
        }
        composable(route = NavScreen.AddAddress.route) {
            AddAddressScreen(navController)
        }
        composable(route = NavScreen.UpdateAddress.route) {
           UpdateAddressScreen(navController = navController)
        }
        composable(route = NavScreen.Dash2.route) {
            Dash2(navController = navController)
        }
        composable(route = NavScreen.UpdateStatus.route) {
            UpdateStatus(navController = navController)
        }
        composable(route = NavScreen.AllOrders.route) {
            AllOrders(navController = navController)
        }
        composable(route = NavScreen.Reports.route) {
            ReportsScreen(navController)
        }
        composable(route = NavScreen.ReportsRequested.route) {
            Reports2(navController)
        }
    }
}
