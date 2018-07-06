package net.hockeyapp.android;

import android.os.Process;
import android.util.Log;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.Thread.UncaughtExceptionHandler;
import java.util.Date;
import java.util.UUID;

public class d implements UncaughtExceptionHandler {
    private UncaughtExceptionHandler defaultExceptionHandler;
    private boolean ignoreDefaultHandler = false;
    private c listener;

    public d(UncaughtExceptionHandler uncaughtExceptionHandler, c cVar, boolean z) {
        this.defaultExceptionHandler = uncaughtExceptionHandler;
        this.ignoreDefaultHandler = z;
        this.listener = cVar;
    }

    public void setListener(c cVar) {
        this.listener = cVar;
    }

    public static void saveException(Throwable th, c cVar) {
        Date date = new Date();
        Writer stringWriter = new StringWriter();
        th.printStackTrace(new PrintWriter(stringWriter));
        try {
            String uuid = UUID.randomUUID().toString();
            String str = a.FILES_PATH + "/" + uuid + ".stacktrace";
            Log.d("HockeyApp", "Writing unhandled exception to: " + str);
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(str));
            bufferedWriter.write("Package: " + a.APP_PACKAGE + "\n");
            bufferedWriter.write("Version Code: " + a.APP_VERSION + "\n");
            bufferedWriter.write("Version Name: " + a.APP_VERSION_NAME + "\n");
            if (cVar == null || cVar.includeDeviceData()) {
                bufferedWriter.write("Android: " + a.ANDROID_VERSION + "\n");
                bufferedWriter.write("Manufacturer: " + a.PHONE_MANUFACTURER + "\n");
                bufferedWriter.write("Model: " + a.PHONE_MODEL + "\n");
            }
            if (a.CRASH_IDENTIFIER != null && (cVar == null || cVar.includeDeviceIdentifier())) {
                bufferedWriter.write("CrashReporter Key: " + a.CRASH_IDENTIFIER + "\n");
            }
            bufferedWriter.write("Date: " + date + "\n");
            bufferedWriter.write("\n");
            bufferedWriter.write(stringWriter.toString());
            bufferedWriter.flush();
            bufferedWriter.close();
            if (cVar != null) {
                writeValueToFile(limitedString(cVar.getUserID()), uuid + ".user");
                writeValueToFile(limitedString(cVar.getContact()), uuid + ".contact");
                writeValueToFile(cVar.getDescription(), uuid + ".description");
            }
        } catch (Throwable e) {
            Log.e("HockeyApp", "Error saving exception stacktrace!\n", e);
        }
    }

    public void uncaughtException(Thread thread, Throwable th) {
        if (a.FILES_PATH == null) {
            this.defaultExceptionHandler.uncaughtException(thread, th);
            return;
        }
        saveException(th, this.listener);
        if (this.ignoreDefaultHandler) {
            Process.killProcess(Process.myPid());
            System.exit(10);
            return;
        }
        this.defaultExceptionHandler.uncaughtException(thread, th);
    }

    private static void writeValueToFile(String str, String str2) {
        try {
            String str3 = a.FILES_PATH + "/" + str2;
            if (str.trim().length() > 0) {
                BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(str3));
                bufferedWriter.write(str);
                bufferedWriter.flush();
                bufferedWriter.close();
            }
        } catch (Exception e) {
        }
    }

    private static String limitedString(String str) {
        if (str == null || str.length() <= 255) {
            return str;
        }
        return str.substring(0, 255);
    }
}
