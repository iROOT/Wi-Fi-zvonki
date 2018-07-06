package com.b.a.a.a.a.a;

import java.io.BufferedWriter;
import java.io.Closeable;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map.Entry;
import java.util.concurrent.Callable;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

final class a implements Closeable {
    static final Pattern a = Pattern.compile("[a-z0-9_-]{1,64}");
    private static final OutputStream r = new OutputStream() {
        public void write(int i) {
        }
    };
    final ThreadPoolExecutor b = new ThreadPoolExecutor(0, 1, 60, TimeUnit.SECONDS, new LinkedBlockingQueue());
    private final File c;
    private final File d;
    private final File e;
    private final File f;
    private final int g;
    private long h;
    private int i;
    private final int j;
    private long k = 0;
    private int l = 0;
    private Writer m;
    private final LinkedHashMap<String, b> n = new LinkedHashMap(0, 0.75f, true);
    private int o;
    private long p = 0;
    private final Callable<Void> q = new Callable<Void>(this) {
        final /* synthetic */ a a;

        {
            this.a = r1;
        }

        public /* synthetic */ Object call() {
            return a();
        }

        public Void a() {
            synchronized (this.a) {
                if (this.a.m == null) {
                } else {
                    this.a.h();
                    this.a.i();
                    if (this.a.f()) {
                        this.a.e();
                        this.a.o = 0;
                    }
                }
            }
            return null;
        }
    };

    public final class a {
        final /* synthetic */ a a;
        private final b b;
        private final boolean[] c;
        private boolean d;
        private boolean e;

        private class a extends FilterOutputStream {
            final /* synthetic */ a a;

            /* synthetic */ a(a aVar, OutputStream outputStream, AnonymousClass1 anonymousClass1) {
                this(aVar, outputStream);
            }

            private a(a aVar, OutputStream outputStream) {
                this.a = aVar;
                super(outputStream);
            }

            public void write(int i) {
                try {
                    this.out.write(i);
                } catch (IOException e) {
                    this.a.d = true;
                }
            }

            public void write(byte[] bArr, int i, int i2) {
                try {
                    this.out.write(bArr, i, i2);
                } catch (IOException e) {
                    this.a.d = true;
                }
            }

            public void close() {
                try {
                    this.out.close();
                } catch (IOException e) {
                    this.a.d = true;
                }
            }

            public void flush() {
                try {
                    this.out.flush();
                } catch (IOException e) {
                    this.a.d = true;
                }
            }
        }

        /* synthetic */ a(a aVar, b bVar, AnonymousClass1 anonymousClass1) {
            this(aVar, bVar);
        }

        private a(a aVar, b bVar) {
            this.a = aVar;
            this.b = bVar;
            this.c = bVar.d ? null : new boolean[aVar.j];
        }

        public OutputStream a(int i) {
            OutputStream b;
            synchronized (this.a) {
                File b2;
                OutputStream fileOutputStream;
                if (this.b.e != this) {
                    throw new IllegalStateException();
                }
                if (!this.b.d) {
                    this.c[i] = true;
                }
                b2 = this.b.b(i);
                try {
                    fileOutputStream = new FileOutputStream(b2);
                } catch (FileNotFoundException e) {
                    this.a.c.mkdirs();
                    try {
                        fileOutputStream = new FileOutputStream(b2);
                    } catch (FileNotFoundException e2) {
                        b = a.r;
                    }
                }
                b = new a(this, fileOutputStream, null);
            }
            return b;
        }

        public void a() {
            if (this.d) {
                this.a.a(this, false);
                this.a.c(this.b.b);
            } else {
                this.a.a(this, true);
            }
            this.e = true;
        }

        public void b() {
            this.a.a(this, false);
        }
    }

    private final class b {
        final /* synthetic */ a a;
        private final String b;
        private final long[] c;
        private boolean d;
        private a e;
        private long f;

        /* synthetic */ b(a aVar, String str, AnonymousClass1 anonymousClass1) {
            this(aVar, str);
        }

        private b(a aVar, String str) {
            this.a = aVar;
            this.b = str;
            this.c = new long[aVar.j];
        }

        public String a() {
            StringBuilder stringBuilder = new StringBuilder();
            for (long append : this.c) {
                stringBuilder.append(' ').append(append);
            }
            return stringBuilder.toString();
        }

        private void a(String[] strArr) {
            if (strArr.length != this.a.j) {
                throw b(strArr);
            }
            int i = 0;
            while (i < strArr.length) {
                try {
                    this.c[i] = Long.parseLong(strArr[i]);
                    i++;
                } catch (NumberFormatException e) {
                    throw b(strArr);
                }
            }
        }

        private IOException b(String[] strArr) {
            throw new IOException("unexpected journal line: " + Arrays.toString(strArr));
        }

        public File a(int i) {
            return new File(this.a.c, this.b + "" + i);
        }

        public File b(int i) {
            return new File(this.a.c, this.b + "" + i + ".tmp");
        }
    }

    public final class c implements Closeable {
        final /* synthetic */ a a;
        private final String b;
        private final long c;
        private File[] d;
        private final InputStream[] e;
        private final long[] f;

        /* synthetic */ c(a aVar, String str, long j, File[] fileArr, InputStream[] inputStreamArr, long[] jArr, AnonymousClass1 anonymousClass1) {
            this(aVar, str, j, fileArr, inputStreamArr, jArr);
        }

        private c(a aVar, String str, long j, File[] fileArr, InputStream[] inputStreamArr, long[] jArr) {
            this.a = aVar;
            this.b = str;
            this.c = j;
            this.d = fileArr;
            this.e = inputStreamArr;
            this.f = jArr;
        }

        public File a(int i) {
            return this.d[i];
        }

        public void close() {
            for (Closeable a : this.e) {
                d.a(a);
            }
        }
    }

    private a(File file, int i, int i2, long j, int i3) {
        this.c = file;
        this.g = i;
        this.d = new File(file, "journal");
        this.e = new File(file, "journal.tmp");
        this.f = new File(file, "journal.bkp");
        this.j = i2;
        this.h = j;
        this.i = i3;
    }

    public static a a(File file, int i, int i2, long j, int i3) {
        if (j <= 0) {
            throw new IllegalArgumentException("maxSize <= 0");
        } else if (i3 <= 0) {
            throw new IllegalArgumentException("maxFileCount <= 0");
        } else if (i2 <= 0) {
            throw new IllegalArgumentException("valueCount <= 0");
        } else {
            File file2 = new File(file, "journal.bkp");
            if (file2.exists()) {
                File file3 = new File(file, "journal");
                if (file3.exists()) {
                    file2.delete();
                } else {
                    a(file2, file3, false);
                }
            }
            a aVar = new a(file, i, i2, j, i3);
            if (aVar.d.exists()) {
                try {
                    aVar.c();
                    aVar.d();
                    aVar.m = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(aVar.d, true), d.a));
                    return aVar;
                } catch (IOException e) {
                    System.out.println("DiskLruCache " + file + " is corrupt: " + e.getMessage() + ", removing");
                    aVar.a();
                }
            }
            file.mkdirs();
            aVar = new a(file, i, i2, j, i3);
            aVar.e();
            return aVar;
        }
    }

    private void c() {
        Closeable cVar = new c(new FileInputStream(this.d), d.a);
        int i;
        try {
            String a = cVar.a();
            String a2 = cVar.a();
            String a3 = cVar.a();
            String a4 = cVar.a();
            String a5 = cVar.a();
            if ("libcore.io.DiskLruCache".equals(a) && "1".equals(a2) && Integer.toString(this.g).equals(a3) && Integer.toString(this.j).equals(a4) && "".equals(a5)) {
                i = 0;
                while (true) {
                    d(cVar.a());
                    i++;
                }
            } else {
                throw new IOException("unexpected journal header: [" + a + ", " + a2 + ", " + a4 + ", " + a5 + "]");
            }
        } catch (EOFException e) {
            this.o = i - this.n.size();
            d.a(cVar);
        } catch (Throwable th) {
            d.a(cVar);
        }
    }

    private void d(String str) {
        int indexOf = str.indexOf(32);
        if (indexOf == -1) {
            throw new IOException("unexpected journal line: " + str);
        }
        String str2;
        int i = indexOf + 1;
        int indexOf2 = str.indexOf(32, i);
        if (indexOf2 == -1) {
            String substring = str.substring(i);
            if (indexOf == "REMOVE".length() && str.startsWith("REMOVE")) {
                this.n.remove(substring);
                return;
            }
            str2 = substring;
        } else {
            str2 = str.substring(i, indexOf2);
        }
        b bVar = (b) this.n.get(str2);
        if (bVar == null) {
            bVar = new b(this, str2, null);
            this.n.put(str2, bVar);
        }
        if (indexOf2 != -1 && indexOf == "CLEAN".length() && str.startsWith("CLEAN")) {
            String[] split = str.substring(indexOf2 + 1).split(" ");
            bVar.d = true;
            bVar.e = null;
            bVar.a(split);
        } else if (indexOf2 == -1 && indexOf == "DIRTY".length() && str.startsWith("DIRTY")) {
            bVar.e = new a(this, bVar, null);
        } else if (indexOf2 != -1 || indexOf != "READ".length() || !str.startsWith("READ")) {
            throw new IOException("unexpected journal line: " + str);
        }
    }

    private void d() {
        a(this.e);
        Iterator it = this.n.values().iterator();
        while (it.hasNext()) {
            b bVar = (b) it.next();
            int i;
            if (bVar.e == null) {
                for (i = 0; i < this.j; i++) {
                    this.k += bVar.c[i];
                    this.l++;
                }
            } else {
                bVar.e = null;
                for (i = 0; i < this.j; i++) {
                    a(bVar.a(i));
                    a(bVar.b(i));
                }
                it.remove();
            }
        }
    }

    private synchronized void e() {
        if (this.m != null) {
            this.m.close();
        }
        Writer bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(this.e), d.a));
        try {
            bufferedWriter.write("libcore.io.DiskLruCache");
            bufferedWriter.write("\n");
            bufferedWriter.write("1");
            bufferedWriter.write("\n");
            bufferedWriter.write(Integer.toString(this.g));
            bufferedWriter.write("\n");
            bufferedWriter.write(Integer.toString(this.j));
            bufferedWriter.write("\n");
            bufferedWriter.write("\n");
            for (b bVar : this.n.values()) {
                if (bVar.e != null) {
                    bufferedWriter.write("DIRTY " + bVar.b + '\n');
                } else {
                    bufferedWriter.write("CLEAN " + bVar.b + bVar.a() + '\n');
                }
            }
            bufferedWriter.close();
            if (this.d.exists()) {
                a(this.d, this.f, true);
            }
            a(this.e, this.d, false);
            this.f.delete();
            this.m = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(this.d, true), d.a));
        } catch (Throwable th) {
            bufferedWriter.close();
        }
    }

    private static void a(File file) {
        if (file.exists() && !file.delete()) {
            throw new IOException();
        }
    }

    private static void a(File file, File file2, boolean z) {
        if (z) {
            a(file2);
        }
        if (!file.renameTo(file2)) {
            throw new IOException();
        }
    }

    public synchronized c a(String str) {
        int i;
        c cVar = null;
        synchronized (this) {
            g();
            e(str);
            b bVar = (b) this.n.get(str);
            if (bVar != null) {
                if (bVar.d) {
                    File[] fileArr = new File[this.j];
                    InputStream[] inputStreamArr = new InputStream[this.j];
                    int i2 = 0;
                    while (i2 < this.j) {
                        try {
                            File a = bVar.a(i2);
                            fileArr[i2] = a;
                            inputStreamArr[i2] = new FileInputStream(a);
                            i2++;
                        } catch (FileNotFoundException e) {
                            i = 0;
                            while (i < this.j && inputStreamArr[i] != null) {
                                d.a(inputStreamArr[i]);
                                i++;
                            }
                        }
                    }
                    this.o++;
                    this.m.append("READ " + str + '\n');
                    if (f()) {
                        this.b.submit(this.q);
                    }
                    cVar = new c(this, str, bVar.f, fileArr, inputStreamArr, bVar.c, null);
                }
            }
        }
        return cVar;
    }

    public a b(String str) {
        return a(str, -1);
    }

    private synchronized a a(String str, long j) {
        a aVar;
        g();
        e(str);
        b bVar = (b) this.n.get(str);
        if (j == -1 || (bVar != null && bVar.f == j)) {
            b bVar2;
            if (bVar == null) {
                bVar = new b(this, str, null);
                this.n.put(str, bVar);
                bVar2 = bVar;
            } else if (bVar.e != null) {
                aVar = null;
            } else {
                bVar2 = bVar;
            }
            aVar = new a(this, bVar2, null);
            bVar2.e = aVar;
            this.m.write("DIRTY " + str + '\n');
            this.m.flush();
        } else {
            aVar = null;
        }
        return aVar;
    }

    private synchronized void a(a aVar, boolean z) {
        int i = 0;
        synchronized (this) {
            b a = aVar.b;
            if (a.e != aVar) {
                throw new IllegalStateException();
            }
            if (z) {
                if (!a.d) {
                    int i2 = 0;
                    while (i2 < this.j) {
                        if (!aVar.c[i2]) {
                            aVar.b();
                            throw new IllegalStateException("Newly created entry didn't create value for index " + i2);
                        } else if (!a.b(i2).exists()) {
                            aVar.b();
                            break;
                        } else {
                            i2++;
                        }
                    }
                }
            }
            while (i < this.j) {
                File b = a.b(i);
                if (!z) {
                    a(b);
                } else if (b.exists()) {
                    File a2 = a.a(i);
                    b.renameTo(a2);
                    long j = a.c[i];
                    long length = a2.length();
                    a.c[i] = length;
                    this.k = (this.k - j) + length;
                    this.l++;
                }
                i++;
            }
            this.o++;
            a.e = null;
            if ((a.d | z) != 0) {
                a.d = true;
                this.m.write("CLEAN " + a.b + a.a() + '\n');
                if (z) {
                    long j2 = this.p;
                    this.p = 1 + j2;
                    a.f = j2;
                }
            } else {
                this.n.remove(a.b);
                this.m.write("REMOVE " + a.b + '\n');
            }
            this.m.flush();
            if (this.k > this.h || this.l > this.i || f()) {
                this.b.submit(this.q);
            }
        }
    }

    private boolean f() {
        return this.o >= 2000 && this.o >= this.n.size();
    }

    public synchronized boolean c(String str) {
        boolean z;
        int i = 0;
        synchronized (this) {
            g();
            e(str);
            b bVar = (b) this.n.get(str);
            if (bVar == null || bVar.e != null) {
                z = false;
            } else {
                while (i < this.j) {
                    File a = bVar.a(i);
                    if (!a.exists() || a.delete()) {
                        this.k -= bVar.c[i];
                        this.l--;
                        bVar.c[i] = 0;
                        i++;
                    } else {
                        throw new IOException("failed to delete " + a);
                    }
                }
                this.o++;
                this.m.append("REMOVE " + str + '\n');
                this.n.remove(str);
                if (f()) {
                    this.b.submit(this.q);
                }
                z = true;
            }
        }
        return z;
    }

    private void g() {
        if (this.m == null) {
            throw new IllegalStateException("cache is closed");
        }
    }

    public synchronized void close() {
        if (this.m != null) {
            Iterator it = new ArrayList(this.n.values()).iterator();
            while (it.hasNext()) {
                b bVar = (b) it.next();
                if (bVar.e != null) {
                    bVar.e.b();
                }
            }
            h();
            i();
            this.m.close();
            this.m = null;
        }
    }

    private void h() {
        while (this.k > this.h) {
            c((String) ((Entry) this.n.entrySet().iterator().next()).getKey());
        }
    }

    private void i() {
        while (this.l > this.i) {
            c((String) ((Entry) this.n.entrySet().iterator().next()).getKey());
        }
    }

    public void a() {
        close();
        d.a(this.c);
    }

    private void e(String str) {
        if (!a.matcher(str).matches()) {
            throw new IllegalArgumentException("keys must match regex [a-z0-9_-]{1,64}: \"" + str + "\"");
        }
    }
}
