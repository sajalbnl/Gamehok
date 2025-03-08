package com.example.gamehok.ui.composables

import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.toColorInt
import com.example.gamehok.R
import com.example.gamehok.data.model.TournamentsListItem
import com.example.gamehok.ui.activities.TournamentsDetailsActivity
import com.example.seekhoassignment.utils.interRegular
import java.io.Serializable

@Composable
fun TournamentCard(tournament: TournamentsListItem) {
    val context = LocalContext.current
    Box(
        modifier = Modifier
            .width(280.dp).clickable(){
                val i = Intent(context, TournamentsDetailsActivity::class.java)
                i.putExtra("Tournament",tournament as Serializable)

                context.startActivity(i)
            }
            .padding(end = 20.dp, top = 10.dp, bottom = 10.dp)
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color("#56E293".toColorInt()),
                        Color("#062E17".toColorInt()),
                    )
                ),
                shape = RoundedCornerShape(12.dp)
            )
    ) {

        Card(
            modifier = Modifier.fillMaxSize(),
            shape = RoundedCornerShape(12.dp),
            colors = CardDefaults.cardColors(containerColor = Color.Transparent)
        ) {

            val images = listOf(R.drawable.cod, R.drawable.bgmi, R.drawable.counter_strike,R.drawable.pubg)
            val randomImage = remember { images.random() }
            Column {
                Box {
                    Image(
                        painter = painterResource(id = randomImage),
                        contentDescription = "",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .height(165.dp)
                            .fillMaxWidth()
                    )
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Box(
                            modifier = Modifier
                                .background(Color("#001208".toColorInt()).copy(alpha = 0.8f), shape = RoundedCornerShape(16.dp))
                                .padding(horizontal = 8.dp, vertical = 4.dp)
                        ) {
                            Text(
                                text = tournament.status.replaceFirstChar { it.uppercase() },
                                color = Color("#ffffff".toColorInt()),
                                fontSize = 12.sp,
                                fontWeight = FontWeight.SemiBold)
                        }
                        Box(
                            modifier = Modifier
                                .background(Color("#001208".toColorInt()).copy(alpha = 0.8f), shape = RoundedCornerShape(16.dp))
                                .padding(horizontal = 8.dp, vertical = 4.dp)
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Image(
                                    painterResource(R.drawable.players_reg),
                                    contentDescription = null,
                                    modifier = Modifier.padding(end=5.dp).size(14.dp)
                                )
                                Text(
                                    text = "${tournament.registeredCount}/${tournament.totalCount}",
                                    color = Color("#ffffff".toColorInt()),
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.SemiBold)
                            }

                        }
                    }
                }

                Column(modifier = Modifier.padding(12.dp)) {
                    Text(
                        text = tournament.name,
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                        fontSize = 18.sp
                    )
                    Text(
                        text = tournament.organizerDetails.name,
                        color = Color.White,
                        fontSize = 14.sp
                    )

                    Row(
                        modifier = Modifier.padding(vertical = 6.dp),
                        horizontalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        TagChip(tournament.gameName.replace("_", " "))
                        TagChip(tournament.teamSize)
                        Box(
                            modifier = Modifier
                                .background(
                                    Color("#002E14".toColorInt()),
                                    shape = RoundedCornerShape(8.dp)
                                )
                                .padding(horizontal = 8.dp, vertical = 4.dp)
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Text(
                                    text = "Entry-${tournament.entryFees} ",
                                    color = Color.White,
                                    fontSize = 12.sp
                                )
                                Image(
                                    painterResource(R.drawable.coin),
                                    contentDescription = null,
                                    modifier = Modifier.size(12.dp)
                                )
                            }
                        }
                    }

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(top = 4.dp)
                    ) {
                        Image(
                            painterResource(R.drawable.timer),
                            contentDescription = null,
                            modifier = Modifier.size(20.dp)
                        )
                        Text(
                            text = " Starts 3rd Aug at 10:00 pm",
                            color = Color.White,
                            fontSize = 14.sp,
                            modifier = Modifier.padding(start = 4.dp)
                        )
                    }
                    val prizeCoins = tournament.prizeCoins
                    val totalPrize = prizeCoins.split(",").sumOf { it.toInt() }

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center,
                        modifier = Modifier.padding(top = 4.dp)
                    ) {
                        Image(
                            painterResource(R.drawable.small_cup),
                            contentDescription = null,
                            modifier = Modifier.size(20.dp)
                        )
                        Text(
                            text = " Prize Pool - $totalPrize ",
                            color = Color.White,
                            fontSize = 14.sp,
                            modifier = Modifier.padding(start = 4.dp)
                        )
                        Image(
                            painterResource(R.drawable.coin),
                            contentDescription = null,
                            modifier = Modifier.size(15.dp)
                        )
                    }
                }
            }
        }
        Image(
            painter = painterResource(id = R.drawable.red_bull),
            contentDescription = "",
            contentScale = ContentScale.Crop,
            modifier = Modifier.padding(end=15.dp).size(50.dp).align(Alignment.CenterEnd)
        )
    }
}


@Composable
fun TagChip(text: String) {
    Box(
        modifier = Modifier
            .background(Color("#002E14".toColorInt()), shape = RoundedCornerShape(8.dp))
            .padding(horizontal = 8.dp, vertical = 4.dp)
    ) {
        Text(
            text = text.replaceFirstChar { it.uppercase() },
            color = Color("#FFFFFF".toColorInt()),
            fontSize = 12.sp,
            fontFamily = interRegular
        )
    }
}