package com.mavenir.android.security;

import com.fgmicrotec.mobile.android.fgmag.VoIP.e;
import com.mavenir.android.common.q;
import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.Security;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import org.spongycastle.asn1.ASN1InputStream;
import org.spongycastle.asn1.ASN1Sequence;
import org.spongycastle.asn1.BEROctetString;
import org.spongycastle.asn1.DERIA5String;
import org.spongycastle.asn1.DEROctetString;
import org.spongycastle.asn1.ocsp.OCSPObjectIdentifiers;
import org.spongycastle.asn1.x509.AccessDescription;
import org.spongycastle.asn1.x509.AuthorityInformationAccess;
import org.spongycastle.asn1.x509.GeneralName;
import org.spongycastle.asn1.x509.X509Extension;
import org.spongycastle.asn1.x509.X509Extensions;
import org.spongycastle.jce.provider.BouncyCastleProvider;
import org.spongycastle.ocsp.BasicOCSPResp;
import org.spongycastle.ocsp.CertificateID;
import org.spongycastle.ocsp.CertificateStatus;
import org.spongycastle.ocsp.OCSPReq;
import org.spongycastle.ocsp.OCSPReqGenerator;
import org.spongycastle.ocsp.OCSPResp;
import org.spongycastle.ocsp.SingleResp;

public class d {
    private static OCSPReq a(X509Certificate x509Certificate, BigInteger bigInteger) {
        Security.addProvider(new BouncyCastleProvider());
        try {
            CertificateID certificateID = new CertificateID("1.3.14.3.2.26", x509Certificate, bigInteger);
            OCSPReqGenerator oCSPReqGenerator = new OCSPReqGenerator();
            oCSPReqGenerator.a(certificateID);
            BigInteger valueOf = BigInteger.valueOf(System.currentTimeMillis());
            Vector vector = new Vector();
            Vector vector2 = new Vector();
            vector.add(OCSPObjectIdentifiers.e);
            vector2.add(new X509Extension(false, new BEROctetString(valueOf.toByteArray())));
            oCSPReqGenerator.a(new X509Extensions(vector, vector2));
            return oCSPReqGenerator.a();
        } catch (Throwable e) {
            throw new c("Cannot generate OSCP Request with the given certificate", e);
        }
    }

    private static OCSPResp a(String str, OCSPReq oCSPReq) {
        HttpURLConnection httpURLConnection;
        Throwable e;
        BufferedOutputStream bufferedOutputStream;
        DataOutputStream dataOutputStream;
        FilterOutputStream filterOutputStream;
        OutputStream bufferedOutputStream2;
        OutputStream outputStream;
        BufferedOutputStream outputStream2 = null;
        try {
            OutputStream bufferedOutputStream3;
            byte[] b = oCSPReq.b();
            HttpURLConnection httpURLConnection2 = (HttpURLConnection) new URL(str).openConnection();
            try {
                httpURLConnection2.setReadTimeout(10000);
                httpURLConnection2.setRequestProperty("Content-Type", "application/ocsp-request");
                httpURLConnection2.setRequestProperty("Accept", "application/ocsp-response");
                httpURLConnection2.setDoOutput(true);
                bufferedOutputStream3 = new BufferedOutputStream(httpURLConnection2.getOutputStream());
            } catch (Throwable e2) {
                httpURLConnection = httpURLConnection2;
                e = e2;
                bufferedOutputStream2 = null;
                try {
                    throw new c("Cannot get ocspResponse from url: " + str, e);
                } catch (Throwable th) {
                    e = th;
                    dataOutputStream = filterOutputStream;
                    outputStream2 = bufferedOutputStream2;
                    if (dataOutputStream != null) {
                        try {
                            dataOutputStream.close();
                        } catch (Exception e3) {
                            q.d("OCSPVerification", "getOCSPResponce(): Error closing stream: " + e3);
                        }
                    }
                    if (outputStream2 != null) {
                        try {
                            outputStream2.close();
                        } catch (Exception e32) {
                            q.d("OCSPVerification", "getOCSPResponce(): Error closing stream: " + e32);
                        }
                    }
                    if (httpURLConnection != null) {
                        try {
                            httpURLConnection.disconnect();
                        } catch (Exception e322) {
                            q.d("OCSPVerification", "getOCSPResponce(): Cannot disconnect: " + e322);
                        }
                    }
                    throw e;
                }
            } catch (Throwable e22) {
                dataOutputStream = null;
                httpURLConnection = httpURLConnection2;
                e = e22;
                if (dataOutputStream != null) {
                    dataOutputStream.close();
                }
                if (outputStream2 != null) {
                    outputStream2.close();
                }
                if (httpURLConnection != null) {
                    httpURLConnection.disconnect();
                }
                throw e;
            }
            try {
                FilterOutputStream dataOutputStream2 = new DataOutputStream(bufferedOutputStream3);
                try {
                    dataOutputStream2.write(b);
                    dataOutputStream2.flush();
                    OCSPResp oCSPResp = new OCSPResp((InputStream) httpURLConnection2.getContent());
                    if (dataOutputStream2 != null) {
                        try {
                            dataOutputStream2.close();
                        } catch (Exception e3222) {
                            q.d("OCSPVerification", "getOCSPResponce(): Error closing stream: " + e3222);
                        }
                    }
                    if (bufferedOutputStream3 != null) {
                        try {
                            bufferedOutputStream3.close();
                        } catch (Exception e32222) {
                            q.d("OCSPVerification", "getOCSPResponce(): Error closing stream: " + e32222);
                        }
                    }
                    if (httpURLConnection2 != null) {
                        try {
                            httpURLConnection2.disconnect();
                        } catch (Exception e4) {
                            q.d("OCSPVerification", "getOCSPResponce(): Cannot disconnect: " + e4);
                        }
                    }
                    return oCSPResp;
                } catch (Throwable e222) {
                    filterOutputStream = dataOutputStream2;
                    OutputStream outputStream3 = bufferedOutputStream3;
                    httpURLConnection = httpURLConnection2;
                    e = e222;
                    bufferedOutputStream2 = outputStream3;
                    throw new c("Cannot get ocspResponse from url: " + str, e);
                } catch (Throwable e2222) {
                    outputStream2 = bufferedOutputStream3;
                    httpURLConnection = httpURLConnection2;
                    e = e2222;
                    if (dataOutputStream2 != null) {
                        dataOutputStream2.close();
                    }
                    if (outputStream2 != null) {
                        outputStream2.close();
                    }
                    if (httpURLConnection != null) {
                        httpURLConnection.disconnect();
                    }
                    throw e;
                }
            } catch (Throwable e22222) {
                Throwable th2 = e22222;
                bufferedOutputStream2 = bufferedOutputStream3;
                httpURLConnection = httpURLConnection2;
                e = th2;
                throw new c("Cannot get ocspResponse from url: " + str, e);
            } catch (Throwable e222222) {
                dataOutputStream2 = null;
                outputStream2 = bufferedOutputStream3;
                httpURLConnection = httpURLConnection2;
                e = e222222;
                if (dataOutputStream2 != null) {
                    dataOutputStream2.close();
                }
                if (outputStream2 != null) {
                    outputStream2.close();
                }
                if (httpURLConnection != null) {
                    httpURLConnection.disconnect();
                }
                throw e;
            }
        } catch (IOException e5) {
            e = e5;
            bufferedOutputStream2 = null;
            httpURLConnection = null;
            throw new c("Cannot get ocspResponse from url: " + str, e);
        } catch (Throwable th3) {
            e = th3;
            dataOutputStream2 = null;
            httpURLConnection = null;
            if (dataOutputStream2 != null) {
                dataOutputStream2.close();
            }
            if (outputStream2 != null) {
                outputStream2.close();
            }
            if (httpURLConnection != null) {
                httpURLConnection.disconnect();
            }
            throw e;
        }
    }

    private static List<String> a(X509Certificate x509Certificate) {
        Throwable e;
        byte[] extensionValue = x509Certificate.getExtensionValue(X509Extensions.x.d());
        if (extensionValue == null) {
            throw new c("Certificate doesn't have authority information access points");
        }
        ASN1InputStream aSN1InputStream;
        try {
            aSN1InputStream = new ASN1InputStream(((DEROctetString) new ASN1InputStream(extensionValue).d()).e());
            try {
                AuthorityInformationAccess a = AuthorityInformationAccess.a((ASN1Sequence) aSN1InputStream.d());
                if (aSN1InputStream != null) {
                    try {
                        aSN1InputStream.close();
                    } catch (Exception e2) {
                        q.d("OCSPVerification", "getAIALocations(): Error closing stream: " + e2);
                    }
                }
                List<String> arrayList = new ArrayList();
                for (AccessDescription accessDescription : a.d()) {
                    if (accessDescription.d().d().equalsIgnoreCase("1.3.6.1.5.5.7.48.1")) {
                        GeneralName e3 = accessDescription.e();
                        if (e3.d() == 6) {
                            arrayList.add(DERIA5String.a(e3.e()).a_());
                        }
                    }
                }
                if (!arrayList.isEmpty()) {
                    return arrayList;
                }
                throw new c("Cant get OCSP urls from certificate");
            } catch (IOException e4) {
                e = e4;
                try {
                    throw new c("Cannot read certificate to get OCSP URLs", e);
                } catch (Throwable th) {
                    e = th;
                    if (aSN1InputStream != null) {
                        try {
                            aSN1InputStream.close();
                        } catch (Exception e22) {
                            q.d("OCSPVerification", "getAIALocations(): Error closing stream: " + e22);
                        }
                    }
                    throw e;
                }
            }
        } catch (IOException e5) {
            e = e5;
            aSN1InputStream = null;
            throw new c("Cannot read certificate to get OCSP URLs", e);
        } catch (Throwable th2) {
            e = th2;
            aSN1InputStream = null;
            if (aSN1InputStream != null) {
                aSN1InputStream.close();
            }
            throw e;
        }
    }

    public static e a(X509Certificate x509Certificate, X509Certificate x509Certificate2) {
        try {
            OCSPReq a = a(x509Certificate2, x509Certificate.getSerialNumber());
            for (String str : a(x509Certificate)) {
                OCSPResp a2 = a(str, a);
                q.a("OCSPVerification", "checkRevocationStatus: serviceUrl=" + str + ", ocspResponse=" + a2.a());
                if (6 == a2.a()) {
                    q.a("OCSPVerification", "checkRevocationStatus: UNAUTHORIZED");
                    return e.FGTLS_ERROR_OCSP_UNAUTHORIZED;
                } else if (2 == a2.a()) {
                    q.a("OCSPVerification", "checkRevocationStatus: INTERNAL ERROR");
                    return e.FGTLS_ERROR_OCSP_INTERNAL_ERROR;
                } else if (1 == a2.a()) {
                    q.a("OCSPVerification", "checkRevocationStatus: MALFORMED REQUEST");
                    return e.FGTLS_ERROR_OCSP_MALFORMED_REQUEST;
                } else if (5 == a2.a()) {
                    q.a("OCSPVerification", "checkRevocationStatus: SIGREQUIRED");
                    return e.FGTLS_ERROR_OCSP_SIGREQUIRED;
                } else if (3 == a2.a()) {
                    q.a("OCSPVerification", "checkRevocationStatus: .TRY_LATER");
                    return e.FGTLS_ERROR_OCSP_TRY_LATER;
                } else {
                    BasicOCSPResp basicOCSPResp = (BasicOCSPResp) a2.b();
                    SingleResp[] a3 = basicOCSPResp == null ? null : basicOCSPResp.a();
                    if (a3 == null) {
                        q.a("OCSPVerification", "checkRevocationStatus: responses == null");
                    } else {
                        q.a("OCSPVerification", "checkRevocationStatus: responses count = " + a3.length);
                    }
                    if (a3 != null && a3.length == 1) {
                        if (a3[0].a() == CertificateStatus.a) {
                            q.a("OCSPVerification", "checkRevocationStatus: cert status GOOD, returning OK");
                            return e.FGTLS_OK;
                        }
                        q.a("OCSPVerification", "checkRevocationStatus: cert status not GOOD, returning ERROR_REVOKED");
                        return e.FGTLS_ERROR_REVOKED;
                    }
                }
            }
            q.a("OCSPVerification", "checkRevocationStatus: returning ERROR_GENERAL");
            return e.FGTLS_ERROR_GENERAL;
        } catch (Throwable e) {
            q.c("OCSPVerification", "checkRevocationStatus: exception, returning FGTLS_ERROR_OCSP_NO_RESPONSE", e);
            return e.FGTLS_ERROR_OCSP_NO_RESPONSE;
        }
    }
}
