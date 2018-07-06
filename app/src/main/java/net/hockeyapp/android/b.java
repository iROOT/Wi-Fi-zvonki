package net.hockeyapp.android;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;
import android.util.Log;
import com.mavenir.android.vtow.activation.ActivationAdapter;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.Thread.UncaughtExceptionHandler;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.hockeyapp.android.c.a;
import net.hockeyapp.android.e.d;
import net.hockeyapp.android.e.f;
import net.hockeyapp.android.e.h;

public class b {
    private static final String ALWAYS_SEND_KEY = "always_send_crash_reports";
    private static String identifier = null;
    private static boolean submitting = false;
    private static String urlString = null;

    public static void register(Context context, String str) {
        register(context, a.BASE_URL, str, null);
    }

    public static void register(Context context, String str, c cVar) {
        register(context, a.BASE_URL, str, cVar);
    }

    public static void register(Context context, String str, String str2, c cVar) {
        initialize(context, str, str2, cVar, false);
        execute(context, cVar);
    }

    public static void initialize(Context context, String str, c cVar) {
        initialize(context, a.BASE_URL, str, cVar, true);
    }

    public static void initialize(Context context, String str, String str2, c cVar) {
        initialize(context, str, str2, cVar, true);
    }

    public static void execute(Context context, c cVar) {
        boolean z = true;
        boolean z2 = cVar != null && cVar.ignoreDefaultHandler();
        Boolean valueOf = Boolean.valueOf(z2);
        WeakReference weakReference = new WeakReference(context);
        int hasStackTraces = hasStackTraces(weakReference);
        if (hasStackTraces == 1) {
            if (context instanceof Activity) {
                z = false;
            }
            Boolean valueOf2 = Boolean.valueOf(Boolean.valueOf(z).booleanValue() | PreferenceManager.getDefaultSharedPreferences(context).getBoolean(ALWAYS_SEND_KEY, false));
            if (cVar != null) {
                valueOf2 = Boolean.valueOf(Boolean.valueOf(valueOf2.booleanValue() | cVar.shouldAutoUploadCrashes()).booleanValue() | cVar.onCrashesFound());
                cVar.onNewCrashesFound();
            }
            if (valueOf2.booleanValue()) {
                sendCrashes(weakReference, cVar, valueOf.booleanValue());
            } else {
                showDialog(weakReference, cVar, valueOf.booleanValue());
            }
        } else if (hasStackTraces == 2) {
            if (cVar != null) {
                cVar.onConfirmedCrashesFound();
            }
            sendCrashes(weakReference, cVar, valueOf.booleanValue());
        } else {
            registerHandler(weakReference, cVar, valueOf.booleanValue());
        }
    }

    public static int hasStackTraces(WeakReference<Context> weakReference) {
        int i = 0;
        String[] searchForStackTraces = searchForStackTraces();
        List list = null;
        if (searchForStackTraces == null || searchForStackTraces.length <= 0) {
            return 0;
        }
        List asList;
        int length;
        if (weakReference != null) {
            try {
                Context context = (Context) weakReference.get();
                if (context != null) {
                    asList = Arrays.asList(context.getSharedPreferences(a.SDK_NAME, 0).getString("ConfirmedFilenames", "").split("\\|"));
                    list = asList;
                    if (list != null) {
                        return 1;
                    }
                    length = searchForStackTraces.length;
                    while (i < length) {
                        if (!list.contains(searchForStackTraces[i])) {
                            return 1;
                        }
                        i++;
                    }
                    return 2;
                }
            } catch (Exception e) {
            }
        }
        asList = null;
        list = asList;
        if (list != null) {
            return 1;
        }
        length = searchForStackTraces.length;
        while (i < length) {
            if (!list.contains(searchForStackTraces[i])) {
                return 1;
            }
            i++;
        }
        return 2;
    }

    public static void submitStackTraces(WeakReference<Context> weakReference, c cVar) {
        submitStackTraces(weakReference, cVar, null);
    }

    public static void submitStackTraces(WeakReference<Context> weakReference, c cVar, net.hockeyapp.android.c.b bVar) {
        String[] searchForStackTraces = searchForStackTraces();
        Boolean valueOf = Boolean.valueOf(false);
        if (searchForStackTraces != null && searchForStackTraces.length > 0) {
            Log.d("HockeyApp", "Found " + searchForStackTraces.length + " stacktrace(s).");
            for (int i = 0; i < searchForStackTraces.length; i++) {
                HttpURLConnection httpURLConnection = null;
                String contentsOfFile;
                int maxRetryAttempts;
                try {
                    String str = searchForStackTraces[i];
                    String contentsOfFile2 = contentsOfFile(weakReference, str);
                    if (contentsOfFile2.length() > 0) {
                        Object userID;
                        Log.d("HockeyApp", "Transmitting crash data: \n" + contentsOfFile2);
                        contentsOfFile = contentsOfFile(weakReference, str.replace(".stacktrace", ".user"));
                        Object contentsOfFile3 = contentsOfFile(weakReference, str.replace(".stacktrace", ".contact"));
                        String userID2;
                        if (bVar != null) {
                            userID2 = bVar.getUserID();
                            if (userID2 == null || userID2.length() <= 0) {
                                userID2 = contentsOfFile;
                            }
                            contentsOfFile = bVar.getUserEmail();
                            if (contentsOfFile != null && contentsOfFile.length() > 0) {
                                contentsOfFile3 = contentsOfFile;
                            }
                        } else {
                            userID2 = contentsOfFile;
                        }
                        str = contentsOfFile(weakReference, str.replace(".stacktrace", ".description"));
                        Object userDescription = bVar != null ? bVar.getUserDescription() : "";
                        if (str != null && str.length() > 0) {
                            userDescription = (userDescription == null || userDescription.length() <= 0) ? String.format("Log:\n%s", new Object[]{str}) : String.format("%s\n\nLog:\n%s", new Object[]{userDescription, str});
                        }
                        Map hashMap = new HashMap();
                        hashMap.put("raw", contentsOfFile2);
                        hashMap.put("userID", userID2);
                        hashMap.put("contact", contentsOfFile3);
                        hashMap.put("description", userDescription);
                        hashMap.put("sdk", a.SDK_NAME);
                        hashMap.put("sdk_version", a.SDK_VERSION);
                        httpURLConnection = new d(getURLString()).setRequestMethod("POST").writeFormFields(hashMap).build();
                        int responseCode = httpURLConnection.getResponseCode();
                        boolean z = responseCode == ActivationAdapter.OP_CONFIGURATION_DAILY || responseCode == ActivationAdapter.OP_CONFIGURATION_APP_UPDATE;
                        valueOf = Boolean.valueOf(z);
                    }
                    if (httpURLConnection != null) {
                        httpURLConnection.disconnect();
                    }
                    if (valueOf.booleanValue()) {
                        Log.d("HockeyApp", "Transmission succeeded");
                        deleteStackTrace(weakReference, searchForStackTraces[i]);
                        if (cVar != null) {
                            cVar.onCrashesSent();
                            contentsOfFile = searchForStackTraces[i];
                            maxRetryAttempts = cVar.getMaxRetryAttempts();
                            deleteRetryCounter(weakReference, contentsOfFile, maxRetryAttempts);
                        }
                    } else {
                        Log.d("HockeyApp", "Transmission failed, will retry on next register() call");
                        if (cVar != null) {
                            cVar.onCrashesNotSent();
                            contentsOfFile = searchForStackTraces[i];
                            maxRetryAttempts = cVar.getMaxRetryAttempts();
                            updateRetryCounter(weakReference, contentsOfFile, maxRetryAttempts);
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    if (httpURLConnection != null) {
                        httpURLConnection.disconnect();
                    }
                    if (valueOf.booleanValue()) {
                        Log.d("HockeyApp", "Transmission succeeded");
                        deleteStackTrace(weakReference, searchForStackTraces[i]);
                        if (cVar != null) {
                            cVar.onCrashesSent();
                            contentsOfFile = searchForStackTraces[i];
                            maxRetryAttempts = cVar.getMaxRetryAttempts();
                        }
                    } else {
                        Log.d("HockeyApp", "Transmission failed, will retry on next register() call");
                        if (cVar != null) {
                            cVar.onCrashesNotSent();
                            contentsOfFile = searchForStackTraces[i];
                            maxRetryAttempts = cVar.getMaxRetryAttempts();
                        }
                    }
                } catch (Throwable th) {
                    if (httpURLConnection != null) {
                        httpURLConnection.disconnect();
                    }
                    if (valueOf.booleanValue()) {
                        Log.d("HockeyApp", "Transmission succeeded");
                        deleteStackTrace(weakReference, searchForStackTraces[i]);
                        if (cVar != null) {
                            cVar.onCrashesSent();
                            deleteRetryCounter(weakReference, searchForStackTraces[i], cVar.getMaxRetryAttempts());
                        }
                    } else {
                        Log.d("HockeyApp", "Transmission failed, will retry on next register() call");
                        if (cVar != null) {
                            cVar.onCrashesNotSent();
                            updateRetryCounter(weakReference, searchForStackTraces[i], cVar.getMaxRetryAttempts());
                        }
                    }
                }
            }
        }
    }

    public static void deleteStackTraces(WeakReference<Context> weakReference) {
        String[] searchForStackTraces = searchForStackTraces();
        if (searchForStackTraces != null && searchForStackTraces.length > 0) {
            Log.d("HockeyApp", "Found " + searchForStackTraces.length + " stacktrace(s).");
            for (int i = 0; i < searchForStackTraces.length; i++) {
                if (weakReference != null) {
                    try {
                        Log.d("HockeyApp", "Delete stacktrace " + searchForStackTraces[i] + ".");
                        deleteStackTrace(weakReference, searchForStackTraces[i]);
                        Context context = (Context) weakReference.get();
                        if (context != null) {
                            context.deleteFile(searchForStackTraces[i]);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public static boolean handleUserInput(a aVar, net.hockeyapp.android.c.b bVar, c cVar, WeakReference<Context> weakReference, boolean z) {
        switch (aVar) {
            case CrashManagerUserInputDontSend:
                if (cVar != null) {
                    cVar.onUserDeniedCrashes();
                }
                deleteStackTraces(weakReference);
                registerHandler(weakReference, cVar, z);
                return true;
            case CrashManagerUserInputAlwaysSend:
                Context context = null;
                if (weakReference != null) {
                    context = (Context) weakReference.get();
                }
                if (context == null) {
                    return false;
                }
                PreferenceManager.getDefaultSharedPreferences(context).edit().putBoolean(ALWAYS_SEND_KEY, true).commit();
                sendCrashes(weakReference, cVar, z, bVar);
                return true;
            case CrashManagerUserInputSend:
                sendCrashes(weakReference, cVar, z, bVar);
                return true;
            default:
                return false;
        }
    }

    public static void resetAlwaysSend(WeakReference<Context> weakReference) {
        if (weakReference != null) {
            Context context = (Context) weakReference.get();
            if (context != null) {
                PreferenceManager.getDefaultSharedPreferences(context).edit().remove(ALWAYS_SEND_KEY).commit();
            }
        }
    }

    private static void initialize(Context context, String str, String str2, c cVar, boolean z) {
        if (context != null) {
            urlString = str;
            identifier = h.sanitizeAppIdentifier(str2);
            a.loadFromContext(context);
            if (identifier == null) {
                identifier = a.APP_PACKAGE;
            }
            if (z) {
                boolean z2 = cVar != null && cVar.ignoreDefaultHandler();
                registerHandler(new WeakReference(context), cVar, Boolean.valueOf(z2).booleanValue());
            }
        }
    }

    private static void showDialog(final WeakReference<Context> weakReference, final c cVar, final boolean z) {
        Context context = null;
        if (weakReference != null) {
            context = (Context) weakReference.get();
        }
        if (context != null) {
            if (cVar == null || !cVar.onHandleAlertView()) {
                Builder builder = new Builder(context);
                builder.setTitle(k.get(cVar, 0));
                builder.setMessage(k.get(cVar, 1));
                builder.setNegativeButton(k.get(cVar, 2), new OnClickListener() {
                    public void onClick(DialogInterface dialogInterface, int i) {
                        b.handleUserInput(a.CrashManagerUserInputDontSend, null, cVar, weakReference, z);
                    }
                });
                builder.setNeutralButton(k.get(cVar, 3), new OnClickListener() {
                    public void onClick(DialogInterface dialogInterface, int i) {
                        b.handleUserInput(a.CrashManagerUserInputAlwaysSend, null, cVar, weakReference, z);
                    }
                });
                builder.setPositiveButton(k.get(cVar, 4), new OnClickListener() {
                    public void onClick(DialogInterface dialogInterface, int i) {
                        b.handleUserInput(a.CrashManagerUserInputSend, null, cVar, weakReference, z);
                    }
                });
                builder.create().show();
            }
        }
    }

    private static void sendCrashes(WeakReference<Context> weakReference, c cVar, boolean z) {
        sendCrashes(weakReference, cVar, z, null);
    }

    private static void sendCrashes(final WeakReference<Context> weakReference, final c cVar, boolean z, final net.hockeyapp.android.c.b bVar) {
        saveConfirmedStackTraces(weakReference);
        registerHandler(weakReference, cVar, z);
        if (!submitting) {
            submitting = true;
            new Thread() {
                public void run() {
                    b.submitStackTraces(weakReference, cVar, bVar);
                    b.submitting = false;
                }
            }.start();
        }
    }

    private static void registerHandler(WeakReference<Context> weakReference, c cVar, boolean z) {
        if (a.APP_VERSION == null || a.APP_PACKAGE == null) {
            Log.d("HockeyApp", "Exception handler not set because version or package is null.");
            return;
        }
        UncaughtExceptionHandler defaultUncaughtExceptionHandler = Thread.getDefaultUncaughtExceptionHandler();
        if (defaultUncaughtExceptionHandler != null) {
            Log.d("HockeyApp", "Current handler class = " + defaultUncaughtExceptionHandler.getClass().getName());
        }
        if (defaultUncaughtExceptionHandler instanceof d) {
            ((d) defaultUncaughtExceptionHandler).setListener(cVar);
        } else {
            Thread.setDefaultUncaughtExceptionHandler(new d(defaultUncaughtExceptionHandler, cVar, z));
        }
    }

    private static String getURLString() {
        return urlString + "api/2/apps/" + identifier + "/crashes/";
    }

    private static void updateRetryCounter(WeakReference<Context> weakReference, String str, int i) {
        if (i != -1 && weakReference != null) {
            Context context = (Context) weakReference.get();
            if (context != null) {
                SharedPreferences sharedPreferences = context.getSharedPreferences(a.SDK_NAME, 0);
                Editor edit = sharedPreferences.edit();
                int i2 = sharedPreferences.getInt("RETRY_COUNT: " + str, 0);
                if (i2 >= i) {
                    deleteStackTrace(weakReference, str);
                    deleteRetryCounter(weakReference, str, i);
                    return;
                }
                edit.putInt("RETRY_COUNT: " + str, i2 + 1);
                edit.commit();
            }
        }
    }

    private static void deleteRetryCounter(WeakReference<Context> weakReference, String str, int i) {
        if (weakReference != null) {
            Context context = (Context) weakReference.get();
            if (context != null) {
                Editor edit = context.getSharedPreferences(a.SDK_NAME, 0).edit();
                edit.remove("RETRY_COUNT: " + str);
                edit.commit();
            }
        }
    }

    private static void deleteStackTrace(WeakReference<Context> weakReference, String str) {
        if (weakReference != null) {
            Context context = (Context) weakReference.get();
            if (context != null) {
                context.deleteFile(str);
                context.deleteFile(str.replace(".stacktrace", ".user"));
                context.deleteFile(str.replace(".stacktrace", ".contact"));
                context.deleteFile(str.replace(".stacktrace", ".description"));
            }
        }
    }

    private static String contentsOfFile(WeakReference<Context> weakReference, String str) {
        IOException e;
        Throwable th;
        BufferedReader bufferedReader = null;
        if (weakReference != null) {
            Context context = (Context) weakReference.get();
            if (context != null) {
                StringBuilder stringBuilder = new StringBuilder();
                BufferedReader bufferedReader2;
                try {
                    bufferedReader2 = new BufferedReader(new InputStreamReader(context.openFileInput(str)));
                    while (true) {
                        try {
                            String readLine = bufferedReader2.readLine();
                            if (readLine == null) {
                                break;
                            }
                            stringBuilder.append(readLine);
                            stringBuilder.append(System.getProperty("line.separator"));
                        } catch (FileNotFoundException e2) {
                            bufferedReader = bufferedReader2;
                        } catch (IOException e3) {
                            e = e3;
                        }
                    }
                    if (bufferedReader2 != null) {
                        bufferedReader2.close();
                    }
                } catch (FileNotFoundException e4) {
                    if (bufferedReader != null) {
                        try {
                            bufferedReader.close();
                        } catch (IOException e5) {
                        }
                    }
                    return stringBuilder.toString();
                } catch (IOException e6) {
                    e = e6;
                    bufferedReader2 = null;
                    try {
                        e.printStackTrace();
                        if (bufferedReader2 != null) {
                            bufferedReader2.close();
                        }
                        return stringBuilder.toString();
                    } catch (Throwable th2) {
                        th = th2;
                        if (bufferedReader2 != null) {
                            try {
                                bufferedReader2.close();
                            } catch (IOException e7) {
                            }
                        }
                        throw th;
                    }
                } catch (Throwable th3) {
                    th = th3;
                    bufferedReader2 = null;
                    if (bufferedReader2 != null) {
                        bufferedReader2.close();
                    }
                    throw th;
                }
                return stringBuilder.toString();
            }
        }
        return null;
    }

    private static void saveConfirmedStackTraces(WeakReference<Context> weakReference) {
        if (weakReference != null) {
            Context context = (Context) weakReference.get();
            if (context != null) {
                try {
                    String[] searchForStackTraces = searchForStackTraces();
                    Editor edit = context.getSharedPreferences(a.SDK_NAME, 0).edit();
                    edit.putString("ConfirmedFilenames", joinArray(searchForStackTraces, "|"));
                    f.applyChanges(edit);
                } catch (Exception e) {
                }
            }
        }
    }

    private static String joinArray(String[] strArr, String str) {
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < strArr.length; i++) {
            stringBuffer.append(strArr[i]);
            if (i < strArr.length - 1) {
                stringBuffer.append(str);
            }
        }
        return stringBuffer.toString();
    }

    private static String[] searchForStackTraces() {
        if (a.FILES_PATH != null) {
            Log.d("HockeyApp", "Looking for exceptions in: " + a.FILES_PATH);
            File file = new File(a.FILES_PATH + "/");
            if (file.mkdir() || file.exists()) {
                return file.list(new FilenameFilter() {
                    public boolean accept(File file, String str) {
                        return str.endsWith(".stacktrace");
                    }
                });
            }
            return new String[0];
        }
        Log.d("HockeyApp", "Can't search for exception as file path is null.");
        return null;
    }
}
