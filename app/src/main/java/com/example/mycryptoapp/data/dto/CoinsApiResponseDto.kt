package com.example.mycryptoapp.data.dto

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class CoinsApiResponseDto (
    @SerializedName("Data")
    @Expose
    val data: List<CoinDataDto>? = null)
