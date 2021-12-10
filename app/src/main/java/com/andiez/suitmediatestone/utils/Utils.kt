package com.andiez.suitmediatestone.utils

import android.util.Log
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList
import kotlin.math.roundToInt

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

fun isPalindrome(words: String): Boolean {
    return words.replace(" ", "") == words.replace(" ", "").reversed()

//    Manual algorithm
//    val listChar = ArrayList<Char>()
//    listChar.addAll(words.lowercase().toList())
//    listChar.removeAll { it == ' ' }
//    if (listChar.size == 1) return false
//    val firstMiddle =
//        if (listChar.size % 2 == 0) listChar.size / 2 else (listChar.size / 2) + 1
//    val secondMiddle =
//        if (listChar.size % 2 == 0) (listChar.size / 2) + 1 else (listChar.size / 2) + 1
//    val firstChars = ArrayList<Char>()
//    val secondChars = ArrayList<Char>()
//    for (i in (firstMiddle - 1) downTo 0) {
//        firstChars.add(listChar[i])
//    }
//    for (i in (secondMiddle - 1) until listChar.size) {
//        secondChars.add(listChar[i])
//    }
//    return firstChars.joinToString("") == secondChars.joinToString("")
}