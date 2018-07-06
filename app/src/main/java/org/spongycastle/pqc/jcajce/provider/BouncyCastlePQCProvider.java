package org.spongycastle.pqc.jcajce.provider;

import java.security.AccessController;
import java.security.PrivilegedAction;
import java.security.Provider;
import java.util.HashMap;
import java.util.Map;
import org.spongycastle.asn1.ASN1ObjectIdentifier;
import org.spongycastle.jcajce.provider.config.ConfigurableProvider;
import org.spongycastle.jcajce.provider.config.ProviderConfiguration;
import org.spongycastle.jcajce.provider.util.AlgorithmProvider;
import org.spongycastle.jcajce.provider.util.AsymmetricKeyInfoConverter;

public class BouncyCastlePQCProvider extends Provider implements ConfigurableProvider {
    public static String a = "BCPQC";
    public static final ProviderConfiguration b = null;
    private static String c = "BouncyCastle Post-Quantum Security Provider v1.50";
    private static final Map d = new HashMap();
    private static final String[] e = new String[]{"Rainbow", "McEliece"};

    public BouncyCastlePQCProvider() {
        super(a, 1.5d, c);
        AccessController.doPrivileged(new PrivilegedAction(this) {
            final /* synthetic */ BouncyCastlePQCProvider a;

            {
                this.a = r1;
            }

            public Object run() {
                this.a.a();
                return null;
            }
        });
    }

    private void a() {
        a("org.spongycastle.pqc.jcajce.provider.", e);
    }

    private void a(String str, String[] strArr) {
        for (int i = 0; i != strArr.length; i++) {
            Class cls = null;
            try {
                ClassLoader classLoader = getClass().getClassLoader();
                if (classLoader != null) {
                    cls = classLoader.loadClass(str + strArr[i] + "$Mappings");
                } else {
                    cls = Class.forName(str + strArr[i] + "$Mappings");
                }
            } catch (ClassNotFoundException e) {
            }
            if (cls != null) {
                try {
                    ((AlgorithmProvider) cls.newInstance()).a(this);
                } catch (Exception e2) {
                    throw new InternalError("cannot create instance of " + str + strArr[i] + "$Mappings : " + e2);
                }
            }
        }
    }

    public boolean b(String str, String str2) {
        return containsKey(new StringBuilder().append(str).append(".").append(str2).toString()) || containsKey("Alg.Alias." + str + "." + str2);
    }

    public void a(String str, String str2) {
        if (containsKey(str)) {
            throw new IllegalStateException("duplicate provider key (" + str + ") found");
        }
        put(str, str2);
    }

    public void a(ASN1ObjectIdentifier aSN1ObjectIdentifier, AsymmetricKeyInfoConverter asymmetricKeyInfoConverter) {
        d.put(aSN1ObjectIdentifier, asymmetricKeyInfoConverter);
    }
}
