package edu.itschool.abitpro.ui.screen.list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import edu.ITSchool.abitpro.theme.White


@Composable
fun Helloscreen(onNavigateToSearch: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp), //TODO: заменить когда дизайнер скажет точные размеры
        verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Приложение готово к работе.\n" + "Остался финальный этап - настройка\n" + "фильтров для выдачи подходящих\n" + "Вам вузов. Рекомендуем указывать\n" + "достоверную информацию, чтобы\n" + "получить предложения, которые\n" + "подходят Вам лучше всего.\n" + "\n" + "Все указанные вами данные\n" + "храняться на вашем устройстве\n" + "локально и ни куда не передаются.",
            color = White,
            modifier = Modifier.padding(bottom = 16.dp), //Todo заменить
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
        )
        Button(

            modifier = Modifier.fillMaxSize(0.8f),
            shape = MaterialTheme.shapes.medium,
            onClick = { onNavigateToSearch() }
        ) {
            Text(text = "Начать поиск")
        }
    }

//    Scaffold(
//
//    ) { }


}

@Preview
@Composable
fun ShowHello() {
    MaterialTheme {
        Helloscreen({})

    }
}

