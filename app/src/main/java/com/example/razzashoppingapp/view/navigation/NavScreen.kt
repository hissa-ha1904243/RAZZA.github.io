package com.example.razzashoppingapp.view.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.vector.ImageVector

sealed class NavScreen(
    val route : String,
    val title : String,
    val icon : ImageVector
)

{

    object LogIn: NavScreen(
        route = "login_screen",
        title = "Login Screen",
        icon = Icons.Default.Home
    )
    object Register: NavScreen(
        route = "register_screen",
        title = "Register Screen",
        icon = Icons.Default.Home
    )
    object ForgotPass1: NavScreen(
        route = "forgotPass1_screen",
        title = "Forgot Password Screen2",
        icon = Icons.Default.Home
    )
    object ForgotPass2: NavScreen(
        route = "forgotPass2_screen",
        title = "Forgot Password Screen2",
        icon = Icons.Default.Home
    )
    object ForgotPass3: NavScreen(
        route = "forgotPass3_screen",
        title = "Forgot Password Screen3",
        icon = Icons.Default.Home
    )
    object Home: NavScreen(
        route = "home",
        title = "Home",
        icon = Icons.Default.Home
    )
//    object AdminHome: NavScreen(
//        route = "admin_home",
//        title = "Admin Home",
//        icon = Icons.Default.Home
//    )
    object First : NavScreen(
        route = "first",
        title = "First",
        icon = Icons.Default.Home
    )
//
    object Profile : NavScreen(
        route = "profile",
        title = "Profile",
        icon = Icons.Default.Person
    )

    object Addresses : NavScreen(
        route = "addresses",
        title = "Addresses",
        icon = Icons.Default.Info
    )

    object Wishlist : NavScreen(
        route = "wishlist",
        title = "Wishlist",
        icon = Icons.Default.Favorite
    )
//
    object Cart : NavScreen(
        route = "cart",
        title = "Cart",
        icon = Icons.Default.ShoppingCart
    )

    object AllProducts: NavScreen(
        route = "allProducts_screen",
        title = "All Products Screen",
        icon = Icons.Default.Info

    )
    object SingleProduct: NavScreen(
        route = "singleProduct_screen",
        title = "Single Product Screen",
        icon = Icons.Default.Info

    )

    object Checkout1: NavScreen(
        route = "checkout1_screen",
        title = "Check out 1 Screen",
        icon = Icons.Default.Info

    )
    object Checkout2: NavScreen(
        route = "checkout2_screen",
        title = "Check out 2 Screen",
        icon = Icons.Default.Info
    )
    object Checkout3: NavScreen(
        route = "checkout3_screen",
        title = "Check out 3 Screen",
        icon = Icons.Default.Info
    )
    object Reviews: NavScreen(
        route = "reviews_screen",
        title = "Reviews Screen",
        icon = Icons.Default.Info
    )
    object PostReview: NavScreen(
        route = "postReview_screen",
        title = "PostReview Screen",
        icon = Icons.Default.Info
    )
    object MyOrder1: NavScreen(
        route = "myOrder1_screen",
        title = "MyOrder1 Screen",
        icon = Icons.Default.Info
    )
    object MyOrder2: NavScreen(
        route = "myOrder2_screen",
        title = "MyOrder2 Screen",
        icon = Icons.Default.Info
    )
    object AddProduct: NavScreen(
        route = "addProduct_screen",
        title = "Add Product Screen",
        icon = Icons.Default.Info
    )
    object AddCategory: NavScreen(
        route = "addCategory_screen",
        title = "Add Category Screen",
        icon = Icons.Default.Info
    )
    object Dashboard: NavScreen(
        route = "dashboard_screen",
        title = "Dashboard Screen",
        icon = Icons.Default.Info
    )
    object DashboardOrder: NavScreen(
        route = "dashboardOrder/{id}",
        title = "Dashboard Order Screen",
        icon = Icons.Default.Info
    )
    object Reports: NavScreen(
        route = "reports_screen",
        title = "Reports Screen",
        icon = Icons.Default.Info
    )

    object TrackOrder: NavScreen(
        route = "trackOrder_screen",
        title = "TrackOrder Screen",
        icon = Icons.Default.Info
    )
    object UpdateProduct: NavScreen(
        route = "updateProduct_screen",
        title = "UpdateProduct Screen",
        icon = Icons.Default.Info
    )
    /*object Back:NavScreen(
        route = "back",
        title = "back to previous screen",
        icon = Icons.Default.ArrowBack
    )*/
    object UpdateReview: NavScreen(
        route = "updateReview_screen",
        title = "UpdateReview Screen",
        icon = Icons.Default.Info
    )
    object AddAddress: NavScreen(
        route = "addAddress_screen",
        title = "AddAddress Screen",
        icon = Icons.Default.Info
    )
    object UpdateAddress: NavScreen(
        route = "updateAddress_screen",
        title = "UpdateAddress Screen",
        icon = Icons.Default.Info
    )
    object Dash2: NavScreen(
        route = "dash2_screen",
        title = "Dash2 Screen",
        icon = Icons.Default.Info
    )
    object UpdateStatus: NavScreen(
        route = "updateStatus_screen",
        title = "UpdateStatus Screen",
        icon = Icons.Default.Info
    )
    object ReportsRequested: NavScreen(
        route = "reportsRequested_screen",
        title = "ReportsRequested Screen",
        icon = Icons.Default.Home
    )
    object AllOrders: NavScreen(
        route = "allOrders_screen",
        title = "AllOrders Screen",
        icon = Icons.Default.Info
    )


}