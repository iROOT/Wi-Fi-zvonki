package com.mavenir.android.services;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import com.mavenir.android.common.q;
import com.mavenir.android.settings.c.c;
import com.mavenir.android.settings.c.p;
import com.mavenir.android.settings.d;
import java.util.ArrayList;
import java.util.Iterator;

public class SupplementaryServicesService extends Service implements a {
    private final IBinder a = new a(this);
    private SupplementaryServicesAdapter b;
    private ArrayList<SupplementaryService> c;
    private b d;
    private Handler e;

    public interface b {
        void a(com.mavenir.android.services.SupplementaryServicesAdapter.a aVar);

        void b(com.mavenir.android.services.SupplementaryServicesAdapter.a aVar);
    }

    public class a extends Binder {
        final /* synthetic */ SupplementaryServicesService a;

        public a(SupplementaryServicesService supplementaryServicesService) {
            this.a = supplementaryServicesService;
        }

        public SupplementaryServicesService a() {
            return this.a;
        }
    }

    public IBinder onBind(Intent intent) {
        q.a("SupplementaryServicesService", "onBind()");
        this.b = new SupplementaryServicesAdapter(this);
        this.b.init();
        this.e = new Handler();
        return this.a;
    }

    public boolean onUnbind(Intent intent) {
        q.a("SupplementaryServicesService", "onUnbind()");
        if (this.b != null) {
            this.b.exit();
        }
        return super.onUnbind(intent);
    }

    public void a(b bVar) {
        this.d = bVar;
    }

    public void a() {
        this.b.setUserInfoReq(p.f(), p.g(), p.h(), c.e(), c.f(), null, false);
    }

    public void a(int i) {
        q.a("SupplementaryServicesService", "setUserInfoCnf()");
    }

    public void b() {
        q.a("SupplementaryServicesService", "getServicesReq()");
        long a = d.a((Context) this, d.b, -1);
        long currentTimeMillis = System.currentTimeMillis() - a;
        if (a == -1 || currentTimeMillis > 7000) {
            this.b.getServicesReq();
        } else {
            this.e.postDelayed(new Runnable(this) {
                final /* synthetic */ SupplementaryServicesService a;

                {
                    this.a = r1;
                }

                public void run() {
                    this.a.b.getServicesReq();
                }
            }, currentTimeMillis);
        }
    }

    public void a(int i, int i2, ArrayList<SupplementaryService> arrayList) {
        q.a("SupplementaryServicesService", "getServicesCnf(): nErrorType: " + com.mavenir.android.services.SupplementaryServicesAdapter.a.values()[i].name() + ", nHttpResponse: " + i2);
        if (i == com.mavenir.android.services.SupplementaryServicesAdapter.a.SS_ERROR_OK.ordinal()) {
            if (arrayList != null) {
                this.c = arrayList;
                Iterator it = arrayList.iterator();
                String str = "";
                while (it.hasNext()) {
                    str = str + ((SupplementaryService) it.next()).toString() + "\n";
                }
                q.a("SupplementaryServicesService", "getServicesCnf(): services count: " + arrayList.size() + "\n" + str);
            } else {
                q.d("SupplementaryServicesService", "getServicesCnf(): returned no services");
            }
        }
        if (this.d != null) {
            this.d.a(com.mavenir.android.services.SupplementaryServicesAdapter.a.values()[i]);
        }
    }

    public void a(SupplementaryService supplementaryService, SupplementaryServiceRule supplementaryServiceRule) {
        int ordinal;
        int ordinal2;
        boolean isActive;
        int noReplyTimerValue;
        boolean z = false;
        q.a("SupplementaryServicesService", "setServiceReq(): serviceType: " + supplementaryService.getServiceType().name() + ", isActive(): " + supplementaryService.isActive() + ", serviceRule: " + (supplementaryServiceRule != null ? supplementaryServiceRule.getRuleType().name() : "n/a"));
        SupplementaryServicesAdapter supplementaryServicesAdapter = this.b;
        int ordinal3 = supplementaryService.getServiceType().ordinal();
        boolean isActive2 = supplementaryService.isActive();
        if (supplementaryServiceRule != null) {
            ordinal = supplementaryServiceRule.getRuleType().ordinal();
        } else {
            ordinal = 0;
        }
        if (supplementaryServiceRule != null) {
            ordinal2 = supplementaryServiceRule.getRuleValueType().ordinal();
        } else {
            ordinal2 = 0;
        }
        if (supplementaryServiceRule != null) {
            isActive = supplementaryServiceRule.isActive();
        } else {
            isActive = false;
        }
        if (supplementaryServiceRule != null) {
            noReplyTimerValue = supplementaryServiceRule.getNoReplyTimerValue();
        } else {
            noReplyTimerValue = 0;
        }
        if (supplementaryServiceRule != null) {
            z = supplementaryServiceRule.isActionAllowed();
        }
        supplementaryServicesAdapter.setServiceReq(ordinal3, isActive2, ordinal, ordinal2, isActive, noReplyTimerValue, z, supplementaryServiceRule != null ? supplementaryServiceRule.getForwardingValue() : "", supplementaryServiceRule != null ? supplementaryServiceRule.getRuleId() : "");
    }

    public void a(int i, int i2) {
        q.a("SupplementaryServicesService", "setServiceCnf(): nErrorType: " + com.mavenir.android.services.SupplementaryServicesAdapter.a.values()[i].name() + ", nHttpResponse: " + i2);
        if (this.d != null) {
            this.d.b(com.mavenir.android.services.SupplementaryServicesAdapter.a.values()[i]);
        }
    }

    public ArrayList<SupplementaryService> c() {
        return this.c;
    }

    public SupplementaryService a(SupplementaryServicesAdapter.d dVar) {
        if (this.c != null) {
            Iterator it = this.c.iterator();
            while (it.hasNext()) {
                SupplementaryService supplementaryService = (SupplementaryService) it.next();
                if (supplementaryService.getServiceType() == dVar) {
                    return supplementaryService;
                }
            }
        }
        return null;
    }

    public SupplementaryServiceRule a(SupplementaryService supplementaryService, com.mavenir.android.services.SupplementaryServicesAdapter.b bVar) {
        if (supplementaryService != null) {
            for (SupplementaryServiceRule supplementaryServiceRule : supplementaryService.getRules()) {
                if (supplementaryServiceRule.getRuleType() == bVar) {
                    return supplementaryServiceRule;
                }
            }
        }
        return null;
    }

    public void b(SupplementaryService supplementaryService, SupplementaryServiceRule supplementaryServiceRule) {
        if (supplementaryService != null && supplementaryServiceRule != null) {
            for (SupplementaryServiceRule supplementaryServiceRule2 : supplementaryService.getRules()) {
                if (supplementaryServiceRule2.getRuleType() == supplementaryServiceRule.getRuleType()) {
                    supplementaryServiceRule2.setIsActive(supplementaryServiceRule.isActive());
                    supplementaryServiceRule2.setActionAllowed(supplementaryServiceRule.isActionAllowed());
                    supplementaryServiceRule2.setForwardingValue(supplementaryServiceRule.getForwardingValue());
                    supplementaryServiceRule2.setNoReplyTimerValue(supplementaryServiceRule.getNoReplyTimerValue());
                }
            }
        }
    }

    public boolean b(SupplementaryServicesAdapter.d dVar) {
        return a(dVar) != null;
    }
}
