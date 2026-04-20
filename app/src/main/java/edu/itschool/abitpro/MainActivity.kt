package edu.itschool.abitpro

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import edu.ITSchool.abitpro.AbitNavHost
import edu.ITSchool.abitpro.theme.AppTheme
import edu.itschool.abitpro.ui.theme.AbitProTheme

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
        val currentScreen = rallyTabRowScreens.find {
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
            AbitBottomBar(navController)


        }
    }
}



@Composable
fun AbitBottomBar(navController: NavHostController){
    BottomAppBar()


//    TODO переменные


}
