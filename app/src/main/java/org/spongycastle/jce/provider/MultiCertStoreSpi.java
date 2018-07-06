package org.spongycastle.jce.provider;

import java.security.cert.CRLSelector;
import java.security.cert.CertSelector;
import java.security.cert.CertStore;
import java.security.cert.CertStoreSpi;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import org.spongycastle.jce.MultiCertStoreParameters;

public class MultiCertStoreSpi extends CertStoreSpi {
    private MultiCertStoreParameters a;

    public Collection engineGetCertificates(CertSelector certSelector) {
        Collection arrayList;
        boolean b = this.a.b();
        if (b) {
            arrayList = new ArrayList();
        } else {
            Object arrayList2 = Collections.EMPTY_LIST;
        }
        for (CertStore certificates : this.a.a()) {
            Collection certificates2 = certificates.getCertificates(certSelector);
            if (b) {
                arrayList2.addAll(certificates2);
            } else if (!certificates2.isEmpty()) {
                return certificates2;
            }
        }
        return arrayList2;
    }

    public Collection engineGetCRLs(CRLSelector cRLSelector) {
        Collection arrayList;
        boolean b = this.a.b();
        if (b) {
            arrayList = new ArrayList();
        } else {
            Object arrayList2 = Collections.EMPTY_LIST;
        }
        for (CertStore cRLs : this.a.a()) {
            Collection cRLs2 = cRLs.getCRLs(cRLSelector);
            if (b) {
                arrayList2.addAll(cRLs2);
            } else if (!cRLs2.isEmpty()) {
                return cRLs2;
            }
        }
        return arrayList2;
    }
}
