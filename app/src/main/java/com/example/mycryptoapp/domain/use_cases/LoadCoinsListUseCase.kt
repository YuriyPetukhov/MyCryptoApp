package com.example.mycryptoapp.domain.use_cases

import com.example.mycryptoapp.domain.repository.CryptoAppRepository

class LoadCoinsListUseCase(private val repository: CryptoAppRepository) {
     suspend fun loadCoins(){
        return repository.getCoinsFromApi()
    }
}