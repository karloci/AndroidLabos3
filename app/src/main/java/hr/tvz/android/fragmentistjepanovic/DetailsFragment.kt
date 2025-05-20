package hr.tvz.android.fragmentistjepanovic

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri

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

        val nameView = view.findViewById<TextView>(R.id.instrumentName)
        val imageView = view.findViewById<ImageView>(R.id.instrumentImage)
        val webButton = view.findViewById<Button>(R.id.webButton)
        val detailsLayout = view.findViewById<View>(R.id.detailsLayout)

        val instrument = arguments?.getParcelable<Instrument>("instrument")

        if (instrument != null) {
            nameView.text = instrument.name
            imageView.setImageResource(instrument.image)
            webButton.setOnClickListener {
                val intent = Intent(Intent.ACTION_VIEW, instrument.url.toUri())
                startActivity(intent)
            }
        } else {
            detailsLayout.visibility = View.GONE
        }
    }

}