package com.example.audiorecorderapp.recorder

import java.io.File

interface AudioRecorder {
    fun start(outputFile: File)
    fun stop()
}