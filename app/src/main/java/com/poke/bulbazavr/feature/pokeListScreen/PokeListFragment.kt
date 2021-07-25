package com.poke.bulbazavr.feature.pokeListScreen

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.poke.bulbazavr.App
import com.poke.bulbazavr.R
import com.poke.bulbazavr.data.Pokemon
import com.poke.bulbazavr.databinding.FragmentPokeListBinding
import com.poke.bulbazavr.feature.pokeListScreen.adapters.PokemonsAdapter
import dagger.android.support.AndroidSupportInjection
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import javax.inject.Inject

class PokeListFragment : MvpAppCompatFragment(R.layout.fragment_poke_list), PokeListView {

    private lateinit var binding: FragmentPokeListBinding
    private var pokemonsAdapter: PokemonsAdapter? = null

    @InjectPresenter
    lateinit var presenter: PokeListPresenter


    override fun onAttach(context: Context) {
       // (activity?.application as App).appComponent.inject(this)
        super.onAttach(context)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentPokeListBinding.bind(view)
        pokemonsAdapter = PokemonsAdapter(
            onPokemonClick = { name ->
                presenter.onPokemonClick()
            }
        )

        binding.rvPokemons.adapter = pokemonsAdapter
    }

    override fun onDestroy() {
        super.onDestroy()
        pokemonsAdapter = null
    }

    override fun navigateToDetailPokemon() {
        val action = PokeListFragmentDirections.actionPokeListFragmentToPokeDetailFragment()
        binding.root.findNavController().navigate(action)
    }

    override fun setPokemons(pokemons: List<Pokemon>) {
        pokemonsAdapter?.submitList(pokemons)
    }
}