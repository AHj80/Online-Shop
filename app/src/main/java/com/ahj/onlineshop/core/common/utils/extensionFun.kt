package com.ahj.onlineshop.core.common.utils

import java.text.DecimalFormat


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


fun Long.formatPriceToPersian(): String {
    val formatter = DecimalFormat("#,###")
    val englishFormatted = formatter.format(this)

    return englishFormatted
        .replace('0', '۰')
        .replace('1', '۱')
        .replace('2', '۲')
        .replace('3', '۳')
        .replace('4', '۴')
        .replace('5', '۵')
        .replace('6', '۶')
        .replace('7', '۷')
        .replace('8', '۸')
        .replace('9', '۹')
}