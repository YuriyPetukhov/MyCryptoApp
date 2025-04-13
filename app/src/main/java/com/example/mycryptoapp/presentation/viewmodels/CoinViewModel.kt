package com.example.mycryptoapp.presentation.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.mycryptoapp.data.repository.CryptoAppRepositoryImpl
import com.example.mycryptoapp.domain.use_cases.LoadCoinsUseCase
import kotlinx.coroutines.launch

class CoinViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = CryptoAppRepositoryImpl(application)

    private val loadCoinsUseCase = LoadCoinsUseCase(repository)

    private val scope = viewModelScope

    val coinsList = repository.getCoinListFromDb()


    init {
        loadCoins()
    }

    fun loadCoins() {
        scope.launch {
            loadCoinsUseCase.loadCoins()
        }
    }

}