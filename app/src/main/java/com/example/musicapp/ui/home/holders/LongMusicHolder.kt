package com.example.musicapp.ui.home.holders

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.musicapp.R
import com.example.musicapp.ui.theme.bg_color
import com.example.musicapp.ui.theme.text_Inactive

@Composable
fun LongMusicHolder(
    modifier: Modifier = Modifier
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
            elevation = CardDefaults.cardElevation(5.dp)
        ) {
            Image(
                modifier = Modifier.fillMaxSize(),
                painter = painterResource(R.drawable.img_default),
                contentDescription = ""
            )
        }

        Column(
            modifier = Modifier
                .weight(1f)
                .padding(start = 8.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "My Song",
                style = MaterialTheme.typography.headlineMedium.copy(
                    fontWeight = FontWeight.ExtraBold,
                ),
                color = Color.White
            )
            Text(
                text = "Artist",
                style = MaterialTheme.typography.titleSmall,
                color = text_Inactive
            )
        }

        Text(
            text = "3:21",
            color = Color.White,
            modifier = Modifier.padding(start = 8.dp)
        )
    }
}

@Preview
@Composable
fun LongMusicPreview(){
    LongMusicHolder(modifier = Modifier.fillMaxWidth())
}
