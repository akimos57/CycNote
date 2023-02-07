package ru.cyclone.cycnote.presentation.screens.main

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.coroutineScope
import ru.cyclone.cycnote.presentation.navigation.Screens
import ru.cyclone.cycnote.presentation.ui.components.NoteItem
import ru.cyclone.cycnote.presentation.ui.theme.CycNoteTheme


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MainScreen(navController: NavHostController) {
    val viewModel = hiltViewModel<MainViewModel>()
    val notes = viewModel.notes.observeAsState(listOf()).value
    val verticalCustomScrollState = staticScrollState(viewModel)

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navController.navigate(Screens.AddScreen.rout) },
                modifier = Modifier
            ) {
                Icon(
                    imageVector = Icons.Filled.Edit,
                    contentDescription = "add"
                )
            }
        }
    ) { paddingParams ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingParams)
                .verticalScroll(verticalCustomScrollState)
        ) {
            Text(
                text = "Заметки",
                fontSize = 42.sp,
                modifier = Modifier
                    .padding(top = 30.dp, start = 24.dp, bottom = 12.dp)
            )
            notes.forEach { note ->
                val showDialog = remember { mutableStateOf(false) }
                if (showDialog.value) {
                    Alert(
                        showDialog = showDialog.value,
                        onDismiss = { showDialog.value = false },
                        isFavourite = note.isFavourite,
                        removeRequested = {
                            viewModel.deleteNote(
                                note = note
                            )
                        },
                        favouriteStateChanged = { viewModel.changeFavouriteState(note = note) }
                    )
                }
                NoteItem(
                    title = note.title,
                    subtitle = note.content,
                    backgroundColor = MaterialTheme.colors.primary,
                    isFavourite = note.isFavourite,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 6.dp)
                        .padding(horizontal = 16.dp)
                        .clickable {
                            navController.navigate(Screens.AddScreen.rout + "/${note.id}")
                        }
                        .combinedClickable(
                            onClick = { navController.navigate(Screens.AddScreen.rout + "/${note.id}") },
                            onLongClick = {
                                showDialog.value = true
                            }
                        )
                )
            }
        }
    }
}

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
                    modifier = Modifier.fillMaxWidth(),
                    onClick = {

                    onDismiss()
                    removeRequested()
                }) {
                    Text(
                        "Удалить заметку",
                        color = MaterialTheme.colors.surface,
                        fontSize = 20.sp,
                        modifier =  Modifier
                            .fillMaxWidth()
                            .padding(start = 20.dp, top = 20.dp)
                    )
                }
                TextButton(
                    modifier = Modifier.fillMaxWidth(),
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

@Composable
fun staticScrollState(vm : MainViewModel): ScrollState {
    val state = rememberScrollState()
    LaunchedEffect(Unit) {
        coroutineScope {
            state.animateScrollTo(vm.scrollPos)
        }
    }
    if (state.isScrollInProgress) {
        DisposableEffect(Unit){
            onDispose {
                vm.scrollPos = state.value
            }
        }
    }
    return state
}

@Preview(showBackground = true)
@Composable
fun PreviewMainScreen() {
    CycNoteTheme {
        MainScreen(rememberNavController())
    }
}