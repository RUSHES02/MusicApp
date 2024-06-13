package com.example.musicapp.scrap

data class Album(
    var albums: List<Album>
) {
    data class Album(
        var album_type: String,
        var artists: List<Artist>,
        var copyrights: List<Copyright>,
        var external_ids: ExternalIds,
        var external_urls: ExternalUrls,
        var genres: List<Any>,
        var id: String,
        var images: List<Image>,
        var label: String,
        var name: String,
        var popularity: Int,
        var release_date: String,
        var release_date_precision: String,
        var total_tracks: Int,
        var tracks: Tracks,
        var type: String,
        var uri: String
    ) {
        data class Artist(
            var external_urls: ExternalUrls,
            var id: String,
            var name: String,
            var type: String,
            var uri: String
        ) {
            data class ExternalUrls(
                var spotify: String
            )
        }

        data class Copyright(
            var text: String,
            var type: String
        )

        data class ExternalIds(
            var upc: String
        )

        data class ExternalUrls(
            var spotify: String
        )

        data class Image(
            var height: Int,
            var url: String,
            var width: Int
        )

        data class Tracks(
            var items: List<Item>,
            var limit: Int,
            var next: Any?,
            var offset: Int,
            var previous: Any?,
            var total: Int
        ) {
            data class Item(
                var artists: List<Artist>,
                var disc_number: Int,
                var duration_ms: Int,
                var explicit: Boolean,
                var external_urls: ExternalUrls,
                var id: String,
                var is_local: Boolean,
                var is_playable: Boolean,
                var name: String,
                var preview_url: String,
                var track_number: Int,
                var type: String,
                var uri: String
            ) {
                data class Artist(
                    var external_urls: ExternalUrls,
                    var id: String,
                    var name: String,
                    var type: String,
                    var uri: String
                ) {
                    data class ExternalUrls(
                        var spotify: String
                    )
                }

                data class ExternalUrls(
                    var spotify: String
                )
            }
        }
    }
}