package edu.itschool.abitpro.ui.screen.list

import androidx.compose.foundation.background
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.R
import edu.itschool.abitpro.ui.theme.AppTheme
import edu.itschool.abitpro.ui.theme.DForeground1
import edu.itschool.abitpro.ui.theme.LBackground1
import edu.itschool.abitpro.ui.theme.LWhite1




@Composable
fun HelloScreen(onSearchClick: () -> Unit) {
    AppTheme {
        Column(
            modifier = Modifier
                .background(LBackground1)
                .fillMaxSize()
                .padding(24.dp), //TODO: заменить когда дизайнер скажет точные размеры
            verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Приложение готово к работе.\n" +          //TODO вставить константу из res.values.strings.hello_text
                        "Остался финальный этап - настройка\n" +
                        "фильтров для выдачи подходящих\n" +
                        "Вам вузов. Рекомендуем указывать\n" +
                        "достоверную информацию, чтобы\n" +
                        "получить предложения, которые\n" +
                        "подходят Вам лучше всего.\n" +
                        "\n" +
                        "Все указанные вами данные\n" +
                        "храняться на вашем устройстве\n" +
                        "локально и ни куда не передаются.",
                color = LWhite1,
                modifier = Modifier.padding(bottom = 16.dp), //Todo заменить
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
            )
            Button(

                modifier = Modifier.fillMaxSize(0.8f),
                shape = MaterialTheme.shapes.medium,
                onClick = { onSearchClick() }
            ) {
                Text(text = "Начать поиск")
            }
        }

    }

}

