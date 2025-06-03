package hr.tvz.android.mvpstjepanovic.instrument.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import hr.tvz.android.mvpstjepanovic.instrument.model.Instrument
import hr.tvz.android.mvpstjepanovic.instrument.repository.InstrumentRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class InstrumentViewModel @Inject constructor(
    private val instrumentRepository: InstrumentRepository
): ViewModel() {
    private val _instruments = MutableStateFlow<List<Instrument>>(emptyList())
    val instruments: StateFlow<List<Instrument>> = _instruments

    init {
        viewModelScope.launch {
            try {
                _instruments.value = instrumentRepository.getAllInstruments()
            } catch (e: Exception) {
                println(e.message)
            }
        }
    }
}