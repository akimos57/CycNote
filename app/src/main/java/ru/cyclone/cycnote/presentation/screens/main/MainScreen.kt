package ru.cyclone.cycnote.presentation.screens.main

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.coroutineScope
import ru.cyclone.cycnote.presentation.navigation.Screens
import ru.cyclone.cycnote.presentation.ui.components.EditDialog
import ru.cyclone.cycnote.presentation.ui.components.NoteItem
import ru.cyclone.cycnote.presentation.ui.theme.CycNoteTheme

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MainScreen(navController: NavHostController) {
    val viewModel = hiltViewModel<MainViewModel>()
    val notes = viewModel.notes.observeAsState(listOf()).value
    val verticalCustomScrollState = staticScrollState(viewModel)
    navController.enableOnBackPressed(false)

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navController.navigate(Screens.EditScreen.rout) },
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
                NoteItem(
                    title = note.title,
                    subtitle = note.content,
                    backgroundColor = MaterialTheme.colors.primaryVariant,
                    isFavourite = note.isFavourite,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 6.dp)
                        .padding(horizontal = 16.dp)
                        .clickable {
                            navController.navigate(Screens.EditScreen.rout + "/${note.noteID}")
                        }
                        .combinedClickable(
                            onClick = { navController.navigate(Screens.EditScreen.rout + "/${note.noteID}") },
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