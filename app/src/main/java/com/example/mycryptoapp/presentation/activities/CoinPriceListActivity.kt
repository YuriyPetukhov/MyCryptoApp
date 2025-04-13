package com.example.mycryptoapp.presentation.activities

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.example.mycryptoapp.databinding.ActivityCoinPriceListBinding
import com.example.mycryptoapp.presentation.adapters.CoinInfoAdapter
import com.example.mycryptoapp.presentation.viewmodels.CoinViewModel

class CoinPriceListActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCoinPriceListBinding
    private lateinit var viewModel: CoinViewModel
    private lateinit var adapter: CoinInfoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        ViewCompat.setOnApplyWindowInsetsListener(binding.coinPriceActivity) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        binding = ActivityCoinPriceListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = CoinInfoAdapter(this)
        binding.rvCoinPriceList.adapter = adapter

        viewModel = ViewModelProvider(this).get(CoinViewModel::class.java)

        viewModel.coinsList.observe(this){
            adapter.coinList = it
        }
    }

}