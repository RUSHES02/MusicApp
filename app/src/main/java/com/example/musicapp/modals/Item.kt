package com.example.musicapp.modals

data class Item(
    var artists: List<Artist>,
    var discNumber: Int,
    var durationMs: Int,
    var explicit: Boolean,
    var externalUrls: ExternalUrls,
    var id: String,
    var isLocal: Boolean,
    var isPlayable: Boolean,
    var name: String,
    var previewUrl: String,
    var trackNumber: Int,
    var type: String,
    var uri: String
)