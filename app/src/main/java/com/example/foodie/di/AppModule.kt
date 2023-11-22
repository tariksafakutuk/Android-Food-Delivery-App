package com.example.foodie.di

import android.content.Context
import androidx.room.Room
import com.example.foodie.data.datasource.FavoriteDataSource
import com.example.foodie.data.datasource.FoodDataSource
import com.example.foodie.data.datasource.UserDataSource
import com.example.foodie.data.repository.FavoriteRepository
import com.example.foodie.data.repository.FoodRepository
import com.example.foodie.data.repository.UserRepository
import com.example.foodie.datastore.LoginPref
import com.example.foodie.retrofit.ApiUtils
import com.example.foodie.retrofit.FoodDao
import com.example.foodie.room.Database
import com.example.foodie.room.FavoriteFoodDao
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
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
    fun provideUserDataSource(collectionUser: CollectionReference, loginPref: LoginPref): UserDataSource {
        return UserDataSource(collectionUser, loginPref)
    }

    @Provides
    @Singleton
    fun provideFoodDataSource(fdao: FoodDao, fds: FavoriteDataSource): FoodDataSource {
        return FoodDataSource(fdao, fds)
    }

    @Provides
    @Singleton
    fun provideFavoriteDataSource(favDao: FavoriteFoodDao): FavoriteDataSource {
        return FavoriteDataSource(favDao)
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
    fun provideFoodDao(): FoodDao {
        return ApiUtils.getFoodDao()
    }

    @Provides
    @Singleton
    fun provideFavoriteFoodDao(@ApplicationContext context: Context): FavoriteFoodDao {
        val db = Room.databaseBuilder(context, Database::class.java, "database.sqlite")
            .createFromAsset("database.sqlite").build()
        return db.getFavoriteFoodDao()
    }

    @Provides
    @Singleton
    fun provideCollectionReference(): CollectionReference {
        return Firebase.firestore.collection("User")
    }

    @Provides
    @Singleton
    fun provideLoginPref(@ApplicationContext context: Context): LoginPref {
        return LoginPref(context)
    }
}