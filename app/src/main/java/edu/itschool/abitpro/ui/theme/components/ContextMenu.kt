package edu.ITSchool.abitpro.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp


data class ContextMenu(
    val label: String,
    val OnClick: () -> Unit,
    val icon: ImageVector? = null
)
@Composable
fun AppContextMenu(
    expanded: Boolean,
    OnDismiss: () -> Unit,
    actions: List<ContextMenu>
)

{
    // Основной контейнер меню от Material Design
    DropdownMenu(
        expanded = expanded,         // Синхронизируем видимость с состоянием
        onDismissRequest = onDismiss // Передаем наверх сигнал о закрытии
    ) {
        // Проходим циклом по нашему списку действий
        actions.forEach { action ->
            // Отрисовываем конкретный пункт
            DropdownMenuItem(
                // Текст пункта
                text = { Text(action.label) },

                // Обработка клика
                onClick = {
                    action.onClick() // Выполняем логику, переданную в MenuAction
                    onDismiss()      // Закрываем меню (важно!)
                },

                // Если иконка передана (не null), рисуем её слева от текста
                trailingIcon  = action.icon?.let {
                    { Icon(it, contentDescription = null) }
                }
            )
        }
    }
}





























