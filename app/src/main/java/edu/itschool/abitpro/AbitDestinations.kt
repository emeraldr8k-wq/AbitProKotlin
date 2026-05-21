/*
 * Copyright 2022 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package edu.itschool.abitpro

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavType
import androidx.navigation.navArgument

/**
 * Contract for information needed on every Abit navigation destination
 */
interface AbitDestination {
    val icon: ImageVector
    val route: String

}

/**
 * Abit app navigation destinations
 */
object Home : AbitDestination {
    override val icon = Icons.Filled.Home
    override val route = "homes"

}

object Account : AbitDestination {
    override val icon = Icons.Filled.AccountCircle
    override val route = "hei"

}

object Hello : AbitDestination {
    override val icon = Icons.Filled.Home
    override val route = "hello"

}

object Search : AbitDestination {
    override val icon = Icons.Filled.Search
    override val route = "search"

}

object SingleHei : AbitDestination {     //Todo экран для единичного вуза
    // Added for simplicity, this icon will not in fact be used, as Singlehei isn't
    // part of the AbitTabRow selection
    override val icon = Icons.Filled.Home
    override val route = "single_hei"
    const val heiTypeArg = "hei_type"

    val routeWithArgs = "${route}/{heiId}"
    val arguments = listOf(
        navArgument(heiTypeArg)
        { type = NavType.StringType }
    )


}


// Screens to be displayed in the top AbitTabRow
val AbitTabRowScreens = listOf(Home, Account, Search)
