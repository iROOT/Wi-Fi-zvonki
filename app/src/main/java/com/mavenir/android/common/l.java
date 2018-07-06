package com.mavenir.android.common;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.NetworkInfo.State;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.Looper;
import android.provider.Settings.System;
import android.telephony.CellInfoLte;
import android.telephony.CellLocation;
import android.telephony.SubscriptionInfo;
import android.telephony.SubscriptionManager;
import android.telephony.TelephonyManager;
import android.telephony.gsm.GsmCellLocation;
import android.text.TextUtils;
import com.fgmicrotec.mobile.android.fgmag.DataConnectionManager.a;
import com.fgmicrotec.mobile.android.fgmag.FgSDKLoader;
import com.fgmicrotec.mobile.android.fgvoip.FgVoIP;
import com.fgmicrotec.mobile.android.fgvoip.f.k;
import com.mavenir.android.settings.c.q;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.reflect.Method;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.Calendar;
import java.util.Enumeration;
import java.util.List;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class l {
    private static l a;
    private static Context b;
    private static boolean g = false;
    private static int h = 0;
    private static int i = 0;
    private static int j = 99;
    private static int k = 99;
    private static int l = 10000;
    private static boolean o = false;
    private ConnectivityManager c = null;
    private TelephonyManager d = null;
    private WifiManager e = null;
    private Handler f;
    private String m;
    private Object n = new Object();
    private Runnable p = new Runnable(this) {
        final /* synthetic */ l a;

        {
            this.a = r1;
        }

        public void run() {
            this.a.e.enableNetwork(this.a.e.getConnectionInfo().getNetworkId(), false);
        }
    };

    public static l a(Context context) {
        if (a == null) {
            a = new l(context.getApplicationContext());
        }
        return a;
    }

    private l(Context context) {
        b = context;
        this.c = (ConnectivityManager) b.getSystemService("connectivity");
        this.d = (TelephonyManager) b.getSystemService("phone");
        this.e = (WifiManager) b.getSystemService("wifi");
        this.f = new Handler(Looper.getMainLooper());
    }

    public String a() {
        return b.getString(k.app_name);
    }

    public String b() {
        return b.getString(k.app_vendor);
    }

    public String c() {
        String str = "";
        try {
            str = String.valueOf(b.getPackageManager().getPackageInfo(b.getPackageName(), 0).versionCode);
        } catch (Exception e) {
            q.c("DeviceInfo", e.getLocalizedMessage(), e.getCause());
        }
        return str;
    }

    public String a(boolean z) {
        String str;
        Exception e;
        String str2 = "";
        try {
            str = b.getPackageManager().getPackageInfo(b.getPackageName(), 0).versionName;
            if (z) {
                try {
                    str2 = (str + " SDK ") + FgVoIP.U().getString(k.app_version_name);
                    str = str2 + " - " + d();
                } catch (Exception e2) {
                    e = e2;
                    q.c("DeviceInfo", e.getLocalizedMessage(), e.getCause());
                    return str;
                }
            }
        } catch (Exception e3) {
            Exception exception = e3;
            str = str2;
            e = exception;
            q.c("DeviceInfo", e.getLocalizedMessage(), e.getCause());
            return str;
        }
        return str;
    }

    public String d() {
        return FgVoIP.U().getString(k.app_build_date);
    }

    public String e() {
        String str = "";
        try {
            str = new FgSDKLoader().fgGetAppVerStr();
        } catch (Exception e) {
            q.c("DeviceInfo", e.getLocalizedMessage(), e.getCause());
        }
        return str;
    }

    public String f() {
        String str = "";
        try {
            str = new FgSDKLoader().fgGetAppBuildDateStr();
        } catch (Exception e) {
            q.c("DeviceInfo", e.getLocalizedMessage(), e.getCause());
        }
        return str;
    }

    public String g() {
        return "Android";
    }

    public String h() {
        return VERSION.RELEASE;
    }

    public String i() {
        return Build.MANUFACTURER;
    }

    public String j() {
        return Build.MODEL;
    }

    public String k() {
        String str = "";
        try {
            if (!(this.d == null || this.d.getDeviceId() == null)) {
                str = this.d.getDeviceId();
            }
        } catch (Exception e) {
            q.d("DeviceInfo", "getDeviceIMEI(): " + e);
        }
        return str;
    }

    public String l() {
        try {
            Class cls = Class.forName("android.os.SystemProperties");
            Method method = cls.getMethod("get", new Class[]{String.class});
            String str = (String) method.invoke(cls, new Object[]{"ril.serialnumber"});
            if (str != null && str.length() > 0) {
                return str;
            }
            str = (String) method.invoke(cls, new Object[]{"ro.serialno"});
            if (str != null && str.length() > 0) {
                return str;
            }
            return "";
        } catch (Exception e) {
            q.c("DeviceInfo", e.getLocalizedMessage(), e.getCause());
        }
    }

    public String m() {
        String str = "";
        String str2;
        try {
            Enumeration networkInterfaces = NetworkInterface.getNetworkInterfaces();
            while (networkInterfaces.hasMoreElements()) {
                Enumeration inetAddresses = ((NetworkInterface) networkInterfaces.nextElement()).getInetAddresses();
                while (inetAddresses.hasMoreElements()) {
                    InetAddress inetAddress = (InetAddress) inetAddresses.nextElement();
                    if (inetAddress.isLoopbackAddress()) {
                        str2 = str;
                    } else {
                        str2 = inetAddress.getHostAddress().toString();
                    }
                    str = str2;
                }
            }
            q.a("DeviceInfo", "getDeviceLocalIPAddress(): ipAddress: " + str);
            return str;
        } catch (SocketException e) {
            SocketException socketException = e;
            str2 = str;
            SocketException socketException2 = socketException;
            q.c("DeviceInfo", socketException2.getLocalizedMessage(), socketException2.getCause());
            return str2;
        } catch (Exception e2) {
            Exception exception = e2;
            str2 = str;
            Exception exception2 = exception;
            q.c("DeviceInfo", exception2.getLocalizedMessage(), exception2.getCause());
            return str2;
        }
    }

    public String n() {
        this.m = "";
        if (FgVoIP.U().ai()) {
            S();
            synchronized (this.n) {
                try {
                    this.n.wait(5000);
                } catch (InterruptedException e) {
                    q.d("DeviceInfo", "getDevicePublicIPAddress(): " + e.getMessage());
                }
            }
        }
        return this.m;
    }

    public String o() {
        Object e;
        String str = "";
        try {
            if (!(this.d == null || this.d.getLine1Number() == null)) {
                String replace = this.d.getLine1Number().replace("+", "");
                if (replace == null) {
                    try {
                        replace = "";
                    } catch (Exception e2) {
                        Exception exception = e2;
                        str = replace;
                        Exception e3 = exception;
                        q.d("DeviceInfo", "getSubscriberMSISDN(): " + e3);
                        return str;
                    }
                }
                str = replace.replace("+", "");
            }
        } catch (Exception e4) {
            e3 = e4;
            q.d("DeviceInfo", "getSubscriberMSISDN(): " + e3);
            return str;
        }
        return str;
    }

    public String p() {
        String str = "";
        try {
            if (this.d == null) {
                return str;
            }
            if (this.d.getSubscriberId() != null) {
                str = this.d.getSubscriberId();
            }
            if (str == null) {
                return "";
            }
            return str;
        } catch (Exception e) {
            q.d("DeviceInfo", "getSubscriberIMSI(): " + e);
            return str;
        }
    }

    public boolean q() {
        try {
            if (VERSION.SDK_INT > 21) {
                SubscriptionManager from = SubscriptionManager.from(b);
                if (from != null && from.getActiveSubscriptionInfoCount() > 1) {
                    return true;
                }
            }
        } catch (Exception e) {
            q.d("DeviceInfo", "hasDualSim(): " + e);
        }
        return false;
    }

    public String r() {
        String str = "";
        if (VERSION.SDK_INT > 21) {
            SubscriptionManager from = SubscriptionManager.from(b);
            if (from != null && from.getActiveSubscriptionInfoCount() > 1) {
                List<SubscriptionInfo> activeSubscriptionInfoList = from.getActiveSubscriptionInfoList();
                if (activeSubscriptionInfoList != null) {
                    for (SubscriptionInfo simSlotIndex : activeSubscriptionInfoList) {
                        String str2;
                        String str3 = "";
                        int subscriptionId = from.getActiveSubscriptionInfoForSimSlotIndex(simSlotIndex.getSimSlotIndex()).getSubscriptionId();
                        if (this.d != null) {
                            try {
                                str2 = (String) Class.forName("android.telephony.TelephonyManager").getMethod("getSubscriberId", new Class[]{Integer.TYPE}).invoke(this.d, new Object[]{Integer.valueOf(subscriptionId)});
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            q.a("DeviceInfo", "IMSI value " + str);
                            if (!str2.equals(p())) {
                                str2 = str;
                            }
                            str = str2;
                        }
                        str2 = str3;
                        try {
                            q.a("DeviceInfo", "IMSI value " + str);
                            if (!str2.equals(p())) {
                                str2 = str;
                            }
                            str = str2;
                        } catch (Exception e2) {
                            q.d("DeviceInfo", "getSubscriberIMSI(): " + e2);
                        }
                    }
                }
            }
        }
        return str;
    }

    public int s() {
        int t;
        Object obj;
        int cid;
        try {
            if (this.d.getNetworkType() == 13) {
                t = t();
            } else {
                t = 268435456;
            }
            if (t == 268435456) {
                try {
                    CellLocation cellLocation = this.d.getCellLocation();
                    if (cellLocation != null && (cellLocation instanceof GsmCellLocation)) {
                        cid = ((GsmCellLocation) cellLocation).getCid() & 65535;
                        if (cid < 0) {
                            return 268435456;
                        }
                        return cid;
                    }
                } catch (Exception e) {
                    Exception obj2 = e;
                    cid = t;
                    q.d("DeviceInfo", "getCellIdentity(): " + obj2);
                    return cid;
                }
            }
            return t;
        } catch (Exception e2) {
            Exception exception = e2;
            cid = 268435456;
            obj2 = exception;
            q.d("DeviceInfo", "getCellIdentity(): " + obj2);
            return cid;
        }
    }

    @TargetApi(17)
    public int t() {
        if (VERSION.SDK_INT < 17) {
            return 268435456;
        }
        try {
            int ci;
            if (this.d.getNetworkType() == 13) {
                List allCellInfo = this.d.getAllCellInfo();
                if (allCellInfo != null) {
                    for (int i = 0; i < allCellInfo.size(); i++) {
                        if (allCellInfo.get(i) instanceof CellInfoLte) {
                            CellInfoLte cellInfoLte = (CellInfoLte) allCellInfo.get(i);
                            if (cellInfoLte.isRegistered() && cellInfoLte.getCellIdentity() != null) {
                                ci = cellInfoLte.getCellIdentity().getCi();
                                break;
                            }
                        }
                    }
                }
            }
            ci = 268435456;
            return ci;
        } catch (Exception e) {
            q.d("DeviceInfo", "getTrackingAreaCode(): " + e.getMessage());
            return 268435456;
        }
    }

    public int u() {
        try {
            CellLocation cellLocation = this.d.getCellLocation();
            if (cellLocation != null && (cellLocation instanceof GsmCellLocation)) {
                int lac = ((GsmCellLocation) cellLocation).getLac();
                if (lac < 0) {
                    return 65536;
                }
                return lac;
            }
        } catch (Exception e) {
            q.d("DeviceInfo", "getCellLocation(): " + e);
        }
        return 65536;
    }

    public int v() {
        q.a("DeviceInfo", "getLteCellLocation(): fetching TAC...");
        int y = y();
        if (y == 65536) {
            q.a("DeviceInfo", "getLteCellLocation(): fetching LAC...");
            y = u();
            if (y == 65536) {
                y = 65536;
            }
        }
        q.a("DeviceInfo", "getLteCellLocation(): TAC: " + y);
        return y;
    }

    public int w() {
        String str = "";
        try {
            String networkOperator = this.d.getNetworkOperator();
            if (networkOperator != null) {
                str = networkOperator.substring(0, 3);
            }
            return Integer.parseInt(str);
        } catch (Exception e) {
            return 1000;
        }
    }

    public int x() {
        String str = "";
        try {
            String networkOperator = this.d.getNetworkOperator();
            if (networkOperator != null) {
                str = networkOperator.substring(3);
            }
            return Integer.parseInt(str);
        } catch (Exception e) {
            return 1000;
        }
    }

    @TargetApi(17)
    public int y() {
        if (VERSION.SDK_INT < 17) {
            return 65536;
        }
        try {
            if (this.d.getNetworkType() != 13) {
                return 65536;
            }
            List allCellInfo = this.d.getAllCellInfo();
            if (allCellInfo != null) {
                int tac;
                for (int i = 0; i < allCellInfo.size(); i++) {
                    if (allCellInfo.get(i) instanceof CellInfoLte) {
                        CellInfoLte cellInfoLte = (CellInfoLte) allCellInfo.get(i);
                        if (cellInfoLte.isRegistered() && cellInfoLte.getCellIdentity() != null) {
                            tac = cellInfoLte.getCellIdentity().getTac();
                            break;
                        }
                    }
                }
                tac = 65536;
                return tac;
            }
            q.d("DeviceInfo", "getTrackingAreaCode(): cannot retrieve Cell Info");
            return 65536;
        } catch (Exception e) {
            q.d("DeviceInfo", "getTrackingAreaCode(): " + e.getMessage());
            return 65536;
        }
    }

    public boolean z() {
        boolean z = false;
        try {
            NetworkInfo[] allNetworkInfo = this.c.getAllNetworkInfo();
            if (allNetworkInfo != null) {
                for (NetworkInfo networkInfo : allNetworkInfo) {
                    if (networkInfo != null && (networkInfo.getType() == 0 || networkInfo.getType() == 5)) {
                        q.a("DeviceInfo", "isMobileDataConnected(): " + networkInfo.toString());
                        z = networkInfo.isConnected();
                        break;
                    }
                }
            }
        } catch (Exception e) {
            q.d("DeviceInfo", "isMobileDataConnected(): " + e.getMessage());
        }
        return z;
    }

    @SuppressLint({"NewApi"})
    public boolean A() {
        try {
            NetworkInfo[] allNetworkInfo = this.c.getAllNetworkInfo();
            if (allNetworkInfo == null) {
                return false;
            }
            for (NetworkInfo networkInfo : allNetworkInfo) {
                if (networkInfo != null && (networkInfo.getType() == 0 || networkInfo.getType() == 5)) {
                    boolean a = CallManager.a();
                    q.a("DeviceInfo", "isLTEMobileDataConnected(): callInProgress: " + a + ", connected: " + networkInfo.isConnected() + ", network: " + networkInfo.toString());
                    if (a) {
                        return networkInfo.isConnected();
                    }
                    if (networkInfo.getSubtype() == 13) {
                        return networkInfo.isConnected();
                    }
                }
            }
            return false;
        } catch (Exception e) {
            q.d("DeviceInfo", "isLTEMobileDataConnected(): " + e.getMessage());
            return false;
        }
    }

    public boolean B() {
        NetworkInfo networkInfo = this.c.getNetworkInfo(5);
        if (networkInfo != null && networkInfo.getState() == State.CONNECTED) {
            return true;
        }
        return false;
    }

    public boolean C() {
        boolean z = false;
        try {
            NetworkInfo[] allNetworkInfo = this.c.getAllNetworkInfo();
            if (allNetworkInfo != null) {
                for (NetworkInfo networkInfo : allNetworkInfo) {
                    if (networkInfo != null && networkInfo.getType() == 1) {
                        z = networkInfo.isConnected();
                        break;
                    }
                }
            }
        } catch (Exception e) {
            q.d("DeviceInfo", "isWifiConnected(): " + e.getMessage());
        }
        return z;
    }

    public boolean D() {
        return System.getInt(b.getContentResolver(), "airplane_mode_on", 0) != 0;
    }

    public void a(int i) {
        h = i;
    }

    public int E() {
        return h;
    }

    public boolean F() {
        return E() == 0;
    }

    public boolean G() {
        return E() == 0 && K() == 13;
    }

    public a H() {
        if (A()) {
            return a.LTE;
        }
        if (C()) {
            return a.WLAN;
        }
        if (z()) {
            return a.GPRS;
        }
        return a.ANY;
    }

    public boolean I() {
        return this.d.isNetworkRoaming();
    }

    public boolean J() {
        return o;
    }

    public void b(boolean z) {
        o = z;
    }

    public int K() {
        return this.d.getNetworkType();
    }

    public String b(int i) {
        switch (i) {
            case 0:
                return "TYPE_MOBILE";
            case 1:
                return "TYPE_WIFI";
            case 2:
                return "TYPE_MOBILE_MMS";
            case 3:
                return "TYPE_MOBILE_SUPL";
            case 4:
                return "TYPE_MOBILE_DUN";
            case 5:
                return "TYPE_MOBILE_HIPRI";
            case 6:
                return "TYPE_WIMAX";
            case 7:
                return "TYPE_BLUETOOTH";
            case 9:
                return "TYPE_ETHERNET";
            default:
                return "TYPE_DUMMY";
        }
    }

    public int L() {
        Calendar instance = Calendar.getInstance();
        return instance.getTimeZone().getOffset(instance.getTimeInMillis()) / 60000;
    }

    public int M() {
        i = 0;
        if (this.e != null) {
            WifiInfo connectionInfo = this.e.getConnectionInfo();
            if (connectionInfo != null) {
                i = connectionInfo.getRssi();
            }
        }
        return i;
    }

    public void c(int i) {
        if (i <= 0 || i >= 99) {
            j = 99;
        } else {
            j = i;
        }
    }

    public int N() {
        return j;
    }

    public void d(int i) {
        if (i <= 0 || i >= 99) {
            k = 99;
        } else {
            k = i;
        }
    }

    public int O() {
        return k;
    }

    public String P() {
        String str = "";
        if (this.e == null) {
            return str;
        }
        WifiInfo connectionInfo = this.e.getConnectionInfo();
        if (connectionInfo == null || connectionInfo.getSSID() == null) {
            return str;
        }
        return connectionInfo.getSSID();
    }

    public String Q() {
        String str = "";
        if (this.e == null) {
            return str;
        }
        WifiInfo connectionInfo = this.e.getConnectionInfo();
        if (connectionInfo == null || connectionInfo.getBSSID() == null) {
            return str;
        }
        return connectionInfo.getBSSID();
    }

    public InetAddress R() {
        WifiInfo connectionInfo = this.e.getConnectionInfo();
        if (connectionInfo != null) {
            InetAddress byName;
            int ipAddress = connectionInfo.getIpAddress();
            try {
                byName = InetAddress.getByName(String.format("%d.%d.%d.%d", new Object[]{Integer.valueOf(ipAddress & 255), Integer.valueOf((ipAddress >> 8) & 255), Integer.valueOf((ipAddress >> 16) & 255), Integer.valueOf((ipAddress >> 24) & 255)}));
            } catch (UnknownHostException e) {
                e.printStackTrace();
                byName = null;
            }
            q.b("DeviceInfo", "getWiFiAddress.... WIFI CONNECTED! Local IP = " + byName);
            if (byName.getHostAddress().equals("0.0.0.0") || byName.isLoopbackAddress()) {
                return null;
            }
            return byName;
        }
        q.b("DeviceInfo", "getWiFiAddress.... wifiInfo is null ");
        return null;
    }

    private void S() {
        new Thread(new Runnable(this) {
            final /* synthetic */ l a;

            {
                this.a = r1;
            }

            public void run() {
                HttpURLConnection httpURLConnection;
                Throwable th;
                InputStream inputStream;
                HttpURLConnection httpURLConnection2;
                Throwable th2;
                InputStream inputStream2 = null;
                synchronized (this.a.n) {
                    Object q;
                    try {
                        InputStream inputStream3;
                        HttpURLConnection httpURLConnection3;
                        q = q.q();
                        q.a("DeviceInfo", "requestPublicIPAddress(): URL:" + q);
                        if (TextUtils.isEmpty(q)) {
                            q.c("DeviceInfo", "requestPublicIPAddress(): URL empty, returning empty IP");
                            this.a.m = "";
                            inputStream3 = null;
                            httpURLConnection3 = null;
                        } else {
                            httpURLConnection3 = (HttpURLConnection) new URL(q).openConnection();
                            try {
                                httpURLConnection3.setRequestMethod("GET");
                                httpURLConnection3.setDoInput(true);
                                httpURLConnection3.connect();
                                inputStream3 = new BufferedInputStream(httpURLConnection3.getInputStream());
                                try {
                                    this.a.m = this.a.a(inputStream3);
                                    q.a("DeviceInfo", "requestPublicIPAddress(): Public IP address: " + this.a.m);
                                } catch (Throwable th3) {
                                    inputStream2 = inputStream3;
                                    th2 = th3;
                                    httpURLConnection2 = httpURLConnection3;
                                    th = th2;
                                    if (inputStream2 != null) {
                                        inputStream2.close();
                                    }
                                    if (httpURLConnection2 != null) {
                                        httpURLConnection2.disconnect();
                                    }
                                    throw th;
                                }
                            } catch (Throwable th32) {
                                th2 = th32;
                                httpURLConnection2 = httpURLConnection3;
                                th = th2;
                                if (inputStream2 != null) {
                                    inputStream2.close();
                                }
                                if (httpURLConnection2 != null) {
                                    httpURLConnection2.disconnect();
                                }
                                throw th;
                            }
                        }
                        if (inputStream3 != null) {
                            try {
                                inputStream3.close();
                            } catch (IOException e) {
                                q.d("DeviceInfo", "requestPublicIPAddress(): cannot close input stream " + e);
                            }
                        }
                        if (httpURLConnection3 != null) {
                            httpURLConnection3.disconnect();
                        }
                    } catch (Throwable th4) {
                        th = th4;
                        httpURLConnection2 = null;
                        if (inputStream2 != null) {
                            inputStream2.close();
                        }
                        if (httpURLConnection2 != null) {
                            httpURLConnection2.disconnect();
                        }
                        throw th;
                    }
                    try {
                        this.a.n.notify();
                    } catch (Exception e2) {
                        q.d("DeviceInfo", "requestPublicIPAddress(): " + e2.getMessage());
                    }
                }
            }
        }).start();
    }

    private String a(InputStream inputStream) {
        try {
            Document parse = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(inputStream);
            q.a("DeviceInfo", "parsePublicIPXML(): XML: " + a(parse));
            return ((Element) parse.getElementsByTagName("ip").item(0)).getFirstChild().getNodeValue();
        } catch (Exception e) {
            q.a("DeviceInfo", "parsePublicIPXML(): " + e.getMessage());
            return "";
        }
    }

    public String a(Document document) {
        try {
            Source dOMSource = new DOMSource(document);
            Writer stringWriter = new StringWriter();
            Result streamResult = new StreamResult(stringWriter);
            Transformer newTransformer = TransformerFactory.newInstance().newTransformer();
            newTransformer.setOutputProperty("indent", "yes");
            newTransformer.transform(dOMSource, streamResult);
            return stringWriter.toString();
        } catch (Exception e) {
            q.d("DeviceInfo", "getStringFromDocument(): " + e.getMessage());
            return "unable to parse";
        }
    }
}
