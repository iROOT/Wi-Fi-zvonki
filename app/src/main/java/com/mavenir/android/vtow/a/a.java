package com.mavenir.android.vtow.a;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import com.fgmicrotec.mobile.android.fgvoip.FgVoIP;
import com.fgmicrotec.mobile.android.fgvoip.f;
import com.mavenir.android.common.q;
import com.mavenir.android.fragments.c;
import com.mavenir.android.fragments.e;
import com.mavenir.android.settings.c.k;

public class a extends FragmentPagerAdapter {
    Fragment a;
    e b;
    c c;
    com.mavenir.android.messaging.b.a d;

    public a(FragmentManager fragmentManager) {
        super(fragmentManager);
    }

    public Fragment getItem(int i) {
        this.a = null;
        switch (i) {
            case 0:
                if (this.b == null) {
                    this.b = new e();
                }
                return this.b;
            case 1:
                if (this.c == null) {
                    this.c = new c();
                }
                return this.c;
            case 2:
                if (this.d == null) {
                    this.d = new com.mavenir.android.messaging.b.a();
                }
                return this.d;
            default:
                q.d("SectionsPagerAdapter", "Fragment not recognized");
                return null;
        }
    }

    public int getCount() {
        if (k.u()) {
            return 3;
        }
        return 2;
    }

    public CharSequence getPageTitle(int i) {
        switch (i) {
            case 0:
                return FgVoIP.U().getString(f.k.cd_maintab_contacts);
            case 1:
                return FgVoIP.U().getString(f.k.cd_maintab_calllog);
            case 2:
                return FgVoIP.U().getString(f.k.cd_maintab_messages);
            default:
                return "Unknown";
        }
    }
}
