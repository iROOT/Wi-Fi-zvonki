package com.android.incallui;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import com.android.incallui.a.b;
import com.android.incallui.widget.multiwaveview.GlowPadView;
import com.android.incallui.widget.multiwaveview.GlowPadView.OnTriggerListener;

public class GlowPadWrapper extends GlowPadView implements OnTriggerListener {
    private static final String a = GlowPadWrapper.class.getSimpleName();
    private final Handler b = new Handler(this) {
        final /* synthetic */ GlowPadWrapper a;

        {
            this.a = r1;
        }

        public void handleMessage(Message message) {
            switch (message.what) {
                case 101:
                    this.a.d();
                    return;
                default:
                    return;
            }
        }
    };
    private a c;
    private boolean d = true;
    private boolean e = false;

    public interface a {
        void a();

        void b();

        void c();

        void d();
    }

    public GlowPadWrapper(Context context) {
        super(context);
        Log.d(a, "class created " + this + " ");
    }

    public GlowPadWrapper(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        Log.d(a, "class created " + this);
    }

    protected void onFinishInflate() {
        Log.d(a, "onFinishInflate()");
        super.onFinishInflate();
        setOnTriggerListener(this);
    }

    public void a() {
        Log.d(a, "startPing");
        this.d = true;
        d();
    }

    public void b() {
        Log.d(a, "stopPing");
        this.d = false;
        this.b.removeMessages(101);
    }

    private void d() {
        Log.d(a, "triggerPing(): " + this.d + " " + this);
        if (this.d && !this.b.hasMessages(101)) {
            ping();
            this.b.sendEmptyMessageDelayed(101, 1200);
        }
    }

    public void onGrabbed(View view, int i) {
        Log.d(a, "onGrabbed()");
        b();
    }

    public void onReleased(View view, int i) {
        Log.d(a, "onReleased()");
        if (this.e) {
            this.e = false;
        } else {
            a();
        }
    }

    public void onTrigger(View view, int i) {
        Log.d(a, "onTrigger()");
        int resourceIdForTarget = getResourceIdForTarget(i);
        if (resourceIdForTarget == b.ic_lockscreen_answer) {
            this.c.a();
            this.e = true;
        } else if (resourceIdForTarget == b.ic_lockscreen_decline) {
            this.c.b();
            this.e = true;
        } else if (resourceIdForTarget == b.ic_lockscreen_text) {
            this.c.c();
            this.e = true;
        } else if (resourceIdForTarget == b.ic_lockscreen_action) {
            this.c.d();
            this.e = true;
        } else {
            Log.e(a, "Trigger detected on unhandled resource. Skipping.");
        }
    }

    public void onGrabbedStateChange(View view, int i) {
    }

    public void onFinishFinalAnimation() {
    }

    public void setAnswerListener(a aVar) {
        this.c = aVar;
    }

    public void c() {
        super.setTargetResources(com.android.incallui.a.a.incoming_call_widget_3way_targets);
    }
}
