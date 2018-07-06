package com.spiritdsp.tsm;

import android.content.Context;
import android.provider.Settings.Secure;
import android.util.Base64;
import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.RequestQueue;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import org.acra.ACRAConstants;
import org.json.JSONException;
import org.json.JSONObject;

class CloudantAnalytics {
    private static final String A1 = "QHd3bGp0bnJofmJjYw==\n";
    private static final String A2 = "Q2NwbWYmZDtLemhhYT5VWF9lXHpPYHVfIGB6W2lo\n";
    private static final String PARTNER_ID = "spirit";
    private static RequestQueue reqQueue;

    CloudantAnalytics() {
    }

    static void OnTsmInit(Context context) {
        try {
            final String requestBody = GenerateJson(Secure.getString(context.getContentResolver(), "android_id"));
            reqQueue = Volley.newRequestQueue(context.getApplicationContext());
            StringRequest request = new StringRequest(1, "https://spiritdsp.cloudant.com/ts", new Listener<String>() {
                public void onResponse(String response) {
                    CloudantAnalytics.reqQueue.stop();
                }
            }, new ErrorListener() {
                public void onErrorResponse(VolleyError error) {
                    String str = null;
                    if (error != null) {
                        try {
                            if (!(error.networkResponse == null || error.networkResponse.data == null)) {
                                str = new String(error.networkResponse.data, "UTF-8");
                                Logging.LogDebugPrint(true, str, new Object[0]);
                                CloudantAnalytics.reqQueue.stop();
                            }
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }
                    }
                    str = "volley fail";
                    Logging.LogDebugPrint(true, str, new Object[0]);
                    CloudantAnalytics.reqQueue.stop();
                }
            }) {
                public byte[] getBody() throws AuthFailureError {
                    return requestBody.getBytes();
                }

                public String getBodyContentType() {
                    return "application/json";
                }

                public Map<String, String> getHeaders() throws AuthFailureError {
                    Map<String, String> res = new HashMap(1);
                    res.put(CloudantAnalytics.gen(CloudantAnalytics.A1), CloudantAnalytics.gen(CloudantAnalytics.A2));
                    return res;
                }
            };
            request.setRetryPolicy(new DefaultRetryPolicy(ACRAConstants.DEFAULT_SOCKET_TIMEOUT, 2, 1.0f));
            reqQueue.add(request);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private static String GenerateJson(String deviceId) throws JSONException {
        JSONObject object = new JSONObject();
        object.put("devId", deviceId);
        object.put("partId", PARTNER_ID);
        object.put("timestamp", System.currentTimeMillis());
        return object.toString();
    }

    private static String gen(String src) {
        byte[] bb = Base64.decode(src, 0);
        byte x = (byte) 1;
        int i = 0;
        while (i < bb.length) {
            byte x2 = (byte) (x + 1);
            bb[i] = (byte) (bb[i] ^ x);
            i++;
            x = x2;
        }
        try {
            return new String(bb, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return "";
        }
    }
}
