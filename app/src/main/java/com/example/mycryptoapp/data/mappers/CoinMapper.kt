package com.example.mycryptoapp.data.mappers

import com.example.mycryptoapp.data.api.ApiFactory.BASE_IMAGE_URL
import com.example.mycryptoapp.data.database.CoinDbModel
import com.example.mycryptoapp.data.dto.CoinPriceRawDataDto
import com.example.mycryptoapp.data.dto.CoinsApiResponseDto
import com.example.mycryptoapp.domain.entity.Coin
import com.google.gson.Gson
import java.sql.Timestamp
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.TimeZone

class CoinMapper {

    fun mapApiResponseToString(apiResponse: CoinsApiResponseDto): String {

        return apiResponse.data?.map { it.coinInfo?.name }?.joinToString(",") ?: "0"
    }


    fun mapFromRawDataToDbModel(
        coinPriceInfoRawData: CoinPriceRawDataDto
    ): List<CoinDbModel> {
        val result = ArrayList<CoinDbModel>()
        val jsonObject = coinPriceInfoRawData.coinPriceInfoJsonObject ?: return result
        val coinKeySet = jsonObject.keySet()
        for (coinKey in coinKeySet) {
            val currencyJson = jsonObject.getAsJsonObject(coinKey)
            val currencyKeySet = currencyJson.keySet()
            for (currencyKey in currencyKeySet) {
                val priceInfo = Gson().fromJson(
                    currencyJson.getAsJsonObject(currencyKey),
                    CoinDbModel::class.java
                )
                result.add(priceInfo)
            }
        }
        return result
    }


    fun mapFromDbModelToDomain(coinDbModel: CoinDbModel): Coin {
        return Coin(
            fromSymbol = coinDbModel.fromSymbol,  // Это не может быть null
            toSymbol = coinDbModel.toSymbol ?: "Unknown",  // Если null, ставим "Unknown"
            price = coinDbModel.price ?: "0",  // Если price null или не удается преобразовать, ставим 0.0
            minPrice = coinDbModel.lowDay ?: "0",
            maxPrice = coinDbModel.highDay ?: "0",
            lastMarket = coinDbModel.lastMarket ?: "None",
            lastUpdate = mapTimestampToTime(coinDbModel.lastUpdate),  // Если lastUpdate null, ставим 0 (по умолчанию)
            imageUrl = mapFullImageUrl(coinDbModel.imageUrl.toString())
        )
    }
//    fun mapDomainToDbModel(coin: Coin): CoinDbModel {
//        return CoinDbModel(
//            type = null,
//            market = null,
//            fromSymbol = coin.fromSymbol,
//            toSymbol = coin.toSymbol,
//            flags = null,
//            price = coin.price.toString(),
//            lastUpdate = coin.lastUpdate,
//            lastVolume = null,
//            lastVolumeTo = null,
//            lastTradeId = null,
//            volumeDay = null,
//            volumeDayTo = null,
//            volume24Hour = null,
//            volume24HourTo = null,
//            openDay = null,
//            highDay = null,
//            lowDay = null,
//            open24Hour = null,
//            high24Hour = null,
//            low24Hour = null,
//            lastMarket = null,
//            volumeHour = null,
//            volumeHourTo = null,
//            openHour = null,
//            highHour = null,
//            lowHour = null,
//            topTierVolume24Hour = null,
//            topTierVolume24HourTo = null,
//            change24Hour = null,
//            changePCT24Hour = null,
//            changeDay = null,
//            changePCTDay = null,
//            supply = null,
//            mktCap = null,
//            totalVolume24Hour = null,
//            totalVolume24HourTo = null,
//            totalTopTierVolume24Hour = null,
//            totalTopTierVolume24HourTo = null,
//            imageUrl = coin.imageUrl
//        )
//    }


    fun mapListCoinDbModeToListCoinDomain(list: List<CoinDbModel>): List<Coin> {
        return list.map { mapFromDbModelToDomain(it) }
    }

    fun mapTimestampToTime(timestamp: Long?): String {
        if (timestamp == null) return ""
        val stamp = Timestamp(timestamp * 1000)
        val date = Date(stamp.time)
        val pattern = "HH:mm:ss"
        val sdf = SimpleDateFormat(pattern, Locale.getDefault())
        sdf.timeZone = TimeZone.getDefault()
        return sdf.format(date)
    }

    fun mapFullImageUrl(imageUrl: String): String {
        return BASE_IMAGE_URL + imageUrl
    }

}