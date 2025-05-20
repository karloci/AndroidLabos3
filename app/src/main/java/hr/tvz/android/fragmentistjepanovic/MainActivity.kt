package hr.tvz.android.fragmentistjepanovic

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.res.Configuration
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.messaging.FirebaseMessaging

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

        val channel = NotificationChannel(
            "fcm_fallback_notification_channel",
            "Stjepanović",
            NotificationManager.IMPORTANCE_DEFAULT
        )
        val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(channel)

        FirebaseMessaging.getInstance().token.addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val token = task.result
                Log.d("FCM token", "Token: $token")
            } else {
                Log.w("FCM", "Fetching FCM token failed", task.exception)
            }
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