package hr.tvz.android.mvpstjepanovic.fragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.collectAsState
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import hr.tvz.android.mvpstjepanovic.adapter.InstrumentAdapter
import hr.tvz.android.mvpstjepanovic.R
import hr.tvz.android.mvpstjepanovic.instrument.model.Instrument
import hr.tvz.android.mvpstjepanovic.instrument.viewmodel.InstrumentViewModel
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ListFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var instrumentsList: List<Instrument>
    private lateinit var callback: OnInstrumentSelectedListener

    private val viewModel: InstrumentViewModel by viewModels()

    interface OnInstrumentSelectedListener {
        fun onInstrumentSelected(instrument: Instrument)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        callback = context as OnInstrumentSelectedListener
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = view.findViewById(R.id.instrumentsList)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(context)

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.instruments.collect { instrumentsList ->
                    instrumentsList.forEach { instrument ->
                        println(instrument.name)
                    }

                    recyclerView.adapter = InstrumentAdapter(requireContext(), instrumentsList) { instrument ->
                        callback.onInstrumentSelected(instrument)
                    }
                }
            }
        }

    }
}