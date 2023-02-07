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
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun Alert(showDialog: Boolean,
          removeRequested: () -> Unit = {},
          favouriteStateChanged: () -> Unit = {},
          onDismiss: () -> Unit = {},
          isFavourite : Boolean
) {
    if (showDialog) {
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
                        onDismiss()
                        removeRequested()
                    },
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    Text(
                        "Удалить заметку?",
                        color = MaterialTheme.colors.surface,
                        fontSize = 20.sp,
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
                            color = MaterialTheme.colors.surface,
                            fontSize = 20.sp,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 20.dp, top = 20.dp)
                        )
                        else -> Text("Добавить в избранное",
                            color = MaterialTheme.colors.surface,
                            fontSize = 20.sp,
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