package dn.marjan.tmdb.di

import androidx.paging.PagingConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dn.marjan.tmdb.data.datasources.remote.services.MovieRemoteDataSource
import dn.marjan.tmdb.data.paging.movies.FeaturedMoviePagingSource
import dn.marjan.tmdb.data.paging.movies.PopularMoviePagingSource
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class PagingModule {

    @Singleton
    @Provides
    fun providePageConfig(): PagingConfig = PagingConfig(pageSize = 20)

    @Singleton
    @Provides
    fun providePopularPagingSource(movieRemoteDataSource: MovieRemoteDataSource): PopularMoviePagingSource =
        PopularMoviePagingSource(movieRemoteDataSource)

    @Singleton
    @Provides
    fun popularFeaturedPagingSource(movieRemoteDataSource: MovieRemoteDataSource): FeaturedMoviePagingSource =
        FeaturedMoviePagingSource(movieRemoteDataSource)


}