package edu.ITSchool.abitpro

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.compose.rally.ui.accounts.SingleAccountScreen
import com.example.compose.rally.ui.bills.BillsScreen
import com.example.compose.rally.ui.bills.SingleBillScreen
import edu.ITSchool.abitpro.screens.AccountScreen
import edu.ITSchool.abitpro.screens.HelloScreen
import edu.itschool.abitpro.ui.screen.list.Home.HomeScreen

@Composable
fun AbitNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = Hello.route,
        modifier = modifier
    ) {
        composable(route = Hello.route) {
            HelloScreen(
                onSearchClick = {
                    navController.navigateSingleTopTo(Search.route)
                }
            )
        }
        composable(route = Home.route) {
            HomeScreen(
                onSearchClick = {
                    navController.navigateSingleTopTo(Search.route)
                }
            )
        }
        composable(route = Search.route) {
            HelloScreen(
                onSearchClick = {
                    navController.navigateSingleTopTo(Search.route)
                }
            )
        }
        composable(route = Account.route) {
            AccountScreen(
                onFavoritesClick = { heiNumber ->               //TODO переход к вузу из избранного
                    navController.navigateToSingleHei(heiNumber)
                }
            )
        }
        composable(
            route = SingleAccount.routeWithArgs,
            arguments = SingleAccount.arguments,
            deepLinks = SingleAccount.deepLinks
        )
        { navBackStackEntry ->
            val accountType =
                navBackStackEntry.arguments?.getString(SingleAccount.accountTypeArg)
            SingleAccountScreen(accountType)
        }
        composable(route = Bills.route) {
            BillsScreen(
                onBillClick = { billType ->
                    navController.navigateToSingleBill(billType)
                }
            )
        }
        composable(
            route = SingleBill.routeWithArgs,
            arguments = SingleBill.arguments

        ) { navBackStackEntry ->
            val billType =
                navBackStackEntry.arguments?.getString(SingleBill.billTypeArg)
            SingleBillScreen(billType)
        }
    }
}


private fun NavHostController.navigateToSingleHei(heiNumber: String) {
    this.navigateSingleTopTo("${SingleHei.route}/$heiNumber")
}


fun NavHostController.navigateSingleTopTo(route: String) =
    this.navigate(route) {
        launchSingleTop = true
        popUpTo(this@navigateSingleTopTo.graph.findStartDestination().id)
        { saveState = true }
        restoreState = true
    }



private fun NavHostController.navigateToSingleBill(billType: String) {
    this.navigateSingleTopTo("${SingleBill.route}/$billType")
}

