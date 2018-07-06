package org.spongycastle.asn1.x509;

import java.text.ParseException;
import java.util.Date;
import org.spongycastle.asn1.ASN1Choice;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.DERGeneralizedTime;
import org.spongycastle.asn1.DERUTCTime;

public class Time extends ASN1Object implements ASN1Choice {
    ASN1Primitive a;

    public Time(ASN1Primitive aSN1Primitive) {
        if ((aSN1Primitive instanceof DERUTCTime) || (aSN1Primitive instanceof DERGeneralizedTime)) {
            this.a = aSN1Primitive;
            return;
        }
        throw new IllegalArgumentException("unknown object passed to Time");
    }

    public static Time a(Object obj) {
        if (obj == null || (obj instanceof Time)) {
            return (Time) obj;
        }
        if (obj instanceof DERUTCTime) {
            return new Time((DERUTCTime) obj);
        }
        if (obj instanceof DERGeneralizedTime) {
            return new Time((DERGeneralizedTime) obj);
        }
        throw new IllegalArgumentException("unknown object in factory: " + obj.getClass().getName());
    }

    public String d() {
        if (this.a instanceof DERUTCTime) {
            return ((DERUTCTime) this.a).f();
        }
        return ((DERGeneralizedTime) this.a).d();
    }

    public Date e() {
        try {
            if (this.a instanceof DERUTCTime) {
                return ((DERUTCTime) this.a).d();
            }
            return ((DERGeneralizedTime) this.a).e();
        } catch (ParseException e) {
            throw new IllegalStateException("invalid date string: " + e.getMessage());
        }
    }

    public ASN1Primitive a() {
        return this.a;
    }

    public String toString() {
        return d();
    }
}
