package com.mavenir.android.apps.tele2;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import com.mavenir.android.applog.a;
import com.mavenir.android.common.q;
import com.mavenir.android.vtow.activation.ActivationAdapter;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FilenameFilter;
import net.hockeyapp.android.b;
import net.hockeyapp.android.c;
import net.hockeyapp.android.g;
import net.hockeyapp.android.o;

public class FgVoIP extends com.fgmicrotec.mobile.android.fgvoip.FgVoIP {
    public boolean s() {
        return false;
    }

    public boolean m() {
        return false;
    }

    public boolean n() {
        return true;
    }

    public boolean o() {
        return false;
    }

    public boolean p() {
        return true;
    }

    public boolean q() {
        return true;
    }

    public boolean r() {
        return true;
    }

    public boolean t() {
        return true;
    }

    public boolean E() {
        return false;
    }

    public boolean F() {
        return false;
    }

    public boolean S() {
        return false;
    }

    public boolean J() {
        return true;
    }

    public boolean u() {
        return false;
    }

    public boolean v() {
        return true;
    }

    public boolean w() {
        return false;
    }

    public boolean x() {
        return false;
    }

    public boolean H() {
        return false;
    }

    public boolean k() {
        return true;
    }

    public boolean l() {
        return true;
    }

    public boolean R() {
        return true;
    }

    public boolean Q() {
        return false;
    }

    public Intent Y() {
        return new Intent(this, ActivationInitialActivity.class);
    }

    public void a(final Activity activity) {
        if (com.fgmicrotec.mobile.android.fgvoip.FgVoIP.U().v()) {
            g.initialize(activity.getApplicationContext());
            b.register(activity, com.fgmicrotec.mobile.android.fgvoip.FgVoIP.U().ab(), new c(this) {
                final /* synthetic */ FgVoIP b;

                public String getUserID() {
                    return a.j();
                }

                public String getContact() {
                    return "";
                }

                public String getDescription() {
                    return "No description";
                }

                public void onCrashesSent() {
                    super.onCrashesSent();
                    q.a("FgVoIP", "CrashManagerListener: onCrashesSent()");
                }

                public void onCrashesNotSent() {
                    super.onCrashesNotSent();
                    q.a("FgVoIP", "CrashManagerListener: onCrashesNotSent()");
                }

                public void onNewCrashesFound() {
                    super.onNewCrashesFound();
                    q.a("FgVoIP", "CrashManagerListener: onNewCrashesFound()");
                    a();
                }

                public void onUserDeniedCrashes() {
                    super.onUserDeniedCrashes();
                    q.a("FgVoIP", "CrashManagerListener: onUserDeniedCrashes()");
                }

                private void a() {
                    Object e;
                    BufferedReader bufferedReader;
                    Throwable th;
                    FileReader fileReader = null;
                    BufferedReader bufferedReader2;
                    try {
                        FileReader fileReader2;
                        File[] listFiles = activity.getFilesDir().listFiles(new FilenameFilter(this) {
                            final /* synthetic */ AnonymousClass1 a;

                            {
                                this.a = r1;
                            }

                            public boolean accept(File file, String str) {
                                return str.endsWith(".stacktrace");
                            }
                        });
                        if (listFiles == null || listFiles.length <= 0) {
                            q.a("FgVoIP", "CrashManagerListener: readAndLogLastCrashFile(): no crash files found");
                            fileReader2 = null;
                            bufferedReader2 = null;
                        } else {
                            File file = listFiles[0];
                            for (int i = 1; i < listFiles.length; i++) {
                                if (file.lastModified() < listFiles[i].lastModified()) {
                                    file = listFiles[i];
                                }
                            }
                            fileReader2 = new FileReader(file);
                            try {
                                bufferedReader2 = new BufferedReader(fileReader2);
                                try {
                                    StringBuilder stringBuilder = new StringBuilder();
                                    stringBuilder.append("---------- Crash report beginning ----------");
                                    stringBuilder.append(System.getProperty("line.separator"));
                                    while (true) {
                                        String readLine = bufferedReader2.readLine();
                                        if (readLine == null) {
                                            break;
                                        }
                                        stringBuilder.append(readLine);
                                        stringBuilder.append(System.getProperty("line.separator"));
                                    }
                                    stringBuilder.append("------------- Crash report end -------------");
                                    stringBuilder.append(System.getProperty("line.separator"));
                                    q.a("FgVoIP", "CrashManagerListener: readAndLogLastCrashFile(): writting log to file...");
                                    a(stringBuilder);
                                } catch (Exception e2) {
                                    e = e2;
                                    fileReader = fileReader2;
                                    bufferedReader = bufferedReader2;
                                    try {
                                        q.d("FgVoIP", "CrashManagerListener: readAndLogLastCrashFile(): " + e);
                                        if (bufferedReader != null) {
                                            try {
                                                bufferedReader.close();
                                            } catch (Exception e3) {
                                                q.d("FgVoIP", "CrashManagerListener: readAndLogLastCrashFile(): " + e3);
                                                return;
                                            }
                                        }
                                        if (fileReader == null) {
                                            fileReader.close();
                                        }
                                    } catch (Throwable th2) {
                                        th = th2;
                                        bufferedReader2 = bufferedReader;
                                        if (bufferedReader2 != null) {
                                            try {
                                                bufferedReader2.close();
                                            } catch (Exception e4) {
                                                q.d("FgVoIP", "CrashManagerListener: readAndLogLastCrashFile(): " + e4);
                                                throw th;
                                            }
                                        }
                                        if (fileReader != null) {
                                            fileReader.close();
                                        }
                                        throw th;
                                    }
                                } catch (Throwable th3) {
                                    th = th3;
                                    fileReader = fileReader2;
                                    if (bufferedReader2 != null) {
                                        bufferedReader2.close();
                                    }
                                    if (fileReader != null) {
                                        fileReader.close();
                                    }
                                    throw th;
                                }
                            } catch (Exception e5) {
                                e = e5;
                                FileReader fileReader3 = fileReader2;
                                bufferedReader = null;
                                fileReader = fileReader3;
                                q.d("FgVoIP", "CrashManagerListener: readAndLogLastCrashFile(): " + e);
                                if (bufferedReader != null) {
                                    bufferedReader.close();
                                }
                                if (fileReader == null) {
                                    fileReader.close();
                                }
                            } catch (Throwable th4) {
                                th = th4;
                                bufferedReader2 = null;
                                fileReader = fileReader2;
                                if (bufferedReader2 != null) {
                                    bufferedReader2.close();
                                }
                                if (fileReader != null) {
                                    fileReader.close();
                                }
                                throw th;
                            }
                        }
                        if (bufferedReader2 != null) {
                            try {
                                bufferedReader2.close();
                            } catch (Exception e32) {
                                q.d("FgVoIP", "CrashManagerListener: readAndLogLastCrashFile(): " + e32);
                                return;
                            }
                        }
                        if (fileReader2 != null) {
                            fileReader2.close();
                        }
                    } catch (Exception e6) {
                        e = e6;
                        bufferedReader = null;
                        q.d("FgVoIP", "CrashManagerListener: readAndLogLastCrashFile(): " + e);
                        if (bufferedReader != null) {
                            bufferedReader.close();
                        }
                        if (fileReader == null) {
                            fileReader.close();
                        }
                    } catch (Throwable th5) {
                        th = th5;
                        bufferedReader2 = null;
                        if (bufferedReader2 != null) {
                            bufferedReader2.close();
                        }
                        if (fileReader != null) {
                            fileReader.close();
                        }
                        throw th;
                    }
                }

                private void a(final StringBuilder stringBuilder) {
                    new Handler().postDelayed(new Runnable(this) {
                        final /* synthetic */ AnonymousClass1 b;

                        public void run() {
                            a.a(com.fgmicrotec.mobile.android.fgvoip.FgVoIP.U()).a(stringBuilder.toString());
                        }
                    }, 2000);
                }
            });
        }
    }

    public void b(Activity activity) {
        if (com.fgmicrotec.mobile.android.fgvoip.FgVoIP.U().u()) {
            o.register(activity, com.fgmicrotec.mobile.android.fgvoip.FgVoIP.U().ab());
        }
    }

    public void az() {
        int b = com.mavenir.android.settings.c.q.b();
        long c = com.mavenir.android.settings.c.q.c();
        long currentTimeMillis = System.currentTimeMillis();
        if (b > 0 && c > 0 && currentTimeMillis >= com.mavenir.android.vtow.activation.b.a((Context) this).f()) {
            if (com.fgmicrotec.mobile.android.fgvoip.FgVoIP.U().H()) {
                com.mavenir.android.vtow.activation.b.a((Context) this).a((int) ActivationAdapter.OP_CONFIGURATION_DAILY, false);
            } else {
                com.mavenir.android.vtow.activation.b.a((Context) this).a(0, false);
            }
        }
    }

    public void b(String str, String str2) {
        if (k() && str.startsWith("*")) {
            if (com.mavenir.android.settings.c.c.g()) {
                o(str2);
            } else {
                m(str);
            }
        } else if (e(str)) {
            if (x() && at()) {
                e(true);
                l(str2);
                return;
            }
            n(str);
        } else if (!at() || f(str)) {
            m(str);
        } else {
            l(str2);
        }
    }

    public boolean B() {
        return true;
    }
}
