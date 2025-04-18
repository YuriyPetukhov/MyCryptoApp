package com.example.mycryptoapp.presentation.activities

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.mycryptoapp.databinding.FragmentCoinDetailBinding
import com.example.mycryptoapp.presentation.viewmodels.MainViewModel
import com.squareup.picasso.Picasso

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [CoinDetailFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CoinDetailFragment : Fragment() {


    private val viewModel: MainViewModel by activityViewModels()
    private var _binding: FragmentCoinDetailBinding? = null
    private val binding get() = _binding!!


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCoinDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val name = arguments?.getString(ARG_PARAM1)
        if (name != null) {
            viewModel.loadCoin(name).observe(viewLifecycleOwner) { coin ->
                if (coin == null) {
                    Toast.makeText(requireContext(), "Монета недоступна", Toast.LENGTH_SHORT).show()
                    parentFragmentManager.popBackStack()
                } else {
                    with(binding) {
                        tvPrice.text = coin.price
                        tvMinPrice.text = coin.minPrice
                        tvMaxPrice.text = coin.maxPrice
                        tvLastMarket.text = coin.lastMarket
                        tvLastUpdate.text = coin.lastUpdate
                        tvFromSymbol.text = coin.fromSymbol
                        tvToSymbol.text = coin.toSymbol
                        Picasso.get().load(coin.imageUrl).into(ivLogoCoin)
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        fun newInstance(name: String) =
            CoinDetailFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, name)
                }
            }
    }
}