package ru.cyclone.cycnote.presentation.ui.components


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp



@Composable
    fun NoteItem(
    title: String,
    subtitle: String,
    backgroundColor: Color,
    modifier: Modifier,
    isFavourite : Boolean
) {
    Column(modifier = modifier) {
        Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .clip(RoundedCornerShape(14.dp))
                    .background(backgroundColor)
            ) {
                Row(
                    modifier = Modifier
                        .height(50.dp)
                        .padding(horizontal = 16.dp)
                        .padding(vertical = 10.dp)
                ) {
                    Text(
                        text = title,
                        fontSize = 25.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF303030)
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    if (isFavourite) {
                        Icon(
                            imageVector = Icons.Filled.Check,
                            contentDescription = "favourite")
                    }
                }
                Column(
                    modifier = Modifier
                        .padding(top = 50.dp)
                        .height(50.dp)
                        .padding(horizontal = 16.dp)
                ) {
                    Text(
                        text = subtitle,
                        fontSize = 20.sp,
                        color = Color(0xFF303030),
                        modifier = Modifier
                            .padding(bottom = 23.dp)
                    )
                }
            }
        }
    }
