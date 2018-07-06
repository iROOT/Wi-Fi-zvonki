package org.spongycastle.jce.interfaces;

import org.spongycastle.jce.spec.GOST3410PublicKeyParameterSetSpec;

public interface GOST3410Params {
    String a();

    String b();

    String c();

    GOST3410PublicKeyParameterSetSpec d();
}
