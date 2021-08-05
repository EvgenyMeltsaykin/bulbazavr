package com.poke.bulbazavr.feature.pokeFavoritesScreen

import android.content.Context
import android.os.Bundle
import android.view.View
import com.poke.bulbazavr.BaseFragment
import com.poke.bulbazavr.R
import com.poke.bulbazavr.appComponent
import com.poke.bulbazavr.data.FavoritePokemonDTO
import com.poke.bulbazavr.databinding.FragmentPokeFavoritesBinding
import com.poke.bulbazavr.feature.pokeFavoritesScreen.adapters.FavoritePokemonsAdapter
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import javax.inject.Inject

class PokeFavoritesFragment : BaseFragment(R.layout.fragment_poke_favorites), PokeFavoritesView {
    private lateinit var binding: FragmentPokeFavoritesBinding
    private var pokemonsAdapter: FavoritePokemonsAdapter? = null

    @Inject
    @InjectPresenter
    lateinit var presenter: PokeFavoritesPresenter

    @ProvidePresenter
    fun providePresenter() = presenter

    override fun onAttach(context: Context) {
        context.appComponent.inject(this)
        super.onAttach(context)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentPokeFavoritesBinding.bind(view)
        bottomNavigationShow()
        setupAdapter()
    }

    private fun setupAdapter() {
        with(binding) {
            pokemonsAdapter = FavoritePokemonsAdapter(
                onPokemonClick = { pokemon, rvItemBinding ->
                    //presenter.onPokemonClick(pokemon, rvItemBinding)
                }
            )
            rvPokemons.adapter = pokemonsAdapter
        }
    }

    override fun showFavoritePokemons(pokemons: List<FavoritePokemonDTO>) {
        pokemonsAdapter?.submitList(pokemons)
    }

}