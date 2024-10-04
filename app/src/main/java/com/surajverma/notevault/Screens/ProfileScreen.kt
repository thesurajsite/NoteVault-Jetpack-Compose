package com.surajverma.notevault.Screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.surajverma.notevault.Model.AuthState
import com.surajverma.notevault.Model.AuthViewModel
import com.surajverma.notevault.Navigation.BottomNavigationBar
import com.surajverma.notevault.ProfileMenu
import com.surajverma.notevault.Model.ProfileModel
import com.surajverma.notevault.Model.ProfileViewModel
import com.surajverma.notevault.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(navController: NavController, authViewModel: AuthViewModel){

  //  val authViewModel : AuthViewModel = viewModel()
    val authState = authViewModel.authState.observeAsState()
    val profileViewModel : ProfileViewModel = viewModel()
    val context = LocalContext.current
    val profile = profileViewModel.userProfileData.observeAsState(null)      // as soon as profileViewModel() gets data, it will update profileScreen
    val profileModel : ProfileModel = ProfileModel()

    // as soon as ProfileScreen opens, it will call getProfileData() and fetch data into the profileViewModel
    LaunchedEffect(key1 = true) {
        profileViewModel.getProfile(context)
    }


    // for logout
    LaunchedEffect(authState.value) {
        when(authState.value){
            is AuthState.Unauthenticated -> navController.navigate("login")
            else -> Unit
        }
    }

    LaunchedEffect(key1 = true) {
        profileViewModel.getProfile(context)
    }

    Scaffold(
        bottomBar = {
            BottomNavigationBar(navController = navController)
        },

        topBar = {
            TopAppBar(title = { /*TODO*/ },
                colors = TopAppBarDefaults.topAppBarColors(colorResource(id = R.color.dark_grey)),
                actions = {
                    ProfileMenu(navController)
                })
        }

    ) { padding->

        Column(
            modifier = Modifier
                .padding()
                .fillMaxHeight()
                .fillMaxWidth()
                .background(colorResource(id = R.color.dark_grey)),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {

            Spacer(modifier = Modifier.height(50.dp))

            Box(modifier = Modifier  //box2
                .size(100.dp)
                .zIndex(1f)
                .offset(y = 60.dp)) {

                Image(
                    painter = painterResource(id = R.drawable.profile),
                    contentDescription = "profile",
                    modifier = Modifier.fillMaxSize()
                )
            }

            Column(modifier = Modifier
                .width(400.dp)
                .fillMaxHeight()
                .padding(horizontal = 15.dp, vertical = 10.dp)
                .clip(RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp))
                .background(colorResource(id = R.color.light_grey)),
                horizontalAlignment = Alignment.CenterHorizontally
            ){

                Spacer(modifier = Modifier.height(60.dp))

                // name
                Text(text = profile.value?.name ?:"---",
                    fontSize = 30.sp,
                    color = Color.White
                )

                Spacer(modifier = Modifier.height(20.dp))

                //college name
                Text(text = profile.value?.collegeName?:"-----",
                    fontSize = 25.sp,
                    color = Color.White,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .border(1.dp, Color.White, shape = RectangleShape)
                        .padding(2.dp)
                        .fillMaxWidth(0.9f)

                )

                Spacer(modifier = Modifier.height(10.dp))

                // enrollment
                Text(text = profile.value?.enrollment?:"-----",
                    fontSize = 25.sp,
                    color = Color.White,
                    modifier = Modifier
                        .border(1.dp, Color.White)
                        .clip(RoundedCornerShape(2.dp))
                        .padding(2.dp)
                        .fillMaxWidth(0.9f),
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(10.dp))

                Row(modifier = Modifier.fillMaxWidth(0.9f)){

                    // branch
                    Text(text = profile.value?.branch?:"----",
                        fontSize = 25.sp,
                        color = Color.White,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .border(1.dp, Color.White)
                            .padding(2.dp)
                            .weight(1f)
                    )

                    Spacer(modifier = Modifier.width(5.dp))

                    // year
                    Text(text = profile.value?.year?:"---",
                        fontSize = 25.sp,
                        color = Color.White,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .border(1.dp, Color.White)
                            .padding(2.dp)
                            .fillMaxWidth()
                            .weight(1f)
                    )

                }

                Spacer(modifier = Modifier.height(10.dp))

                // phone
                Text(text = profile.value?.phone?:"----------",
                    fontSize = 25.sp,
                    color = Color.White,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .border(1.dp, Color.White)
                        .padding(2.dp)
                        .fillMaxWidth(0.9f)
                )

                Spacer(modifier = Modifier.height(20.dp))

                Button(onClick = {
                    authViewModel.signout()
                },
                    colors = ButtonDefaults.buttonColors(Color.Red),
                    shape = RectangleShape,
                    modifier = Modifier.fillMaxWidth(0.9f)
                ) {
                    Text(text = "LOGOUT",
                        fontSize = 20.sp,
                        color = Color.White)
                }
            }


        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProfileScreenPreview(){
    val navController= rememberNavController()
    ProfileScreen(navController = navController, AuthViewModel(ProfileViewModel()))
}