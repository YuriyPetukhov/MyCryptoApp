package com.example.mycryptoapp.domain.repository

import androidx.lifecycle.LiveData
import com.example.mycryptoapp.data.database.CoinDbModel
import com.example.mycryptoapp.domain.entity.Coin

interface CryptoAppRepository {
    suspend fun getCoinsFromApi()

    fun getCoinListFromDb(): LiveData<List<Coin>>

    suspend fun getCoinFromDb(name: String): LiveData<Coin>

    suspend fun setCoinsToDb(list: List<CoinDbModel>)
}