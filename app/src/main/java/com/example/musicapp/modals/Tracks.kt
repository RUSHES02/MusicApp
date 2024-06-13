package com.example.musicapp.modals

data class Tracks(
    var items: List<Item>,
    var limit: Int,
    var next: Any?,
    var offset: Int,
    var previous: Any?,
    var total: Int
)