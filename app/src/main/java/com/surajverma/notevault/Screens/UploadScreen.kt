package com.surajverma.notevault.Screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.surajverma.notevault.Navigation.BottomNavigationBar
import com.surajverma.notevault.R

@Composable
fun UploadScreen(navController: NavController){

    Scaffold(
        bottomBar = {
            BottomNavigationBar(navController)
        }
    ) { padding->

        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .fillMaxWidth()
                .background(colorResource(id = R.color.light_grey)),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            Text(text = "Upload Screen", fontSize = 30.sp, color = Color.White)

        }



    }

}