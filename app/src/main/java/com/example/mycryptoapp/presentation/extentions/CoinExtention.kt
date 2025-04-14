package com.example.mycryptoapp.presentation.extentions

import com.example.mycryptoapp.data.api.ApiFactory.BASE_IMAGE_URL
import com.example.mycryptoapp.domain.entity.Coin
import java.sql.Date
import java.sql.Timestamp
import java.text.SimpleDateFormat
import java.util.Locale
import java.util.TimeZone

//fun Coin.getFormattedTime(): String {
//    if (lastUpdate == 0L) return ""
//    val stamp = Timestamp(lastUpdate * 1000)
//    val date = Date(stamp.time)
//    val pattern = "HH:mm:ss"
//    val sdf = SimpleDateFormat(pattern, Locale.getDefault())
//    sdf.timeZone = TimeZone.getDefault()
//    return sdf.format(date)
//}
//
//fun Coin.getFullImageUrl(): String {
//    return BASE_IMAGE_URL + imageUrl
//
//}