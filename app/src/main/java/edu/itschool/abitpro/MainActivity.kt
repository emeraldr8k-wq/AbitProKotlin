package edu.itschool.abitpro

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import edu.ITSchool.abitpro.AbitNavHost
import edu.ITSchool.abitpro.AbitTabRowScreens
import edu.ITSchool.abitpro.Home
import edu.ITSchool.abitpro.theme.AppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AbitApp()
        }
    }
}


@Composable
fun AbitApp() {
    AppTheme {
        val navController = rememberNavController()

        val currentBackStack by navController.currentBackStackEntryAsState()
        // Fetch your currentDestination:
        val currentDestination = currentBackStack?.destination
        // ...
        val currentScreen = AbitTabRowScreens.find {
            it.route == currentDestination?.route
        } ?: Home
        Scaffold(

            bottomBar = {
                AbitBottomBar(navController)
            }
//            topBar = {
//                RallyTabRow(
//                    allScreens = rallyTabRowScreens,
//                    onTabSelected = { newScreen ->
//                        navController
//                            .navigateSingleTopTo(newScreen.route)
//                    },
//                    currentScreen = currentScreen
//                )
//            }
        ) { innerPadding ->
            AbitNavHost(
                navController = navController,
                modifier = Modifier.padding(innerPadding)
            )


        }
    }
}


@Composable
fun AbitBottomBar(navController: NavHostController) {
    val currentBackStack by navController.currentBackStackEntryAsState()
    val curRoute = currentBackStack?.destination?.route


    NavigationBar {
        AbitTabRowScreens.forEach { screen ->
            NavigationBarItem(
                selected = curRoute == screen.route,
                onClick = {
                    navController.navigate(screen.route) {
                        popUpTo(navController.graph.startDestinationId){
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                icon = {
                    screen.icon
                }
            )
        }

    }


}
