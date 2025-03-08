package com.example.gamehok.ui.activities

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.toColorInt
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.example.gamehok.ui.theme.GamehokTheme
import dagger.hilt.android.AndroidEntryPoint
import com.example.gamehok.R
import com.example.gamehok.data.model.TournamentsListItem
import com.example.gamehok.ui.composables.TagChip
import com.example.gamehok.ui.composables.TournamentCard
import com.example.gamehok.ui.vm.GamesListViewModel
import com.example.gamehok.ui.vm.TournamentsListViewModel
import com.example.seekhoassignment.utils.interRegular
import com.example.seekhoassignment.utils.network.ApiState


@AndroidEntryPoint
class TournamentsDetailsActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContent {
            GamehokTheme {

                Surface(modifier = Modifier.fillMaxSize(), color = Color("#08090A".toColorInt())) {

                    TournamentDetailsScreenView(intent.getSerializableExtra("Tournament") as TournamentsListItem,
                        intent.getIntExtra("Image",0))
                }
            }
        }
    }
}

@SuppressLint("ContextCastToActivity")
@Composable
fun TournamentDetailsScreenView(tournament : TournamentsListItem,image:Int){
    val context = LocalContext.current as Activity
    val scrollState = rememberScrollState()
    Box(modifier = Modifier.fillMaxSize()) {

    Column(
        modifier = Modifier
        .padding(top=50.dp, bottom = 60.dp)
        .fillMaxSize()
        .background(brush = Brush.verticalGradient(
            colors = listOf(
                Color("#08090A".toColorInt()),
                Color("#08090A".toColorInt()),
                Color("#001a0b".toColorInt()),
            )
        ),)
    ) {
        Box(
            modifier = Modifier.fillMaxWidth().size(180.dp)
        ) {
            Image(
                painter = painterResource(id = image),
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
                    Text(
                        text = tournament.status.replaceFirstChar { it.uppercase() },
                        color = Color("#ffffff".toColorInt()),
                        fontSize = 12.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }
                Box(
                    modifier = Modifier
                        .background(
                            Color("#001208".toColorInt()).copy(alpha = 0.8f),
                            shape = RoundedCornerShape(16.dp)
                        )
                        .padding(horizontal = 10.dp, vertical = 4.dp)
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Image(
                            painterResource(R.drawable.players_reg),
                            contentDescription = null,
                            modifier = Modifier.padding(end = 5.dp).size(14.dp)
                        )
                        Text(
                            text = "${tournament.registeredCount}/${tournament.totalCount}",
                            color = Color("#ffffff".toColorInt()),
                            fontSize = 12.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                }
            }
        }
        Column(modifier = Modifier.verticalScroll(scrollState)) {
            Spacer(modifier = Modifier.height(10.dp))
            Row(
                modifier = Modifier.padding(16.dp)
            ) {
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
                    modifier = Modifier.padding(end = 15.dp).size(50.dp)
                )
            }
            // Tabs
            var selectedTab = remember { mutableStateOf(0) }
            TabRow(
                selectedTabIndex = selectedTab.value,
                containerColor = Color("#08090A".toColorInt()),
                indicator = { tabPositions ->
                    TabRowDefaults.Indicator(
                        Modifier
                            .tabIndicatorOffset(tabPositions[selectedTab.value]),
                        color = Color("#FFFFFF".toColorInt())
                    )
                }
            ) {
                listOf("Overview", "Players", "Rules").forEachIndexed { index, title ->
                    Tab(
                        selected = selectedTab.value == index,
                        onClick = { selectedTab.value = index },
                        text = {
                            Text(
                                title,
                                color = if (selectedTab.value == index) Color("#FFFFFF".toColorInt()) else Color(
                                    "#A8A8A8".toColorInt()
                                )
                            )
                        }
                    )
                }
            }
            TournamentDetailsScreen()
            Spacer(modifier = Modifier.height(20.dp))
            TournamentScreen()
            Spacer(modifier = Modifier.height(20.dp))
            OrganiserCard()
            Spacer(modifier = Modifier.height(20.dp))
            MoreTournaments()
        }
    }
        Column(modifier = Modifier.background(color = Color("#08090A".toColorInt())).align(Alignment.BottomCenter).padding(bottom = 30.dp)) {
            HorizontalDivider(
            color = Color("#7F7F7F".toColorInt()).copy(alpha = 2f),
            thickness = 2.dp
        )
            Spacer(modifier=Modifier.height(12.dp))
            Button(
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color("#01B752".toColorInt()),
                ),
                modifier = Modifier
                    .padding(start = 15.dp, end = 15.dp)
                    .fillMaxWidth(),
                shape = RoundedCornerShape(10.dp),
                onClick = {Toast.makeText(context,"Coming Soon...", Toast.LENGTH_SHORT).show()
                }
            ){
                Text(
                    modifier = Modifier.padding(
                        top = 5.dp,
                        bottom = 5.dp
                    ),
                    text = "JOIN TOURNAMENT",
                    color = Color("#ffffff".toColorInt()),
                    fontWeight = FontWeight.SemiBold,
                    fontFamily = interRegular,
                    fontSize = 12.sp
                )
            }}

        }

}

@Composable
fun TournamentDetailsScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text(
            text = "Details",
            fontSize = 20.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color.White
        )

        DetailItem(R.drawable.solo_team, "TEAM SIZE", "Solo")
        DetailItem(R.drawable.single_ele, "FORMAT", "Single Elimination")
        DetailItem(R.drawable.calender, "TOURNAMENT STARTS", "Tue 24th Jan 2024, 01:00 PM")
        DetailItem(R.drawable.check_in, "CHECK-IN", "10 mins before the match starts")

        Spacer(modifier = Modifier.height(16.dp))

        Card(
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = Color("#4D5A53".toColorInt()))
        ) {
            Column(modifier = Modifier) {
                Row(
                    modifier = Modifier.fillMaxWidth().background(brush = Brush.horizontalGradient(
                        colors = listOf(
                            Color("#182920".toColorInt()),
                            Color("#4D5A53".toColorInt()),
                        )
                    )).padding(horizontal = 18.dp, vertical = 14.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Total Tournament Prize",
                        fontSize = 17.sp,
                        fontWeight = FontWeight.Medium,
                        color = Color.White
                    )
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = "2000 ",
                            fontSize = 17.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color("#FFFFFF".toColorInt())
                        )
                        Image(
                            painterResource(R.drawable.g_icon),
                            contentDescription = null,
                            modifier = Modifier.size(16.dp)
                        )

                    }

                }
                Spacer(modifier = Modifier.height(8.dp))

                val prizes = listOf(
                    "1st Prize" to "1000 ",
                    "2nd Prize" to "500 ",
                    "3rd Prize" to "200 ",
                    "4th Prize" to "100 ",
                    "5th Prize" to "100 "
                )

                prizes.forEachIndexed { index,(place, amount) ->
                    Row(
                        modifier = Modifier.fillMaxWidth().padding(horizontal = 18.dp,vertical = 5.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Row (verticalAlignment = Alignment.CenterVertically) {
                            Image(
                                painterResource(R.drawable.cup_grey),
                                contentDescription = null,
                                modifier = Modifier.size(22.dp)
                            )
                            Spacer(modifier = Modifier.width(5.dp))
                            Text(text = place, fontSize = 14.sp, color = Color.White)
                        }
                        Row (verticalAlignment = Alignment.CenterVertically){
                            Text(text = amount, fontSize = 14.sp, fontWeight = FontWeight.Bold, color = Color("#FFFFFF".toColorInt()))
                            Image(
                                painterResource(R.drawable.g_icon),
                                contentDescription = null,
                                modifier = Modifier.size(14.dp)
                            )

                        }

                    }
                    // Add a divider only if it's not the last item
                    if (index != prizes.lastIndex) {
                        Spacer(modifier = Modifier.height(6.dp))
                        HorizontalDivider(
                            thickness = 2.dp,
                            color = Color("#08090A".toColorInt()),
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                    Spacer(modifier = Modifier.height(6.dp))
                }
            }
        }

    }
}

@Composable
fun DetailItem(icon: Int, title: String, value: String) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        Image(
            painter = painterResource(id=icon),
            contentDescription = null,
            modifier = Modifier.size(30.dp)
        )
        Spacer(modifier = Modifier.width(10.dp))
        Column {
            Text(text = title, fontSize = 12.sp, color = Color("#A8A8A8".toColorInt()))
            Text(text = value, fontSize = 16.sp, fontWeight = FontWeight.Normal, color = Color.White)
        }
    }
}
@Composable
fun TournamentScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Rounds and Schedule",
            fontSize = 20.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color.White
        )

        Spacer(modifier = Modifier.height(12.dp))

        repeat(3) {
            RoundCard()
            Spacer(modifier = Modifier.height(8.dp))
        }

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "How to Join a Match",
            fontSize = 20.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color.White
        )

        Spacer(modifier = Modifier.height(8.dp))

        JoinInstructions()
    }
}

@Composable
fun RoundCard() {

    Column(
            modifier = Modifier.padding(6.dp)
        ) {
            Row {
                val text = buildAnnotatedString {
                    append(AnnotatedString("Qualifiers ", spanStyle = SpanStyle(color = Color("#FFFFFF".toColorInt()))))
                    append(AnnotatedString("(Round 1)", spanStyle = SpanStyle(color = Color("#A8A8A8".toColorInt()))))
                }

                Text(
                    text = text,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Normal
                )
                Spacer(modifier = Modifier.weight(1f))
                Box(
                    modifier = Modifier
                        .background(brush = Brush.linearGradient(
                            colors = listOf(
                                Color("#1C192E".toColorInt()),
                                Color("#311A61".toColorInt()),
                                Color("#311A61".toColorInt()),
                            )
                        ), shape = RoundedCornerShape(8.dp))
                        .padding(horizontal = 8.dp, vertical = 4.dp)
                ) {
                    Text(
                        text = "Single Elimination",
                        color = Color.White,
                        fontSize = 12.sp
                    )
                }

            }


            Spacer(modifier = Modifier.height(4.dp))
            Row {
                Text(
                    text = "Top 4 to next round",
                    color = Color.White,
                    fontSize = 14.sp
                )

                Spacer(modifier = Modifier.weight(1f))

                Text(
                    text = "3rd Aug, 10:00 pm",
                    color = Color.White,
                    fontSize = 14.sp
                )

            }


        }
    Spacer(modifier = Modifier.height(6.dp))
    HorizontalDivider(
        thickness = 2.dp,
        color = Color("#3C4B43".toColorInt()),
        modifier = Modifier.fillMaxWidth()
    )


}

@Composable
fun JoinInstructions() {
    val instructions = listOf(
        "Have your game open already and on the latest version",
        "Once the match is configured you will receive an invite in-game to join the lobby.",
        "Join the match and wait for the game to start.",
        "When eliminated return to the match room page to be ready to join the next map in the round."
    )

    Column {
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
fun OrganiserCard() {
    val context = LocalContext.current
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .background(Color.Transparent, shape = RoundedCornerShape(12.dp))
            .border(1.dp, Color(0xFF3A3F3E), RoundedCornerShape(12.dp))

    ) {
        Row(
            modifier = Modifier.fillMaxWidth().background(brush = Brush.horizontalGradient(
                colors = listOf(
                    Color("#182920".toColorInt()),
                    Color("#4D5A53".toColorInt()),
                ),

            ),
                shape = RoundedCornerShape(topEnd = 12.dp, topStart = 12.dp)),
        ){
            Text(
                text = "Organiser’s Details and contact",
                color = Color("#FFFFFF".toColorInt()),
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                modifier = Modifier.padding(10.dp)
            )
        }


        Spacer(modifier = Modifier.height(12.dp))

        Row(modifier = Modifier.padding(16.dp)) {
            Column (modifier = Modifier.weight(0.7f)){
                Row (verticalAlignment = Alignment.CenterVertically){
                    Image(
                        painterResource(R.drawable.g_icon),
                        contentDescription = null,
                        modifier = Modifier.size(22.dp)
                    )
                    Spacer(modifier = Modifier.width(12.dp))
                    Text(
                        text = "Gamehok Esports",
                        color = Color.White,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
                Text(
                    text = "This is the in house organiser of this platform you can follow our page on this platform for regular updates",
                    color = Color("#BCBCBC".toColorInt()),
                    fontSize = 12.sp,

                )

            }

            Button(
                onClick = { Toast.makeText(context,"Coming Soon...", Toast.LENGTH_SHORT).show()},
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color("#002E14".toColorInt())
                ),
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier.weight(0.3f)
            ) {
                Text(
                    text = "Follow",
                    color = Color.White,
                    fontFamily = interRegular
                )
            }

        }


        Spacer(modifier = Modifier.height(12.dp))
        Row(modifier = Modifier.padding(start = 16.dp, end = 16.dp)) {
            ContactItem(icon = Icons.Default.Phone, text = "9890987754")
            Spacer(modifier = Modifier.weight(1f))
            ContactItem(icon = Icons.Default.Email, text = "Support@gamehok.com")

        }
        Spacer(modifier = Modifier.height(8.dp))
        Row(modifier = Modifier.padding(start = 16.dp, end = 16.dp)) {
            ContactItem(icon = Icons.Default.Person, text = "9890987754")}
        Spacer(modifier = Modifier.height(12.dp))
    }
}

@Composable
fun ContactItem(icon: androidx.compose.ui.graphics.vector.ImageVector, text: String) {
    Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier) {
        Icon(imageVector = icon, contentDescription = null, tint = Color("#D1D4D2".toColorInt()))
        Spacer(modifier = Modifier.width(8.dp))
        Text(text = text, color = Color("#BCBCBC".toColorInt()), fontSize = 14.sp)
    }
}

@Composable
fun MoreTournaments(){
    val context=LocalContext.current
    val tournamentsListViewModel = hiltViewModel<TournamentsListViewModel>()
    val tournamentList = tournamentsListViewModel.tournamentsList.collectAsState()

    LaunchedEffect(Unit) {

        tournamentsListViewModel.fetchTournamentsList()
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
    Text(
        text = "More tournaments for you",
        fontSize = 20.sp,
        fontWeight = FontWeight.SemiBold,
        color = Color.White
    )
        tournamentList.value.let {
            when(it){
                is ApiState.Loading -> {
                    Row(modifier = Modifier.fillMaxWidth().padding(vertical = 30.dp), horizontalArrangement = Arrangement.Center) {
                        CircularProgressIndicator(color=Color("#0CF285".toColorInt()))
                    }

                }
                is ApiState.Error -> {
                    Toast.makeText(context,"Error In Loading", Toast.LENGTH_SHORT).show()
                }
                is ApiState.Success -> {
                    LazyRow(modifier= Modifier
                    ) {
                        items(it.data.size) { tournament ->
                            TournamentCard(it.data[tournament])
                        }
                    }
                }
            }
        }
        Spacer(modifier = Modifier.height(40.dp))
    }

}

