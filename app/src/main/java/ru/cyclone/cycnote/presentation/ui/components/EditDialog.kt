package ru.cyclone.cycnote.presentation.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.AlertDialog
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp

@Composable
fun EditDialog(showDialog: Boolean,
          removeRequested: () -> Unit = {},
          favouriteStateChanged: () -> Unit = {},
          onDismiss: () -> Unit = {},
          isFavourite : Boolean
) {
    val confirmDialogShow = remember { mutableStateOf(false) }
    if (showDialog) {
        ConfirmDialog(
            requested = {
                removeRequested()
                onDismiss()
            },
            showDialog = confirmDialogShow.value,
            onDismiss = { confirmDialogShow.value = false },
        )
        AlertDialog(
            modifier = Modifier
                .height(150.dp)
                .width(300.dp)
                .clip(RoundedCornerShape(14.dp)),
            onDismissRequest = onDismiss,
            backgroundColor = MaterialTheme.colors.secondary,
            buttons = {
                TextButton(
                    onClick = {
                        confirmDialogShow.value = true
                    },
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    Text(
                        "Удалить заметку",
                        style = MaterialTheme.typography.h6,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 20.dp, top = 20.dp)
                    )
                }
                TextButton(
                    onClick = {
                        onDismiss()
                        favouriteStateChanged()
                    }) {
                    when (isFavourite) {
                        true -> Text("Убрать из избранного",
                            style = MaterialTheme.typography.h6,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 20.dp, top = 20.dp)
                        )
                        else -> Text("Добавить в избранное",
                            style = MaterialTheme.typography.h6,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 20.dp, top = 20.dp)
                        )
                    }
                }
            }
        )
    }
}

