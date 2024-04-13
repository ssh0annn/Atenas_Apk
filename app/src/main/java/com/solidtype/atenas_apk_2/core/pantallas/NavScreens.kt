package com.solidtype.atenas_apk_2.core.pantallas

import android.content.Context
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.solidtype.atenas_apk_2.Authentication.presentation.home.component.HomeScreen
import com.solidtype.atenas_apk_2.Authentication.presentation.login.component.Container
import com.solidtype.atenas_apk_2.Authentication.presentation.register.OutlinedTextFieldExample

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Navigation() {
    val context = LocalContext.current

    val navController = rememberNavController()

    NavHost(

        navController = navController,
        startDestination = Screens.Login.route

    ){
        composable(route = Screens.Login.route ) {
            Container(context,navController)
        }
        composable(
            route = Screens.Register.route,
            arguments = listOf(
                navArgument(
                    name = "userId"
                ) {
                    type = NavType.IntType
                    defaultValue = -1
                }
            )
        ) {
            OutlinedTextFieldExample(navController)
        }
        composable(route = Screens.Home.route ) {
            HomeScreen(navController)
        }

    }

}
