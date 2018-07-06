package net.hockeyapp.android.e;

import android.content.Context;
import android.content.pm.PackageManager.NameNotFoundException;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.Scanner;
import java.util.regex.Pattern;
import net.hockeyapp.android.n;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class j {
    public static final String VERSION_MAX = "99.0";
    private int currentVersionCode;
    private n listener;
    private JSONObject newest;
    private ArrayList<JSONObject> sortedVersions;

    public j(Context context, String str, n nVar) {
        this.listener = nVar;
        loadVersions(context, str);
        sortVersions();
    }

    private void loadVersions(Context context, String str) {
        this.newest = new JSONObject();
        this.sortedVersions = new ArrayList();
        this.currentVersionCode = this.listener.getCurrentVersionCode();
        try {
            JSONArray jSONArray = new JSONArray(str);
            int currentVersionCode = this.listener.getCurrentVersionCode();
            for (int i = 0; i < jSONArray.length(); i++) {
                Object obj;
                JSONObject jSONObject = jSONArray.getJSONObject(i);
                if (jSONObject.getInt("version") > currentVersionCode) {
                    obj = 1;
                } else {
                    obj = null;
                }
                Object obj2;
                if (jSONObject.getInt("version") == currentVersionCode && isNewerThanLastUpdateTime(context, jSONObject.getLong("timestamp"))) {
                    obj2 = 1;
                } else {
                    obj2 = null;
                }
                if (!(obj == null && obj2 == null)) {
                    this.newest = jSONObject;
                    currentVersionCode = jSONObject.getInt("version");
                }
                this.sortedVersions.add(jSONObject);
            }
        } catch (JSONException e) {
        } catch (NullPointerException e2) {
        }
    }

    private void sortVersions() {
        Collections.sort(this.sortedVersions, new Comparator<JSONObject>() {
            public int compare(JSONObject jSONObject, JSONObject jSONObject2) {
                try {
                    return jSONObject.getInt("version") > jSONObject2.getInt("version") ? 0 : 0;
                } catch (JSONException e) {
                } catch (NullPointerException e2) {
                }
            }
        });
    }

    public String getVersionString() {
        return failSafeGetStringFromJSON(this.newest, "shortversion", "") + " (" + failSafeGetStringFromJSON(this.newest, "version", "") + ")";
    }

    public String getFileDateString() {
        return new SimpleDateFormat("dd.MM.yyyy").format(new Date(failSafeGetLongFromJSON(this.newest, "timestamp", 0) * 1000));
    }

    public long getFileSizeBytes() {
        boolean booleanValue = Boolean.valueOf(failSafeGetStringFromJSON(this.newest, "external", "false")).booleanValue();
        long failSafeGetLongFromJSON = failSafeGetLongFromJSON(this.newest, "appsize", 0);
        return (booleanValue && failSafeGetLongFromJSON == 0) ? -1 : failSafeGetLongFromJSON;
    }

    private static String failSafeGetStringFromJSON(JSONObject jSONObject, String str, String str2) {
        try {
            str2 = jSONObject.getString(str);
        } catch (JSONException e) {
        }
        return str2;
    }

    private static long failSafeGetLongFromJSON(JSONObject jSONObject, String str, long j) {
        try {
            j = jSONObject.getLong(str);
        } catch (JSONException e) {
        }
        return j;
    }

    public String getReleaseNotes(boolean z) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<html>");
        stringBuilder.append("<body style='padding: 0px 0px 20px 0px'>");
        Iterator it = this.sortedVersions.iterator();
        int i = 0;
        while (it.hasNext()) {
            JSONObject jSONObject = (JSONObject) it.next();
            if (i > 0) {
                stringBuilder.append(getSeparator());
                if (z) {
                    stringBuilder.append(getRestoreButton(i, jSONObject));
                }
            }
            stringBuilder.append(getVersionLine(i, jSONObject));
            stringBuilder.append(getVersionNotes(i, jSONObject));
            i++;
        }
        stringBuilder.append("</body>");
        stringBuilder.append("</html>");
        return stringBuilder.toString();
    }

    private Object getSeparator() {
        return "<hr style='border-top: 1px solid #c8c8c8; border-bottom: 0px; margin: 40px 10px 0px 10px;' />";
    }

    private String getRestoreButton(int i, JSONObject jSONObject) {
        StringBuilder stringBuilder = new StringBuilder();
        String versionID = getVersionID(jSONObject);
        if (versionID.length() > 0) {
            stringBuilder.append("<a href='restore:" + versionID + "'  style='background: #c8c8c8; color: #000; display: block; float: right; padding: 7px; margin: 0px 10px 10px; text-decoration: none;'>Restore</a>");
        }
        return stringBuilder.toString();
    }

    private String getVersionID(JSONObject jSONObject) {
        String str = "";
        try {
            str = jSONObject.getString("id");
        } catch (JSONException e) {
        }
        return str;
    }

    private String getVersionLine(int i, JSONObject jSONObject) {
        StringBuilder stringBuilder = new StringBuilder();
        int versionCode = getVersionCode(this.newest);
        int versionCode2 = getVersionCode(jSONObject);
        String versionName = getVersionName(jSONObject);
        stringBuilder.append("<div style='padding: 20px 10px 10px;'><strong>");
        if (i == 0) {
            stringBuilder.append("Newest version:");
        } else {
            stringBuilder.append("Version " + versionName + " (" + versionCode2 + "): ");
            if (versionCode2 != versionCode && versionCode2 == this.currentVersionCode) {
                this.currentVersionCode = -1;
                stringBuilder.append("[INSTALLED]");
            }
        }
        stringBuilder.append("</strong></div>");
        return stringBuilder.toString();
    }

    private int getVersionCode(JSONObject jSONObject) {
        int i = 0;
        try {
            i = jSONObject.getInt("version");
        } catch (JSONException e) {
        }
        return i;
    }

    private String getVersionName(JSONObject jSONObject) {
        String str = "";
        try {
            str = jSONObject.getString("shortversion");
        } catch (JSONException e) {
        }
        return str;
    }

    private String getVersionNotes(int i, JSONObject jSONObject) {
        StringBuilder stringBuilder = new StringBuilder();
        String failSafeGetStringFromJSON = failSafeGetStringFromJSON(jSONObject, "notes", "");
        stringBuilder.append("<div style='padding: 0px 10px;'>");
        if (failSafeGetStringFromJSON.trim().length() == 0) {
            stringBuilder.append("<em>No information.</em>");
        } else {
            stringBuilder.append(failSafeGetStringFromJSON);
        }
        stringBuilder.append("</div>");
        return stringBuilder.toString();
    }

    public static int compareVersionStrings(String str, String str2) {
        if (str == null || str2 == null) {
            return 0;
        }
        try {
            Scanner scanner = new Scanner(str.replaceAll("\\-.*", ""));
            Scanner scanner2 = new Scanner(str2.replaceAll("\\-.*", ""));
            scanner.useDelimiter("\\.");
            scanner2.useDelimiter("\\.");
            while (scanner.hasNextInt() && scanner2.hasNextInt()) {
                int nextInt = scanner.nextInt();
                int nextInt2 = scanner2.nextInt();
                if (nextInt < nextInt2) {
                    return -1;
                }
                if (nextInt > nextInt2) {
                    return 1;
                }
            }
            if (scanner.hasNextInt()) {
                return 1;
            }
            if (scanner2.hasNextInt()) {
                return -1;
            }
            return 0;
        } catch (Exception e) {
            return 0;
        }
    }

    public static boolean isNewerThanLastUpdateTime(Context context, long j) {
        if (context == null) {
            return false;
        }
        try {
            if (j > (new File(context.getPackageManager().getApplicationInfo(context.getPackageName(), 0).sourceDir).lastModified() / 1000) + 1800) {
                return true;
            }
            return false;
        } catch (NameNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static String mapGoogleVersion(String str) {
        if (str == null || str.equalsIgnoreCase("L")) {
            return "5.0";
        }
        if (str.equalsIgnoreCase("M")) {
            return "6.0";
        }
        if (Pattern.matches("^[a-zA-Z]+", str)) {
            return VERSION_MAX;
        }
        return str;
    }
}
