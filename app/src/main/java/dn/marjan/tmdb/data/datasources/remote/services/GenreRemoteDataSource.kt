package dn.marjan.tmdb.data.datasources.remote.services

import dn.marjan.tmdb.app.base.error.ServerException
import dn.marjan.tmdb.data.datasources.remote.client.RestClient
import dn.marjan.tmdb.data.datasources.remote.parser.JsonParser
import dn.marjan.tmdb.data.model.GenreResponse
import okhttp3.ResponseBody
import javax.inject.Inject

interface GenreRemoteDataSource {
    suspend fun getMovieGenres(): GenreResponse
    suspend fun getTvshowGenres(): GenreResponse
}

class GenreRemoteDataSourceImpl @Inject constructor(
    private val restClient: RestClient,
    private val jsonParser: JsonParser
) : GenreRemoteDataSource {
    override suspend fun getMovieGenres(): GenreResponse {
        try {
            val res: ResponseBody = restClient.getRequest("genre/movie/list")
            return jsonParser.fromJson(res.string(), GenreResponse::class.java)
        } catch (error: ServerException) {
            throw ServerException(error.errorMessage)
        }
    }

    override suspend fun getTvshowGenres(): GenreResponse {
        try {
            val res: ResponseBody = restClient.getRequest("genre/tv/list")
            return jsonParser.fromJson(res.string(), GenreResponse::class.java)
        } catch (error: ServerException) {
            throw ServerException(error.errorMessage)
        }
    }
}