package com.surajverma.notevault.Navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.surajverma.notevault.Model.AuthViewModel
import com.surajverma.notevault.Screens.EditProfile
import com.surajverma.notevault.Screens.HomeScreen
import com.surajverma.notevault.Screens.LoginScreen
import com.surajverma.notevault.Screens.ProfileScreen
import com.surajverma.notevault.Screens.SignupScreen
import com.surajverma.notevault.Screens.SplashScreen
import com.surajverma.notevault.Screens.UploadScreen

@Composable
fun NavGraph(){
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "login") {

        composable(route = "home"){
            HomeScreen(navController, AuthViewModel())
        }

        composable(route = "profile"){
            ProfileScreen(navController)
        }


        composable(route = "upload"){
            UploadScreen(navController)
        }

        composable(route= "splash"){
            SplashScreen(navController)
        }

        composable(route = "signup"){
            SignupScreen(navController, AuthViewModel())
        }

        composable(route= "login"){
            LoginScreen(navController)
        }

        composable(route= "editProfile"){
            EditProfile()
        }

    }
}