package com.surajverma.notevault.Screens

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.surajverma.notevault.Model.AuthState
import com.surajverma.notevault.Model.AuthViewModel
import com.surajverma.notevault.Model.ProfileViewModel
import com.surajverma.notevault.R

@Composable
fun SignupScreen(navController: NavController, authViewModel: AuthViewModel){

    var email by remember {
        mutableStateOf("")
    }

    var password by remember {
        mutableStateOf("")
    }

    var confirmPassword by remember {
        mutableStateOf("")
    }

    val authState = authViewModel.authState.observeAsState()
    val context = LocalContext.current

    LaunchedEffect(authState.value) {
        when(authState.value){
            is AuthState.Authenticated -> navController.navigate("home")
            is AuthState.Error -> Toast.makeText(context, (authState.value as AuthState.Error).message, Toast.LENGTH_SHORT).show()
            else -> Unit

        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.light_grey)),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {

        Image(painter = painterResource(id = R.drawable.logo_transparent), contentDescription = "null", colorFilter = ColorFilter.tint(Color.White))

        Spacer(modifier = Modifier.height(60.dp))

        Text(text = "Create Account", fontSize = 40.sp, color = Color.White)

        Spacer(modifier = Modifier.height(40.dp))

        OutlinedTextField(value = email,
            onValueChange = {email=it},
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 40.dp),
            colors = OutlinedTextFieldDefaults.colors(unfocusedBorderColor = Color.White,
                focusedBorderColor = colorResource(id = R.color.color_primary),
                focusedTextColor = Color.White,
                unfocusedTextColor = Color.White,
                focusedLabelColor = colorResource(id = R.color.color_primary),
                unfocusedLabelColor = Color.White),
            label = { Text(text = "Email") },
            // input size
            textStyle = TextStyle(
                fontSize = 20.sp
            )
        )

        Spacer(modifier = Modifier.height(20.dp))

        OutlinedTextField(value = password,
            onValueChange = {password=it},
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 40.dp),
            colors = OutlinedTextFieldDefaults.colors(focusedBorderColor = colorResource(id = R.color.color_primary),
                unfocusedBorderColor = Color.White,
                focusedTextColor = Color.White,
                unfocusedTextColor = Color.White,
                focusedLabelColor = colorResource(id = R.color.color_primary),
                unfocusedLabelColor = Color.White),
            label = { Text(text = "Password")},
            // input size
            textStyle = TextStyle(
                fontSize = 20.sp
            )
        )

        Spacer(modifier = Modifier.height(20.dp))

        OutlinedTextField(value = confirmPassword,
            onValueChange = {confirmPassword=it},
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 40.dp),
            colors = OutlinedTextFieldDefaults.colors(focusedBorderColor = colorResource(id = R.color.color_primary),
                unfocusedBorderColor = Color.White,
                focusedTextColor = Color.White,
                unfocusedTextColor = Color.White,
                focusedLabelColor = colorResource(id = R.color.color_primary),
                unfocusedLabelColor = Color.White),
            label = { Text(text = "Confirm Password")},
            // input size
            textStyle = TextStyle(
                fontSize = 20.sp
            )
        )

        Spacer(modifier = Modifier.height(20.dp))

        Button(onClick = {
            if(email.isNotEmpty() && password.isNotEmpty()) {
                if (password == confirmPassword){
                authViewModel.signup(email, password, context)
                }
                else{
                    Toast.makeText(context, "Password and Confirm Password must be same", Toast.LENGTH_SHORT).show()
                }
            }
        },
            colors = ButtonDefaults.buttonColors(colorResource(id = R.color.color_primary)),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 40.dp),
            shape = RoundedCornerShape(0.dp)
        ) {
            Text(text = "Create Account", fontSize = 20.sp)
        }

        Spacer(modifier = Modifier.height(50.dp))

        TextButton(onClick = {
            navController.navigate("login") {
                navController.popBackStack()
            }
        }) {
            Text(text = "Already Have an Account? Login Here", color = Color.White)
        }


    }

}

@Preview(showBackground = true)
@Composable
fun SignupScreenPreview() {
    val navController = rememberNavController()
    SignupScreen(navController, AuthViewModel(ProfileViewModel()))
}