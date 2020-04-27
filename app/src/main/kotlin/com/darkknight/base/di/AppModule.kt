package com.darkknight.base.di


import androidx.room.Room
import com.darkknight.base.BuildConfig
import com.darkknight.base.data.room.GrowOnDatabase
import com.darkknight.base.repository.OnBoardingRepository
import com.darkknight.base.repository.OnBoardingRepositoryImpl
import org.koin.dsl.module

/**
 *
 * Created by Rooparsh Kalia on 2020-02-23
 *
 **/


val appModule = module {

    single<OnBoardingRepository> { OnBoardingRepositoryImpl(get()) }


    single { BuildConfig.DB_NAME }

    single {
        Room.databaseBuilder(get(), GrowOnDatabase::class.java, get())
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .build()
    }

}