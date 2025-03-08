package com.example.gamehok.ui.composables


import android.widget.Toast
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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.toColorInt
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.gamehok.ui.vm.GamesListViewModel
import com.example.gamehok.ui.vm.TournamentsListViewModel
import com.example.seekhoassignment.utils.network.ApiState
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import com.example.gamehok.R
import com.example.seekhoassignment.utils.interRegular


@Composable
fun Home(navController: NavController){
    val context=LocalContext.current
    val gamesListViewModel = hiltViewModel<GamesListViewModel>()
    val tournamentsListViewModel = hiltViewModel<TournamentsListViewModel>()
    val tournamentList = tournamentsListViewModel.tournamentsList.collectAsState()
    val scrollState = rememberScrollState()
    val gamesList=gamesListViewModel.gamesList.collectAsState()

    LaunchedEffect(Unit) {
        gamesListViewModel.fetchGamesList()
        tournamentsListViewModel.fetchTournamentsList()
    }
    val cards = listOf(
        "1",
        "2",
        "3",
    )
    val pagerState = rememberPagerState(0,0f){cards.size}
    val coroutineScope = rememberCoroutineScope()
    LaunchedEffect(pagerState) {
        while (true) {
            delay(3000)
            coroutineScope.launch {
                val nextPage = (pagerState.currentPage + 1) % cards.size
                pagerState.animateScrollToPage(nextPage)
            }
        }
    }
    Column(modifier = Modifier.fillMaxSize().verticalScroll(scrollState).background(
            brush = Brush.verticalGradient(
                colors = listOf(
                    Color("#08090A".toColorInt()),
                    Color("#08090A".toColorInt()),
                    Color("#08090A".toColorInt()),
                    Color("#001a0b".toColorInt()),
                )
            ),
        )
    ) {

        HorizontalPager(
            state = pagerState, modifier = Modifier,
            reverseLayout = false,
            verticalAlignment = Alignment.CenterVertically,
        ) { page ->
            BannerCard()
        }
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            cards.indices.forEach { index ->
                Box(
                    modifier = Modifier
                        .padding(horizontal = 4.dp).height(6.dp)
                        .width(6.dp)
                        .background(if (pagerState.currentPage == index) Color("#ffffff".toColorInt()) else Color.Gray, CircleShape)
                )
            }
        }


        Row (verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(horizontal = 25.dp, vertical = 15.dp)){
            Text(
                text = "Play Tournament by Games",
                fontWeight = FontWeight.SemiBold,
                fontSize = 16.sp,
                color = Color("#F2F2F2".toColorInt())
            )
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = "View All ",
                fontWeight = FontWeight.Normal,
                modifier = Modifier.clickable(indication = null,
                    interactionSource = remember { MutableInteractionSource() }){
                    Toast.makeText(context,"Coming soon...", Toast.LENGTH_SHORT).show()
                },
                fontSize = 14.sp,
                color = Color("#0CF285".toColorInt())
            )

        }
        gamesList.value.let {
            when(it){
                is ApiState.Loading -> {
                }
                is ApiState.Error -> {
                    Toast.makeText(context,"Error In Loading", Toast.LENGTH_SHORT).show()
                }
                is ApiState.Success -> {
                    LazyRow(
                        modifier = Modifier.fillMaxWidth().padding(horizontal = 2.dp),
                        horizontalArrangement = Arrangement.Center,
                    ) {
                        items(it.data.size) { game ->
                            GameCard(it.data[game])
                        }
                    }
                }
            }
        }

        HorizontalDivider(
            thickness = 2.dp,
            color = Color("#414141".toColorInt()),
            modifier = Modifier.padding(horizontal = 25.dp, vertical = 10.dp)
        )
        Row (verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(horizontal = 25.dp, vertical = 5.dp)){
            Text(
                text = "Compete in Battles",
                fontWeight = FontWeight.SemiBold,
                fontSize = 16.sp,
                color = Color("#F2F2F2".toColorInt())
            )
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = "View All ",
                modifier = Modifier.clickable(indication = null,
                    interactionSource = remember { MutableInteractionSource() }){
                    Toast.makeText(context,"Coming soon...", Toast.LENGTH_SHORT).show()
                },
                fontWeight = FontWeight.Normal,
                fontSize = 14.sp,
                color = Color("#0CF285".toColorInt())
            )

        }
        tournamentList.value.let {
            when(it){
                is ApiState.Loading -> {
                }
                is ApiState.Error -> {
                    Toast.makeText(context,"Error In Loading", Toast.LENGTH_SHORT).show()
                }
                is ApiState.Success -> {
                    LazyRow(modifier= Modifier.padding(start = 24.dp)
                    ) {
                        items(it.data.size) { tournament ->
                            TournamentCard(it.data[tournament])
                        }
                    }
                }
            }
        }

        Row (verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(horizontal = 25.dp, vertical = 15.dp)){
            Text(
                text = "People to follow",
                fontWeight = FontWeight.SemiBold,
                fontSize = 16.sp,
                color = Color("#F2F2F2".toColorInt())
            )
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = "View More",
                modifier = Modifier.clickable(indication = null,
                    interactionSource = remember { MutableInteractionSource() }){
                    Toast.makeText(context,"Coming soon...", Toast.LENGTH_SHORT).show()
                },
                fontWeight = FontWeight.Normal,
                fontSize = 14.sp,
                color = Color("#0CF285".toColorInt())
            )

        }
        FollowListItem(painterResource(R.drawable.img_1),"Legend Gamer")
        FollowListItem(painterResource(R.drawable.img_2),"Legend Gamer")
        FollowListItem(painterResource(R.drawable.img_3),"Legend Gamer")
    }
}

@Composable
fun FollowListItem(image: Painter, name: String) {
    val context=LocalContext.current
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 11.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = image,
            contentDescription = "Profile Image",
            modifier = Modifier
                .size(45.dp)
                .clip(CircleShape),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.width(13.dp))

        Text(
            text = name,
            fontSize = 17.sp,
            fontFamily = interRegular,
            fontWeight = FontWeight.Normal,
            color = Color.White,
            modifier = Modifier.weight(1f)
        )

        Button(
            onClick = { Toast.makeText(context,"Coming Soon...", Toast.LENGTH_SHORT).show()},
            colors = ButtonDefaults.buttonColors(
                containerColor = Color("#002E14".toColorInt())
            ),
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier
        ) {
            Text(
                text = "Follow",
                color = Color.White,
                fontFamily = interRegular
            )
        }
    }
}









