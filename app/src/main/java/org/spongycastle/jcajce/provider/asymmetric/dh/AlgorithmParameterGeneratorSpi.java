package org.spongycastle.jcajce.provider.asymmetric.dh;

import java.security.AlgorithmParameters;
import java.security.InvalidAlgorithmParameterException;
import java.security.SecureRandom;
import java.security.spec.AlgorithmParameterSpec;
import javax.crypto.spec.DHGenParameterSpec;
import javax.crypto.spec.DHParameterSpec;
import net.hockeyapp.android.k;
import org.spongycastle.crypto.generators.DHParametersGenerator;
import org.spongycastle.crypto.params.DHParameters;

public class AlgorithmParameterGeneratorSpi extends java.security.AlgorithmParameterGeneratorSpi {
    protected SecureRandom a;
    protected int b = k.FEEDBACK_FAILED_TITLE_ID;
    private int c = 0;

    protected void engineInit(int i, SecureRandom secureRandom) {
        this.b = i;
        this.a = secureRandom;
    }

    protected void engineInit(AlgorithmParameterSpec algorithmParameterSpec, SecureRandom secureRandom) {
        if (algorithmParameterSpec instanceof DHGenParameterSpec) {
            DHGenParameterSpec dHGenParameterSpec = (DHGenParameterSpec) algorithmParameterSpec;
            this.b = dHGenParameterSpec.getPrimeSize();
            this.c = dHGenParameterSpec.getExponentSize();
            this.a = secureRandom;
            return;
        }
        throw new InvalidAlgorithmParameterException("DH parameter generator requires a DHGenParameterSpec for initialisation");
    }

    protected AlgorithmParameters engineGenerateParameters() {
        DHParametersGenerator dHParametersGenerator = new DHParametersGenerator();
        if (this.a != null) {
            dHParametersGenerator.a(this.b, 20, this.a);
        } else {
            dHParametersGenerator.a(this.b, 20, new SecureRandom());
        }
        DHParameters a = dHParametersGenerator.a();
        try {
            AlgorithmParameters instance = AlgorithmParameters.getInstance("DH", "SC");
            instance.init(new DHParameterSpec(a.a(), a.b(), this.c));
            return instance;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
