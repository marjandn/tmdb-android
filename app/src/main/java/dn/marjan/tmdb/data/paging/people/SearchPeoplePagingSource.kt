package dn.marjan.tmdb.data.paging.people

import dn.marjan.tmdb.data.datasources.remote.parameters.SearchParam
import dn.marjan.tmdb.data.datasources.remote.services.PeopleRemoteDataSource
import dn.marjan.tmdb.data.model.PeopleResponse
import dn.marjan.tmdb.data.paging.BasePagingSource
import dn.marjan.tmdb.domain.entity.People
import javax.inject.Inject

class SearchPeoplePagingSource @Inject constructor(
    private val peopleRemoteDataSource: PeopleRemoteDataSource,
    private val query: String
) : BasePagingSource<People>() {


    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, People> {
        val position = params.key ?: 1

        return try {
            val response: PeopleResponse =
                peopleRemoteDataSource.searchPeople(SearchParam(position, query))
            val people = response.toEntity()
            LoadResult.Page(
                data = people,
                prevKey = if (position == 1) null else position,
                nextKey = if (people.isNotEmpty()) position + 1 else null,
            )
        } catch (error: Exception) {
            LoadResult.Error(error)
        }
    }
}