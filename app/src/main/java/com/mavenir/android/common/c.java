package com.mavenir.android.common;

import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.util.Log;

public class c {
    private final String a = "VoiceRecords";
    private MediaRecorder b = null;
    private MediaPlayer c = null;
    private int d = 1;
    private int e = 1;
    private int f = 2;

    public void a(String str, int i) {
        Log.d("AudioRecorder", "startPlayer()");
        this.c = new MediaPlayer();
        try {
            this.c.setAudioStreamType(i);
            this.c.setVolume(1.0f, 1.0f);
            this.c.setDataSource(str);
            this.c.prepare();
            this.c.start();
        } catch (Exception e) {
            Log.e("AudioRecorder", "startPlayer(): " + e.getMessage());
        }
    }
}
