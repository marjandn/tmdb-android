package dn.marjan.tmdb.presentation.tvshows.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import dn.marjan.tmdb.app.base.datastate.DataState
import dn.marjan.tmdb.app.constants.TvshowType
import dn.marjan.tmdb.data.datasources.remote.parameters.PagingParam
import dn.marjan.tmdb.domain.entity.Tvshow
import dn.marjan.tmdb.domain.usecase.tvshow.GetFeaturedTvshows
import dn.marjan.tmdb.domain.usecase.tvshow.GetPopularTvshows
import dn.marjan.tmdb.presentation.tvshows.components.AllTvshowsState
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class AllTvshowsViewModel @Inject constructor(
    private val getPopularTvshow: GetPopularTvshows,
    private val getFeaturedTvshow: GetFeaturedTvshows,
    private val dispatcher: CoroutineDispatcher,
) : ViewModel() {

    private var _tvshowPager: Flow<PagingData<Tvshow>> = MutableStateFlow(PagingData.empty())
    val tvshowPager: Flow<PagingData<Tvshow>> get() = _tvshowPager


    fun getBodyData(bodyContentType: TvshowType) {
        when (bodyContentType) {
            TvshowType.populraTvshows -> getPopularTvshow()
            else -> getFeaturedTvshow()
        }
    }

    private fun getPopularTvshow() {
        _tvshowPager = getPopularTvshow.invoke()
            .flowOn(dispatcher).cachedIn(viewModelScope)
    }

    private fun getFeaturedTvshow() {
        _tvshowPager = getFeaturedTvshow.invoke()
            .flowOn(dispatcher).cachedIn(viewModelScope)
    }


}