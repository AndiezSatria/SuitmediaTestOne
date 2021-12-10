package com.andiez.suitmediatestone.utils

import java.text.SimpleDateFormat
import java.util.*

fun convertOldFormatDate(oldDate: String): String {
    val oldFormat = "dd-MM-yyyy"
    val newFormat = "d MMMM, yyyy"
    val sdf = SimpleDateFormat(oldFormat, Locale.getDefault())
    val date = sdf.parse(oldDate)
    sdf.applyPattern(newFormat)
    return sdf.format(date!!)
}
fun convertFormatDate(oldDate: String): String {
    val oldFormat = "yyyy-MM-dd"
    val newFormat = "d MMMM, yyyy"
    val sdf = SimpleDateFormat(oldFormat, Locale.getDefault())
    val date = sdf.parse(oldDate)
    sdf.applyPattern(newFormat)
    return sdf.format(date!!)
}