package ru.cyclone.cycnote.presentation.screens.add

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.cyclone.cycnote.presentation.ui.theme.CycNoteTheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.toArgb
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import ru.cyclone.cycnote.domain.model.Note
import ru.cyclone.cycnote.presentation.navigation.Screens
import ru.cyclone.cycnote.presentation.ui.theme.noteItem
import java.util.*


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun AddScreen(navController: NavController) {

    val viewModel = hiltViewModel<AddViewModel>()
    var title by rememberSaveable { mutableStateOf("") }
    var description by rememberSaveable { mutableStateOf("") }

    Scaffold(
        topBar = {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .padding(top = 52.dp)
                    .fillMaxWidth()
                    .height(48.dp)
                    .padding(horizontal = 24.dp)

                ) {
                    Box(
                        modifier = Modifier
                            .width(48.dp)
                            .height(48.dp)
                            .clip(RoundedCornerShape(15.dp))
                            .background(Color(0xFF42AAFF))
                            .clickable { navController.popBackStack() }


                    ) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            tint = Color.White,
                            contentDescription = "nav back",
                            modifier = Modifier
                                .align(Alignment.Center)
                        )
                    }
                Box(
                    modifier = Modifier
                        .width(48.dp)
                        .height(48.dp)
                        .clip(RoundedCornerShape(15.dp))
                        .background(Color(0xFF42AAFF))


                ) {
                    Icon(
                        imageVector = Icons.Filled.Check,
                        tint = Color.White,
                        contentDescription = "save note",
                        modifier = Modifier
                            .align(Alignment.Center)
                            .clickable {
                                val color: Int = noteItem.toArgb()
                                viewModel.addNote(
                                    Note(
                                        title = title,
                                        content = description,
                                        backgroundColor = color
                                    )
                                ) {
                                    navController.navigate(Screens.MainScreen.rout)
                                }
                            }
                    )
                }

            }
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TextField(
                value = title,
                onValueChange = { title = it },
                label = { Text(text = "Название") }
            )
            TextField(
                value = description,
                onValueChange = { description = it },
                label = { Text(text = "Начните ввод") },
                modifier = Modifier
                    .padding(top = 24.dp)
            )
        }
    }
}

@Preview
@Composable
fun previewAddScreen() {
    CycNoteTheme{AddScreen(rememberNavController())}

}















