package com.poke.bulbazavr

import android.widget.Toast
import moxy.MvpAppCompatFragment

open class BaseFragment(layoutId: Int) : MvpAppCompatFragment(layoutId) {

    fun showToast(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    protected fun bottomNavigationShow() {
        (activity as BottomNavigation).bottomNavigationShow()
    }

    protected fun bottomNavigationHide() {
        (activity as BottomNavigation).bottomNavigationHide()
    }

}