package com.mavenir.android.view;

import android.content.Context;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnKeyListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.fgmicrotec.mobile.android.fgvoip.f.g;
import com.fgmicrotec.mobile.android.fgvoip.f.h;
import com.fgmicrotec.mobile.android.fgvoip.f.k;

public class DockedSearchView extends LinearLayout {
    OnKeyListener a = new OnKeyListener(this) {
        final /* synthetic */ DockedSearchView a;

        {
            this.a = r1;
        }

        public boolean onKey(View view, int i, KeyEvent keyEvent) {
            if (i != 4) {
                return false;
            }
            if (this.a.f != null) {
                this.a.f.b();
            }
            return true;
        }
    };
    private Context b;
    private EditText c;
    private ImageView d;
    private a e;
    private b f;
    private String g;

    public interface a {
        boolean a(String str);
    }

    public interface b {
        boolean a();

        boolean b();
    }

    public DockedSearchView(Context context) {
        super(context);
        this.b = context;
        this.g = "";
        View inflate = ((LayoutInflater) this.b.getSystemService("layout_inflater")).inflate(h.search_layout, this, true);
        this.c = (EditText) inflate.findViewById(g.search_text);
        this.d = (ImageView) inflate.findViewById(g.clearNumberImageView);
        this.d.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ DockedSearchView a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.c.setText("");
                this.a.g = "";
                this.a.d.setVisibility(4);
                com.mavenir.android.common.t.a.a(this.a.b.getString(k.cd_searchview_cleared));
            }
        });
        this.d.setVisibility(4);
        this.c.addTextChangedListener(new TextWatcher(this) {
            final /* synthetic */ DockedSearchView a;

            {
                this.a = r1;
            }

            public void afterTextChanged(Editable editable) {
            }

            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                String charSequence2 = charSequence.toString();
                if (this.a.e != null && !TextUtils.equals(charSequence2, this.a.g)) {
                    if (this.a.g.length() == 0) {
                        this.a.d.setVisibility(0);
                    } else if (charSequence2.length() == 0) {
                        this.a.d.setVisibility(4);
                    }
                    this.a.e.a(charSequence2);
                    this.a.g = charSequence2;
                }
            }
        });
        this.c.setOnKeyListener(this.a);
        this.c.setFocusable(true);
        this.c.requestFocus();
        ((InputMethodManager) this.b.getSystemService("input_method")).toggleSoftInput(1, 0);
    }

    protected void onDetachedFromWindow() {
        ((InputMethodManager) this.b.getSystemService("input_method")).hideSoftInputFromInputMethod(getApplicationWindowToken(), 0);
        if (this.f != null) {
            this.c.getText().clear();
            this.f.a();
        }
        super.onDetachedFromWindow();
    }

    public void setOnSearchTextListener(a aVar) {
        this.e = aVar;
    }

    public void setOnSearchViewDetachedListener(b bVar) {
        this.f = bVar;
    }

    public void setSearchHint(String str) {
        this.c.setHint(str);
    }
}
