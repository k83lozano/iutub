package com.ytplayer.keiti

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import com.google.android.youtube.player.*
import java.util.*

const val YOUTUBE_ID_KEY="leNp_V_6Obs"
const val PLAYLIST_ID_KEY="PLg8ZJqKAO3wRVOSmN6iMSjs3kyPYBz6XB"

class Youtube : YouTubeBaseActivity(), YouTubePlayer.OnInitializedListener{

    val TAG= "Youtube"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val layout =layoutInflater.inflate(R.layout.activity_youtube, null) as ConstraintLayout
        setContentView(layout)

        val playerView=YouTubePlayerView(this)
        playerView.layoutParams=ConstraintLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )
        layout.addView(playerView)

        playerView.initialize(getString(R.string.GOOGLE_API_KEY), this)
    }
    override fun onInitializationSuccess(
        provider: YouTubePlayer.Provider?,
        player: YouTubePlayer?,
        wasRestored: Boolean
    ) {
        Log.d(TAG, "onInitializationSuccess")
        Toast.makeText(this, "Initialized Successfully", Toast.LENGTH_LONG).show()

        player?.setPlaybackEventListener(playbackEventListener)
        player?.setPlayerStateChangeListener(changeStateListener)

        if (!wasRestored) {
            player?.cueVideo(YOUTUBE_ID_KEY)
        }
    }

        override fun onInitializationFailure(
            provider: YouTubePlayer.Provider?,
            youTubeInitializationResult: YouTubeInitializationResult?
        ) {
            Log.d(TAG, "onInitializationFailure")
            val REQUEST_CODE=0
            if(youTubeInitializationResult?.isUserRecoverableError==true){
                youTubeInitializationResult.getErrorDialog(this,REQUEST_CODE).show()
            }else{
                Toast.makeText(this, "Error starting player", Toast.LENGTH_LONG).show()
            }
        }

        private val playbackEventListener=object : YouTubePlayer.PlaybackEventListener{
            override fun onPlaying() {
                Toast.makeText(this@Youtube, "Playing", Toast.LENGTH_SHORT).show()
            }

            override fun onPaused() {
                Toast.makeText(this@Youtube, "Paused", Toast.LENGTH_SHORT).show()
            }

            override fun onStopped() {
            }

            override fun onBuffering(p0: Boolean) {
            }

            override fun onSeekTo(p0: Int) {
            }

        }

        private val changeStateListener=object: YouTubePlayer.PlayerStateChangeListener{
            override fun onLoading() {
            }

            override fun onLoaded(p0: String?) {
            }

            override fun onAdStarted() {
                Toast.makeText(this@Youtube, "Ad Playing", Toast.LENGTH_SHORT).show()
            }

            override fun onVideoStarted() {
            }

            override fun onVideoEnded() {
                Toast.makeText(this@Youtube, "Finished", Toast.LENGTH_SHORT).show()
            }

            override fun onError(p0: YouTubePlayer.ErrorReason?) {
            }

        }
    }
