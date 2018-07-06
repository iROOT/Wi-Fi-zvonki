package com.mavenir.android.fragments;

import android.os.Bundle;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;
import com.fgmicrotec.mobile.android.fgvoip.f;
import com.fgmicrotec.mobile.android.fgvoip.f.g;
import com.mavenir.android.common.CallManager;
import com.mavenir.android.common.h;
import com.mavenir.android.common.q;
import java.util.List;

public class CallConferenceManagerFragment extends Fragment implements OnClickListener {
    private View a;
    private CallManager b;
    private List<h> c;
    private ViewGroup[] d;

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        super.onCreateView(layoutInflater, viewGroup, bundle);
        return (ViewGroup) layoutInflater.inflate(f.h.call_screen_conference_fragment, viewGroup, false);
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.a = view.findViewById(g.conference_manage_done);
        this.a.setOnClickListener(this);
        this.d = new ViewGroup[2];
        int[] iArr = new int[]{g.conference_caller0, g.conference_caller1};
        for (int i = 0; i < 2; i++) {
            this.d[i] = (ViewGroup) view.findViewById(iArr[i]);
        }
    }

    public void onClick(View view) {
        if (view.getId() == g.conference_manage_done) {
            this.b.c(false);
        } else if (view.getId() == g.conference_caller_disconnect) {
            q.a("CallConferenceManagerFragment", "onClick(): User pressed disconnect participant");
        } else if (view.getId() == g.conference_caller_separate) {
            q.a("CallConferenceManagerFragment", "onClick(): User pressed separate participant");
        }
    }

    public void a(CallManager callManager) {
        this.b = callManager;
    }

    public void a() {
        this.c = this.b.A();
        q.a("CallConferenceManagerFragment", "updateConferenceParticipants(): count: " + this.c.size());
        for (int i = 0; i < 2; i++) {
            a(i, true);
        }
    }

    private void a(int i, boolean z) {
        this.d[i].setVisibility(0);
        this.d[i].findViewById(g.conference_caller_disconnect).setOnClickListener(this);
        View findViewById = this.d[i].findViewById(g.conference_caller_separate);
        findViewById.setOnClickListener(this);
        if (z) {
            findViewById.setVisibility(0);
        } else {
            findViewById.setVisibility(4);
        }
        TextView textView = (TextView) this.d[i].findViewById(g.conference_caller_name);
        TextView textView2 = (TextView) this.d[i].findViewById(g.conference_caller_number);
        TextView textView3 = (TextView) this.d[i].findViewById(g.conference_caller_number_type);
        if (this.c != null && this.c.size() > 0) {
            a(i, textView, textView2, textView3);
        }
    }

    public final void a(int i, TextView textView, TextView textView2, TextView textView3) {
        CharSequence a = ((h) this.c.get(i)).a();
        CharSequence b = ((h) this.c.get(i)).b();
        int c = ((h) this.c.get(i)).c();
        CharSequence d = ((h) this.c.get(i)).d();
        textView.setText(a);
        if (TextUtils.isEmpty(b)) {
            textView2.setVisibility(8);
            textView3.setVisibility(8);
            return;
        }
        textView2.setVisibility(0);
        textView2.setText(b);
        d = Phone.getTypeLabel(getResources(), c, d).toString();
        if (TextUtils.isEmpty(d)) {
            textView3.setVisibility(0);
            textView3.setText(d);
            return;
        }
        textView3.setVisibility(8);
    }
}
