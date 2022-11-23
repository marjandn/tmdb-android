package dn.marjan.tmdb.presentation.tvshows.components

import androidx.annotation.StringRes
import dn.marjan.tmdb.domain.entity.Movie
import dn.marjan.tmdb.domain.entity.Tvshow


data class AllTvshowsState(
    val isLoading: Boolean = false,
    val tvshows: List<Tvshow> = emptyList(),
    val errorMessage: String = ""
)