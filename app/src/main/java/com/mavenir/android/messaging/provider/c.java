package com.mavenir.android.messaging.provider;

import android.database.AbstractCursor;
import android.database.ContentObserver;
import android.database.Cursor;
import android.database.DataSetObserver;

public class c extends AbstractCursor {
    private Cursor a;
    private Cursor b;
    private Cursor c = this.a;
    private int[] d;
    private String e;
    private int f;
    private int g;

    public c(Cursor cursor, Cursor cursor2, String str) {
        this.a = cursor;
        this.b = cursor2;
        if (this.c == null) {
            this.c = this.b;
        }
        this.e = str;
        this.f = -1;
        if (this.e != null) {
            this.f = getColumnIndex(this.e);
        }
        this.g = getColumnIndex("_id");
        a();
    }

    public int getColumnIndex(String str) {
        if (this.c != null) {
            return this.c.getColumnIndex(str);
        }
        return -1;
    }

    private void a() {
        this.d = null;
        this.d = new int[getCount()];
        if (this.a != null && this.b != null) {
            b();
            Cursor cursor = (this.d.length == 0 || this.d[0] < 0) ? this.a : this.b;
            this.c = cursor;
        } else if (this.a != null) {
            a(this.a);
            this.c = this.a;
        } else if (this.b != null) {
            a(this.b);
            this.c = this.b;
        }
    }

    private void b() {
        this.a.moveToPosition(-1);
        this.b.moveToPosition(-1);
        boolean moveToNext = this.a.moveToNext();
        boolean moveToNext2 = this.b.moveToNext();
        int i = 0;
        while (true) {
            if (moveToNext || moveToNext2) {
                if (this.f > -1 && moveToNext && moveToNext2) {
                    long j = this.a.getLong(this.f);
                    long j2 = this.b.getLong(this.f);
                    boolean equals = this.a.getString(0).equals("mms");
                    boolean equals2 = this.b.getString(0).equals("mms");
                    if (equals) {
                        j *= 1000;
                    }
                    if (equals2) {
                        j2 *= 1000;
                    }
                    if (j <= j2) {
                        this.d[i] = (-this.a.getPosition()) - 1;
                        moveToNext = this.a.moveToNext();
                    } else {
                        this.d[i] = this.b.getPosition();
                        moveToNext2 = this.b.moveToNext();
                    }
                } else if (moveToNext) {
                    this.d[i] = (-this.a.getPosition()) - 1;
                    moveToNext = this.a.moveToNext();
                } else {
                    this.d[i] = this.b.getPosition();
                    moveToNext2 = this.b.moveToNext();
                }
                i++;
            } else {
                return;
            }
        }
    }

    private void a(Cursor cursor) {
        int i = 0;
        int i2 = cursor == this.a ? 1 : 0;
        cursor.moveToPosition(-1);
        while (cursor.moveToNext()) {
            int i3;
            int[] iArr = this.d;
            if (i2 != 0) {
                i3 = (-i) - 1;
            } else {
                i3 = i;
            }
            iArr[i] = i3;
            i++;
        }
    }

    public int getCount() {
        int i = 0;
        int count = this.a != null ? this.a.getCount() : 0;
        if (this.b != null) {
            i = this.b.getCount();
        }
        return count + i;
    }

    public boolean onMove(int i, int i2) {
        int i3 = this.d[i2];
        if (i3 < 0) {
            this.c = this.a;
            this.c.moveToPosition((-i3) - 1);
        } else {
            this.c = this.b;
            this.c.moveToPosition(i3);
        }
        return true;
    }

    public String getString(int i) {
        return this.c.getString(i);
    }

    public short getShort(int i) {
        return this.c.getShort(i);
    }

    public int getInt(int i) {
        return this.c.getInt(i);
    }

    public long getLong(int i) {
        long j = this.c.getLong(i);
        if (i == this.g && this.c == this.a) {
            return -j;
        }
        return j;
    }

    public float getFloat(int i) {
        return this.c.getFloat(i);
    }

    public double getDouble(int i) {
        return this.c.getDouble(i);
    }

    public int getType(int i) {
        return this.c.getType(i);
    }

    public boolean isNull(int i) {
        return this.c.isNull(i);
    }

    public byte[] getBlob(int i) {
        return this.c.getBlob(i);
    }

    public String[] getColumnNames() {
        if (this.c != null) {
            return this.c.getColumnNames();
        }
        return new String[0];
    }

    public void deactivate() {
        if (this.a != null) {
            this.a.deactivate();
        }
        if (this.b != null) {
            this.b.deactivate();
        }
        super.deactivate();
    }

    public void close() {
        if (this.a != null) {
            this.a.close();
        }
        if (this.b != null) {
            this.b.close();
        }
        super.close();
    }

    public void registerContentObserver(ContentObserver contentObserver) {
        if (this.a != null) {
            this.a.registerContentObserver(contentObserver);
        }
        if (this.b != null) {
            this.b.registerContentObserver(contentObserver);
        }
    }

    public void unregisterContentObserver(ContentObserver contentObserver) {
        if (this.a != null) {
            this.a.unregisterContentObserver(contentObserver);
        }
        if (this.b != null) {
            this.b.unregisterContentObserver(contentObserver);
        }
    }

    public void registerDataSetObserver(DataSetObserver dataSetObserver) {
        if (this.a != null) {
            this.a.registerDataSetObserver(dataSetObserver);
        }
        if (this.b != null) {
            this.b.registerDataSetObserver(dataSetObserver);
        }
    }

    public void unregisterDataSetObserver(DataSetObserver dataSetObserver) {
        if (this.a != null) {
            this.a.unregisterDataSetObserver(dataSetObserver);
        }
        if (this.b != null) {
            this.b.unregisterDataSetObserver(dataSetObserver);
        }
    }

    public boolean requery() {
        if (this.a != null && !this.a.requery()) {
            return false;
        }
        if (this.b != null && !this.b.requery()) {
            return false;
        }
        a();
        return true;
    }
}
