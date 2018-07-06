package com.mavenir.android.common;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;

public abstract class WorkerThreadService extends Service {
    protected volatile a a;
    private volatile Looper b;
    private String c;
    private boolean d = false;
    private boolean e = false;

    protected static final class a extends Handler {
        private WorkerThreadService a = null;

        public a(Looper looper, WorkerThreadService workerThreadService) {
            super(looper);
            this.a = workerThreadService;
        }

        public void dispatchMessage(Message message) {
            try {
                super.dispatchMessage(message);
            } catch (Throwable e) {
                q.c("WorkerThreadService", "WorkerHandler.dispatchMessage", e);
            }
        }

        public void handleMessage(Message message) {
            this.a.b(message);
        }
    }

    protected abstract void a();

    protected abstract void a(Intent intent);

    protected abstract void a(Message message);

    public WorkerThreadService(String str) {
        this.c = str;
    }

    public void a(boolean z) {
        this.e = z;
    }

    public void onCreate() {
        super.onCreate();
        HandlerThread handlerThread = new HandlerThread("Worker[" + this.c + "]");
        handlerThread.start();
        q.b("WorkerThreadService", handlerThread.getName() + " started");
        this.b = handlerThread.getLooper();
        this.a = new a(this.b, this);
    }

    public void onStart(Intent intent, int i) {
        if (intent != null) {
            if ("IntentActions.StartServiceReq".equals(intent.getAction())) {
                q.b("WorkerThreadService", this.c + " started indefinitely");
                this.d = true;
            } else if ("IntentActions.StopServiceReq".equals(intent.getAction())) {
                q.b("WorkerThreadService", this.c + " stopping");
                a();
                stopSelf();
            } else {
                Message obtainMessage = this.a.obtainMessage(1);
                obtainMessage.arg1 = i;
                obtainMessage.obj = intent;
                this.a.sendMessage(obtainMessage);
            }
        }
    }

    public int onStartCommand(Intent intent, int i, int i2) {
        onStart(intent, i2);
        if (intent == null || "IntentActions.StopServiceReq".equals(intent.getAction())) {
            return 2;
        }
        if (this.e) {
            return 3;
        }
        if (this.d) {
            return 1;
        }
        return 2;
    }

    public void onDestroy() {
        this.b.quit();
    }

    public IBinder onBind(Intent intent) {
        return null;
    }

    private void b(Message message) {
        if (message.what == 1) {
            Intent intent = (Intent) message.obj;
            if (intent != null) {
                a(intent);
            }
        } else {
            a(message);
        }
        if (!this.d) {
            stopSelf(message.arg1);
        }
    }
}
