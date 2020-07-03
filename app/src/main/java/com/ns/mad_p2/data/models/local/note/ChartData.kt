package com.ns.mad_p2.data.models.local.note

class ChartData(
    var day: Int,
    var num: Int
) {
    override fun toString(): String {
        return "$day -> $num"
    }
}