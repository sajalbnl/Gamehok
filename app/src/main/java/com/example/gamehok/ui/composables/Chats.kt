package com.example.gamehok.ui.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Canvas
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.unit.dp
import androidx.core.graphics.toColorInt
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas

@Composable
fun Chats(){
    Column(modifier = Modifier.fillMaxSize().background(Color("#08090A".toColorInt()))) {

    }

}


//@Composable
//fun SpotlightEffect() {
//    Canvas(
//        modifier = Modifier
//            .size(100.dp)
//            .offset(y = (-20).dp) // Move the spotlight above the icon
//    ) {
//        drawIntoCanvas { canvas ->
//            val paint = Paint().apply {
//                color = Color(0xFF0CAF60)
//                alpha = 0.3f
//            }
//            withTransform({
//                scale(scaleX = 1.5f, scaleY = 1f, pivot = center)
//            }) {
//                canvas.drawRoundRect(
//                    left = size.width * 0.2f,
//                    top = size.height * 0.2f,
//                    right = size.width * 0.8f,
//                    bottom = size.height * 0.5f,
//                    radiusX = 30f,
//                    radiusY = 30f,
//                    paint = paint.asFrameworkPaint()
//                )
//            }
//        }
//    }
//}
//
//@Preview
//@Composable
//fun PreviewSpotlightNav() {
//    SpotlightBottomNavigation()
//}