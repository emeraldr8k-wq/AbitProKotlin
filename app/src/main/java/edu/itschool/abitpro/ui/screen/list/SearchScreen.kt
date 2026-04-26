package edu.itschool.abitpro.ui.screen.list

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import edu.itschool.abitpro.ui.theme.AppTheme
import edu.itschool.abitpro.domain.model.Hei


@Composable
fun SearchScreen(
    hei: List<Hei>,
    onHeiClick: (String) -> Unit,
    onSearchClick: () -> Unit = {} //Todo

) {
    Text("SearchScreen")
    AppTheme {
        LazyColumn {
            items(hei) { hei ->
                HeiItem(
                    hei = hei,
                    onClick = { onHeiClick(hei.id.toString()) }

                )
            }
        }

    }
}

@Composable
fun HeiItem(hei: Hei, onClick: () -> Unit) {
    Card(

    ) {
        TODO("Как будет выглядеть карточка вуза")
        Text("HeiCard")
    }

}