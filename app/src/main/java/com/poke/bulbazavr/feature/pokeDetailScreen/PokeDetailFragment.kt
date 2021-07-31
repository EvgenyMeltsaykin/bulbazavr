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
import com.poke.bulbazavr.utils.requestListener
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import javax.inject.Inject

class PokeDetailFragment : BaseFragment(R.layout.fragment_poke_detail), PokeDetailView,
    OnBackPressListener {

    private lateinit var binding: FragmentPokeDetailBinding
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
        binding.ivPokemonAvatar.transitionName = args.pokemonName
        presenter.init(args.pokemonName)
    }

    private fun setupUI() {
        setToolbarTitle(args.pokemonName)
        bottomNavigationHide()
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
        }
    }

    override fun onBackPressed(callback: () -> Unit) {
        binding.mlMain.transitionToStart()
        Handler(Looper.getMainLooper()).postDelayed({ callback.invoke() },
            binding.mlMain.transitionTimeMs - 50)
    }

}