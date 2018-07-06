package com.fgmicrotec.mobile.android.fgvoip;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class HelpTopicListFragment extends ListFragment {
    private static a c = new a() {
        public void a(int i) {
        }
    };
    private a a = c;
    private int b = -1;

    public interface a {
        void a(int i);
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setListAdapter(new ArrayAdapter(getActivity(), 17367062, 16908308, b.a));
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        if (bundle != null && bundle.containsKey("activated_position")) {
            a(bundle.getInt("activated_position"));
        }
    }

    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof a) {
            this.a = (a) activity;
            return;
        }
        throw new IllegalStateException("Activity must implement fragment's callbacks.");
    }

    public void onDetach() {
        super.onDetach();
        this.a = c;
    }

    public void onListItemClick(ListView listView, View view, int i, long j) {
        super.onListItemClick(listView, view, i, j);
        this.a.a(((com.fgmicrotec.mobile.android.fgvoip.b.a) b.a.get(i)).a);
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        if (this.b != -1) {
            bundle.putInt("activated_position", this.b);
        }
    }

    private void a(int i) {
        if (i == -1) {
            getListView().setItemChecked(this.b, false);
        } else {
            getListView().setItemChecked(i, true);
        }
        this.b = i;
    }
}
