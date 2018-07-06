package com.spiritdsp.tsm;

import android.app.Activity;
import android.content.Context;
import android.view.View;

public interface TSM {

    public enum Option {
        EnableAudioVolumeObservation
    }

    View createVideoView(Context context, int... iArr);

    void setContext(Activity activity);

    void setOption(Option option, Object obj);
}
