package com.example.mycryptoapp.data.repository

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.example.mycryptoapp.data.api.ApiFactory
import com.example.mycryptoapp.data.database.AppDatabase
import com.example.mycryptoapp.data.database.CoinDbModel
import com.example.mycryptoapp.data.mappers.CoinMapper
import com.example.mycryptoapp.domain.entity.Coin
import com.example.mycryptoapp.domain.repository.CryptoAppRepository

class CryptoAppRepositoryImpl(application: Application) : CryptoAppRepository {
    private val apiService = ApiFactory.apiService
    private val coinDao = AppDatabase.getInstance(application).coinDao()
    private val mapper = CoinMapper()
    override suspend fun getCoinsFromApi() {
        try {
            val coinsNames = apiService.getTopCoinsNames(limit = 50)
            val apiResponse = mapper.mapApiResponseToString(coinsNames)

            val rawDataDto = apiService.getPriceList(fSyms = apiResponse)
            val dbModelList = mapper.mapFromRawDataToDbModel(rawDataDto)

            setCoinsToDb(dbModelList)

        } catch (e: Exception) {
            Log.e(
                "CryptoAppRepositoryImpl",
                "Ошибка: ${e.localizedMessage}, класс: ${e::class.java.simpleName}"
            )
        }
    }


    override suspend fun setCoinsToDb(list: List<CoinDbModel>) {
        coinDao.insertPriceList(list)
    }

    override fun getCoinListFromDb(): LiveData<List<Coin>> {
        return coinDao.getPriceList().map { mapper.mapListCoinDbModeToListCoinDomain(it) }

    }

    override suspend fun getCoinFromDb(name: String): LiveData<Coin> {
        val coin = coinDao.getPriceInfoAboutCoin(name)
        return coin.map { mapper.mapFromDbModelToDomain(it) }
    }

}