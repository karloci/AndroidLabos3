package hr.tvz.android.fragmentistjepanovic

import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

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

        instrumentsList = arrayListOf(
            Instrument(getString(R.string.harpsichord), "https://en.wikipedia.org/wiki/Harpsichord", R.drawable.harpsichord),
            Instrument(getString(R.string.accordion), "https://en.wikipedia.org/wiki/Accordion", R.drawable.accordion),
            Instrument(getString(R.string.lute), "https://en.wikipedia.org/wiki/Lute", R.drawable.lute),
            Instrument(getString(R.string.organ), "https://en.wikipedia.org/wiki/Organ_(music)", R.drawable.organ),
            Instrument(getString(R.string.violin), "https://en.wikipedia.org/wiki/Violin", R.drawable.violin),
        )

        recyclerView = view.findViewById(R.id.instrumentsList)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = InstrumentAdapter(instrumentsList) { instrument ->
            callback.onInstrumentSelected(instrument)
        }
    }
}