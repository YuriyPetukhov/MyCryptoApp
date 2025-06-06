package com.example.mycryptoapp.data.dto

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class CoinInfoDto (
        @SerializedName("Name")
        @Expose
        val name: String? = null
    )