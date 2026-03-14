package com.example.mysportcomplexapp.ui.app.screens


import com.example.mysportcomplexapp.ui.app.viewmodel.TennisViewModel
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.mysportcomplexapp.R
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.ui.graphics.Color
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import androidx.compose.ui.text.style.TextOverflow


@Preview(showBackground = true)

@Composable
fun TennisBookingScreenPreview() {
    TennisScreen(navController=rememberNavController())
}
@Composable
fun TennisScreen(navController: NavHostController, viewModel: TennisViewModel = viewModel()) {
    val uiState by viewModel.uiState.collectAsState()
    Box(modifier = Modifier
        .fillMaxWidth()
        .border(1.dp, Color.Gray)
        .padding(16.dp)){
    LazyColumn(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        stickyHeader {
            Text(text = "Tennis",fontSize=16.sp)//rajouter de la couleur Color=TennisColor.colorSpace
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)){

            Image(
                painter = painterResource(id = R.drawable.tennis),
            contentDescription = "Tennis image",
            modifier = Modifier
                .size(102.dp),

            contentScale = ContentScale.Crop
            )
            Image(
                painter = painterResource(id = R.drawable.terrain_de_tennis),
                contentDescription = "Tennis image",
                modifier = Modifier,

                contentScale = ContentScale.Crop
            ) }}


        items(uiState.courts) { court ->

                Column() {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .border(1.dp, Color.Gray)
                            .padding(16.dp)
                    ) {
                        Column() {
                            Text(
                                text = court.name,
                                fontSize = 16.sp
                            )
                            Spacer(modifier = Modifier.height(20.dp))

                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.spacedBy(8.dp)
                            )
                            {
                                court.timeSlots.forEach { horaire ->

                                    Button(
                                        onClick = { viewModel.onSlotClicked(court, horaire, navController) },
                                        enabled = horaire.isAvailable,
                                        modifier = Modifier.wrapContentSize()
                                    ) {
                                        Text(text = horaire.label, fontSize = 11.sp,maxLines = 1,
                                            textAlign = TextAlign.Center,overflow = TextOverflow.Visible,softWrap = false)

                                    }
                                }
                            }

                        }
                    }


                    Spacer(modifier = Modifier.height(8.dp))
                }
            }
            }
        }

    }


