package com.surajverma.notevault.Screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.surajverma.notevault.Model.ProfileModel
import com.surajverma.notevault.Model.ProfileViewModel
import com.surajverma.notevault.R

@Composable
fun EditProfile(){

    val profileViewModel : ProfileViewModel = viewModel()
    val context = LocalContext.current

    var name by remember {
        mutableStateOf("")
    }

    var college by remember {
        mutableStateOf("")
    }

    var enrollment by remember {
        mutableStateOf("")
    }

    var branch by remember {
        mutableStateOf("")
    }

    var year by remember {
        mutableStateOf("")
    }

    var phone by remember {
        mutableStateOf("")
    }

    Scaffold()
    {
        padding ->

        Column(modifier = Modifier
            .padding()
            .fillMaxSize()
            .background(colorResource(id = R.color.dark_grey)),
            horizontalAlignment = Alignment.CenterHorizontally)
        {
            Spacer(modifier = Modifier.height(80.dp))

            Text(text = "Edit Profile",
                fontSize = 30.sp,
                color = Color.White
            )

            Spacer(modifier = Modifier.height(50.dp))

            OutlinedTextField(value = name, onValueChange = {name=it},
                modifier = Modifier.fillMaxWidth(0.8f),
                label = { Text(text = "Name")},
                textStyle = TextStyle(fontSize = 20.sp),
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedBorderColor = Color.White,
                    focusedBorderColor = colorResource(id = R.color.color_primary),
                    focusedLabelColor = colorResource(id = R.color.color_primary),
                    unfocusedLabelColor = Color.White,
                    focusedTextColor = Color.White,
                    unfocusedTextColor = Color.White
                )
            )

            Spacer(modifier = Modifier.height(10.dp))

            OutlinedTextField(value = college, onValueChange = {college=it},
                modifier = Modifier.fillMaxWidth(0.8f),
                label = { Text(text = "college")},
                textStyle = TextStyle(fontSize = 20.sp),
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedBorderColor = Color.White,
                    focusedBorderColor = colorResource(id = R.color.color_primary),
                    focusedLabelColor = colorResource(id = R.color.color_primary),
                    unfocusedLabelColor = Color.White,
                    focusedTextColor = Color.White,
                    unfocusedTextColor = Color.White
                )
            )

            Spacer(modifier = Modifier.height(10.dp))

            OutlinedTextField(value = enrollment, onValueChange = {enrollment=it},
                modifier = Modifier.fillMaxWidth(0.8f),
                label = { Text(text = "Enrollment No.")},
                textStyle = TextStyle(fontSize = 20.sp),
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedBorderColor = Color.White,
                    focusedBorderColor = colorResource(id = R.color.color_primary),
                    focusedLabelColor = colorResource(id = R.color.color_primary),
                    unfocusedLabelColor = Color.White,
                    focusedTextColor = Color.White,
                    unfocusedTextColor = Color.White
                )
            )

            Spacer(modifier = Modifier.height(10.dp))

            OutlinedTextField(value = branch, onValueChange = {branch=it},
                modifier = Modifier.fillMaxWidth(0.8f),
                label = { Text(text = "Branch")},
                textStyle = TextStyle(fontSize = 20.sp),
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedBorderColor = Color.White,
                    focusedBorderColor = colorResource(id = R.color.color_primary),
                    focusedLabelColor = colorResource(id = R.color.color_primary),
                    unfocusedLabelColor = Color.White,
                    focusedTextColor = Color.White,
                    unfocusedTextColor = Color.White
                )
            )

            Spacer(modifier = Modifier.height(10.dp))

            OutlinedTextField(value = year, onValueChange = {year=it},
                modifier = Modifier.fillMaxWidth(0.8f),
                label = { Text(text = "Year")},
                textStyle = TextStyle(fontSize = 20.sp),
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedBorderColor = Color.White,
                    focusedBorderColor = colorResource(id = R.color.color_primary),
                    focusedLabelColor = colorResource(id = R.color.color_primary),
                    unfocusedLabelColor = Color.White,
                    focusedTextColor = Color.White,
                    unfocusedTextColor = Color.White
                )
            )

            Spacer(modifier = Modifier.height(10.dp))

            OutlinedTextField(value = phone, onValueChange = {phone=it},
                modifier = Modifier.fillMaxWidth(0.8f),
                label = { Text(text = "Phone")},
                textStyle = TextStyle(fontSize = 20.sp),
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedBorderColor = Color.White,
                    focusedBorderColor = colorResource(id = R.color.color_primary),
                    focusedLabelColor = colorResource(id = R.color.color_primary),
                    unfocusedLabelColor = Color.White,
                    focusedTextColor = Color.White,
                    unfocusedTextColor = Color.White
                )
            )

            Spacer(modifier = Modifier.height(20.dp))

            Button(onClick = {

                profileViewModel.saveProfile(ProfileModel(name, college, enrollment, branch, year, phone), context)
            },
                modifier = Modifier
                    .fillMaxWidth(0.8f),
                colors = ButtonDefaults.buttonColors(colorResource(id = R.color.color_primary)),
                shape = RectangleShape
            )
            {
                Text(text = "SAVE", color = Color.Black)
            }

        }

    }

}

@Preview(showBackground = true)
@Composable
fun EditProfilePreview(){
    EditProfile()
}