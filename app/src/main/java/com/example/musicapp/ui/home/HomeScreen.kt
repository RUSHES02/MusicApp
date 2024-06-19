package com.example.musicapp.ui.home

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import com.example.musicapp.domain.data.Song
import com.example.musicapp.ui.home.holders.LongMusicHolder
import com.example.musicapp.ui.home.holders.SquareMusicHolder
import com.example.musicapp.ui.theme.bg_color

@Composable
fun HomeScreen(
    onEvent: (HomeEvent) -> Unit,
    uiState: HomeUiState,
    onClick: () -> Unit,
) {

    Surface(
        modifier = Modifier
            .fillMaxSize(),
        color = bg_color
    ) {

        with(uiState){
            when {
                loading == true -> {

                    Log.d("home", "loading")
                    Box(modifier = Modifier.fillMaxSize()) {
                        CircularProgressIndicator(
                            color = Color.Red,
                            modifier = Modifier
                                .size(100.dp)
                                .fillMaxHeight()
                                .align(Alignment.Center)
                                .padding(
                                    top = 16.dp,
                                    start = 16.dp,
                                    end = 16.dp,
                                    bottom = 16.dp
                                )
                        )
                    }
                }

                loading == false && errorMessage == null -> {
                    Column(
                        modifier = Modifier.padding(20.dp)
                    ) {
                        Text(
                            text = "Browse",
                            style = MaterialTheme.typography.headlineMedium.copy(
                                fontWeight = FontWeight.ExtraBold,
                            ),
                            color = Color.White
                        )

                        Spacer(modifier = Modifier.size(10.dp))

                        Categories(modifier = Modifier)

                        Spacer(modifier = Modifier.size(10.dp))


                        Log.d("home", "loading complete")

                        if (songs != null) {
                            Log.d("home", songs.toString())

                            HorizontalRecommendations(
                                songs = songs,
                                onEvent = onEvent,
                                onClick = onClick,
                            )

                            Spacer(modifier = Modifier.size(25.dp))

                            Text(
                                text = "For You",
                                style = MaterialTheme.typography.headlineMedium.copy(
                                    fontWeight = FontWeight.ExtraBold,
                                ),
                                color = Color.White
                            )

                            Spacer(modifier = Modifier.size(10.dp))

                            VerticalRecommendations(
                                songs = songs,
                                onEvent = onEvent,
                                onBarClick = onClick,
                            )
                        }
                    }
                }

            }
        }

    }
}

@Composable
fun Categories(modifier: Modifier) {
    var activeCategory by rememberSaveable { mutableIntStateOf(0) }

    val categories = listOf("Popular", "Artists", "Podcasts", "Favorites", "Albums")

    LazyRow(
        modifier = modifier.padding(vertical = 8.dp)
    ) {
        itemsIndexed(categories) { index, category ->
            CategoryItem(
                modifier = Modifier,
                category = category,
                index = index,
                active = activeCategory,
                onClick = { selectedIndex -> activeCategory = selectedIndex }
            )
        }
    }
}

@Composable
fun CategoryItem(
    modifier: Modifier,
    category: String,
    index: Int,
    active: Int,
    onClick: (Int) -> Unit
) {
//    Log.d("CategoryItem", "Index: $index, Active: $active")

    val textColor = if (index == active) Color.White else Color.Gray
    val textDecoration = if (index == active) TextDecoration.Underline else TextDecoration.None

    Box(
        modifier = modifier
            .padding(horizontal = 8.dp)
            .clickable { onClick(index) }
    ) {
        Text(
            text = category,
            style = MaterialTheme.typography.bodyLarge.copy(
                color = textColor,
                textDecoration = textDecoration
            ),
        )
    }
}


@Composable
fun HorizontalRecommendations(
    songs: List<Song>,
    onEvent: (HomeEvent) -> Unit,
    onClick: () -> Unit
){
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(songs){ song ->
            SquareMusicHolder(
                modifier = Modifier,
                onClick = {
                    onEvent(HomeEvent.OnSongSelected(song))
                    onEvent(HomeEvent.PlaySong)
                    onClick()
                },
                song = song)
        }
    }
}

@Composable
fun VerticalRecommendations(
    songs: List<Song>,
    onEvent: (HomeEvent) -> Unit,
    onBarClick: () -> Unit
){
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(songs){ song ->
            LongMusicHolder(
                modifier = Modifier.fillMaxWidth(),
                song = song,
                onClick = {
                    onBarClick()
                    onEvent(HomeEvent.OnSongSelected(song))
                    onEvent(HomeEvent.PlaySong)
                },
            )
        }
    }
}