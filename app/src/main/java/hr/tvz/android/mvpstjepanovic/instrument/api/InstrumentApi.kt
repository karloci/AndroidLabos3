package hr.tvz.android.mvpstjepanovic.instrument.api

import hr.tvz.android.mvpstjepanovic.instrument.model.Instrument
import retrofit2.Response
import retrofit2.http.GET

interface InstrumentApi {
    @GET("/instruments")
    suspend fun getInstruments(): Response<List<Instrument>>
}