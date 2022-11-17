package dn.marjan.tmdb.data.datasources.remote.services

import dn.marjan.tmdb.app.base.error.ServerException
import dn.marjan.tmdb.data.datasources.remote.client.RestClient
import dn.marjan.tmdb.data.datasources.remote.parameters.PagingParam
import dn.marjan.tmdb.data.datasources.remote.parameters.SearchParam
import dn.marjan.tmdb.data.datasources.remote.parser.JsonParser
import dn.marjan.tmdb.data.model.MovieResponse
import okhttp3.ResponseBody
import javax.inject.Inject


interface MovieRemoteDataSource {

    suspend fun getPopularMovies(pageParam: PagingParam ): MovieResponse
    suspend fun getUpComingMovies(pageParam: PagingParam): MovieResponse
    suspend fun getFeatureMoves(pageParam: PagingParam): MovieResponse

    suspend fun searchMovie(searchParam: SearchParam): MovieResponse
}

class MovieRemoteDataSourceImpl @Inject constructor(
    private val restClient: RestClient,
    private val jsonParser: JsonParser
) :
    MovieRemoteDataSource {

    // todo: Replace ResponseBody(from Retrofit lib) class with a wrapper class

    override suspend fun getPopularMovies(pageParam: PagingParam): MovieResponse {
        try {
            val res: ResponseBody =
                restClient.getRequest(url = "movie/popular", data = pageParam.toJson())

            return jsonParser.fromJson<MovieResponse>(res.string(), MovieResponse::class.java)

        } catch (error: ServerException) {
                  throw ServerException(error.errorMessage)
        }
    }

    override suspend fun getUpComingMovies(pageParam: PagingParam): MovieResponse {
        try {
            val res: ResponseBody =
                restClient.getRequest(url = "movie/upcoming", data = pageParam.toJson())

            return jsonParser.fromJson<MovieResponse>(res.string(), MovieResponse::class.java)

        } catch (error: ServerException) {
                  throw ServerException(error.errorMessage)
        }
    }

    override suspend fun getFeatureMoves(pageParam: PagingParam): MovieResponse {
        try {
            val res: ResponseBody =
                restClient.getRequest(url = "movie/top_rated", data = pageParam.toJson())

            return jsonParser.fromJson<MovieResponse>(res.string(), MovieResponse::class.java)

        } catch (error: ServerException) {
                  throw ServerException(error.errorMessage)
        }
    }

    override suspend fun searchMovie(searchParam: SearchParam): MovieResponse {
        try {
            val res: ResponseBody =
                restClient.getRequest(url = "search/movie", data = searchParam.toJson())

            return jsonParser.fromJson<MovieResponse>(res.string(), MovieResponse::class.java)

        } catch (error: ServerException) {
                  throw ServerException(error.errorMessage)
        }
    }


}
