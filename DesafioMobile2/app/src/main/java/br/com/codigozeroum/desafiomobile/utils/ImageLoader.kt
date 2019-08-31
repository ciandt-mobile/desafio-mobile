/*
 * ImageLoader.kt
 * DesafioMobile
 *
 * Created by Flavio Campos on 30/08/19 05:27
 * Copyright © 2019 Codigo ZeroUm. All rights reserved.
 */

package br.com.codigozeroum.desafiomobile.utils

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions


object ImageLoader {

    fun loadImageWith(context: Context, url: String?, into: ImageView?) {

        url?.let { _ ->
            into?.let {
                Glide.with(context)
                    .load(url)
                    .apply(
                        RequestOptions()
                            .diskCacheStrategy(DiskCacheStrategy.ALL)
                    )
                    .into(into)
            }
        }
    }
}