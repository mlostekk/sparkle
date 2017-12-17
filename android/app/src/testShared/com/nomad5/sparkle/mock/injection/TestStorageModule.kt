package com.nomad5.sparkle.mock.injection

import com.google.gson.Gson
import com.nomad5.sparkle.injection.StorageModule
import com.nomad5.sparkle.mock.model.storage.mock.MockStorage
import com.nomad5.sparkle.model.storage.StorageInterface
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/****************************************************************************************************************************
 * Module which provides mocked storage related objects
 */
@Module
class TestStorageModule : StorageModule() {

    @Provides
    @Singleton
    override fun provideStorage(gson: Gson): StorageInterface {
        return MockStorage()
    }
}