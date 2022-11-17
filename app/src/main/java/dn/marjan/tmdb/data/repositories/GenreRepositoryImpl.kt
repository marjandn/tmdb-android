package dn.marjan.tmdb.data.repositories

import dn.marjan.tmdb.app.base.datastate.DataState
import dn.marjan.tmdb.app.base.error.ServerException
import dn.marjan.tmdb.data.datasources.remote.services.GenreRemoteDataSource
import dn.marjan.tmdb.data.model.GenreResponse
import dn.marjan.tmdb.domain.entity.Genre
import dn.marjan.tmdb.domain.repository.GenreRepository
import javax.inject.Inject

class GenreRepositoryImpl @Inject constructor(private val remoteDataSource: GenreRemoteDataSource): GenreRepository {
    override suspend fun getMovieGenres(): DataState<List<Genre>> {
        return try{
            val response: GenreResponse = remoteDataSource.getMovieGenres()
            DataState.DataSuccessState(data = response.toEntity())
        }catch (error: ServerException){
            DataState.DataFailedState(error.errorMessage)
        }
    }

    override suspend fun getTvshowGenres(): DataState<List<Genre>> {
        return try{
            val response: GenreResponse = remoteDataSource.getTvshowGenres()
            DataState.DataSuccessState(data = response.toEntity())
        }catch (error: ServerException){
            DataState.DataFailedState(error.errorMessage)
        }
    }
}