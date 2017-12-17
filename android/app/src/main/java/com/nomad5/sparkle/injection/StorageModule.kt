package com.nomad5.sparkle.injection

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.nomad5.sparkle.model.storage.StorageInterface
import com.nomad5.sparkle.model.storage.retroCouch.CouchDbStorage
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/****************************************************************************************************************************
 */
@Module
class StorageModule {

    @Provides
    @Singleton
    fun provideGson(): Gson {
        return GsonBuilder().create()
    }

    @Provides
    @Singleton
    fun provideStorage(gson: Gson): StorageInterface {
        return CouchDbStorage(gson)
    }

}