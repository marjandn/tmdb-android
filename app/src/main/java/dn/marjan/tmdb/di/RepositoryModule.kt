package dn.marjan.tmdb.di

import androidx.paging.PagingConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dn.marjan.tmdb.data.datasources.remote.services.MovieRemoteDataSource
import dn.marjan.tmdb.data.datasources.remote.services.PeopleRemoteDataSource
import dn.marjan.tmdb.data.datasources.remote.services.TvshowRemoteDataSource
import dn.marjan.tmdb.data.paging.movies.FeaturedMoviePagingSource
import dn.marjan.tmdb.data.paging.movies.PopularMoviePagingSource
import dn.marjan.tmdb.data.paging.people.PopularPeoplePagingSource
import dn.marjan.tmdb.data.paging.tvshow.FeaturedTvshowPagingSource
import dn.marjan.tmdb.data.paging.tvshow.PopularTvshowPagingSource
import dn.marjan.tmdb.data.repositories.MovieRepositoryImpl
import dn.marjan.tmdb.data.repositories.PeopleRepositoryImpl
import dn.marjan.tmdb.data.repositories.TvshowRepositoryImpl
import dn.marjan.tmdb.domain.repository.MovieRepository
import dn.marjan.tmdb.domain.repository.PeopleRepository
import dn.marjan.tmdb.domain.repository.TvshowRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Singleton
    @Provides
    fun provideMovieRepository(
        movieRemoteDataSource: MovieRemoteDataSource,
        popularMoviePagingSource: PopularMoviePagingSource,
        pageConfig: PagingConfig,
        featuredMoviePagingSource: FeaturedMoviePagingSource
    ): MovieRepository =
        MovieRepositoryImpl(
            movieRemoteDataSource = movieRemoteDataSource,
            popularMoviePagingSource = popularMoviePagingSource,
            pageConfig = pageConfig,
            featuredMoviePagingSource = featuredMoviePagingSource
        )

    @Singleton
    @Provides
    fun provideTvshowRepository(
        tvshowRemoteDataSource: TvshowRemoteDataSource,
        popularTvshowPagingSource: PopularTvshowPagingSource,
        featuredTvshowPagingSource: FeaturedTvshowPagingSource,
        pageConfig: PagingConfig
    ): TvshowRepository =
        TvshowRepositoryImpl(
            tvshowRemoteDataSource,
            popularTvshowPagingSource,
            featuredTvshowPagingSource,
            pageConfig
        )

    @Singleton
    @Provides
    fun providePeopleRepository(
        peopleRemoteDataSource: PeopleRemoteDataSource,
        pageConfig: PagingConfig,
        popularPeoplePagingSource: PopularPeoplePagingSource
    ): PeopleRepository =
        PeopleRepositoryImpl(peopleRemoteDataSource, pageConfig, popularPeoplePagingSource)
}