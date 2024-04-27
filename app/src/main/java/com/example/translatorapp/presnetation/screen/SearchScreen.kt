package com.example.translatorapp.presnetation.screen

import android.media.MediaDrm.ErrorCodes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.translatorapp.presnetation.compose.ErrorCard
import com.example.translatorapp.presnetation.compose.Loader
import com.example.translatorapp.presnetation.compose.WordResultCompose
import com.example.translatorapp.presnetation.viewmodel.WordEvent
import com.example.translatorapp.presnetation.viewmodel.WordState
import com.example.translatorapp.presnetation.viewmodel.WordStatus
import com.example.translatorapp.presnetation.viewmodel.WordViewModel


@Composable

fun SearchScreen(
    wordViewModel: WordViewModel,
    state: WordState
) {

    Scaffold(
        topBar = {

            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                label = {
                    Text(
                        text = "Translate word...",
                        fontSize = 15.sp,
                        modifier = Modifier.alpha(0.7f)
                    )
                },
                value = state.word,
                onValueChange = {
                    wordViewModel.onEvent(WordEvent.OnSearchWordChangedEvent(it))
                },

                trailingIcon = {
                    Icon(
                        imageVector = Icons.Rounded.Search,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier
                            .size(30.dp)
                            .clickable {
                                wordViewModel.onEvent(WordEvent.OnSearchClicked)
                            }
                    )
                },
            )
        },
    ) { padd ->
        Column(
            modifier = Modifier.padding(padd)
        )
        {


            when (state.wordStatus) {
                WordStatus.ERROR -> ErrorCard(error = state.error)
                WordStatus.LOADING -> Loader()
                WordStatus.DONE -> WordResultCompose(word = state.wordItem)
                else -> Unit
            }
        }
    }
}