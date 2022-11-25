package dn.marjan.tmdb.data.repositories

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import dn.marjan.tmdb.app.base.datastate.DataState
import dn.marjan.tmdb.app.base.error.ServerException
import dn.marjan.tmdb.data.datasources.remote.parameters.PagingParam
import dn.marjan.tmdb.data.datasources.remote.parameters.PersonDetailsParam
import dn.marjan.tmdb.data.datasources.remote.services.PeopleRemoteDataSource
import dn.marjan.tmdb.data.model.PeopleResponse
import dn.marjan.tmdb.data.model.PersonDetailsResponse
import dn.marjan.tmdb.data.model.PersonPicturesResponse
import dn.marjan.tmdb.data.paging.people.PopularPeoplePagingSource
import dn.marjan.tmdb.data.paging.people.SearchPeoplePagingSource
import dn.marjan.tmdb.domain.entity.People
import dn.marjan.tmdb.domain.repository.PeopleRepository
import dn.marjan.tmdb.domain.entity.PersonPictures
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PeopleRepositoryImpl @Inject constructor(
    private val peopleRemoteDataSource: PeopleRemoteDataSource,
    private val pagingConfig: PagingConfig,
    private val popularPeoplePagingSource: PopularPeoplePagingSource
) :
    PeopleRepository {

    override fun getPopularPeople(): Flow<PagingData<People>> = Pager(pagingConfig) {
        popularPeoplePagingSource
    }.flow

    override suspend fun getFirstTenPopularPeople(): DataState<List<People>> {
        return try {
            val res: PeopleResponse = peopleRemoteDataSource.getPopularPeople(PagingParam(1))
            DataState.DataSuccessState(data = res.toEntity().take(10))
        } catch (error: ServerException) {
            DataState.DataFailedState(error.errorMessage)
        }
    }

    override  fun searchPeople(query: String): Flow<PagingData<People>> =Pager(pagingConfig){
        SearchPeoplePagingSource(
            peopleRemoteDataSource, query
        )
    }.flow

    override suspend fun getPersonDetails(detailsParam: PersonDetailsParam): DataState<People> {
        return try {
            val res: PersonDetailsResponse = peopleRemoteDataSource.getPersonDetails(detailsParam)
            DataState.DataSuccessState(data = res.toEntity())
        } catch (error: ServerException) {
            DataState.DataFailedState(error.errorMessage)
        }
    }

    override suspend fun getPersonPictures(detailsParam: PersonDetailsParam): DataState<List<PersonPictures>> {
        return try {
            val res: PersonPicturesResponse = peopleRemoteDataSource.getPersonPictures(detailsParam)
            DataState.DataSuccessState(data = res.toEntity())
        } catch (error: ServerException) {
            DataState.DataFailedState(error.errorMessage)
        }
    }
}