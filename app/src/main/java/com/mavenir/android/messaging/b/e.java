package com.mavenir.android.messaging.b;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.DialogInterface.OnDismissListener;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.ContentObserver;
import android.database.Cursor;
import android.graphics.Point;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcelable;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.provider.ContactsContract.Contacts;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.support.v4.app.LoaderManager.LoaderCallbacks;
import android.support.v4.content.k;
import android.support.v4.content.n;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.Display;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.View.OnLayoutChangeListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FilterQueryProvider;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import android.widget.Toast;
import com.fgmicrotec.mobile.android.fgmag.VoIP;
import com.fgmicrotec.mobile.android.fgvoip.FgVoIP;
import com.fgmicrotec.mobile.android.fgvoip.f.g;
import com.fgmicrotec.mobile.android.fgvoip.f.h;
import com.fgmicrotec.mobile.android.fgvoip.f.i;
import com.mavenir.android.common.o;
import com.mavenir.android.common.q;
import com.mavenir.android.common.t;
import com.mavenir.android.messaging.a.f;
import com.mavenir.android.messaging.c.d;
import com.mavenir.android.view.FlowLayout;

public class e extends ListFragment implements LoaderCallbacks<Cursor>, TextWatcher, OnClickListener, OnFocusChangeListener, OnLayoutChangeListener, OnScrollListener, OnItemClickListener, OnItemLongClickListener, OnEditorActionListener, o {
    private int A = -1;
    private int a = -2;
    private com.mavenir.android.messaging.a.e b = null;
    private LinearLayout c;
    private LinearLayout d;
    private ScrollView e;
    private FlowLayout f;
    private AutoCompleteTextView g;
    private ProgressBar h;
    private ImageButton i;
    private EditText j;
    private View k;
    private ImageView l;
    private String[] m;
    private String[] n;
    private boolean o = false;
    private boolean p = false;
    private boolean q = false;
    private boolean r = false;
    private f s;
    private Handler t;
    private c u;
    private String v;
    private boolean w = false;
    private boolean x = false;
    private com.mavenir.android.messaging.c.c y;
    private a z;

    private class a extends ContentObserver {
        final /* synthetic */ e a;

        public a(e eVar, Handler handler) {
            this.a = eVar;
            super(handler);
        }

        public void onChange(boolean z) {
            super.onChange(z);
            q.a("MessagesFragment", "ContactsContentObserver.onChange()");
            if (this.a.y != null) {
                q.a("MessagesFragment", "ContactsContentObserver.reloadingConversationData()");
                this.a.h();
            }
        }

        public boolean deliverSelfNotifications() {
            return true;
        }
    }

    private class b extends AsyncTask<Void, Void, com.mavenir.android.messaging.c.c> {
        final /* synthetic */ e a;

        private b(e eVar) {
            this.a = eVar;
        }

        /* synthetic */ b(e eVar, AnonymousClass1 anonymousClass1) {
            this(eVar);
        }

        protected /* synthetic */ Object doInBackground(Object[] objArr) {
            return a((Void[]) objArr);
        }

        protected /* synthetic */ void onPostExecute(Object obj) {
            a((com.mavenir.android.messaging.c.c) obj);
        }

        protected void onPreExecute() {
            super.onPreExecute();
            this.a.getListView().setVisibility(8);
            this.a.h.setVisibility(0);
        }

        protected com.mavenir.android.messaging.c.c a(Void... voidArr) {
            return this.a.t();
        }

        protected void a(com.mavenir.android.messaging.c.c cVar) {
            super.onPostExecute(cVar);
            if (cVar == null || cVar.c() == this.a.y.c()) {
                q.a("MessagesFragment", "onLoadFinished(): no messages - returning to threads list");
                Activity activity = this.a.getActivity();
                if (activity != null) {
                    activity.finish();
                }
            } else {
                q.a("MessagesFragment", "onLoadFinished(): ThreadId=" + this.a.y.c() + ", new threadId=" + cVar.c());
                this.a.a(cVar.c());
            }
            try {
                this.a.getListView().setVisibility(0);
                this.a.h.setVisibility(8);
            } catch (Exception e) {
                q.d("MessagesFragment", "onPostExecute(): " + e);
            }
        }
    }

    private class c extends BroadcastReceiver {
        final /* synthetic */ e a;

        private c(e eVar) {
            this.a = eVar;
        }

        /* synthetic */ c(e eVar, AnonymousClass1 anonymousClass1) {
            this(eVar);
        }

        public void onReceive(Context context, Intent intent) {
            if (intent != null && intent.getAction() != null) {
                if ("com.fgmicrotec.mobile.android.voip.ReceivedMessageSipSmsInd".equals(intent.getAction())) {
                    if (intent.hasExtra("extra_message_sender_uri_string")) {
                        String stringExtra = intent.getStringExtra("extra_message_sender_uri_string");
                        if (stringExtra != null && !stringExtra.equals(this.a.y)) {
                        }
                    }
                } else if ("com.fgmicrotec.mobile.android.voip.SendMessageSipSmsCnf".equals(intent.getAction())) {
                    this.a.i.setEnabled(true);
                    this.a.a("", false);
                } else if ("com.fgmicrotec.mobile.android.voip.SendMessageLegacySmsCnf".equals(intent.getAction())) {
                    this.a.i.setEnabled(true);
                    this.a.a("", false);
                } else if ("com.fgmicrotec.mobile.android.voip.ReceivedSMSStatusInd".equals(intent.getAction())) {
                    if (intent.getIntExtra("extra_response_status_type", -1) != 0) {
                    }
                } else if (!"com.fgmicrotec.mobile.android.voip.SendMessageSipSmsResp".equals(intent.getAction()) && "com.fgmicrotec.mobile.android.voip.ActionMessageDeleted".equals(intent.getAction())) {
                    this.a.x = true;
                    com.mavenir.android.messaging.utils.b.c(this.a.y.c());
                }
            }
        }
    }

    public /* synthetic */ void onLoadFinished(n nVar, Object obj) {
        a(nVar, (Cursor) obj);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        super.onCreateView(layoutInflater, viewGroup, bundle);
        return (ViewGroup) layoutInflater.inflate(h.message_fragment, viewGroup, false);
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        getActivity().getActionBar().setDisplayHomeAsUpEnabled(true);
        setHasOptionsMenu(true);
        d();
        this.s = new f(getActivity(), null, null);
        setListAdapter(this.s);
        this.t = new Handler();
        h();
        c();
        getLoaderManager().initLoader(0, null, this);
        f();
    }

    public void onDestroy() {
        super.onDestroy();
        getActivity().unregisterReceiver(this.u);
        g();
        if (TextUtils.isEmpty(this.j.getText().toString())) {
            a(true);
        } else {
            p();
        }
        this.y.p();
    }

    public void onResume() {
        super.onResume();
        if (this.w) {
            this.w = false;
            this.t.postDelayed(new Runnable(this) {
                final /* synthetic */ e a;

                {
                    this.a = r1;
                }

                public void run() {
                    FgVoIP.U().showSoftKeyboard(this.a.j);
                    this.a.p = true;
                }
            }, 100);
        }
        this.y.p();
    }

    public n<Cursor> onCreateLoader(int i, Bundle bundle) {
        switch (i) {
            case 0:
                String str;
                Uri b = this.y.b();
                if (this.v == null || this.v.length() <= 0) {
                    str = null;
                } else {
                    str = "address LIKE '%" + this.v + "%' OR " + "body" + " LIKE '%" + this.v + "%'";
                }
                return new k(getActivity(), b, d.a, str, null, "normalized_date ASC");
            default:
                return null;
        }
    }

    public void a(n<Cursor> nVar, Cursor cursor) {
        if (!(this.s.a() == null || this.s.a().isClosed())) {
            q.a("MessagesFragment", "onLoadFinished(): closing previous cursor");
            this.s.a().close();
        }
        if (cursor.isClosed()) {
            q.a("MessagesFragment", "onLoadFinished(): RESTARTING");
            getLoaderManager().restartLoader(0, null, this);
            return;
        }
        q.a("MessagesFragment", "onLoadFinished(): num: " + cursor.getCount());
        if (this.x && (cursor == null || cursor.getCount() == 0)) {
            q.a("MessagesFragment", "onLoadFinished(): Message deleted, no more messages - returning to threads list");
            Activity activity = getActivity();
            if (activity != null) {
                activity.finish();
            }
        } else if (cursor.getCount() != 0 || this.y.c() <= 0 || this.y.o()) {
            int i;
            this.y.p();
            this.s = new f(getActivity(), cursor, this.v);
            f fVar = this.s;
            boolean z = this.y.g() != null && this.y.g().size() > 1;
            fVar.a(z);
            setListAdapter(this.s);
            q.a("MessagesFragment", "onLoadFinished(): setting new cursor");
            ListView listView = getListView();
            if (this.A > -1) {
                i = this.A;
            } else {
                i = getListView().getCount();
            }
            listView.setSelection(i);
        } else {
            new b().execute(new Void[]{(Void) null});
        }
    }

    public void onLoaderReset(n<Cursor> nVar) {
        this.s.b(null);
    }

    public void onClick(View view) {
        int id = view.getId();
        if (id == -10) {
            a((Button) view);
        } else if (id == g.message_attach_button) {
            j();
        } else if (id == g.message_send_button) {
            k();
        } else if (id == g.message_compose_text) {
            l();
        }
    }

    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        if (i >= 0) {
            Cursor cursor = (Cursor) this.b.getItem(i);
            if (cursor == null || cursor.getCount() <= 0) {
                c(null);
                return;
            }
            String string = cursor.getString(cursor.getColumnIndex("display_name"));
            String string2 = cursor.getString(cursor.getColumnIndex("data1"));
            if (t.f.h(string2)) {
                b(string, string2);
                return;
            }
            c(string2);
            d(getString(com.fgmicrotec.mobile.android.fgvoip.f.k.message_recipient_invalid_title), getString(com.fgmicrotec.mobile.android.fgvoip.f.k.message_recipient_invalid));
        }
    }

    public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long j) {
        Cursor a = this.s.a();
        int position = a.getPosition();
        a.moveToPosition(i);
        d dVar = new d(getActivity(), a);
        a.moveToPosition(position);
        com.mavenir.android.messaging.utils.d.a(getActivity(), dVar).show(getActivity().getSupportFragmentManager(), "MessageActionDialog");
        return true;
    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
        super.onCreateOptionsMenu(menu, menuInflater);
        menuInflater.inflate(i.message_fragment, menu);
        MenuItem findItem = menu.findItem(g.menu_add);
        if (this.y != null && this.y.g() != null) {
            findItem.setVisible(this.y.g().d());
        }
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        int itemId = menuItem.getItemId();
        if (itemId == g.menu_call) {
            m();
            return true;
        } else if (itemId != g.menu_add) {
            return super.onOptionsItemSelected(menuItem);
        } else {
            s();
            return true;
        }
    }

    public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
        if (i == 5) {
            this.j.requestFocus();
            return true;
        } else if (i != 4) {
            return false;
        } else {
            k();
            return true;
        }
    }

    public void afterTextChanged(Editable editable) {
        this.g.setContentDescription(com.mavenir.android.common.t.a.b(this.g.getText().toString()));
    }

    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        if (charSequence.length() >= 1) {
            if (charSequence.toString().equals("\n")) {
                this.g.getText().clear();
                this.j.requestFocus();
            } else if (charSequence.charAt(charSequence.length() - 1) == '\n') {
                this.g.removeTextChangedListener(this);
                int length = this.g.getText().length();
                this.g.getText().delete(length - 1, length);
                this.g.addTextChangedListener(this);
                n();
            }
        }
    }

    public void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        LayoutParams layoutParams;
        q.a("MessagesFragment", "onLayoutChange(): top: " + i2 + ", bottom: " + i4);
        int i9 = i4 - i2;
        if (this.f.getChildCount() > 0) {
            int i10 = 2;
            if (b() > VoIP.REASON_CODE_CALLEE_TEMP_UNAVAILABLE) {
                i10 = 3;
            }
            this.a = i10 * this.f.getLineHeight();
        }
        if (i9 < this.a) {
            layoutParams = new LinearLayout.LayoutParams(-1, -2);
        } else {
            layoutParams = new LinearLayout.LayoutParams(-1, this.a);
        }
        this.e.setLayoutParams(layoutParams);
        this.e.fullScroll(130);
    }

    public void onFocusChange(View view, boolean z) {
        int id = view.getId();
        if (id == g.recipientEditText) {
            this.r = n();
            if (this.y.g() != null && this.y.g().size() > 0) {
                if (z) {
                    this.d.setVisibility(0);
                    this.g.setHint(com.fgmicrotec.mobile.android.fgvoip.f.k.message_recipient_enter_hint);
                    return;
                }
                CharSequence charSequence = this.y.g().a()[0];
                if (this.y.g().size() - 1 > 0) {
                    this.g.setHint(getString(com.fgmicrotec.mobile.android.fgvoip.f.k.message_recipient_hint_contacts, charSequence, Integer.valueOf(r1)));
                } else {
                    this.g.setHint(charSequence);
                }
                this.d.setVisibility(8);
            }
        } else if (id != g.message_compose_text || !z) {
        } else {
            if (this.r) {
                this.j.setSelection(this.j.getText().length());
                return;
            }
            this.g.requestFocus();
            this.j.clearFocus();
        }
    }

    public void onScrollStateChanged(AbsListView absListView, int i) {
    }

    public void onScroll(AbsListView absListView, int i, int i2, int i3) {
        if (i3 - i2 == i) {
            this.A = -1;
        } else {
            this.A = i;
        }
    }

    public void a(int i) {
        if (this.j.length() > 0) {
            a(" " + this.m[i], true);
        } else {
            a(this.m[i], true);
        }
    }

    private int b() {
        Display defaultDisplay = getActivity().getWindowManager().getDefaultDisplay();
        Point point = new Point();
        defaultDisplay.getSize(point);
        return point.y;
    }

    private void c() {
        this.u = new c();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.fgmicrotec.mobile.android.voip.ReceivedMessageSipSmsInd");
        intentFilter.addAction("com.fgmicrotec.mobile.android.voip.SendMessageSipSmsCnf");
        intentFilter.addAction("com.fgmicrotec.mobile.android.voip.SendMessageLegacySmsCnf");
        intentFilter.addAction("com.fgmicrotec.mobile.android.voip.ReceivedSMSStatusInd");
        intentFilter.addAction("com.fgmicrotec.mobile.android.voip.SendMessageSipSmsResp");
        intentFilter.addAction("com.fgmicrotec.mobile.android.voip.RefreshMessagingThread");
        intentFilter.addAction("com.fgmicrotec.mobile.android.voip.ActionMessageDeleted");
        getActivity().registerReceiver(this.u, intentFilter);
    }

    private void d() {
        this.c = (LinearLayout) getActivity().findViewById(g.recipientMainContainer);
        this.d = (LinearLayout) getActivity().findViewById(g.recipientContainerLayout);
        this.d.setVisibility(8);
        this.e = (ScrollView) getActivity().findViewById(g.recipientScrollView);
        this.e.setScrollbarFadingEnabled(false);
        this.f = (FlowLayout) getActivity().findViewById(g.recipientFlowLayout);
        this.f.addOnLayoutChangeListener(this);
        this.g = (AutoCompleteTextView) getActivity().findViewById(g.recipientEditText);
        this.g.addTextChangedListener(this);
        this.g.setOnItemClickListener(this);
        this.g.setOnFocusChangeListener(this);
        this.g.setOnEditorActionListener(this);
        this.h = (ProgressBar) getActivity().findViewById(g.progressBar);
        e();
        this.i = (ImageButton) getActivity().findViewById(g.message_send_button);
        this.j = (EditText) getActivity().findViewById(g.message_compose_text);
        this.k = getActivity().findViewById(g.message_attachment_menu);
        this.l = (ImageButton) getActivity().findViewById(g.message_attach_button);
        if (this.i != null) {
            this.i.setOnClickListener(this);
        }
        if (this.l != null) {
            this.l.setOnClickListener(this);
        }
        if (this.j != null) {
            this.j.setOnClickListener(this);
            this.j.setOnEditorActionListener(this);
            this.j.setOnFocusChangeListener(this);
        }
        this.m = getResources().getStringArray(com.fgmicrotec.mobile.android.fgvoip.f.b.emo_shortcuts);
        this.n = getResources().getStringArray(com.fgmicrotec.mobile.android.fgvoip.f.b.text_templates);
        getListView().setEmptyView((TextView) getActivity().findViewById(g.emptyTextView));
        getListView().setFastScrollEnabled(true);
        getListView().setTextFilterEnabled(false);
        getListView().setOnScrollListener(this);
        getListView().setOnItemLongClickListener(this);
    }

    private void e() {
        this.b = new com.mavenir.android.messaging.a.e(getActivity(), null);
        this.b.a(new FilterQueryProvider(this) {
            final /* synthetic */ e a;

            {
                this.a = r1;
            }

            public Cursor runQuery(CharSequence charSequence) {
                if (charSequence != null) {
                    return this.a.b(charSequence.toString());
                }
                return null;
            }
        });
        this.g.setAdapter(this.b);
    }

    private void f() {
        this.z = new a(this, this.t);
        getActivity().getContentResolver().registerContentObserver(Contacts.CONTENT_URI, true, this.z);
    }

    private void g() {
        getActivity().getContentResolver().unregisterContentObserver(this.z);
    }

    private Cursor b(String str) {
        String[] strArr = new String[]{"_id", "display_name", "data1", "photo_id", "contact_id"};
        return getActivity().getContentResolver().query(Uri.withAppendedPath(Phone.CONTENT_FILTER_URI, str), strArr, null, null, "display_name ASC");
    }

    private void h() {
        Bundle bundle = null;
        if (!(getActivity() == null || getActivity().getIntent() == null)) {
            bundle = getActivity().getIntent().getExtras();
        }
        if (bundle == null) {
            q.d("MessagesFragment", "loadConversationData(): No Bundle data");
            return;
        }
        String action = getActivity().getIntent().getAction();
        String[] stringArray = bundle.getStringArray("EXTRA_PHONE_NUMBERS");
        long j = bundle.getLong("extra_conversationId", -1);
        if (stringArray == null || stringArray.length == 0) {
            this.y = new com.mavenir.android.messaging.c.c(getActivity(), j, true);
            if (this.y != null && this.y.c() > 0) {
                this.y.b(true);
                com.mavenir.android.messaging.utils.b.c(this.y);
            }
        } else {
            this.y = com.mavenir.android.messaging.c.c.a(getActivity(), stringArray, true);
        }
        if ("ACTION_NEW_MESSAGE".equals(action)) {
            if (this.y.c() > 0) {
                this.c.setVisibility(8);
            } else {
                this.c.setVisibility(0);
            }
        } else if ("ACTION_FORWARD_MESSAGE".equals(action)) {
            long j2 = bundle.getLong("EXTRA_MESSAGE_ID");
            if (j2 >= 0) {
                r0 = com.mavenir.android.messaging.provider.d.a(getActivity(), j2);
            } else {
                r0 = com.mavenir.android.messaging.provider.d.b(getActivity(), -j2);
            }
            if (r0 != null) {
                a(r0.g(), false);
            }
        } else if ("ACTION_RESEND_MESSAGE".equals(action)) {
            r0 = com.mavenir.android.messaging.provider.d.a(getActivity(), bundle.getLong("EXTRA_MESSAGE_ID"));
            if (r0 != null) {
                this.y = new com.mavenir.android.messaging.c.c(getActivity(), r0.d(), true);
                this.c.setVisibility(8);
                r0 = r0.g();
                if (r0 != null && r0.trim().length() > 0) {
                    this.t.postDelayed(new Runnable(this) {
                        final /* synthetic */ e b;

                        public void run() {
                            this.b.j.setText(r0);
                            this.b.k();
                        }
                    }, 100);
                }
            }
        } else {
            getActivity().getWindow().setSoftInputMode(2);
            if (this.y.c() > 0) {
                this.c.setVisibility(8);
            } else {
                this.c.setVisibility(0);
            }
            this.v = bundle.getString("EXTRA_SEARCH_STRING");
            r0 = getActivity().getIntent().getStringExtra("android.intent.extra.SUBJECT");
            action = getActivity().getIntent().getStringExtra("android.intent.extra.TEXT");
            if (r0 != null) {
                a(r0, true);
            }
            if (!(r0 == null || action == null)) {
                a("\n", true);
            }
            if (action != null) {
                a(action, true);
            }
        }
        if (this.y.o()) {
            a(com.mavenir.android.messaging.provider.d.f(getActivity(), this.y.e()), false);
        }
        getListView().setSelection(this.A > -1 ? this.A : getListView().getCount());
        i();
    }

    private void a(long j) {
        if (this.y == null || j != this.y.c()) {
            this.y = new com.mavenir.android.messaging.c.c(getActivity(), j, true);
        }
        if (this.y.c() > 0) {
            this.c.setVisibility(8);
        }
        getLoaderManager().restartLoader(0, null, this);
        i();
    }

    private void i() {
        q.a("MessagesFragment", "loadRecipients for thread id: " + com.mavenir.android.messaging.provider.a.a(this.y.c()));
        if (this.y.c() <= 0) {
            this.y.a(com.mavenir.android.messaging.c.b.a(getActivity(), getActivity().getIntent().getExtras().getStringArray("EXTRA_PHONE_NUMBERS"), false));
        }
        int i;
        com.mavenir.android.messaging.c.a aVar;
        if (this.y.c() <= 0 || this.y.g() == null || this.y.g().size() <= 0) {
            getActivity().getActionBar().setTitle(com.fgmicrotec.mobile.android.fgvoip.f.k.message_new_title);
            if (this.y.g() != null && this.y.g().size() > 0) {
                for (i = 0; i < this.y.g().size(); i++) {
                    aVar = (com.mavenir.android.messaging.c.a) this.y.g().get(i);
                    String d = aVar.d();
                    String b = aVar.b();
                    if (t.f.h(d)) {
                        b(b, d);
                    } else {
                        c(d);
                        d(getString(com.fgmicrotec.mobile.android.fgvoip.f.k.message_recipient_invalid_title), getString(com.fgmicrotec.mobile.android.fgvoip.f.k.message_recipient_invalid));
                    }
                }
                a("", true);
            }
        } else {
            CharSequence stringBuffer = new StringBuffer();
            CharSequence stringBuffer2 = new StringBuffer();
            for (i = 0; i < this.y.g().size(); i++) {
                aVar = (com.mavenir.android.messaging.c.a) this.y.g().get(i);
                String d2 = aVar.d();
                Object b2 = aVar.b();
                if (TextUtils.isEmpty(b2)) {
                    stringBuffer.append(d2);
                } else {
                    stringBuffer.append(b2);
                    if (this.y.g().size() == 1) {
                        stringBuffer2.append(d2);
                    }
                }
                if (i < this.y.g().size() - 1) {
                    stringBuffer.append(", ");
                }
            }
            getActivity().getActionBar().setTitle(stringBuffer);
            if (stringBuffer2.length() > 0) {
                getActivity().getActionBar().setSubtitle(stringBuffer2);
            }
        }
        getActivity().invalidateOptionsMenu();
    }

    private void j() {
        new b(this).show(getFragmentManager(), "dialog");
    }

    private void k() {
        if (this.j.getText().toString().trim().equals("")) {
            d(getString(com.fgmicrotec.mobile.android.fgvoip.f.k.message_empty), getString(com.fgmicrotec.mobile.android.fgvoip.f.k.message_body));
            q.c("MessagesFragment", "processSendButtonPressed(): empty message");
            return;
        }
        if (this.o) {
            this.k.setVisibility(8);
            this.o = false;
        }
        if (this.p) {
            FgVoIP.U().hideSoftKeyboard(this.j);
            this.p = false;
        }
        this.i.setEnabled(false);
        a(this.j.getText().toString());
    }

    private void l() {
        if (this.o) {
            this.k.setVisibility(8);
            this.o = false;
            this.p = true;
            return;
        }
        this.p = true;
    }

    private void m() {
        if (this.y == null || this.y.g() == null || this.y.g().size() == 0) {
            q.d("MessagesFragment", "selectNumberAndCall(): No conversation or recipient data");
        } else if (this.y.g().size() > 1) {
            u();
        } else if (this.y.g().size() == 1) {
            FgVoIP.U().j(this.y.g().b()[0]);
        }
    }

    private void a(String str, boolean z) {
        if (str != null) {
            if (z) {
                this.j.append(str);
            } else {
                this.j.setText(str);
            }
        }
        this.j.requestFocus();
        this.j.setSelection(this.j.getText().length());
    }

    public void a(String str) {
        if (!n() || a()) {
            this.g.requestFocus();
            this.j.clearFocus();
            this.i.setEnabled(true);
            d(getString(com.fgmicrotec.mobile.android.fgvoip.f.k.message_recipient_invalid_title), getString(com.fgmicrotec.mobile.android.fgvoip.f.k.message_recipient_invalid));
        } else if (this.y.g() == null || this.y.g().size() == 0) {
            Toast.makeText(getActivity(), getString(com.fgmicrotec.mobile.android.fgvoip.f.k.message_toast_no_recipients), 0).show();
            this.i.setEnabled(true);
        } else {
            for (String equals : this.y.g().b()) {
                if (equals.equals(FgVoIP.U().aA())) {
                    Toast.makeText(getActivity(), getString(com.fgmicrotec.mobile.android.fgvoip.f.k.message_toast_recipient_self), 0).show();
                    this.i.setEnabled(true);
                    return;
                }
            }
            for (String equals2 : this.y.g().b()) {
                if (t.f.g(equals2)) {
                    q.a("MessagesFragment", "sendMessage(): PhoneNumber " + equals2 + " contains letters, preventing send...");
                    Toast.makeText(getActivity(), getString(com.fgmicrotec.mobile.android.fgvoip.f.k.message_toast_invalid_recipients), 0).show();
                    this.i.setEnabled(true);
                    return;
                }
            }
            if (FgVoIP.U().at()) {
                long c = this.y.c();
                long d = this.y.d();
                if (c <= 0 && c != d) {
                    q.c("MessagesFragment", "ThreadId changed or recipients changed. original threadId: " + c + " new threadId: " + d);
                }
                boolean l = com.mavenir.android.settings.c.k.l();
                d dVar = new d(getActivity());
                dVar.a("sms");
                dVar.d(this.y.e());
                dVar.c(str);
                dVar.e(System.currentTimeMillis());
                dVar.b(com.mavenir.android.common.t.b.a(com.mavenir.android.common.t.b.a(this.y.g().b()), ":"));
                dVar.c(l);
                dVar.a(6);
                dVar.a(true);
                com.mavenir.android.messaging.provider.d.c(getActivity(), dVar);
                o();
                a("", false);
                this.i.setEnabled(true);
                FgVoIP.U().hideSoftKeyboard(this.j);
                if (c != d) {
                    a(this.y.c());
                }
                a(false);
                q();
                return;
            }
            a("", false);
            this.i.setEnabled(true);
            String str2 = "";
            for (String equals22 : this.y.g().b()) {
                if (TextUtils.isEmpty(str2)) {
                    str2 = str2 + equals22;
                } else {
                    str2 = str2 + ";" + equals22;
                }
            }
            Intent intent = new Intent("android.intent.action.SENDTO", Uri.parse("smsto:" + str2));
            intent.putExtra("sms_body", str);
            startActivity(intent);
        }
    }

    private void c(String str) {
        this.g.removeTextChangedListener(this);
        if (str != null) {
            this.g.setText(str);
            this.g.setSelection(this.g.getText().length());
        } else {
            this.g.getText().clear();
        }
        this.g.addTextChangedListener(this);
    }

    private boolean n() {
        String trim = this.g.getText().toString().trim();
        if (trim != null && trim.length() > 0) {
            if (t.f.h(trim)) {
                b(null, trim);
            } else {
                d(getString(com.fgmicrotec.mobile.android.fgvoip.f.k.message_recipient_invalid_title), getString(com.fgmicrotec.mobile.android.fgvoip.f.k.message_recipient_invalid));
                return false;
            }
        }
        return true;
    }

    public boolean a() {
        for (String h : this.y.g().b()) {
            if (!t.f.h(h)) {
                return true;
            }
        }
        return false;
    }

    private void o() {
        Intent intent = new Intent();
        intent.setAction("com.fgmicrotec.mobile.android.voip.SendMessageReq");
        getActivity().sendBroadcast(intent);
    }

    private void p() {
        String obj = this.j.getText().toString();
        Context activity = getActivity();
        if (obj.length() > 0) {
            q.a("MessagesFragment", "saveDraftSms(): " + obj);
            try {
                com.mavenir.android.messaging.utils.c.a(getActivity()).a(true);
                if (this.y.g() == null || this.y.g().isEmpty()) {
                    q.a("MessagesFragment", "saveDraftSms(): no recipients, discarding...");
                    Toast.makeText(getActivity(), getActivity().getString(com.fgmicrotec.mobile.android.fgvoip.f.k.message_discarded_draft), 0).show();
                } else if (a()) {
                    q.a("MessagesFragment", "saveDraftSms(): invalid recipients, discarding...");
                    Toast.makeText(getActivity(), getActivity().getString(com.fgmicrotec.mobile.android.fgvoip.f.k.message_discarded_draft), 0).show();
                    com.mavenir.android.messaging.utils.c.a(getActivity()).a(false);
                } else {
                    if (!this.y.g().isEmpty()) {
                        this.y.d();
                    }
                    if (this.y.c() > 0) {
                        this.y.c(true);
                        if (com.mavenir.android.messaging.provider.d.a(activity, this.y.e(), obj)) {
                            Toast.makeText(getActivity(), getActivity().getString(com.fgmicrotec.mobile.android.fgvoip.f.k.message_saved_draft), 0).show();
                        } else {
                            Toast.makeText(getActivity(), getActivity().getString(com.fgmicrotec.mobile.android.fgvoip.f.k.message_failed_draft), 0).show();
                        }
                    } else {
                        q.d("MessagesFragment", "saveDraftSms(): Failed to save draft, conversation id = -1");
                        Toast.makeText(getActivity(), getActivity().getString(com.fgmicrotec.mobile.android.fgvoip.f.k.message_failed_draft), 0).show();
                    }
                    com.mavenir.android.messaging.utils.c.a(getActivity()).a(false);
                }
            } finally {
                com.mavenir.android.messaging.utils.c.a(getActivity()).a(false);
            }
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void a(boolean r6) {
        /*
        r5 = this;
        r4 = 0;
        r0 = r5.y;
        r0 = r0.o();
        if (r0 == 0) goto L_0x004e;
    L_0x0009:
        r0 = r5.getActivity();	 Catch:{ Exception -> 0x004f }
        r0 = com.mavenir.android.messaging.utils.c.a(r0);	 Catch:{ Exception -> 0x004f }
        r1 = 1;
        r0.a(r1);	 Catch:{ Exception -> 0x004f }
        r0 = r5.getActivity();	 Catch:{ Exception -> 0x004f }
        r1 = r5.y;	 Catch:{ Exception -> 0x004f }
        r2 = r1.e();	 Catch:{ Exception -> 0x004f }
        r0 = com.mavenir.android.messaging.provider.d.d(r0, r2);	 Catch:{ Exception -> 0x004f }
        r1 = r5.y;	 Catch:{ Exception -> 0x004f }
        r2 = 0;
        r1.c(r2);	 Catch:{ Exception -> 0x004f }
        if (r0 <= 0) goto L_0x0043;
    L_0x002b:
        if (r6 == 0) goto L_0x0043;
    L_0x002d:
        r0 = r5.getActivity();	 Catch:{ Exception -> 0x004f }
        r1 = r5.getActivity();	 Catch:{ Exception -> 0x004f }
        r2 = com.fgmicrotec.mobile.android.fgvoip.f.k.message_discarded_draft;	 Catch:{ Exception -> 0x004f }
        r1 = r1.getString(r2);	 Catch:{ Exception -> 0x004f }
        r2 = 0;
        r0 = android.widget.Toast.makeText(r0, r1, r2);	 Catch:{ Exception -> 0x004f }
        r0.show();	 Catch:{ Exception -> 0x004f }
    L_0x0043:
        r0 = r5.getActivity();
        r0 = com.mavenir.android.messaging.utils.c.a(r0);
        r0.a(r4);
    L_0x004e:
        return;
    L_0x004f:
        r0 = move-exception;
        r1 = "MessagesFragment";
        r2 = r0.getLocalizedMessage();	 Catch:{ all -> 0x0069 }
        r0 = r0.getCause();	 Catch:{ all -> 0x0069 }
        com.mavenir.android.common.q.c(r1, r2, r0);	 Catch:{ all -> 0x0069 }
        r0 = r5.getActivity();
        r0 = com.mavenir.android.messaging.utils.c.a(r0);
        r0.a(r4);
        goto L_0x004e;
    L_0x0069:
        r0 = move-exception;
        r1 = r5.getActivity();
        r1 = com.mavenir.android.messaging.utils.c.a(r1);
        r1.a(r4);
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mavenir.android.messaging.b.e.a(boolean):void");
    }

    private void q() {
        if (this.y.o()) {
            try {
                com.mavenir.android.messaging.provider.d.e(getActivity(), this.y.e());
            } catch (Exception e) {
                q.c("MessagesFragment", e.getLocalizedMessage(), e.getCause());
            }
        }
    }

    private Button a(String str, String str2) {
        CharSequence charSequence;
        Button button = (Button) getLayoutInflater(null).inflate(h.message_recipient_button, null);
        button.setId(-10);
        button.setTag(str2);
        if (str == null) {
            charSequence = str2;
        } else {
            Object charSequence2 = str;
        }
        button.setText(charSequence2);
        int i = com.fgmicrotec.mobile.android.fgvoip.f.k.cd_message_recipient_button;
        Object[] objArr = new Object[1];
        if (str == null) {
            str = com.mavenir.android.common.t.a.b(str2);
        }
        objArr[0] = str;
        button.setContentDescription(getString(i, objArr));
        button.setOnClickListener(this);
        return button;
    }

    private void b(String str, String str2) {
        String i = t.f.i(str2);
        if (str == null) {
            str = com.mavenir.android.common.k.a(i, null);
        }
        if (c(str, i)) {
            d(getString(com.fgmicrotec.mobile.android.fgvoip.f.k.dialog_warning_title), getString(com.fgmicrotec.mobile.android.fgvoip.f.k.message_recipient_already_added));
        } else {
            this.f.addView(a(str, i));
            if (this.f.getChildCount() > 0 && this.d.getVisibility() == 8) {
                this.d.setVisibility(0);
            }
            r();
        }
        c(null);
    }

    private void d(String str) {
        Button button = (Button) this.f.findViewWithTag(str);
        if (button != null) {
            this.f.removeView(button);
            this.g.requestFocus();
            r();
        } else {
            q.d("MessagesFragment", "removeRecipient(): no recipient with number " + str + " found!");
        }
        if (this.f.getChildCount() == 0 && this.d.getVisibility() == 0) {
            this.d.setVisibility(8);
        }
    }

    private void e(String str) {
        Button button = (Button) this.f.findViewWithTag(str);
        if (button != null) {
            d(str);
            this.g.setText(button.getTag().toString());
            this.g.setSelection(this.g.getText().length());
            return;
        }
        q.d("MessagesFragment", "editRecipient(): no recipient with number " + str + " found!");
    }

    private void r() {
        com.mavenir.android.messaging.c.b bVar = new com.mavenir.android.messaging.c.b();
        if (this.f.getChildCount() > 0) {
            String[] strArr = new String[this.f.getChildCount()];
            for (int i = 0; i < this.f.getChildCount(); i++) {
                strArr[i] = this.f.getChildAt(i).getTag().toString();
            }
            bVar = com.mavenir.android.messaging.c.b.a(getActivity(), strArr, false);
        }
        a(bVar);
    }

    private void a(com.mavenir.android.messaging.c.b bVar) {
        if (this.y != null) {
            this.y.a(bVar);
        }
    }

    private void s() {
        String[] c = this.y.g().c();
        if (c.length == 1) {
            f(c[0]);
        } else if (c.length > 1) {
            a(c);
        }
    }

    private void f(String str) {
        FgVoIP.U().h(str);
    }

    private void g(String str) {
        Parcelable withAppendedId = ContentUris.withAppendedId(Contacts.CONTENT_URI, com.mavenir.android.common.k.a(str));
        Bundle bundle = new Bundle();
        bundle.putParcelable("contactUri", withAppendedId);
        Fragment dVar = new com.mavenir.android.fragments.d();
        dVar.setArguments(bundle);
        getParentFragment().getFragmentManager().beginTransaction().replace(getParentFragment().getId(), dVar).addToBackStack(null).commit();
    }

    private boolean c(String str, String str2) {
        return ((Button) this.f.findViewWithTag(str2)) != null;
    }

    private com.mavenir.android.messaging.c.c t() {
        if (!com.mavenir.android.messaging.provider.g.b) {
            return null;
        }
        Context activity = getActivity();
        if (activity == null) {
            return null;
        }
        q.a("MessagesFragment", "getFreshConversationForNumber(): check for new thread id");
        com.mavenir.android.messaging.c.c contentResolver = activity.getContentResolver();
        Cursor a = com.mavenir.android.messaging.provider.g.g.a((ContentResolver) contentResolver);
        if (a != null) {
            try {
                if (a.getCount() > 0) {
                    a.moveToPosition(-1);
                    while (a.moveToNext()) {
                        contentResolver = com.mavenir.android.messaging.c.c.a(activity, a, false, false);
                        if (contentResolver != null && contentResolver.c() != 0 && contentResolver.g().equals(this.y.g())) {
                            q.a("MessagesFragment", "getFreshConversationForNumber(): found new thread id: " + com.mavenir.android.messaging.provider.a.a(contentResolver.c()));
                            return contentResolver;
                        }
                    }
                }
            } finally {
                if (a != null) {
                    a.close();
                }
            }
        }
        if (a == null) {
            return null;
        }
        a.close();
        return null;
    }

    private void u() {
        Builder builder = new Builder(getActivity());
        builder.setTitle(com.fgmicrotec.mobile.android.fgvoip.f.k.outgoing_call_complete_action_using).setItems(this.y.g().a(), new DialogInterface.OnClickListener(this) {
            final /* synthetic */ e a;

            {
                this.a = r1;
            }

            public void onClick(DialogInterface dialogInterface, int i) {
                FgVoIP.U().j(this.a.y.g().b()[i]);
            }
        });
        builder.create().show();
    }

    private void a(final String[] strArr) {
        Builder builder = new Builder(getActivity());
        builder.setTitle(com.fgmicrotec.mobile.android.fgvoip.f.k.outgoing_call_complete_action_using).setItems(strArr, new DialogInterface.OnClickListener(this) {
            final /* synthetic */ e b;

            public void onClick(DialogInterface dialogInterface, int i) {
                this.b.f(strArr[i]);
            }
        });
        builder.create().show();
    }

    private void a(Button button) {
        CharSequence[] stringArray;
        final String charSequence = button.getText().toString();
        final String obj = button.getTag().toString();
        if (charSequence.equals(obj)) {
            stringArray = getResources().getStringArray(com.fgmicrotec.mobile.android.fgvoip.f.b.message_recipient_actions_add);
        } else {
            stringArray = getResources().getStringArray(com.fgmicrotec.mobile.android.fgvoip.f.b.message_recipient_actions_view);
        }
        Builder builder = new Builder(getActivity());
        builder.setTitle(button.getText());
        builder.setItems(stringArray, new DialogInterface.OnClickListener(this) {
            final /* synthetic */ e c;

            public void onClick(DialogInterface dialogInterface, int i) {
                if (i == 0) {
                    this.c.d(obj);
                } else if (i == 1) {
                    if (this.c.n()) {
                        this.c.e(obj);
                    }
                } else if (charSequence.equals(obj)) {
                    this.c.f(obj);
                } else {
                    this.c.g(obj);
                }
            }
        });
        builder.create().show();
    }

    private void d(String str, String str2) {
        if (!this.q) {
            Builder builder = new Builder(getActivity());
            builder.setCancelable(true).setIcon(17301543).setTitle(str).setMessage(str2).setPositiveButton(getString(com.fgmicrotec.mobile.android.fgvoip.f.k.dialog_ok), new DialogInterface.OnClickListener(this) {
                final /* synthetic */ e a;

                {
                    this.a = r1;
                }

                public void onClick(DialogInterface dialogInterface, int i) {
                    this.a.q = false;
                }
            }).setOnCancelListener(new OnCancelListener(this) {
                final /* synthetic */ e a;

                {
                    this.a = r1;
                }

                public void onCancel(DialogInterface dialogInterface) {
                    this.a.q = false;
                }
            });
            if (VERSION.SDK_INT >= 17) {
                builder.setOnDismissListener(new OnDismissListener(this) {
                    final /* synthetic */ e a;

                    {
                        this.a = r1;
                    }

                    public void onDismiss(DialogInterface dialogInterface) {
                        this.a.q = false;
                    }
                });
            }
            builder.create().show();
            this.q = true;
        }
    }
}
