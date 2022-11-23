package dn.marjan.tmdb.presentation.people.viewmodel

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
import dn.marjan.tmdb.data.datasources.remote.parameters.PagingParam
import dn.marjan.tmdb.domain.entity.Movie
import dn.marjan.tmdb.domain.entity.People
import dn.marjan.tmdb.domain.usecase.people.GetPopularPeople
import dn.marjan.tmdb.presentation.people.components.AllPeopleState
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class AllPeopleViewModel @Inject constructor(
    private val getPopularPeople: GetPopularPeople,
    private val dispatcher: CoroutineDispatcher,
) : ViewModel() {

    private var _peoplePager: Flow<PagingData<People>> =
        MutableStateFlow<PagingData<People>>(PagingData.empty())
    val peoplePager: Flow<PagingData<People>> get() = _peoplePager



    fun getBodyData() {
        getPopularPeople()
    }

    private fun getPopularPeople() {
        _peoplePager = getPopularPeople.invoke()
            .flowOn(dispatcher)
            .cachedIn(viewModelScope)
    }



}