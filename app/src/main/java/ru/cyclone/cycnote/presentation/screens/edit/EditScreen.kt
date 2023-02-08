package ru.cyclone.cycnote.presentation.screens.edit

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
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.saveable.autoSaver
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import net.yslibrary.android.keyboardvisibilityevent.KeyboardVisibilityEvent
import ru.cyclone.cycnote.domain.model.Note
import ru.cyclone.cycnote.presentation.navigation.Screens
import ru.cyclone.cycnote.presentation.ui.components.EditDialog
import ru.cyclone.cycnote.presentation.ui.theme.noteItem


@Composable
fun EditScreen(
    navController: NavController,
    id: String?
) {
    navController.enableOnBackPressed(true)
    val viewModel = hiltViewModel<EditViewModel>()
    var title by rememberSaveable { mutableStateOf("") }
    var description by rememberSaveable { mutableStateOf("") }

    val showDialog = remember { mutableStateOf(false) }

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
        modifier = Modifier.background(MaterialTheme.colors.primary),
        topBar = {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(14.dp)
                    .height(48.dp)
            ) {
                Box(
                    modifier = Modifier
                        .width(48.dp)
                        .height(48.dp)
                        .clip(RoundedCornerShape(15.dp))
                        .clickable {
//                            val color: Int = noteItem.toArgb()
//                            if (note != null) {
//                                viewModel.addNote(
//                                    Note(
//                                        noteID = note.noteID,
//                                        title = title,
//                                        content = description,
//                                        backgroundColor = color,
//                                        isFavourite = note.isFavourite
//                                    )
//                                ) {
//                                    navController.navigate(Screens.MainScreen.rout)
//                                }
//                            }
                            val color: Int = noteItem.toArgb()
                            if (id != null) {
                                if (note != null) {
                                    viewModel.addNote(
                                        Note(
                                            noteID = id.toLong(),
                                            title = title,
                                            content = description,
                                            backgroundColor = color,
                                            isFavourite = note.isFavourite
                                        )
                                    ) {
                                        navController.navigate(Screens.MainScreen.rout)
                                    }
                                }
                            } else {
                                viewModel.addNote(
                                    Note(
                                        title = title,
                                        content = description,
                                        backgroundColor = color,
                                        isFavourite = false
                                    ),
                                ) {
                                    navController.navigate(Screens.MainScreen.rout)
                                }
                            }
                            focusManager.clearFocus(false)

                        }
                ) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = "nav back",
                        modifier = Modifier
                            .align(Alignment.Center)
                            .height(30.dp)
                            .width(30.dp)
                    )
                }
                Spacer(Modifier.weight(1f, true))
                Box(
                    modifier = Modifier
                        .width(48.dp)
                        .height(48.dp)
                        .clip(RoundedCornerShape(15.dp))
                        .clickable {
                            val color: Int = noteItem.toArgb()
                            if (id != null) {
                                if (note != null) {
                                    viewModel.addNote(
                                        Note(
                                            noteID = id.toLong(),
                                            title = title,
                                            content = description,
                                            backgroundColor = color,
                                            isFavourite = note.isFavourite
                                        )
                                    ) {
                                        navController.navigate(Screens.MainScreen.rout)
                                    }
                                }
                            } else {
                                viewModel.addNote(
                                    Note(
                                        title = title,
                                        content = description,
                                        backgroundColor = color,
                                        isFavourite = false
                                    ),
                                ) {
                                    navController.navigate(Screens.MainScreen.rout)
                                    focusManager.clearFocus(false)
                                }
                            }
                            focusManager.clearFocus(false)
                        }

                ) {
                    Icon(
                        imageVector = Icons.Filled.Check,
                        contentDescription = "save note",
                        modifier = Modifier
                            .align(Alignment.Center)
                            .height(33.dp)
                            .width(33.dp)
                    )
                }
                if (note != null) {
                    Box(
                        modifier = Modifier
                            .width(48.dp)
                            .height(48.dp)
                            .clip(RoundedCornerShape(15.dp))
                            .clickable {
                                showDialog.value = true
                            }
                    ) {
                        Icon(
                            imageVector = Icons.Filled.MoreVert,
                            contentDescription = "menu",
                            modifier = Modifier
                                .align(Alignment.Center)
                                .height(33.dp)
                                .width(33.dp)

                        )
                    }
                }
            }
        }
    ) { paddingValues ->
        if (note != null) {
            EditDialog(
                showDialog = showDialog.value,
                onDismiss = { showDialog.value = false },
                isFavourite = note.isFavourite,
                removeRequested = {
                    viewModel.deleteNote(note = note)
                    navController.navigate(Screens.MainScreen.rout)
                },
                favouriteStateChanged = {
                    viewModel.changeFavouriteState(note = note)
                }
            )
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
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
                    .fillMaxWidth(),
                value = title,
                onValueChange = { title = it },
                colors = TextFieldDefaults.textFieldColors(
                    cursorColor = MaterialTheme.colors.secondary,
                    disabledLabelColor = MaterialTheme.colors.secondary,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    backgroundColor = MaterialTheme.colors.background
                ),
                textStyle = TextStyle(fontSize = 22.sp, fontWeight = FontWeight.Medium),
                placeholder = {
                    Text(
                        text = "Название",
                        fontSize = 22.sp
                    )
                },
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
                    cursorColor = MaterialTheme.colors.secondary,
                    disabledLabelColor = MaterialTheme.colors.secondary,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    backgroundColor = MaterialTheme.colors.background
                ),
                textStyle = TextStyle(fontSize = 18.sp, lineHeight = 30.sp),
                placeholder = {
                    Text(
                        text = "Начните ввод",
                        fontSize = 18.sp
                    )
                },
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 8.dp),
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
        }
    }
}















