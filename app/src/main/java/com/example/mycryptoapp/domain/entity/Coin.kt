package com.example.mycryptoapp.domain.entity

data class Coin(
    val fromSymbol: String,
    val toSymbol: String,
    val price: String,
    val minPrice: String,
    val maxPrice: String,
    val lastMarket: String,
    val lastUpdate: String,
    val imageUrl: String
)