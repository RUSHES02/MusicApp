package com.example.musicapp.ui.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.musicapp.R
import com.example.musicapp.domain.data.Song

@Composable
fun SquareMusicHolder(
    modifier: Modifier,
    song: Song,
    onClick: () -> Unit,

    ) {
    Card(
        modifier = modifier
            .width(100.dp)
            .height(100.dp),
        shape = RoundedCornerShape(15.dp),
        elevation = CardDefaults.cardElevation(5.dp),
        onClick = onClick
    ) {
        Box{
            AsyncImage(
                model = song.imageUrl,
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop,
                error = painterResource(R.drawable.img_default),
                fallback = painterResource(R.drawable.img_default)
            )

//            Image(
//                modifier = Modifier
//                    .fillMaxSize(),
//                painter = painterResource(R.drawable.img_default),
//                contentDescription = "",
//                contentScale = ContentScale.Crop
//            )

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(
                                Color.Transparent,
                                Color.Black
                            ),
                            startY = 50f
                        )
                    )
            )

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(12.dp),
                contentAlignment = Alignment.BottomCenter
            ){
                Text(
                    text = song.title,
                    style = TextStyle(
                        color = Color.White,
                        fontSize = 10.sp,
                        textAlign = TextAlign.Center
                    )
                )
            }

        }
    }
}

//@Preview
//@Composable
//fun SquareMusicHolderPreview() {
//    SquareMusicHolder(
//        modifier = Modifier,
//        image = "app/src/main/res/drawable/img_default.jpeg",
//        name = "Sample Song Name"
//    )
//}