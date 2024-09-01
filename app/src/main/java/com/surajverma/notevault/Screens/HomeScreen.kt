package com.surajverma.notevault.Screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.surajverma.notevault.AuthState
import com.surajverma.notevault.AuthViewModel
import com.surajverma.notevault.Navigation.BottomNavigationBar
import com.surajverma.notevault.R

@Composable
fun HomeScreen(navController: NavController, authViewModel: AuthViewModel){

    val authState = authViewModel.authState.observeAsState()

    LaunchedEffect(authState.value) {
        when(authState.value){
            is AuthState.Unauthenticated -> navController.navigate("login")
            else -> Unit
        }
    }

    Scaffold(
        bottomBar = {
            BottomNavigationBar(navController)
        }

    ){ innerPadding ->

        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .fillMaxWidth()
                .background(colorResource(id = R.color.light_grey)),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {



            Text(text = "Home Screen", fontSize = 30.sp, color = Color.White)

            TextButton(onClick = {
                authViewModel.signout()
            }) {
                Text(text = "SignOut", fontSize = 25.sp, color = Color.White)
            }

        }

    }


}
