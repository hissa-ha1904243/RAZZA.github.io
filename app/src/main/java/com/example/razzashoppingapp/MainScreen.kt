package com.example.razzashoppingapp

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.razzashoppingapp.view.navigation.NavGraph
import com.example.razzashoppingapp.view.navigation.NavScreen

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MainScreen(){
    val navController = rememberNavController()
    NavGraph(navController = navController)
}

@Composable
fun TopBar(navController: NavHostController){
   /* val screens = listOf(
       // NavScreen.Back,
        NavScreen.Cart,


    )
*/
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    TopAppBar(
        backgroundColor = Color(0xFFB5A9A9),
        modifier = Modifier.fillMaxWidth()
    ){
        BackNav(navController)
        Spacer(modifier = Modifier.width(210.dp))
        CartNav(navController)
        /*screens.forEach { screen ->
            if (currentDestination != null) {
                AddItem(
                    screen = screen,
                    currentDestination = currentDestination,
                    navController = navController,
                )
            }

        }*/

    }
}

@Composable
fun BottomBar(navController : NavHostController){
    val screens = listOf(
        NavScreen.Home,
        NavScreen.Profile,
        NavScreen.Wishlist
    )

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    BottomNavigation(
        backgroundColor = Color(0xFFB5A9A9)
    ){
        screens.forEach { screen ->
            if (currentDestination != null) {
                AddItem(
                    screen = screen,
                    currentDestination = currentDestination,
                    navController = navController,
                )
            }
        }
    }
}

@Composable
fun RowScope.AddItem(
    screen: NavScreen,
    currentDestination : NavDestination,
    navController: NavHostController
){
    BottomNavigationItem(
        //label = {Text(text  = screen.title)},
        icon = { Icon(imageVector = screen.icon , contentDescription = "Navigation Icon", Modifier.size(35.dp))},
        selected = currentDestination.hierarchy.any {
            it.route == screen.route
        },
        onClick = {
            navController.navigate(screen.route){
                popUpTo(navController.graph.findStartDestination().id)
                launchSingleTop = true
            }
        },
        selectedContentColor = Color(0xFF745D5D),
        unselectedContentColor = Color(0xFF988989),
        alwaysShowLabel = false,
    )
}

@Composable
fun BackNav(navController: NavController) {
    Box(Modifier.clickable{
        navController.popBackStack()
    }, Alignment.TopStart) {
        Icon(
            painter = painterResource(id = R.drawable.back),
            contentDescription = "back arrow",
            Modifier
                .size(80.dp)
                .padding(10.dp),
            tint = Color(0xFF988989)
        )

    }
}

@Composable
fun CartNav(navController: NavController) {
    Box(Modifier.clickable{
        navController.navigate(NavScreen.Cart.route)
    }, Alignment.TopStart) {
        Icon(
            painter = painterResource(id = R.drawable.cart),
            contentDescription = "cart",
            Modifier
                .size(80.dp)
                .padding(10.dp),
            tint = Color(0xFF988989)
        )

    }
}