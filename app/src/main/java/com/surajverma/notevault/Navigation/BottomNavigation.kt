package com.surajverma.notevault.Navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.surajverma.notevault.R
import kotlinx.coroutines.selects.select

// Sealed class for Botoom Navigation Bar
sealed class BottomNavItem(val route: String, val icon: ImageVector, val label: String){
    object Home: BottomNavItem("home", Icons.Default.Home, "Home")
    object Upload: BottomNavItem("upload", Icons.Default.KeyboardArrowUp, "Upload")
    object Profile: BottomNavItem("profile", Icons.Default.Person, "Profile")
}

// Bottom Navigation Bar
@Composable
fun BottomNavigationBar(navController: NavController){
    val items = listOf(
        BottomNavItem.Home,
        BottomNavItem.Upload,
        BottomNavItem.Profile
    )

    NavigationBar(
        containerColor = colorResource(id = R.color.dark_grey),
    ) {
        items.forEach { item ->

            val isSelected = navController.currentBackStackEntryAsState().value?.destination?.route == item.route

            NavigationBarItem(
                icon = { Icon(item.icon,
                        contentDescription = item.label,
                        tint = if(isSelected) Color.Black else colorResource(id = R.color.color_primary)
                    ) },
                selected = navController.currentBackStackEntryAsState().value?.destination?.route == item.route,
                label = { Text(item.label, color = if (isSelected) Color.White else colorResource(id = R.color.color_primary)) },
                onClick = {
                    navController.navigate(item.route) {
                        popUpTo(navController.graph.startDestinationId)
                        launchSingleTop = true
                    }
                }
            )
        }
    }
}