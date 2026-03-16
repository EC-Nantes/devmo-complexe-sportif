package com.example.mysportcomplexapp.ui.app.screens


import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.BorderStroke
import com.example.mysportcomplexapp.ui.app.viewmodel.TennisViewModel
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.layout.width
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.Surface
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Shadow

import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import androidx.compose.ui.text.style.TextOverflow
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import androidx.compose.material3.CardDefaults
import com.example.mysportcomplexapp.ui.app.theme.TennisColor
import java.time.format.TextStyle


@Preview(showBackground = true)

@Composable
fun TennisBookingScreenPreview() {
    TennisScreen(navController=rememberNavController())
}


@Composable
fun TennisScreen(navController: NavHostController, viewModel: TennisViewModel = viewModel()) {
    val uiState by viewModel.uiState.collectAsState()
    val date = remember {
        SimpleDateFormat("dd/MM/yyyy", Locale.FRANCE).format(Date())
    }

    Card(modifier = Modifier
        .fillMaxWidth()
        .padding(16.dp),
        ){
    LazyColumn(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        stickyHeader {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(140.dp)
                    .background(color =TennisColor)


            ) {
                // Image de fond terrain (floutée / opacifiée)
                Image(
                    painter = painterResource(id = R.drawable.terrain_de_tennis),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxSize()
                        .alpha(0.5f),
                    contentScale = ContentScale.Crop
                )

                Row(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    // Logo tennis avec ombre et bordure arrondie
                    Surface(
                        shape = CircleShape,
                        shadowElevation = 8.dp,
                        modifier = Modifier.size(80.dp)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.tennis),
                            contentDescription = "Tennis image",
                            modifier = Modifier.fillMaxSize(),
                            contentScale = ContentScale.Crop
                        )
                    }

                    // Texte avec style
                    Column {
                        Text(
                            text = "Tennis",
                            fontSize = 36.sp,
                            fontWeight = FontWeight.ExtraBold,
                            color = Color.White,
                            letterSpacing = 2.sp,

                        )
                        Text(
                            text = "Le : $date",
                            fontSize = 14.sp,
                            color = Color.White.copy(alpha = 0.85f),
                            fontStyle = FontStyle.Italic
                        )
                    }
                }


            }
        }
        item{
            Button(onClick = {viewModel.changeDate()},
                    colors= ButtonDefaults.buttonColors(containerColor=Color.Black,contentColor=Color.White)){Text(
            text = "Le : $date",
            fontSize = 14.sp,
            fontStyle = FontStyle.Italic,
                textAlign = TextAlign.Center,
        )}}

        items(uiState.courts) { court ->

                Column() {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                            .border(
                                width = 2.dp,
                                color = Color(0xFFFFB74D), // orange clair
                                shape = RoundedCornerShape(16.dp)
                            ),
                                colors = CardDefaults.cardColors(
                                containerColor = TennisColor.copy(alpha=0.15f)
                                )



                    ) {
                        Column(Modifier.padding(6.dp)) {
                            Row(){
                                Spacer(modifier = Modifier.width(20.dp))
                                Text(
                                    text = court.name,
                                    fontSize = 16.sp
                                )
                            }

                            Spacer(modifier = Modifier.height(5.dp))

                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.spacedBy(8.dp)
                            )
                            {
                                court.timeSlots.forEach { horaire ->

                                    Button(
                                        onClick = { viewModel.onSlotClicked(court, horaire, navController) },
                                        enabled = horaire.isAvailable,
                                        modifier = Modifier.wrapContentSize(),
                                        colors= ButtonDefaults.buttonColors(containerColor=Color.Black,contentColor=Color.White),
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


