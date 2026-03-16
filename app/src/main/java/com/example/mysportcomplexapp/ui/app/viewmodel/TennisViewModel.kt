package com.example.mysportcomplexapp.ui.app.viewmodel

import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update



data class TimeSlot(
    val id: String,
    val label: String,
    val isAvailable: Boolean
)

data class TennisCourt(
    val id: String,
    val name: String,
    val isIndoor: Boolean,
    val timeSlots: List<TimeSlot>
)

data class Reservation(
    val id: String,
    val courtName: String,
    val date: String,
    val slotLabel: String,
    val playerName: String
)

data class TennisUiState(
    val courts: List<TennisCourt> = emptyList(),

    val reservations: List<Reservation> = emptyList(),
    val selectedCourt: TennisCourt? = null,
    val selectedSlot: TimeSlot? = null,
    val selectedHours:String?="",
    val showReservationDialog:Boolean=true,
    val playerName: String = "",
    //ajout en plus de la base
    val showAddUserDialog: Boolean = false,
    val nom: String = "",
    val prenom: String = ""


    )

class TennisViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(TennisUiState())
    val uiState: StateFlow<TennisUiState> = _uiState.asStateFlow()
    //uistate garder en mémoire

    init {
        loadCourts()
    }

    private fun loadCourts() {
        val courts = listOf(
            TennisCourt(
                id = "t1",
                name = "TERRAIN 1 - EXTERIEUR",
                isIndoor = false,
                timeSlots = listOf(
                    TimeSlot("t1_10", "10h00", true),
                    TimeSlot("t1_12", "12h00", false),
                    TimeSlot("t1_13", "13h00", true),
                    TimeSlot("t1_19", "19h00", true),
                )
            ),
            TennisCourt(
                id = "t2",
                name = "TERRAIN 2 - EXTERIEUR",
                isIndoor = false,
                timeSlots = listOf(
                    TimeSlot("t2_10", "10h00", true),
                    TimeSlot("t2_18", "18h00", false),
                )
            ),
            TennisCourt(
                id = "t3",
                name = "TERRAIN 3 - COUVERT",
                isIndoor = true,
                timeSlots = listOf(
                    TimeSlot("t3_10", "10h00", true),
                    TimeSlot("t3_12", "12h00", true),
                    TimeSlot("t3_15", "15h00", false),
                    TimeSlot("t3_17", "17h00", true),
                )
            ),
            TennisCourt(
                id = "t4",
                name = "TERRAIN 4 - EXTERIEUR",
                isIndoor = false,
                timeSlots = listOf(
                    TimeSlot("t4_8", "8h00", true),
                    TimeSlot("t4_11", "11h00", true),
                    TimeSlot("t4_16", "16h00", true),
                    TimeSlot("t4_17", "17h00", true),
                )
            ),
        )
        _uiState.update { it.copy(courts = courts) }
    }



//choix de l'horaire et du terrain
    fun onSlotClicked(court: TennisCourt, slot: TimeSlot,navController: NavController) {
        _uiState.update {
            it.copy(
                selectedCourt = court,
                selectedSlot = slot,
                selectedHours=slot.label,
                showReservationDialog = true
            )
        }
        navController.navigate("reservation")
    }



    fun onDismissAddUserDialog(){
        _uiState.update {
            it.copy(
                showAddUserDialog = false,
                nom = "null",
                prenom = "null",
            )
        }
    }


    fun onConfirmReservation() {
        val state = _uiState.value
        val court = state.selectedCourt ?: return
        val slot = state.selectedSlot ?: return


    }

    fun add() {
        val state = _uiState.value
        // votre logique d'ajout ici
        _uiState.update { it.copy(showAddUserDialog = true, nom = "", prenom = "") }
    }
    fun onDismissReservationDialog(navController: NavController) {
        _uiState.update {
            it.copy(
                showReservationDialog = false,
                selectedCourt = null,
                selectedSlot = null
            )
        }
        navController.navigate("tennis")
    }
    fun onNomChanged(nom: String) {
        _uiState.update { it.copy(nom = nom) }
    }

    fun onPrenomChanged(prenom: String) {
        _uiState.update { it.copy(prenom = prenom) }
    }






}



