package ru.cyclone.cycnote.presentation.screens.edit

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
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import net.yslibrary.android.keyboardvisibilityevent.KeyboardVisibilityEvent
import ru.cyclone.cycnote.domain.model.Note
import ru.cyclone.cycnote.presentation.navigation.Screens
import ru.cyclone.cycnote.presentation.ui.theme.backgroundColor
import ru.cyclone.cycnote.presentation.ui.theme.noteItem
import java.util.*


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun AddScreen(
    navController: NavController,
    id: String?
) {
    val viewModel = hiltViewModel<EditViewModel>()
    var title by rememberSaveable { mutableStateOf("") }
    var description by rememberSaveable { mutableStateOf("") }

    val note = viewModel.note.observeAsState().value
    id?.toLong()?.let { viewModel.getNoteById(id = it) }

    title = note?.title?:""
    description = note?.content?:""

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
                    .background(Color.White)
                    .padding(14.dp)
                    .fillMaxWidth()
                    .height(48.dp)

            ) {
                Box(
                    modifier = Modifier
                        .width(48.dp)
                        .height(48.dp)
                        .clip(RoundedCornerShape(15.dp))
                        .clickable {
                            val color: Int = noteItem.toArgb()
                            if (id != null) {
                                viewModel.addNote(
                                    Note(
                                        id = id.toLong(),
                                        title = title,
                                        content = description,
                                        backgroundColor = color
                                    )
                                ) {
                                    navController.navigate(Screens.MainScreen.rout)
                                }
                            } else {
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
                        }
//                        .clickable { navController.popBackStack() }


                ) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        tint = Color.Black,
                        contentDescription = "nav back",
                        modifier = Modifier
                            .align(Alignment.Center)
                            .height(30.dp)
                            .width(30.dp)
                    )
                }
                Box(
                    modifier = Modifier
                        .width(48.dp)
                        .height(48.dp)
                        .clip(RoundedCornerShape(15.dp))
                        .clickable {
                            val color: Int = noteItem.toArgb()
                            if (id != null) {
                                viewModel.addNote(
                                    Note(
                                        id = id.toLong(),
                                        title = title,
                                        content = description,
                                        backgroundColor = color
                                    )
                                ) {
                                    navController.navigate(Screens.MainScreen.rout)
                                }
                            } else {
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
                        }

                ) {
                    Icon(
                        imageVector = Icons.Filled.Check,
                        tint = Color.Black,
                        contentDescription = "save note",
                        modifier = Modifier
                            .align(Alignment.Center)
                            .height(33.dp)
                            .width(33.dp)

                    )
                }

            }
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .pointerInput(Unit) {
                    detectTapGestures(
                        onTap = { focusManager.clearFocus() }
                    )
                },
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White),
                value = title,
                onValueChange = { title = it },
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Color.White,
                    cursorColor = Color.Black,
                    disabledLabelColor = Color.White,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                ),
                textStyle = TextStyle(fontSize = 28.sp),
                placeholder = { Text(
                    text = "Название",
                    fontSize = 28.sp,
                    color = Color(0xFFC0C0C0),
                    )},
                singleLine = false,
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
            TextField(
                value = description,
                onValueChange = { description = it },
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Color.White,
                    cursorColor = Color.Black,
                    disabledLabelColor = Color.White,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                ),
                textStyle = TextStyle(fontSize = 22.sp),
                placeholder = { Text(
                    text = "Начните ввод",
                    fontSize = 22.sp,
                    color = Color(0xFFC0C0C0)
                )},
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 8.dp),
                keyboardOptions = KeyboardOptions(
                    capitalization = KeyboardCapitalization.Sentences,
                    autoCorrect = true
                )
            )
        }
    }
}















