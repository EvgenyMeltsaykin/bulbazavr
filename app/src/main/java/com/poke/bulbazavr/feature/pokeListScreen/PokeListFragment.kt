package com.poke.bulbazavr.feature.pokeListScreen

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.poke.bulbazavr.R
import com.poke.bulbazavr.appComponent
import com.poke.bulbazavr.data.Pokemon
import com.poke.bulbazavr.databinding.FragmentPokeListBinding
import com.poke.bulbazavr.feature.pokeListScreen.adapters.PokemonsAdapter
import com.poke.bulbazavr.utils.Constans.LOAD_THRESHOLD
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import javax.inject.Inject


class PokeListFragment : MvpAppCompatFragment(R.layout.fragment_poke_list), PokeListView {

    private lateinit var binding: FragmentPokeListBinding
    private var pokemonsAdapter: PokemonsAdapter? = null

    @Inject
    @InjectPresenter
    lateinit var presenter: PokeListPresenter

    @ProvidePresenter
    fun providePresenter() = presenter

    override fun onAttach(context: Context) {
        context.appComponent.inject(this)
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentPokeListBinding.bind(view)
        setAdapter()
    }

    private fun setAdapter() {
        with(binding) {
            pokemonsAdapter = PokemonsAdapter(
                onPokemonClick = { name ->
                    presenter.onPokemonClick()
                }
            )
            rvPokemons.adapter = pokemonsAdapter
            rvPokemons.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                    val totalItemCount = layoutManager.itemCount
                    val lastVisible = layoutManager.findLastVisibleItemPosition()
                    val endHasBeenReached = lastVisible + LOAD_THRESHOLD >= totalItemCount
                    if (totalItemCount > 0 && endHasBeenReached) {
                        presenter.loadNextPage()
                    }
                }
            })
        }
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
        pokemonsAdapter?.notifyDataSetChanged()
    }
}