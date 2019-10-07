package com.tartagliaeg.tmdb.tools_android

import androidx.fragment.app.FragmentActivity

interface Navigation<Params> {
    fun navigate(activity: FragmentActivity, params: Params)
}
