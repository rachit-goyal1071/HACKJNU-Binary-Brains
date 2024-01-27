package com.example.multiplatformtest.android.components

import YourComposableFunction
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.multiplatformtest.android.screens.HospitalsList

@Composable
fun Navgraph(
    navController: NavHostController,
    innerPaddingValues: PaddingValues
) {
    NavHost(navController = navController, startDestination = Screens.Notifications.route){
        composable(Screens.Notifications.route){
            YourComposableFunction()
        }
        composable(Screens.Hospitals.route){
            HospitalsList()
        }
    }
}