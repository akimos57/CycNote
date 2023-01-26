package ru.cyclone.cycnote.presentation.ui.components


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp



@Composable
    fun NoteItem(title: String,subtitle: String, backgroundColor: Color, modifier: Modifier) {
        Column(modifier = modifier) {

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(100.dp)
                        .clip(RoundedCornerShape(10.dp))
                        .background(backgroundColor)
                ) {
                    Column(
                        modifier = Modifier
                            .height(50.dp)
                    ) {


                    Text(
                        text = title,
                        fontSize = 25.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF303030),
                        modifier = Modifier
                            .padding(vertical = 10.dp)
                            .padding(horizontal = 16.dp)

                    )

                    }
                    Column(
                        modifier = Modifier
                            .padding(top = 50.dp)
                            .height(50.dp)
                    ) {
                        Text(
                            text = subtitle,
                            fontSize = 20.sp,
                            color = Color(0xFF303030),
                            modifier = Modifier
                                .padding(bottom = 23.dp)
                                .padding(horizontal = 16.dp)
                        )
                    }

                }


        }
    }
