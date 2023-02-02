package ru.cyclone.cycnote.presentation.screens.details

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import ru.cyclone.cycnote.presentation.navigation.Screens
import java.util.*


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun DetailsScreen(navController: NavController, id: String?) {
    val viewModel = hiltViewModel<DetailsViewModel>()
    val note = viewModel.note.observeAsState().value
    id?.toLong()?.let { viewModel.getNoteById(id = it) }
//    var title by rememberSaveable { mutableStateOf("") }
//    var description by rememberSaveable { mutableStateOf("") }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navController.navigate(
                    Screens.AddScreen.rout + "/${note?.id}"
                )},
                modifier = Modifier
            ) {
                Icon(
                    imageVector = Icons.Filled.Edit,
                    tint = Color.White,
                    contentDescription = "add"
                )
            }
        },
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
                        .clickable {
                            viewModel.deleteNote {
                                navController.navigate(Screens.MainScreen.rout)
                            }
                        }



                ) {
                    Icon(
                        imageVector = Icons.Filled.Delete,
                        tint = Color.White,
                        contentDescription = "delete note",
                        modifier = Modifier
                            .align(Alignment.Center)

                    )
                }

            }
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 68.dp)
                .padding(horizontal = 27.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = note?.title ?: "",
                fontSize = 35.sp,
                style = TextStyle(color = Color(0xFF303030), fontWeight = FontWeight.Light)
            )
            Text(
                text = note?.content ?: "",
                fontSize = 25.sp,
                style = TextStyle(color = Color(0xFF303030), fontWeight = FontWeight.Light)
            )
        }
    }
}