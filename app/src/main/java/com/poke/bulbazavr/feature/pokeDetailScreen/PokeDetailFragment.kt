package com.poke.bulbazavr.feature.pokeDetailScreen

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.navArgs
import com.poke.bulbazavr.BaseFragment
import com.poke.bulbazavr.R
import com.poke.bulbazavr.databinding.FragmentPokeDetailBinding

class PokeDetailFragment : BaseFragment(R.layout.fragment_poke_detail) {

    private lateinit var binding: FragmentPokeDetailBinding
    private val args by navArgs<PokeDetailFragmentArgs>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentPokeDetailBinding.bind(view)
        setToolbarTitle(args.pokemonName)
        bottomNavigationHide()
    }
}