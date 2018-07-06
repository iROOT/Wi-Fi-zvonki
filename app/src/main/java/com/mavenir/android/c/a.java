package com.mavenir.android.c;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.ConnectivityManager.NetworkCallback;
import android.net.NetworkInfo.State;
import android.os.AsyncTask;
import android.os.Handler;
import com.mavenir.android.common.q;
import com.mavenir.android.common.t.e;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

public class a {
    private ConnectivityManager a = null;
    private NetworkCallback b = null;
    private List<String> c = null;
    private Handler d = null;
    private c e;
    private Runnable f = new Runnable(this) {
        final /* synthetic */ a a;

        {
            this.a = r1;
        }

        public void run() {
            q.a("HighPriorityMobileNetwork", "handleActivateHipri Periodic HIPRI renewal handler after 50sec");
            new a(this.a).execute(new Void[0]);
        }
    };

    private class a extends AsyncTask<Void, Void, Boolean> {
        final /* synthetic */ a a;

        protected /* synthetic */ Object doInBackground(Object[] objArr) {
            return a((Void[]) objArr);
        }

        protected /* synthetic */ void onPostExecute(Object obj) {
            a((Boolean) obj);
        }

        public a(a aVar) {
            this.a = aVar;
        }

        protected void onPreExecute() {
            q.a("HighPriorityMobileNetwork", "ActivateHipriAsyncTask: onPreExecute()");
            super.onPreExecute();
        }

        protected Boolean a(Void... voidArr) {
            q.a("HighPriorityMobileNetwork", "ActivateHipriAsyncTask: doInBackground()");
            this.a.e.a(b.CONNECTING);
            return Boolean.valueOf(this.a.b());
        }

        protected void a(Boolean bool) {
            super.onPostExecute(bool);
            q.a("HighPriorityMobileNetwork", "ActivateHipriAsyncTask: onPostExecute(): " + bool);
            this.a.e.a(bool.booleanValue() ? b.CONNECTED : b.DISCONNECTED);
        }
    }

    public enum b {
        DISCONNECTED,
        DISCONNECTING,
        CONNECTING,
        CONNECTED
    }

    public interface c {
        void a(b bVar);
    }

    public a(Context context, c cVar) {
        this.a = (ConnectivityManager) context.getSystemService("connectivity");
        this.d = new Handler();
        this.e = cVar;
    }

    public void a(List<String> list) {
        this.c = list;
        new a(this).execute(new Void[0]);
    }

    public void a() {
        c();
    }

    private boolean b() {
        boolean z = false;
        q.a("HighPriorityMobileNetwork", "activate(): activating HIPRI");
        if (this.a != null) {
            int startUsingNetworkFeature = this.a.startUsingNetworkFeature(0, "enableHIPRI");
            q.b("HighPriorityMobileNetwork", "activate(): HIPRI " + (startUsingNetworkFeature >= 0 ? "enabled" : "disabled"));
            if (startUsingNetworkFeature != -1) {
                State state = State.UNKNOWN;
                startUsingNetworkFeature = 0;
                while (startUsingNetworkFeature < 20) {
                    try {
                        state = this.a.getNetworkInfo(5).getState();
                        if (state.compareTo(State.CONNECTED) == 0) {
                            q.b("HighPriorityMobileNetwork", "activate(): current HIPRI State = " + state);
                            break;
                        }
                        Thread.sleep(500);
                        startUsingNetworkFeature++;
                    } catch (InterruptedException e) {
                    } catch (NullPointerException e2) {
                        q.d("HighPriorityMobileNetwork", "activateHipriOld(): " + e2);
                    }
                }
                if (state == State.CONNECTED) {
                    if (this.c != null && !this.c.isEmpty()) {
                        for (String str : this.c) {
                            URI uri;
                            q.a("HighPriorityMobileNetwork", "destination addrees " + str);
                            try {
                                uri = new URI(str);
                            } catch (URISyntaxException e3) {
                                e3.printStackTrace();
                                uri = null;
                            }
                            String host = uri == null ? str : uri.getHost();
                            q.a("HighPriorityMobileNetwork", "modifeddestination  addrees::  " + host);
                            startUsingNetworkFeature = e.a(host);
                            q.a("HighPriorityMobileNetwork", "hostaddress  int value::  ");
                            if (startUsingNetworkFeature == -1 && uri != null) {
                                q.a("HighPriorityMobileNetwork", "hostaddress == -1 ");
                                break;
                            }
                            boolean z2;
                            boolean requestRouteToHost = this.a.requestRouteToHost(5, startUsingNetworkFeature);
                            q.a("HighPriorityMobileNetwork", "activateHipriOld() destination " + str + "Host address " + startUsingNetworkFeature + "canRoute value " + requestRouteToHost);
                            if (requestRouteToHost) {
                                q.b("HighPriorityMobileNetwork", "activate(): HIPRI routed to: " + str);
                                this.d.postDelayed(this.f, 50000);
                                z2 = true;
                            } else {
                                q.d("HighPriorityMobileNetwork", "activate(): cannot route HIPRI routed to: " + str);
                                z2 = z;
                            }
                            z = z2;
                        }
                    } else {
                        q.d("HighPriorityMobileNetwork", "activate(): Remote host not specified");
                    }
                } else {
                    q.d("HighPriorityMobileNetwork", "activate(): HIPRI connection failed");
                }
            }
        }
        return z;
    }

    private void c() {
        if (this.a != null) {
            this.d.removeCallbacks(this.f);
            if (this.a.getNetworkInfo(5).getState() != State.CONNECTED) {
                return;
            }
            if (this.a.stopUsingNetworkFeature(0, "enableHIPRI") == -1) {
                q.a("HighPriorityMobileNetwork", "deactivate(): failed to deactivate HIPRI");
            } else {
                q.a("HighPriorityMobileNetwork", "deactivate(): HIPRI deactivated");
            }
        }
    }
}
