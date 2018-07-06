package com.mavenir.android.fragments;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import com.fgmicrotec.mobile.android.fgvoip.f.a;
import com.fgmicrotec.mobile.android.fgvoip.f.e;
import com.fgmicrotec.mobile.android.fgvoip.f.f;
import com.fgmicrotec.mobile.android.fgvoip.f.g;
import com.fgmicrotec.mobile.android.fgvoip.f.h;
import com.fgmicrotec.mobile.android.fgvoip.f.k;
import com.mavenir.android.common.CallManager;
import com.mavenir.android.common.b;
import com.mavenir.android.common.q;
import com.mavenir.android.settings.c.c;

public class CallCardFragment extends Fragment {
    private View a;
    private ImageView b = null;
    private TextView c;
    private TextView d;
    private TextView e;
    private TextView f;
    private boolean g = false;
    private View h;
    private ImageView i = null;
    private TextView j;
    private TextView k;
    private TextView l;
    private boolean m = false;
    private LinearLayout n;
    private SeekBar o;
    private CallManager p;
    private Handler q;
    private Runnable r = new Runnable(this) {
        final /* synthetic */ CallCardFragment a;

        {
            this.a = r1;
        }

        public void run() {
            CharSequence ao = this.a.p.ao();
            if (this.a.f != null) {
                this.a.f.setText(ao);
            }
            this.a.q.postDelayed(this.a.r, 1000);
        }
    };
    private Runnable s = new Runnable(this) {
        final /* synthetic */ CallCardFragment a;

        {
            this.a = r1;
        }

        public void run() {
            this.a.e();
        }
    };

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        super.onCreateView(layoutInflater, viewGroup, bundle);
        return (ViewGroup) layoutInflater.inflate(h.call_screen_call_card_fragment, viewGroup, false);
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.a = view.findViewById(g.call_screen_active_contact_info_layout);
        this.b = (ImageView) view.findViewById(g.call_screen_active_contact_photo);
        this.c = (TextView) view.findViewById(g.call_screen_active_contact_name);
        this.d = (TextView) view.findViewById(g.call_screen_active_contact_number);
        this.e = (TextView) view.findViewById(g.call_screen_active_call_status);
        this.f = (TextView) view.findViewById(g.call_screen_active_call_duration);
        this.h = view.findViewById(g.call_card_passive_info);
        this.i = (ImageView) view.findViewById(g.call_screen_passive_contact_photo);
        this.j = (TextView) view.findViewById(g.call_screen_passive_contact_name);
        this.k = (TextView) view.findViewById(g.call_screen_passive_contact_number);
        this.l = (TextView) view.findViewById(g.call_screen_passive_call_status);
        this.n = (LinearLayout) view.findViewById(g.seekBarLayout);
        this.o = (SeekBar) view.findViewById(g.volumeSeekBar);
        this.o.setMax(5);
        this.o.setEnabled(false);
    }

    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.q = new Handler();
    }

    public void onDestroy() {
        super.onDestroy();
        this.q.removeCallbacks(this.r);
    }

    public void a(CallManager callManager) {
        this.p = callManager;
    }

    public void a(boolean z) {
        if (z) {
            this.a.setBackgroundColor(getResources().getColor(e.call_banner_active_background));
            this.h.setVisibility(0);
            return;
        }
        this.a.setBackgroundColor(getResources().getColor(e.call_banner_background));
        this.h.setVisibility(8);
    }

    public void b(boolean z) {
        this.d.setVisibility(z ? 0 : 4);
    }

    public void c(boolean z) {
        this.f.setVisibility(z ? 0 : 4);
    }

    public void d(boolean z) {
        Animation animation = null;
        if (getActivity() != null) {
            if (z) {
                animation = AnimationUtils.loadAnimation(getActivity(), a.grow_down);
            } else {
                animation = AnimationUtils.loadAnimation(getActivity(), a.shrink_up);
            }
        }
        if (animation != null) {
            animation.setAnimationListener(new b(this.e, z));
            if ((z && !this.g) || (!z && this.g)) {
                this.e.startAnimation(animation);
                this.g = z;
            }
        }
    }

    public void e(boolean z) {
        this.k.setVisibility(z ? 0 : 4);
    }

    public void f(boolean z) {
        Animation loadAnimation;
        if (z) {
            loadAnimation = AnimationUtils.loadAnimation(getActivity(), a.grow_down);
        } else {
            loadAnimation = AnimationUtils.loadAnimation(getActivity(), a.shrink_up);
        }
        loadAnimation.setAnimationListener(new b(this.l, z));
        if ((z && !this.m) || (!z && this.m)) {
            this.l.startAnimation(loadAnimation);
            this.m = z;
        }
    }

    public void a(String str, String str2, String str3, Bitmap bitmap, boolean z) {
        boolean z2 = false;
        if (z) {
            str = getString(k.call_screen_conference_title);
            bitmap = BitmapFactory.decodeResource(getResources(), f.picture_conference);
        }
        Object h = c.h();
        if (TextUtils.isEmpty(h) || !h.equals(str2)) {
            a(str);
            b(str2);
            if (str == null || !str.equals(str2)) {
                z2 = true;
            }
            b(z2);
            c(str3);
            a(bitmap);
            return;
        }
        a(getResources().getString(k.menu_echo_test));
        b(false);
    }

    public void a(String str) {
        if (this.c != null) {
            this.c.setText(str);
        }
    }

    public void b(String str) {
        if (this.d != null) {
            this.d.setText(str);
        }
    }

    public void c(String str) {
    }

    public void d(String str) {
        if (this.e != null) {
            this.e.setText(str);
        }
    }

    public void a(Bitmap bitmap) {
        if (this.b == null) {
            return;
        }
        if (bitmap != null) {
            this.b.setImageBitmap(bitmap);
        } else {
            this.b.setImageResource(f.picture_unknown);
        }
    }

    public void b(String str, String str2, String str3, Bitmap bitmap, boolean z) {
        q.a("CallCardFragment", "setPassiveParty(): name: " + str + ", number: " + str2);
        if (z) {
            str = getString(k.call_screen_conference_title);
            bitmap = BitmapFactory.decodeResource(getResources(), f.picture_conference);
        }
        e(str);
        f(str2);
        boolean z2 = str == null || !str.equals(str2);
        e(z2);
        g(str3);
        b(bitmap);
    }

    public void e(String str) {
        if (this.j != null) {
            this.j.setText(str);
        }
    }

    public void f(String str) {
        if (this.k != null) {
            this.k.setText(str);
        }
    }

    public void g(String str) {
    }

    public void h(String str) {
        if (this.l != null) {
            this.l.setText(str);
        }
    }

    public void b(Bitmap bitmap) {
        if (this.i == null) {
            return;
        }
        if (bitmap != null) {
            this.i.setImageBitmap(bitmap);
        } else {
            this.i.setImageResource(f.picture_unknown);
        }
    }

    public void g(boolean z) {
        this.q.removeCallbacks(this.r);
        if (z) {
            this.p.C();
            this.q.postDelayed(this.r, 1000);
            return;
        }
        this.q.post(this.r);
    }

    public void a() {
        this.q.removeCallbacks(this.r);
    }

    public void b() {
        if (this.e != null && this.p.l() == this.p.m()) {
            if (this.p.i()) {
                d(getString(k.call_screen_status_hold));
                d(true);
                return;
            }
            d(false);
        }
    }

    public boolean c() {
        if (this.e != null) {
            if (this.p.l() == this.p.m()) {
                if (this.p.t()) {
                    d(true);
                    d(getString(k.call_screen_status_held));
                    return true;
                }
                d(false);
                return true;
            } else if (this.p.B()) {
                d(true);
                d(getString(k.call_screen_status_held));
            } else {
                d(false);
            }
        }
        return false;
    }

    public void d() {
        if (this.p.n() == 2 && this.d != null && this.c != null && this.j != null && this.b != null && this.i != null) {
            if (this.p.l() == this.p.m()) {
                a(this.p.p(), this.p.q(), null, this.p.r(), false);
                b(this.p.x(), this.p.w(), null, this.p.y(), false);
                com.fgmicrotec.mobile.android.fgvoip.e.a().a(this.p.p(), this.p.q(), this.p.s(), false, true, false, false);
            } else {
                a(this.p.x(), this.p.w(), null, this.p.y(), false);
                b(this.p.p(), this.p.q(), null, this.p.r(), false);
                com.fgmicrotec.mobile.android.fgvoip.e.a().a(this.p.w(), this.p.x(), this.p.z(), false, true, false, false);
            }
            c();
        }
    }

    public void e() {
        this.n.setVisibility(8);
    }
}
