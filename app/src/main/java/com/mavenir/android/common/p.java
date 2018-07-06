package com.mavenir.android.common;

import android.database.Cursor;
import android.database.DataSetObserver;
import android.util.SparseIntArray;
import android.widget.SectionIndexer;

public final class p extends DataSetObserver implements SectionIndexer {
    private static String a = "JapaneseContactListIndexer";
    private static final String[] b = new String[]{" ", "あ", "か", "さ", "た", "な", "は", "ま", "や", "ら", "わ", "Ａ", "Ｂ", "Ｃ", "Ｄ", "Ｅ", "Ｆ", "Ｇ", "Ｈ", "Ｉ", "Ｊ", "Ｋ", "Ｌ", "Ｍ", "Ｎ", "Ｏ", "Ｐ", "Ｑ", "Ｒ", "Ｓ", "Ｔ", "Ｕ", "Ｖ", "Ｗ", "Ｘ", "Ｙ", "｀", "数", "記"};
    private static final int c = b.length;
    private int d;
    private Cursor e;
    private SparseIntArray f = new SparseIntArray(c);

    public p(Cursor cursor, int i) {
        this.d = i;
        this.e = cursor;
        if (cursor != null) {
            cursor.registerDataSetObserver(this);
        }
    }

    public void a(Cursor cursor) {
        if (this.e != null) {
            this.e.unregisterDataSetObserver(this);
        }
        this.e = cursor;
        if (cursor != null) {
            this.e.registerDataSetObserver(this);
        }
    }

    private int a(int i) {
        if (i < b.length - 2) {
            return b[i].codePointAt(0);
        }
        if (i == b.length - 2) {
            return 65382;
        }
        return 65392;
    }

    public int getPositionForSection(int i) {
        SparseIntArray sparseIntArray = this.f;
        Cursor cursor = this.e;
        if (cursor == null || i <= 0) {
            return 0;
        }
        if (i >= c) {
            i = c - 1;
        }
        int position = cursor.getPosition();
        int codePointAt = b[i].codePointAt(0);
        int i2 = sparseIntArray.get(codePointAt, Integer.MIN_VALUE);
        if (Integer.MIN_VALUE != i2) {
            return i2;
        }
        int count = cursor.getCount();
        i2 = sparseIntArray.get(b[i - 1].codePointAt(0), Integer.MIN_VALUE);
        if (i2 == Integer.MIN_VALUE) {
            i2 = 0;
        }
        while (count - i2 > 100) {
            int i3 = (count + i2) / 2;
            cursor.moveToPosition(i3);
            do {
                String string = cursor.getString(this.d);
                if (string != null && string.length() != 0) {
                    break;
                }
                q.d(a, "sort_name is null or its length is 0. index: " + i3);
                cursor.moveToNext();
                i3++;
            } while (i3 < count);
            if (i3 == count) {
                break;
            }
            if (string.codePointAt(0) < a(i)) {
                i2 = count;
            } else {
                int i4 = i2;
                i2 = i3;
                i3 = i4;
            }
            count = i2;
            i2 = i3;
        }
        cursor.moveToPosition(i2);
        while (!cursor.isAfterLast()) {
            String string2 = cursor.getString(this.d);
            if (string2 == null || string2.length() == 0) {
                q.d(a, "sort_name is null or its length is 0. index: " + i2);
            } else if (string2.codePointAt(0) >= a(i)) {
                break;
            }
            i2++;
            cursor.moveToNext();
        }
        sparseIntArray.put(codePointAt, i2);
        cursor.moveToPosition(position);
        return i2;
    }

    public int getSectionForPosition(int i) {
        return 0;
    }

    public Object[] getSections() {
        return b;
    }

    public void onChanged() {
        super.onChanged();
        this.f.clear();
    }

    public void onInvalidated() {
        super.onInvalidated();
        this.f.clear();
    }
}
