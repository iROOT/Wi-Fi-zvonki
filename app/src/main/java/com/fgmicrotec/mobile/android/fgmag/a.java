package com.fgmicrotec.mobile.android.fgmag;

import com.fgmicrotec.mobile.android.fgvoip.FgVoIP;
import com.mavenir.android.common.q;
import java.net.InetAddress;
import java.util.ArrayList;

public class a {
    private ArrayList<a> a = new ArrayList();

    private class a {
        final /* synthetic */ a a;
        private int b;
        private int c;
        private int d;
        private int e;
        private int f;
        private InetAddress g;
        private int h;

        a(a aVar, int i, int i2, int i3, int i4, int i5, InetAddress inetAddress) {
            this.a = aVar;
            this.b = i;
            this.c = i2;
            this.d = i3;
            this.e = i4;
            this.f = i5;
            this.g = inetAddress;
            this.h = aVar.a(inetAddress);
        }
    }

    public a() {
        this.a.clear();
    }

    public boolean a(int i) {
        for (int i2 = 0; i2 < this.a.size(); i2++) {
            if (((a) this.a.get(i2)).c == i) {
                return true;
            }
        }
        return false;
    }

    public void a() {
        this.a.clear();
    }

    public boolean a(int i, int i2, int i3, int i4) {
        if (a(i2)) {
            return false;
        }
        InetAddress b = b();
        if (b == null) {
            return false;
        }
        this.a.add(new a(this, i, i2, i3, i4, c(), b));
        return true;
    }

    public boolean b(int i) {
        for (int i2 = 0; i2 < this.a.size(); i2++) {
            if (((a) this.a.get(i2)).f == i) {
                this.a.remove(i2);
                return true;
            }
        }
        return false;
    }

    public int c(int i) {
        for (int i2 = 0; i2 < this.a.size(); i2++) {
            if (((a) this.a.get(i2)).c == i) {
                return ((a) this.a.get(i2)).f;
            }
        }
        return -1;
    }

    public int a(int i, int i2) {
        int i3 = 0;
        while (i3 < this.a.size()) {
            if (((a) this.a.get(i3)).c == i && ((a) this.a.get(i3)).d == i2) {
                return ((a) this.a.get(i3)).h;
            }
            i3++;
        }
        return -1;
    }

    public InetAddress b(int i, int i2) {
        int i3 = 0;
        while (i3 < this.a.size()) {
            if (((a) this.a.get(i3)).c == i && ((a) this.a.get(i3)).d == i2) {
                return ((a) this.a.get(i3)).g;
            }
            i3++;
        }
        return null;
    }

    private int c() {
        int size = this.a.size();
        if (size <= 0) {
            return 1;
        }
        int b = ((a) this.a.get(size - 1)).f;
        int i = 0;
        while (i < size) {
            int b2;
            if (b < ((a) this.a.get(i)).f) {
                b2 = ((a) this.a.get(i)).f;
            } else {
                b2 = b;
            }
            i++;
            b = b2;
        }
        return b + 1;
    }

    public InetAddress b() {
        InetAddress as = FgVoIP.U().as();
        if (as != null) {
            return as;
        }
        q.d("ConnectionHandler", "getLocalIpAddress: inetAddress = null");
        return null;
    }

    private int a(InetAddress inetAddress) {
        long j = 0;
        int i = 0;
        while (inetAddress != null && i < inetAddress.getAddress().length) {
            long j2 = j << 8;
            if (inetAddress.getAddress()[i] < (byte) 0) {
                j = (long) (inetAddress.getAddress()[i] + 256);
            } else {
                j = (long) inetAddress.getAddress()[i];
            }
            i++;
            j = j2 | j;
        }
        return (int) j;
    }
}
