package com.poke.bulbazavr.feature.pokeDetailScreen

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.transition.TransitionInflater
import android.view.View
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.poke.bulbazavr.BaseFragment
import com.poke.bulbazavr.OnBackPressListener
import com.poke.bulbazavr.R
import com.poke.bulbazavr.appComponent
import com.poke.bulbazavr.data.PokemonDTO
import com.poke.bulbazavr.databinding.FragmentPokeDetailBinding
import com.poke.bulbazavr.feature.pokeDetailScreen.adapters.*
import com.poke.bulbazavr.utils.Constans.ABILITY_ID
import com.poke.bulbazavr.utils.Constans.END_HEADER_ID
import com.poke.bulbazavr.utils.Constans.STAT_ID
import com.poke.bulbazavr.utils.delegate.adapter.CompositeAdapter
import com.poke.bulbazavr.utils.delegate.adapter.DelegateAdapterItem
import com.poke.bulbazavr.utils.requestListener
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import javax.inject.Inject

class PokeDetailFragment : BaseFragment(R.layout.fragment_poke_detail), PokeDetailView,
    OnBackPressListener {

    private lateinit var binding: FragmentPokeDetailBinding
    private var pokemonInfoAdapter: CompositeAdapter? = null
    private val args by navArgs<PokeDetailFragmentArgs>()

    @Inject
    @InjectPresenter
    lateinit var presenter: PokeDetailPresenter

    @ProvidePresenter
    fun providePresenter() = presenter

    override fun onAttach(context: Context) {
        context.appComponent.inject(this)
        setFragment(this)
        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedElementEnterTransition =
            TransitionInflater.from(requireContext()).inflateTransition(R.transition.shared_image)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentPokeDetailBinding.bind(view)
        postponeEnterTransition()
        setupUI()
        setupAdapter()
        binding.ivPokemonAvatar.transitionName = args.pokemonName
        presenter.init(args.pokemonName)
    }

    private fun setupUI() {
        setToolbarTitle(args.pokemonName)
        bottomNavigationHide()
    }

    private fun setupAdapter() {
        pokemonInfoAdapter = CompositeAdapter.Builder()
            .add(HeaderDelegateAdapter(onClick = { headerType ->
                when (headerType) {
                    STAT_ID -> presenter.visibleActionStats()
                    ABILITY_ID -> presenter.visibleActionAbilities()
                }

            }))
            .add(StatDelegateAdapter())
            .add(AbilityDelegateAdapter())
            .build()
        binding.rvInfo.adapter = pokemonInfoAdapter
    }

    override fun setupInfoPokemon(pokemon: PokemonDTO) {
        with(binding) {
            tvPokemonName.text = pokemon.name.replaceFirstChar { it.uppercaseChar() }
            Glide.with(requireContext())
                .load(pokemon.sprites.frontDefault)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .thumbnail(0.1f)
                .requestListener(
                    onLoadFailed = { _, _, _, _ ->
                        startPostponedEnterTransition()
                        binding.mlMain.transitionToEnd()
                    },
                    onResourceReady = { _, _, _, _, _ ->
                        startPostponedEnterTransition()
                        binding.mlMain.transitionToEnd()
                    }
                )
                .into(ivPokemonAvatar)

            setupInfoInRecyclerView(pokemon)
        }
    }

    private fun setupInfoInRecyclerView(pokemon: PokemonDTO) {
        val items: MutableList<DelegateAdapterItem> = mutableListOf()
        with(items) {
            add(HeaderDelegateItem(getString(R.string.characteristics), STAT_ID))
            pokemon.stats.info?.let { stats ->
                stats.forEach { stat ->
                    val statInfo =
                        stat.copy(statName = stat.statName.replaceFirstChar { it.uppercaseChar() })
                    add(StatDelegateItem(statInfo))
                }
            }
            add(HeaderDelegateItem(getString(R.string.abilities), ABILITY_ID))
            pokemon.abilities.info?.let { abilities ->
                abilities.forEach { ability ->
                    val abilityInfo =
                        ability.copy(name = ability.name.replaceFirstChar { it.uppercaseChar() })
                    add(AbilityDelegateItem(abilityInfo))
                }
            }
            add(HeaderDelegateItem("", END_HEADER_ID))
        }

        pokemonInfoAdapter?.submitList(items)
    }

    override fun onDestroy() {
        super.onDestroy()
        pokemonInfoAdapter = null
    }

    override fun onBackPressed(callback: () -> Unit) {
        binding.mlMain.transitionToStart()
        Handler(Looper.getMainLooper()).postDelayed(
            { callback.invoke() },
            binding.mlMain.transitionTimeMs
        )
    }

}