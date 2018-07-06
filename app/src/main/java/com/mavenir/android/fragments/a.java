package com.mavenir.android.fragments;

import android.annotation.SuppressLint;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.provider.CallLog.Calls;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.provider.ContactsContract.Contacts;
import android.provider.ContactsContract.PhoneLookup;
import android.support.v4.app.ListFragment;
import android.support.v4.app.LoaderManager.LoaderCallbacks;
import android.support.v4.content.n;
import android.support.v7.app.ActionBarActivity;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.fgmicrotec.mobile.android.fgvoip.FgVoIP;
import com.fgmicrotec.mobile.android.fgvoip.f.f;
import com.fgmicrotec.mobile.android.fgvoip.f.g;
import com.fgmicrotec.mobile.android.fgvoip.f.h;
import com.fgmicrotec.mobile.android.fgvoip.f.i;
import com.mavenir.android.common.c;
import com.mavenir.android.common.d;
import com.mavenir.android.common.q;
import com.mavenir.android.common.t;
import com.mavenir.android.settings.c.k;
import java.io.IOException;
import java.io.InputStream;

public class a extends ListFragment implements LoaderCallbacks<Cursor>, OnClickListener {
    public static String[] a = new String[]{"_id", "display_name"};
    private static final String[] g = new String[]{"_id", "type", "name", "number", "numbertype", "numberlabel", "date", "duration", "voicemail_uri"};
    private ImageView b;
    private TextView c;
    private long d = -1;
    private com.mavenir.android.a.a e;
    private c f;
    private LoaderCallbacks<Bitmap> h = new LoaderCallbacks<Bitmap>(this) {
        final /* synthetic */ a a;

        {
            this.a = r1;
        }

        public /* synthetic */ void onLoadFinished(n nVar, Object obj) {
            a(nVar, (Bitmap) obj);
        }

        public n<Bitmap> onCreateLoader(int i, Bundle bundle) {
            return new a(this.a.getActivity(), (Uri) bundle.getParcelable("uri"));
        }

        public void a(n<Bitmap> nVar, Bitmap bitmap) {
            if (bitmap != null) {
                this.a.b.setImageBitmap(bitmap);
            } else {
                this.a.b.setImageResource(f.picture_unknown);
            }
        }

        public void onLoaderReset(n<Bitmap> nVar) {
        }
    };

    private static class a extends d<Bitmap> {
        private Uri f = null;

        @SuppressLint({"NewApi"})
        public /* synthetic */ Object h() {
            return B();
        }

        public a(Context context, Uri uri) {
            super(context);
            this.f = uri;
        }

        @SuppressLint({"NewApi"})
        public Bitmap B() {
            Bitmap bitmap;
            Exception exception;
            Exception exception2;
            Throwable th;
            Object obj;
            InputStream inputStream = null;
            InputStream openContactPhotoInputStream;
            try {
                if (VERSION.SDK_INT >= 14) {
                    openContactPhotoInputStream = Contacts.openContactPhotoInputStream(m().getContentResolver(), this.f, true);
                } else {
                    openContactPhotoInputStream = Contacts.openContactPhotoInputStream(m().getContentResolver(), this.f);
                }
                if (openContactPhotoInputStream != null) {
                    try {
                        Bitmap decodeStream = BitmapFactory.decodeStream(openContactPhotoInputStream);
                        try {
                            q.b("TEST", "w: " + decodeStream.getWidth() + ", h: " + decodeStream.getHeight());
                            bitmap = decodeStream;
                        } catch (Exception e) {
                            exception = e;
                            bitmap = decodeStream;
                            exception2 = exception;
                            try {
                                q.d("CallDetailsFragment", "doLoadInBackground(): " + exception2.getLocalizedMessage());
                                if (openContactPhotoInputStream != null) {
                                    try {
                                        openContactPhotoInputStream.close();
                                    } catch (IOException e2) {
                                        q.d("CallDetailsFragment", "doLoadInBackground(): " + e2.getLocalizedMessage());
                                    }
                                }
                                return bitmap;
                            } catch (Throwable th2) {
                                th = th2;
                                if (openContactPhotoInputStream != null) {
                                    try {
                                        openContactPhotoInputStream.close();
                                    } catch (IOException e22) {
                                        q.d("CallDetailsFragment", "doLoadInBackground(): " + e22.getLocalizedMessage());
                                    }
                                }
                                throw th;
                            }
                        }
                    } catch (Exception e3) {
                        exception = e3;
                        obj = inputStream;
                        exception2 = exception;
                        q.d("CallDetailsFragment", "doLoadInBackground(): " + exception2.getLocalizedMessage());
                        if (openContactPhotoInputStream != null) {
                            openContactPhotoInputStream.close();
                        }
                        return bitmap;
                    }
                }
                obj = inputStream;
                if (openContactPhotoInputStream != null) {
                    try {
                        openContactPhotoInputStream.close();
                    } catch (IOException e222) {
                        q.d("CallDetailsFragment", "doLoadInBackground(): " + e222.getLocalizedMessage());
                    }
                }
            } catch (Exception e32) {
                openContactPhotoInputStream = inputStream;
                exception = e32;
                bitmap = inputStream;
                exception2 = exception;
                q.d("CallDetailsFragment", "doLoadInBackground(): " + exception2.getLocalizedMessage());
                if (openContactPhotoInputStream != null) {
                    openContactPhotoInputStream.close();
                }
                return bitmap;
            } catch (Throwable th3) {
                th = th3;
                openContactPhotoInputStream = inputStream;
                if (openContactPhotoInputStream != null) {
                    openContactPhotoInputStream.close();
                }
                throw th;
            }
            return bitmap;
        }
    }

    public /* synthetic */ void onLoadFinished(n nVar, Object obj) {
        a(nVar, (Cursor) obj);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        q.a("CallDetailsFragment", "onCreateView()");
        ViewGroup viewGroup2 = (ViewGroup) layoutInflater.inflate(h.call_log_details_view, viewGroup, false);
        viewGroup2.addView(super.onCreateView(layoutInflater, viewGroup, bundle));
        return viewGroup2;
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        setHasOptionsMenu(true);
        ((ActionBarActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        this.b = (ImageView) getActivity().findViewById(g.contactImageView);
        this.c = (TextView) getActivity().findViewById(g.separatorTextView);
        this.d = b();
        a();
        this.e = new com.mavenir.android.a.a(getActivity(), this);
        setListAdapter(this.e);
        getListView().setFastScrollEnabled(false);
        getListView().setTextFilterEnabled(false);
        setListShown(false);
        if (FgVoIP.U().s() && this.f == null) {
            this.f = new c();
        }
        getLoaderManager().initLoader(0, null, this);
    }

    public void onResume() {
        super.onResume();
        a(getActivity().getIntent().getStringExtra("ExtraName"), getActivity().getIntent().getStringExtra("ExtraNumber"));
    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
        menu.clear();
        if (!t.f.j(getActivity().getIntent().getStringExtra("ExtraNumber"))) {
            menuInflater.inflate(i.call_details_activity, menu);
            MenuItem findItem = menu.findItem(g.menu_view_contact);
            MenuItem findItem2 = menu.findItem(g.menu_create_contact);
            MenuItem findItem3 = menu.findItem(g.menu_sms);
            MenuItem findItem4 = menu.findItem(g.menu_video_call);
            if (b() != -1) {
                findItem.setVisible(true);
                findItem.setEnabled(true);
                findItem2.setVisible(false);
                findItem2.setEnabled(false);
            } else {
                findItem.setVisible(false);
                findItem.setEnabled(false);
                findItem2.setVisible(true);
                findItem2.setEnabled(true);
            }
            if (!FgVoIP.U().z()) {
                findItem4.setVisible(false);
            }
            findItem3.setVisible(k.u());
        }
        super.onCreateOptionsMenu(menu, menuInflater);
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        int itemId = menuItem.getItemId();
        if (itemId == 16908332) {
            FgVoIP.U().c(getActivity());
        } else if (itemId == g.menu_call) {
            c();
        } else if (itemId == g.menu_video_call) {
            d();
        } else if (itemId == g.menu_view_contact) {
            g();
        } else if (itemId == g.menu_create_contact) {
            f();
        } else if (itemId == g.menu_sms) {
            e();
        }
        return super.onOptionsItemSelected(menuItem);
    }

    public n<Cursor> onCreateLoader(int i, Bundle bundle) {
        long longExtra = getActivity().getIntent().getLongExtra("ExtraStartDate", -1);
        long longExtra2 = getActivity().getIntent().getLongExtra("ExtraEndDate", -1);
        boolean booleanExtra = getActivity().getIntent().getBooleanExtra("ExtraMissedOnly", false);
        String str = "date<=? AND date>?";
        String[] strArr = new String[]{String.valueOf(longExtra), String.valueOf(longExtra2)};
        if (longExtra2 == -1) {
            str = "date<=?";
            strArr = new String[]{String.valueOf(longExtra)};
        }
        if (booleanExtra) {
            str = str + " AND type=?";
            strArr = longExtra2 == -1 ? new String[]{String.valueOf(longExtra), String.valueOf(3)} : new String[]{String.valueOf(longExtra), String.valueOf(longExtra2), String.valueOf(3)};
        }
        return new android.support.v4.content.k(getActivity(), Calls.CONTENT_URI, g, str, strArr, "date DESC");
    }

    public void a(n<Cursor> nVar, Cursor cursor) {
        q.a("CallDetailsFragment", "onLoadFinished(): data count: " + cursor.getCount());
        this.e.b(cursor);
        if (isResumed()) {
            setListShown(true);
        } else {
            setListShownNoAnimation(true);
        }
    }

    public void onLoaderReset(n<Cursor> nVar) {
        q.a("CallDetailsFragment", "onLoadReset(): cursor -> null");
        this.e.b(null);
    }

    public void onClick(View view) {
        if (view.getId() == g.play_button) {
            a(((Integer) view.getTag()).intValue());
        }
    }

    private void a(int i) {
        Object a = this.e.a(i);
        if (!TextUtils.isEmpty(a) && this.f != null) {
            this.f.a(a, 3);
        }
    }

    private void a() {
        CharSequence charSequence;
        CharSequence charSequence2 = null;
        CharSequence stringExtra = getActivity().getIntent().getStringExtra("ExtraName");
        String stringExtra2 = getActivity().getIntent().getStringExtra("ExtraNumber");
        String stringExtra3 = getActivity().getIntent().getStringExtra("ExtraType");
        int intExtra = getActivity().getIntent().getIntExtra("ExtraCount", -1);
        long longExtra = getActivity().getIntent().getLongExtra("ExtraStartDate", -1);
        if (intExtra > 1) {
            stringExtra + " (" + intExtra + ")";
        }
        stringExtra2 + " / " + stringExtra3;
        CharSequence a = t.d.a(longExtra, "dd/MM/yyyy");
        if (FgVoIP.U().e(stringExtra2)) {
            String string = getString(com.fgmicrotec.mobile.android.fgvoip.f.k.call_log_emergency_number);
            if (intExtra > 1) {
                string = string + " (" + intExtra + ")";
            }
            stringExtra = string;
        } else if (t.f.j(stringExtra2)) {
            r2 = getString(com.fgmicrotec.mobile.android.fgvoip.f.k.call_log_blocked_number);
            charSequence = null;
        } else {
            if (!TextUtils.isEmpty(stringExtra)) {
                charSequence2 = stringExtra2;
                stringExtra2 = stringExtra;
            }
            if (intExtra > 1) {
                r2 = stringExtra2 + " (" + intExtra + ")";
                charSequence = charSequence2;
            } else {
                r2 = stringExtra2;
                charSequence = charSequence2;
            }
        }
        ((ActionBarActivity) getActivity()).getSupportActionBar().setTitle(stringExtra);
        ((ActionBarActivity) getActivity()).getSupportActionBar().setSubtitle(charSequence);
        this.c.setText(a);
        long b = b();
        if (b > 0) {
            Bundle bundle = new Bundle();
            bundle.putParcelable("uri", ContentUris.withAppendedId(Contacts.CONTENT_URI, b));
            getLoaderManager().restartLoader(1, bundle, this.h);
        }
    }

    private long b() {
        Object e;
        Throwable th;
        String stringExtra = getActivity().getIntent().getStringExtra("ExtraName");
        String stringExtra2 = getActivity().getIntent().getStringExtra("ExtraNumber");
        if (stringExtra == null) {
            return -1;
        }
        Uri withAppendedPath = Uri.withAppendedPath(Phone.CONTENT_FILTER_URI, Uri.encode(stringExtra2));
        Cursor query;
        long j;
        try {
            query = getActivity().getContentResolver().query(withAppendedPath, new String[]{"contact_id", "display_name", "data1"}, null, null, null);
            j = -1;
            while (query.moveToNext()) {
                try {
                    if (stringExtra.equals(query.getString(1))) {
                        j = query.getLong(0);
                    }
                } catch (Exception e2) {
                    e = e2;
                }
            }
            if (query == null || query.isClosed()) {
                return j;
            }
            query.close();
            return j;
        } catch (Exception e3) {
            e = e3;
            query = null;
            j = -1;
            try {
                q.d("CallDetailsFragment", "getContactId(): " + e);
                if (query == null || query.isClosed()) {
                    return j;
                }
                query.close();
                return j;
            } catch (Throwable th2) {
                th = th2;
                query.close();
                throw th;
            }
        } catch (Throwable th3) {
            th = th3;
            query = null;
            if (!(query == null || query.isClosed())) {
                query.close();
            }
            throw th;
        }
    }

    private void c() {
        FgVoIP.U().j(getActivity().getIntent().getStringExtra("ExtraNumber"));
        getActivity().finish();
    }

    private void d() {
        FgVoIP.U().k(getActivity().getIntent().getStringExtra("ExtraNumber"));
        getActivity().finish();
    }

    private void e() {
        com.mavenir.android.activity.a.a(getActivity(), getActivity().getIntent().getStringExtra("ExtraNumber"), null, false);
        getActivity().finish();
    }

    private void f() {
        FgVoIP.U().h(getActivity().getIntent().getStringExtra("ExtraNumber"));
    }

    private void g() {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.setData(ContentUris.withAppendedId(Contacts.CONTENT_URI, this.d));
        intent.putExtra("finishActivityOnSaveCompleted", true);
        try {
            startActivity(intent);
        } catch (Throwable e) {
            q.c("CallDetailsFragment", "openViewContact()", e);
        }
    }

    private void a(String str, String str2) {
        Throwable th;
        Cursor query;
        Object string;
        try {
            q.a("CallDetailsFragment", "queryContactInfo(): number: " + str2);
            query = getActivity().getContentResolver().query(Uri.withAppendedPath(PhoneLookup.CONTENT_FILTER_URI, Uri.encode(str2)), a, null, null, null);
            if (query != null) {
                try {
                    if (query.moveToFirst()) {
                        string = query.getString(query.getColumnIndex("display_name"));
                        if (!TextUtils.equals(str, string)) {
                            getActivity().getIntent().removeExtra("ExtraName");
                            getActivity().getIntent().putExtra("ExtraName", string);
                            a();
                        }
                    }
                } catch (Exception e) {
                    string = e;
                    try {
                        q.d("CallDetailsFragment", "queryContactInfo(): " + string);
                        if (query != null) {
                            return;
                        }
                    } catch (Throwable th2) {
                        th = th2;
                        query.close();
                        throw th;
                    }
                }
            }
            if (query != null && !query.isClosed()) {
                query.close();
            }
        } catch (Exception e2) {
            string = e2;
            query = null;
            q.d("CallDetailsFragment", "queryContactInfo(): " + string);
            if (query != null && !query.isClosed()) {
                query.close();
            }
        } catch (Throwable th3) {
            th = th3;
            query = null;
            if (!(query == null || query.isClosed())) {
                query.close();
            }
            throw th;
        }
    }
}
