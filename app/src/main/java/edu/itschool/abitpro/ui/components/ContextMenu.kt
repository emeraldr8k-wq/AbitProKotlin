package edu.ITSchool.abitpro.components

import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector


data class ContextMenu(
    val label: String,
    val OnClick: () -> Unit,
    val icon: ImageVector? = null
)
//@Composable
//fun AppContextMenu(
//    expanded: Boolean,
//    OnDismiss: () -> Unit,
//    actions: List<ContextMenu>
//)

//{
    // Основной контейнер меню от Material Design
//    DropdownMenu(
//        expanded = expanded         // Синхронизируем видимость с состоянием
//        onDismissRequest = //TODO onDismiss // Передаем наверх сигнал о закрытии
//    ) {
//        // Проходим циклом по нашему списку действий
//        actions.forEach { action ->
//            // Отрисовываем конкретный пункт
//            DropdownMenuItem(
//                // Текст пункта
//                text = { Text(action.label) },
//
//                // Обработка клика
//                onClick = {
//                    action.onClick() // Выполняем логику, переданную в MenuAction
//                    // TODO onDismiss()      // Закрываем меню (важно!)
//                },
//
//                // Если иконка передана (не null), рисуем её слева от текста
//                trailingIcon  = action.icon?.let {
//                    { Icon(it, contentDescription = null) }
//                }
//            )
//        }
//    }
//}





























