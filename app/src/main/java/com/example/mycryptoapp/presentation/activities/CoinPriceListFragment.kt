package com.example.mycryptoapp.presentation.activities

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.mycryptoapp.R
import com.example.mycryptoapp.databinding.FragmentCoinPriceListBinding
import com.example.mycryptoapp.domain.entity.Coin
import com.example.mycryptoapp.presentation.adapters.CoinInfoAdapter
import com.example.mycryptoapp.presentation.viewmodels.MainViewModel

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class CoinPriceListFragment : Fragment() {

    private val viewModel: MainViewModel by activityViewModels()
    private lateinit var adapter: CoinInfoAdapter
    private var _binding: FragmentCoinPriceListBinding? = null
    private val binding get() = _binding!!


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCoinPriceListBinding.inflate(inflater, container, false )
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = CoinInfoAdapter(requireContext())
        viewModel.coinsList.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }
        adapter.onCoinClickListener = object: CoinInfoAdapter.OnCoinClickListener{
            override fun onCoinClick(coin: Coin) {
                val fragment = CoinDetailFragment.newInstance(coin.fromSymbol)
                val fragmentManager = requireActivity().supportFragmentManager
                if (isOnePaneMod()) {
                    fragmentManager.beginTransaction()
                        .replace(R.id.main_container, fragment)
                        .addToBackStack(null)
                        .commit()
                } else {
                    fragmentManager.beginTransaction()
                        .replace(R.id.details_container, fragment)
                        .commit()
                }
            }
        }
        val rvCoinPriceList = binding.rvCoinPriceList
        rvCoinPriceList.adapter = adapter
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun isOnePaneMod(): Boolean {
        return requireActivity().findViewById<View?>(R.id.details_container) == null
    }
}