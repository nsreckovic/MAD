package rs.raf.projekat2.nikola_sreckovic_rn3517_pavle_prica_rn7518.data.models.local.note

class ChartData(
    var day: Int,
    var num: Int
) {
    override fun toString(): String {
        return "$day -> $num"
    }
}