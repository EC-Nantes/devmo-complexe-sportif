package com.example.mysportcomplexapp.ui.app.screens


import androidx.compose.foundation.border
import com.example.mysportcomplexapp.ui.app.viewmodel.TennisViewModel
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.compose.material3.HorizontalDivider
import androidx.compose.ui.Alignment


@Preview(showBackground = true)
@Composable
fun Test(){
    ResaScreen(navController=rememberNavController())
}
@Composable
fun ResaScreen(navController: NavHostController, viewModel: TennisViewModel = viewModel()) {
    val uiState by viewModel.uiState.collectAsState()

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(text = "Votre Résevation : Mr ${uiState.playerName}")
        Row(horizontalArrangement = Arrangement.spacedBy(35.dp)) {
            Box(
                modifier = Modifier
                    .border(1.dp, Color.Gray)
                    .padding(4.dp)
            ) {
                Text(text = "Mercredi 11 Mars ${uiState.selectedSlot?.label}")

            }

            Box(
                modifier = Modifier
                    .border(1.dp, Color.Gray)
                    .padding(4.dp)
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally){
                Text(text = " 10h - 11h ")// à changer
                Text(text = "1h",textAlign = TextAlign.Center,
                    )}
            }
            Box(
                modifier = Modifier
                    .border(1.dp, Color.Gray)
                    .padding(4.dp)
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(text = " 11h - 12h ")// à changer
                    Text(text = "2h",textAlign = TextAlign.Center,
                        )
                }
            }
        }


        Column(
            modifier = Modifier
                .border(1.dp, Color.Gray)
                .padding(16.dp).width(120.dp),
            verticalArrangement = Arrangement.spacedBy(2.dp)

        ) {


            Text(text = "Les Joueurs sous la responsabilité de ${uiState.playerName}")


            Row() {

                Row() {
                    Text(text = "✅")
                    Text(text = "Moi")
                }

                Text(text = " Prix : 20€ ")//${uiState.price}=20€

            }
            Text(text = "Terrain : ${uiState.selectedCourt?.name}")



            HorizontalDivider(
                modifier = Modifier.fillMaxWidth(),
                thickness = 4.dp,
                color = Color.Gray
            )
            Row() {
                    Text(text = "✅")
                    Text(text = "Moi")
                }

        }

        Box(modifier = Modifier.fillMaxWidth(),contentAlignment = Alignment.TopCenter
        ){
            Text(text = "Total: 20€",)//${uiState.price}=20€
        }


        Column(){
        Button(
            onClick = { viewModel.onConfirmReservation() },
            modifier = Modifier.fillMaxWidth(),
            colors=ButtonDefaults.buttonColors(Color.Green)//vert
        ) {
            Text("Confirmer la réservation")
        }
        
        Button(
            onClick = { viewModel.onConfirmReservation() },
            modifier = Modifier.fillMaxWidth(),
            colors=ButtonDefaults.buttonColors(Color.Red)//rouge
        ) {
            Text("Annuler la réservation")
        }

    }}

}

