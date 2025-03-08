package com.example.gamehok.ui.composables

import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
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
import com.example.gamehok.ui.activities.GameDetailsActivity
import com.example.gamehok.ui.activities.TournamentsDetailsActivity
import java.io.Serializable

@Composable
fun GameCard(game: GamesListItem) {
    val context = LocalContext.current
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
    val img : Int =if (game.gameName.lowercase() == "bgmi") R.drawable.pubg
    else if(game.gameName.lowercase() == "free_fire") R.drawable.counter_strike
    else R.drawable.cod
    Column(
        modifier = Modifier.clickable(){
            val i = Intent(context, GameDetailsActivity::class.java)
            i.putExtra("Image",img)
            context.startActivity(i)
        },
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(img),
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