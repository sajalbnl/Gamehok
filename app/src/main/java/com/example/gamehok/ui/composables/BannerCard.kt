package com.example.gamehok.ui.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.InlineTextContent
import androidx.compose.foundation.text.appendInlineContent
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.toColorInt
import com.example.gamehok.R

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