package dn.marjan.tmdb.data.paging.people

import dn.marjan.tmdb.data.datasources.remote.parameters.PagingParam
import dn.marjan.tmdb.data.datasources.remote.services.PeopleRemoteDataSource
import dn.marjan.tmdb.data.model.PeopleResponse
import dn.marjan.tmdb.data.paging.BasePagingSource
import dn.marjan.tmdb.domain.entity.People
import javax.inject.Inject

class PopularPeoplePagingSource @Inject constructor(private val peopleRemoteDataSource: PeopleRemoteDataSource): BasePagingSource<People>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, People> {
        val position = params.key ?: 1

        return try{
            val response : PeopleResponse = peopleRemoteDataSource.getPopularPeople(PagingParam(position))
            val people: List<People> = response.toEntity()

            LoadResult.Page(
                data = people,
                prevKey = getPrevKey(position),
                nextKey = getNextKey(position, people)
            )
        }catch (error: Exception){
            LoadResult.Error(error)
        }
    }
}