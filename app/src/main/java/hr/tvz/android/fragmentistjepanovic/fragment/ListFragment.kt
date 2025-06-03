package hr.tvz.android.fragmentistjepanovic.fragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import hr.tvz.android.fragmentistjepanovic.adapter.InstrumentAdapter
import hr.tvz.android.fragmentistjepanovic.R
import hr.tvz.android.fragmentistjepanovic.model.Instrument

class ListFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var instrumentsList: ArrayList<Instrument>
    private lateinit var callback: OnInstrumentSelectedListener

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

        instrumentsList = ArrayList<Instrument>()

        recyclerView = view.findViewById(R.id.instrumentsList)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = InstrumentAdapter(view.context, instrumentsList) { instrument ->
            callback.onInstrumentSelected(instrument)
        }
    }

    fun insertAll() {
        val instrumentNamesList = arrayListOf(
            getString(R.string.harpsichord),
            getString(R.string.accordion),
            getString(R.string.lute),
            getString(R.string.organ),
            getString(R.string.violin),
        )

        val instrumentUrlsList = arrayListOf(
            "https://en.wikipedia.org/wiki/Harpsichord",
            "https://en.wikipedia.org/wiki/Accordion",
            "https://en.wikipedia.org/wiki/Lute",
            "https://en.wikipedia.org/wiki/Organ_(music)",
            "https://en.wikipedia.org/wiki/Violin",
        )

        val instrumentImagesList = arrayListOf(
            "harpsichord",
            "accordion",
            "lute",
            "organ",
            "violin",
        )
    }
}