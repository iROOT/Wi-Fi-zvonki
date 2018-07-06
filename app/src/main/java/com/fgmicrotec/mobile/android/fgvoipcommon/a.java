package com.fgmicrotec.mobile.android.fgvoipcommon;

import android.os.Handler;
import com.mavenir.android.common.q;
import com.mavenir.android.vtow.activation.ActivationAdapter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.Vector;
import org.apache.http.HttpConnectionMetrics;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.conn.params.ConnManagerParams;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.AbstractClientConnAdapter;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;

public class a {
    private static int a = 2000;
    private b b;
    private DefaultHttpClient c;
    private File d;
    private Vector<b> e = new Vector();

    static class a extends Thread {
        private final DefaultHttpClient a;
        private final HttpContext b;
        private final HttpGet c;
        private final int d;
        private final b e;
        private final String f;
        private final Vector<b> g;
        private final File h;
        private final Handler i;
        private boolean j;
        private Runnable k = new Runnable(this) {
            final /* synthetic */ a a;

            {
                this.a = r1;
            }

            public void run() {
                if (this.a.b != null && this.a.j) {
                    AbstractClientConnAdapter abstractClientConnAdapter = (AbstractClientConnAdapter) this.a.b.getAttribute("http.connection");
                    if (abstractClientConnAdapter != null && this.a.j) {
                        try {
                            HttpConnectionMetrics metrics = abstractClientConnAdapter.getMetrics();
                            if (metrics != null && this.a.j) {
                                this.a.e.a(this.a.d, (int) metrics.getReceivedBytesCount());
                            }
                            if (this.a.i != null && this.a.j) {
                                this.a.i.postDelayed(this.a.k, (long) a.a);
                            }
                        } catch (IllegalStateException e) {
                            this.a.j = false;
                        }
                    }
                }
            }
        };

        public a(DefaultHttpClient defaultHttpClient, HttpGet httpGet, int i, b bVar, String str, Vector<b> vector, File file) {
            this.a = defaultHttpClient;
            this.b = new BasicHttpContext();
            this.c = httpGet;
            this.d = i;
            this.e = bVar;
            this.f = str;
            this.g = vector;
            this.h = file;
            this.i = new Handler();
            this.j = false;
        }

        public void run() {
            try {
                this.i.postDelayed(this.k, (long) a.a);
                this.j = true;
                HttpResponse execute = this.a.execute(this.c, this.b);
                int statusCode = execute.getStatusLine().getStatusCode();
                if (statusCode < ActivationAdapter.OP_CONFIGURATION_INITIAL || statusCode >= ActivationAdapter.REASON_NEW_CONFIG_DATA) {
                    this.e.a(-2, this.d, "");
                    this.j = false;
                    this.i.removeCallbacks(this.k);
                    for (statusCode = 0; statusCode < this.g.size(); statusCode++) {
                        if (this.d == ((b) this.g.get(statusCode)).b) {
                            this.g.remove(statusCode);
                            return;
                        }
                    }
                }
                String substring;
                File file;
                HttpEntity entity;
                File file2;
                OutputStream fileOutputStream;
                int i;
                String str = this.f;
                if (str.trim().length() == 0) {
                    str = this.c.getURI().getPath();
                    if (str.lastIndexOf("/") > 0) {
                        substring = str.substring(str.lastIndexOf("/") + 1);
                        file = this.h;
                        entity = execute.getEntity();
                        if (entity == null) {
                            file.mkdirs();
                            file2 = new File(file, substring);
                            if (file2.exists() && this.f.trim().length() == 0) {
                                file2 = new File(file, Long.toString(new Date().getTime()) + "_" + substring);
                            }
                            try {
                                fileOutputStream = new FileOutputStream(file2);
                                entity.writeTo(fileOutputStream);
                                fileOutputStream.close();
                                i = 0;
                            } catch (Exception e) {
                                i = -1;
                            }
                        } else {
                            file.mkdirs();
                            file2 = new File(file, substring);
                            if (file2.exists()) {
                                file2 = new File(file, new Date().toString() + substring);
                            }
                            try {
                                file2.createNewFile();
                                i = 0;
                            } catch (Exception e2) {
                                i = -1;
                            }
                        }
                        this.e.a(i, this.d, file2.getAbsolutePath());
                        this.j = false;
                        this.i.removeCallbacks(this.k);
                        while (statusCode < this.g.size()) {
                            if (this.d == ((b) this.g.get(statusCode)).b) {
                            } else {
                                this.g.remove(statusCode);
                                return;
                            }
                        }
                    }
                }
                substring = str;
                file = this.h;
                entity = execute.getEntity();
                if (entity == null) {
                    file.mkdirs();
                    file2 = new File(file, substring);
                    if (file2.exists()) {
                        file2 = new File(file, new Date().toString() + substring);
                    }
                    file2.createNewFile();
                    i = 0;
                } else {
                    file.mkdirs();
                    file2 = new File(file, substring);
                    file2 = new File(file, Long.toString(new Date().getTime()) + "_" + substring);
                    fileOutputStream = new FileOutputStream(file2);
                    entity.writeTo(fileOutputStream);
                    fileOutputStream.close();
                    i = 0;
                }
                this.e.a(i, this.d, file2.getAbsolutePath());
                this.j = false;
                this.i.removeCallbacks(this.k);
                for (statusCode = 0; statusCode < this.g.size(); statusCode++) {
                    if (this.d == ((b) this.g.get(statusCode)).b) {
                        this.g.remove(statusCode);
                        return;
                    }
                }
            } catch (Exception e3) {
                q.b("Parampion", "Http::GetObjectThread e: " + e3.toString());
                this.c.abort();
                this.e.a(-1, this.d, "");
            }
        }
    }

    private class b {
        final /* synthetic */ a a;
        private int b;
        private HttpRequestBase c;

        public b(a aVar, int i, HttpRequestBase httpRequestBase) {
            this.a = aVar;
            this.b = i;
            this.c = httpRequestBase;
        }
    }

    public a(b bVar) {
        this.b = bVar;
    }

    public void a() {
        HttpParams basicHttpParams = new BasicHttpParams();
        ConnManagerParams.setMaxTotalConnections(basicHttpParams, 100);
        HttpProtocolParams.setVersion(basicHttpParams, HttpVersion.HTTP_1_1);
        SchemeRegistry schemeRegistry = new SchemeRegistry();
        schemeRegistry.register(new Scheme("http", PlainSocketFactory.getSocketFactory(), 80));
        this.c = new DefaultHttpClient(new ThreadSafeClientConnManager(basicHttpParams, schemeRegistry), basicHttpParams);
    }

    public void a(File file) {
        this.d = file;
    }

    public void a(int i, String str, String str2) {
        String str3;
        HttpRequestBase httpGet = new HttpGet(str);
        this.e.add(new b(this, i, httpGet));
        DefaultHttpClient defaultHttpClient = this.c;
        b bVar = this.b;
        if (str2 == null) {
            str3 = "";
        } else {
            str3 = str2;
        }
        new a(defaultHttpClient, httpGet, i, bVar, str3, this.e, this.d).start();
    }
}
