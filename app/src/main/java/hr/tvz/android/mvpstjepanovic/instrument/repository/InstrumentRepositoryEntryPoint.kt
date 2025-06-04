package hr.tvz.android.mvpstjepanovic.instrument.repository

import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@EntryPoint
@InstallIn(SingletonComponent::class)
interface InstrumentRepositoryEntryPoint {
    fun instrumentRepository(): InstrumentRepository
}
