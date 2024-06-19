package com.example.musicapp.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.musicapp.R
import com.example.musicapp.domain.PlayerState
import com.example.musicapp.domain.data.Song

@Composable
fun HomeBottomPlayer(
    modifier: Modifier = Modifier,
    onEvent: (HomeEvent) -> Unit,
    playerState: PlayerState?,
    song: Song?,
    onBarClick: () -> Unit
) {
    val playPauseIcon = painterResource(if (playerState == PlayerState.PLAYING) R.drawable.ic_pause else R.drawable.ic_play)

    val playPauseOnClick = {
        onEvent(if (playerState == PlayerState.PLAYING) HomeEvent.PauseSong else HomeEvent.ResumeSong)
    }

    if (song != null) {
        Box(
            modifier = modifier
                .height(64.dp)
                .clickable(onClick = { onBarClick() })
                .background(Color(0xffcccccc))
                .padding(10.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            ) {
                Image(
                    painter = rememberAsyncImagePainter(song.imageUrl),
                    contentDescription = song.title,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.size(48.dp)
                )
                Column(
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight()
                        .padding(horizontal = 16.dp)
                ) {
                    Text(
                        text = song.title,
                        style = MaterialTheme.typography.body2,
                        color = MaterialTheme.colors.onBackground,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                    Text(
                        text = song.artist,
                        style = MaterialTheme.typography.body2,
                        color = MaterialTheme.colors.onBackground,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.graphicsLayer { alpha = 0.60f }
                    )
                }
                Row(
                    modifier = Modifier
                        .weight(1f),
                    horizontalArrangement = Arrangement.SpaceAround,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(R.drawable.ic_previous),
                        contentDescription = "previous song",
                        modifier = Modifier
                            .scale(0.5f)
                            .clickable {
                                onEvent(HomeEvent.SkipToPreviousSong)
                            },
                        tint = Color.Black
                    )
                    Icon(
                        painter = playPauseIcon,
                        contentDescription = "play",
                        modifier = Modifier
                            .scale(0.5f)
                            .clickable {
                                       playPauseOnClick()
                            },
                        tint = Color.Black

                    )

                    Icon(
                        painter = painterResource(R.drawable.ic_next),
                        contentDescription = "previous song",
                        modifier = Modifier
                            .scale(0.5f)
                            .clickable {
                                onEvent(HomeEvent.SkipToNextSong)
                            },
                        tint = Color.Black
                    )
                }
            }
            }
        }
    }
