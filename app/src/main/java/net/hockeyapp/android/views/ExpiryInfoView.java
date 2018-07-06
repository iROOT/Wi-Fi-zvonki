package net.hockeyapp.android.views;

import android.content.Context;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import net.hockeyapp.android.e.k;

public class ExpiryInfoView extends RelativeLayout {
    public ExpiryInfoView(Context context) {
        this(context, "");
    }

    public ExpiryInfoView(Context context, String str) {
        super(context);
        loadLayoutParams(context);
        loadShadowView(context);
        loadTextView(context, str);
    }

    private void loadLayoutParams(Context context) {
        LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -1);
        setBackgroundColor(-1);
        setLayoutParams(layoutParams);
    }

    private void loadShadowView(Context context) {
        LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, (int) TypedValue.applyDimension(1, 3.0f, getResources().getDisplayMetrics()));
        layoutParams.addRule(10, -1);
        View imageView = new ImageView(context);
        imageView.setLayoutParams(layoutParams);
        imageView.setBackgroundDrawable(k.getGradient());
        addView(imageView);
    }

    private void loadTextView(Context context, String str) {
        int applyDimension = (int) TypedValue.applyDimension(1, 20.0f, getResources().getDisplayMetrics());
        LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -2);
        layoutParams.addRule(13, -1);
        layoutParams.setMargins(applyDimension, applyDimension, applyDimension, applyDimension);
        View textView = new TextView(context);
        textView.setGravity(17);
        textView.setLayoutParams(layoutParams);
        textView.setText(str);
        textView.setTextColor(-16777216);
        addView(textView);
    }
}
