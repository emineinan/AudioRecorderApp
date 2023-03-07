package com.example.audiorecorderapp

import android.Manifest
import android.graphics.Color
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults.buttonColors
import androidx.compose.material.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.app.ActivityCompat
import com.example.audiorecorderapp.playback.AndroidAudioPlayer
import com.example.audiorecorderapp.recorder.AndroidAudioRecorder
import com.example.audiorecorderapp.ui.theme.AudioRecorderAppTheme
import java.io.File

class MainActivity : ComponentActivity() {
    private val recorder by lazy { AndroidAudioRecorder(applicationContext) }
    private val player by lazy { AndroidAudioPlayer(applicationContext) }
    private var audioFile: File? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.RECORD_AUDIO), 0)

        setContent {
            AudioRecorderAppTheme {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Button(
                        onClick = {
                            File(cacheDir, "audio.mp3").also {
                                recorder.start(it)
                                audioFile = it
                            }
                        },
                        colors = buttonColors(backgroundColor = Red),
                        modifier = Modifier.width(240.dp)
                    ) {
                        Text(text = "Start Recording", color = White, fontSize = 18.sp)
                    }
                    Spacer(modifier = Modifier.height(12.dp))
                    Button(
                        onClick = {
                            recorder.stop()
                        },
                        colors = buttonColors(backgroundColor = Red),
                        modifier = Modifier.width(240.dp)
                    ) {
                        Text(text = "Stop Recording", color = White, fontSize = 18.sp)
                    }
                    Spacer(modifier = Modifier.height(12.dp))
                    Button(
                        onClick = {
                            player.playFile(audioFile ?: return@Button)
                        },
                        colors = buttonColors(backgroundColor = Red),
                        modifier = Modifier.width(240.dp)
                    ) {
                        Text(text = "Play", color = White, fontSize = 18.sp)
                    }
                    Spacer(modifier = Modifier.height(12.dp))
                    Button(
                        onClick = {
                            player.stop()
                        },
                        colors = buttonColors(backgroundColor = Red),
                        modifier = Modifier.width(240.dp)
                    ) {
                        Text(text = "Stop Playing", color = White, fontSize = 18.sp)
                    }
                }
            }
        }
    }
}
