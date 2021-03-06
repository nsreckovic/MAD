package com.ns.mad_p2.data.models.local.subject

class Filter (
    var group: String,
    var day: String,
    var professor_subject: String
) {
    init {
        if (group == "All") group = "%"
        else group = "%$group%"
        when (day) {
            "All" -> day = "%"
            "Monday" -> day = "PON"
            "Tuesday" -> day = "UTO"
            "Wednesday" -> day = "SRE"
            "Thursday" -> day = "ČET"
            "Friday" -> day = "PET"
            "Saturday" -> day = "SUB"
            "Sunday" -> day = "NED"
        }
        professor_subject = "%$professor_subject%"
    }
}
