package hr.tvz.android.mvpstjepanovic.instrument.repository

import hr.tvz.android.mvpstjepanovic.instrument.api.InstrumentApi
import hr.tvz.android.mvpstjepanovic.instrument.model.Instrument
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class InstrumentRepository(
    private val instrumentApi: InstrumentApi
) {
    private val _instruments = MutableStateFlow<List<Instrument>>(emptyList())
    val instruments: StateFlow<List<Instrument>> = _instruments.asStateFlow()

    suspend fun getAllInstruments(): List<Instrument> {
        return if (_instruments.value.isEmpty()) {
            fetchInstruments()
        } else {
            _instruments.value
        }
    }

    suspend fun refreshInstruments(): List<Instrument> {
        return fetchInstruments()
    }

    private suspend fun fetchInstruments(): List<Instrument> {
        val response = instrumentApi.getInstruments()

        if (response.isSuccessful && response.body() != null) {
            val list = response.body() ?: emptyList<Instrument>()
            _instruments.value = list
            return list
        }

        throw Exception("Error: ${response.code()}")
    }
}