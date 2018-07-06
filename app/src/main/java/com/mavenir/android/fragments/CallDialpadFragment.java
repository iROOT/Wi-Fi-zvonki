package com.mavenir.android.fragments;

import android.annotation.SuppressLint;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.method.DialerKeyListener;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnHoverListener;
import android.view.View.OnKeyListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityManager;
import android.widget.EditText;
import com.fgmicrotec.mobile.android.fgvoip.f.g;
import com.fgmicrotec.mobile.android.fgvoip.f.h;
import com.mavenir.android.common.CallManager;
import com.mavenir.android.common.q;
import java.util.HashMap;

public class CallDialpadFragment extends Fragment implements OnClickListener, OnHoverListener, OnKeyListener, OnTouchListener {
    private static final HashMap<Integer, Character> c = new HashMap();
    private CallManager a;
    private EditText b;
    private a d;

    private class a extends DialerKeyListener {
        public final char[] a;
        final /* synthetic */ CallDialpadFragment b;

        private a(CallDialpadFragment callDialpadFragment) {
            this.b = callDialpadFragment;
            this.a = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '#', '*'};
        }

        protected char[] getAcceptedChars() {
            return this.a;
        }

        public boolean backspace(View view, Editable editable, int i, KeyEvent keyEvent) {
            return false;
        }

        public boolean onKeyDown(View view, Editable editable, int i, KeyEvent keyEvent) {
            char lookup = (char) lookup(keyEvent, editable);
            if (keyEvent.getRepeatCount() != 0 || !super.onKeyDown(view, editable, i, keyEvent)) {
                return false;
            }
            if (ok(getAcceptedChars(), lookup)) {
                q.a("CallDialpadFragment", "DTMFKeyListener reading '" + lookup + "' from input.");
            } else {
                q.a("CallDialpadFragment", "DTMFKeyListener rejecting '" + lookup + "' from input.");
            }
            return true;
        }

        public boolean onKeyUp(View view, Editable editable, int i, KeyEvent keyEvent) {
            super.onKeyUp(view, editable, i, keyEvent);
            char lookup = (char) lookup(keyEvent, editable);
            if (!ok(getAcceptedChars(), lookup)) {
                return false;
            }
            q.a("", "Stopping the tone for '" + lookup + "'");
            return true;
        }
    }

    static {
        c.put(Integer.valueOf(g.one), Character.valueOf('1'));
        c.put(Integer.valueOf(g.two), Character.valueOf('2'));
        c.put(Integer.valueOf(g.three), Character.valueOf('3'));
        c.put(Integer.valueOf(g.four), Character.valueOf('4'));
        c.put(Integer.valueOf(g.five), Character.valueOf('5'));
        c.put(Integer.valueOf(g.six), Character.valueOf('6'));
        c.put(Integer.valueOf(g.seven), Character.valueOf('7'));
        c.put(Integer.valueOf(g.eight), Character.valueOf('8'));
        c.put(Integer.valueOf(g.nine), Character.valueOf('9'));
        c.put(Integer.valueOf(g.zero), Character.valueOf('0'));
        c.put(Integer.valueOf(g.pound), Character.valueOf('#'));
        c.put(Integer.valueOf(g.star), Character.valueOf('*'));
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(h.call_screen_dtmf_fragment, viewGroup, false);
        this.b = (EditText) inflate.findViewById(g.dtmfDialerField);
        if (this.b != null) {
            this.d = new a();
            this.b.setKeyListener(this.d);
            this.b.setLongClickable(false);
            a(inflate);
        }
        return inflate;
    }

    public void onDestroyView() {
        this.d = null;
        super.onDestroyView();
    }

    public void onClick(View view) {
        if (((AccessibilityManager) getActivity().getSystemService("accessibility")).isEnabled()) {
            q.a("CallDialpadFragment", "onClick(): accessibility manager enabled");
            int id = view.getId();
            if (!view.isPressed() && c.containsKey(Integer.valueOf(id))) {
                q.a("CallDialpadFragment", "onClick(): DTMF: " + c.get(Integer.valueOf(id)));
                a(((Character) c.get(Integer.valueOf(id))).charValue(), true);
            }
        }
    }

    public boolean onHover(View view, MotionEvent motionEvent) {
        AccessibilityManager accessibilityManager = (AccessibilityManager) getActivity().getSystemService("accessibility");
        if (accessibilityManager.isEnabled() && accessibilityManager.isTouchExplorationEnabled()) {
            int paddingLeft = view.getPaddingLeft();
            int width = view.getWidth() - view.getPaddingRight();
            int paddingTop = view.getPaddingTop();
            int height = view.getHeight() - view.getPaddingBottom();
            switch (motionEvent.getActionMasked()) {
                case 9:
                    view.setClickable(false);
                    break;
                case 10:
                    int x = (int) motionEvent.getX();
                    int y = (int) motionEvent.getY();
                    if (x > paddingLeft && x < width && y > paddingTop && y < height) {
                        view.performClick();
                    }
                    view.setClickable(true);
                    break;
            }
        }
        return false;
    }

    public boolean onKey(View view, int i, KeyEvent keyEvent) {
        q.a("CallDialpadFragment", "onKey:  keyCode " + i + ", view " + view);
        if (i == 23) {
            int id = view.getId();
            if (c.containsKey(Integer.valueOf(id))) {
                switch (keyEvent.getAction()) {
                    case 0:
                        if (keyEvent.getRepeatCount() == 0) {
                            a(((Character) c.get(Integer.valueOf(id))).charValue(), false);
                            break;
                        }
                        break;
                    case 1:
                        this.a.aj();
                        break;
                }
            }
        }
        return false;
    }

    public boolean onTouch(View view, MotionEvent motionEvent) {
        int id = view.getId();
        if (c.containsKey(Integer.valueOf(id))) {
            switch (motionEvent.getAction()) {
                case 0:
                    a(((Character) c.get(Integer.valueOf(id))).charValue(), false);
                    break;
                case 1:
                case 3:
                    this.a.aj();
                    break;
            }
        }
        return false;
    }

    private void a(char c, boolean z) {
        a(c);
        this.a.a(c, z);
    }

    public void a(char c) {
        if (this.b != null) {
            this.b.getText().append(c);
        }
    }

    public void a(CallManager callManager) {
        this.a = callManager;
    }

    @SuppressLint({"NewApi"})
    private void a(View view) {
        for (Integer intValue : c.keySet()) {
            View findViewById = view.findViewById(intValue.intValue());
            findViewById.setOnTouchListener(this);
            findViewById.setClickable(true);
            findViewById.setOnKeyListener(this);
            findViewById.setOnClickListener(this);
            if (VERSION.SDK_INT >= 14) {
                findViewById.setOnHoverListener(this);
            }
        }
    }
}
