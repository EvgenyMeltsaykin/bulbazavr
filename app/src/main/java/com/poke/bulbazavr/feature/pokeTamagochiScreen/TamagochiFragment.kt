package com.poke.bulbazavr.feature.pokeTamagochiScreen

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.poke.bulbazavr.BaseFragment
import com.poke.bulbazavr.OnBackPressListener
import com.poke.bulbazavr.R
import com.poke.bulbazavr.appComponent
import com.poke.bulbazavr.databinding.FragmentTamagochiBinding
import com.poke.core.data.dto.FavoritePokemonDTO
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import javax.inject.Inject

class TamagochiFragment : BaseFragment(R.layout.fragment_tamagochi), TamagochiView,
    OnBackPressListener {
    private lateinit var binding: FragmentTamagochiBinding
    private val args by navArgs<TamagochiFragmentArgs>()

    @Inject
    @InjectPresenter
    lateinit var presenter: TamagochiPresenter

    @ProvidePresenter
    fun providePresenter() = presenter

    override fun onAttach(context: Context) {
        context.appComponent.inject(this)
        super.onAttach(context)
    }

    override fun onResume() {
        super.onResume()
        presenter.updateInfo()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentTamagochiBinding.bind(view)
        setToolbarTitle(args.pokemonName)
        bottomNavigationHide()
        presenter.init(args.pokemonName)
        setupListener()
        //postponeEnterTransition()
    }

    private fun setupListener() {
        with(binding) {
            btnFeed.setOnClickListener {
                presenter.onFeedClick()
            }

            btnPlay.setOnClickListener {
                presenter.onPlayClick()
            }

            btnFullInfo.setOnClickListener {
                presenter.onFullInfoClick()
            }
        }
    }

    override fun onBackPressed(callback: () -> Unit) {
        callback.invoke()
    }

    override fun setupInfo(favoritePokemon: FavoritePokemonDTO) {
        with(binding) {
            Glide.with(requireContext()).load(favoritePokemon.url).into(ivPokemonAvatar)
            pbFood.progress = favoritePokemon.foodIndicator
            pbFun.progress = favoritePokemon.funIndicator
            pbHp.progress = favoritePokemon.hpIndicator
            tvFoodProgress.text = getString(R.string.progress, favoritePokemon.foodIndicator)
            tvFunProgress.text = getString(R.string.progress, favoritePokemon.funIndicator)
            tvHpProgress.text = getString(R.string.progress, favoritePokemon.hpIndicator)
        }
    }

    override fun navigateToFullInfo(pokemonName: String) {
        loaderVisible(true)
        val action =
            TamagochiFragmentDirections.actionTamagochiFragmentToPokeDetailFragment(pokemonName)
        binding.root.findNavController().navigate(action)
    }

    override fun navigateToFavoriteList() {
        binding.root.findNavController().popBackStack()
    }
}