package dn.marjan.tmdb.data.paging.movies

import android.util.Log
import androidx.paging.PagingSource
import dn.marjan.tmdb.data.datasources.remote.parameters.SearchParam
import dn.marjan.tmdb.data.datasources.remote.services.MovieRemoteDataSource
import dn.marjan.tmdb.data.model.MovieResponse
import dn.marjan.tmdb.data.paging.BasePagingSource
import dn.marjan.tmdb.domain.entity.Movie
import javax.inject.Inject

class SearchMoviePagingSource  @Inject constructor(
    private val movieRemoteDataSource: MovieRemoteDataSource,
    private val query: String
) : BasePagingSource<Movie>() {


    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        println("TMDB query is: $query")
        val position = params.key ?: 1
        return try {
            val response: MovieResponse = movieRemoteDataSource.searchMovie(SearchParam(position, query))
            val movies = response.toEntity()
            LoadResult.Page(
                data = movies,
                prevKey = if (position == 1) null else position,
                nextKey = if (movies.isNotEmpty()) position + 1 else null,
            )
        } catch (error: Exception) {
            LoadResult.Error(error)
        }
    }
}