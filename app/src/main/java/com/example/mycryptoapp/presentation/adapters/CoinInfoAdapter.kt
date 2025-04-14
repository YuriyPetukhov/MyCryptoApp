package com.example.mycryptoapp.presentation.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.mycryptoapp.databinding.ItemCoinInfoBinding
import com.example.mycryptoapp.domain.entity.Coin
import com.squareup.picasso.Picasso


class CoinInfoAdapter(private val context: Context) : ListAdapter<Coin, CoinInfoAdapter.CoinInfoViewHolder>(
    CoinDiffCallback
) {
    var onCoinClickListener: OnCoinClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoinInfoViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemCoinInfoBinding.inflate(inflater, parent, false)
        return CoinInfoViewHolder(binding)
    }


    override fun onBindViewHolder(holder: CoinInfoViewHolder, position: Int) {
        val coin = getItem(position)
        with(holder.binding) {
            tvPrice.text = coin.price.toString()
            tvSymbols.text = "${coin.fromSymbol}/${coin.toSymbol}"
            tvLastUpdate.text = coin.lastUpdate
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

object CoinDiffCallback : DiffUtil.ItemCallback<Coin>() {
    override fun areItemsTheSame(oldItem: Coin, newItem: Coin): Boolean {
        return oldItem.fromSymbol == newItem.fromSymbol
    }

    override fun areContentsTheSame(oldItem: Coin, newItem: Coin): Boolean {
        return oldItem == newItem
    }
}