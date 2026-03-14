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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
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



@Composable
fun test(navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ){
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ){
            Image(
                painter = painterResource(id = R.drawable.complexe),
                contentDescription = "Image Complexe",
                modifier = Modifier.size(64.dp),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = "Complexe Sportif Victor Delacôte",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
        }
        Spacer(modifier = Modifier.height(24.dp))

        val sports = listOf(
            Sport("Tennis", R.drawable.tennis, "tennis", TennisColor),
            Sport("Football", R.drawable.foot, "football", FootballColor),
            Sport("Padel", R.drawable.padel, "padel", PadelColor),
            Sport("Musculation", R.drawable.muscu, "muscu", MuscuColor),
            Sport("Gymnase", R.drawable.gymnase, "gym", GymColor),
            Sport("Escalade", R.drawable.escalade, "escalade", EscaladeColor)
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
fun AccueilScreen(navController: NavHostController) {

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
        border = BorderStroke(2.dp, color.copy(alpha = 0.9f)),
        colors = CardDefaults.cardColors(
            containerColor = color.copy(alpha = 0.15f)
        )
    ) {
        Box(modifier = Modifier.fillMaxSize().padding(horizontal = 50.dp)){
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

@Preview(showBackground = true)
@Composable
fun SportCardPreview() {
    SportCard(
        name = "Tennis",
        imageRes = R.drawable.tennis,
        color = TennisColor,
        onClick = {}
    )
}