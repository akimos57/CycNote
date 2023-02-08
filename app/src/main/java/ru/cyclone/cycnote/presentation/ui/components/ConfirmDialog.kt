package ru.cyclone.cycnote.presentation.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.AlertDialog
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun ConfirmDialog(
    showDialog: Boolean,
    requested : () -> Unit,
    onDismiss: () -> Unit = {},
) {
    if (showDialog) {
        AlertDialog(
            modifier = Modifier
                .width(300.dp)
                .clip(RoundedCornerShape(14.dp)),
            title = {
                Text(
                    text = "Удалить заметку?",
                    style = MaterialTheme.typography.h6,
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier
                        .padding(start = 20.dp, top = 20.dp)

                )
            },
            onDismissRequest = onDismiss,
            confirmButton = {
                TextButton(
                    onClick = {
                        requested()
                        onDismiss()
                    }
                ) {
                    Text(
                        text = "Да",
                        style = MaterialTheme.typography.h6,
                        modifier = Modifier
                            .padding(start = 20.dp, top = 20.dp)
                    )
                }},
            dismissButton = {
                TextButton(
                    onClick = {
                        onDismiss()
                    }
                ) {
                    Text(
                        text = "Нет",
                        style = MaterialTheme.typography.h6,
                        modifier = Modifier
                            .padding(start = 20.dp, top = 20.dp)
                    )
                }
            },
            backgroundColor = MaterialTheme.colors.secondary
        )
    }
}