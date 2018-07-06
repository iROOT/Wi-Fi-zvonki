package org.spongycastle.math.ec;

import java.math.BigInteger;
import net.hockeyapp.android.k;

public abstract class ECPoint {
    protected static ECFieldElement[] a = new ECFieldElement[0];
    protected ECCurve b;
    protected ECFieldElement c;
    protected ECFieldElement d;
    protected ECFieldElement[] e;
    protected boolean f;
    protected PreCompInfo g;

    public static class F2m extends ECPoint {
        public F2m(ECCurve eCCurve, ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2) {
            this(eCCurve, eCFieldElement, eCFieldElement2, false);
        }

        public F2m(ECCurve eCCurve, ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2, boolean z) {
            super(eCCurve, eCFieldElement, eCFieldElement2);
            if ((eCFieldElement == null || eCFieldElement2 != null) && (eCFieldElement != null || eCFieldElement2 == null)) {
                if (eCFieldElement != null) {
                    org.spongycastle.math.ec.ECFieldElement.F2m.a(this.c, this.d);
                    if (eCCurve != null) {
                        org.spongycastle.math.ec.ECFieldElement.F2m.a(this.c, this.b.f());
                    }
                }
                this.f = z;
                return;
            }
            throw new IllegalArgumentException("Exactly one of the field elements is null");
        }

        F2m(ECCurve eCCurve, ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2, ECFieldElement[] eCFieldElementArr, boolean z) {
            super(eCCurve, eCFieldElement, eCFieldElement2, eCFieldElementArr);
            this.f = z;
        }

        public ECFieldElement f() {
            int b = b();
            switch (b) {
                case 5:
                case 6:
                    if (l() || this.c.i()) {
                        return this.d;
                    }
                    ECFieldElement eCFieldElement = this.c;
                    eCFieldElement = this.d.b(eCFieldElement).c(eCFieldElement);
                    if (6 != b) {
                        return eCFieldElement;
                    }
                    ECFieldElement eCFieldElement2 = this.e[0];
                    if (eCFieldElement2.h() != 1) {
                        return eCFieldElement.d(eCFieldElement2);
                    }
                    return eCFieldElement;
                default:
                    return this.d;
            }
        }

        protected boolean n() {
            ECFieldElement g = g();
            if (g.i()) {
                return false;
            }
            ECFieldElement h = h();
            switch (b()) {
                case 5:
                case 6:
                    return h.b(g).j();
                default:
                    return h.d(g).j();
            }
        }

        private static void a(ECPoint eCPoint, ECPoint eCPoint2) {
            if (eCPoint.b != eCPoint2.b) {
                throw new IllegalArgumentException("Only points on the same curve can be added or subtracted");
            }
        }

        public ECPoint b(ECPoint eCPoint) {
            a(this, eCPoint);
            return a((F2m) eCPoint);
        }

        public F2m a(F2m f2m) {
            if (l()) {
                return f2m;
            }
            if (f2m.l()) {
                return this;
            }
            ECCurve a = a();
            int h = a.h();
            ECFieldElement eCFieldElement = this.c;
            ECFieldElement eCFieldElement2 = f2m.c;
            ECFieldElement eCFieldElement3;
            ECFieldElement eCFieldElement4;
            ECFieldElement a2;
            ECFieldElement eCFieldElement5;
            ECFieldElement eCFieldElement6;
            ECFieldElement c;
            ECFieldElement b;
            switch (h) {
                case 0:
                    eCFieldElement3 = this.d;
                    eCFieldElement4 = f2m.d;
                    if (!eCFieldElement.equals(eCFieldElement2)) {
                        a2 = eCFieldElement.a(eCFieldElement2);
                        eCFieldElement4 = eCFieldElement3.a(eCFieldElement4).d(a2);
                        a2 = eCFieldElement4.e().a(eCFieldElement4).a(a2).a(a.f());
                        return new F2m(a, a2, eCFieldElement4.c(eCFieldElement.a(a2)).a(a2).a(eCFieldElement3), this.f);
                    } else if (eCFieldElement3.equals(eCFieldElement4)) {
                        return (F2m) p();
                    } else {
                        return (F2m) a.e();
                    }
                case 1:
                    a2 = this.d;
                    eCFieldElement5 = this.e[0];
                    eCFieldElement4 = f2m.d;
                    eCFieldElement6 = f2m.e[0];
                    Object obj = eCFieldElement6.h() == 1 ? 1 : null;
                    c = eCFieldElement5.c(eCFieldElement4);
                    eCFieldElement4 = obj != null ? a2 : a2.c(eCFieldElement6);
                    ECFieldElement b2 = c.b(eCFieldElement4);
                    ECFieldElement c2 = eCFieldElement5.c(eCFieldElement2);
                    eCFieldElement2 = obj != null ? eCFieldElement : eCFieldElement.c(eCFieldElement6);
                    b = c2.b(eCFieldElement2);
                    if (!c2.equals(eCFieldElement2)) {
                        c = b.e();
                        eCFieldElement2 = obj != null ? eCFieldElement5 : eCFieldElement5.c(eCFieldElement6);
                        eCFieldElement5 = b2.e().a(b2.c(b).a(c.c(a.f()))).c(eCFieldElement2).a(b.c(c));
                        eCFieldElement4 = b.c(eCFieldElement5);
                        a2 = (obj != null ? c : c.c(eCFieldElement6)).c(b2.c(eCFieldElement).a(a2.c(b))).a(eCFieldElement5.c(b2.a(b)));
                        eCFieldElement = c.c(b).c(eCFieldElement2);
                        return new F2m(a, eCFieldElement4, a2, new ECFieldElement[]{eCFieldElement}, this.f);
                    } else if (c.equals(eCFieldElement4)) {
                        return (F2m) p();
                    } else {
                        return (F2m) a.e();
                    }
                case 6:
                    if (eCFieldElement.i()) {
                        return f2m.a(this);
                    }
                    Object obj2;
                    c = this.d;
                    b = this.e[0];
                    eCFieldElement4 = f2m.d;
                    ECFieldElement eCFieldElement7 = f2m.e[0];
                    Object obj3 = b.h() == 1 ? 1 : null;
                    if (obj3 == null) {
                        a2 = eCFieldElement2.c(b);
                        eCFieldElement3 = eCFieldElement4.c(b);
                    } else {
                        eCFieldElement3 = eCFieldElement4;
                        a2 = eCFieldElement2;
                    }
                    if (eCFieldElement7.h() == 1) {
                        obj2 = 1;
                    } else {
                        obj2 = null;
                    }
                    if (obj2 == null) {
                        eCFieldElement6 = eCFieldElement.c(eCFieldElement7);
                        eCFieldElement5 = c.c(eCFieldElement7);
                    } else {
                        eCFieldElement5 = c;
                        eCFieldElement6 = eCFieldElement;
                    }
                    eCFieldElement3 = eCFieldElement5.a(eCFieldElement3);
                    eCFieldElement5 = eCFieldElement6.a(a2);
                    if (!eCFieldElement5.i()) {
                        if (eCFieldElement2.i()) {
                            eCFieldElement3 = f();
                            eCFieldElement4 = eCFieldElement3.a(eCFieldElement4).d(eCFieldElement);
                            a2 = eCFieldElement4.e().a(eCFieldElement4).a(eCFieldElement).a(a.f());
                            eCFieldElement3 = eCFieldElement4.c(eCFieldElement.a(a2)).a(a2).a(eCFieldElement3);
                            if (!a2.i()) {
                                eCFieldElement3 = eCFieldElement3.d(a2).a(a2);
                            }
                            eCFieldElement = a.a(ECConstants.d);
                            eCFieldElement4 = a2;
                            a2 = eCFieldElement3;
                        } else {
                            eCFieldElement2 = eCFieldElement5.e();
                            eCFieldElement4 = eCFieldElement3.c(eCFieldElement6);
                            a2 = eCFieldElement3.c(a2);
                            eCFieldElement3 = eCFieldElement3.c(eCFieldElement2);
                            if (obj2 == null) {
                                eCFieldElement3 = eCFieldElement3.c(eCFieldElement7);
                            }
                            eCFieldElement4 = eCFieldElement4.c(a2);
                            a2 = a2.a(eCFieldElement2).e().a(eCFieldElement3.c(c.a(b)));
                            if (obj3 == null) {
                                eCFieldElement = eCFieldElement3.c(b);
                            } else {
                                eCFieldElement = eCFieldElement3;
                            }
                        }
                        return new F2m(a, eCFieldElement4, a2, new ECFieldElement[]{eCFieldElement}, this.f);
                    } else if (eCFieldElement3.i()) {
                        return (F2m) p();
                    } else {
                        return (F2m) a.e();
                    }
                default:
                    throw new IllegalStateException("unsupported coordinate system");
            }
        }

        public ECPoint c(ECPoint eCPoint) {
            a(this, eCPoint);
            return b((F2m) eCPoint);
        }

        public F2m b(F2m f2m) {
            return f2m.l() ? this : a((F2m) f2m.o());
        }

        public F2m r() {
            if (l()) {
                return this;
            }
            ECCurve a = a();
            int h = a.h();
            ECFieldElement eCFieldElement = this.c;
            switch (h) {
                case 0:
                case 5:
                    return new F2m(a, eCFieldElement.e(), this.d.e(), this.f);
                case 1:
                case 6:
                    ECFieldElement eCFieldElement2 = this.d;
                    ECFieldElement eCFieldElement3 = this.e[0];
                    return new F2m(a, eCFieldElement.e(), eCFieldElement2.e(), new ECFieldElement[]{eCFieldElement3.e()}, this.f);
                default:
                    throw new IllegalStateException("unsupported coordinate system");
            }
        }

        public ECPoint p() {
            if (l()) {
                return this;
            }
            ECCurve a = a();
            ECFieldElement eCFieldElement = this.c;
            if (eCFieldElement.i()) {
                return a.e();
            }
            ECFieldElement a2;
            ECFieldElement a3;
            ECFieldElement eCFieldElement2;
            ECFieldElement c;
            ECFieldElement e;
            ECFieldElement a4;
            switch (a.h()) {
                case 0:
                    a2 = this.d.d(eCFieldElement).a(eCFieldElement);
                    a3 = a2.e().a(a2).a(a.f());
                    return new F2m(a, a3, eCFieldElement.e().a(a3.c(a2.c())), this.f);
                case 1:
                    a2 = this.d;
                    eCFieldElement2 = this.e[0];
                    Object obj = eCFieldElement2.h() == 1 ? 1 : null;
                    c = obj != null ? eCFieldElement : eCFieldElement.c(eCFieldElement2);
                    if (obj == null) {
                        a2 = a2.c(eCFieldElement2);
                    }
                    e = eCFieldElement.e();
                    a2 = e.a(a2);
                    eCFieldElement2 = c.e();
                    a4 = a2.e().a(a2.c(c)).a(a.f().c(eCFieldElement2));
                    a3 = c.c(a4);
                    e = a4.c(a2.a(c)).a(e.e().c(c));
                    eCFieldElement2 = c.c(eCFieldElement2);
                    return new F2m(a, a3, e, new ECFieldElement[]{eCFieldElement2}, this.f);
                case 6:
                    ECFieldElement eCFieldElement3 = this.d;
                    e = this.e[0];
                    Object obj2 = e.h() == 1 ? 1 : null;
                    ECFieldElement c2 = obj2 != null ? eCFieldElement3 : eCFieldElement3.c(e);
                    a2 = obj2 != null ? e : e.e();
                    eCFieldElement2 = a.f();
                    c = obj2 != null ? eCFieldElement2 : eCFieldElement2.c(a2);
                    ECFieldElement a5 = eCFieldElement3.e().a(c2).a(c);
                    a3 = a5.e();
                    a4 = obj2 != null ? a5 : a5.c(a2);
                    if (a.g().h() < (a.a() >> 1)) {
                        e = eCFieldElement3.a(eCFieldElement).e();
                        e = e.a(a5).a(a2).c(e).a(c.e().a(a.g().c(a2.e()))).a(a3).a(eCFieldElement2.c().c(a4));
                    } else {
                        e = (obj2 != null ? eCFieldElement : eCFieldElement.c(e)).e().a(a3).a(a5.c(c2)).a(a4);
                    }
                    return new F2m(a, a3, e, new ECFieldElement[]{a4}, this.f);
                default:
                    throw new IllegalStateException("unsupported coordinate system");
            }
        }

        public ECPoint d(ECPoint eCPoint) {
            if (l()) {
                return eCPoint;
            }
            if (eCPoint.l()) {
                return p();
            }
            ECCurve a = a();
            ECFieldElement eCFieldElement = this.c;
            if (eCFieldElement.i()) {
                return eCPoint;
            }
            switch (a.h()) {
                case 6:
                    ECFieldElement eCFieldElement2 = eCPoint.c;
                    ECFieldElement eCFieldElement3 = eCPoint.e[0];
                    if (eCFieldElement2.i() || eCFieldElement3.h() != 1) {
                        return p().b(eCPoint);
                    }
                    eCFieldElement3 = this.d;
                    ECFieldElement eCFieldElement4 = this.e[0];
                    ECFieldElement eCFieldElement5 = eCPoint.d;
                    eCFieldElement = eCFieldElement.e();
                    ECFieldElement e = eCFieldElement3.e();
                    ECFieldElement e2 = eCFieldElement4.e();
                    eCFieldElement3 = a.f().c(e2).a(e).a(eCFieldElement3.c(eCFieldElement4));
                    eCFieldElement4 = eCFieldElement5.c();
                    eCFieldElement = a.f().a(eCFieldElement4).c(e2).a(e).c(eCFieldElement3).a(eCFieldElement.c(e2));
                    eCFieldElement2 = eCFieldElement2.c(e2);
                    eCFieldElement5 = eCFieldElement2.a(eCFieldElement3).e();
                    return new F2m(a, eCFieldElement.e().c(eCFieldElement2), eCFieldElement.a(eCFieldElement5).e().c(eCFieldElement3).a(eCFieldElement4.c(eCFieldElement.c(eCFieldElement5).c(e2))), new ECFieldElement[]{e}, this.f);
                default:
                    return p().b(eCPoint);
            }
        }

        public ECPoint o() {
            if (l()) {
                return this;
            }
            ECFieldElement eCFieldElement = this.c;
            if (eCFieldElement.i()) {
                return this;
            }
            ECFieldElement eCFieldElement2;
            ECFieldElement eCFieldElement3;
            switch (b()) {
                case 0:
                    return new F2m(this.b, eCFieldElement, this.d.a(eCFieldElement), this.f);
                case 1:
                    eCFieldElement2 = this.d;
                    eCFieldElement3 = this.e[0];
                    return new F2m(this.b, eCFieldElement, eCFieldElement2.a(eCFieldElement), new ECFieldElement[]{eCFieldElement3}, this.f);
                case 5:
                    return new F2m(this.b, eCFieldElement, this.d.c(), this.f);
                case 6:
                    eCFieldElement2 = this.d;
                    eCFieldElement3 = this.e[0];
                    return new F2m(this.b, eCFieldElement, eCFieldElement2.a(eCFieldElement3), new ECFieldElement[]{eCFieldElement3}, this.f);
                default:
                    throw new IllegalStateException("unsupported coordinate system");
            }
        }
    }

    public static class Fp extends ECPoint {
        public Fp(ECCurve eCCurve, ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2) {
            this(eCCurve, eCFieldElement, eCFieldElement2, false);
        }

        public Fp(ECCurve eCCurve, ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2, boolean z) {
            super(eCCurve, eCFieldElement, eCFieldElement2);
            if ((eCFieldElement == null || eCFieldElement2 != null) && (eCFieldElement != null || eCFieldElement2 == null)) {
                this.f = z;
                return;
            }
            throw new IllegalArgumentException("Exactly one of the field elements is null");
        }

        Fp(ECCurve eCCurve, ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2, ECFieldElement[] eCFieldElementArr, boolean z) {
            super(eCCurve, eCFieldElement, eCFieldElement2, eCFieldElementArr);
            this.f = z;
        }

        protected boolean n() {
            return d().j();
        }

        public ECFieldElement a(int i) {
            if (i == 1 && 4 == b()) {
                return r();
            }
            return super.a(i);
        }

        public ECPoint b(ECPoint eCPoint) {
            if (l()) {
                return eCPoint;
            }
            if (eCPoint.l()) {
                return this;
            }
            if (this == eCPoint) {
                return p();
            }
            ECCurve a = a();
            int h = a.h();
            ECFieldElement eCFieldElement = this.c;
            ECFieldElement eCFieldElement2 = this.d;
            ECFieldElement eCFieldElement3 = eCPoint.c;
            ECFieldElement eCFieldElement4 = eCPoint.d;
            ECFieldElement b;
            ECFieldElement b2;
            ECFieldElement eCFieldElement5;
            ECFieldElement eCFieldElement6;
            Object obj;
            switch (h) {
                case 0:
                    b = eCFieldElement3.b(eCFieldElement);
                    b2 = eCFieldElement4.b(eCFieldElement2);
                    if (!b.i()) {
                        b = b2.d(b);
                        b2 = b.e().b(eCFieldElement).b(eCFieldElement3);
                        return new Fp(a, b2, b.c(eCFieldElement.b(b2)).b(eCFieldElement2), this.f);
                    } else if (b2.i()) {
                        return p();
                    } else {
                        return a.e();
                    }
                case 1:
                    eCFieldElement5 = this.e[0];
                    eCFieldElement6 = eCPoint.e[0];
                    Object obj2 = eCFieldElement5.h() == 1 ? 1 : null;
                    obj = eCFieldElement6.h() == 1 ? 1 : null;
                    if (obj2 == null) {
                        eCFieldElement4 = eCFieldElement4.c(eCFieldElement5);
                    }
                    b2 = obj != null ? eCFieldElement2 : eCFieldElement2.c(eCFieldElement6);
                    eCFieldElement4 = eCFieldElement4.b(b2);
                    if (obj2 == null) {
                        eCFieldElement3 = eCFieldElement3.c(eCFieldElement5);
                    }
                    eCFieldElement2 = obj != null ? eCFieldElement : eCFieldElement.c(eCFieldElement6);
                    eCFieldElement = eCFieldElement3.b(eCFieldElement2);
                    if (!eCFieldElement.i()) {
                        b = obj2 != null ? eCFieldElement6 : obj != null ? eCFieldElement5 : eCFieldElement5.c(eCFieldElement6);
                        eCFieldElement6 = eCFieldElement.e();
                        eCFieldElement5 = eCFieldElement6.c(eCFieldElement);
                        eCFieldElement6 = eCFieldElement6.c(eCFieldElement2);
                        eCFieldElement3 = eCFieldElement4.e().c(b).b(eCFieldElement5).b(b(eCFieldElement6));
                        return new Fp(a, eCFieldElement.c(eCFieldElement3), eCFieldElement6.b(eCFieldElement3).c(eCFieldElement4).b(eCFieldElement5.c(b2)), new ECFieldElement[]{eCFieldElement5.c(b)}, this.f);
                    } else if (eCFieldElement4.i()) {
                        return p();
                    } else {
                        return a.e();
                    }
                case 2:
                case 4:
                    ECFieldElement[] eCFieldElementArr;
                    ECFieldElement eCFieldElement7 = this.e[0];
                    ECFieldElement eCFieldElement8 = eCPoint.e[0];
                    if (eCFieldElement7.h() == 1) {
                        obj = 1;
                    } else {
                        obj = null;
                    }
                    if (obj == null && eCFieldElement7.equals(eCFieldElement8)) {
                        eCFieldElement6 = eCFieldElement.b(eCFieldElement3);
                        eCFieldElement4 = eCFieldElement2.b(eCFieldElement4);
                        if (!eCFieldElement6.i()) {
                            b = eCFieldElement6.e();
                            eCFieldElement = eCFieldElement.c(b);
                            eCFieldElement5 = eCFieldElement3.c(b);
                            eCFieldElement2 = eCFieldElement.b(eCFieldElement5).c(eCFieldElement2);
                            eCFieldElement5 = eCFieldElement4.e().b(eCFieldElement).b(eCFieldElement5);
                            eCFieldElement = eCFieldElement.b(eCFieldElement5).c(eCFieldElement4).b(eCFieldElement2);
                            if (obj != null) {
                                eCFieldElement2 = eCFieldElement6;
                            } else {
                                eCFieldElement2 = eCFieldElement6.c(eCFieldElement7);
                                b = null;
                            }
                            eCFieldElement6 = eCFieldElement2;
                            b2 = eCFieldElement;
                            eCFieldElement2 = eCFieldElement5;
                        } else if (eCFieldElement4.i()) {
                            return p();
                        } else {
                            return a.e();
                        }
                    }
                    if (obj != null) {
                        b = eCFieldElement4;
                        eCFieldElement6 = eCFieldElement3;
                    } else {
                        b = eCFieldElement7.e();
                        eCFieldElement6 = b.c(eCFieldElement3);
                        b = b.c(eCFieldElement7).c(eCFieldElement4);
                    }
                    Object obj3 = eCFieldElement8.h() == 1 ? 1 : null;
                    if (obj3 == null) {
                        eCFieldElement5 = eCFieldElement8.e();
                        eCFieldElement = eCFieldElement5.c(eCFieldElement);
                        eCFieldElement2 = eCFieldElement5.c(eCFieldElement8).c(eCFieldElement2);
                    }
                    eCFieldElement5 = eCFieldElement.b(eCFieldElement6);
                    eCFieldElement3 = eCFieldElement2.b(b);
                    if (!eCFieldElement5.i()) {
                        b = eCFieldElement5.e();
                        ECFieldElement c = b.c(eCFieldElement5);
                        eCFieldElement = b.c(eCFieldElement);
                        eCFieldElement6 = eCFieldElement3.e().a(c).b(b(eCFieldElement));
                        eCFieldElement = eCFieldElement.b(eCFieldElement6).c(eCFieldElement3).b(eCFieldElement2.c(c));
                        if (obj == null) {
                            eCFieldElement2 = eCFieldElement5.c(eCFieldElement7);
                        } else {
                            eCFieldElement2 = eCFieldElement5;
                        }
                        if (obj3 == null) {
                            eCFieldElement2 = eCFieldElement2.c(eCFieldElement8);
                        }
                        ECFieldElement eCFieldElement9;
                        if (eCFieldElement2 == eCFieldElement5) {
                            b2 = eCFieldElement;
                            eCFieldElement9 = eCFieldElement2;
                            eCFieldElement2 = eCFieldElement6;
                            eCFieldElement6 = eCFieldElement9;
                        } else {
                            b = null;
                            b2 = eCFieldElement;
                            eCFieldElement9 = eCFieldElement2;
                            eCFieldElement2 = eCFieldElement6;
                            eCFieldElement6 = eCFieldElement9;
                        }
                    } else if (eCFieldElement3.i()) {
                        return p();
                    } else {
                        return a.e();
                    }
                    if (h == 4) {
                        b = b(eCFieldElement6, b);
                        eCFieldElementArr = new ECFieldElement[]{eCFieldElement6, b};
                    } else {
                        eCFieldElementArr = new ECFieldElement[]{eCFieldElement6};
                    }
                    return new Fp(a, eCFieldElement2, b2, eCFieldElementArr, this.f);
                default:
                    throw new IllegalStateException("unsupported coordinate system");
            }
        }

        public ECPoint p() {
            if (l()) {
                return this;
            }
            ECCurve a = a();
            ECFieldElement eCFieldElement = this.d;
            if (eCFieldElement.i()) {
                return a.e();
            }
            int h = a.h();
            ECFieldElement eCFieldElement2 = this.c;
            ECFieldElement d;
            ECFieldElement b;
            Object obj;
            ECFieldElement f;
            ECFieldElement c;
            ECFieldElement c2;
            switch (h) {
                case 0:
                    d = c(eCFieldElement2.e()).a(a().f()).d(b(eCFieldElement));
                    b = d.e().b(b(eCFieldElement2));
                    return new Fp(a, b, d.c(eCFieldElement2.b(b)).b(eCFieldElement), this.f);
                case 1:
                    b = this.e[0];
                    obj = b.h() == 1 ? 1 : null;
                    d = obj != null ? b : b.e();
                    f = a.f();
                    if (obj == null) {
                        d = f.c(d);
                    } else {
                        d = f;
                    }
                    f = d.a(c(eCFieldElement2.e()));
                    c = obj != null ? eCFieldElement : eCFieldElement.c(b);
                    d = obj != null ? eCFieldElement.e() : c.c(eCFieldElement);
                    eCFieldElement = d(eCFieldElement2.c(d));
                    eCFieldElement2 = f.e().b(b(eCFieldElement));
                    b = b(eCFieldElement2.c(c));
                    f = f.c(eCFieldElement.b(eCFieldElement2)).b(b(b(d).e()));
                    c2 = b(obj != null ? d(d) : b(c).e()).c(c);
                    return new Fp(a, b, f, new ECFieldElement[]{c2}, this.f);
                case 2:
                    c = this.e[0];
                    obj = c.h() == 1 ? 1 : null;
                    d = obj != null ? c : c.e();
                    f = eCFieldElement.e();
                    ECFieldElement e = f.e();
                    b = a.f();
                    ECFieldElement d2 = b.d();
                    if (d2.a().equals(BigInteger.valueOf(3))) {
                        b = c(eCFieldElement2.a(d).c(eCFieldElement2.b(d)));
                        d = d(f.c(eCFieldElement2));
                        f = b;
                    } else {
                        ECFieldElement e2 = eCFieldElement2.e();
                        ECFieldElement c3 = c(e2);
                        if (obj != null) {
                            d = c3.a(b);
                        } else {
                            d = d.e();
                            if (d2.h() < b.h()) {
                                d = c3.b(d.c(d2));
                            } else {
                                d = c3.a(d.c(b));
                            }
                        }
                        f = d;
                        d = b(a(eCFieldElement2, f, e2, e));
                    }
                    b = f.e().b(b(d));
                    f = d.b(b).c(f).b(e(e));
                    d = b(eCFieldElement);
                    if (obj == null) {
                        c2 = d.c(c);
                    } else {
                        c2 = d;
                    }
                    return new Fp(a, b, f, new ECFieldElement[]{c2}, this.f);
                case 4:
                    return b(true);
                default:
                    throw new IllegalStateException("unsupported coordinate system");
            }
        }

        public ECPoint d(ECPoint eCPoint) {
            if (this == eCPoint) {
                return q();
            }
            if (l()) {
                return eCPoint;
            }
            if (eCPoint.l()) {
                return p();
            }
            ECFieldElement eCFieldElement = this.d;
            if (eCFieldElement.i()) {
                return eCPoint;
            }
            ECCurve a = a();
            switch (a.h()) {
                case 0:
                    ECFieldElement eCFieldElement2 = this.c;
                    ECFieldElement eCFieldElement3 = eCPoint.c;
                    ECFieldElement eCFieldElement4 = eCPoint.d;
                    ECFieldElement b = eCFieldElement3.b(eCFieldElement2);
                    eCFieldElement4 = eCFieldElement4.b(eCFieldElement);
                    if (b.i()) {
                        return eCFieldElement4.i() ? q() : this;
                    } else {
                        ECFieldElement e = b.e();
                        ECFieldElement b2 = e.c(b(eCFieldElement2).a(eCFieldElement3)).b(eCFieldElement4.e());
                        if (b2.i()) {
                            return a.e();
                        }
                        ECFieldElement f = b2.c(b).f();
                        eCFieldElement4 = b2.c(f).c(eCFieldElement4);
                        b = b(eCFieldElement).c(e).c(b).c(f).b(eCFieldElement4);
                        eCFieldElement3 = b.b(eCFieldElement4).c(eCFieldElement4.a(b)).a(eCFieldElement3);
                        return new Fp(a, eCFieldElement3, eCFieldElement2.b(eCFieldElement3).c(b).b(eCFieldElement), this.f);
                    }
                case 4:
                    return b(false).b(eCPoint);
                default:
                    return p().b(eCPoint);
            }
        }

        public ECPoint q() {
            if (l() || this.d.i()) {
                return this;
            }
            ECCurve a = a();
            switch (a.h()) {
                case 0:
                    ECFieldElement eCFieldElement = this.c;
                    ECFieldElement eCFieldElement2 = this.d;
                    ECFieldElement b = b(eCFieldElement2);
                    ECFieldElement e = b.e();
                    ECFieldElement a2 = c(eCFieldElement.e()).a(a().f());
                    ECFieldElement b2 = c(eCFieldElement).c(e).b(a2.e());
                    if (b2.i()) {
                        return a().e();
                    }
                    b = b2.c(b).f();
                    a2 = b2.c(b).c(a2);
                    b = e.e().c(b).b(a2);
                    e = b.b(a2).c(a2.a(b)).a(eCFieldElement);
                    return new Fp(a, e, eCFieldElement.b(e).c(b).b(eCFieldElement2), this.f);
                case 4:
                    return b(false).b((ECPoint) this);
                default:
                    return p().b((ECPoint) this);
            }
        }

        protected ECFieldElement b(ECFieldElement eCFieldElement) {
            return eCFieldElement.a(eCFieldElement);
        }

        protected ECFieldElement c(ECFieldElement eCFieldElement) {
            return b(eCFieldElement).a(eCFieldElement);
        }

        protected ECFieldElement d(ECFieldElement eCFieldElement) {
            return b(b(eCFieldElement));
        }

        protected ECFieldElement e(ECFieldElement eCFieldElement) {
            return d(b(eCFieldElement));
        }

        protected ECFieldElement a(ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2, ECFieldElement eCFieldElement3, ECFieldElement eCFieldElement4) {
            return eCFieldElement.a(eCFieldElement2).e().b(eCFieldElement3).b(eCFieldElement4);
        }

        public ECPoint c(ECPoint eCPoint) {
            return eCPoint.l() ? this : b(eCPoint.o());
        }

        public ECPoint o() {
            if (l()) {
                return this;
            }
            ECCurve a = a();
            if (a.h() != 0) {
                return new Fp(a, this.c, this.d.d(), this.e, this.f);
            }
            return new Fp(a, this.c, this.d.d(), this.f);
        }

        protected ECFieldElement b(ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2) {
            if (eCFieldElement2 == null) {
                eCFieldElement2 = eCFieldElement.e();
            }
            ECFieldElement e = eCFieldElement2.e();
            ECFieldElement f = a().f();
            ECFieldElement d = f.d();
            if (d.h() < f.h()) {
                return e.c(d).d();
            }
            return e.c(f);
        }

        protected ECFieldElement r() {
            ECFieldElement eCFieldElement = this.e[1];
            if (eCFieldElement != null) {
                return eCFieldElement;
            }
            ECFieldElement[] eCFieldElementArr = this.e;
            eCFieldElement = b(this.e[0], null);
            eCFieldElementArr[1] = eCFieldElement;
            return eCFieldElement;
        }

        protected Fp b(boolean z) {
            ECFieldElement eCFieldElement = this.c;
            ECFieldElement eCFieldElement2 = this.d;
            ECFieldElement eCFieldElement3 = this.e[0];
            ECFieldElement r = r();
            ECFieldElement e = eCFieldElement.e();
            ECFieldElement a = c(e).a(r);
            ECFieldElement e2 = eCFieldElement2.e();
            ECFieldElement e3 = e2.e();
            eCFieldElement = b(a(eCFieldElement, e2, e, e3));
            e = a.e().b(b(eCFieldElement));
            e2 = e(e3);
            a = a.c(eCFieldElement.b(e)).b(e2);
            r = z ? b(e2.c(r)) : null;
            e2 = b(eCFieldElement3.h() == 1 ? eCFieldElement2 : eCFieldElement2.c(eCFieldElement3));
            return new Fp(a(), e, a, new ECFieldElement[]{e2, r}, this.f);
        }
    }

    public abstract ECPoint b(ECPoint eCPoint);

    public abstract ECPoint c(ECPoint eCPoint);

    protected abstract boolean n();

    public abstract ECPoint o();

    public abstract ECPoint p();

    protected static ECFieldElement[] a(ECCurve eCCurve) {
        int h = eCCurve == null ? 0 : eCCurve.h();
        switch (h) {
            case 0:
            case 5:
                return a;
            default:
                ECFieldElement a = eCCurve.a(ECConstants.d);
                switch (h) {
                    case 1:
                    case 2:
                    case 6:
                        return new ECFieldElement[]{a};
                    case 3:
                        return new ECFieldElement[]{a, a, a};
                    case 4:
                        return new ECFieldElement[]{a, eCCurve.f()};
                    default:
                        throw new IllegalArgumentException("unknown coordinate system");
                }
        }
    }

    protected ECPoint(ECCurve eCCurve, ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2) {
        this(eCCurve, eCFieldElement, eCFieldElement2, a(eCCurve));
    }

    protected ECPoint(ECCurve eCCurve, ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2, ECFieldElement[] eCFieldElementArr) {
        this.g = null;
        this.b = eCCurve;
        this.c = eCFieldElement;
        this.d = eCFieldElement2;
        this.e = eCFieldElementArr;
    }

    public ECCurve a() {
        return this.b;
    }

    protected int b() {
        return this.b == null ? 0 : this.b.h();
    }

    public ECFieldElement c() {
        i();
        return e();
    }

    public ECFieldElement d() {
        i();
        return f();
    }

    public ECFieldElement e() {
        return this.c;
    }

    public ECFieldElement f() {
        return this.d;
    }

    public ECFieldElement a(int i) {
        return (i < 0 || i >= this.e.length) ? null : this.e[i];
    }

    protected ECFieldElement g() {
        return this.c;
    }

    protected ECFieldElement h() {
        return this.d;
    }

    protected void i() {
        if (!j()) {
            throw new IllegalStateException("point not in normal form");
        }
    }

    public boolean j() {
        int b = b();
        if (b == 0 || b == 5 || l() || this.e[0].h() == 1) {
            return true;
        }
        return false;
    }

    public ECPoint k() {
        if (l()) {
            return this;
        }
        switch (b()) {
            case 0:
            case 5:
                return this;
            default:
                ECFieldElement a = a(0);
                if (a.h() != 1) {
                    return a(a.f());
                }
                return this;
        }
    }

    ECPoint a(ECFieldElement eCFieldElement) {
        switch (b()) {
            case 1:
            case 6:
                return a(eCFieldElement, eCFieldElement);
            case 2:
            case 3:
            case 4:
                ECFieldElement e = eCFieldElement.e();
                return a(e, e.c(eCFieldElement));
            default:
                throw new IllegalStateException("not a projective coordinate system");
        }
    }

    protected ECPoint a(ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2) {
        return a().a(g().c(eCFieldElement), h().c(eCFieldElement2), this.f);
    }

    public boolean l() {
        return this.c == null || this.d == null || (this.e.length > 0 && this.e[0].i());
    }

    public boolean a(ECPoint eCPoint) {
        boolean z = true;
        if (eCPoint == null) {
            return false;
        }
        ECCurve a = a();
        ECCurve a2 = eCPoint.a();
        if (a == null) {
            int i = 1;
        } else {
            boolean i2 = false;
        }
        if (a2 == null) {
            int i3 = 1;
        } else {
            boolean i32 = false;
        }
        boolean l = l();
        boolean l2 = eCPoint.l();
        if (l || l2) {
            if (!(l && l2 && (i2 != 0 || i32 != 0 || a.equals(a2)))) {
                z = false;
            }
            return z;
        }
        if (i2 == 0 || i32 == 0) {
            if (i2 != 0) {
                eCPoint = eCPoint.k();
            } else if (i32 != 0) {
                this = k();
            } else if (!a.equals(a2)) {
                return false;
            } else {
                ECPoint[] eCPointArr = new ECPoint[]{this, a.b(eCPoint)};
                a.a(eCPointArr);
                this = eCPointArr[0];
                eCPoint = eCPointArr[1];
            }
        }
        if (!(e().equals(eCPoint.e()) && f().equals(eCPoint.f()))) {
            z = false;
        }
        return z;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof ECPoint) {
            return a((ECPoint) obj);
        }
        return false;
    }

    public int hashCode() {
        ECCurve a = a();
        int hashCode = a == null ? 0 : a.hashCode() ^ -1;
        if (l()) {
            return hashCode;
        }
        ECPoint k = k();
        return (hashCode ^ (k.e().hashCode() * 17)) ^ (k.f().hashCode() * k.DOWNLOAD_FAILED_DIALOG_MESSAGE_ID);
    }

    public String toString() {
        if (l()) {
            return "INF";
        }
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append('(');
        stringBuffer.append(g());
        stringBuffer.append(',');
        stringBuffer.append(h());
        for (Object append : this.e) {
            stringBuffer.append(',');
            stringBuffer.append(append);
        }
        stringBuffer.append(')');
        return stringBuffer.toString();
    }

    public byte[] m() {
        return a(this.f);
    }

    public byte[] a(boolean z) {
        if (l()) {
            return new byte[1];
        }
        ECPoint k = k();
        Object k2 = k.e().k();
        if (z) {
            Object obj = new byte[(k2.length + 1)];
            obj[0] = (byte) (k.n() ? 3 : 2);
            System.arraycopy(k2, 0, obj, 1, k2.length);
            return obj;
        }
        obj = k.f().k();
        byte[] bArr = new byte[((k2.length + obj.length) + 1)];
        bArr[0] = (byte) 4;
        System.arraycopy(k2, 0, bArr, 1, k2.length);
        System.arraycopy(obj, 0, bArr, k2.length + 1, obj.length);
        return bArr;
    }

    public ECPoint b(int i) {
        if (i < 0) {
            throw new IllegalArgumentException("'e' cannot be negative");
        }
        while (true) {
            i--;
            if (i < 0) {
                return this;
            }
            this = p();
        }
    }

    public ECPoint d(ECPoint eCPoint) {
        return p().b(eCPoint);
    }

    public ECPoint q() {
        return d(this);
    }

    public ECPoint a(BigInteger bigInteger) {
        return a().i().a(this, bigInteger);
    }
}
