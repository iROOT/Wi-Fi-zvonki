package com.mavenir.android.fragments;

import android.annotation.SuppressLint;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.provider.ContactsContract.Contacts;
import android.provider.ContactsContract.Data;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager.LoaderCallbacks;
import android.support.v4.content.k;
import android.support.v4.content.n;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnGroupClickListener;
import android.widget.ExpandableListView.OnGroupCollapseListener;
import android.widget.ExpandableListView.OnGroupExpandListener;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.fgmicrotec.mobile.android.fgvoip.FgVoIP;
import com.fgmicrotec.mobile.android.fgvoip.f.f;
import com.fgmicrotec.mobile.android.fgvoip.f.g;
import com.fgmicrotec.mobile.android.fgvoip.f.h;
import com.fgmicrotec.mobile.android.fgvoip.f.i;
import com.mavenir.android.common.q;
import com.mavenir.android.common.t;
import java.io.InputStream;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class d extends Fragment {
    private static final String[] f = new String[]{"_id", "display_name"};
    private static final String[] i = new String[]{"_id", "display_name", "mimetype", "data1", "data2", "data3", "data4", "is_super_primary"};
    private Uri a = null;
    private boolean b = false;
    private ImageView c = null;
    private Set<String> d = new HashSet();
    private int[] e = new int[]{g.callButton, g.messageButton};
    private LoaderCallbacks<Cursor> g = new LoaderCallbacks<Cursor>(this) {
        final /* synthetic */ d a;

        {
            this.a = r1;
        }

        public /* synthetic */ void onLoadFinished(n nVar, Object obj) {
            a(nVar, (Cursor) obj);
        }

        public n<Cursor> onCreateLoader(int i, Bundle bundle) {
            if (this.a.a != null) {
                return new k(this.a.getActivity(), this.a.a, d.f, null, null, null);
            }
            return null;
        }

        public void a(n<Cursor> nVar, Cursor cursor) {
            if (cursor == null) {
                this.a.getActivity().finish();
            } else if (cursor.moveToFirst()) {
                long j = cursor.getLong(cursor.getColumnIndexOrThrow("_id"));
                Bundle bundle = new Bundle();
                if (this.a.b) {
                    bundle.putParcelable("uri", ContentUris.withAppendedId(Contacts.CONTENT_URI, j));
                    this.a.getLoaderManager().restartLoader(2, bundle, this.a.h);
                }
                this.a.b = true;
                bundle = new Bundle();
                bundle.putLong("contactId", j);
                this.a.getLoaderManager().restartLoader(1, bundle, this.a.j);
                CharSequence string = cursor.getString(cursor.getColumnIndexOrThrow("display_name"));
                if (TextUtils.isEmpty(string)) {
                    this.a.e().setTitle(this.a.getActivity().getTitle());
                } else {
                    this.a.e().setTitle(string);
                }
            } else {
                this.a.getActivity().finish();
            }
        }

        public void onLoaderReset(n<Cursor> nVar) {
            this.a.e().setTitle(this.a.getActivity().getTitle());
        }
    };
    private LoaderCallbacks<Bitmap> h = new LoaderCallbacks<Bitmap>(this) {
        final /* synthetic */ d a;

        {
            this.a = r1;
        }

        public /* synthetic */ void onLoadFinished(n nVar, Object obj) {
            a(nVar, (Bitmap) obj);
        }

        public n<Bitmap> onCreateLoader(int i, Bundle bundle) {
            return new d(this.a.getActivity(), (Uri) bundle.getParcelable("uri"));
        }

        public void a(n<Bitmap> nVar, Bitmap bitmap) {
            if (bitmap != null) {
                this.a.c.setImageBitmap(bitmap);
            } else {
                this.a.c.setImageResource(f.picture_unknown);
            }
        }

        public void onLoaderReset(n<Bitmap> nVar) {
        }
    };
    private LoaderCallbacks<a> j = new LoaderCallbacks<a>(this) {
        final /* synthetic */ d a;

        {
            this.a = r1;
        }

        public /* synthetic */ void onLoadFinished(n nVar, Object obj) {
            a(nVar, (a) obj);
        }

        public n<a> onCreateLoader(int i, Bundle bundle) {
            return new b(this.a, bundle.getLong("contactId"));
        }

        public void a(n<a> nVar, a aVar) {
            this.a.a(aVar);
        }

        public void onLoaderReset(n<a> nVar) {
        }
    };

    public static class a {
        public List<c> a = null;
    }

    private static class b extends com.mavenir.android.common.d<a> {
        private d f = null;
        private long g = 0;

        public /* synthetic */ Object h() {
            return B();
        }

        public b(d dVar, long j) {
            super(dVar.getActivity());
            this.f = dVar;
            this.g = j;
        }

        public a B() {
            a b = this.f.b();
            b.a = new LinkedList();
            Cursor query = m().getContentResolver().query(Data.CONTENT_URI, d.i, "contact_id=?", new String[]{Long.toString(this.g)}, "is_super_primary DESC, _id ASC");
            if (query == null) {
                return b;
            }
            try {
                int columnIndexOrThrow = query.getColumnIndexOrThrow("mimetype");
                int columnIndexOrThrow2 = query.getColumnIndexOrThrow("data1");
                int columnIndexOrThrow3 = query.getColumnIndexOrThrow("data2");
                int columnIndexOrThrow4 = query.getColumnIndexOrThrow("data3");
                int columnIndexOrThrow5 = query.getColumnIndexOrThrow("is_super_primary");
                Resources resources = m().getResources();
                while (query.moveToNext()) {
                    if ("vnd.android.cursor.item/phone_v2".equals(query.getString(columnIndexOrThrow)) && query.getString(columnIndexOrThrow2) != null) {
                        boolean z;
                        c c = this.f.c();
                        c.a = query.getString(columnIndexOrThrow2);
                        c.b = t.f.b(c.a);
                        c.c = Phone.getTypeLabel(resources, query.getInt(columnIndexOrThrow3), query.getString(columnIndexOrThrow4)).toString();
                        c.d = true;
                        c.e = com.mavenir.android.settings.c.k.u();
                        b.a.add(c);
                        if (query.getInt(columnIndexOrThrow5) == 1) {
                            z = true;
                        } else {
                            z = false;
                        }
                        c.g = z;
                    }
                }
                this.f.a(b, query);
                return b;
            } finally {
                if (query != null) {
                    query.close();
                }
            }
        }
    }

    public static class c {
        public String a = null;
        public String b = null;
        public String c = null;
        public boolean d = false;
        public boolean e = false;
        public int f = 0;
        public boolean g = false;
    }

    private static class d extends com.mavenir.android.common.d<Bitmap> {
        private Uri f = null;

        @SuppressLint({"NewApi"})
        public /* synthetic */ Object h() {
            return B();
        }

        public d(Context context, Uri uri) {
            super(context);
            this.f = uri;
        }

        @SuppressLint({"NewApi"})
        public Bitmap B() {
            Throwable e;
            Throwable th;
            Bitmap bitmap = null;
            InputStream openContactPhotoInputStream;
            try {
                if (VERSION.SDK_INT >= 14) {
                    openContactPhotoInputStream = Contacts.openContactPhotoInputStream(m().getContentResolver(), this.f, true);
                } else {
                    openContactPhotoInputStream = Contacts.openContactPhotoInputStream(m().getContentResolver(), this.f);
                }
                if (openContactPhotoInputStream != null) {
                    try {
                        bitmap = BitmapFactory.decodeStream(openContactPhotoInputStream);
                    } catch (Exception e2) {
                        e = e2;
                        try {
                            q.c("ContactDetailsFragment", "doLoadInBackground(): ", e);
                            if (openContactPhotoInputStream != null) {
                                try {
                                    openContactPhotoInputStream.close();
                                } catch (Throwable e3) {
                                    q.c("ContactDetailsFragment", "doLoadInBackground(): ", e3);
                                }
                            }
                            return bitmap;
                        } catch (Throwable th2) {
                            th = th2;
                            if (openContactPhotoInputStream != null) {
                                try {
                                    openContactPhotoInputStream.close();
                                } catch (Throwable e32) {
                                    q.c("ContactDetailsFragment", "doLoadInBackground(): ", e32);
                                }
                            }
                            throw th;
                        }
                    }
                }
                if (openContactPhotoInputStream != null) {
                    try {
                        openContactPhotoInputStream.close();
                    } catch (Throwable e322) {
                        q.c("ContactDetailsFragment", "doLoadInBackground(): ", e322);
                    }
                }
            } catch (Exception e4) {
                e322 = e4;
                openContactPhotoInputStream = bitmap;
                q.c("ContactDetailsFragment", "doLoadInBackground(): ", e322);
                if (openContactPhotoInputStream != null) {
                    openContactPhotoInputStream.close();
                }
                return bitmap;
            } catch (Throwable e3222) {
                openContactPhotoInputStream = bitmap;
                th = e3222;
                if (openContactPhotoInputStream != null) {
                    openContactPhotoInputStream.close();
                }
                throw th;
            }
            return bitmap;
        }
    }

    protected static class e extends BaseExpandableListAdapter {
        protected d a = null;
        protected List<c> b = null;
        protected LayoutInflater c = null;

        public e(d dVar, List<c> list) {
            this.a = dVar;
            this.b = list;
            this.c = (LayoutInflater) dVar.getActivity().getSystemService("layout_inflater");
        }

        public int getGroupCount() {
            return this.b.size();
        }

        public int getChildrenCount(int i) {
            return ((c) this.b.get(i)).f;
        }

        public Object getGroup(int i) {
            return this.b.get(i);
        }

        public Object getChild(int i, int i2) {
            return this.b.get(i);
        }

        public long getGroupId(int i) {
            return (long) i;
        }

        public long getChildId(int i, int i2) {
            return (long) i;
        }

        public boolean hasStableIds() {
            return false;
        }

        protected View a(int i, boolean z, View view, ViewGroup viewGroup) {
            if (view == null) {
                return this.c.inflate(h.contact_details_phone_row, viewGroup, false);
            }
            return view;
        }

        public View getGroupView(int i, boolean z, View view, ViewGroup viewGroup) {
            View a = a(i, z, view, viewGroup);
            final c cVar = (c) this.b.get(i);
            ((TextView) a.findViewById(g.text1)).setText(cVar.c);
            ((TextView) a.findViewById(g.text2)).setText(cVar.a);
            ImageView imageView = (ImageView) a.findViewById(g.defaultIcon);
            if (imageView != null) {
                imageView.setVisibility(cVar.g ? 0 : 8);
            }
            int[] d = this.a.d();
            for (final int i2 : d) {
                View findViewById = a.findViewById(i2);
                if (findViewById != null) {
                    findViewById.setVisibility(8);
                    if (this.a.a(cVar, i2)) {
                        findViewById.setVisibility(0);
                        if (i2 == g.callButton) {
                            findViewById.setContentDescription(this.a.getString(com.fgmicrotec.mobile.android.fgvoip.f.k.cd_contact_details_call_button, cVar.c, com.mavenir.android.common.t.a.b(cVar.a)));
                        } else if (i2 == g.messageButton) {
                            findViewById.setContentDescription(this.a.getString(com.fgmicrotec.mobile.android.fgvoip.f.k.cd_contact_details_sms_button, cVar.c, com.mavenir.android.common.t.a.b(cVar.a)));
                        }
                        findViewById.setOnClickListener(new OnClickListener(this) {
                            final /* synthetic */ e c;

                            public void onClick(View view) {
                                this.c.a.b(cVar, i2);
                            }
                        });
                    }
                }
            }
            imageView = (ImageView) a.findViewById(g.groupIndicatorButton);
            if (imageView != null) {
                imageView.setVisibility(8);
                if (cVar.f > 0) {
                    imageView.setVisibility(0);
                    imageView.setImageResource(z ? f.ic_down : f.ic_right);
                }
            }
            a.setContentDescription(this.a.getString(com.fgmicrotec.mobile.android.fgvoip.f.k.cd_contact_details_item, com.mavenir.android.common.t.a.b(cVar.a), cVar.c));
            return a;
        }

        public View getChildView(int i, int i2, boolean z, View view, ViewGroup viewGroup) {
            return null;
        }

        public boolean isChildSelectable(int i, int i2) {
            return false;
        }
    }

    public void a(String str) {
        long a = com.mavenir.android.common.k.a(str);
        if (a > -1) {
            a(ContentUris.withAppendedId(Contacts.CONTENT_URI, a));
            return;
        }
        a b = b();
        b.a = new LinkedList();
        c c = c();
        c.a = str;
        c.b = t.f.a(str);
        c.c = Phone.getTypeLabel(getResources(), 2, "Other").toString();
        c.d = true;
        c.e = true;
        b.a.add(c);
        a(b, null);
        a(b);
    }

    public void a(Uri uri) {
        if (uri == null) {
            Toast.makeText(getActivity(), "Unknown contact", 0).show();
            getActivity().finish();
            return;
        }
        this.a = Contacts.getLookupUri(getActivity().getContentResolver(), uri);
        Bundle bundle = new Bundle();
        bundle.putParcelable("uri", uri);
        getLoaderManager().restartLoader(2, bundle, this.h);
        this.b = false;
        getLoaderManager().restartLoader(0, bundle, this.g);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(a(), viewGroup, false);
        this.c = (ImageView) inflate.findViewById(g.contactImageView);
        setHasOptionsMenu(true);
        if (bundle != null) {
            this.a = (Uri) bundle.getParcelable("mContactLookupUri");
        }
        if (this.a != null) {
            this.b = true;
            getLoaderManager().restartLoader(0, null, this.g);
        }
        return inflate;
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putParcelable("mContactLookupUri", this.a);
    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
        menuInflater.inflate(i.contact_details_activity, menu);
        super.onCreateOptionsMenu(menu, menuInflater);
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() != g.menu_view_contact) {
            return super.onOptionsItemSelected(menuItem);
        }
        if (this.a != null) {
            Intent intent = new Intent("android.intent.action.EDIT");
            intent.setData(this.a);
            getActivity().startActivity(intent);
        }
        return true;
    }

    protected int a() {
        return h.contact_details_fragment;
    }

    protected a b() {
        new a().a = new LinkedList();
        return new a();
    }

    protected c c() {
        return new c();
    }

    protected void a(a aVar, Cursor cursor) {
    }

    protected int[] d() {
        return this.e;
    }

    protected boolean a(c cVar, int i) {
        if (i == g.callButton) {
            return cVar.d;
        }
        if (i == g.messageButton) {
            return cVar.e;
        }
        return false;
    }

    protected void b(c cVar, int i) {
        if (i == g.callButton) {
            FgVoIP.U().j(cVar.a);
        } else if (i == g.messageButton) {
            com.mavenir.android.activity.a.a(getActivity(), cVar.a, null, false);
        }
    }

    protected void a(a aVar) {
        Object obj;
        if (aVar.a == null || aVar.a.isEmpty()) {
            obj = null;
        } else {
            obj = new e(this, aVar.a);
        }
        ExpandableListView expandableListView = (ExpandableListView) getView().findViewById(g.phone_list_exp);
        expandableListView.setAdapter(obj);
        expandableListView.setOnGroupClickListener(new OnGroupClickListener(this) {
            final /* synthetic */ d b;

            public boolean onGroupClick(ExpandableListView expandableListView, View view, int i, long j) {
                c cVar = (c) obj.getGroup(i);
                if (cVar.f != 0) {
                    return false;
                }
                FgVoIP.U().j(cVar.b);
                return true;
            }
        });
        a(expandableListView, aVar);
    }

    protected void a(final ExpandableListView expandableListView, a aVar) {
        expandableListView.setOnGroupExpandListener(new OnGroupExpandListener(this) {
            final /* synthetic */ d b;

            public void onGroupExpand(int i) {
                this.b.d.add(((c) expandableListView.getAdapter().getItem(i)).b);
            }
        });
        expandableListView.setOnGroupCollapseListener(new OnGroupCollapseListener(this) {
            final /* synthetic */ d b;

            public void onGroupCollapse(int i) {
                this.b.d.remove(((c) expandableListView.getAdapter().getItem(i)).b);
            }
        });
        if (aVar.a != null && expandableListView.getAdapter() != null) {
            if (aVar.a.size() == 1) {
                expandableListView.expandGroup(0);
            } else if (!this.d.isEmpty()) {
                int i = 0;
                for (c cVar : aVar.a) {
                    if (this.d.contains(cVar.b)) {
                        expandableListView.expandGroup(i);
                        return;
                    }
                    i++;
                }
            }
        }
    }

    protected ActionBar e() {
        return ((ActionBarActivity) getActivity()).getSupportActionBar();
    }
}
