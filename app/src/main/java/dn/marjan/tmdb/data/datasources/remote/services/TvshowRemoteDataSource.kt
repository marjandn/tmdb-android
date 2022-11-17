package dn.marjan.tmdb.data.datasources.remote.services

import dn.marjan.tmdb.app.base.error.ServerException
import dn.marjan.tmdb.data.datasources.remote.client.RestClient
import dn.marjan.tmdb.data.datasources.remote.parameters.PagingParam
import dn.marjan.tmdb.data.datasources.remote.parameters.SearchParam
import dn.marjan.tmdb.data.datasources.remote.parser.JsonParser
import dn.marjan.tmdb.data.model.MovieResponse
import dn.marjan.tmdb.data.model.TvshowResponse
import dn.marjan.tmdb.domain.entity.Tvshow
import okhttp3.ResponseBody
import javax.inject.Inject

interface TvshowRemoteDataSource {
    suspend fun getPopularTvshows(pageParam: PagingParam): TvshowResponse
    suspend fun getFeatureTvshows(pageParam: PagingParam): TvshowResponse

    suspend fun searchTvshow(searchParam: SearchParam): TvshowResponse
}

class TvshowRemoteDataSourceImpl @Inject constructor(
    private val restClient: RestClient,
    private val jsonParser: JsonParser
) : TvshowRemoteDataSource {

    override suspend fun getPopularTvshows(pageParam: PagingParam): TvshowResponse {
        try {
            val response: ResponseBody = restClient.getRequest("tv/popular", pageParam.toJson())
            return   jsonParser.fromJson<TvshowResponse>(response.string() , TvshowResponse::class.java)
        } catch (error: ServerException) {
                  throw ServerException(error.errorMessage)
        }
    }

    override suspend fun getFeatureTvshows(pageParam: PagingParam): TvshowResponse {
        try {
            val response: ResponseBody = restClient.getRequest("tv/top_rated", pageParam.toJson())
            return   jsonParser.fromJson<TvshowResponse>(response.string() , TvshowResponse::class.java)
        } catch (error: ServerException) {
                  throw ServerException(error.errorMessage)
        }
    }

    override suspend fun searchTvshow(searchParam: SearchParam): TvshowResponse {
        try {
            val response: ResponseBody = restClient.getRequest("search/tv", searchParam.toJson())
            return   jsonParser.fromJson<TvshowResponse>(response.string() , TvshowResponse::class.java)
        } catch (error: ServerException) {
                  throw ServerException(error.errorMessage)
        }
    }


}