package dn.marjan.tmdb.domain.usecase.movie

import dn.marjan.tmdb.app.base.datastate.DataState
import dn.marjan.tmdb.data.datasources.remote.parameters.MovieDetailsParam
import dn.marjan.tmdb.domain.entity.MovieCredits
import dn.marjan.tmdb.domain.repository.MovieRepository
import javax.inject.Inject

class GetMovieCredits @Inject constructor(val movieRepository: MovieRepository) {

    suspend operator fun invoke(movieId: Int): DataState<List<MovieCredits>> =
        movieRepository.getMovieCredits(MovieDetailsParam(movieId))

}