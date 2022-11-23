package dn.marjan.tmdb.presentation.movies.components

import androidx.annotation.StringRes
import dn.marjan.tmdb.domain.entity.Movie


data class AllMoviesState(
    val isLoading: Boolean = false,
    val movies: List<Movie> = emptyList(),
    val errorMessage: String = ""
)