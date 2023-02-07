package ru.cyclone.cycnote.presentation.ui.components

import androidx.compose.material.AlertDialog
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable

@Composable
fun Alert(showDialog: Boolean,
          removeRequested: () -> Unit = {},
          favouriteStateChanged: () -> Unit = {},
          onDismiss: () -> Unit = {},
          isFavourite : Boolean
) {
    if (showDialog) {
        AlertDialog(
            text = {
                Text("Item dialog")
            },
            onDismissRequest = onDismiss,
            buttons = {
                TextButton(onClick = {
                    onDismiss()
                    removeRequested()
                }) {
                    Text("Удалить заметку?")
                }
                TextButton(onClick = {
                    onDismiss()
                    favouriteStateChanged()
                }) {
                    when (isFavourite) {
                        true -> Text("Убрать из избранного")
                        else -> Text("Добавить в избранное")
                    }
                }
            }
        )
    }
}