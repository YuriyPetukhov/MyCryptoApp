package com.example.mycryptoapp.presentation.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.mycryptoapp.data.repository.CryptoAppRepositoryImpl
import com.example.mycryptoapp.domain.entity.Coin
import com.example.mycryptoapp.domain.use_cases.LoadCoinUseCase
import com.example.mycryptoapp.domain.use_cases.LoadCoinsListUseCase
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = CryptoAppRepositoryImpl(application)

    private val loadCoinsUseCase = LoadCoinsListUseCase(repository)
    private val loadCoinUseCase = LoadCoinUseCase(repository)

    private val scope = viewModelScope

    val coinsList = repository.getCoinListFromDb()


    init {
        loadCoinsList()
    }

    fun loadCoinsList() {
        scope.launch {
            while (isActive) {
                loadCoinsUseCase.loadCoins()
                delay(10000)
            }

        }
    }

    fun loadCoin(name: String): LiveData<Coin> {
        return loadCoinUseCase.loadCoin(name)
    }
}