package com.example.mycryptoapp.domain.use_cases

import androidx.lifecycle.LiveData
import com.example.mycryptoapp.domain.entity.Coin
import com.example.mycryptoapp.domain.repository.CryptoAppRepository

class LoadCoinUseCase(private val repository: CryptoAppRepository) {
    fun loadCoin(name: String): LiveData<Coin> {
        return repository.getCoinFromDb(name)
    }
}