package dn.marjan.tmdb.domain.repository

import androidx.paging.PagingData
import dn.marjan.tmdb.app.base.datastate.DataState
import dn.marjan.tmdb.data.datasources.remote.parameters.PersonDetailsParam
import dn.marjan.tmdb.domain.entity.People
import dn.marjan.tmdb.domain.entity.PersonPictures
import kotlinx.coroutines.flow.Flow

interface PeopleRepository {
     fun getPopularPeople(): Flow<PagingData<People>>

    suspend fun getFirstTenPopularPeople(): DataState<List<People>>

    fun searchPeople(query: String): Flow<PagingData<People>>

    suspend fun getPersonDetails(detailsParam: PersonDetailsParam): DataState<People>
    suspend fun getPersonPictures(detailsParam: PersonDetailsParam):DataState<List<PersonPictures>>

}