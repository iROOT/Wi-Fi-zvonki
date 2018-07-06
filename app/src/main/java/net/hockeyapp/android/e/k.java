package net.hockeyapp.android.e;

import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.GradientDrawable.Orientation;

public class k {
    public static Drawable getGradient() {
        return new GradientDrawable(Orientation.TOP_BOTTOM, new int[]{-16777216, 0});
    }
}
