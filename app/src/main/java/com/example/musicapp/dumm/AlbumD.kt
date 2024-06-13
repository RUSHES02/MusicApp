package com.example.musicapp.dumm


import com.google.gson.annotations.SerializedName

data class AlbumD(
    @SerializedName("albums")
    val albums: List<Album>
) {
    data class Album(
        @SerializedName("album_type")
        val albumType: String,
        @SerializedName("artists")
        val artists: List<Artist>,
        @SerializedName("copyrights")
        val copyrights: List<Copyright>,
        @SerializedName("external_ids")
        val externalIds: ExternalIds,
        @SerializedName("external_urls")
        val externalUrls: ExternalUrls,
        @SerializedName("genres")
        val genres: List<Any>,
        @SerializedName("id")
        val id: String,
        @SerializedName("images")
        val images: List<Image>,
        @SerializedName("label")
        val label: String,
        @SerializedName("name")
        val name: String,
        @SerializedName("popularity")
        val popularity: Int,
        @SerializedName("release_date")
        val releaseDate: String,
        @SerializedName("release_date_precision")
        val releaseDatePrecision: String,
        @SerializedName("total_tracks")
        val totalTracks: Int,
        @SerializedName("tracks")
        val tracks: Tracks,
        @SerializedName("type")
        val type: String,
        @SerializedName("uri")
        val uri: String
    ) {
        data class Artist(
            @SerializedName("external_urls")
            val externalUrls: ExternalUrls,
            @SerializedName("id")
            val id: String,
            @SerializedName("name")
            val name: String,
            @SerializedName("type")
            val type: String,
            @SerializedName("uri")
            val uri: String
        ) {
            data class ExternalUrls(
                @SerializedName("spotify")
                val spotify: String
            )
        }

        data class Copyright(
            @SerializedName("text")
            val text: String,
            @SerializedName("type")
            val type: String
        )

        data class ExternalIds(
            @SerializedName("upc")
            val upc: String
        )

        data class ExternalUrls(
            @SerializedName("spotify")
            val spotify: String
        )

        data class Image(
            @SerializedName("height")
            val height: Int,
            @SerializedName("url")
            val url: String,
            @SerializedName("width")
            val width: Int
        )

        data class Tracks(
            @SerializedName("items")
            val items: List<Item>,
            @SerializedName("limit")
            val limit: Int,
            @SerializedName("next")
            val next: Any?,
            @SerializedName("offset")
            val offset: Int,
            @SerializedName("previous")
            val previous: Any?,
            @SerializedName("total")
            val total: Int
        ) {
            data class Item(
                @SerializedName("artists")
                val artists: List<Artist>,
                @SerializedName("disc_number")
                val discNumber: Int,
                @SerializedName("duration_ms")
                val durationMs: Int,
                @SerializedName("explicit")
                val explicit: Boolean,
                @SerializedName("external_urls")
                val externalUrls: ExternalUrls,
                @SerializedName("id")
                val id: String,
                @SerializedName("is_local")
                val isLocal: Boolean,
                @SerializedName("is_playable")
                val isPlayable: Boolean,
                @SerializedName("name")
                val name: String,
                @SerializedName("preview_url")
                val previewUrl: String,
                @SerializedName("track_number")
                val trackNumber: Int,
                @SerializedName("type")
                val type: String,
                @SerializedName("uri")
                val uri: String
            ) {
                data class Artist(
                    @SerializedName("external_urls")
                    val externalUrls: ExternalUrls,
                    @SerializedName("id")
                    val id: String,
                    @SerializedName("name")
                    val name: String,
                    @SerializedName("type")
                    val type: String,
                    @SerializedName("uri")
                    val uri: String
                ) {
                    data class ExternalUrls(
                        @SerializedName("spotify")
                        val spotify: String
                    )
                }

                data class ExternalUrls(
                    @SerializedName("spotify")
                    val spotify: String
                )
            }
        }
    }
}