package com.example.gamehok.ui.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.rememberAsyncImagePainter
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.example.gamehok.R
import com.example.gamehok.data.model.GamesListItem

@Composable
fun GameCard(game: GamesListItem) {
    val painter =
        rememberAsyncImagePainter(
            ImageRequest.Builder(
                LocalContext.current
            )
                .data(data = game.gameName)
                .apply(block = fun ImageRequest.Builder.() {
                    crossfade(true)
                }).build()
        )
    Column(
        modifier = Modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = if (game.gameName.lowercase() == "bgmi") painterResource(R.drawable.pubg)
            else if(game.gameName.lowercase() == "free_fire") painterResource(R.drawable.counter_strike)
            else painterResource(R.drawable.cod),
            contentDescription = "",
            contentScale = ContentScale.Crop,
            modifier = Modifier.padding(10.dp)
                .size(100.dp)
                .clip(RoundedCornerShape(12.dp))
        )
        Text(
            text = game.gameName.replace("_", " "),
            color = Color.White,
            fontSize = 12.sp,
            fontWeight = FontWeight.Medium,
            textAlign = TextAlign.Center
        )
    }
}