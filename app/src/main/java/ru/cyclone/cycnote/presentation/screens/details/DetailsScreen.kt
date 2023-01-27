package ru.cyclone.cycnote.presentation.screens.details

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import ru.cyclone.cycnote.presentation.navigation.Screens
import ru.cyclone.cycnote.presentation.screens.add.AddViewModel
import java.util.*


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun DetailsScreen(navController: NavController, id: String?) {

    val viewModel = hiltViewModel<DetailsViewModel>()
    val note = viewModel.note.observeAsState().value
    id?.toLong()?.let { viewModel.getNoteById(id = it) }
    var title by rememberSaveable { mutableStateOf("") }
    var description by rememberSaveable { mutableStateOf("") }
    var contentField by rememberSaveable { mutableStateOf(note?.content.toString()) }

    Scaffold(
        topBar = {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .padding(top = 24.dp)
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
                .fillMaxWidth()
                .padding(top = 24.dp)
                .padding(horizontal = 27.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = note?.title ?: "",
                fontSize = 35.sp,
                style = TextStyle(color = Color(0xFF303030), fontWeight = FontWeight.Light)
            )
            Text(
                modifier = Modifier
                    .padding(top = 24.dp),
                text = note?.content ?: "",
                fontSize = 25.sp,
                style = TextStyle(color = Color(0xFF303030), fontWeight = FontWeight.Light)
           )
//            Column() {
//                Column(
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .padding(top = 24.dp)
//                        .background(Color(0xFF0B4E85))
//                    ,
//                    verticalArrangement = Arrangement.Top,
//                    horizontalAlignment = Alignment.CenterHorizontally
//                ) {
//                    TextField(
//                        modifier = Modifier
//                            .fillMaxWidth()
//                            .height(70.dp)
//                            .background(Color(0xFFFFFFFF)),
//                        value = note?.title ?: "",
//                        onValueChange = { title = it }
//
//                    )
//
//                        TextField(
//                            modifier = Modifier
//                                .padding(top = 24.dp)
//                                .fillMaxWidth()
//                                .background(Color(0xFFFFFFFF)),
//                            value = contentField,
//                            onValueChange = { contentField = it },
//
//
//                            )
//
//                }
//        }


        }
    }
}