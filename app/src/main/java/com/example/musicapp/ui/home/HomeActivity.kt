package com.example.musicapp.ui.home

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.musicapp.ui.home.viewModel.AlbumViewModel
import com.example.musicapp.ui.theme.MusicAppTheme
import com.example.musicapp.ui.theme.bg_color

class HomeActivity : ComponentActivity() {

    private val albumViewModel : AlbumViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MusicAppTheme {
                Scaffold(modifier = Modifier
                    .fillMaxSize()
                    ) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier
                            .padding(innerPadding)
                            .fillMaxSize(),
                        musicViewModel = albumViewModel,
                    )
                }
            }
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun GreetingPreview() {
        MusicAppTheme {
            Greeting("Android",
                musicViewModel = albumViewModel)
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier, musicViewModel: AlbumViewModel) {

    val albumState = musicViewModel.album.collectAsState()
    val album = albumState.value
    Log.d("album", album.toString())



//    musicViewModel.fetchAlbum()

    Surface(
        modifier = Modifier
            .fillMaxSize(),
        color = bg_color
    ) {
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

//            VerticalRecommendations(album = albums!!.albums)

//            album?.let { albums ->
//                LazyRow {
//                    items(albums { it.tracks.items }) { track ->
//                        SquareMusicHolder(
//                            modifier = Modifier.padding(end = 10.dp),
//                            image = "https://spotify23.p.rapidapi.com/albums/?ids=3IBcauSj5M2A6lTeffJzdv", // Replace with appropriate image URL
//                            name =
//                        )
//                    }
//                }
//            }
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

//
//@Composable
//fun VerticalRecommendations(
//    album: List<Albums.Album>
//){
//
//    LazyRow {
//        itemsIndexed(album.tracks.items){ index, track ->
//            SquareMusicHolder(modifier = Modifier, image = album.images[0].url, name = track.name)
//        }
//    }
//
//}