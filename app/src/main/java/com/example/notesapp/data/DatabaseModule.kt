package com.example.notesapp.data

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java, "app_database")
            .build()
    }

    @Provides
    fun provideRatesDao(database: AppDatabase): NoteDao {
        return database.notesDao()
    }

    @Provides
    @Singleton
    fun provideDatabaseRepository(dao: NoteDao): DatabaseRepository {
        return DatabaseRepositoryImpl(dao = dao)
    }
}