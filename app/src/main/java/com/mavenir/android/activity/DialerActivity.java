package com.mavenir.android.activity;

import android.content.ContentUris;
import android.content.Intent;
import android.database.Cursor;
import android.media.AudioManager;
import android.media.ToneGenerator;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.provider.ContactsContract.Contacts;
import android.provider.Settings.System;
import android.support.v4.app.LoaderManager.LoaderCallbacks;
import android.support.v4.content.n;
import android.support.v7.app.ActionBarActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.TypedValue;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ListView;
import android.widget.Toast;
import com.fgmicrotec.mobile.android.fgmag.VoIP;
import com.fgmicrotec.mobile.android.fgvoip.FgVoIP;
import com.fgmicrotec.mobile.android.fgvoip.f.a;
import com.fgmicrotec.mobile.android.fgvoip.f.c;
import com.fgmicrotec.mobile.android.fgvoip.f.g;
import com.fgmicrotec.mobile.android.fgvoip.f.h;
import com.mavenir.android.a.f;
import com.mavenir.android.common.q;
import com.mavenir.android.common.t;
import com.mavenir.android.settings.c.k;
import java.util.HashMap;

public class DialerActivity extends ActionBarActivity implements LoaderCallbacks<Cursor>, TextWatcher, OnClickListener, OnLongClickListener, OnTouchListener, OnItemClickListener {
    private static String i = "";
    private static final HashMap<Integer, Character> m = new HashMap();
    private static final HashMap<Character, Integer> n = new HashMap();
    private View a;
    private View b;
    private ListView c;
    private EditText d;
    private ImageButton e;
    private ImageButton f;
    private ImageButton g;
    private ImageButton h;
    private String j;
    private LayoutParams k;
    private f l;
    private ToneGenerator o;
    private final Object p = new Object();
    private boolean q;

    public /* synthetic */ void onLoadFinished(n nVar, Object obj) {
        a(nVar, (Cursor) obj);
    }

    static {
        n.put(Character.valueOf('1'), Integer.valueOf(1));
        n.put(Character.valueOf('2'), Integer.valueOf(2));
        n.put(Character.valueOf('3'), Integer.valueOf(3));
        n.put(Character.valueOf('4'), Integer.valueOf(4));
        n.put(Character.valueOf('5'), Integer.valueOf(5));
        n.put(Character.valueOf('6'), Integer.valueOf(6));
        n.put(Character.valueOf('7'), Integer.valueOf(7));
        n.put(Character.valueOf('8'), Integer.valueOf(8));
        n.put(Character.valueOf('9'), Integer.valueOf(9));
        n.put(Character.valueOf('0'), Integer.valueOf(0));
        n.put(Character.valueOf('#'), Integer.valueOf(11));
        n.put(Character.valueOf('*'), Integer.valueOf(10));
        m.put(Integer.valueOf(g.one), Character.valueOf('1'));
        m.put(Integer.valueOf(g.two), Character.valueOf('2'));
        m.put(Integer.valueOf(g.three), Character.valueOf('3'));
        m.put(Integer.valueOf(g.four), Character.valueOf('4'));
        m.put(Integer.valueOf(g.five), Character.valueOf('5'));
        m.put(Integer.valueOf(g.six), Character.valueOf('6'));
        m.put(Integer.valueOf(g.seven), Character.valueOf('7'));
        m.put(Integer.valueOf(g.eight), Character.valueOf('8'));
        m.put(Integer.valueOf(g.nine), Character.valueOf('9'));
        m.put(Integer.valueOf(g.zero), Character.valueOf('0'));
        m.put(Integer.valueOf(g.pound), Character.valueOf('#'));
        m.put(Integer.valueOf(g.star), Character.valueOf('*'));
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        overridePendingTransition(a.slide_in_up, a.collapse_down);
        super.setContentView(h.dialpad_fragment);
        a();
        this.l = new f(this, this, true);
        this.c.setAdapter(this.l);
    }

    public void onPause() {
        super.onPause();
        i();
        synchronized (this.p) {
            if (this.o != null) {
                this.o.release();
                this.o = null;
            }
        }
    }

    public void onResume() {
        boolean z = true;
        super.onResume();
        if (System.getInt(getContentResolver(), "dtmf_tone", 1) != 1) {
            z = false;
        }
        this.q = z;
        synchronized (this.p) {
            if (this.o == null) {
                try {
                    this.o = new ToneGenerator(8, 80);
                } catch (RuntimeException e) {
                    q.c("DialerActivity", "Error while creating local tone generator: " + e);
                    this.o = null;
                }
            }
        }
    }

    public void onBackPressed() {
        a("");
        super.onBackPressed();
    }

    public void finish() {
        super.finish();
        overridePendingTransition(a.slide_in_up, a.slide_out_down);
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        int itemId = menuItem.getItemId();
        if (itemId == g.menu_add_contact) {
            c();
        } else if (itemId == g.menu_call) {
            d();
        } else if (itemId == g.menu_remove_digit) {
            f();
        }
        return super.onOptionsItemSelected(menuItem);
    }

    public boolean onTouch(View view, MotionEvent motionEvent) {
        int id = view.getId();
        if (m.containsKey(Integer.valueOf(id))) {
            Character ch = (Character) m.get(Integer.valueOf(id));
            if (motionEvent.getAction() == 0) {
                a(ch);
                a(((Integer) n.get(ch)).intValue(), (int) VoIP.REASON_CODE_CALLEE_TEMP_UNAVAILABLE);
            } else if (motionEvent.getAction() == 1) {
                i();
            }
        }
        return false;
    }

    public void onClick(View view) {
        int id = view.getId();
        if (id == g.fakeTransparentView) {
            finish();
        } else if (id == g.addContactButton) {
            c();
        } else if (id == g.initiateAudioCallButton) {
            d();
        } else if (id == g.initiateVideoCallButton) {
            e();
        } else if (id == g.call_button) {
            a(view);
        } else if (id == g.sms_button) {
            b(view);
        } else if (id == g.clearButton) {
            f();
        }
    }

    public boolean onLongClick(View view) {
        int id = view.getId();
        if (id == g.zero) {
            g();
        } else if (id == g.clearButton) {
            this.d.getText().clear();
        }
        return false;
    }

    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        Cursor cursor = (Cursor) this.l.getItem(i);
        long j2 = cursor.getLong(cursor.getColumnIndex("contact_id"));
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.setClass(this, ContactDetailsActivity.class);
        intent.setData(ContentUris.withAppendedId(Contacts.CONTENT_URI, j2));
        startActivity(intent);
        this.d.getText().clear();
        finish();
    }

    public void afterTextChanged(Editable editable) {
    }

    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        this.j = charSequence.toString();
        this.e.setEnabled(charSequence.length() > 0);
        a(this.j);
    }

    public n<Cursor> onCreateLoader(int i, Bundle bundle) {
        if (VERSION.SDK_INT >= 9) {
            String str = "has_phone_number=?";
            new String[1][0] = "1";
        }
        Uri uri = Phone.CONTENT_URI;
        if (this.j == null) {
            return null;
        }
        str = "_id IN (SELECT DISTINCT data_id  FROM phone_lookup WHERE normalized_number LIKE '%" + this.j + "%'";
        if (this.j.startsWith("00")) {
            str = str + " OR normalized_number LIKE '+" + this.j.substring(2) + "%'";
        } else if (this.j.startsWith("0")) {
            str = str + " OR normalized_number LIKE '%" + this.j.substring(1) + "%'";
        }
        String str2 = str + ")";
        if (k.z() == 0) {
            return new android.support.v4.content.k(this, uri, new String[]{"_id", "contact_id", "display_name", "photo_id", "data1", "data2", "data3", "display_name_alt"}, str2, null, "display_name COLLATE LOCALIZED ASC");
        }
        return new android.support.v4.content.k(this, uri, new String[]{"_id", "contact_id", "display_name", "photo_id", "data1", "data2", "data3", "display_name_alt"}, str2, null, "display_name_alt COLLATE LOCALIZED ASC");
    }

    public void a(n<Cursor> nVar, Cursor cursor) {
        if (cursor == null || cursor.getCount() <= 0) {
            this.c.setVisibility(8);
            if (VERSION.SDK_INT >= 16) {
                this.c.setImportantForAccessibility(4);
                return;
            }
            return;
        }
        this.c.setVisibility(0);
        this.l.b(cursor);
        if (VERSION.SDK_INT >= 16) {
            this.c.setImportantForAccessibility(0);
        }
    }

    public void onLoaderReset(n<Cursor> nVar) {
        this.l.b(null);
    }

    private void a() {
        this.a = findViewById(g.fakeTransparentView);
        this.b = findViewById(g.listviewContainer);
        this.c = (ListView) findViewById(g.contactListView);
        this.d = (EditText) findViewById(g.numberEditText);
        this.e = (ImageButton) findViewById(g.addContactButton);
        this.g = (ImageButton) findViewById(g.initiateVideoCallButton);
        this.f = (ImageButton) findViewById(g.initiateAudioCallButton);
        this.h = (ImageButton) findViewById(g.clearButton);
        if (this.d != null) {
            this.d.addTextChangedListener(this);
            this.d.setContentDescription(getString(com.fgmicrotec.mobile.android.fgvoip.f.k.cd_dialpad_edittext, new Object[]{this.d.getText().toString()}));
        }
        if (this.a != null) {
            this.a.setOnClickListener(this);
            this.k = (LayoutParams) this.a.getLayoutParams();
        }
        if (this.e != null) {
            this.e.setOnClickListener(this);
            this.e.setEnabled(this.d.getText().toString().trim().length() > 0);
        }
        if (this.f != null) {
            this.f.setOnClickListener(this);
            this.f.setContentDescription(getString(com.fgmicrotec.mobile.android.fgvoip.f.k.cd_dialpad_call_button, new Object[]{t.a.b(this.d.getText().toString())}));
        }
        if (this.h != null) {
            this.h.setOnClickListener(this);
            this.h.setOnLongClickListener(this);
        }
        if (this.c != null) {
            this.c.setOnItemClickListener(this);
        }
        if (FgVoIP.U().aj() && FgVoIP.U().z()) {
            this.e.setVisibility(8);
            this.g.setVisibility(0);
        } else {
            this.e.setVisibility(0);
            this.g.setVisibility(8);
        }
        for (Integer intValue : m.keySet()) {
            int intValue2 = intValue.intValue();
            View findViewById = findViewById(intValue2);
            findViewById.setOnTouchListener(this);
            if (intValue2 == g.zero) {
                findViewById.setOnLongClickListener(this);
            }
        }
    }

    private int b() {
        TypedValue typedValue = new TypedValue();
        if (getTheme().resolveAttribute(c.actionBarSize, typedValue, true)) {
            return TypedValue.complexToDimensionPixelSize(typedValue.data, getResources().getDisplayMetrics());
        }
        return 0;
    }

    private void c() {
        FgVoIP.U().h(this.d.getText().toString());
    }

    private void d() {
        String trim = this.d.getText().toString().trim();
        if (trim.length() > 0) {
            FgVoIP.U().j(trim);
            i = trim;
            this.d.getText().clear();
            finish();
        } else if (i.length() > 0) {
            this.d.setText(i);
        }
    }

    private void e() {
        String trim = this.d.getText().toString().trim();
        if (trim.length() > 0) {
            FgVoIP.U().k(trim);
            i = trim;
            this.d.getText().clear();
            finish();
        } else if (i.length() > 0) {
            this.d.setText(i);
        }
    }

    private void a(View view) {
        String c = c(view);
        if (c != null) {
            this.d.setText(t.f.a(c));
            d();
            finish();
            return;
        }
        Toast.makeText(this, getString(com.fgmicrotec.mobile.android.fgvoip.f.k.call_invalid_number), 0).show();
    }

    private void b(View view) {
        String c = c(view);
        if (c != null) {
            a.a(this, t.f.a(c), null, false);
            this.d.getText().clear();
            finish();
            return;
        }
        Toast.makeText(this, getString(com.fgmicrotec.mobile.android.fgvoip.f.k.call_invalid_number), 0).show();
    }

    private String c(View view) {
        int intValue = ((Integer) view.getTag()).intValue();
        Cursor a = this.l.a();
        if (a != null) {
            a.moveToPosition(intValue);
        }
        if (a != null) {
            return a.getString(a.getColumnIndex("data1"));
        }
        return null;
    }

    private void a(Character ch) {
        this.d.getText().append(ch.charValue());
        String b = t.a.b(this.d.getText().toString());
        this.d.setContentDescription(getString(com.fgmicrotec.mobile.android.fgvoip.f.k.cd_dialpad_edittext, new Object[]{b}));
        this.f.setContentDescription(getString(com.fgmicrotec.mobile.android.fgvoip.f.k.cd_dialpad_call_button, new Object[]{b}));
        t.a.a(ch.toString());
    }

    private void f() {
        int length = this.d.getText().length();
        Character ch = null;
        if (length > 0) {
            ch = Character.valueOf(this.d.getText().charAt(length - 1));
            this.d.setText(this.d.getText().delete(length - 1, length));
        }
        if (ch != null) {
            t.a.a(getString(com.fgmicrotec.mobile.android.fgvoip.f.k.cd_dialpad_deleted, new Object[]{ch.toString()}));
        }
        String b = t.a.b(this.d.getText().toString());
        this.d.setContentDescription(getString(com.fgmicrotec.mobile.android.fgvoip.f.k.cd_dialpad_edittext, new Object[]{b}));
    }

    private void g() {
        int length = this.d.getText().length();
        char charAt = this.d.getText().charAt(0);
        if (length == 1 && charAt != '+') {
            h();
            a(Character.valueOf('+'));
        }
    }

    private void h() {
        int selectionStart = this.d.getSelectionStart();
        if (selectionStart > 0) {
            this.d.setSelection(selectionStart);
            this.d.getText().delete(selectionStart - 1, selectionStart);
        }
    }

    private void a(int i, int i2) {
        if (this.q) {
            int ringerMode = ((AudioManager) getSystemService("audio")).getRingerMode();
            if (ringerMode != 0 && ringerMode != 1) {
                synchronized (this.p) {
                    if (this.o == null) {
                        q.c("DialerActivity", "playTone: mToneGenerator == null, tone: " + i);
                        return;
                    }
                    this.o.startTone(i, i2);
                }
            }
        }
    }

    private void i() {
        if (this.q) {
            synchronized (this.p) {
                if (this.o == null) {
                    q.c("DialerActivity", "stopTone: mToneGenerator == null");
                    return;
                }
                this.o.stopTone();
            }
        }
    }

    private void a(String str) {
        if (str.length() <= 0) {
            this.l.a(null);
            String str2 = "";
            this.b.setVisibility(8);
            this.a.setLayoutParams(this.k);
        } else {
            this.l.a(this.j);
            ViewGroup.LayoutParams layoutParams = new LayoutParams(-1, b());
            this.b.setVisibility(0);
            this.a.setLayoutParams(layoutParams);
        }
        if (getSupportLoaderManager() != null) {
            getSupportLoaderManager().restartLoader(0, null, this);
        }
    }
}
