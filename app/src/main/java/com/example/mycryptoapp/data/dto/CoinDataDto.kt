package com.example.mycryptoapp.data.dto

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class CoinDataDto(
        @SerializedName("CoinInfo")
        @Expose
        val coinInfo: CoinInfoDto? = null
    )

