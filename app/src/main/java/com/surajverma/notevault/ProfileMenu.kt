package com.surajverma.notevault

import androidx.compose.foundation.layout.Box
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController

@Composable
fun ProfileMenu(navController: NavController){

    var expanded by remember { mutableStateOf(false)}

    Box {
        IconButton(onClick = { expanded = true }) {
            Icon(Icons.Default.MoreVert, tint = Color.White ,contentDescription = "profile menu")
        }

        DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false })
        {
            DropdownMenuItem(
                text = { Text(text = "Edit Profile") },
                onClick = { navController.navigate("editProfile") },
                leadingIcon = {Icon(Icons.Default.Edit, contentDescription = "null")})

        }
    }
}