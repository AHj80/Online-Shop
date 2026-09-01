package com.ahj.onlineshop.core.common.utils

import android.content.Context
import android.content.Intent
import androidx.core.net.toUri
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import com.ahj.onlineshop.app.navigation.Screens
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

fun Context.shareText(text: String ){
    val sendIntent = Intent().apply {
        action = Intent.ACTION_SEND
        putExtra(Intent.EXTRA_TEXT , text )
        type = "text/plain"
    }

    val shareIntent = Intent.createChooser(sendIntent , "اشتراکگذاری با : ")
    this.startActivity(shareIntent)

}

fun NavController.singleScreen( screens: Screens){
    this.navigate(screens) {
        popUpTo(this@singleScreen.graph.findStartDestination().id) {
            saveState = true
        }
        launchSingleTop = true
        restoreState = true
    }


}

fun Context.openUrl(url: String, packageName: String? = null) {
    try {
        val intent = Intent(Intent.ACTION_VIEW, url.toUri()).apply {
            packageName?.let { setPackage(it) }
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        }
        startActivity(intent)
    } catch (e: Exception) {
        val fallbackIntent = Intent(Intent.ACTION_VIEW, url.toUri()).apply {
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        }
        startActivity(fallbackIntent)
    }
}