package com.spiritdsp.tsm;

import android.content.Context;
import android.graphics.Rect;
import android.view.ViewGroup.LayoutParams;
import android.widget.FrameLayout;

class VideoView extends FrameLayout {
    public static final int VT_BOTH = 2;
    public static final int VT_PREVIEW = 0;
    public static final int VT_REMOTE = 1;
    private static final int aResizeView = 2;
    private static final int aShowView = 3;
    private boolean isAttached = false;
    protected boolean isDebug = true;
    private int mID;
    private int mType;
    private c mWrapper;
    private boolean wasAttached = false;

    private native void _complete_render(int i);

    private native void _register_vv_render(int i);

    private native void _unregister_vv_render(int i);

    public VideoView(Context context, int... iArr) {
        int i = 1;
        super(context);
        Logging.LogDebugPrint(this.isDebug, "VV:<init>", new Object[0]);
        this.isAttached = false;
        setVisibility(0);
        setBackgroundColor(0);
        setDrawingCacheEnabled(false);
        setClipChildren(true);
        if (iArr.length == 0) {
            this.mType = 2;
            this.mID = 0;
        } else {
            if (iArr[0] == 0) {
                i = 0;
            }
            this.mType = i;
            this.mID = iArr[0];
        }
        setId(this.mID);
    }

    protected void onWindowVisibilityChanged(int i) {
        Logging.LogDebugPrint(this.isDebug, "VV:onWindowVisibilityChanged %d", Integer.valueOf(i));
        super.onWindowVisibilityChanged(i);
    }

    protected void onAttachedToWindow() {
        Logging.LogDebugPrint(this.isDebug, "VV:onAttachedToWindow", new Object[0]);
        super.onAttachedToWindow();
        if (this.wasAttached) {
            removeAll();
            this.wasAttached = false;
        }
        if (!this.isAttached) {
            Logging.LogDebugPrint(this.isDebug, "VV:attach", new Object[0]);
            setKeepScreenOn(true);
            _register_vv_render(this.mType);
            LayoutParams layoutParams = getLayoutParams();
            layoutParams.width = -1;
            layoutParams.height = -1;
            setLayoutParams(layoutParams);
            add_view_impl(this.mType);
            this.isAttached = true;
        }
    }

    protected void onDetachedFromWindow() {
        Logging.LogDebugPrint(this.isDebug, "VV:onDetachedFromWindow", new Object[0]);
        if (this.isAttached) {
            this.isAttached = false;
            setKeepScreenOn(false);
        }
        super.onDetachedFromWindow();
    }

    private void removeAll() {
        _unregister_vv_render(this.mType);
        del_view_impl();
        removeAllViews();
    }

    private void add_view_impl(int i) {
        int i2 = 1;
        Rect rect = new Rect();
        getWindowVisibleDisplayFrame(rect);
        int width = rect.width();
        int height = rect.height();
        if (width < 1) {
            width = 1;
        }
        if (height >= 1) {
            i2 = height;
        }
        this.mWrapper = new c(getContext(), this.mType, 0, 0, width, i2);
        this.mWrapper.a(this);
    }

    private void del_view_impl() {
        Logging.LogDebugPrint(this.isDebug, "VV:>del view impl", new Object[0]);
        if (this.mWrapper != null) {
            this.mWrapper.b(this);
            this.mWrapper.a();
            this.mWrapper = null;
            Logging.LogDebugPrint(this.isDebug, "VV:<del view impl %d", new Object[0]);
        }
    }

    private void complete_action(int i, int i2) {
        Logging.LogDebugPrint(this.isDebug, "VV:%d action %d completed", Integer.valueOf(i), Integer.valueOf(i2));
        _complete_render(i2);
    }
}
