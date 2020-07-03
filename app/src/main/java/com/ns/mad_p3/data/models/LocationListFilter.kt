package com.ns.mad_p3.data.models

class LocationListFilter(
    var title: String,
    var content: String,
    val order: String
) {
    init {
        title = "%" + title + "%"
        content = "%" + content + "%"
    }
}