package hr.tvz.android.mvpstjepanovic

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import dagger.hilt.android.AndroidEntryPoint
import hr.tvz.android.mvpstjepanovic.fragment.DetailsFragment
import hr.tvz.android.mvpstjepanovic.fragment.ListFragment
import hr.tvz.android.mvpstjepanovic.instrument.model.Instrument

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), ListFragment.OnInstrumentSelectedListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    override fun onInstrumentSelected(instrument: Instrument) {
        val detailsFragment = DetailsFragment().apply {
            arguments = Bundle().apply {
                putParcelable("instrument", instrument)
            }
        }

        val isLandscape = resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE

        if (isLandscape) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainerView2, detailsFragment)
                .commit()
        } else {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainerView3, detailsFragment)
                .addToBackStack(null)
                .commit()
        }
    }
}