package hr.tvz.android.mvpstjepanovic.instrument

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import hr.tvz.android.mvpstjepanovic.instrument.api.InstrumentApi
import hr.tvz.android.mvpstjepanovic.instrument.repository.InstrumentRepository
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object InstrumentModule {
    @Provides
    @Singleton
    fun provideInstrumentApi(retrofit: Retrofit): InstrumentApi {
        return retrofit.create(InstrumentApi::class.java)
    }

    @Provides
    @Singleton
    fun provideInstrumentRepository(instrumentApi: InstrumentApi): InstrumentRepository {
        return InstrumentRepository(instrumentApi)
    }
}