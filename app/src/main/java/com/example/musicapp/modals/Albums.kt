package com.example.musicapp.modals

import com.google.gson.annotations.SerializedName


data class Albums(
    var albums: List<Album?> = emptyList()
) {
    data class Album(
        @SerializedName("album_type") var albumType: String,
        var artists: List<Artist>,
        var copyrights: List<Copyright>,
        var externalIds: ExternalIds,
        var externalUrls: ExternalUrls,
        var genres: List<Any>,
        var id: String,
        var images: List<Image>,
        var label: String,
        var name: String,
        var popularity: Int,
        var releaseDate: String,
        var releaseDatePrecision: String,
        var totalTracks: Int,
        var tracks: Tracks,
        var type: String,
        var uri: String
    )
}
