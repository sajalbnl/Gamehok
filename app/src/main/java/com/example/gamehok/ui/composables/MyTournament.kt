package com.example.gamehok.ui.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import androidx.core.graphics.toColorInt

@Composable
fun MyTournament(){
    Column(modifier = Modifier.fillMaxSize().background(Color("#08090A".toColorInt())),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = "Coming Soon...", color = Color("#BCBCBC".toColorInt()), fontSize = 20.sp)

    }

}