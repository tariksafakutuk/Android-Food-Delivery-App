package com.example.foodie.di

import android.content.Context
import com.example.foodie.data.datasource.FavoriteDataSource
import com.example.foodie.data.datasource.FoodDataSource
import com.example.foodie.data.datasource.UserDataSource
import com.example.foodie.data.repository.FavoriteRepository
import com.example.foodie.data.repository.FoodRepository
import com.example.foodie.data.repository.UserRepository
import com.example.foodie.datastore.LoginPref
import com.example.foodie.retrofit.ApiUtils
import com.example.foodie.retrofit.FoodDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Provides
    @Singleton
    fun provideUserDataSource(): UserDataSource {
        return UserDataSource()
    }

    @Provides
    @Singleton
    fun provideFoodDataSource(fdao: FoodDao): FoodDataSource {
        return FoodDataSource(fdao)
    }

    @Provides
    @Singleton
    fun provideFavoriteDataSource(): FavoriteDataSource {
        return FavoriteDataSource()
    }

    @Provides
    @Singleton
    fun provideUserRepository(uds: UserDataSource): UserRepository {
        return UserRepository(uds)
    }

    @Provides
    @Singleton
    fun provideFoodRepository(fds: FoodDataSource): FoodRepository {
        return FoodRepository(fds)
    }

    @Provides
    @Singleton
    fun provideFavoriteRepository(fds: FavoriteDataSource): FavoriteRepository {
        return FavoriteRepository(fds)
    }

    @Provides
    @Singleton
    fun proviteFoodDao(): FoodDao {
        return ApiUtils.getFoodDao()
    }

    @Provides
    @Singleton
    fun provideLoginPref(@ApplicationContext context: Context): LoginPref {
        return LoginPref(context)
    }
}