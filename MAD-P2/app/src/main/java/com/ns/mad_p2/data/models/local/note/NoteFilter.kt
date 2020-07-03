package com.ns.mad_p2.data.models.local.note

class NoteFilter(
    var title_content: String,
    var archived: String
) {
    init {
        title_content = "%" + title_content + "%"
        when (archived) {
            "true" -> archived = "%"
        }
    }
}