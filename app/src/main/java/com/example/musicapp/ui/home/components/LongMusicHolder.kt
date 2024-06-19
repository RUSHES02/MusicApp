package com.example.musicapp.ui.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.musicapp.R
import com.example.musicapp.domain.data.Song
import com.example.musicapp.ui.theme.bg_color
import com.example.musicapp.ui.theme.text_Inactive

@Composable
fun LongMusicHolder(
    modifier: Modifier,
    song: Song,
    onClick: () -> Unit,
    ){
    Row(
        modifier = modifier
            .background(bg_color)
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Card(
            modifier = Modifier
                .width(60.dp)
                .height(60.dp),
            shape = RoundedCornerShape(5.dp),
            elevation = CardDefaults.cardElevation(5.dp),
            onClick = onClick,
            ) {
//            Image(
//                modifier = Modifier.fillMaxSize(),
//                painter = painterResource(R.drawable.img_default),
//                contentDescription = ""
//            )
            AsyncImage(
                model = song.imageUrl,
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop,
                error = painterResource(R.drawable.img_default),
                fallback = painterResource(R.drawable.img_default)
            )
        }

        Column(
            modifier = Modifier
                .weight(1f)
                .padding(start = 8.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = song.title,
                style = MaterialTheme.typography.headlineSmall.copy(
                    fontWeight = FontWeight.ExtraBold,
                ),
                color = Color.White
            )
            Text(
                text = song.artist,
                style = MaterialTheme.typography.titleSmall,
                color = text_Inactive
            )
        }

        Text(
            text = song.duration,
            color = Color.White,
            modifier = Modifier.padding(start = 8.dp)
        )
    }
}

//@Preview
//@Composable
//fun LongMusicPreview(){
//    LongMusicHolder(modifier = Modifier.fillMaxWidth())
//}
