package dn.marjan.tmdb.presentation.movies.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import dn.marjan.tmdb.app.base.datastate.DataState
import dn.marjan.tmdb.app.constants.MoviesType
import dn.marjan.tmdb.data.datasources.remote.parameters.PagingParam
import dn.marjan.tmdb.domain.entity.Movie
import dn.marjan.tmdb.domain.usecase.movie.GetFeaturedMovies
import dn.marjan.tmdb.domain.usecase.movie.GetPopularMovies
import dn.marjan.tmdb.presentation.movies.components.AllMoviesState
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class AllMoviesViewModel @Inject constructor(
    private val getPopularMovies: GetPopularMovies,
    private val getFeaturedMovies: GetFeaturedMovies,
    private val dispatcher: CoroutineDispatcher,
) : ViewModel() {

    private var _moviesPager: Flow<PagingData<Movie>> =
        MutableStateFlow<PagingData<Movie>>(PagingData.empty())
    val moviesPager: Flow<PagingData<Movie>> get() = _moviesPager



    fun getBodyData(bodyContentType: MoviesType) {
        when (bodyContentType) {
            MoviesType.populraMovies -> getPopularMovies()
            else -> getFeaturedMovies()
        }
    }


    private fun getPopularMovies() {
        _moviesPager = getPopularMovies.invoke()
            .flowOn(dispatcher)
            .cachedIn(viewModelScope)
    }


    private fun getFeaturedMovies() {

        _moviesPager = getFeaturedMovies.invoke()
            .flowOn(dispatcher)
            .cachedIn(viewModelScope)
    }


}