package com.example.mysportcomplexapp.ui.app.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.mysportcomplexapp.R
import com.example.mysportcomplexapp.ui.app.theme.TennisColor
import com.example.mysportcomplexapp.ui.app.theme.FootballColor
import com.example.mysportcomplexapp.ui.app.theme.PadelColor
import com.example.mysportcomplexapp.ui.app.theme.MuscuColor
import com.example.mysportcomplexapp.ui.app.theme.GymColor
import com.example.mysportcomplexapp.ui.app.theme.EscaladeColor

import com.example.mysportcomplexapp.ui.app.Sport
import com.example.mysportcomplexapp.ui.app.theme.MySportComplexAppTheme

@Composable
fun AccueilScreen(navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ){Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(170.dp)
    ) {

        Image(
            painter = painterResource(id = R.drawable.complexe),
            contentDescription = "Image complexe",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        Text(
            text = "Complexe Sportif\nVictor Delacôte",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            color = Color.White,
            style = TextStyle(
                drawStyle = Stroke(width = 15f)
            ),
            modifier = Modifier.align(Alignment.Center)
        )

        Text(
            text = "Complexe Sportif\nVictor Delacôte",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            color = Color.Black,
            modifier = Modifier.align(Alignment.Center)
        )
    }
        Spacer(modifier = Modifier.height(24.dp))

        val sports = listOf(
            Sport("TENNIS", R.drawable.tennis, "tennis", TennisColor),
            Sport("FOOT A 5", R.drawable.foot, "football", FootballColor),
            Sport("PADEL", R.drawable.padel, "padel", PadelColor),
            Sport("MUSCU", R.drawable.muscu, "muscu", MuscuColor),
            Sport("GYMNASE", R.drawable.gymnase, "gym", GymColor),
            Sport("ESCALADE", R.drawable.escalade, "escalade", EscaladeColor)
        )

        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ){
            sports.forEach{sport ->
                SportCard(
                    name = sport.name,
                    imageRes = sport.image,
                    color = sport.color,
                    onClick = {
                        // Ici on a la navigation uniquement pour Tennis mais dans l'idée on devrait faire pour tous les sports
                        sport.route?.let{
                            navController.navigate(it)
                        }
                    }
                )
            }
        }
    }
}

@Composable
fun test(navController: NavHostController) {

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ){
        Text("ACCUEIL")
    }

}


@Composable
fun SportCard(
    name: String,
    imageRes: Int,
    color: Color,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp)
            .clickable { onClick() },
        shape = RoundedCornerShape(12.dp),
        border = BorderStroke(4.dp, color.copy(alpha = 0.9f)),
        colors = CardDefaults.cardColors(
            containerColor = color.copy(alpha = 0.15f)
        )
    ) {
        Row(verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 40.dp)) {
            Box(modifier = Modifier.weight(1f), contentAlignment = Alignment.Center){
                Text(
                    text = name,
                    fontSize = 40.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.align(Alignment.Center)
                )

                Image(
                    painter = painterResource(id = imageRes),
                    contentDescription = "$name image",
                    modifier = Modifier
                        .size(48.dp)
                        .align(Alignment.CenterEnd),
                    contentScale = ContentScale.Crop
                )
            }
        }

    }
}

@Composable
fun SportCardPreview() {
    SportCard(
        name = "TENNIS",
        imageRes = R.drawable.tennis,
        color = TennisColor,
        onClick = {}
    )
}

@Preview(showBackground = true)
@Composable
fun AccueilScreenPreview() {

    val navController = rememberNavController()

    MySportComplexAppTheme {
        AccueilScreen(navController)
    }
}