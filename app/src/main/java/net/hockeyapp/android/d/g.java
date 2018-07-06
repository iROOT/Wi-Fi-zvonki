package net.hockeyapp.android.d;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import com.mavenir.android.vtow.activation.ActivationAdapter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.util.Map;
import net.hockeyapp.android.a;
import net.hockeyapp.android.e.d;
import net.hockeyapp.android.e.f;
import org.json.JSONException;
import org.json.JSONObject;

public class g extends d<Void, Void, Boolean> {
    private Context context;
    private Handler handler;
    private final int mode;
    private final Map<String, String> params;
    private ProgressDialog progressDialog;
    private boolean showProgressDialog = true;
    private final String urlString;

    public g(Context context, Handler handler, String str, int i, Map<String, String> map) {
        this.context = context;
        this.handler = handler;
        this.urlString = str;
        this.mode = i;
        this.params = map;
        if (context != null) {
            a.loadFromContext(context);
        }
    }

    public void setShowProgressDialog(boolean z) {
        this.showProgressDialog = z;
    }

    public void attach(Context context, Handler handler) {
        this.context = context;
        this.handler = handler;
    }

    public void detach() {
        this.context = null;
        this.handler = null;
        this.progressDialog = null;
    }

    protected void onPreExecute() {
        if ((this.progressDialog == null || !this.progressDialog.isShowing()) && this.showProgressDialog) {
            this.progressDialog = ProgressDialog.show(this.context, "", "Please wait...", true, false);
        }
    }

    protected Boolean doInBackground(Void... voidArr) {
        HttpURLConnection httpURLConnection = null;
        try {
            httpURLConnection = makeRequest(this.mode, this.params);
            httpURLConnection.connect();
            if (httpURLConnection.getResponseCode() == ActivationAdapter.OP_CONFIGURATION_INITIAL) {
                Object stringFromConnection = d.getStringFromConnection(httpURLConnection);
                if (!TextUtils.isEmpty(stringFromConnection)) {
                    Boolean valueOf = Boolean.valueOf(handleResponse(stringFromConnection));
                    if (httpURLConnection == null) {
                        return valueOf;
                    }
                    httpURLConnection.disconnect();
                    return valueOf;
                }
            }
            if (httpURLConnection != null) {
                httpURLConnection.disconnect();
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            if (httpURLConnection != null) {
                httpURLConnection.disconnect();
            }
        } catch (IOException e2) {
            e2.printStackTrace();
            if (httpURLConnection != null) {
                httpURLConnection.disconnect();
            }
        } catch (Throwable th) {
            if (httpURLConnection != null) {
                httpURLConnection.disconnect();
            }
        }
        return Boolean.valueOf(false);
    }

    protected void onPostExecute(Boolean bool) {
        if (this.progressDialog != null) {
            try {
                this.progressDialog.dismiss();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (this.handler != null) {
            Message message = new Message();
            Bundle bundle = new Bundle();
            bundle.putBoolean("success", bool.booleanValue());
            message.setData(bundle);
            this.handler.sendMessage(message);
        }
    }

    private HttpURLConnection makeRequest(int i, Map<String, String> map) {
        if (i == 1) {
            return new d(this.urlString).setRequestMethod("POST").writeFormFields(map).build();
        }
        if (i == 2) {
            return new d(this.urlString).setRequestMethod("POST").setBasicAuthorization((String) map.get("email"), (String) map.get("password")).build();
        }
        if (i == 3) {
            return new d(this.urlString + "?" + ((String) map.get("type")) + "=" + ((String) map.get("id"))).build();
        }
        throw new IllegalArgumentException("Login mode " + i + " not supported.");
    }

    private boolean handleResponse(String str) {
        SharedPreferences sharedPreferences = this.context.getSharedPreferences("net.hockeyapp.android.login", 0);
        try {
            JSONObject jSONObject = new JSONObject(str);
            String string = jSONObject.getString("status");
            if (TextUtils.isEmpty(string)) {
                return false;
            }
            Object string2;
            if (this.mode == 1) {
                if (!string.equals("identified")) {
                    return false;
                }
                string2 = jSONObject.getString("iuid");
                if (TextUtils.isEmpty(string2)) {
                    return false;
                }
                f.applyChanges(sharedPreferences.edit().putString("iuid", string2));
                return true;
            } else if (this.mode == 2) {
                if (!string.equals("authorized")) {
                    return false;
                }
                string2 = jSONObject.getString("auid");
                if (TextUtils.isEmpty(string2)) {
                    return false;
                }
                f.applyChanges(sharedPreferences.edit().putString("auid", string2));
                return true;
            } else if (this.mode != 3) {
                throw new IllegalArgumentException("Login mode " + this.mode + " not supported.");
            } else if (string.equals("validated")) {
                return true;
            } else {
                f.applyChanges(sharedPreferences.edit().remove("iuid").remove("auid"));
                return false;
            }
        } catch (JSONException e) {
            e.printStackTrace();
            return false;
        }
    }
}
