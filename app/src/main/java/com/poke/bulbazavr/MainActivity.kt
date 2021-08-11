package com.poke.bulbazavr

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.poke.bulbazavr.databinding.ActivityMainBinding
import moxy.MvpAppCompatActivity

class MainActivity : MvpAppCompatActivity(), BottomNavigation, UIControl, FragmentInfoForActivity {
    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!
    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration

    private var currentOpenedFragment: Fragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.findNavController()
        appBarConfiguration =
            AppBarConfiguration(setOf(R.id.pokeListFragment, R.id.pokeFavoritesFragment))
        setSupportActionBar(binding.toolbar)
        setupActionBarWithNavController(navController, appBarConfiguration)
        binding.btvBottomMenu.setupWithNavController(navController)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun bottomNavigationShow() {
        binding.btvBottomMenu.visibility = View.VISIBLE
    }

    override fun bottomNavigationHide() {
        binding.btvBottomMenu.visibility = View.GONE
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    override fun setToolbarTitle(title: String) {
        binding.toolbar.title = title.replaceFirstChar { it.uppercaseChar() }
    }

    override fun loaderVisible(isVisible: Boolean) {
        with(binding) {
            if (isVisible) {
                pbLoader.visibility = View.VISIBLE
                navHostFragment.visibility = View.INVISIBLE
            } else {
                pbLoader.visibility = View.GONE
                navHostFragment.visibility = View.VISIBLE
            }
        }
    }

    override fun onBackPressed() {
        if (currentOpenedFragment != null && currentOpenedFragment is OnBackPressListener) {
            (currentOpenedFragment as OnBackPressListener).onBackPressed {
                currentOpenedFragment = null
                super.onBackPressed()
            }
        } else {
            super.onBackPressed()
        }
    }

    override fun setCurrentVisibleFragment(fragment: Fragment) {
        currentOpenedFragment = fragment
    }
}