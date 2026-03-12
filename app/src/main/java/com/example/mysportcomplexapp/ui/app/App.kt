package com.example.mysportcomplexapp.ui.app

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.compose.material3.*
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

import com.example.mysportcomplexapp.ui.app.screens.AccueilScreen
import com.example.mysportcomplexapp.ui.app.screens.TennisScreen
import com.example.mysportcomplexapp.ui.app.screens.ResaScreen
import com.example.mysportcomplexapp.ui.app.screens.ClubScreen
import com.example.mysportcomplexapp.ui.app.screens.PlanningScreen
import com.example.mysportcomplexapp.ui.app.screens.ProfilScreen

@Composable
fun App(){
    val navController = rememberNavController()

    Scaffold(
        bottomBar = { BottomBar(navController) }
    ) { padding ->
        NavGraph(navController = navController,
            modifier = Modifier.padding(padding))

    }
}

@Composable
fun BottomBar(navController: NavController) {

    val items = listOf(
        BottomNavItem.Home,
        BottomNavItem.Club,
        BottomNavItem.Planning,
        BottomNavItem.Profile
    )

    NavigationBar {

        items.forEach { item ->

            NavigationBarItem(
                icon = { Icon(item.icon, contentDescription = item.label) },
                label = { Text(item.label) },
                selected = false,
                onClick = {
                    navController.navigate(item.route)
                }
            )
        }
    }
}

sealed class BottomNavItem(
    val route: String,
    val label: String,
    val icon: ImageVector
) {

    object Home : BottomNavItem("home","Accueil", Icons.Default.Home)
    object Club : BottomNavItem("club","Le club", Icons.Default.Info)
    object Planning : BottomNavItem("planning","Mon planning", Icons.Default.DateRange)
    object Profile : BottomNavItem("profile","Profil", Icons.Default.AccountCircle)
}

@Composable
fun NavGraph(navController: NavHostController, modifier: Modifier = Modifier) {

    NavHost(
        navController = navController,
        startDestination = "home",
        modifier = modifier
    ) {

        composable("home") { AccueilScreen(navController) }

        composable("tennis") { TennisScreen(navController) }

        composable("resa") { ResaScreen(navController) }

        composable("club") { ClubScreen() }

        composable("planning") { PlanningScreen() }

        composable("profile") { ProfilScreen() }
    }
}