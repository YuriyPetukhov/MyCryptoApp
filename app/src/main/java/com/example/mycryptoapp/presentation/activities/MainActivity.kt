package com.example.mycryptoapp.presentation.activities

import android.content.res.Configuration
import android.os.Bundle
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

        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        initViewModel()

        val fragmentManager = supportFragmentManager

        if (savedInstanceState == null) {
            setupFragments()
        } else {
            if (isLandscape()) {
                val currentFragment = fragmentManager.findFragmentById(binding.mainContainer.id)
                // Проверяем, если в mainContainer нет CoinPriceListFragment — ставим его
                if (currentFragment !is CoinPriceListFragment) {
                    val priceListFragment = CoinPriceListFragment()
                    fragmentManager.beginTransaction()
                        .replace(binding.mainContainer.id, priceListFragment, "PRICE_LIST_FRAGMENT")
                        .commit()
                }

                // Проверяем, если в mainContainer был CoinDetailFragment, переносим его в detailsContainer
                if (currentFragment is CoinDetailFragment) {
                    val args = currentFragment.arguments
                    fragmentManager.beginTransaction()
                        .remove(currentFragment)
                        .commit()
                    fragmentManager.executePendingTransactions()

                    val newDetailFragment = CoinDetailFragment()
                    newDetailFragment.arguments = args
                    fragmentManager.beginTransaction()
                        .replace(binding.detailsContainer!!.id, newDetailFragment, "DETAIL_FRAGMENT")
                        .commit()
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



