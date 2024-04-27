package com.example.translatorapp.presnetation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.translatorapp.presnetation.screen.SearchScreen
import com.example.translatorapp.presnetation.theme.TranslatorAppTheme
import com.example.translatorapp.presnetation.viewmodel.WordViewModel
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TranslatorAppTheme {
                StatusBarColor()
                val wordViewModel = hiltViewModel<WordViewModel>()
                val state by wordViewModel.state.collectAsState()

                SearchScreen(wordViewModel = wordViewModel, state = state)
            }
        }
    }


    @Composable
    fun StatusBarColor() {
        val uiController = rememberSystemUiController()

        val color = MaterialTheme.colorScheme.background
        LaunchedEffect(color) {
            uiController.setStatusBarColor(color)
        }
    }
}

