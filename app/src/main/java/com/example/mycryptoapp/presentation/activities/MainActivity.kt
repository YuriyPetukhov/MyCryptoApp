package com.example.mycryptoapp.presentation.activities

import android.content.res.Configuration
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.example.mycryptoapp.R
import com.example.mycryptoapp.databinding.ActivityMainBinding
import com.example.mycryptoapp.presentation.viewmodels.MainViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Применяем отступы под системные панели
        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        initViewModel()

        if (savedInstanceState == null) {
            setupFragments()
        } else {
            // Проверяем: если альбомная ориентация
            if (isLandscape()) {
                val fragmentManager = supportFragmentManager
                val coinsPriceListFragment = fragmentManager.findFragmentById(binding.mainContainer.id)
                if (coinsPriceListFragment is CoinDetailFragment) {
                    // Удаляем фрагмент из mainContainer
                    fragmentManager.beginTransaction()
                        .remove(coinsPriceListFragment)
                        .commit()

                    fragmentManager.executePendingTransactions()
                    val detailsContainer = findViewById<View?>(R.id.details_container)
                    if (detailsContainer != null) {
                        // Переносим фрагмент в detailsContainer
                        fragmentManager.beginTransaction()
                            .replace(detailsContainer.id, coinsPriceListFragment)
                            .commit()
                    }
                }
            }
        }
    }



    private fun setupFragments() {
        if (isLandscape()) {
            showTwoFragments()
        } else {
            showSingleFragment()
        }
    }

    private fun showSingleFragment() {
        val coinPriceListFragment = CoinPriceListFragment()
        supportFragmentManager.beginTransaction()
            .replace(R.id.main_container, coinPriceListFragment)
            .commit()

    }

    private fun showTwoFragments() {
        val coinPriceListFragment = CoinPriceListFragment()
        supportFragmentManager.beginTransaction()
            .replace(R.id.main_container, coinPriceListFragment)
            .commit()
        val coinDetailFragment = CoinDetailFragment()
        supportFragmentManager.beginTransaction()
            .replace(R.id.details_container, coinDetailFragment)
            .commit()
    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
    }

    private fun isLandscape() =
        resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE

}



