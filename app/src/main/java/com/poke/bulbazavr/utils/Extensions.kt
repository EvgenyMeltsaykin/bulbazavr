package com.poke.core.utils

import android.graphics.drawable.Drawable
import com.bumptech.glide.RequestBuilder
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target


fun RequestBuilder<Drawable>.requestListener(
    onLoadFailed: (GlideException?, Any?, Target<Drawable>?, Boolean) -> Unit,
    onResourceReady: (Drawable?, Any?, Target<Drawable>?, DataSource?, Boolean) -> Unit
): RequestBuilder<Drawable> {
    return listener(object : RequestListener<Drawable> {
        override fun onLoadFailed(
            e: GlideException?,
            model: Any?,
            target: Target<Drawable>?,
            isFirstResource: Boolean,
        ): Boolean {
            onLoadFailed(e, model, target, isFirstResource)
            return false
        }

        override fun onResourceReady(
            resource: Drawable?,
            model: Any?,
            target: Target<Drawable>?,
            dataSource: DataSource?,
            isFirstResource: Boolean,
        ): Boolean {
            onResourceReady(resource, model, target, dataSource, isFirstResource)
            return false
        }
    })
}