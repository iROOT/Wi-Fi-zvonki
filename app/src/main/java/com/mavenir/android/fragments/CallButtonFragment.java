package com.mavenir.android.fragments;

import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.NotificationCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ToggleButton;
import com.fgmicrotec.mobile.android.fgvoip.FgVoIP;
import com.fgmicrotec.mobile.android.fgvoip.f.d;
import com.fgmicrotec.mobile.android.fgvoip.f.g;
import com.fgmicrotec.mobile.android.fgvoip.f.h;
import com.mavenir.android.common.CallManager;
import com.mavenir.android.common.q;
import com.mavenir.android.settings.c;

public class CallButtonFragment extends Fragment implements OnClickListener {
    private ImageButton a;
    private View b;
    private ImageButton c;
    private ToggleButton d;
    private ToggleButton e;
    private ToggleButton f;
    private ToggleButton g;
    private ToggleButton h;
    private ImageButton i;
    private ImageButton j;
    private ImageButton k;
    private ImageButton l;
    private ImageButton m;
    private ImageButton n;
    private CallManager o;

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        super.onCreateView(layoutInflater, viewGroup, bundle);
        return (ViewGroup) layoutInflater.inflate(h.call_screen_call_buttons_fragment, viewGroup, false);
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.b = view.findViewById(g.call_screen_manage_conference_button);
        this.c = (ImageButton) view.findViewById(g.call_screen_end_call_button);
        this.d = (ToggleButton) view.findViewById(g.call_screen_dialpad_button);
        this.e = (ToggleButton) view.findViewById(g.call_screen_speaker_button);
        this.f = (ToggleButton) view.findViewById(g.call_screen_bluetooth_button);
        this.g = (ToggleButton) view.findViewById(g.call_screen_mute_button);
        this.g.setEnabled(false);
        this.a = (ImageButton) view.findViewById(g.call_screen_video_on_button);
        this.h = (ToggleButton) view.findViewById(g.call_screen_hold_button);
        this.i = (ImageButton) view.findViewById(g.call_screen_swap_button);
        this.j = (ImageButton) view.findViewById(g.call_screen_merge_button);
        this.k = (ImageButton) view.findViewById(g.call_screen_add_call_button);
        this.l = (ImageButton) view.findViewById(g.call_screen_add_participant_button);
        this.m = (ImageButton) view.findViewById(g.call_screen_qos_button);
        this.n = (ImageButton) view.findViewById(g.call_screen_share_button);
        this.n.setVisibility(8);
        if (this.a != null) {
            this.a.setOnClickListener(this);
        }
        if (this.b != null) {
            this.b.setOnClickListener(this);
        }
        if (this.c != null) {
            this.c.setOnClickListener(this);
        }
        if (this.d != null) {
            this.d.setOnClickListener(this);
        }
        if (this.e != null) {
            this.e.setOnClickListener(this);
        }
        if (this.f != null) {
            this.f.setOnClickListener(this);
        }
        if (this.g != null) {
            this.g.setOnClickListener(this);
        }
        if (this.h != null) {
            this.h.setOnClickListener(this);
        }
        if (this.i != null) {
            this.i.setOnClickListener(this);
        }
        if (this.j != null) {
            this.j.setOnClickListener(this);
        }
        if (this.k != null) {
            this.k.setOnClickListener(this);
        }
        if (this.l != null) {
            this.l.setOnClickListener(this);
        }
        if (this.m != null) {
            this.m.setOnClickListener(this);
        }
        if (this.n != null) {
            this.n.setOnClickListener(this);
        }
    }

    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    public void onClick(View view) {
        int id = view.getId();
        if (id == g.call_screen_manage_conference_button) {
            q.a("CallButtonFragment", "onClick(): User pressed manage conference button");
            this.o.c(true);
        } else if (id == g.call_screen_end_call_button) {
            q.a("CallButtonFragment", "onClick(): User pressed end call button");
            this.o.T();
        } else if (id == g.call_screen_dialpad_button) {
            q.a("CallButtonFragment", "onClick(): User pressed dialpad button");
            this.o.d(this.d.isChecked());
        } else if (id == g.call_screen_speaker_button) {
            q.a("CallButtonFragment", "onClick(): User pressed speaker button");
            this.o.U();
        } else if (id == g.call_screen_bluetooth_button) {
            q.a("CallButtonFragment", "onClick(): User pressed bluetooth button");
            this.o.V();
        } else if (id == g.call_screen_mute_button) {
            q.a("CallButtonFragment", "onClick(): User pressed mute button");
            this.o.Y();
        } else if (id == g.call_screen_hold_button) {
            q.a("CallButtonFragment", "onClick(): User pressed hold button");
            this.o.ab();
        } else if (id == g.call_screen_video_on_button) {
            q.a("CallButtonFragment", "onClick(): User pressed toggle video button");
            this.o.ac();
        } else if (id == g.call_screen_swap_button) {
            q.a("CallButtonFragment", "onClick(): User pressed swap button");
            this.o.ae();
        } else if (id == g.call_screen_merge_button) {
            q.a("CallButtonFragment", "onClick(): User pressed merge button");
            this.o.ah();
        } else if (id == g.call_screen_add_call_button) {
            q.a("CallButtonFragment", "onClick(): User pressed add call button");
            this.o.af();
        } else if (id == g.call_screen_add_participant_button) {
            q.a("CallButtonFragment", "onClick(): User pressed add participant button");
            this.o.ag();
        } else if (id == g.call_screen_qos_button) {
            q.a("CallButtonFragment", "onClick(): User pressed qos button");
            this.o.am();
        } else if (id == g.call_screen_share_button) {
            q.a("CallButtonFragment", "onClick(): User pressed share button");
            this.o.ap();
        } else {
            q.d("CallButtonFragment", "onClick(): unrecognized button: " + view.getId());
        }
    }

    public void a(CallManager callManager) {
        this.o = callManager;
    }

    public void a(boolean z) {
        c(z);
        d(z);
        e(z);
        g(z);
        j(z);
        m(z);
        a(true, false);
        t(true);
        v(true);
    }

    public void a() {
        b(false);
        f(true);
        e(false);
        l(true);
        g(true);
        i(true);
        j(true);
        n(true);
        m(false);
        o(false);
        q(false);
        u(false);
        w(false);
        r(false);
        s(false);
        x(false);
    }

    public void b() {
        b(false);
        d(true);
        f(true);
        e(true);
        l(true);
        g(true);
        i(true);
        j(true);
        n(true);
        x(true);
        o(true);
        boolean z = !this.o.b() && FgVoIP.U().z();
        q(z);
        u(true);
        w(true);
        r(false);
        s(false);
    }

    public void c() {
        b(false);
        f(true);
        l(true);
        i(true);
        n(true);
        o(false);
        q(false);
        u(false);
        w(false);
        r(true);
        s(true);
        x(true);
        a(true);
    }

    public void d() {
        b(false);
        f(true);
        l(true);
        i(true);
        n(true);
        o(false);
        q(false);
        u(false);
        w(false);
        r(false);
        s(false);
        x(false);
        a(true);
    }

    public void e() {
        b(false);
        f(true);
        l(true);
        i(true);
        n(true);
        o(false);
        q(false);
        u(false);
        w(false);
        r(false);
        s(false);
        x(true);
        a(true);
    }

    public void b(boolean z) {
        this.b.setVisibility(z ? 0 : 8);
    }

    public void c(boolean z) {
        this.b.setEnabled(z);
    }

    public void d(boolean z) {
        this.c.setEnabled(z);
    }

    public void e(boolean z) {
        this.d.setEnabled(z);
    }

    public void f(boolean z) {
        this.d.setVisibility(z ? 0 : 8);
    }

    public void g(boolean z) {
        this.e.setEnabled(z);
    }

    public void h(boolean z) {
        this.e.setChecked(z);
    }

    public void i(boolean z) {
        ToggleButton toggleButton = this.f;
        int i = (z && this.o.N()) ? 0 : 8;
        toggleButton.setVisibility(i);
    }

    public void j(boolean z) {
        this.f.setEnabled(z);
    }

    public void k(boolean z) {
        this.f.setChecked(z);
        i(true);
    }

    public void l(boolean z) {
        this.e.setVisibility(z ? 0 : 8);
    }

    public void m(boolean z) {
        this.g.setEnabled(z);
    }

    public void n(boolean z) {
        this.g.setVisibility(z ? 0 : 8);
    }

    @TargetApi(11)
    public void a(boolean z, boolean z2) {
        this.h.setEnabled(z);
        this.h.setChecked(z2);
        this.h.getBackground().setAlpha(z ? 255 : NotificationCompat.FLAG_HIGH_PRIORITY);
    }

    public void o(boolean z) {
        int i;
        int i2 = 0;
        if (z && FgVoIP.U().c(d.enable_hold)) {
            i = 1;
        } else {
            i = 0;
        }
        ToggleButton toggleButton = this.h;
        if (i == 0) {
            i2 = 8;
        }
        toggleButton.setVisibility(i2);
    }

    @TargetApi(11)
    public void p(boolean z) {
        this.a.setEnabled(z);
        this.a.getBackground().setAlpha(z ? 255 : NotificationCompat.FLAG_HIGH_PRIORITY);
    }

    public void q(boolean z) {
        int i;
        int i2 = 0;
        if (z && FgVoIP.U().c(d.enable_video_toggle)) {
            i = 1;
        } else {
            i = 0;
        }
        ImageButton imageButton = this.a;
        if (i == 0) {
            i2 = 8;
        }
        imageButton.setVisibility(i2);
    }

    public void r(boolean z) {
        this.i.setVisibility(z ? 0 : 8);
    }

    public void s(boolean z) {
        this.j.setVisibility(z ? 0 : 8);
    }

    public void t(boolean z) {
        this.k.setEnabled(z);
    }

    public void u(boolean z) {
        int i;
        int i2 = 0;
        if (z && FgVoIP.U().c(d.enable_add_call)) {
            i = 1;
        } else {
            i = 0;
        }
        ImageButton imageButton = this.k;
        if (i == 0) {
            i2 = 8;
        }
        imageButton.setVisibility(i2);
    }

    public void v(boolean z) {
        this.k.setEnabled(z);
    }

    public void w(boolean z) {
        int i;
        int i2 = 0;
        if (z && FgVoIP.U().c(d.enable_add_participant)) {
            i = 1;
        } else {
            i = 0;
        }
        ImageButton imageButton = this.l;
        if (i == 0) {
            i2 = 8;
        }
        imageButton.setVisibility(i2);
    }

    public void x(boolean z) {
        int i;
        int i2 = 0;
        if (z && c.h.i()) {
            i = 1;
        } else {
            i = 0;
        }
        ImageButton imageButton = this.m;
        if (i == 0) {
            i2 = 8;
        }
        imageButton.setVisibility(i2);
    }

    public void y(boolean z) {
        this.n.setVisibility(z ? 0 : 8);
    }

    @TargetApi(16)
    public void z(boolean z) {
        int i = 255;
        this.n.setEnabled(z);
        if (VERSION.SDK_INT >= 16) {
            ImageButton imageButton = this.n;
            if (!z) {
                i = NotificationCompat.FLAG_HIGH_PRIORITY;
            }
            imageButton.setImageAlpha(i);
            return;
        }
        imageButton = this.n;
        if (!z) {
            i = NotificationCompat.FLAG_HIGH_PRIORITY;
        }
        imageButton.setAlpha(i);
    }
}
