package dn.marjan.tmdb.domain.usecase.movie

import dn.marjan.tmdb.app.base.datastate.DataState
import dn.marjan.tmdb.domain.entity.Movie
import dn.marjan.tmdb.domain.repository.MovieRepository
import javax.inject.Inject

class GetFirstTenPopularMovies @Inject constructor(private val movieRepository: MovieRepository) {

    suspend operator fun invoke(): DataState<List<Movie>> = movieRepository.getFirstTenPopularMovies()
}