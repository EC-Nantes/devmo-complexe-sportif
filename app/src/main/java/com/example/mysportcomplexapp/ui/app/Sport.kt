package com.example.mysportcomplexapp.ui.app

import androidx.compose.ui.graphics.Color

data class Sport(
    val name: String,
    val image: Int,
    val route: String? = null,
    val color: Color
)
