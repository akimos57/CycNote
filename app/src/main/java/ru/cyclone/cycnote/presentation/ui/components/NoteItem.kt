package ru.cyclone.cycnote.presentation.ui.components


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
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
                        modifier = Modifier
                            .width(300.dp),
                        text = title,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    if (isFavourite) {
                        Icon(
                            imageVector = Icons.Filled.Star,
                            tint = Color(0xFFFFD700),
                            contentDescription = "favourite")
                    }
                }
                Column(
                    modifier = Modifier
                        .padding(top = 50.dp)
                        .height(50.dp)
                        .width(340.dp)
                        .padding(horizontal = 16.dp)
                ) {
                    Text(
                        text = subtitle,
                        fontSize = 18.sp,
                        modifier = Modifier
                            .padding(bottom = 23.dp)
                    )
                }
            }
        }
    }
