package rs.raf.projekat2.nikola_sreckovic_rn3517_pavle_prica_rn7518.data.models.local.note

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