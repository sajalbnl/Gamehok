package com.example.gamehok.ui.activities

import android.annotation.SuppressLint
import android.app.Activity
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.toColorInt
import com.example.gamehok.R
import com.example.gamehok.ui.theme.GamehokTheme
import com.example.seekhoassignment.utils.interRegular

class GameDetailsActivity  : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GamehokTheme {

                Surface(modifier = Modifier.fillMaxSize(), color = Color("#08090A".toColorInt())) {

                    GameDetailsScreenView(intent.getIntExtra("Image",0))
                }
            }
        }
    }
}
@SuppressLint("ContextCastToActivity")
@Composable
fun GameDetailsScreenView(img:Int){
    val context = LocalContext.current as Activity
    val scrollState = rememberScrollState()
    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .padding(top = 50.dp, bottom = 60.dp)
                .fillMaxSize()
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            Color("#08090A".toColorInt()),
                            Color("#08090A".toColorInt()),
                            Color("#001a0b".toColorInt()),
                        )
                    ),
                )
        ) {
            Box(
                modifier = Modifier.fillMaxWidth().size(180.dp)
            ) {
                Image(
                    painter = painterResource(id = img),
                    contentDescription = "Tournament Banner",
                    modifier = Modifier.fillMaxSize()
                        .background(shape = RectangleShape, color = Color.Transparent),
                    contentScale = ContentScale.Crop
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth().align(Alignment.TopCenter)
                        .padding(15.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Box(
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                            .background(
                                Color("#001208".toColorInt()).copy(alpha = 0.5f),
                                shape = CircleShape
                            )
                            .clickable(indication = null,
                                interactionSource = remember { MutableInteractionSource() }) {
                                context.finish()
                            }
                    ) {
                        Image(
                            painterResource(R.drawable.back_arrow),
                            contentDescription = null,
                            modifier = Modifier.padding(10.dp).size(16.dp)
                        )
                    }
                    Box(
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                            .background(
                                Color("#002E14".toColorInt()),
                                shape = RoundedCornerShape(16.dp)
                            )
                            .clickable(indication = null,
                                interactionSource = remember { MutableInteractionSource() }) {
                                Toast.makeText(context, "Coming soon...", Toast.LENGTH_SHORT).show()
                            }

                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Image(
                                painterResource(R.drawable.share),
                                contentDescription = null,
                                modifier = Modifier.padding(10.dp).size(16.dp)
                            )
                        }
                    }
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth().align(Alignment.BottomCenter)
                        .padding(15.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Box(
                        modifier = Modifier
                            .background(
                                Color("#001208".toColorInt()).copy(alpha = 0.8f),
                                shape = RoundedCornerShape(16.dp)
                            )
                            .padding(horizontal = 10.dp, vertical = 4.dp)
                    ) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Text(
                                text = "Total Prize Pool",
                                color = Color("#ffffff".toColorInt()),
                                fontSize = 12.sp,
                                fontWeight = FontWeight.SemiBold
                            )
                            Text(
                                text = "3000",
                                color = Color("#ffffff".toColorInt()),
                                fontSize = 12.sp,
                                fontWeight = FontWeight.SemiBold
                            )
                        }
                    }
                    Box(
                        modifier = Modifier
                            .background(
                                Color("#001208".toColorInt()).copy(alpha = 0.8f),
                                shape = RoundedCornerShape(16.dp)
                            )
                            .padding(horizontal = 10.dp, vertical = 4.dp)
                    ) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Text(
                                text = "Active Members",
                                color = Color("#ffffff".toColorInt()),
                                fontSize = 12.sp,
                                fontWeight = FontWeight.SemiBold
                            )
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Image(
                                    painterResource(R.drawable.players_reg),
                                    contentDescription = null,
                                    modifier = Modifier.padding(end = 5.dp).size(14.dp)
                                )
                                Text(
                                    text = "2500",
                                    color = Color("#ffffff".toColorInt()),
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.SemiBold
                                )
                            }
                        }

                    }
                }
            }
            Column(modifier = Modifier.verticalScroll(scrollState).padding(horizontal = 16.dp)) {
                Spacer(modifier = Modifier.height(10.dp))
                TournamentCard()
                TournamentCard2()
                Spacer(modifier = Modifier.height(10.dp))
                HowToPlayScreen()
                Spacer(modifier = Modifier.height(15.dp))
                Text(
                    text = "System Requirements",
                    fontSize = 20.sp,
                    modifier = Modifier.padding(start = 10.dp),
                    fontWeight = FontWeight.SemiBold,
                    color = Color.White
                )

                Spacer(modifier = Modifier.height(10.dp))

                GameDetails()
                Spacer(modifier = Modifier.height(20.dp))

            }
        }
        Column(
            modifier = Modifier.background(color = Color("#08090A".toColorInt()))
                .align(Alignment.BottomCenter).padding(bottom = 30.dp)
        ) {
            HorizontalDivider(
                color = Color("#7F7F7F".toColorInt()).copy(alpha = 2f),
                thickness = 2.dp
            )
            Spacer(modifier = Modifier.height(12.dp))
            Button(
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color("#01B752".toColorInt()),
                ),
                modifier = Modifier
                    .padding(start = 15.dp, end = 15.dp)
                    .fillMaxWidth(),
                shape = RoundedCornerShape(10.dp),
                onClick = {
                    Toast.makeText(context, "Coming Soon...", Toast.LENGTH_SHORT).show()
                }
            ) {
                Text(
                    modifier = Modifier.padding(
                        top = 5.dp,
                        bottom = 5.dp
                    ),
                    text = "Book A Slot",
                    color = Color("#ffffff".toColorInt()),
                    fontWeight = FontWeight.SemiBold,
                    fontFamily = interRegular,
                    fontSize = 12.sp
                )
            }
        }
    }
}

@Composable
fun HowToPlayScreen() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "How to Play",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )

        Spacer(modifier = Modifier.height(16.dp))

        HowToPlayStep(
            img = R.drawable.download,
            iconBackground = Color(0xFFDDEBFF),
            title = "Download & Install",
            description = "Get the game from our official website"
        )
        VerticalDivider(
            modifier = Modifier
                .width(2.dp)
                .height(20.dp)
                .background(Color.Gray)
        )

        HowToPlayStep(
            img = R.drawable.team,
            iconBackground = Color(0xFFDFFFE0),
            title = "Form a Team",
            description = "Solo, Duo, or Squad (4 players)"
        )

        VerticalDivider(
            modifier = Modifier
                .width(2.dp)
                .height(20.dp)
                .background(Color.Gray)
        )
        HowToPlayStep(
            img = R.drawable.register,
            iconBackground = Color(0xFFEDE0FF),
            title = "Register",
            description = "Sign up for tournaments"
        )
        VerticalDivider(
            modifier = Modifier
                .width(2.dp)
                .height(20.dp)
                .background(Color.Gray)
        )
        HowToPlayStep(
            img = R.drawable.trophy,
            iconBackground = Color(0xFFDDEBFF),
            title = "Compete & Win",
            description = "Play and earn prizes"
        )
    }
}
@Composable
fun HowToPlayStep(
    img: Int,
    iconBackground: Color,
    title: String,
    description: String
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        Box(
            modifier = Modifier
                .size(50.dp)
                .clip(CircleShape)
                .background(iconBackground),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(img),
                contentDescription = null,
                modifier = Modifier.size(24.dp)
            )
        }

        Spacer(modifier = Modifier.width(15.dp))

        Column {
            Text(
                text = title,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )

            Text(
                text = description,
                fontSize = 14.sp,
                color = Color.Gray,
                textAlign = TextAlign.Start,
                modifier = Modifier.padding(top = 4.dp)
            )
        }
    }
}


@Composable
fun GameDetails() {
    val instructions = listOf(
        "OS: Windows 10 64-bit",
        "Processor: Intel Core i5-6600K",
        "Memory: 8 GB RAM",
        "Graphics: NVIDIA GTX 1060",
        "Storage: 50 GB available space"
    )
    Column (modifier = Modifier.padding(start = 10.dp, bottom = 30.dp)){
        instructions.forEach {
            Row(
                verticalAlignment = Alignment.Top,
                modifier = Modifier.padding(vertical = 4.dp)
            ) {
                Text(text = "•", color = Color("#BCBCBC".toColorInt()), fontSize = 14.sp)
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = it, color = Color("#BCBCBC".toColorInt()), fontSize = 14.sp)
            }
        }
    }
}
@Composable
fun TournamentCard() {
    Card(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = Color("#002E14".toColorInt()),
        ),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Winter Championship",
                color = Color.White,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = "Thu, Feb 20, 03:00 PM",
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Gray
            )

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Image(
                        painter = painterResource(R.drawable.small_cup),
                        contentDescription = "Prize",
                        modifier = Modifier.size(30.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = "$10,000",
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                }
                Text(
                    text = "Squad",
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text(text = "Starts In", color = Color.Gray, fontSize = 12.sp)
                    Text(
                        text = "-17d -12h",
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                    )
                }
                Column {
                    Text(text = "Slots", color = Color.Gray, fontSize = 12.sp)
                    Text(
                        text = "19/64",
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                    )
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            Button(
                onClick = {  },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF28A745))
            ) {
                Text(text = "Register Now", color = Color.White)
            }
        }
    }
}

@Composable
fun TournamentCard2() {
    Card(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = Color("#002E14".toColorInt()),
        ),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Spring Tournament",
                color = Color.White,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = "Sat, Feb 22, 06:00 PM",
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Gray
            )

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Image(
                        painter = painterResource(R.drawable.small_cup),
                        contentDescription = "Prize",
                        modifier = Modifier.size(30.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = "\$5,000",
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                }
                Text(
                    text = "Duo",
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text(text = "Starts In", color = Color.Gray, fontSize = 12.sp)
                    Text(
                        text = "-15d -9h",
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                    )
                }
                Column {
                    Text(text = "Slots", color = Color.Gray, fontSize = 12.sp)
                    Text(
                        text = "8/32",
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                    )
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            Button(
                onClick = {  },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF28A745))
            ) {
                Text(text = "Register Now", color = Color.White)
            }
        }
    }
}

