package com.example.mycryptoapp.presentation.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mycryptoapp.databinding.ItemCoinInfoBinding
import com.example.mycryptoapp.domain.entity.Coin
import com.squareup.picasso.Picasso


class CoinInfoAdapter(private val context: Context) :
    RecyclerView.Adapter<CoinInfoAdapter.CoinInfoViewHolder>() {

    var coinList: List<Coin> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    var onCoinClickListener: OnCoinClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoinInfoViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemCoinInfoBinding.inflate(inflater, parent, false)
        return CoinInfoViewHolder(binding)
    }

    override fun getItemCount() = coinList.size

    override fun onBindViewHolder(holder: CoinInfoViewHolder, position: Int) {
        val coin = coinList[position]
        with(holder.binding) {
            tvPrice.text = coin.price.toString()
            tvSymbols.text = "${coin.fromSymbol}/${coin.toSymbol}"
            tvLastUpdate.text = coin.lastUpdate.toString() // форматируй при необходимости
            Picasso.get().load(coin.imageUrl).into(ivLogoCoin)
            root.setOnClickListener {
                onCoinClickListener?.onCoinClick(coin)
            }
        }

    }

    inner class  CoinInfoViewHolder(
    val binding: ItemCoinInfoBinding
    ) : RecyclerView.ViewHolder(binding.root)

    interface OnCoinClickListener {
        fun onCoinClick(coin: Coin)
    }
}