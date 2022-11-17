package dn.marjan.tmdb.data.repositories

import dn.marjan.tmdb.app.base.datastate.DataState
import dn.marjan.tmdb.app.base.error.ServerException
import dn.marjan.tmdb.data.datasources.remote.parameters.PagingParam
import dn.marjan.tmdb.data.datasources.remote.parameters.SearchParam
import dn.marjan.tmdb.data.datasources.remote.services.PeopleRemoteDataSource
import dn.marjan.tmdb.data.model.PeopleResponse
import dn.marjan.tmdb.domain.entity.People
import dn.marjan.tmdb.domain.repository.PeopleRepository
import javax.inject.Inject

class PeopleRepositoryImpl @Inject constructor(private val peopleRemoteDataSource: PeopleRemoteDataSource): PeopleRepository {

    override suspend fun getPopularPeople(pagingParam: PagingParam): DataState<List<People>> {
        return try {
            val res: PeopleResponse = peopleRemoteDataSource.getPopularPeople(pagingParam)
            DataState.DataSuccessState(data = res.toEntity())
        }   catch (error: ServerException){
            DataState.DataFailedState(error.errorMessage)
        }
    }

    override suspend fun searchPeople(searchParam: SearchParam): DataState<List<People>> {
        return try {
            val res: PeopleResponse = peopleRemoteDataSource.searchPeople(searchParam)
            DataState.DataSuccessState(data = res.toEntity())
        }   catch (error: ServerException){
            DataState.DataFailedState(error.errorMessage)
        }
    }
}