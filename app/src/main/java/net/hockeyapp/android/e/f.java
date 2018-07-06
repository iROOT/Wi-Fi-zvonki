package net.hockeyapp.android.e;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Build.VERSION;

public class f {
    private SharedPreferences feedbackTokenPrefs;
    private Editor feedbackTokenPrefsEditor;
    private SharedPreferences nameEmailSubjectPrefs;
    private Editor nameEmailSubjectPrefsEditor;

    private static class a {
        public static final f INSTANCE = new f();

        private a() {
        }
    }

    private f() {
    }

    public static f getInstance() {
        return a.INSTANCE;
    }

    public void saveFeedbackTokenToPrefs(Context context, String str) {
        if (context != null) {
            this.feedbackTokenPrefs = context.getSharedPreferences(h.PREFS_FEEDBACK_TOKEN, 0);
            if (this.feedbackTokenPrefs != null) {
                this.feedbackTokenPrefsEditor = this.feedbackTokenPrefs.edit();
                this.feedbackTokenPrefsEditor.putString(h.PREFS_KEY_FEEDBACK_TOKEN, str);
                applyChanges(this.feedbackTokenPrefsEditor);
            }
        }
    }

    public String getFeedbackTokenFromPrefs(Context context) {
        if (context == null) {
            return null;
        }
        this.feedbackTokenPrefs = context.getSharedPreferences(h.PREFS_FEEDBACK_TOKEN, 0);
        if (this.feedbackTokenPrefs != null) {
            return this.feedbackTokenPrefs.getString(h.PREFS_KEY_FEEDBACK_TOKEN, null);
        }
        return null;
    }

    public void saveNameEmailSubjectToPrefs(Context context, String str, String str2, String str3) {
        if (context != null) {
            this.nameEmailSubjectPrefs = context.getSharedPreferences(h.PREFS_NAME_EMAIL_SUBJECT, 0);
            if (this.nameEmailSubjectPrefs != null) {
                this.nameEmailSubjectPrefsEditor = this.nameEmailSubjectPrefs.edit();
                if (str == null || str2 == null || str3 == null) {
                    this.nameEmailSubjectPrefsEditor.putString(h.PREFS_KEY_NAME_EMAIL_SUBJECT, null);
                } else {
                    this.nameEmailSubjectPrefsEditor.putString(h.PREFS_KEY_NAME_EMAIL_SUBJECT, String.format("%s|%s|%s", new Object[]{str, str2, str3}));
                }
                applyChanges(this.nameEmailSubjectPrefsEditor);
            }
        }
    }

    public String getNameEmailFromPrefs(Context context) {
        if (context == null) {
            return null;
        }
        this.nameEmailSubjectPrefs = context.getSharedPreferences(h.PREFS_NAME_EMAIL_SUBJECT, 0);
        if (this.nameEmailSubjectPrefs != null) {
            return this.nameEmailSubjectPrefs.getString(h.PREFS_KEY_NAME_EMAIL_SUBJECT, null);
        }
        return null;
    }

    public static void applyChanges(Editor editor) {
        if (applySupported().booleanValue()) {
            editor.apply();
        } else {
            editor.commit();
        }
    }

    public static Boolean applySupported() {
        try {
            boolean z;
            if (VERSION.SDK_INT >= 9) {
                z = true;
            } else {
                z = false;
            }
            return Boolean.valueOf(z);
        } catch (NoClassDefFoundError e) {
            return Boolean.valueOf(false);
        }
    }
}
