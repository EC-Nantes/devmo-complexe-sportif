package com.example.mysportcomplexapp.ui.app

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.compose.material3.*
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.getValue
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController

import com.example.mysportcomplexapp.ui.app.screens.AccueilScreen
import com.example.mysportcomplexapp.ui.app.screens.TennisScreen
import com.example.mysportcomplexapp.ui.app.screens.ResaScreen
import com.example.mysportcomplexapp.ui.app.screens.ClubScreen
import com.example.mysportcomplexapp.ui.app.screens.PlanningScreen
import com.example.mysportcomplexapp.ui.app.screens.ProfilScreen
import com.example.mysportcomplexapp.ui.app.theme.BlueIndicator
import com.example.mysportcomplexapp.ui.app.theme.Transparent
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

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    val items = listOf(
        BottomNavItem.Home,
        BottomNavItem.Club,
        BottomNavItem.Planning,
        BottomNavItem.Profile
    )

    NavigationBar {

        items.forEach { item ->

            val isSelected = item.route == currentRoute

            NavigationBarItem(
                icon = {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {

                        Box(
                            modifier = Modifier
                                .height(4.dp)
                                .width(24.dp)
                                .background(
                                    color = if (isSelected) BlueIndicator.copy(alpha = 0.3f)
                                    else Transparent,
                                    shape = RoundedCornerShape(2.dp)
                                )
                        )

                        Spacer(modifier = Modifier.height(4.dp))
                        Icon(item.icon, contentDescription = item.label)
                    }
                       },
                label = { Text(item.label) },
                selected = isSelected,
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