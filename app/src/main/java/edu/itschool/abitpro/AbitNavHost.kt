package edu.ITSchool.abitpro

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import edu.itschool.abitpro.ui.screen.list.AccountScreen
import edu.itschool.abitpro.ui.screen.list.Home.HomeScreen
import edu.itschool.abitpro.ui.screen.list.SearchScreen

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
//            Helloscreen (
////                onSearchClick = {
////                    navController.navigateSingleTopTo(Search.route)
////                }
//            )
        }
        composable(route = Home.route) {
            HomeScreen(
//                onSearchClick = {
//                    navController.navigateSingleTopTo(Search.route)
//                }
            )
        }
        composable(route = Search.route) {
            SearchScreen(
                onSearchClick = {
                    navController.navigateSingleTopTo(Search.route)
                }
            )
        }
        composable(route = Account.route) {
            AccountScreen(
//                onFavoritesClick = { heiNumber ->               //TODO переход к вузу из избранного
//                    navController.navigateToSingleHei(heiNumber)
//                }
            )
        }
        composable(
            route = SingleHei.routeWithArgs,
            arguments = SingleHei.arguments,
            deepLinks = SingleHei.deepLinks
        )
        { navBackStackEntry ->
            val accountType =
                navBackStackEntry.arguments?.getString(SingleHei.accountTypeArg)
//            SingleHeiScreen(accountType)
        }
//        composable(route = Bills.route) {
//            BillsScreen(
//                onBillClick = { billType ->
//                    navController.navigateToSingleBill(billType)
//                }
//            )
    }
//        composable(
//            route = SingleBill.routeWithArgs,
//            arguments = SingleBill.arguments
//
//        ) { navBackStackEntry ->
//            val billType =
//                navBackStackEntry.arguments?.getString(SingleBill.billTypeArg)
//            SingleBillScreen(billType)
//        }
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


//private fun NavHostController.navigateToSingleBill(billType: String) {
//    this.navigateSingleTopTo("${SingleBill.route}/$billType")
//}

