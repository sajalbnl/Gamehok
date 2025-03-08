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
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.toColorInt
import androidx.navigation.compose.rememberNavController
import com.example.gamehok.ui.theme.GamehokTheme
import dagger.hilt.android.AndroidEntryPoint
import com.example.gamehok.R
import com.example.gamehok.data.model.TournamentsListItem
import com.example.gamehok.ui.composables.TagChip
import com.example.seekhoassignment.utils.interRegular


@AndroidEntryPoint
class TournamentsDetailsActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContent {
            GamehokTheme {

                val navController = rememberNavController()

                Surface(modifier = Modifier.fillMaxSize(), color = Color("#08090A".toColorInt())) {

                    TournamentDetailsScreenView(intent.getSerializableExtra("Tournament") as TournamentsListItem)
                }
            }
        }
    }
}

@SuppressLint("ContextCastToActivity")
@Composable
fun TournamentDetailsScreenView(tournament : TournamentsListItem){
    val context = LocalContext.current as Activity
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
        .verticalScroll(scrollState)
        .padding(top=50.dp)
        .fillMaxSize()
        .background(Color("#08090A".toColorInt()))
    ) {
        Box(modifier = Modifier.fillMaxWidth().size(280.dp)
        ){
            Image(
                painter = painterResource(id = R.drawable.bgmi),
                contentDescription = "Tournament Banner",
                modifier = Modifier.fillMaxSize().background(shape = RectangleShape, color = Color.Transparent),
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
                        .background(Color("#001208".toColorInt()).copy(alpha = 0.5f), shape = CircleShape)
                        .clickable(indication = null,
                            interactionSource = remember { MutableInteractionSource() }){
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
                        .background(Color("#002E14".toColorInt()), shape = RoundedCornerShape(16.dp))
                        .clickable(indication = null,
                            interactionSource = remember { MutableInteractionSource() }){
                            Toast.makeText(context,"Coming soon...", Toast.LENGTH_SHORT).show()
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
                        .background(Color("#001208".toColorInt()).copy(alpha = 0.8f), shape = RoundedCornerShape(16.dp))
                        .padding(horizontal = 10.dp, vertical = 4.dp)
                ) {
                    Text(
                        text = "Registration Closes in 2d 15h 10m",
                        color = Color("#ffffff".toColorInt()),
                        fontSize = 12.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }
                Box(
                    modifier = Modifier
                        .background(Color("#001208".toColorInt()).copy(alpha = 0.8f), shape = RoundedCornerShape(16.dp))
                        .padding(horizontal = 10.dp, vertical = 4.dp)
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

        Spacer(modifier = Modifier.height(10.dp))
        Row(
            modifier = Modifier.padding(16.dp)){
            Column() {
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
            }
            Spacer(modifier = Modifier.weight(1f))

            Image(
                painter = painterResource(id = R.drawable.red_bull),
                contentDescription = "",
                contentScale = ContentScale.Crop,
                modifier = Modifier.padding(end=15.dp).size(50.dp)
            )
        }


        // Tabs
//        var selectedTab by remember { mutableStateOf(0) }
//        TabRow(selectedTabIndex = selectedTab) {
//            listOf("Overview", "Players", "Rules").forEachIndexed { index, title ->
//                Tab(
//                    selected = selectedTab == index,
//                    onClick = { selectedTab = index },
//                    text = { Text(title, color = Color.White) }
//                )
//            }
//        }
//
//        // Details Section
//        Column(modifier = Modifier.padding(16.dp)) {
//            Text("Details", color = Color.White, fontSize = 18.sp, fontWeight = FontWeight.Bold)
//            Spacer(modifier = Modifier.height(8.dp))
//            DetailItem("Team Size", "Solo")
//            DetailItem("Format", "Single Elimination")
//        }
    }

}