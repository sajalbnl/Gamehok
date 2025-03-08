package com.example.gamehok.ui.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.toColorInt
import androidx.navigation.NavController
import com.example.gamehok.R


@Composable
fun TopBar(navController: NavController){
    val context = LocalContext.current
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color("#08090A".toColorInt())).padding(top=5.dp)
            .padding(horizontal = 20.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Box {
            Image(
                painter = painterResource(id = R.drawable.avatar),
                contentDescription = "Profile",
                modifier = Modifier.padding(5.dp)
                    .size(45.dp)
                    .clip(CircleShape)
            )
            Box(
                modifier = Modifier
                    .size(20.dp)
                    .background(Color("#01A74B".toColorInt()), CircleShape)
                    .align(Alignment.BottomEnd)

            ){
                Image( painter = painterResource(id = R.drawable.hamburger),
                    contentDescription = "Tickets",
                    modifier = Modifier.size(10.dp).align(Alignment.Center))
            }
        }

        Spacer(modifier = Modifier.weight(1f))

        Row(
            modifier = Modifier
                .background(Color("#01A74B".toColorInt()), shape = RoundedCornerShape(20.dp))
                .padding(horizontal = 16.dp, vertical = 6.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Row (verticalAlignment = Alignment.CenterVertically){
                Image(
                    painter = painterResource(id = R.drawable.tickets),
                    contentDescription = "Tickets",
                    modifier = Modifier.size(16.dp)
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(text = "245", color = Color("#ffffff".toColorInt()), fontSize = 14.sp)

            }
            Spacer(modifier = Modifier.width(10.dp))

            VerticalDivider(
                color = Color("#0CF285".toColorInt()), modifier = Modifier
                    .height(18.dp)
                    .width(4.dp)

            )
            Spacer(modifier = Modifier.width(5.dp))

            Row(verticalAlignment = Alignment.CenterVertically){

                Image(
                    painter = painterResource(id = R.drawable.coins),
                    contentDescription = "Coins",
                    modifier = Modifier.size(16.dp)
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(text = "2456", color = Color("#ffffff".toColorInt()), fontSize = 14.sp)
            }

        }

        Spacer(modifier = Modifier.width(16.dp))

        // Notification Bell Icon
        Icon(
            painter = painterResource(id = R.drawable.bell_icon), // Replace with your bell icon
            contentDescription = "Notifications",
            tint = Color("#FFFFFF".toColorInt()),
            modifier = Modifier.size(24.dp)

        )
    }

}