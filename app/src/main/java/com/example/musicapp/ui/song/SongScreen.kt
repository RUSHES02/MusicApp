package com.example.musicapp.ui.song

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.musicapp.R
import com.example.musicapp.domain.data.Song
import com.example.musicapp.ui.theme.bg_grey
import com.example.musicapp.ui.theme.bg_purple

@Composable
fun SongScreen(
    onEvent: (SongEvent) -> Unit,
    musicControllerUiState: MusicControllerUiState,
    onNavigateUp: () -> Boolean,
    song: Song?,
    ) {

    var sliderPosition by remember { mutableFloatStateOf(0f) }
    Log.d("song screen","$song")

    Box(
        modifier = Modifier
            .fillMaxSize()
            .systemBarsPadding()
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        bg_purple,
                        bg_grey
                    ),
                    startY = 10f
                )
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp)
                .layoutId("bgColumn"),
            verticalArrangement = Arrangement.SpaceBetween
        ) {

            Row {
                Icon(
                    imageVector = Icons.Filled.KeyboardArrowDown,
                    contentDescription = "collapse",
                    tint = Color.White,
                    modifier = Modifier
                        .clickable { onNavigateUp() }
                )

                Spacer(
                    modifier = Modifier
                        .weight(1f)
                )

                Icon(
                    imageVector = Icons.Filled.Share,
                    contentDescription = "collapse",
                    tint = Color.White
                )
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                musicControllerUiState.currentMusic?.let {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .aspectRatio(1f / 1f),
                        shape = RoundedCornerShape(15.dp),
                        elevation = CardDefaults.cardElevation(5.dp)
                    ) {
                        AsyncImage(
                            model = it.imageUrl,
                            contentDescription = null,
                            modifier = Modifier.fillMaxSize(),
                            contentScale = ContentScale.Crop,
                            error = painterResource(R.drawable.img_default),
                            fallback = painterResource(R.drawable.img_default)
                        )
                    }

                    Spacer(modifier = Modifier.size(10.dp))

                    Text(
                        text = it.title,
                        style = MaterialTheme.typography.headlineLarge.copy(
                        fontWeight = FontWeight.ExtraBold,
                    ),
                    color = Color.White
                    )
                    Text(
                        text = it.artist,
                        style = MaterialTheme.typography.bodyMedium.copy(
                            fontWeight = FontWeight.ExtraBold,
                        ),
                        color = Color.White
                    )
                }

                Box {
                    Column {
                        Slider(
                            value = sliderPosition,
                            onValueChange = { sliderPosition = it },
                            colors = SliderDefaults.colors(
                                thumbColor = Color.Transparent,
                                activeTrackColor = Color.White,
                                inactiveTrackColor = MaterialTheme.colorScheme.secondaryContainer,
                            ),
                        )

                        Row(
                            modifier = Modifier
                                .fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceAround,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                painter = painterResource(R.drawable.ic_previous),
                                contentDescription = "previous song",
                                modifier = Modifier
                                    .scale(0.5f)
                                    .clickable {
                                        onEvent(SongEvent.SkipToPreviousSong)
                                    },
                                tint = Color.White
                            )
                            Card(
                                shape = CircleShape,
                                modifier = Modifier
                                    .size(100.dp),
                                onClick = {
                                    onEvent(SongEvent.PauseSong)
                                }
                            ) {
                                Box(
                                    modifier = Modifier
                                        .background(Color.White)
                                        .padding(10.dp)
                                        .fillMaxSize(),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Icon(
                                        painter = painterResource(R.drawable.ic_pause),
                                        contentDescription = "play",
                                        modifier = Modifier.scale(0.5f),
                                        tint = Color.Black
                                    )

                                }
                            }

                            Icon(
                                painter = painterResource(R.drawable.ic_next),
                                contentDescription = "previous song",
                                modifier = Modifier
                                    .scale(0.5f)
                                    .clickable {
                                        onEvent(SongEvent.SkipToNextSong)
                                    },
                                tint = Color.White
                            )
                        }
                    }
                }
            }

        }
    }
}
