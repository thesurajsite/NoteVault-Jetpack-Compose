package com.surajverma.notevault.Screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.surajverma.notevault.R
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavController){
    Column(
        modifier= Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.color_primary)),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(painter = painterResource(id = R.drawable.logo), contentDescription = null, modifier = Modifier.padding(4.dp))
    }

    LaunchedEffect(key1 = true) {
        delay(2000)
        navController.navigate("home")

    }
}