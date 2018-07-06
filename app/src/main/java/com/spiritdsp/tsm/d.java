package com.spiritdsp.tsm;

import android.app.Activity;
import android.content.Context;
import android.view.View;

public interface d {

    public enum a {
        EnableAudioVolumeObservation
    }

    View createVideoView(Context context, int... iArr);

    void setContext(Activity activity);

    void setOption(a aVar, Object obj);
}
