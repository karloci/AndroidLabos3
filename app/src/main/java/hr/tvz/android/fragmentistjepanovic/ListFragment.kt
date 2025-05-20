package hr.tvz.android.fragmentistjepanovic

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ListFragment : Fragment() {
    private lateinit var adapter: InstrumentAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var instrumentsList: ArrayList<Instrument>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
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

        val layoutManager = LinearLayoutManager(context)
        recyclerView = view.findViewById(R.id.instrumentsList)
        recyclerView.layoutManager = layoutManager
        recyclerView.setHasFixedSize(true)
        adapter = InstrumentAdapter(instrumentsList)
        recyclerView.adapter = adapter
    }
}