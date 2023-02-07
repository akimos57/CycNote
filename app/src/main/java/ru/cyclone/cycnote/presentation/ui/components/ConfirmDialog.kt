package ru.cyclone.cycnote.presentation.ui.components

import androidx.compose.material.AlertDialog
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable

@Composable
fun ConfirmDialog(
    showDialog: Boolean,
    requested : () -> Unit,
    onDismiss: () -> Unit = {},
) {
    if (showDialog) {
        AlertDialog(
            title = {
                Text("Вы уверены?")
            },
            onDismissRequest = onDismiss,
            confirmButton = {
                TextButton(
                    onClick = {
                        requested()
                        onDismiss()
                    }
                ) {
                    Text(text = "Да")
                }},
            dismissButton = {
                TextButton(
                    onClick = {
                        onDismiss()
                    }
                ) {
                    Text(text = "Нет")
                }
            },
            backgroundColor = MaterialTheme.colors.background
        )
    }
}