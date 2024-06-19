package com.example.musicapp.domain

import androidx.media3.common.MediaItem
import androidx.media3.common.MediaMetadata
import com.example.musicapp.data2.Albums2
import com.example.musicapp.domain.data.Song
import com.example.musicapp.modal.Albums

fun MediaItem.toSong() =
    Song(
        mediaId = mediaId,
        title = mediaMetadata.title.toString(),
        artist = listOf(mediaMetadata.artist).toString(),
        songUrl = mediaId,
        imageUrl = mediaMetadata.artworkUri.toString(),
        duration = mediaMetadata.duration.toString()
    )


fun Albums.Data.AlbumDetails.Tracks.Item.toSong() =
    Song(
        mediaId = uid,
        title = track.name,
        artist = track.artists.items.joinToString(", ") { it.profile.name },
        songUrl = track.uri,
        imageUrl = null.toString(),
        duration = "${(track.duration?.totalMilliseconds ?: 0) / 60000 }:${(track.duration?.totalMilliseconds ?: 0) % 60000 / 1000}"
    )

fun Albums2.Album.Tracks.Item.toSong() =
    Song(
        mediaId = id,
        title = name,
        artist = artists.joinToString(", ") { it.name },
        songUrl = previewUrl,
        imageUrl = null.toString(),
        duration = "${durationMs / 60000}:${durationMs % 60000 / 1000}"
    )

fun Map<String, Any>.toSong(id: String) =
    Song(
        mediaId = id,
        title = this["name"] as? String ?: "",
        artist = this["artist"] as? String ?: "",
        songUrl = this["song"] as? String ?: "",
        imageUrl = this["thumbnail"] as? String ?: "",
        duration = this["duration"] as? String ?: "",
    )


val MediaMetadata.duration: Long? get() = extras?.getLong("duration")