package com.mavenir.android.messaging.provider;

public class a {
    public long a = -1;
    public long b = -1;

    public a(long j, long j2) {
        this.a = j;
        this.b = j2;
    }

    public static a a(String str) {
        try {
            return a(Long.parseLong(str));
        } catch (Exception e) {
            return new a(-1, -1);
        }
    }

    public static a a(long j) {
        a aVar = new a();
        aVar.a = j & 4294967295L;
        if (aVar.a == 4294967295L) {
            aVar.a = -1;
        }
        aVar.b = (j >>> 32) - 1;
        return aVar;
    }

    public long a() {
        return (this.a & 4294967295L) | (((this.b + 1) & 4294967295L) << 32);
    }

    public String toString() {
        return "{ custId=" + this.a + ", natId=" + this.b + " }";
    }
}
