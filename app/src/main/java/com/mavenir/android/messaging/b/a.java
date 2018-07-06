package com.mavenir.android.messaging.b;

import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager.LoaderCallbacks;
import android.support.v4.content.k;
import android.support.v4.content.n;
import android.support.v4.view.o;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.EditText;
import android.widget.ListView;
import com.fgmicrotec.mobile.android.fgvoip.f;
import com.fgmicrotec.mobile.android.fgvoip.f.g;
import com.fgmicrotec.mobile.android.fgvoip.f.h;
import com.fgmicrotec.mobile.android.fgvoip.f.i;
import com.mavenir.android.common.q;
import com.mavenir.android.messaging.activity.ConversationActivity;
import com.mavenir.android.messaging.c.c;
import com.mavenir.android.view.DockedSearchView;
import com.mavenir.android.view.DockedSearchView.b;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class a extends Fragment implements LoaderCallbacks<Cursor>, OnItemClickListener, OnItemLongClickListener, com.mavenir.android.view.DockedSearchView.a, b {
    private static String g = null;
    private ListView a;
    private View b;
    private View c;
    private DockedSearchView d = null;
    private MenuItem e = null;
    private MenuItem f = null;
    private com.mavenir.android.messaging.a.a h;

    public /* synthetic */ void onLoadFinished(n nVar, Object obj) {
        a(nVar, (Cursor) obj);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(h.message_conversation_fragment, viewGroup, false);
        this.b = inflate.findViewById(g.messenger_empty_view);
        this.a = (ListView) inflate.findViewById(g.messenger_listview);
        this.c = inflate.findViewById(g.messenger_search_empty);
        this.a.setOnItemClickListener(this);
        this.a.setOnItemLongClickListener(this);
        return inflate;
    }

    public void onStop() {
        super.onStop();
        if (g != null && g.length() > 0) {
            g = null;
            a(null);
        }
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        setHasOptionsMenu(true);
        this.h = new com.mavenir.android.messaging.a.a(getActivity(), this.a);
        this.a.setAdapter(this.h);
        getLoaderManager().initLoader(0, null, this);
    }

    public void onDestroy() {
        super.onDestroy();
    }

    public void onResume() {
        super.onResume();
        getLoaderManager().restartLoader(0, null, this);
    }

    public n<Cursor> onCreateLoader(int i, Bundle bundle) {
        String str;
        Uri uri = com.mavenir.android.messaging.provider.g.g.a;
        String[] strArr = com.mavenir.android.messaging.provider.g.g.d;
        String str2 = "date DESC";
        if (d()) {
            str = "_id IN (SELECT thread_id FROM sms WHERE type <> 3 AND (body LIKE '%" + g + "%' OR " + "address" + " LIKE '%" + g + "%'))";
        } else {
            str = null;
        }
        return new k(getActivity(), uri, strArr, str, null, str2);
    }

    public void a(n<Cursor> nVar, Cursor cursor) {
        this.h.a(cursor, g);
        if (cursor != null) {
            if (cursor.getCount() > 0) {
                if (!d()) {
                    a(cursor);
                }
                a(true, false, false);
            } else if (cursor.getCount() != 0 || g == null || g.length() <= 0) {
                a(false, true, false);
            } else {
                a(false, false, true);
            }
        }
    }

    private void a(Cursor cursor) {
        long currentTimeMillis = System.currentTimeMillis();
        int position = cursor.getPosition();
        cursor.moveToPosition(1);
        Set hashSet = new HashSet();
        while (cursor.moveToNext()) {
            hashSet.add(Long.valueOf(cursor.getLong(cursor.getColumnIndex("_id"))));
        }
        cursor.moveToPosition(position);
        com.mavenir.android.messaging.utils.b.a(hashSet);
        q.a("ConversationListFragment", "syncCache(): Cache synchronization ended in " + (System.currentTimeMillis() - currentTimeMillis) + "ms");
    }

    public void onLoaderReset(n<Cursor> nVar) {
        q.a("ConversationListFragment", "onLoadReset(): cursor -> null");
        this.h.b(null);
    }

    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        long c = c.a(getActivity(), (Cursor) this.a.getItemAtPosition(i), false).c();
        Intent intent = new Intent(getActivity().getApplicationContext(), ConversationActivity.class);
        intent.setAction("ACTION_VIEW_MESSAGES");
        intent.putExtra("extra_conversationId", c);
        if (g != null) {
            intent.putExtra("EXTRA_SEARCH_STRING", g);
        }
        startActivity(intent);
    }

    public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long j) {
        c a = c.a(getActivity(), (Cursor) this.a.getItemAtPosition(i), false);
        if (a.f()) {
            return false;
        }
        com.mavenir.android.messaging.utils.a.a(getActivity(), a).show(getFragmentManager(), "ConversationActionsDialog");
        return true;
    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
        if (com.mavenir.android.settings.c.h.i()) {
            menuInflater.inflate(i.conversations_activity_eng, menu);
        } else {
            menuInflater.inflate(i.conversations_activity, menu);
        }
        super.onCreateOptionsMenu(menu, menuInflater);
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        int itemId = menuItem.getItemId();
        if (itemId == g.menu_new_message) {
            ConversationActivity.newMessage(getActivity(), new ArrayList(), null, null, false);
            return true;
        }
        if (itemId == g.menu_simulate_receive_message) {
            e();
        } else if (itemId == g.menu_search) {
            this.d = new DockedSearchView(getActivity());
            this.d.setSearchHint(getString(f.k.messages_search_hint));
            this.d.setOnSearchTextListener(this);
            this.d.setOnSearchViewDetachedListener(this);
            if (this.e != null && this.e.isVisible()) {
                o.a(this.e, 0);
            }
            o.a(menuItem, this.d);
            o.a(menuItem, 2);
            this.f = menuItem;
            return true;
        }
        return super.onOptionsItemSelected(menuItem);
    }

    public boolean a() {
        if (this.e != null && this.e.isVisible()) {
            o.a(this.e, 5);
        }
        g = null;
        a("");
        return true;
    }

    public boolean b() {
        a("");
        c();
        return true;
    }

    public boolean a(String str) {
        g = str;
        if (isAdded()) {
            getLoaderManager().restartLoader(0, null, this);
        }
        return true;
    }

    private void c() {
        if (this.f.isVisible()) {
            o.a(this.f, null);
            o.a(this.f, 2);
        }
    }

    private void a(boolean z, boolean z2, boolean z3) {
        int i;
        int i2 = 0;
        ListView listView = this.a;
        if (z) {
            i = 0;
        } else {
            i = 8;
        }
        listView.setVisibility(i);
        View view = this.b;
        if (z2) {
            i = 0;
        } else {
            i = 8;
        }
        view.setVisibility(i);
        View view2 = this.c;
        if (!z3) {
            i2 = 8;
        }
        view2.setVisibility(i2);
    }

    private boolean d() {
        return g != null && g.length() > 0;
    }

    private void e() {
        View inflate = LayoutInflater.from(getActivity()).inflate(h.message_simulator_dialog, null);
        final EditText editText = (EditText) inflate.findViewById(g.messageTextEditText);
        final EditText editText2 = (EditText) inflate.findViewById(g.simulatedSenderEditText);
        Builder builder = new Builder(inflate.getContext());
        builder.setTitle(f.k.message_simulator_title);
        builder.setView(inflate);
        builder.setPositiveButton(f.k.message_simulator_send_button, new OnClickListener(this) {
            final /* synthetic */ a c;

            public void onClick(DialogInterface dialogInterface, int i) {
                String str = "";
                String str2 = "";
                if (editText != null) {
                    str = editText.getText().toString();
                }
                if (editText2 != null) {
                    str2 = editText2.getText().toString();
                }
                Intent intent = new Intent();
                intent.setAction("com.fgmicrotec.mobile.android.voip.SimulateReceiveSmsReq");
                intent.putExtra("extra_message_string", str);
                intent.putExtra("extra_message_sender_uri_string", str2);
                this.c.getActivity().sendBroadcast(intent);
                dialogInterface.dismiss();
            }
        });
        builder.setNegativeButton(f.k.cancel, null);
        builder.create().show();
    }
}
