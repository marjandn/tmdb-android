package dn.marjan.tmdb.domain.usecase.movie

import dn.marjan.tmdb.app.base.datastate.DataState
import dn.marjan.tmdb.data.datasources.remote.parameters.MovieDetailsParam
import dn.marjan.tmdb.domain.entity.MoviePictures
import dn.marjan.tmdb.domain.repository.MovieRepository
import javax.inject.Inject

class GetMoviePictures @Inject constructor(val movieRepository: MovieRepository) {

    suspend operator fun invoke(movieId: Int): List<String> {
        val response = movieRepository.getMoviePictures(MovieDetailsParam(movieId))

        return if (response is DataState.DataSuccessState) response.data.map { it.posterPath }
        else emptyList()
    }

}