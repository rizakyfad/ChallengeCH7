package com.rizaki.challengech6Binar

import android.app.Application
import com.rizaki.challengech6Binar.Module.*
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin


class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@MyApplication)
            modules(
                listOf(
                    databaseModule,
                    networkModule,
                    repositoryModule,
                    viewModelModule,
                    storageModule
                )
            )
        }
    }
}