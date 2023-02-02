package ru.cyclone.cycnote.presentation.screens.add

import android.annotation.SuppressLint
import android.app.Activity
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import net.yslibrary.android.keyboardvisibilityevent.KeyboardVisibilityEvent
import ru.cyclone.cycnote.domain.model.Note
import ru.cyclone.cycnote.presentation.navigation.Screens
import ru.cyclone.cycnote.presentation.ui.theme.CycNoteTheme
import ru.cyclone.cycnote.presentation.ui.theme.noteItem
import java.util.*


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun AddScreen(navController: NavController) {

    val viewModel = hiltViewModel<AddViewModel>()
    var title by rememberSaveable { mutableStateOf("") }
    var description by rememberSaveable { mutableStateOf("") }

    val focusManager = LocalFocusManager.current

    // Clear focus with keyboard back press listener
    KeyboardVisibilityEvent.setEventListener(
        LocalContext.current as Activity
    ) { keyboardState ->
        if (!keyboardState)
            focusManager.clearFocus()
    }

    Scaffold(
        topBar = {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .padding(4.dp)
                    .fillMaxWidth()
                    .height(48.dp)

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
                .fillMaxSize()
                .pointerInput(Unit) {
                    detectTapGestures(
                        onTap = { focusManager.clearFocus() }
                    )
                },
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TextField(
                value = title,
                onValueChange = { title = it },
                label = { Text(text = "Название") },
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    capitalization = KeyboardCapitalization.Sentences,
                    autoCorrect = true
                ),
                keyboardActions = KeyboardActions(
                    onDone = {
                        focusManager.clearFocus()
                    }
                )
            )
            OutlinedTextField(
                value = description,
                onValueChange = { description = it },
                label = { Text(text = "Начните ввод") },
                modifier = Modifier
                    .padding(top = 24.dp),
                keyboardOptions = KeyboardOptions(
                    capitalization = KeyboardCapitalization.Sentences,
                    autoCorrect = true
                )
            )
        }
    }
}

@Preview
@Composable
fun PreviewAddScreen() {
    CycNoteTheme{AddScreen(rememberNavController())}
}















