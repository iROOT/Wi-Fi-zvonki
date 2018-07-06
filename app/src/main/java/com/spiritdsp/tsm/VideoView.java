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
    private RenderWrapper mWrapper;
    private boolean wasAttached = false;

    private native void _complete_render(int i);

    private native void _register_vv_render(int i);

    private native void _unregister_vv_render(int i);

    public VideoView(Context context, int... ids) {
        int i = 1;
        super(context);
        Logging.LogDebugPrint(this.isDebug, "VV:<init>", new Object[0]);
        this.isAttached = false;
        setVisibility(0);
        setBackgroundColor(0);
        setDrawingCacheEnabled(false);
        setClipChildren(true);
        if (ids.length == 0) {
            this.mType = 2;
            this.mID = 0;
        } else {
            if (ids[0] == 0) {
                i = 0;
            }
            this.mType = i;
            this.mID = ids[0];
        }
        setId(this.mID);
    }

    protected void onWindowVisibilityChanged(int visibility) {
        Logging.LogDebugPrint(this.isDebug, "VV:onWindowVisibilityChanged %d", Integer.valueOf(visibility));
        super.onWindowVisibilityChanged(visibility);
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
            LayoutParams lp = getLayoutParams();
            lp.width = -1;
            lp.height = -1;
            setLayoutParams(lp);
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

    private void add_view_impl(int type) {
        Rect screenSize = new Rect();
        getWindowVisibleDisplayFrame(screenSize);
        int w = screenSize.width();
        int h = screenSize.height();
        if (w < 1) {
            w = 1;
        }
        if (h < 1) {
            h = 1;
        }
        this.mWrapper = new RenderWrapper(getContext(), this.mType, 0, 0, w, h);
        this.mWrapper.AddToView(this);
    }

    private void del_view_impl() {
        Logging.LogDebugPrint(this.isDebug, "VV:>del view impl", new Object[0]);
        if (this.mWrapper != null) {
            this.mWrapper.RemoveFromView(this);
            this.mWrapper.Release();
            this.mWrapper = null;
            Logging.LogDebugPrint(this.isDebug, "VV:<del view impl %d", new Object[0]);
        }
    }

    private void complete_action(int view_type, int action) {
        Logging.LogDebugPrint(this.isDebug, "VV:%d action %d completed", Integer.valueOf(view_type), Integer.valueOf(action));
        _complete_render(action);
    }
}
