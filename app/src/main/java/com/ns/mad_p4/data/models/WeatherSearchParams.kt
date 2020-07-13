package com.ns.mad_p4.data.models

class WeatherSearchParams(
    var city_name: String,
    val date: Long,
    var days: Int,
    val days_str: String
) {
    init {
        if (city_name == "") city_name = "a"
       when(days_str) {
           "1 Day" -> days = 1
           "2 Days" -> days = 2
           "3 Days" -> days = 3
           "4 Days" -> days = 4
           "5 Days" -> days = 5
           "6 Days" -> days = 6
           "7 Days" -> days = 7
           "8 Days" -> days = 8
           "9 Days" -> days = 9
           "10 Days" -> days = 10
       }
    }
}