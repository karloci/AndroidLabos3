package hr.tvz.android.mvpstjepanovic.utils.audio

import android.content.Context
import android.media.MediaPlayer

object AudioPlayer {
    private var player: MediaPlayer? = null

    fun play(context: Context, resId: Int) {
        stop()
        player = MediaPlayer.create(context, resId)
        player?.isLooping = true
        player?.start()
    }

    fun stop() {
        player?.stop()
        player?.release()
        player = null
    }
}