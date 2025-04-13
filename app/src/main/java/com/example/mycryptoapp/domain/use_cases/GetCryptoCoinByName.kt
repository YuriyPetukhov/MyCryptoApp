package com.example.mycryptoapp.domain.use_cases

import androidx.lifecycle.LiveData
import com.example.mycryptoapp.domain.entity.Coin
import com.example.mycryptoapp.domain.repository.CryptoAppRepository

class GetCryptoCoinByName(private val repository: CryptoAppRepository) {
    suspend fun getCryptoCoinByName(name: String): LiveData<Coin> {
        return repository.getCoinFromDb(name)
    }
}