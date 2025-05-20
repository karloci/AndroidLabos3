package hr.tvz.android.fragmentistjepanovic

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

class DetailsFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val instrument = arguments?.getParcelable<Instrument>("instrument")
        if (instrument != null) {
            val nameView = view.findViewById<TextView>(R.id.instrumentName)
            val imageView = view.findViewById<ImageView>(R.id.instrumentImage)

            nameView.text = instrument.name
            imageView.setImageResource(instrument.image)
        }
    }
}