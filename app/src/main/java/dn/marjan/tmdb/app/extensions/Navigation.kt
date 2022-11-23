package dn.marjan.tmdb.app.extensions

import android.content.Context
import android.content.Intent


inline fun <reified T> Context.navigation(extras: Map<String, String>): Intent =
    Intent(this, T::class.java).apply {
        extras.forEach { (key, value) -> this.putExtra(key, value) }
    }