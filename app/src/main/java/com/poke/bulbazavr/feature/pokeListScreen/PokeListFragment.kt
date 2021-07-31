package com.poke.bulbazavr.feature.pokeListScreen

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.core.view.doOnPreDraw
import androidx.navigation.findNavController
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.poke.bulbazavr.BaseFragment
import com.poke.bulbazavr.R
import com.poke.bulbazavr.appComponent
import com.poke.bulbazavr.data.PokemonDTO
import com.poke.bulbazavr.databinding.FragmentPokeListBinding
import com.poke.bulbazavr.databinding.PokemonListItemBinding
import com.poke.bulbazavr.feature.pokeListScreen.adapters.PokemonsAdapter
import com.poke.bulbazavr.utils.Constans.LOAD_THRESHOLD
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import javax.inject.Inject


class PokeListFragment : BaseFragment(R.layout.fragment_poke_list), PokeListView {

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
        postponeEnterTransition()
        binding.rvPokemons.doOnPreDraw { startPostponedEnterTransition() }
        bottomNavigationShow()
        setAdapter()
    }

    private fun setAdapter() {
        with(binding) {
            pokemonsAdapter = PokemonsAdapter(
                onPokemonClick = { pokemon, rvItemBinding ->
                    presenter.onPokemonClick(pokemon, rvItemBinding)
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

    override fun navigateToDetailPokemon(name: String, rvItemBinding: PokemonListItemBinding) {
        val action = PokeListFragmentDirections.actionPokeListFragmentToPokeDetailFragment(name)
        val extras = FragmentNavigatorExtras(
            rvItemBinding.ivPokemonAvatar to name,
        )
        binding.root.findNavController().navigate(action, extras)
    }

    override fun setPokemons(pokemons: List<PokemonDTO>) {
        pokemonsAdapter?.submitList(pokemons)
    }

    override fun showLoader() {
        loaderVisible(isVisible = true)
    }

    override fun hideLoader() {
        loaderVisible(isVisible = false)
    }

    override fun showRecyclerViewLoader() {
        binding.pbRvLoader.visibility = View.VISIBLE
    }

    override fun hideRecyclerViewLoader() {
        binding.pbRvLoader.visibility = View.GONE
    }
}