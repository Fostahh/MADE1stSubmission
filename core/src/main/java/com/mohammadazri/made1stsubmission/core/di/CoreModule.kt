package com.mohammadazri.made1stsubmission.core.di

import androidx.room.Room
import com.mohammadazri.made1stsubmission.core.BuildConfig
import com.mohammadazri.made1stsubmission.core.data.MovieRepository
import com.mohammadazri.made1stsubmission.core.data.TvShowRepository
import com.mohammadazri.made1stsubmission.core.data.source.local.LocalDataSource
import com.mohammadazri.made1stsubmission.core.data.source.local.room.MovieTvShowDatabase
import com.mohammadazri.made1stsubmission.core.data.source.remote.RemoteDataSource
import com.mohammadazri.made1stsubmission.core.data.source.remote.network.ApiService
import com.mohammadazri.made1stsubmission.core.domain.repository.IMovieRepository
import com.mohammadazri.made1stsubmission.core.domain.repository.ITvShowRepository
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory
import okhttp3.CertificatePinner
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val databaseModule = module {
    factory { get<MovieTvShowDatabase>().movieTvShowDao() }
    single {
        val passphrase: ByteArray = SQLiteDatabase.getBytes("mohammadazri".toCharArray())
        val factory = SupportFactory(passphrase)
        Room.databaseBuilder(
            androidContext(),
            MovieTvShowDatabase::class.java, "Film.db"
        ).fallbackToDestructiveMigration().openHelperFactory(factory).build()
    }
}

val networkModule = module {
    single {
        val certificatePinner = CertificatePinner.Builder()
            .add(BuildConfig.Hostname, BuildConfig.CertificatePinner)
            .build()

        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .certificatePinner(certificatePinner)
            .build()
    }
    single {
        val retrofit = Retrofit.Builder()
            .baseUrl(BuildConfig.BaseURL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
        retrofit.create(ApiService::class.java)
    }
}

val repositoryModule = module {
    single { LocalDataSource(get()) }
    single { RemoteDataSource(get()) }
    single<IMovieRepository> {
        MovieRepository(
            get(),
            get()
        )
    }
    single<ITvShowRepository> {
        TvShowRepository(
            get(),
            get()
        )
    }
}