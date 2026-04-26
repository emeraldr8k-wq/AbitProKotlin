package edu.itschool.abitpro

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import edu.itschool.abitpro.domain.model.Hei
import edu.itschool.abitpro.ui.screen.list.AccountScreen
import edu.itschool.abitpro.ui.screen.list.HelloScreen
import edu.itschool.abitpro.ui.screen.list.Home.HomeScreen
import edu.itschool.abitpro.ui.screen.list.SearchScreen
import edu.itschool.abitpro.ui.screen.list.SingleHeiScreen


@Composable
fun AbitNavHost(
    navController: NavHostController, modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = Home.route,
        modifier = modifier
    )

    {
        composable(route = Hello.route) {
            HelloScreen(
                onSearchClick = {
                    navController.navigateSingleTopTo(Search.route)
                })
        }
        composable(route = Home.route) {
            HomeScreen(
                onSearchClick = {
                    navController.navigateSingleTopTo(Search.route)
                })
        }
        composable(route = Account.route) {
            AccountScreen(
//                onHeiClick = { heiId ->
//                    navController.navigateToSingleHei(heiId.toString())
//                }
            )
        }
        composable(route = Search.route) {
            val HeiList = listOf<Hei>(
                //TODO убрать когда бэк даст пример списка
                Hei(1, "СПБГУ"),
                Hei(2, "МГУ"),
                Hei(3, "ВГУ"),
                Hei(4, "ВГТУ"),
            )
            SearchScreen(
                hei = HeiList,
                onHeiClick = { heiId ->
                    navController.navigateToSingleHei(heiId.toString())
                }
//                onSearchClick = {
//                    navController.navigateSingleTopTo(Search.route)
//                }
            )
        }

//        composable(
//            route = SingleHei.routeWithArgs,
//            arguments = SingleHei.arguments
//        )
//        { navBackStackEntry ->
//            val heiId =
//                navBackStackEntry.arguments?.getString(SingleHei.heiTypeArg)
//            SingleHeiScreen(heiId.toString())
//        }


//        composable(route = Bills.route) {
//            BillsScreen(
//                onBillClick = { billType ->
//                    navController.navigateToSingleBill(billType)
//                }
//            )

////        composable(
////            route = SingleBill.routeWithArgs,
////            arguments = SingleBill.arguments
////
////        ) { navBackStackEntry ->
////            val billType =
////                navBackStackEntry.arguments?.getString(SingleBill.billTypeArg)
////            SingleBillScreen(billType)
////        }
    }
}

fun NavHostController.navigateSingleTopTo(route: String) = this.navigate(route) {
    launchSingleTop = true
    popUpTo(this@navigateSingleTopTo.graph.findStartDestination().id) { saveState = true }
    restoreState = true
}

fun NavHostController.navigateToSingleHei(heiId: String) {
    this.navigateSingleTopTo("${SingleHei.route}/$heiId")
}


//private fun NavHostController.navigateToSingleBill(billType: String) {
//    this.navigateSingleTopTo("${SingleBill.route}/$billType")
//}

