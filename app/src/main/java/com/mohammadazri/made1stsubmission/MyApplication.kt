package com.mohammadazri.made1stsubmission

import android.app.Application
import com.mohammadazri.made1stsubmission.core.di.databaseModule
import com.mohammadazri.made1stsubmission.core.di.networkModule
import com.mohammadazri.made1stsubmission.core.di.repositoryModule
import com.mohammadazri.made1stsubmission.di.useCaseModule
import com.mohammadazri.made1stsubmission.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@MyApplication)
            modules(
                listOf(
                    databaseModule,
                    networkModule,
                    repositoryModule,
                    useCaseModule,
                    viewModelModule
                )
            )
        }
    }
}