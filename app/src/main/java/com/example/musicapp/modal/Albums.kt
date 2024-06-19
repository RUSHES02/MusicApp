package com.example.musicapp.modal

import com.google.gson.annotations.SerializedName

data class Albums(
    @SerializedName("data")
    val `data`: Data
) {
    data class Data(
        @SerializedName("album")
        val album: AlbumDetails
    ) {
        data class AlbumDetails(
            @SerializedName("playability")
            val playability: Playability,
            @SerializedName("tracks")
            val tracks: Tracks
        ) {
            data class Playability(
                @SerializedName("playable")
                val playable: Boolean
            )

            data class Tracks(
                @SerializedName("items")
                val items: List<Item>,
                @SerializedName("totalCount")
                val totalCount: Int
            ) {
                data class Item(
                    @SerializedName("track")
                    val track: Track,
                    @SerializedName("uid")
                    val uid: String
                ) {
                    data class Track(
                        @SerializedName("artists")
                        val artists: Artists,
                        @SerializedName("contentRating")
                        val contentRating: ContentRating?,
                        @SerializedName("discNumber")
                        val discNumber: Int?,
                        @SerializedName("duration")
                        val duration: Duration?,
                        @SerializedName("name")
                        val name: String,
                        @SerializedName("playability")
                        val playability: Playability?,
                        @SerializedName("playcount")
                        val playcount: String?,
                        @SerializedName("relinkingInformation")
                        val relinkingInformation: Any?,
                        @SerializedName("saved")
                        val saved: Boolean?,
                        @SerializedName("trackNumber")
                        val trackNumber: Int?,
                        @SerializedName("uri")
                        val uri: String
                    ) {
                        data class Artists(
                            @SerializedName("items")
                            val items: List<ArtistItem>
                        ) {
                            data class ArtistItem(
                                @SerializedName("profile")
                                val profile: Profile,
                                @SerializedName("uri")
                                val uri: String
                            ) {
                                data class Profile(
                                    @SerializedName("name")
                                    val name: String
                                )
                            }
                        }

                        data class ContentRating(
                            @SerializedName("label")
                            val label: String
                        )

                        data class Duration(
                            @SerializedName("totalMilliseconds")
                            val totalMilliseconds: Int
                        )

                        data class Playability(
                            @SerializedName("playable")
                            val playable: Boolean
                        )
                    }
                }
            }
        }
    }
}
