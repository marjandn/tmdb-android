package dn.marjan.tmdb.presentation.dashboard.viewmodel

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dn.marjan.tmdb.app.base.datastate.DataState
import dn.marjan.tmdb.data.datasources.remote.parameters.PagingParam
import dn.marjan.tmdb.domain.entity.Movie
import dn.marjan.tmdb.domain.entity.People
import dn.marjan.tmdb.domain.entity.Tvshow
import dn.marjan.tmdb.domain.usecase.movie.*
import dn.marjan.tmdb.domain.usecase.people.GetFirstTenPopularPeople
import dn.marjan.tmdb.domain.usecase.people.GetPopularPeople
import dn.marjan.tmdb.domain.usecase.tvshow.GetFeaturedTvshows
import dn.marjan.tmdb.domain.usecase.tvshow.GetFirstTenFeaturedTvshow
import dn.marjan.tmdb.domain.usecase.tvshow.GetFirstTenPopularTvshow
import dn.marjan.tmdb.domain.usecase.tvshow.GetPopularTvshows
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val getPopularMoviesFirst: GetFirstTenPopularMovies,
    private val getFeaturedMovies: GetFirstTenFeaturedMovies,
    private val getUpcomingMovies: GetFirstTenUpcomingMovies,
    private val getPopularTvshows: GetFirstTenPopularTvshow,
    private val getFeaturedTvshows: GetFirstTenFeaturedTvshow,
    private val getPopularPeople: GetFirstTenPopularPeople,
    private val dispatcher: CoroutineDispatcher
) : ViewModel() {

    var popularMovies = mutableStateListOf<Movie>()
        private set

    var featuredMovies = mutableStateListOf<Movie>()
        private set

    var upComingMovies = mutableStateListOf<Movie>()
        private set

    var popoularTvshows = mutableStateListOf<Tvshow>()
        private set

    var featuredTvshow = mutableStateListOf<Tvshow>()
        private set

    var popularPeople = mutableStateListOf<People>()
        private set


    init {
        getPopularMovies()
        getFeaturedMovies()
        getUpcomingMovies()
        getPopularTvshows()
        getFeaturedTvshows()
        getPopularPeople()
    }


    private fun getPopularPeople() {
        viewModelScope.launch(dispatcher) {
            val res: DataState<List<People>> = getPopularPeople.invoke()
            if (res is DataState.DataSuccessState) popularPeople.addAll(res.data)
        }

    }


    private fun getPopularMovies() {
        viewModelScope.launch(dispatcher) {
            val res: DataState<List<Movie>> = getPopularMoviesFirst.invoke()
            if (res is DataState.DataSuccessState) popularMovies.addAll(res.data)
        }

    }

    private fun getFeaturedMovies() {
        viewModelScope.launch(dispatcher) {
            val res: DataState<List<Movie>> = getFeaturedMovies.invoke()
            if (res is DataState.DataSuccessState) featuredMovies.addAll(res.data)
        }
    }

    private fun getUpcomingMovies() {
        viewModelScope.launch(dispatcher) {
            val res: DataState<List<Movie>> = getUpcomingMovies.invoke()
            if (res is DataState.DataSuccessState) upComingMovies.addAll(res.data)
        }
    }

    private fun getPopularTvshows() {
        viewModelScope.launch(dispatcher) {
            val response: DataState<List<Tvshow>> = getPopularTvshows.invoke()
            if (response is DataState.DataSuccessState) popoularTvshows.addAll(response.data)
        }
    }

    private fun getFeaturedTvshows() {
        viewModelScope.launch(dispatcher) {
            val response: DataState<List<Tvshow>> = getFeaturedTvshows.invoke()
            if (response is DataState.DataSuccessState) featuredTvshow.addAll(response.data)

        }
    }
}
