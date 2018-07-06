package com.mavenir.android.security;

import android.os.Build.VERSION;
import com.fgmicrotec.mobile.android.fgmag.VoIP.e;
import com.mavenir.android.common.q;
import com.mavenir.android.common.t.d;
import com.mavenir.android.security.a.a;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.GeneralSecurityException;
import java.security.KeyStore;
import java.security.Principal;
import java.security.cert.CertPathBuilder;
import java.security.cert.CertPathParameters;
import java.security.cert.CertSelector;
import java.security.cert.CertStore;
import java.security.cert.Certificate;
import java.security.cert.CertificateEncodingException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.CollectionCertStoreParameters;
import java.security.cert.PKIXBuilderParameters;
import java.security.cert.TrustAnchor;
import java.security.cert.X509CertSelector;
import java.security.cert.X509Certificate;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;
import org.spongycastle.asn1.x500.style.BCStyle;

public class b {
    private static b a = null;

    private b() {
    }

    public static b a() {
        if (a == null) {
            a = new b();
        }
        return a;
    }

    private String b() {
        String property = System.getProperty("javax.net.ssl.trustStore");
        if (property == null) {
            return System.getProperty("java.home") + File.separator + "etc" + File.separator + "security" + File.separator + "cacerts.bks";
        }
        return property;
    }

    private String c() {
        String property = System.getProperty("javax.net.ssl.trustStorePassword");
        if (property == null) {
            return "changeit";
        }
        return property;
    }

    public Set<X509Certificate> a(Principal principal) {
        BufferedInputStream bufferedInputStream;
        FileInputStream fileInputStream;
        FileInputStream fileInputStream2;
        InputStream fileInputStream22;
        InputStream bufferedInputStream2;
        InputStream inputStream;
        Enumeration aliases;
        X509Certificate x509Certificate;
        Exception e;
        InputStream inputStream2;
        InputStream inputStream3;
        Set<X509Certificate> hashSet;
        BufferedInputStream inputStream32;
        FileInputStream fileInputStream3;
        Throwable th;
        BufferedInputStream bufferedInputStream3 = null;
        Set<X509Certificate> hashSet2 = new HashSet();
        try {
            KeyStore keyStore;
            if (VERSION.SDK_INT >= 14) {
                KeyStore instance = KeyStore.getInstance("AndroidCAStore");
                instance.load(null, null);
                bufferedInputStream = null;
                fileInputStream = null;
                fileInputStream22 = null;
                keyStore = instance;
            } else {
                keyStore = KeyStore.getInstance("BKS");
                InputStream fileInputStream4;
                InputStream bufferedInputStream4;
                try {
                    fileInputStream4 = new FileInputStream(b());
                    try {
                        bufferedInputStream4 = new BufferedInputStream(fileInputStream4);
                        try {
                            keyStore.load(bufferedInputStream4, c().toCharArray());
                            fileInputStream = null;
                            fileInputStream22 = fileInputStream4;
                            fileInputStream4 = bufferedInputStream4;
                        } catch (IOException e2) {
                            try {
                                fileInputStream22 = new FileInputStream(b());
                                try {
                                    bufferedInputStream2 = new BufferedInputStream(fileInputStream22);
                                    try {
                                        keyStore.load(bufferedInputStream2, null);
                                        inputStream = bufferedInputStream2;
                                        bufferedInputStream2 = fileInputStream22;
                                        fileInputStream22 = fileInputStream4;
                                        fileInputStream4 = bufferedInputStream4;
                                        aliases = keyStore.aliases();
                                        while (aliases.hasMoreElements()) {
                                            x509Certificate = (X509Certificate) keyStore.getCertificate((String) aliases.nextElement());
                                            if (!x509Certificate.getSubjectX500Principal().equals(principal)) {
                                                hashSet2.add(x509Certificate);
                                            }
                                        }
                                        if (bufferedInputStream != null) {
                                            try {
                                                bufferedInputStream.close();
                                            } catch (Exception e3) {
                                                q.d("CertificateVerification", "getTrustedRootCert(): unable to close stream: " + e3);
                                            }
                                        }
                                        if (fileInputStream22 != null) {
                                            fileInputStream22.close();
                                        }
                                        if (bufferedInputStream3 != null) {
                                            bufferedInputStream3.close();
                                        }
                                        if (fileInputStream != null) {
                                            fileInputStream.close();
                                        }
                                        return hashSet2;
                                    } catch (Exception e4) {
                                        inputStream = bufferedInputStream2;
                                        bufferedInputStream2 = fileInputStream4;
                                        fileInputStream4 = fileInputStream22;
                                        inputStream2 = bufferedInputStream4;
                                        e3 = e4;
                                        inputStream32 = inputStream2;
                                        try {
                                            q.c("CertificateVerification", e3.getLocalizedMessage(), e3.getCause());
                                            hashSet = new HashSet();
                                            if (inputStream32 != null) {
                                                try {
                                                    inputStream32.close();
                                                } catch (Exception e42) {
                                                    q.d("CertificateVerification", "getTrustedRootCert(): unable to close stream: " + e42);
                                                    return hashSet;
                                                }
                                            }
                                            if (fileInputStream != null) {
                                                fileInputStream.close();
                                            }
                                            if (bufferedInputStream3 != null) {
                                                bufferedInputStream3.close();
                                            }
                                            if (fileInputStream3 != null) {
                                                return hashSet;
                                            }
                                            fileInputStream3.close();
                                            return hashSet;
                                        } catch (Throwable th2) {
                                            th = th2;
                                            fileInputStream22 = fileInputStream;
                                            fileInputStream = fileInputStream3;
                                            bufferedInputStream = inputStream32;
                                            if (bufferedInputStream != null) {
                                                try {
                                                    bufferedInputStream.close();
                                                } catch (Exception e422) {
                                                    q.d("CertificateVerification", "getTrustedRootCert(): unable to close stream: " + e422);
                                                    throw th;
                                                }
                                            }
                                            if (fileInputStream22 != null) {
                                                fileInputStream22.close();
                                            }
                                            if (bufferedInputStream3 != null) {
                                                bufferedInputStream3.close();
                                            }
                                            if (fileInputStream != null) {
                                                fileInputStream.close();
                                            }
                                            throw th;
                                        }
                                    } catch (Throwable th3) {
                                        inputStream = bufferedInputStream2;
                                        bufferedInputStream2 = fileInputStream22;
                                        fileInputStream22 = fileInputStream4;
                                        fileInputStream4 = bufferedInputStream4;
                                        th = th3;
                                        if (bufferedInputStream != null) {
                                            bufferedInputStream.close();
                                        }
                                        if (fileInputStream22 != null) {
                                            fileInputStream22.close();
                                        }
                                        if (bufferedInputStream3 != null) {
                                            bufferedInputStream3.close();
                                        }
                                        if (fileInputStream != null) {
                                            fileInputStream.close();
                                        }
                                        throw th;
                                    }
                                } catch (Exception e4222) {
                                    bufferedInputStream2 = fileInputStream4;
                                    fileInputStream4 = fileInputStream22;
                                    inputStream2 = bufferedInputStream4;
                                    e3 = e4222;
                                    inputStream32 = inputStream2;
                                    q.c("CertificateVerification", e3.getLocalizedMessage(), e3.getCause());
                                    hashSet = new HashSet();
                                    if (inputStream32 != null) {
                                        inputStream32.close();
                                    }
                                    if (fileInputStream != null) {
                                        fileInputStream.close();
                                    }
                                    if (bufferedInputStream3 != null) {
                                        bufferedInputStream3.close();
                                    }
                                    if (fileInputStream3 != null) {
                                        return hashSet;
                                    }
                                    fileInputStream3.close();
                                    return hashSet;
                                } catch (Throwable th32) {
                                    bufferedInputStream2 = fileInputStream22;
                                    fileInputStream22 = fileInputStream4;
                                    fileInputStream4 = bufferedInputStream4;
                                    th = th32;
                                    if (bufferedInputStream != null) {
                                        bufferedInputStream.close();
                                    }
                                    if (fileInputStream22 != null) {
                                        fileInputStream22.close();
                                    }
                                    if (bufferedInputStream3 != null) {
                                        bufferedInputStream3.close();
                                    }
                                    if (fileInputStream != null) {
                                        fileInputStream.close();
                                    }
                                    throw th;
                                }
                            } catch (Exception e42222) {
                                bufferedInputStream2 = fileInputStream4;
                                fileInputStream3 = null;
                                inputStream2 = bufferedInputStream4;
                                e3 = e42222;
                                inputStream32 = inputStream2;
                                q.c("CertificateVerification", e3.getLocalizedMessage(), e3.getCause());
                                hashSet = new HashSet();
                                if (inputStream32 != null) {
                                    inputStream32.close();
                                }
                                if (fileInputStream != null) {
                                    fileInputStream.close();
                                }
                                if (bufferedInputStream3 != null) {
                                    bufferedInputStream3.close();
                                }
                                if (fileInputStream3 != null) {
                                    return hashSet;
                                }
                                fileInputStream3.close();
                                return hashSet;
                            } catch (Throwable th322) {
                                fileInputStream = null;
                                fileInputStream22 = fileInputStream4;
                                fileInputStream4 = bufferedInputStream4;
                                th = th322;
                                if (bufferedInputStream != null) {
                                    bufferedInputStream.close();
                                }
                                if (fileInputStream22 != null) {
                                    fileInputStream22.close();
                                }
                                if (bufferedInputStream3 != null) {
                                    bufferedInputStream3.close();
                                }
                                if (fileInputStream != null) {
                                    fileInputStream.close();
                                }
                                throw th;
                            }
                        }
                    } catch (IOException e5) {
                        bufferedInputStream4 = null;
                        fileInputStream22 = new FileInputStream(b());
                        bufferedInputStream2 = new BufferedInputStream(fileInputStream22);
                        keyStore.load(bufferedInputStream2, null);
                        inputStream = bufferedInputStream2;
                        bufferedInputStream2 = fileInputStream22;
                        fileInputStream22 = fileInputStream4;
                        fileInputStream4 = bufferedInputStream4;
                        aliases = keyStore.aliases();
                        while (aliases.hasMoreElements()) {
                            x509Certificate = (X509Certificate) keyStore.getCertificate((String) aliases.nextElement());
                            if (!x509Certificate.getSubjectX500Principal().equals(principal)) {
                                hashSet2.add(x509Certificate);
                            }
                        }
                        if (bufferedInputStream != null) {
                            bufferedInputStream.close();
                        }
                        if (fileInputStream22 != null) {
                            fileInputStream22.close();
                        }
                        if (bufferedInputStream3 != null) {
                            bufferedInputStream3.close();
                        }
                        if (fileInputStream != null) {
                            fileInputStream.close();
                        }
                        return hashSet2;
                    } catch (Exception e6) {
                        e3 = e6;
                        inputStream32 = null;
                        bufferedInputStream2 = fileInputStream4;
                        fileInputStream3 = null;
                        q.c("CertificateVerification", e3.getLocalizedMessage(), e3.getCause());
                        hashSet = new HashSet();
                        if (inputStream32 != null) {
                            inputStream32.close();
                        }
                        if (fileInputStream != null) {
                            fileInputStream.close();
                        }
                        if (bufferedInputStream3 != null) {
                            bufferedInputStream3.close();
                        }
                        if (fileInputStream3 != null) {
                            return hashSet;
                        }
                        fileInputStream3.close();
                        return hashSet;
                    } catch (Throwable th4) {
                        th = th4;
                        fileInputStream = null;
                        fileInputStream22 = fileInputStream4;
                        bufferedInputStream = null;
                        if (bufferedInputStream != null) {
                            bufferedInputStream.close();
                        }
                        if (fileInputStream22 != null) {
                            fileInputStream22.close();
                        }
                        if (bufferedInputStream3 != null) {
                            bufferedInputStream3.close();
                        }
                        if (fileInputStream != null) {
                            fileInputStream.close();
                        }
                        throw th;
                    }
                } catch (IOException e7) {
                    bufferedInputStream4 = null;
                    fileInputStream4 = null;
                    fileInputStream22 = new FileInputStream(b());
                    bufferedInputStream2 = new BufferedInputStream(fileInputStream22);
                    keyStore.load(bufferedInputStream2, null);
                    inputStream = bufferedInputStream2;
                    bufferedInputStream2 = fileInputStream22;
                    fileInputStream22 = fileInputStream4;
                    fileInputStream4 = bufferedInputStream4;
                    aliases = keyStore.aliases();
                    while (aliases.hasMoreElements()) {
                        x509Certificate = (X509Certificate) keyStore.getCertificate((String) aliases.nextElement());
                        if (!x509Certificate.getSubjectX500Principal().equals(principal)) {
                            hashSet2.add(x509Certificate);
                        }
                    }
                    if (bufferedInputStream != null) {
                        bufferedInputStream.close();
                    }
                    if (fileInputStream22 != null) {
                        fileInputStream22.close();
                    }
                    if (bufferedInputStream3 != null) {
                        bufferedInputStream3.close();
                    }
                    if (fileInputStream != null) {
                        fileInputStream.close();
                    }
                    return hashSet2;
                }
            }
            try {
                aliases = keyStore.aliases();
                while (aliases.hasMoreElements()) {
                    x509Certificate = (X509Certificate) keyStore.getCertificate((String) aliases.nextElement());
                    if (!x509Certificate.getSubjectX500Principal().equals(principal)) {
                        hashSet2.add(x509Certificate);
                    }
                }
                if (bufferedInputStream != null) {
                    bufferedInputStream.close();
                }
                if (fileInputStream22 != null) {
                    fileInputStream22.close();
                }
                if (bufferedInputStream3 != null) {
                    bufferedInputStream3.close();
                }
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
                return hashSet2;
            } catch (Exception e8) {
                e3 = e8;
                inputStream32 = bufferedInputStream;
                fileInputStream3 = fileInputStream;
                fileInputStream = fileInputStream22;
                q.c("CertificateVerification", e3.getLocalizedMessage(), e3.getCause());
                hashSet = new HashSet();
                if (inputStream32 != null) {
                    inputStream32.close();
                }
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
                if (bufferedInputStream3 != null) {
                    bufferedInputStream3.close();
                }
                if (fileInputStream3 != null) {
                    return hashSet;
                }
                fileInputStream3.close();
                return hashSet;
            } catch (Throwable th5) {
                th = th5;
                if (bufferedInputStream != null) {
                    bufferedInputStream.close();
                }
                if (fileInputStream22 != null) {
                    fileInputStream22.close();
                }
                if (bufferedInputStream3 != null) {
                    bufferedInputStream3.close();
                }
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
                throw th;
            }
        } catch (Exception e9) {
            e3 = e9;
            inputStream32 = null;
            fileInputStream3 = null;
            fileInputStream = null;
            q.c("CertificateVerification", e3.getLocalizedMessage(), e3.getCause());
            hashSet = new HashSet();
            if (inputStream32 != null) {
                inputStream32.close();
            }
            if (fileInputStream != null) {
                fileInputStream.close();
            }
            if (bufferedInputStream3 != null) {
                bufferedInputStream3.close();
            }
            if (fileInputStream3 != null) {
                return hashSet;
            }
            fileInputStream3.close();
            return hashSet;
        } catch (Throwable th6) {
            th = th6;
            bufferedInputStream = null;
            fileInputStream = null;
            fileInputStream22 = null;
            if (bufferedInputStream != null) {
                bufferedInputStream.close();
            }
            if (fileInputStream22 != null) {
                fileInputStream22.close();
            }
            if (bufferedInputStream3 != null) {
                bufferedInputStream3.close();
            }
            if (fileInputStream != null) {
                fileInputStream.close();
            }
            throw th;
        }
    }

    public Certificate[] a(byte[][] bArr) {
        Certificate[] certificateArr = new Certificate[bArr.length];
        int i = 0;
        while (i < bArr.length) {
            try {
                certificateArr[i] = CertificateFactory.getInstance("X.509").generateCertificate(new ByteArrayInputStream(bArr[i]));
                i++;
            } catch (CertificateException e) {
                q.d("CertificateVerification", "createCertificatesFromByteArray: " + e.getLocalizedMessage());
            }
        }
        return certificateArr;
    }

    public void a(X509Certificate x509Certificate) {
        try {
            q.b("CertificateVerification", "Subject CN: " + com.mavenir.android.security.a.b.a(x509Certificate, BCStyle.f));
            q.b("CertificateVerification", "Subject O: " + com.mavenir.android.security.a.b.a(x509Certificate, BCStyle.c));
            q.b("CertificateVerification", "Issuer CN: " + com.mavenir.android.security.a.b.b(x509Certificate, BCStyle.f));
            q.b("CertificateVerification", "Issuer O: " + com.mavenir.android.security.a.b.b(x509Certificate, BCStyle.c));
            q.b("CertificateVerification", "Version: " + a.a(x509Certificate));
            q.b("CertificateVerification", "Serial number: " + a.b(x509Certificate));
            q.b("CertificateVerification", "Issued on: " + d.a(a.c(x509Certificate)));
            q.b("CertificateVerification", "Expires on: " + d.a(a.d(x509Certificate)));
        } catch (CertificateEncodingException e) {
            q.c("CertificateVerification", "printCertificateInformations(): CertificateEncodingException: " + e.getLocalizedMessage(), e.getCause());
        } catch (Exception e2) {
            q.c("CertificateVerification", "printCertificateInformations(): " + e2.getLocalizedMessage(), e2.getCause());
        }
    }

    public e a(Certificate[] certificateArr, boolean z) {
        X509Certificate x509Certificate = (X509Certificate) certificateArr[0];
        e eVar = e.FGTLS_OK;
        try {
            a(x509Certificate);
            if (x509Certificate.getIssuerDN().equals(x509Certificate.getSubjectDN())) {
                q.d("CertificateVerification", "Certificate self signed");
                return e.FGTLS_ERROR_SELF_SIGNED;
            }
            e eVar2;
            Set hashSet = new HashSet();
            for (Certificate certificate : certificateArr) {
                hashSet.add((X509Certificate) certificate);
            }
            Set a = a(((X509Certificate) certificateArr[certificateArr.length - 1]).getIssuerX500Principal());
            if (!z) {
                eVar2 = eVar;
            } else if (certificateArr.length == 1) {
                eVar2 = d.a(x509Certificate, (X509Certificate) a.iterator().next());
            } else {
                eVar2 = d.a(x509Certificate, (X509Certificate) certificateArr[1]);
            }
            a(x509Certificate, a, hashSet);
            return eVar2;
        } catch (GeneralSecurityException e) {
            q.c("CertificateVerification", "verifyCertificates: ", e.getCause());
            return e.FGTLS_ERROR_NOT_TRUSTED;
        }
    }

    private static void a(X509Certificate x509Certificate, Set<X509Certificate> set, Set<X509Certificate> set2) {
        CertSelector x509CertSelector = new X509CertSelector();
        x509CertSelector.setCertificate(x509Certificate);
        Set hashSet = new HashSet();
        for (X509Certificate trustAnchor : set) {
            hashSet.add(new TrustAnchor(trustAnchor, null));
        }
        CertPathParameters pKIXBuilderParameters = new PKIXBuilderParameters(hashSet, x509CertSelector);
        pKIXBuilderParameters.setRevocationEnabled(false);
        pKIXBuilderParameters.addCertStore(CertStore.getInstance("Collection", new CollectionCertStoreParameters(set2), "BC"));
        CertPathBuilder.getInstance("PKIX", "BC").build(pKIXBuilderParameters);
    }
}
