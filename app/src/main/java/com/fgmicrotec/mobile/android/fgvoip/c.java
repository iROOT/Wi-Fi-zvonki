package com.fgmicrotec.mobile.android.fgvoip;

import android.content.pm.PackageInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.fgmicrotec.mobile.android.fgmag.FgSDKLoader;
import com.fgmicrotec.mobile.android.fgvoip.b.a;
import com.fgmicrotec.mobile.android.fgvoip.b.b;
import com.fgmicrotec.mobile.android.fgvoip.f.g;
import com.fgmicrotec.mobile.android.fgvoip.f.h;
import com.fgmicrotec.mobile.android.fgvoip.f.k;
import com.mavenir.android.common.q;

public class c extends Fragment {
    private a a;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (getArguments().containsKey("topic_id")) {
            this.a = (a) b.b.get(Integer.valueOf(getArguments().getInt("topic_id")));
        }
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        if (this.a == null) {
            return null;
        }
        getActivity().setTitle(this.a.b);
        if (this.a == b.b.get(Integer.valueOf(b.ABOUT.ordinal()))) {
            View inflate = layoutInflater.inflate(h.about, viewGroup, false);
            a(inflate);
            return inflate;
        }
        inflate = layoutInflater.inflate(h.help_topic_detail_fragment, viewGroup, false);
        b(inflate);
        return inflate;
    }

    private void a(View view) {
        TextView textView = (TextView) view.findViewById(g.About_TextView_Name);
        TextView textView2 = (TextView) view.findViewById(g.About_TextView_Date);
        TextView textView3 = (TextView) view.findViewById(g.About_TextView_Build);
        FgSDKLoader fgSDKLoader = new FgSDKLoader();
        String str = "";
        str = "";
        try {
            PackageInfo packageInfo = getActivity().getPackageManager().getPackageInfo(getActivity().getPackageName(), 0);
            CharSequence fgGetAppVerStr = fgSDKLoader.fgGetAppVerStr();
            CharSequence fgGetAppBuildDateStr = fgSDKLoader.fgGetAppBuildDateStr();
            textView.setText(getString(k.app_name) + " v" + packageInfo.versionName);
            if (textView3 != null) {
                textView3.setText(fgGetAppVerStr);
            }
            textView2.setText(fgGetAppBuildDateStr);
        } catch (Exception e) {
            q.c("HelpTopicDetailFragment", e.getLocalizedMessage(), e.getCause());
        }
    }

    private void b(View view) {
        ((TextView) view.findViewById(g.topic_detail)).setText(Html.fromHtml(this.a.c));
    }
}
