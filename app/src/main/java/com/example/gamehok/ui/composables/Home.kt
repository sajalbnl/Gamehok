package com.example.gamehok.ui.composables


import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import com.example.gamehok.R
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.InlineTextContent
import androidx.compose.foundation.text.appendInlineContent
import androidx.compose.material3.Card
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import coil3.compose.rememberAsyncImagePainter
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.toLowerCase
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.toColorInt
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.example.gamehok.data.model.GamesListItem
import com.example.gamehok.ui.vm.GamesListViewModel
import com.example.seekhoassignment.utils.network.ApiState
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.Locale


@Composable
fun Home(navController: NavController){
    val context=LocalContext.current
    val gamesListViewModel = hiltViewModel<GamesListViewModel>()
    val gamesList=gamesListViewModel.gamesList.collectAsState()

    LaunchedEffect(Unit) {
        gamesListViewModel.fetchGamesList()
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
    Column(modifier = Modifier.fillMaxSize().background(Color("#08090A".toColorInt()))) {

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
                fontWeight = FontWeight.Normal,
                fontSize = 14.sp,
                color = Color("#0CF285".toColorInt())
            )

        }
    }
}

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

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun BannerCard(){

    Card(
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 10.dp),
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(brush = Brush.verticalGradient(
                    colors = listOf(
                        Color("#FEEBA6".toColorInt()),
                        Color("#FFFFFF".toColorInt()),
                    )
                ), shape = RoundedCornerShape(16.dp))
        ) {
            Column(
                modifier = Modifier.padding(start = 12.dp, end = 12.dp, bottom = 16.dp)
            ) {
                Row(modifier = Modifier,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Gamehok",
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                        color = Color("#091118".toColorInt())
                    )
                    Spacer(modifier = Modifier.width(10.dp))

                        Image(
                            painterResource(R.drawable.tilted_rectangle),
                            contentDescription = "",
                            modifier = Modifier.size(80.dp)
                        )

                    Spacer(modifier = Modifier.weight(1f))
                    Box(
                        modifier = Modifier.background(color = Color("#FA7B4D".toColorInt()), shape = RoundedCornerShape(10.dp)),
                    ) {
                        Text(
                            text = "Get Now",
                            color = Color("#ffffff".toColorInt()),
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(horizontal = 12.dp, vertical = 2.dp),
                            textAlign = TextAlign.Center,
                            fontSize = 14.sp)
                    }
                }


                val imageId = "inlineImage"

                // Creating an AnnotatedString with an inline image
                val annotatedText = buildAnnotatedString {
                    append("Upgrade to premium membership and get 100 ")
                    appendInlineContent(imageId, "[ticket]")
                    append(" and many other premium features.")
                }

                val inlineContent = mapOf(
                    imageId to InlineTextContent(
                        placeholder = androidx.compose.ui.text.Placeholder(
                            width = 18.sp,
                            height = 18.sp,
                            placeholderVerticalAlign = androidx.compose.ui.text.PlaceholderVerticalAlign.Center
                        )
                    ) {
                        Image(
                            painter = painterResource(R.drawable.tickets),
                            contentDescription = "Ticket Icon",
                            modifier = Modifier.size(18.dp)
                        )
                    }
                )
                Text(
                    text = annotatedText,
                    style = TextStyle(
                        fontSize = 14.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color(0xFF091118)
                    ),
                    inlineContent = inlineContent
                )

                Spacer(modifier = Modifier.height(14.dp))

                Row (verticalAlignment = Alignment.CenterVertically){
                    Text(
                        text = "View All Feature ",
                        fontSize = 14.sp,
                        color = Color("#01A74B".toColorInt()),
                        modifier = Modifier.clickable { /* Handle click */ }
                    )
                    Icon(
                        painter = painterResource(R.drawable.arrow),
                        contentDescription = "",
                        tint = Color("#01A74B".toColorInt()),
                        modifier=Modifier.size(18.dp)

                    )
                }

            }
        }

    }
}