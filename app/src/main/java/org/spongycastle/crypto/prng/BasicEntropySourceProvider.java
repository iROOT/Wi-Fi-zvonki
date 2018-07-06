package org.spongycastle.crypto.prng;

import java.security.SecureRandom;

public class BasicEntropySourceProvider implements EntropySourceProvider {
    private final SecureRandom a;
    private final boolean b;

    /* renamed from: org.spongycastle.crypto.prng.BasicEntropySourceProvider$1 */
    class AnonymousClass1 implements EntropySource {
        final /* synthetic */ int a;
        final /* synthetic */ BasicEntropySourceProvider b;

        public byte[] a() {
            return this.b.a.generateSeed((this.a + 7) / 8);
        }

        public int b() {
            return this.a;
        }
    }

    public BasicEntropySourceProvider(SecureRandom secureRandom, boolean z) {
        this.a = secureRandom;
        this.b = z;
    }
}
