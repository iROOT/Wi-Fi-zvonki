package com.mavenir.android.modules;

import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.Message;
import com.fgmicrotec.mobile.android.fgvoip.FgVoIP;
import com.mavenir.android.common.WorkerThreadService;
import com.mavenir.android.common.q;
import java.util.ArrayList;
import java.util.List;

public class ModulesService extends WorkerThreadService {
    private List<a> b = new ArrayList(8);
    private final IBinder c = new a(this);
    private int d = 20;
    private Runnable e = new Runnable(this) {
        final /* synthetic */ ModulesService a;

        {
            this.a = r1;
        }

        public void run() {
            q.a("ModulesService", "starting SDK interface");
            if (FgVoIP.U().am()) {
                this.a.c();
            } else if (this.a.d > 0) {
                q.a("ModulesService", "call service not running yet - waiting");
                this.a.d = this.a.d - 1;
                this.a.a.postDelayed(this.a.e, 500);
            } else {
                q.a("ModulesService", "call service not running yet - will stop trying now");
            }
        }
    };

    public class a extends Binder {
        final /* synthetic */ ModulesService a;

        public a(ModulesService modulesService) {
            this.a = modulesService;
        }
    }

    public ModulesService() {
        super("ModulesService");
        a(false);
    }

    public void onCreate() {
        super.onCreate();
        q.b("ModulesService", "service created");
        b();
    }

    public IBinder onBind(Intent intent) {
        q.a("ModulesService", "onBind: " + intent);
        return this.c;
    }

    public void onDestroy() {
        q.a("ModulesService", "onDestroy()");
    }

    protected void a() {
        q.a("ModulesService", "onStop()");
        this.a.post(new Runnable(this) {
            final /* synthetic */ ModulesService a;

            {
                this.a = r1;
            }

            public void run() {
                this.a.d();
            }
        });
    }

    private void b() {
        q.a("ModulesService", "startSDKInterface");
        if (!FgVoIP.U().am()) {
            q.b("ModulesService", "callService not running, starting it!");
            FgVoIP.U().a("IntentActions.ActionNone");
        }
        this.a.post(this.e);
    }

    private void c() {
        e();
    }

    private void d() {
        q.a("ModulesService", "onServiceStop");
        f();
        q.b("ModulesService", "posting quit message to work thread");
        super.onDestroy();
    }

    private void e() {
        q.a("ModulesService", "initModules");
        FgVoIP.U().a(this.b);
        q.a("ModulesService", "modules: " + this.b.size());
        for (a aVar : this.b) {
            q.a("ModulesService", "- " + aVar.a());
            aVar.a(this, this.a);
        }
        q.a("ModulesService", "modules inited");
    }

    private void f() {
        for (a aVar : this.b) {
            q.a("ModulesService", "stopping module: " + aVar.a());
            aVar.b();
        }
    }

    protected void a(Intent intent) {
        if (intent == null) {
            try {
                q.a("ModulesService", "onHandleIntent: skipping null");
                return;
            } catch (Throwable e) {
                q.c("ModulesService", "onHandleIntent", e);
                return;
            }
        }
        String stringExtra = intent.getStringExtra("EXTRA_MODULE_ID");
        a a = a(stringExtra);
        if (a == null) {
            q.a("ModulesService", "onHandleIntent: skipping - no module: " + intent);
            return;
        }
        q.a("ModulesService", "onHandleIntent: module: " + stringExtra + ", intent: " + intent);
        a.a(intent);
    }

    protected void a(Message message) {
        q.a("ModulesService", "onHandleMessage: " + message);
    }

    public a a(String str) {
        for (a aVar : this.b) {
            if (aVar.a().equals(str)) {
                return aVar;
            }
        }
        return null;
    }
}
