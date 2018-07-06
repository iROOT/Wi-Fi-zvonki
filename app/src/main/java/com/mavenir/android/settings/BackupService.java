package com.mavenir.android.settings;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.Environment;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.provider.Settings.Secure;
import android.support.v4.content.o;
import android.text.TextUtils;
import android.util.Base64;
import com.fgmicrotec.mobile.android.fgvoip.FgVoIP;
import com.fgmicrotec.mobile.android.fgvoip.f.k;
import com.mavenir.android.applog.AppLogAdapter;
import com.mavenir.android.applog.AppLogAdapter.b;
import com.mavenir.android.applog.AppLogAdapter.c;
import com.mavenir.android.common.q;
import com.mavenir.android.common.z;
import com.mavenir.android.messaging.provider.d;
import com.mavenir.android.messaging.service.MessagingService;
import com.mavenir.android.security.CryptoAES;
import com.mavenir.android.settings.c.g;
import com.mavenir.android.settings.c.m;
import com.mavenir.android.settings.c.p;
import com.mavenir.android.settings.c.y;
import com.mavenir.android.vtow.activity.ActivationInitialActivity;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BackupService extends Service {
    public static String a;
    private static long g = 0;
    private static long h = 0;
    private static long i = 0;
    private final IBinder b = new a(this);
    private boolean c = false;
    private boolean d = false;
    private boolean e = false;
    private boolean f = false;
    private Handler j = new Handler(this) {
        final /* synthetic */ BackupService a;

        {
            this.a = r1;
        }

        public void handleMessage(Message message) {
            this.a.a(message.what);
        }
    };

    public class a extends Binder {
        final /* synthetic */ BackupService a;

        public a(BackupService backupService) {
            this.a = backupService;
        }
    }

    public IBinder onBind(Intent intent) {
        return this.b;
    }

    public void onCreate() {
        super.onCreate();
        q.b("BackupService", "BackupService onCreate()");
        a = getString(k.app_name).replace(" ", "");
    }

    public int onStartCommand(Intent intent, int i, int i2) {
        int onStartCommand = super.onStartCommand(intent, i, i2);
        if (!(intent == null || intent.getAction() == null)) {
            if (intent.getAction().equals("IntentActions.StopServiceReq")) {
                stopSelf();
            } else if (intent.getAction().equals("com.mavenir.android.action_backup_user_prefs")) {
                q.a("BackupService", "onStartCommand(): ACTION_BACKUP_USER_PREFS");
                this.j.sendEmptyMessage(0);
            } else if (intent.getAction().equals("com.mavenir.android.action_backup_whitelist")) {
                q.a("BackupService", "onStartCommand(): ACTION_BACKUP_WHITELIST");
                this.j.sendEmptyMessage(1);
            } else if (intent.getAction().equals("com.mavenir.android.action_backup_sms")) {
                q.a("BackupService", "onStartCommand(): ACTION_BACKUP_SMS");
                if (this.j.hasMessages(2)) {
                    q.a("BackupService", "onStartCommand(): backup already scheduled, delaying...");
                    this.j.removeMessages(2);
                }
                this.j.sendEmptyMessageDelayed(2, 10000);
            } else if (intent.getAction().equals("com.mavenir.android.action_backup_data")) {
                q.a("BackupService", "onStartCommand(): ACTION_BACKUP_DATA");
                a(true, null, -1);
            } else if (intent.getAction().equals("com.mavenir.android.action_restore_data")) {
                q.a("BackupService", "onStartCommand(): ACTION_RESTORE_DATA");
                e();
            } else if (intent.getAction().equals("com.mavenir.android.action_restore_user_prefs")) {
                q.a("BackupService", "onStartCommand(): ACTION_RESTORE_USER_PREFS");
                h();
            } else if (intent.getAction().equals("com.mavenir.android.action_backup_deactivated")) {
                q.a("BackupService", "onStartCommand(): ACTION_BACKUP_DEACTIVATED");
                a(true, intent.getAction(), intent.getIntExtra("com.mavenir.android.extra_backup_reason", -1));
            } else if (intent.getAction().equals("com.mavenir.android.action_backup_no_sim")) {
                q.a("BackupService", "onStartCommand(): ACTION_BACKUP_NO_SIM");
                a(true, intent.getAction(), intent.getIntExtra("com.mavenir.android.extra_backup_reason", -1));
            } else if (intent.getAction().equals("com.mavenir.android.action_backup_sim_swapped")) {
                q.a("BackupService", "onStartCommand(): ACTION_BACKUP_SIM_SWAPPED");
                a(true, intent.getAction(), intent.getIntExtra("com.mavenir.android.extra_backup_reason", -1));
            } else if (intent.getAction().equals("com.mavenir.android.action_backup_sim_removed")) {
                q.a("BackupService", "onStartCommand(): ACTION_BACKUP_SIM_REMOVED");
                a(true, intent.getAction(), intent.getIntExtra("com.mavenir.android.extra_backup_reason", -1));
            } else if (intent.getAction().equals("com.mavenir.android.action_backup_disabled")) {
                q.a("BackupService", "onStartCommand(): ACTION_BACKUP_DISABLED");
                n();
            }
        }
        return onStartCommand;
    }

    public void onDestroy() {
        super.onDestroy();
        q.b("BackupService", "BackupService onDestroy()");
    }

    private boolean a() {
        return c.k.d() > 0;
    }

    private File b() {
        return new File("//data//data//" + getPackageName() + "//databases//");
    }

    private File c() {
        return getDir("Common", 0);
    }

    private File d() {
        Object n = p.n();
        if (TextUtils.isEmpty(n)) {
            return null;
        }
        return getDir(n, 0);
    }

    private File a(String str, boolean z) {
        if (z) {
            str = Base64.encodeToString(FgVoIP.U().b(str), 8).replace("\n", "");
        }
        File file = new File(Environment.getExternalStorageDirectory() + "/" + a + "/" + str);
        if (!file.exists()) {
            file.mkdirs();
        }
        return file;
    }

    public static File a(File file, String str) {
        try {
            String replace = Base64.encodeToString(FgVoIP.U().b(str), 8).replace("\n", "");
            if (!file.exists()) {
                file.mkdirs();
            }
            return new File(file, replace);
        } catch (Exception e) {
            q.d("BackupService", "getOrCreateFile(): " + e);
            return null;
        }
    }

    private File b(File file, String str) {
        try {
            if (!file.exists()) {
                file.mkdirs();
            }
            return new File(file, str);
        } catch (Exception e) {
            q.d("BackupService", "getOrCreateFile(): " + e);
            return null;
        }
    }

    private void a(final int i) {
        new Thread(new Runnable(this) {
            final /* synthetic */ BackupService b;

            public void run() {
                if (!c.k.g()) {
                    return;
                }
                if (!this.b.a()) {
                    q.c("BackupService", "bgrBackupData(): Backup not allowed");
                } else if (i == 0) {
                    this.b.g();
                    this.b.a(false);
                } else if (i == 1) {
                    this.b.i();
                    this.b.b(false);
                } else if (i == 2) {
                    this.b.k();
                    this.b.a(false, true);
                } else if (i == 4) {
                    this.b.a(false);
                } else if (i == 5) {
                    this.b.b(false);
                } else if (i == 6) {
                    this.b.a(false, true);
                }
            }
        }).start();
    }

    private void a(boolean z, String str, int i) {
        if (c.k.g() && !this.c) {
            this.c = true;
            g();
            i();
            k();
            c(z);
            this.c = false;
        }
        a(str, i);
    }

    private void e() {
        new Thread(new Runnable(this) {
            final /* synthetic */ BackupService a;

            {
                this.a = r1;
            }

            public void run() {
                if (c.k.g()) {
                    this.a.m();
                    this.a.h();
                    this.a.j();
                    this.a.l();
                }
                Intent intent = new Intent(this.a, ActivationInitialActivity.class);
                intent.setAction("com.mavenir.android.activation.action_restoration_cnf");
                o.a(this.a).a(intent);
            }
        }).start();
    }

    private void a(String str, int i) {
        if (!str.equals("com.mavenir.android.action_backup_data")) {
            g.b();
            d.a(this);
            FgVoIP.U().a(false);
            FgVoIP.U().a("com.mavenir.android.messaging.service.MessagingService", "IntentActions.StopServiceReq");
            Intent intent = new Intent();
            intent.setAction("InternalIntents.UpdateSmsNotification");
            sendBroadcast(intent);
            if (i == -2) {
                f();
            } else {
                b(str, i);
            }
        }
    }

    private void f() {
        o.a((Context) this).a(new Intent("com.mavenir.android.activation.action_deactivation_cnf"));
    }

    private void b(String str, int i) {
        if (!str.equals("com.mavenir.android.action_backup_deactivated")) {
            c cVar;
            String string;
            if (str.equals("com.mavenir.android.action_backup_sim_swapped")) {
                cVar = c.FGAPPLOG_EVENT_REASON_SIM_HOT_SWAPPED;
                string = getResources().getString(k.exception_sim_swapped);
            } else if (str.equals("com.mavenir.android.action_backup_sim_removed")) {
                cVar = c.FGAPPLOG_EVENT_REASON_SIM_REMOVED;
                string = getResources().getString(k.exception_sim_no_sim);
            } else {
                cVar = c.FGAPPLOG_EVENT_REASON_NO_SIM;
                string = getResources().getString(k.exception_sim_no_sim);
            }
            com.mavenir.android.applog.a.a((Context) this).a(b.FGAPPLOG_EVENT_GROUP_SIM_ERROR, AppLogAdapter.d.FGAPPLOG_EVENT_TYPE_DEACTIVATED, cVar, "");
            FgVoIP.U().a(com.mavenir.android.fragments.f.a.H3G_DEACTIVATION, -1, 0, string);
        } else if (i != -1) {
            FgVoIP.U().a(com.mavenir.android.fragments.f.a.H3G_DEACTIVATION, i);
        }
    }

    private void g() {
        Object e;
        Throwable th;
        int i = 1;
        if (this.d) {
            q.c("BackupService", "backupUserPreferences(): user backup in progress, skipping...");
            return;
        }
        this.d = true;
        FileOutputStream fileOutputStream;
        try {
            fileOutputStream = new FileOutputStream(b(c(), "user_preferences.txt"));
            try {
                int i2;
                a(fileOutputStream, "save_user_data", "" + (c.k.g() ? 1 : 0));
                String str = "start_after_phone_boot";
                StringBuilder append = new StringBuilder().append("");
                if (c.k.h()) {
                    i2 = 1;
                } else {
                    i2 = 0;
                }
                a(fileOutputStream, str, append.append(i2).toString());
                if (FgVoIP.U().F()) {
                    str = "enable_status_icon";
                    append = new StringBuilder().append("");
                    if (c.k.i()) {
                        i2 = 1;
                    } else {
                        i2 = 0;
                    }
                    a(fileOutputStream, str, append.append(i2).toString());
                }
                if (FgVoIP.U().G()) {
                    str = "enable_messaging";
                    append = new StringBuilder().append("");
                    if (c.k.u()) {
                        i2 = 1;
                    } else {
                        i2 = 0;
                    }
                    a(fileOutputStream, str, append.append(i2).toString());
                }
                if (FgVoIP.U().K()) {
                    str = "sms_request_delivery_reports";
                    append = new StringBuilder().append("");
                    if (c.k.l()) {
                        i2 = 1;
                    } else {
                        i2 = 0;
                    }
                    a(fileOutputStream, str, append.append(i2).toString());
                }
                str = "sms_notify_incoming";
                append = new StringBuilder().append("");
                if (c.k.p()) {
                    i2 = 1;
                } else {
                    i2 = 0;
                }
                a(fileOutputStream, str, append.append(i2).toString());
                a(fileOutputStream, "media_alert_volume", "" + m.k());
                str = "enable_tcp_keep_alive";
                append = new StringBuilder().append("");
                if (c.k.t()) {
                    i2 = 1;
                } else {
                    i2 = 0;
                }
                a(fileOutputStream, str, append.append(i2).toString());
                str = "contacts_display_order";
                append = new StringBuilder().append("");
                if (c.k.y() == 1) {
                    i2 = 1;
                } else {
                    i2 = 0;
                }
                a(fileOutputStream, str, append.append(i2).toString());
                String str2 = "contacts_sort_order";
                StringBuilder append2 = new StringBuilder().append("");
                if (c.k.z() != 1) {
                    i = 0;
                }
                a(fileOutputStream, str2, append2.append(i).toString());
                q.b("BackupService", "backupUserPreferences(): User preferences backed up!");
                if (fileOutputStream != null) {
                    try {
                        fileOutputStream.close();
                    } catch (Exception e2) {
                        q.d("BackupService", "backupUserPreferences(): error closing stream: " + e2);
                    }
                }
            } catch (Exception e3) {
                e = e3;
                try {
                    q.d("BackupService", "backupUserPreferences(): " + e);
                    if (fileOutputStream != null) {
                        try {
                            fileOutputStream.close();
                        } catch (Exception e22) {
                            q.d("BackupService", "backupUserPreferences(): error closing stream: " + e22);
                        }
                    }
                    this.d = false;
                } catch (Throwable th2) {
                    th = th2;
                    if (fileOutputStream != null) {
                        try {
                            fileOutputStream.close();
                        } catch (Exception e4) {
                            q.d("BackupService", "backupUserPreferences(): error closing stream: " + e4);
                        }
                    }
                    throw th;
                }
            }
        } catch (Exception e5) {
            e = e5;
            fileOutputStream = null;
            q.d("BackupService", "backupUserPreferences(): " + e);
            if (fileOutputStream != null) {
                fileOutputStream.close();
            }
            this.d = false;
        } catch (Throwable th3) {
            th = th3;
            fileOutputStream = null;
            if (fileOutputStream != null) {
                fileOutputStream.close();
            }
            throw th;
        }
        this.d = false;
    }

    private void h() {
        Object e;
        Throwable th;
        FileInputStream fileInputStream = null;
        try {
            File b = b(c(), "user_preferences.txt");
            if (b.exists()) {
                FileInputStream fileInputStream2 = new FileInputStream(b);
                try {
                    if (a(a(fileInputStream2))) {
                        q.b("BackupService", "restoreUserPreferences(): User preferences restored!");
                    } else {
                        q.c("BackupService", "restoreUserPreferences(): User preferences failed to restore!");
                    }
                    if (fileInputStream2 != null) {
                        try {
                            fileInputStream2.close();
                            return;
                        } catch (Exception e2) {
                            q.d("BackupService", "restoreUserPreferences(): error closing stream: " + e2);
                            return;
                        }
                    }
                    return;
                } catch (Exception e3) {
                    e = e3;
                    fileInputStream = fileInputStream2;
                    try {
                        q.d("BackupService", "restoreUserPreferences(): " + e);
                        if (fileInputStream != null) {
                            try {
                                fileInputStream.close();
                            } catch (Exception e22) {
                                q.d("BackupService", "restoreUserPreferences(): error closing stream: " + e22);
                                return;
                            }
                        }
                    } catch (Throwable th2) {
                        th = th2;
                        if (fileInputStream != null) {
                            try {
                                fileInputStream.close();
                            } catch (Exception e4) {
                                q.d("BackupService", "restoreUserPreferences(): error closing stream: " + e4);
                            }
                        }
                        throw th;
                    }
                } catch (Throwable th3) {
                    th = th3;
                    fileInputStream = fileInputStream2;
                    if (fileInputStream != null) {
                        fileInputStream.close();
                    }
                    throw th;
                }
            }
            q.c("BackupService", "restoreUserPreferences(): Stored preferences not found!");
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (Exception e222) {
                    q.d("BackupService", "restoreUserPreferences(): error closing stream: " + e222);
                }
            }
        } catch (Exception e5) {
            e = e5;
            q.d("BackupService", "restoreUserPreferences(): " + e);
            if (fileInputStream != null) {
                fileInputStream.close();
            }
        }
    }

    private boolean a(List<String> list) {
        if (list == null || list.isEmpty()) {
            return false;
        }
        for (String split : list) {
            String[] split2 = split.split("=");
            if (split2.length == 2) {
                boolean z;
                if (split2[0].equals("save_user_data")) {
                    if (Integer.parseInt(split2[1]) == 0) {
                        z = false;
                    } else {
                        z = true;
                    }
                    c.k.a(z);
                } else if (split2[0].equals("start_after_phone_boot")) {
                    if (Integer.parseInt(split2[1]) == 0) {
                        z = false;
                    } else {
                        z = true;
                    }
                    c.k.b(z);
                } else if (split2[0].equals("enable_status_icon")) {
                    if (Integer.parseInt(split2[1]) == 0) {
                        z = false;
                    } else {
                        z = true;
                    }
                    if (FgVoIP.U().F()) {
                        c.k.c(z);
                    }
                } else if (split2[0].equals("enable_messaging")) {
                    if (Integer.parseInt(split2[1]) == 0) {
                        z = false;
                    } else {
                        z = true;
                    }
                    if (FgVoIP.U().G()) {
                        c.k.k(z);
                    }
                } else if (split2[0].equals("sms_request_delivery_reports")) {
                    if (Integer.parseInt(split2[1]) == 0) {
                        z = false;
                    } else {
                        z = true;
                    }
                    if (FgVoIP.U().K()) {
                        c.k.d(z);
                    }
                } else if (split2[0].equals("sms_notify_incoming")) {
                    if (Integer.parseInt(split2[1]) == 0) {
                        z = false;
                    } else {
                        z = true;
                    }
                    c.k.g(z);
                } else if (split2[0].equals("media_alert_volume")) {
                    int parseInt = Integer.parseInt(split2[1]);
                    if (FgVoIP.U().Q()) {
                        m.g(parseInt);
                    }
                } else if (split2[0].equals("enable_tcp_keep_alive")) {
                    if (Integer.parseInt(split2[1]) == 0) {
                        z = false;
                    } else {
                        z = true;
                    }
                    c.k.j(z);
                } else if (split2[0].equals("contacts_display_order")) {
                    c.k.e(Integer.parseInt(split2[1]));
                } else if (split2[0].equals("contacts_sort_order")) {
                    c.k.f(Integer.parseInt(split2[1]));
                }
            }
        }
        return true;
    }

    private void i() {
        Object e;
        Throwable th;
        if (this.e) {
            q.c("BackupService", "backupWhitelist(): white list backup in progress, skipping...");
            return;
        }
        this.e = true;
        FileOutputStream fileOutputStream = null;
        FileOutputStream fileOutputStream2;
        try {
            List b = y.b();
            if (b != null && b.size() != 0) {
                fileOutputStream2 = new FileOutputStream(b(c(), "whitelist.txt"));
                int i = 0;
                while (i < b.size()) {
                    try {
                        String a = ((z) b.get(i)).a();
                        Object b2 = ((z) b.get(i)).b();
                        boolean c = ((z) b.get(i)).c();
                        if (!TextUtils.isEmpty(a)) {
                            a(fileOutputStream2, a, "" + (c ? 1 : 0));
                        } else if (TextUtils.isEmpty(b2)) {
                            q.d("BackupService", "backupWhitelist(): ssid and bssid are unknown, skipping...");
                        } else {
                            a(fileOutputStream2, "bssid:" + b2, "" + (c ? 1 : 0));
                        }
                        i++;
                    } catch (Exception e2) {
                        e = e2;
                    }
                }
                q.b("BackupService", "backupWhitelist(): Whitelist backed up!");
                if (fileOutputStream2 != null) {
                    try {
                        fileOutputStream2.close();
                    } catch (Exception e3) {
                        q.d("BackupService", "backupWhitelist(): error closing stream: " + e3);
                    }
                }
                this.e = false;
            } else if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (Exception e32) {
                    q.d("BackupService", "backupWhitelist(): error closing stream: " + e32);
                }
            }
        } catch (Exception e4) {
            e = e4;
            fileOutputStream2 = fileOutputStream;
            try {
                q.d("BackupService", "backupWhitelist(): " + e);
                if (fileOutputStream2 != null) {
                    try {
                        fileOutputStream2.close();
                    } catch (Exception e322) {
                        q.d("BackupService", "backupWhitelist(): error closing stream: " + e322);
                    }
                }
                this.e = false;
            } catch (Throwable th2) {
                th = th2;
                if (fileOutputStream2 != null) {
                    try {
                        fileOutputStream2.close();
                    } catch (Exception e5) {
                        q.d("BackupService", "backupWhitelist(): error closing stream: " + e5);
                    }
                }
                throw th;
            }
        } catch (Throwable th3) {
            th = th3;
            fileOutputStream2 = fileOutputStream;
            if (fileOutputStream2 != null) {
                fileOutputStream2.close();
            }
            throw th;
        }
    }

    private void j() {
        Object e;
        Throwable th;
        FileInputStream fileInputStream = null;
        try {
            File b = b(c(), "whitelist.txt");
            if (b.exists()) {
                FileInputStream fileInputStream2 = new FileInputStream(b);
                try {
                    if (b(a(fileInputStream2))) {
                        q.b("BackupService", "restoreWhitelist(): White list restored!");
                    } else {
                        q.c("BackupService", "restoreWhitelist(): White list failed to restore!");
                    }
                    if (fileInputStream2 != null) {
                        try {
                            fileInputStream2.close();
                            return;
                        } catch (Exception e2) {
                            q.d("BackupService", "restoreWhitelist(): error closing stream: " + e2);
                            return;
                        }
                    }
                    return;
                } catch (Exception e3) {
                    e = e3;
                    fileInputStream = fileInputStream2;
                    try {
                        q.d("BackupService", "restoreWhitelist(): " + e);
                        if (fileInputStream != null) {
                            try {
                                fileInputStream.close();
                            } catch (Exception e22) {
                                q.d("BackupService", "restoreWhitelist(): error closing stream: " + e22);
                                return;
                            }
                        }
                    } catch (Throwable th2) {
                        th = th2;
                        if (fileInputStream != null) {
                            try {
                                fileInputStream.close();
                            } catch (Exception e4) {
                                q.d("BackupService", "restoreWhitelist(): error closing stream: " + e4);
                            }
                        }
                        throw th;
                    }
                } catch (Throwable th3) {
                    th = th3;
                    fileInputStream = fileInputStream2;
                    if (fileInputStream != null) {
                        fileInputStream.close();
                    }
                    throw th;
                }
            }
            q.c("BackupService", "restoreWhitelist(): Stored whitelist not found!");
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (Exception e222) {
                    q.d("BackupService", "restoreWhitelist(): error closing stream: " + e222);
                }
            }
        } catch (Exception e5) {
            e = e5;
            q.d("BackupService", "restoreWhitelist(): " + e);
            if (fileInputStream != null) {
                fileInputStream.close();
            }
        }
    }

    private boolean b(List<String> list) {
        if (list == null || list.isEmpty()) {
            return false;
        }
        y.a();
        for (String split : list) {
            String[] split2 = split.split("=");
            if (split2.length == 2) {
                boolean z;
                if (Integer.parseInt(split2[1]) == 0) {
                    z = false;
                } else {
                    z = true;
                }
                if (split2[0].startsWith("bssid:")) {
                    y.a(null, split2[0].substring(6), z);
                } else {
                    y.a(split2[0], null, z);
                }
            }
        }
        return true;
    }

    private void k() {
        if (this.f) {
            q.c("BackupService", "backupSmsDatabase(): SMS db backup in progress, skipping...");
            return;
        }
        this.f = true;
        CharSequence replace = "mmssms.db".replace(".db", "");
        File b = b();
        File d = d();
        if (d == null || !d.exists()) {
            q.d("BackupService", "backupSmsDatabase(): cannot create backup folder!");
            this.f = false;
            return;
        }
        File[] listFiles = b.listFiles();
        if (listFiles == null || listFiles.length == 0) {
            q.c("BackupService", "backupSmsDatabase(): nothing to backup!");
            this.f = false;
            return;
        }
        a(d);
        for (int i = 0; i < listFiles.length; i++) {
            if (listFiles[i].getName().contains(replace)) {
                if (a(listFiles[i], b(d, listFiles[i].getName()))) {
                    q.b("BackupService", "backupSmsDatabase(): Sms file: " + listFiles[i].getName() + " backed up!");
                } else {
                    q.c("BackupService", "backupSmsDatabase(): Sms file: " + listFiles[i].getName() + " failed to back up!");
                }
            }
        }
        this.f = false;
    }

    private void l() {
        CharSequence replace = "mmssms.db".replace(".db", "");
        q.b("BackupService", "restoreSmsDatabase(): Restoring sms database...");
        File b = b();
        File d = d();
        if (d == null || !d.exists()) {
            q.c("BackupService", "restoreSmsDatabase(): Stored sms database not found!");
            return;
        }
        File[] listFiles = d.listFiles();
        if (listFiles == null || listFiles.length == 0) {
            q.c("BackupService", "restoreSmsDatabase(): Stored sms database not found!");
            return;
        }
        for (int i = 0; i < listFiles.length; i++) {
            if (listFiles[i].getName().contains(replace)) {
                if (a(listFiles[i], new File(b, listFiles[i].getName()))) {
                    q.b("BackupService", "restoreSmsDatabase(): Sms file: " + listFiles[i].getName() + " restored!");
                } else {
                    q.c("BackupService", "restoreSmsDatabase(): Sms file: " + listFiles[i].getName() + " failed to restore!");
                }
            }
        }
        Intent intent = new Intent(this, MessagingService.class);
        intent.setAction("MessagingService.ACTION_REFRESH_CACHE");
        startService(intent);
    }

    private boolean a(boolean z) {
        if (!Environment.getExternalStorageState().equals("mounted")) {
            q.c("BackupService", "exportUserPrefsToExternalStorage(): no external storage mounted for writting");
            return false;
        } else if (this.d) {
            q.c("BackupService", "exportUserPrefsToExternalStorage(): backup in progress, delay export...");
            if (this.j.hasMessages(4)) {
                this.j.removeMessages(4);
            }
            this.j.sendEmptyMessageDelayed(4, 10000);
            return false;
        } else {
            q.b("BackupService", "exportUserPrefsToExternalStorage(): export in progress...");
            File c = c();
            File a = a("Common", true);
            if (c != null && a.canWrite()) {
                b(b(c, "user_preferences.txt"), a(a, "user_preferences.txt"));
            }
            g = System.currentTimeMillis();
            return true;
        }
    }

    private boolean b(boolean z) {
        if (!Environment.getExternalStorageState().equals("mounted")) {
            q.c("BackupService", "exportWhitelistToExternalStorage(): no external storage mounted for writting");
            return false;
        } else if (this.e) {
            q.c("BackupService", "exportWhitelistToExternalStorage(): backup in progress, delay export...");
            if (this.j.hasMessages(5)) {
                this.j.removeMessages(5);
            }
            this.j.sendEmptyMessageDelayed(5, 10000);
            return false;
        } else {
            q.b("BackupService", "exportWhitelistToExternalStorage(): export in progress...");
            File c = c();
            File a = a("Common", true);
            if (c != null && a.canWrite()) {
                b(b(c, "whitelist.txt"), a(a, "whitelist.txt"));
            }
            h = System.currentTimeMillis();
            return true;
        }
    }

    private boolean a(boolean z, boolean z2) {
        int i = 0;
        if (!Environment.getExternalStorageState().equals("mounted")) {
            q.c("BackupService", "exportSmsToExternalStorage(): no external storage mounted for writting");
            return false;
        } else if (this.f) {
            q.c("BackupService", "exportSmsToExternalStorage(): backup in progress, delay export...");
            if (this.j.hasMessages(6)) {
                this.j.removeMessages(6);
            }
            this.j.sendEmptyMessageDelayed(6, 10000);
            return false;
        } else {
            q.b("BackupService", "exportSmsToExternalStorage(): export in progress...");
            File d = d();
            File a = a(p.n(), true);
            if (!(d == null || a == null || !a.canWrite())) {
                File[] listFiles = d.listFiles();
                a(a);
                if (listFiles != null) {
                    int length = listFiles.length;
                    while (i < length) {
                        File file = listFiles[i];
                        if (z2) {
                            b(file, a(a, file.getName()));
                        } else {
                            a(file, b(a, file.getName()));
                        }
                        i++;
                    }
                }
            }
            i = System.currentTimeMillis();
            return true;
        }
    }

    private boolean c(boolean z) {
        boolean a = a(z);
        boolean b = b(z);
        boolean a2 = a(z, true);
        if (a && b && a2) {
            return true;
        }
        return false;
    }

    private void m() {
        if (Environment.getExternalStorageState().equals("mounted") || Environment.getExternalStorageState().equals("mounted_ro")) {
            File c = c();
            File d = d();
            File a = a("Common", true);
            File a2 = a(p.n(), true);
            if (c != null && c.canWrite()) {
                c(a(a, "user_preferences.txt"), b(c, "user_preferences.txt"));
                c(a(a, "whitelist.txt"), b(c, "whitelist.txt"));
            }
            if (d != null && d.canWrite()) {
                File[] listFiles = a2.listFiles();
                if (listFiles != null) {
                    if (listFiles.length > 0) {
                        a(d);
                    }
                    for (File file : listFiles) {
                        String str = "mmssms.db";
                        byte[] decode = Base64.decode(file.getName(), 8);
                        if (Arrays.equals(FgVoIP.U().b("mmssms.db-shm"), decode)) {
                            str = str + "-shm";
                        } else if (Arrays.equals(FgVoIP.U().b("mmssms.db-wal"), decode)) {
                            str = str + "-wal";
                        } else if (Arrays.equals(FgVoIP.U().b("mmssms.db"), decode)) {
                            str = "mmssms.db";
                        } else {
                        }
                        c(file, b(d, str));
                    }
                    return;
                }
                return;
            }
            return;
        }
        q.c("BackupService", "importFromExternalToInternalStorage(): no external storage mounted for reading");
    }

    private void a(FileOutputStream fileOutputStream, String str, String str2) {
        try {
            fileOutputStream.write((str + "=" + str2 + "\n").getBytes());
        } catch (Exception e) {
            q.d("BackupService", "writeToFile(): " + e);
        }
    }

    private List<String> a(FileInputStream fileInputStream) {
        List<String> arrayList = new ArrayList();
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));
            while (true) {
                String readLine = bufferedReader.readLine();
                if (readLine == null) {
                    break;
                }
                arrayList.add(readLine.replace("\n", ""));
            }
            bufferedReader.close();
        } catch (Exception e) {
            q.d("BackupService", "readFromFile(): " + e);
        }
        return arrayList;
    }

    private boolean a(File file, File file2) {
        Exception e;
        FileOutputStream fileOutputStream;
        FileInputStream fileInputStream;
        FileChannel fileChannel;
        Throwable th;
        FileChannel fileChannel2 = null;
        FileInputStream fileInputStream2 = null;
        FileOutputStream fileOutputStream2 = null;
        FileChannel fileChannel3 = null;
        FileChannel fileChannel4 = null;
        FileChannel channel;
        try {
            if (file.exists()) {
                FileInputStream fileInputStream3 = new FileInputStream(file);
                try {
                    FileOutputStream fileOutputStream3 = new FileOutputStream(file2);
                    try {
                        channel = fileInputStream3.getChannel();
                        try {
                            FileChannel channel2 = fileOutputStream3.getChannel();
                            try {
                                channel2.transferFrom(channel, 0, channel.size());
                                if (fileInputStream3 != null) {
                                    try {
                                        fileInputStream3.close();
                                    } catch (Exception e2) {
                                        q.d("BackupService", "copyFile(): error closing stream: " + e2);
                                    }
                                }
                                if (fileOutputStream3 != null) {
                                    fileOutputStream3.close();
                                }
                                if (channel != null) {
                                    channel.close();
                                }
                                if (channel2 != null) {
                                    channel2.close();
                                }
                                return true;
                            } catch (Exception e3) {
                                fileOutputStream = fileOutputStream3;
                                fileInputStream = fileInputStream3;
                                fileChannel = channel;
                                channel = channel2;
                                e2 = e3;
                                fileChannel2 = fileChannel;
                                try {
                                    q.d("BackupService", "copyFile(): " + e2.getLocalizedMessage());
                                    if (fileInputStream != null) {
                                        try {
                                            fileInputStream.close();
                                        } catch (Exception e22) {
                                            q.d("BackupService", "copyFile(): error closing stream: " + e22);
                                        }
                                    }
                                    if (fileOutputStream != null) {
                                        fileOutputStream.close();
                                    }
                                    if (fileChannel2 != null) {
                                        fileChannel2.close();
                                    }
                                    if (channel != null) {
                                        channel.close();
                                    }
                                    return false;
                                } catch (Throwable th2) {
                                    th = th2;
                                    if (fileInputStream != null) {
                                        try {
                                            fileInputStream.close();
                                        } catch (Exception e4) {
                                            q.d("BackupService", "copyFile(): error closing stream: " + e4);
                                            throw th;
                                        }
                                    }
                                    if (fileOutputStream != null) {
                                        fileOutputStream.close();
                                    }
                                    if (fileChannel2 != null) {
                                        fileChannel2.close();
                                    }
                                    if (channel != null) {
                                        channel.close();
                                    }
                                    throw th;
                                }
                            } catch (Throwable th3) {
                                fileOutputStream = fileOutputStream3;
                                fileInputStream = fileInputStream3;
                                fileChannel = channel;
                                channel = channel2;
                                th = th3;
                                fileChannel2 = fileChannel;
                                if (fileInputStream != null) {
                                    fileInputStream.close();
                                }
                                if (fileOutputStream != null) {
                                    fileOutputStream.close();
                                }
                                if (fileChannel2 != null) {
                                    fileChannel2.close();
                                }
                                if (channel != null) {
                                    channel.close();
                                }
                                throw th;
                            }
                        } catch (Exception e5) {
                            e22 = e5;
                            fileOutputStream = fileOutputStream3;
                            fileInputStream = fileInputStream3;
                            fileChannel2 = channel;
                            channel = null;
                            q.d("BackupService", "copyFile(): " + e22.getLocalizedMessage());
                            if (fileInputStream != null) {
                                fileInputStream.close();
                            }
                            if (fileOutputStream != null) {
                                fileOutputStream.close();
                            }
                            if (fileChannel2 != null) {
                                fileChannel2.close();
                            }
                            if (channel != null) {
                                channel.close();
                            }
                            return false;
                        } catch (Throwable th4) {
                            th = th4;
                            fileOutputStream = fileOutputStream3;
                            fileInputStream = fileInputStream3;
                            fileChannel2 = channel;
                            channel = null;
                            if (fileInputStream != null) {
                                fileInputStream.close();
                            }
                            if (fileOutputStream != null) {
                                fileOutputStream.close();
                            }
                            if (fileChannel2 != null) {
                                fileChannel2.close();
                            }
                            if (channel != null) {
                                channel.close();
                            }
                            throw th;
                        }
                    } catch (Exception e6) {
                        e22 = e6;
                        channel = null;
                        fileOutputStream = fileOutputStream3;
                        fileInputStream = fileInputStream3;
                        q.d("BackupService", "copyFile(): " + e22.getLocalizedMessage());
                        if (fileInputStream != null) {
                            fileInputStream.close();
                        }
                        if (fileOutputStream != null) {
                            fileOutputStream.close();
                        }
                        if (fileChannel2 != null) {
                            fileChannel2.close();
                        }
                        if (channel != null) {
                            channel.close();
                        }
                        return false;
                    } catch (Throwable th5) {
                        th = th5;
                        channel = null;
                        fileOutputStream = fileOutputStream3;
                        fileInputStream = fileInputStream3;
                        if (fileInputStream != null) {
                            fileInputStream.close();
                        }
                        if (fileOutputStream != null) {
                            fileOutputStream.close();
                        }
                        if (fileChannel2 != null) {
                            fileChannel2.close();
                        }
                        if (channel != null) {
                            channel.close();
                        }
                        throw th;
                    }
                } catch (Exception e7) {
                    e22 = e7;
                    channel = null;
                    fileOutputStream = null;
                    fileInputStream = fileInputStream3;
                    q.d("BackupService", "copyFile(): " + e22.getLocalizedMessage());
                    if (fileInputStream != null) {
                        fileInputStream.close();
                    }
                    if (fileOutputStream != null) {
                        fileOutputStream.close();
                    }
                    if (fileChannel2 != null) {
                        fileChannel2.close();
                    }
                    if (channel != null) {
                        channel.close();
                    }
                    return false;
                } catch (Throwable th6) {
                    th = th6;
                    channel = null;
                    fileOutputStream = null;
                    fileInputStream = fileInputStream3;
                    if (fileInputStream != null) {
                        fileInputStream.close();
                    }
                    if (fileOutputStream != null) {
                        fileOutputStream.close();
                    }
                    if (fileChannel2 != null) {
                        fileChannel2.close();
                    }
                    if (channel != null) {
                        channel.close();
                    }
                    throw th;
                }
            }
            if (null != null) {
                try {
                    fileInputStream2.close();
                } catch (Exception e222) {
                    q.d("BackupService", "copyFile(): error closing stream: " + e222);
                }
            }
            if (null != null) {
                fileOutputStream2.close();
            }
            if (null != null) {
                fileChannel3.close();
            }
            if (null != null) {
                fileChannel4.close();
            }
            return false;
        } catch (Exception e8) {
            e222 = e8;
            channel = null;
            fileOutputStream = null;
            fileInputStream = null;
            q.d("BackupService", "copyFile(): " + e222.getLocalizedMessage());
            if (fileInputStream != null) {
                fileInputStream.close();
            }
            if (fileOutputStream != null) {
                fileOutputStream.close();
            }
            if (fileChannel2 != null) {
                fileChannel2.close();
            }
            if (channel != null) {
                channel.close();
            }
            return false;
        } catch (Throwable th7) {
            th = th7;
            channel = null;
            fileOutputStream = null;
            fileInputStream = null;
            if (fileInputStream != null) {
                fileInputStream.close();
            }
            if (fileOutputStream != null) {
                fileOutputStream.close();
            }
            if (fileChannel2 != null) {
                fileChannel2.close();
            }
            if (channel != null) {
                channel.close();
            }
            throw th;
        }
    }

    private void a(File file) {
        int i = 0;
        if (file != null && file.exists()) {
            File[] listFiles = file.listFiles();
            if (listFiles != null) {
                int length = listFiles.length;
                String name = file.getName();
                for (File delete : listFiles) {
                    if (delete.delete()) {
                        i++;
                    }
                }
                q.b("BackupService", "deleteAllFilesInFolder(): Folder " + name + ": deleted " + i + "/" + length + " files");
                try {
                    if (file.listFiles().length != 0) {
                        return;
                    }
                    if (file.delete()) {
                        q.b("BackupService", "deleteAllFilesInFolder(): Folder " + name + " deleted");
                    } else {
                        q.c("BackupService", "deleteAllFilesInFolder(): Folder " + name + " failed to delete");
                    }
                } catch (Exception e) {
                    q.c("BackupService", "deleteAllFilesInFolder(): Folder " + name + " failed to delete");
                }
            }
        }
    }

    private void n() {
        new Thread(new Runnable(this) {
            final /* synthetic */ BackupService a;

            {
                this.a = r1;
            }

            public void run() {
                this.a.a(this.a.a("Common", true));
                this.a.a(this.a.a(p.n(), true));
                this.a.a(this.a.c());
                this.a.a(this.a.d());
                BackupService.g = 0;
                BackupService.h = 0;
                BackupService.i = 0;
            }
        }).start();
    }

    private void b(File file, File file2) {
        if (file != null) {
            try {
                if (file.exists()) {
                    if (CryptoAES.getInstance().encryptFile(Secure.getString(FgVoIP.U().getContentResolver(), "android_id"), file, file2)) {
                        q.b("BackupService", "encryptFile(): File " + file2.getName() + " encrypted");
                        return;
                    } else {
                        q.c("BackupService", "encryptFile(): File " + file2.getName() + " not encrypted");
                        return;
                    }
                }
            } catch (Exception e) {
                q.d("BackupService", "encryptFile(): " + e);
                return;
            }
        }
        q.c("BackupService", "encryptFile(): File " + file.getName() + " doesn't exist");
    }

    private void c(File file, File file2) {
        boolean z = false;
        if (file != null) {
            try {
                if (file.exists()) {
                    File b = b(file2.getParentFile(), "temp_decryption_file");
                    boolean decryptFile = CryptoAES.getInstance().decryptFile(Secure.getString(FgVoIP.U().getContentResolver(), "android_id"), file, b);
                    if (!decryptFile) {
                        decryptFile = CryptoAES.getInstance().decryptFile(Base64.encodeToString(FgVoIP.h, 0), file, b);
                    }
                    if (decryptFile) {
                        z = a(b, file2);
                    }
                    if (z) {
                        q.b("BackupService", "decryptFile(): File " + file.getName() + " decrypted");
                    } else {
                        q.c("BackupService", "decryptFile(): File " + file.getName() + " not decrypted");
                    }
                    b.delete();
                    return;
                }
            } catch (Exception e) {
                q.d("BackupService", "decryptFile(): " + e);
                return;
            }
        }
        q.c("BackupService", "decryptFile(): File " + file.getName() + " doesn't exist");
    }
}
