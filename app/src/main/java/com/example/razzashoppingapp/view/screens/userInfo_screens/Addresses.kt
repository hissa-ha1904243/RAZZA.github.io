package com.example.razzashoppingapp.view.screens.userInfo_screens

import android.annotation.SuppressLint
import androidx.activity.ComponentActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.razzashoppingapp.BottomBar
import com.example.razzashoppingapp.R
import com.example.razzashoppingapp.TopBar
import com.example.razzashoppingapp.room.entity.Address
import com.example.razzashoppingapp.room.entity.User
import com.example.razzashoppingapp.view.navigation.NavScreen
import com.example.razzashoppingapp.viewmodel.AuthViewModel
import com.example.razzashoppingapp.viewmodel.RazzaViewModel

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun Addresses(
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
//        AddressList(AddressSampleData.addressesSample)
        Column(modifier = Modifier.fillMaxSize()) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            ) {

            }
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                        .background(Color.White),
                    contentAlignment = Alignment.Center
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "My Addresses",
                            color = Color(0xFF413232),
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center,
                            fontFamily = FontFamily.Serif,
                            fontSize = 24.sp,
                            modifier = Modifier.padding(horizontal = 10.dp)
                        )
                        RoundButton(
                            imageVector = Icons.Default.Add,
                            modifier = Modifier.padding(horizontal = 15.dp),
                            onClick = {navController.navigate(route = NavScreen.AddAddress.route) })
                    }
                }
                Divider(
                    color = Color(0xFFCACACA),
                    thickness = 1.dp,
                    modifier = Modifier.padding(20.dp)
                )
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(9f)
                        .background(Color.White)
                ) {
                    val addresses: List<Address> by razzaViewModel.addressesFB.collectAsState()

                    AddressList(navController,addresses)
                }
            }
        }
    }
}


@Composable
fun AddressCard(adr: Address, navController: NavHostController,
                razzaViewModel: RazzaViewModel = viewModel(LocalContext.current as ComponentActivity)
) {
    val currentUser: User by razzaViewModel.user.collectAsState()

    val authViewModel =
        viewModel<AuthViewModel>(viewModelStoreOwner = LocalContext.current as ComponentActivity)

    if(adr.userEmail==authViewModel.user?.email) {
    Row(modifier = Modifier.padding(all = 8.dp)) {
        Column(modifier = Modifier.clickable { }) {
            Text(
                text = "\t${adr.name} (${adr.city})",
                color = Color(0xFF745D5D),
                fontFamily = FontFamily.Serif
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "\tZone Number: ${adr.zoneNo.toString()}",
                modifier = Modifier.padding(all = 4.dp),
                style = MaterialTheme.typography.body2
            )
            Text(
                text = "\tStreet Number: ${adr.streetNo.toString()}",
                modifier = Modifier.padding(all = 4.dp),
                style = MaterialTheme.typography.body2
            )
            Text(
                text = "\tBuilding: ${adr.buildingNo.toString()}",
                modifier = Modifier.padding(all = 4.dp),
                style = MaterialTheme.typography.body2
            )
            Row() {
                Image(
                    painter = painterResource(id = R.drawable.edit),
                    contentDescription = "Edit Product",
                    modifier = Modifier
                        .size(30.dp)
                        .padding(1.dp)
                        .clickable {
                            razzaViewModel.setCurrentAddress(adr)
                            navController.navigate(NavScreen.UpdateAddress.route)
                        }
                )
                Image(
                    painter = painterResource(id = R.drawable.delete),
                    contentDescription = "Remove Product",
                    modifier = Modifier
                        .size(30.dp)
                        .padding(1.dp)
                        .clickable {
                            razzaViewModel.deleteAddress(adr)

                        }
                )
            }
            Divider(
                color = Color(0xFFCACACA),
                thickness = 1.dp,
                modifier = Modifier.padding(20.dp)
            )
        }

    }
}
}

@Composable
fun AddressList(navController: NavHostController, addresses:List<Address>) {

    if (addresses.isEmpty()) {
        Text("addresses list is empty")
    } else {
        LazyColumn {
            items(addresses)
            {
                AddressCard(it,navController)
            }

        }
    }
}

@Composable
fun RoundButton(
    modifier: Modifier = Modifier,
    imageVector: ImageVector,
    onClick: () -> Unit,
    elevation: Dp = 4.dp
) {
    Card(
        modifier
            .padding(2.dp)
            .clickable(onClick = onClick),
        shape = CircleShape,
        backgroundColor = MaterialTheme.colors.background,
        elevation = elevation
    ) {
        Icon(imageVector = imageVector, contentDescription = "+ or - icon", tint = Color.Black)
    }
}



























