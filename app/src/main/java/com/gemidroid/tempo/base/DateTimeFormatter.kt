package com.gemidroid.tempo.base

import java.text.SimpleDateFormat
import java.util.*

fun dateTimeFormatter(publishDate: String): String {
    val fmt = SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss'Z'")
    val date = fmt.parse(publishDate)
    val fmtOut = SimpleDateFormat("EEEE dd-MM-yyyy hh:mm a", Locale("en"))
    return fmtOut.format(date!!)
}