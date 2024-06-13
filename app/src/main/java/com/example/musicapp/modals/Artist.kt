package com.example.musicapp.modals

data class Artist(
    var externalUrls: ExternalUrls,
    var id: String,
    var name: String,
    var type: String,
    var uri: String
)