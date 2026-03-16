package com.example.mysportcomplexapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.mysportcomplexapp.ui.app.App
import com.example.mysportcomplexapp.ui.app.theme.MySportComplexAppTheme
import com.example.mysportcomplexapp.ui.app.App

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MySportComplexAppTheme {
                App()
            }
        }
    }
}