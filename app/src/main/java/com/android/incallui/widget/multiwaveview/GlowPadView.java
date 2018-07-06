package com.android.incallui.widget.multiwaveview;

import android.annotation.TargetApi;
import android.content.ComponentName;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v4.app.NotificationCompat;
import android.support.v4.view.ad;
import android.support.v4.view.e;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.accessibility.AccessibilityManager;
import android.view.animation.Interpolator;
import com.a.a.a.a;
import com.a.a.m;
import com.a.a.m.b;
import com.android.incallui.a.c;
import com.mavenir.android.vtow.activation.ActivationAdapter;
import java.util.ArrayList;

public class GlowPadView extends View {
    private static final boolean DEBUG = false;
    private static final int HIDE_ANIMATION_DELAY = 200;
    private static final int HIDE_ANIMATION_DURATION = 200;
    private static final int INITIAL_SHOW_HANDLE_DURATION = 200;
    private static final int RETURN_TO_HOME_DELAY = 1200;
    private static final int RETURN_TO_HOME_DURATION = 200;
    private static final int REVEAL_GLOW_DELAY = 0;
    private static final int REVEAL_GLOW_DURATION = 0;
    private static final float RING_SCALE_COLLAPSED = 0.5f;
    private static final float RING_SCALE_EXPANDED = 1.0f;
    private static final int SHOW_ANIMATION_DELAY = 50;
    private static final int SHOW_ANIMATION_DURATION = 200;
    private static final float SNAP_MARGIN_DEFAULT = 20.0f;
    private static final int STATE_FINISH = 5;
    private static final int STATE_FIRST_TOUCH = 2;
    private static final int STATE_IDLE = 0;
    private static final int STATE_SNAP = 4;
    private static final int STATE_START = 1;
    private static final int STATE_TRACKING = 3;
    private static final String TAG = "GlowPadView";
    private static final float TAP_RADIUS_SCALE_ACCESSIBILITY_ENABLED = 1.3f;
    private static final float TARGET_SCALE_COLLAPSED = 0.8f;
    private static final float TARGET_SCALE_EXPANDED = 1.0f;
    private static final int WAVE_ANIMATION_DURATION = 1350;
    private int mActiveTarget;
    private boolean mAllowScaling;
    private boolean mAlwaysTrackFinger;
    private boolean mAnimatingTargets;
    private Tweener mBackgroundAnimator;
    private ArrayList<String> mDirectionDescriptions;
    private int mDirectionDescriptionsResourceId;
    private boolean mDragging;
    private int mFeedbackCount;
    private AnimationBundle mGlowAnimations;
    private float mGlowRadius;
    private int mGrabbedState;
    private int mGravity;
    private TargetDrawable mHandleDrawable;
    private int mHorizontalInset;
    private boolean mInitialLayout;
    private float mInnerRadius;
    private int mMaxTargetHeight;
    private int mMaxTargetWidth;
    private int mNewTargetResources;
    private OnTriggerListener mOnTriggerListener;
    private float mOuterRadius;
    private TargetDrawable mOuterRing;
    private PointCloud mPointCloud;
    private int mPointerId;
    private a mResetListener;
    private a mResetListenerWithPing;
    private float mRingScaleFactor;
    private float mSnapMargin;
    private AnimationBundle mTargetAnimations;
    private ArrayList<String> mTargetDescriptions;
    private int mTargetDescriptionsResourceId;
    private ArrayList<TargetDrawable> mTargetDrawables;
    private int mTargetResourceId;
    private a mTargetUpdateListener;
    private b mUpdateListener;
    private int mVerticalInset;
    private int mVibrationDuration;
    private Vibrator mVibrator;
    private AnimationBundle mWaveAnimations;
    private float mWaveCenterX;
    private float mWaveCenterY;

    public interface OnTriggerListener {
        public static final int CENTER_HANDLE = 1;
        public static final int NO_HANDLE = 0;

        void onFinishFinalAnimation();

        void onGrabbed(View view, int i);

        void onGrabbedStateChange(View view, int i);

        void onReleased(View view, int i);

        void onTrigger(View view, int i);
    }

    private class AnimationBundle extends ArrayList<Tweener> {
        private static final long serialVersionUID = -6319262269245852568L;
        private boolean mSuspended;

        private AnimationBundle() {
        }

        /* synthetic */ AnimationBundle(GlowPadView glowPadView, AnonymousClass1 anonymousClass1) {
            this();
        }

        public void start() {
            if (!this.mSuspended) {
                int size = size();
                for (int i = 0; i < size; i++) {
                    ((Tweener) get(i)).animator.a();
                }
            }
        }

        public void cancel() {
            int size = size();
            for (int i = 0; i < size; i++) {
                ((Tweener) get(i)).animator.b();
            }
            clear();
        }

        public void stop() {
            int size = size();
            for (int i = 0; i < size; i++) {
                ((Tweener) get(i)).animator.c();
            }
            clear();
        }

        public void setSuspended(boolean z) {
            this.mSuspended = z;
        }
    }

    public GlowPadView(Context context) {
        this(context, null);
    }

    public GlowPadView(Context context, AttributeSet attributeSet) {
        boolean z = true;
        Drawable drawable = null;
        super(context, attributeSet);
        this.mTargetDrawables = new ArrayList();
        this.mWaveAnimations = new AnimationBundle();
        this.mTargetAnimations = new AnimationBundle();
        this.mGlowAnimations = new AnimationBundle();
        this.mFeedbackCount = 3;
        this.mVibrationDuration = 0;
        this.mActiveTarget = -1;
        this.mRingScaleFactor = 1.0f;
        this.mOuterRadius = 0.0f;
        this.mSnapMargin = 0.0f;
        this.mResetListener = new com.a.a.b() {
            public void onAnimationEnd(com.a.a.a aVar) {
                GlowPadView.this.switchToState(0, GlowPadView.this.mWaveCenterX, GlowPadView.this.mWaveCenterY);
                GlowPadView.this.dispatchOnFinishFinalAnimation();
            }
        };
        this.mResetListenerWithPing = new com.a.a.b() {
            public void onAnimationEnd(com.a.a.a aVar) {
                GlowPadView.this.ping();
                GlowPadView.this.switchToState(0, GlowPadView.this.mWaveCenterX, GlowPadView.this.mWaveCenterY);
                GlowPadView.this.dispatchOnFinishFinalAnimation();
            }
        };
        this.mUpdateListener = new b() {
            public void onAnimationUpdate(m mVar) {
                GlowPadView.this.invalidate();
            }
        };
        this.mTargetUpdateListener = new com.a.a.b() {
            public void onAnimationEnd(com.a.a.a aVar) {
                if (GlowPadView.this.mNewTargetResources != 0) {
                    GlowPadView.this.internalSetTargetResources(GlowPadView.this.mNewTargetResources);
                    GlowPadView.this.mNewTargetResources = 0;
                    GlowPadView.this.hideTargets(false, false);
                }
                GlowPadView.this.mAnimatingTargets = false;
            }
        };
        this.mGravity = 48;
        this.mInitialLayout = true;
        Resources resources = context.getResources();
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, c.GlowPadView);
        try {
            this.mInnerRadius = obtainStyledAttributes.getDimension(c.GlowPadView_innerRadius, this.mInnerRadius);
            this.mOuterRadius = obtainStyledAttributes.getDimension(c.GlowPadView_outerRadius, this.mOuterRadius);
            this.mSnapMargin = obtainStyledAttributes.getDimension(c.GlowPadView_snapMargin, this.mSnapMargin);
            this.mVibrationDuration = obtainStyledAttributes.getInt(c.GlowPadView_vibrationDuration, this.mVibrationDuration);
            this.mFeedbackCount = obtainStyledAttributes.getInt(c.GlowPadView_feedbackCount, this.mFeedbackCount);
            this.mAllowScaling = obtainStyledAttributes.getBoolean(c.GlowPadView_allowScaling, false);
            TypedValue peekValue = obtainStyledAttributes.peekValue(c.GlowPadView_handleDrawable);
            this.mHandleDrawable = new TargetDrawable(resources, peekValue != null ? peekValue.resourceId : 0, 2);
            this.mHandleDrawable.setState(TargetDrawable.STATE_INACTIVE);
            this.mOuterRing = new TargetDrawable(resources, getResourceId(obtainStyledAttributes, c.GlowPadView_outerRingDrawable), 1);
            this.mAlwaysTrackFinger = obtainStyledAttributes.getBoolean(c.GlowPadView_alwaysTrackFinger, false);
            int resourceId = getResourceId(obtainStyledAttributes, c.GlowPadView_pointDrawable);
            if (resourceId != 0) {
                drawable = resources.getDrawable(resourceId);
            }
            this.mGlowRadius = obtainStyledAttributes.getDimension(c.GlowPadView_glowRadius, 0.0f);
            peekValue = new TypedValue();
            if (obtainStyledAttributes.getValue(c.GlowPadView_targetDrawables, peekValue)) {
                internalSetTargetResources(peekValue.resourceId);
            }
            if (this.mTargetDrawables == null || this.mTargetDrawables.size() == 0) {
                throw new IllegalStateException("Must specify at least one target drawable");
            }
            if (obtainStyledAttributes.getValue(c.GlowPadView_targetDescriptions, peekValue)) {
                int i = peekValue.resourceId;
                if (i == 0) {
                    throw new IllegalStateException("Must specify target descriptions");
                }
                setTargetDescriptionsResourceId(i);
            }
            if (obtainStyledAttributes.getValue(c.GlowPadView_directionDescriptions, peekValue)) {
                resourceId = peekValue.resourceId;
                if (resourceId == 0) {
                    throw new IllegalStateException("Must specify direction descriptions");
                }
                setDirectionDescriptionsResourceId(resourceId);
            }
            this.mGravity = obtainStyledAttributes.getInt(c.GlowPadView_android_gravity, 48);
            if (this.mVibrationDuration <= 0) {
                z = false;
            }
            setVibrateEnabled(z);
            assignDefaultsIfNeeded();
            this.mPointCloud = new PointCloud(drawable);
            this.mPointCloud.makePointCloud(this.mInnerRadius, this.mOuterRadius);
            this.mPointCloud.glowManager.setRadius(this.mGlowRadius);
        } finally {
            obtainStyledAttributes.recycle();
        }
    }

    private int getResourceId(TypedArray typedArray, int i) {
        TypedValue peekValue = typedArray.peekValue(i);
        return peekValue == null ? 0 : peekValue.resourceId;
    }

    private void dump() {
        Log.v(TAG, "Outer Radius = " + this.mOuterRadius);
        Log.v(TAG, "SnapMargin = " + this.mSnapMargin);
        Log.v(TAG, "FeedbackCount = " + this.mFeedbackCount);
        Log.v(TAG, "VibrationDuration = " + this.mVibrationDuration);
        Log.v(TAG, "GlowRadius = " + this.mGlowRadius);
        Log.v(TAG, "WaveCenterX = " + this.mWaveCenterX);
        Log.v(TAG, "WaveCenterY = " + this.mWaveCenterY);
    }

    public void suspendAnimations() {
        this.mWaveAnimations.setSuspended(true);
        this.mTargetAnimations.setSuspended(true);
        this.mGlowAnimations.setSuspended(true);
    }

    public void resumeAnimations() {
        this.mWaveAnimations.setSuspended(false);
        this.mTargetAnimations.setSuspended(false);
        this.mGlowAnimations.setSuspended(false);
        this.mWaveAnimations.start();
        this.mTargetAnimations.start();
        this.mGlowAnimations.start();
    }

    protected int getSuggestedMinimumWidth() {
        return (int) (Math.max((float) this.mOuterRing.getWidth(), 2.0f * this.mOuterRadius) + ((float) this.mMaxTargetWidth));
    }

    protected int getSuggestedMinimumHeight() {
        return (int) (Math.max((float) this.mOuterRing.getHeight(), 2.0f * this.mOuterRadius) + ((float) this.mMaxTargetHeight));
    }

    protected int getScaledSuggestedMinimumWidth() {
        return (int) ((this.mRingScaleFactor * Math.max((float) this.mOuterRing.getWidth(), 2.0f * this.mOuterRadius)) + ((float) this.mMaxTargetWidth));
    }

    protected int getScaledSuggestedMinimumHeight() {
        return (int) ((this.mRingScaleFactor * Math.max((float) this.mOuterRing.getHeight(), 2.0f * this.mOuterRadius)) + ((float) this.mMaxTargetHeight));
    }

    private int resolveMeasured(int i, int i2) {
        int size = MeasureSpec.getSize(i);
        switch (MeasureSpec.getMode(i)) {
            case Integer.MIN_VALUE:
                return Math.min(size, i2);
            case 0:
                return i2;
            default:
                return size;
        }
    }

    protected void onMeasure(int i, int i2) {
        int suggestedMinimumWidth = getSuggestedMinimumWidth();
        int suggestedMinimumHeight = getSuggestedMinimumHeight();
        int resolveMeasured = resolveMeasured(i, suggestedMinimumWidth);
        int resolveMeasured2 = resolveMeasured(i2, suggestedMinimumHeight);
        this.mRingScaleFactor = computeScaleFactor(suggestedMinimumWidth, suggestedMinimumHeight, resolveMeasured, resolveMeasured2);
        computeInsets(resolveMeasured - getScaledSuggestedMinimumWidth(), resolveMeasured2 - getScaledSuggestedMinimumHeight());
        setMeasuredDimension(resolveMeasured, resolveMeasured2);
    }

    private void switchToState(int i, float f, float f2) {
        switch (i) {
            case 0:
                deactivateTargets();
                hideGlow(0, 0, 0.0f, null);
                startBackgroundAnimation(0, 0.0f);
                this.mHandleDrawable.setState(TargetDrawable.STATE_INACTIVE);
                this.mHandleDrawable.setAlpha(1.0f);
                return;
            case 1:
                startBackgroundAnimation(0, 0.0f);
                return;
            case 2:
                this.mHandleDrawable.setAlpha(0.0f);
                deactivateTargets();
                showTargets(true);
                startBackgroundAnimation(ActivationAdapter.OP_CONFIGURATION_INITIAL, 1.0f);
                setGrabbedState(1);
                if (((AccessibilityManager) getContext().getSystemService("accessibility")).isEnabled()) {
                    announceTargets();
                    return;
                }
                return;
            case 3:
                this.mHandleDrawable.setAlpha(0.0f);
                showGlow(0, 0, 1.0f, null);
                return;
            case 4:
                this.mHandleDrawable.setAlpha(0.0f);
                showGlow(0, 0, 0.0f, null);
                return;
            case 5:
                doFinish();
                return;
            default:
                return;
        }
    }

    private void showGlow(int i, int i2, float f, a aVar) {
        this.mGlowAnimations.cancel();
        this.mGlowAnimations.add(Tweener.to(this.mPointCloud.glowManager, (long) i, "ease", Cubic.easeIn, "delay", Integer.valueOf(i2), "alpha", Float.valueOf(f), "onUpdate", this.mUpdateListener, "onComplete", aVar));
        this.mGlowAnimations.start();
    }

    private void hideGlow(int i, int i2, float f, a aVar) {
        this.mGlowAnimations.cancel();
        this.mGlowAnimations.add(Tweener.to(this.mPointCloud.glowManager, (long) i, "ease", Quart.easeOut, "delay", Integer.valueOf(i2), "alpha", Float.valueOf(f), "x", Float.valueOf(0.0f), "y", Float.valueOf(0.0f), "onUpdate", this.mUpdateListener, "onComplete", aVar));
        this.mGlowAnimations.start();
    }

    private void deactivateTargets() {
        int size = this.mTargetDrawables.size();
        for (int i = 0; i < size; i++) {
            ((TargetDrawable) this.mTargetDrawables.get(i)).setState(TargetDrawable.STATE_INACTIVE);
        }
        this.mActiveTarget = -1;
    }

    private void dispatchTriggerEvent(int i) {
        vibrate();
        if (this.mOnTriggerListener != null) {
            this.mOnTriggerListener.onTrigger(this, i);
        }
    }

    private void dispatchOnFinishFinalAnimation() {
        if (this.mOnTriggerListener != null) {
            this.mOnTriggerListener.onFinishFinalAnimation();
        }
    }

    private void doFinish() {
        int i = this.mActiveTarget;
        if (i != -1) {
            highlightSelected(i);
            hideGlow(ActivationAdapter.OP_CONFIGURATION_INITIAL, RETURN_TO_HOME_DELAY, 0.0f, this.mResetListener);
            dispatchTriggerEvent(i);
            if (!this.mAlwaysTrackFinger) {
                this.mTargetAnimations.stop();
            }
        } else {
            hideGlow(ActivationAdapter.OP_CONFIGURATION_INITIAL, 0, 0.0f, this.mResetListenerWithPing);
            hideTargets(true, false);
        }
        setGrabbedState(0);
    }

    private void highlightSelected(int i) {
        ((TargetDrawable) this.mTargetDrawables.get(i)).setState(TargetDrawable.STATE_ACTIVE);
        hideUnselected(i);
    }

    private void hideUnselected(int i) {
        for (int i2 = 0; i2 < this.mTargetDrawables.size(); i2++) {
            if (i2 != i) {
                ((TargetDrawable) this.mTargetDrawables.get(i2)).setAlpha(0.0f);
            }
        }
    }

    private void hideTargets(boolean z, boolean z2) {
        this.mTargetAnimations.cancel();
        this.mAnimatingTargets = z;
        int i = z ? ActivationAdapter.OP_CONFIGURATION_INITIAL : 0;
        int i2 = z ? ActivationAdapter.OP_CONFIGURATION_INITIAL : 0;
        float f = z2 ? 1.0f : TARGET_SCALE_COLLAPSED;
        int size = this.mTargetDrawables.size();
        Interpolator interpolator = Cubic.easeOut;
        for (int i3 = 0; i3 < size; i3++) {
            TargetDrawable targetDrawable = (TargetDrawable) this.mTargetDrawables.get(i3);
            targetDrawable.setState(TargetDrawable.STATE_INACTIVE);
            this.mTargetAnimations.add(Tweener.to(targetDrawable, (long) i, "ease", interpolator, "alpha", Float.valueOf(0.0f), "scaleX", Float.valueOf(f), "scaleY", Float.valueOf(f), "delay", Integer.valueOf(i2), "onUpdate", this.mUpdateListener));
        }
        float f2 = (z2 ? 1.0f : RING_SCALE_COLLAPSED) * this.mRingScaleFactor;
        this.mTargetAnimations.add(Tweener.to(this.mOuterRing, (long) i, "ease", interpolator, "alpha", Float.valueOf(0.0f), "scaleX", Float.valueOf(f2), "scaleY", Float.valueOf(f2), "delay", Integer.valueOf(i2), "onUpdate", this.mUpdateListener, "onComplete", this.mTargetUpdateListener));
        this.mTargetAnimations.start();
    }

    private void showTargets(boolean z) {
        this.mTargetAnimations.stop();
        this.mAnimatingTargets = z;
        int i = z ? SHOW_ANIMATION_DELAY : 0;
        int i2 = z ? ActivationAdapter.OP_CONFIGURATION_INITIAL : 0;
        int size = this.mTargetDrawables.size();
        for (int i3 = 0; i3 < size; i3++) {
            TargetDrawable targetDrawable = (TargetDrawable) this.mTargetDrawables.get(i3);
            targetDrawable.setState(TargetDrawable.STATE_INACTIVE);
            this.mTargetAnimations.add(Tweener.to(targetDrawable, (long) i2, "ease", Cubic.easeOut, "alpha", Float.valueOf(1.0f), "scaleX", Float.valueOf(1.0f), "scaleY", Float.valueOf(1.0f), "delay", Integer.valueOf(i), "onUpdate", this.mUpdateListener));
        }
        float f = this.mRingScaleFactor * 1.0f;
        this.mTargetAnimations.add(Tweener.to(this.mOuterRing, (long) i2, "ease", Cubic.easeOut, "alpha", Float.valueOf(1.0f), "scaleX", Float.valueOf(f), "scaleY", Float.valueOf(f), "delay", Integer.valueOf(i), "onUpdate", this.mUpdateListener, "onComplete", this.mTargetUpdateListener));
        this.mTargetAnimations.start();
    }

    private void vibrate() {
        if (this.mVibrator != null) {
            this.mVibrator.vibrate((long) this.mVibrationDuration);
        }
    }

    private ArrayList<TargetDrawable> loadDrawableArray(int i) {
        Resources resources = getContext().getResources();
        TypedArray obtainTypedArray = resources.obtainTypedArray(i);
        int length = obtainTypedArray.length();
        ArrayList<TargetDrawable> arrayList = new ArrayList(length);
        for (int i2 = 0; i2 < length; i2++) {
            int i3;
            TypedValue peekValue = obtainTypedArray.peekValue(i2);
            if (peekValue != null) {
                i3 = peekValue.resourceId;
            } else {
                i3 = 0;
            }
            arrayList.add(new TargetDrawable(resources, i3, 3));
        }
        obtainTypedArray.recycle();
        return arrayList;
    }

    private void internalSetTargetResources(int i) {
        ArrayList loadDrawableArray = loadDrawableArray(i);
        this.mTargetDrawables = loadDrawableArray;
        this.mTargetResourceId = i;
        int width = this.mHandleDrawable.getWidth();
        int height = this.mHandleDrawable.getHeight();
        int size = loadDrawableArray.size();
        int i2 = width;
        width = height;
        for (height = 0; height < size; height++) {
            TargetDrawable targetDrawable = (TargetDrawable) loadDrawableArray.get(height);
            i2 = Math.max(i2, targetDrawable.getWidth());
            width = Math.max(width, targetDrawable.getHeight());
        }
        if (this.mMaxTargetWidth == i2 && this.mMaxTargetHeight == width) {
            updateTargetPositions(this.mWaveCenterX, this.mWaveCenterY);
            updatePointCloudPosition(this.mWaveCenterX, this.mWaveCenterY);
            return;
        }
        this.mMaxTargetWidth = i2;
        this.mMaxTargetHeight = width;
        requestLayout();
    }

    public void setTargetResources(int i) {
        if (this.mAnimatingTargets) {
            this.mNewTargetResources = i;
        } else {
            internalSetTargetResources(i);
        }
    }

    public int getTargetResourceId() {
        return this.mTargetResourceId;
    }

    public void setTargetDescriptionsResourceId(int i) {
        this.mTargetDescriptionsResourceId = i;
        if (this.mTargetDescriptions != null) {
            this.mTargetDescriptions.clear();
        }
    }

    public int getTargetDescriptionsResourceId() {
        return this.mTargetDescriptionsResourceId;
    }

    public void setDirectionDescriptionsResourceId(int i) {
        this.mDirectionDescriptionsResourceId = i;
        if (this.mDirectionDescriptions != null) {
            this.mDirectionDescriptions.clear();
        }
    }

    public int getDirectionDescriptionsResourceId() {
        return this.mDirectionDescriptionsResourceId;
    }

    public void setVibrateEnabled(boolean z) {
        if (z && this.mVibrator == null) {
            this.mVibrator = (Vibrator) getContext().getSystemService("vibrator");
        } else {
            this.mVibrator = null;
        }
    }

    public void ping() {
        if (this.mFeedbackCount > 0) {
            int i;
            AnimationBundle animationBundle = this.mWaveAnimations;
            if (animationBundle.size() <= 0 || !((Tweener) animationBundle.get(0)).animator.k() || ((Tweener) animationBundle.get(0)).animator.i() >= 675) {
                i = 1;
            } else {
                i = 0;
            }
            if (i != 0) {
                startWaveAnimation();
            }
        }
    }

    private void stopAndHideWaveAnimation() {
        this.mWaveAnimations.cancel();
        this.mPointCloud.waveManager.setAlpha(0.0f);
    }

    private void startWaveAnimation() {
        this.mWaveAnimations.cancel();
        this.mPointCloud.waveManager.setAlpha(1.0f);
        this.mPointCloud.waveManager.setRadius(((float) this.mHandleDrawable.getWidth()) / 2.0f);
        this.mWaveAnimations.add(Tweener.to(this.mPointCloud.waveManager, 1350, "ease", Quad.easeOut, "delay", Integer.valueOf(0), "radius", Float.valueOf(this.mOuterRadius * 2.0f), "onUpdate", this.mUpdateListener, "onComplete", new com.a.a.b() {
            public void onAnimationEnd(com.a.a.a aVar) {
                GlowPadView.this.mPointCloud.waveManager.setRadius(0.0f);
                GlowPadView.this.mPointCloud.waveManager.setAlpha(0.0f);
            }
        }));
        this.mWaveAnimations.start();
    }

    public void reset(boolean z) {
        this.mGlowAnimations.stop();
        this.mTargetAnimations.stop();
        startBackgroundAnimation(0, 0.0f);
        stopAndHideWaveAnimation();
        hideTargets(z, false);
        hideGlow(0, 0, 0.0f, null);
        Tweener.reset();
    }

    private void startBackgroundAnimation(int i, float f) {
        Drawable background = getBackground();
        if (this.mAlwaysTrackFinger && background != null) {
            if (this.mBackgroundAnimator != null) {
                this.mBackgroundAnimator.animator.b();
            }
            this.mBackgroundAnimator = Tweener.to(background, (long) i, "ease", Cubic.easeIn, "alpha", Integer.valueOf((int) (255.0f * f)), "delay", Integer.valueOf(SHOW_ANIMATION_DELAY));
            this.mBackgroundAnimator.animator.a();
        }
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        boolean z = false;
        switch (motionEvent.getActionMasked()) {
            case 0:
            case 5:
                handleDown(motionEvent);
                handleMove(motionEvent);
                z = true;
                break;
            case 1:
            case 6:
                handleMove(motionEvent);
                handleUp(motionEvent);
                z = true;
                break;
            case 2:
                handleMove(motionEvent);
                z = true;
                break;
            case 3:
                handleMove(motionEvent);
                handleCancel(motionEvent);
                z = true;
                break;
        }
        invalidate();
        if (z) {
            return true;
        }
        return super.onTouchEvent(motionEvent);
    }

    private void updateGlowPosition(float f, float f2) {
        float y = (f2 - this.mOuterRing.getY()) * (1.0f / this.mRingScaleFactor);
        this.mPointCloud.glowManager.setX(((f - this.mOuterRing.getX()) * (1.0f / this.mRingScaleFactor)) + this.mOuterRing.getX());
        this.mPointCloud.glowManager.setY(y + this.mOuterRing.getY());
    }

    private void handleDown(MotionEvent motionEvent) {
        int actionIndex = motionEvent.getActionIndex();
        float x = motionEvent.getX(actionIndex);
        float y = motionEvent.getY(actionIndex);
        switchToState(1, x, y);
        if (trySwitchToFirstTouchState(x, y)) {
            this.mPointerId = motionEvent.getPointerId(actionIndex);
            updateGlowPosition(x, y);
            return;
        }
        this.mDragging = false;
    }

    private void handleUp(MotionEvent motionEvent) {
        int actionIndex = motionEvent.getActionIndex();
        if (motionEvent.getPointerId(actionIndex) == this.mPointerId) {
            switchToState(5, motionEvent.getX(actionIndex), motionEvent.getY(actionIndex));
        }
    }

    private void handleCancel(MotionEvent motionEvent) {
        int findPointerIndex = motionEvent.findPointerIndex(this.mPointerId);
        if (findPointerIndex == -1) {
            findPointerIndex = 0;
        }
        switchToState(5, motionEvent.getX(findPointerIndex), motionEvent.getY(findPointerIndex));
    }

    @TargetApi(16)
    private void handleMove(MotionEvent motionEvent) {
        int historySize = motionEvent.getHistorySize();
        ArrayList arrayList = this.mTargetDrawables;
        int size = arrayList.size();
        int findPointerIndex = motionEvent.findPointerIndex(this.mPointerId);
        if (findPointerIndex != -1) {
            int i = 0;
            float f = 0.0f;
            float f2 = 0.0f;
            int i2 = -1;
            while (i < historySize + 1) {
                float historicalX;
                if (i < historySize) {
                    historicalX = motionEvent.getHistoricalX(findPointerIndex, i);
                } else {
                    historicalX = motionEvent.getX(findPointerIndex);
                }
                if (i < historySize) {
                    f = motionEvent.getHistoricalY(findPointerIndex, i);
                } else {
                    f = motionEvent.getY(findPointerIndex);
                }
                float f3 = historicalX - this.mWaveCenterX;
                float f4 = f - this.mWaveCenterY;
                f2 = (float) Math.sqrt((double) dist2(f3, f4));
                f2 = f2 > this.mOuterRadius ? this.mOuterRadius / f2 : 1.0f;
                float f5 = f3 * f2;
                float f6 = f4 * f2;
                double atan2 = Math.atan2((double) (-f4), (double) f3);
                if (!this.mDragging) {
                    trySwitchToFirstTouchState(historicalX, f);
                }
                if (this.mDragging) {
                    f = (this.mRingScaleFactor * this.mOuterRadius) - this.mSnapMargin;
                    historicalX = f * f;
                    int i3 = 0;
                    while (i3 < size) {
                        int i4;
                        double d = (((((double) i3) - 0.5d) * 2.0d) * 3.141592653589793d) / ((double) size);
                        double d2 = (((((double) i3) + 0.5d) * 2.0d) * 3.141592653589793d) / ((double) size);
                        if (((TargetDrawable) arrayList.get(i3)).isEnabled()) {
                            Object obj = ((atan2 <= d || atan2 > d2) && (6.283185307179586d + atan2 <= d || 6.283185307179586d + atan2 > d2)) ? null : 1;
                            if (obj != null && dist2(f3, f4) > historicalX) {
                                i4 = i3;
                                i3++;
                                i2 = i4;
                            }
                        }
                        i4 = i2;
                        i3++;
                        i2 = i4;
                    }
                }
                i++;
                f2 = f5;
                f = f6;
            }
            if (this.mDragging) {
                if (i2 != -1) {
                    switchToState(4, f2, f);
                    updateGlowPosition(f2, f);
                } else {
                    switchToState(3, f2, f);
                    updateGlowPosition(f2, f);
                }
                if (this.mActiveTarget != i2) {
                    if (this.mActiveTarget != -1) {
                        ((TargetDrawable) arrayList.get(this.mActiveTarget)).setState(TargetDrawable.STATE_INACTIVE);
                    }
                    if (i2 != -1) {
                        ((TargetDrawable) arrayList.get(i2)).setState(TargetDrawable.STATE_FOCUSED);
                        if (((AccessibilityManager) getContext().getSystemService("accessibility")).isEnabled()) {
                            CharSequence targetDescription = getTargetDescription(i2);
                            if (VERSION.SDK_INT >= 16) {
                                announceForAccessibility(targetDescription);
                            }
                        }
                    }
                }
                this.mActiveTarget = i2;
            }
        }
    }

    @TargetApi(14)
    public boolean onHoverEvent(MotionEvent motionEvent) {
        if (((AccessibilityManager) getContext().getSystemService("accessibility")).isTouchExplorationEnabled()) {
            int action = motionEvent.getAction();
            switch (action) {
                case 7:
                    motionEvent.setAction(2);
                    break;
                case 9:
                    motionEvent.setAction(0);
                    break;
                case 10:
                    motionEvent.setAction(1);
                    break;
            }
            onTouchEvent(motionEvent);
            motionEvent.setAction(action);
        }
        super.onHoverEvent(motionEvent);
        return true;
    }

    private void setGrabbedState(int i) {
        if (i != this.mGrabbedState) {
            if (i != 0) {
                vibrate();
            }
            this.mGrabbedState = i;
            if (this.mOnTriggerListener != null) {
                if (i == 0) {
                    this.mOnTriggerListener.onReleased(this, 1);
                } else {
                    this.mOnTriggerListener.onGrabbed(this, 1);
                }
                this.mOnTriggerListener.onGrabbedStateChange(this, i);
            }
        }
    }

    private boolean trySwitchToFirstTouchState(float f, float f2) {
        float f3 = f - this.mWaveCenterX;
        float f4 = f2 - this.mWaveCenterY;
        if (!this.mAlwaysTrackFinger && dist2(f3, f4) > getScaledGlowRadiusSquared()) {
            return false;
        }
        switchToState(2, f, f2);
        updateGlowPosition(f3, f4);
        this.mDragging = true;
        return true;
    }

    private void assignDefaultsIfNeeded() {
        if (this.mOuterRadius == 0.0f) {
            this.mOuterRadius = ((float) Math.max(this.mOuterRing.getWidth(), this.mOuterRing.getHeight())) / 2.0f;
        }
        if (this.mSnapMargin == 0.0f) {
            this.mSnapMargin = TypedValue.applyDimension(1, SNAP_MARGIN_DEFAULT, getContext().getResources().getDisplayMetrics());
        }
        if (this.mInnerRadius == 0.0f) {
            this.mInnerRadius = ((float) this.mHandleDrawable.getWidth()) / 10.0f;
        }
    }

    private void computeInsets(int i, int i2) {
        int a = e.a(this.mGravity, ad.e(this));
        switch (a & 7) {
            case 3:
                this.mHorizontalInset = 0;
                break;
            case 5:
                this.mHorizontalInset = i;
                break;
            default:
                this.mHorizontalInset = i / 2;
                break;
        }
        switch (a & 112) {
            case 48:
                this.mVerticalInset = 0;
                return;
            case 80:
                this.mVerticalInset = i2;
                return;
            default:
                this.mVerticalInset = i2 / 2;
                return;
        }
    }

    private float computeScaleFactor(int i, int i2, int i3, int i4) {
        float f = 1.0f;
        if (!this.mAllowScaling) {
            return 1.0f;
        }
        float f2;
        int a = e.a(this.mGravity, ad.e(this));
        switch (a & 7) {
            case 3:
            case 5:
                f2 = 1.0f;
                break;
            default:
                if (i <= i3) {
                    f2 = 1.0f;
                    break;
                }
                f2 = ((((float) i3) * 1.0f) - ((float) this.mMaxTargetWidth)) / ((float) (i - this.mMaxTargetWidth));
                break;
        }
        switch (a & 112) {
            case 48:
            case 80:
                break;
            default:
                if (i2 > i4) {
                    f = ((1.0f * ((float) i4)) - ((float) this.mMaxTargetHeight)) / ((float) (i2 - this.mMaxTargetHeight));
                    break;
                }
                break;
        }
        return Math.min(f2, f);
    }

    private float getRingWidth() {
        return this.mRingScaleFactor * Math.max((float) this.mOuterRing.getWidth(), 2.0f * this.mOuterRadius);
    }

    private float getRingHeight() {
        return this.mRingScaleFactor * Math.max((float) this.mOuterRing.getHeight(), 2.0f * this.mOuterRadius);
    }

    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        int i5 = i3 - i;
        i5 = i4 - i2;
        float ringWidth = getRingWidth();
        ringWidth = ((ringWidth + ((float) this.mMaxTargetWidth)) / 2.0f) + ((float) this.mHorizontalInset);
        float f = (float) this.mVerticalInset;
        float ringHeight = ((getRingHeight() + ((float) this.mMaxTargetHeight)) / 2.0f) + f;
        if (this.mInitialLayout) {
            stopAndHideWaveAnimation();
            hideTargets(false, false);
            this.mInitialLayout = false;
        }
        this.mOuterRing.setPositionX(ringWidth);
        this.mOuterRing.setPositionY(ringHeight);
        this.mPointCloud.setScale(this.mRingScaleFactor);
        this.mHandleDrawable.setPositionX(ringWidth);
        this.mHandleDrawable.setPositionY(ringHeight);
        updateTargetPositions(ringWidth, ringHeight);
        updatePointCloudPosition(ringWidth, ringHeight);
        updateGlowPosition(ringWidth, ringHeight);
        this.mWaveCenterX = ringWidth;
        this.mWaveCenterY = ringHeight;
    }

    private void updateTargetPositions(float f, float f2) {
        ArrayList arrayList = this.mTargetDrawables;
        int size = arrayList.size();
        float f3 = (float) (-6.283185307179586d / ((double) size));
        for (int i = 0; i < size; i++) {
            TargetDrawable targetDrawable = (TargetDrawable) arrayList.get(i);
            float f4 = ((float) i) * f3;
            targetDrawable.setPositionX(f);
            targetDrawable.setPositionY(f2);
            targetDrawable.setX((getRingWidth() / 2.0f) * ((float) Math.cos((double) f4)));
            targetDrawable.setY(((float) Math.sin((double) f4)) * (getRingHeight() / 2.0f));
        }
    }

    private void updatePointCloudPosition(float f, float f2) {
        this.mPointCloud.setCenter(f, f2);
    }

    protected void onDraw(Canvas canvas) {
        this.mPointCloud.draw(canvas);
        this.mOuterRing.draw(canvas);
        int size = this.mTargetDrawables.size();
        for (int i = 0; i < size; i++) {
            TargetDrawable targetDrawable = (TargetDrawable) this.mTargetDrawables.get(i);
            if (targetDrawable != null) {
                targetDrawable.draw(canvas);
            }
        }
        this.mHandleDrawable.draw(canvas);
    }

    public void setOnTriggerListener(OnTriggerListener onTriggerListener) {
        this.mOnTriggerListener = onTriggerListener;
    }

    private float square(float f) {
        return f * f;
    }

    private float dist2(float f, float f2) {
        return (f * f) + (f2 * f2);
    }

    private float getScaledGlowRadiusSquared() {
        float f;
        if (((AccessibilityManager) getContext().getSystemService("accessibility")).isEnabled()) {
            f = TAP_RADIUS_SCALE_ACCESSIBILITY_ENABLED * this.mGlowRadius;
        } else {
            f = this.mGlowRadius;
        }
        return square(f);
    }

    @TargetApi(16)
    private void announceTargets() {
        StringBuilder stringBuilder = new StringBuilder();
        int size = this.mTargetDrawables.size();
        for (int i = 0; i < size; i++) {
            CharSequence targetDescription = getTargetDescription(i);
            Object directionDescription = getDirectionDescription(i);
            if (!(TextUtils.isEmpty(targetDescription) || TextUtils.isEmpty(directionDescription))) {
                stringBuilder.append(String.format(directionDescription, new Object[]{targetDescription}));
            }
        }
        if (stringBuilder.length() > 0 && VERSION.SDK_INT >= 16) {
            announceForAccessibility(stringBuilder.toString());
        }
    }

    private String getTargetDescription(int i) {
        if (this.mTargetDescriptions == null || this.mTargetDescriptions.isEmpty()) {
            this.mTargetDescriptions = loadDescriptions(this.mTargetDescriptionsResourceId);
            if (this.mTargetDrawables.size() != this.mTargetDescriptions.size()) {
                Log.w(TAG, "The number of target drawables must be equal to the number of target descriptions.");
                return null;
            }
        }
        return (String) this.mTargetDescriptions.get(i);
    }

    private String getDirectionDescription(int i) {
        if (this.mDirectionDescriptions == null || this.mDirectionDescriptions.isEmpty()) {
            this.mDirectionDescriptions = loadDescriptions(this.mDirectionDescriptionsResourceId);
            if (this.mTargetDrawables.size() != this.mDirectionDescriptions.size()) {
                Log.w(TAG, "The number of target drawables must be equal to the number of direction descriptions.");
                return null;
            }
        }
        return (String) this.mDirectionDescriptions.get(i);
    }

    private ArrayList<String> loadDescriptions(int i) {
        TypedArray obtainTypedArray = getContext().getResources().obtainTypedArray(i);
        int length = obtainTypedArray.length();
        ArrayList<String> arrayList = new ArrayList(length);
        for (int i2 = 0; i2 < length; i2++) {
            arrayList.add(obtainTypedArray.getString(i2));
        }
        obtainTypedArray.recycle();
        return arrayList;
    }

    public int getResourceIdForTarget(int i) {
        TargetDrawable targetDrawable = (TargetDrawable) this.mTargetDrawables.get(i);
        return targetDrawable == null ? 0 : targetDrawable.getResourceId();
    }

    public void setEnableTarget(int i, boolean z) {
        for (int i2 = 0; i2 < this.mTargetDrawables.size(); i2++) {
            TargetDrawable targetDrawable = (TargetDrawable) this.mTargetDrawables.get(i2);
            if (targetDrawable.getResourceId() == i) {
                targetDrawable.setEnabled(z);
                return;
            }
        }
    }

    public int getTargetPosition(int i) {
        for (int i2 = 0; i2 < this.mTargetDrawables.size(); i2++) {
            if (((TargetDrawable) this.mTargetDrawables.get(i2)).getResourceId() == i) {
                return i2;
            }
        }
        return -1;
    }

    private boolean replaceTargetDrawables(Resources resources, int i, int i2) {
        boolean z = false;
        if (!(i == 0 || i2 == 0)) {
            ArrayList arrayList = this.mTargetDrawables;
            int size = arrayList.size();
            int i3 = 0;
            while (i3 < size) {
                boolean z2;
                TargetDrawable targetDrawable = (TargetDrawable) arrayList.get(i3);
                if (targetDrawable == null || targetDrawable.getResourceId() != i) {
                    z2 = z;
                } else {
                    targetDrawable.setDrawable(resources, i2);
                    z2 = true;
                }
                i3++;
                z = z2;
            }
            if (z) {
                requestLayout();
            }
        }
        return z;
    }

    public boolean replaceTargetDrawablesIfPresent(ComponentName componentName, String str, int i) {
        boolean z = false;
        if (i != 0) {
            if (componentName != null) {
                try {
                    PackageManager packageManager = getContext().getPackageManager();
                    Bundle bundle = packageManager.getActivityInfo(componentName, NotificationCompat.FLAG_HIGH_PRIORITY).metaData;
                    if (bundle != null) {
                        int i2 = bundle.getInt(str);
                        if (i2 != 0) {
                            z = replaceTargetDrawables(packageManager.getResourcesForActivity(componentName), i, i2);
                        }
                    }
                } catch (Throwable e) {
                    Log.w(TAG, "Failed to swap drawable; " + componentName.flattenToShortString() + " not found", e);
                } catch (Throwable e2) {
                    Log.w(TAG, "Failed to swap drawable from " + componentName.flattenToShortString(), e2);
                }
            }
            if (!z) {
                replaceTargetDrawables(getContext().getResources(), i, i);
            }
        }
        return z;
    }
}
