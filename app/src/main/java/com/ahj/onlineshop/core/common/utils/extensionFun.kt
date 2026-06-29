package com.ahj.onlineshop.core.common.utils


// for String
fun String.toPersianDigit(): String {
    val englishDigit = listOf("0", "1", "2", "3", "4", "5", "6", "7", "8", "9")
    val persianDigit = listOf("۰", "۱", "۲", "۳", "۴", "۵", "۶", "۷", "۸", "۹")

    var result = this

    for (i in 0..9) {
        result = result.replace(englishDigit[i], persianDigit[i])
    }
    return result
}

// for Integer
fun Int.toPersianDigit(): String = this.toString().toPersianDigit()

