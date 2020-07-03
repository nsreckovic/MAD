package rs.raf.projekat3.nikola_sreckovic_rn3517.data.models

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