package dn.marjan.tmdb.presentation.movies.details.components

import dn.marjan.tmdb.domain.entity.MovieCredits
import dn.marjan.tmdb.domain.entity.MovieDetails
import dn.marjan.tmdb.domain.entity.MoviePictures

data class MoviePrimaryDetailsState(
    val isLoading: Boolean = false,
    val movieDetails: MovieDetails? = null ,
    val hasError: Boolean = false
)
data class MoviePicturesState(
    val isLoading: Boolean = false,
    val  moviePictures: List<String> = emptyList(),
)
data class MovieCreditStates(
    val isLoading: Boolean = false,
    val  movieCredits: List<MovieCredits> = emptyList(),
)