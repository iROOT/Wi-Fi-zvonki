package org.spongycastle.jcajce.spec;

import java.security.spec.AlgorithmParameterSpec;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class SkeinParameterSpec implements AlgorithmParameterSpec {
    private Map a;

    public static class Builder {
        private Map a = new HashMap();
    }

    public SkeinParameterSpec() {
        this(new HashMap());
    }

    private SkeinParameterSpec(Map map) {
        this.a = Collections.unmodifiableMap(map);
    }

    public Map a() {
        return this.a;
    }
}
