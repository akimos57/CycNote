package ru.cyclone.cycnote.presentation.screens.main

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import ru.cyclone.cycnote.presentation.navigation.Screens
import ru.cyclone.cycnote.presentation.ui.components.NoteItem
import ru.cyclone.cycnote.presentation.ui.theme.CycNoteTheme


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MainScreen(navController: NavHostController) {
    val viewModel = hiltViewModel<MainViewModel>()
    val notes = viewModel.notes.observeAsState(listOf()).value

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navController.navigate(Screens.AddScreen.rout) },
                modifier = Modifier
            ) {
                Icon(
                    imageVector = Icons.Filled.Edit,
                    contentDescription = "add")
            }
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()

        ) {
            Text(
                text = "Заметки",
                fontSize = 42.sp,
                modifier = Modifier
                    .padding(top = 30.dp, start =24.dp, bottom = 12.dp)
            )

            notes.forEach { note ->
                NoteItem(
                    title = note.title,
                    subtitle = note.content,
                    backgroundColor = MaterialTheme.colors.primary,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 12.dp)
                        .padding(horizontal = 24.dp)
                        .clickable {
                            navController.navigate(Screens.AddScreen.rout + "/${note.id}")
                        }
                )
            }
        }
    }
}



@Preview(showBackground = true)
@Composable
fun PreviewMainScreen() {
   CycNoteTheme {
       MainScreen(rememberNavController())
   }
}