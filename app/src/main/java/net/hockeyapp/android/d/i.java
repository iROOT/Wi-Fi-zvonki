package net.hockeyapp.android.d;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.hockeyapp.android.a;
import net.hockeyapp.android.e.d;
import net.hockeyapp.android.e.h;

public class i extends d<Void, Void, HashMap<String, String>> {
    private List<Uri> attachmentUris;
    private Context context;
    private String email;
    private Handler handler;
    private boolean isFetchMessages;
    private int lastMessageId = -1;
    private String name;
    private ProgressDialog progressDialog;
    private boolean showProgressDialog = true;
    private String subject;
    private String text;
    private String token;
    private HttpURLConnection urlConnection;
    private String urlString;

    public i(Context context, String str, String str2, String str3, String str4, String str5, List<Uri> list, String str6, Handler handler, boolean z) {
        this.context = context;
        this.urlString = str;
        this.name = str2;
        this.email = str3;
        this.subject = str4;
        this.text = str5;
        this.attachmentUris = list;
        this.token = str6;
        this.handler = handler;
        this.isFetchMessages = z;
        if (context != null) {
            a.loadFromContext(context);
        }
    }

    public void setShowProgressDialog(boolean z) {
        this.showProgressDialog = z;
    }

    public void setLastMessageId(int i) {
        this.lastMessageId = i;
    }

    public void attach(Context context) {
        this.context = context;
    }

    public void detach() {
        this.context = null;
        this.progressDialog = null;
    }

    protected void onPreExecute() {
        CharSequence charSequence = "Sending feedback..";
        if (this.isFetchMessages) {
            charSequence = "Retrieving discussions...";
        }
        if ((this.progressDialog == null || !this.progressDialog.isShowing()) && this.showProgressDialog) {
            this.progressDialog = ProgressDialog.show(this.context, "", charSequence, true, false);
        }
    }

    protected HashMap<String, String> doInBackground(Void... voidArr) {
        if (this.isFetchMessages && this.token != null) {
            return doGet();
        }
        if (this.isFetchMessages) {
            return null;
        }
        if (this.attachmentUris.isEmpty()) {
            return doPostPut();
        }
        HashMap<String, String> doPostPutWithAttachments = doPostPutWithAttachments();
        String str = (String) doPostPutWithAttachments.get("status");
        if (!(str == null || !str.startsWith("2") || this.context == null)) {
            File file = new File(this.context.getCacheDir(), "HockeyApp");
            if (file.exists()) {
                for (File delete : file.listFiles()) {
                    delete.delete();
                }
            }
        }
        return doPostPutWithAttachments;
    }

    protected void onPostExecute(HashMap<String, String> hashMap) {
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
            if (hashMap != null) {
                bundle.putString("request_type", (String) hashMap.get("type"));
                bundle.putString("feedback_response", (String) hashMap.get("response"));
                bundle.putString("feedback_status", (String) hashMap.get("status"));
            } else {
                bundle.putString("request_type", "unknown");
            }
            message.setData(bundle);
            this.handler.sendMessage(message);
        }
    }

    private HashMap<String, String> doPostPut() {
        HashMap<String, String> hashMap = new HashMap();
        hashMap.put("type", "send");
        HttpURLConnection httpURLConnection = null;
        try {
            Map hashMap2 = new HashMap();
            hashMap2.put("name", this.name);
            hashMap2.put("email", this.email);
            hashMap2.put("subject", this.subject);
            hashMap2.put("text", this.text);
            hashMap2.put("bundle_identifier", a.APP_PACKAGE);
            hashMap2.put("bundle_short_version", a.APP_VERSION_NAME);
            hashMap2.put("bundle_version", a.APP_VERSION);
            hashMap2.put("os_version", a.ANDROID_VERSION);
            hashMap2.put("oem", a.PHONE_MANUFACTURER);
            hashMap2.put("model", a.PHONE_MODEL);
            if (this.token != null) {
                this.urlString += this.token + "/";
            }
            httpURLConnection = new d(this.urlString).setRequestMethod(this.token != null ? "PUT" : "POST").writeFormFields(hashMap2).build();
            httpURLConnection.connect();
            hashMap.put("status", String.valueOf(httpURLConnection.getResponseCode()));
            hashMap.put("response", d.getStringFromConnection(httpURLConnection));
            if (httpURLConnection != null) {
                httpURLConnection.disconnect();
            }
        } catch (IOException e) {
            e.printStackTrace();
            if (httpURLConnection != null) {
                httpURLConnection.disconnect();
            }
        } catch (Throwable th) {
            if (httpURLConnection != null) {
                httpURLConnection.disconnect();
            }
        }
        return hashMap;
    }

    private HashMap<String, String> doPostPutWithAttachments() {
        HashMap<String, String> hashMap = new HashMap();
        hashMap.put("type", "send");
        HttpURLConnection httpURLConnection = null;
        try {
            Map hashMap2 = new HashMap();
            hashMap2.put("name", this.name);
            hashMap2.put("email", this.email);
            hashMap2.put("subject", this.subject);
            hashMap2.put("text", this.text);
            hashMap2.put("bundle_identifier", a.APP_PACKAGE);
            hashMap2.put("bundle_short_version", a.APP_VERSION_NAME);
            hashMap2.put("bundle_version", a.APP_VERSION);
            hashMap2.put("os_version", a.ANDROID_VERSION);
            hashMap2.put("oem", a.PHONE_MANUFACTURER);
            hashMap2.put("model", a.PHONE_MODEL);
            if (this.token != null) {
                this.urlString += this.token + "/";
            }
            httpURLConnection = new d(this.urlString).setRequestMethod(this.token != null ? "PUT" : "POST").writeMultipartData(hashMap2, this.context, this.attachmentUris).build();
            httpURLConnection.connect();
            hashMap.put("status", String.valueOf(httpURLConnection.getResponseCode()));
            hashMap.put("response", d.getStringFromConnection(httpURLConnection));
            if (httpURLConnection != null) {
                httpURLConnection.disconnect();
            }
        } catch (IOException e) {
            e.printStackTrace();
            if (httpURLConnection != null) {
                httpURLConnection.disconnect();
            }
        } catch (Throwable th) {
            if (httpURLConnection != null) {
                httpURLConnection.disconnect();
            }
        }
        return hashMap;
    }

    private HashMap<String, String> doGet() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(this.urlString + h.encodeParam(this.token));
        if (this.lastMessageId != -1) {
            stringBuilder.append("?last_message_id=" + this.lastMessageId);
        }
        HashMap<String, String> hashMap = new HashMap();
        HttpURLConnection httpURLConnection = null;
        try {
            httpURLConnection = new d(stringBuilder.toString()).build();
            hashMap.put("type", "fetch");
            httpURLConnection.connect();
            hashMap.put("status", String.valueOf(httpURLConnection.getResponseCode()));
            hashMap.put("response", d.getStringFromConnection(httpURLConnection));
            if (httpURLConnection != null) {
                httpURLConnection.disconnect();
            }
        } catch (IOException e) {
            e.printStackTrace();
            if (httpURLConnection != null) {
                httpURLConnection.disconnect();
            }
        } catch (Throwable th) {
            if (httpURLConnection != null) {
                httpURLConnection.disconnect();
            }
        }
        return hashMap;
    }
}
