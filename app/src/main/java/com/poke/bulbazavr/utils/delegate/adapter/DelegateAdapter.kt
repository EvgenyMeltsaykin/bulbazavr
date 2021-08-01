package com.poke.bulbazavr.utils.delegate.adapter

import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.LinearLayout
import androidx.recyclerview.widget.RecyclerView
import com.poke.bulbazavr.R


abstract class DelegateAdapter<M, in VH : RecyclerView.ViewHolder>(val modelClass: Class<out M>) {

    abstract fun createViewHolder(parent: ViewGroup): RecyclerView.ViewHolder
    abstract fun bindViewHolder(
        item: M,
        viewHolder: VH,
        payloads: List<DelegateAdapterItem.Payloadable>
    )

    fun animationAppearanceSmooth(viewToAnimate: View) {
        val animation: Animation =
            AnimationUtils.loadAnimation(viewToAnimate.context, R.anim.smooth_animation)
        viewToAnimate.startAnimation(animation)
    }

    open fun onViewRecycled(viewHolder: VH) = Unit
    open fun onViewDetachedFromWindow(viewHolder: VH) = Unit
    open fun onViewAttachedToWindow(viewHolder: VH) = Unit

    //установка видимости элемента
    fun setVisibility(curV: View, visible: Boolean, parentId: Int) {
        //найдем блок, благодаря которому будем сдвигать текст
        val vPadding = curV.findViewById<LinearLayout>(R.id.tv_title)
        val params: LinearLayout.LayoutParams
        if (visible) {
            params = LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
            if (vPadding != null) {
                if (parentId >= 0) { //это дочерний элемент, делаем отступ
                    vPadding.setPadding(80, 0, 0, 0)
                } else {
                    vPadding.setPadding(0, 0, 0, 0)
                }
            }
        } else params = LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0)
        curV.layoutParams = params
    }
}