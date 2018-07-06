package org.spongycastle.jce.provider;

import java.security.cert.PolicyNode;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class PKIXPolicyNode implements PolicyNode {
    protected List a;
    protected int b;
    protected Set c;
    protected PolicyNode d;
    protected Set e;
    protected String f;
    protected boolean g;

    public PKIXPolicyNode(List list, int i, Set set, PolicyNode policyNode, Set set2, String str, boolean z) {
        this.a = list;
        this.b = i;
        this.c = set;
        this.d = policyNode;
        this.e = set2;
        this.f = str;
        this.g = z;
    }

    public void a(PKIXPolicyNode pKIXPolicyNode) {
        this.a.add(pKIXPolicyNode);
        pKIXPolicyNode.c(this);
    }

    public Iterator getChildren() {
        return this.a.iterator();
    }

    public int getDepth() {
        return this.b;
    }

    public Set getExpectedPolicies() {
        return this.c;
    }

    public PolicyNode getParent() {
        return this.d;
    }

    public Set getPolicyQualifiers() {
        return this.e;
    }

    public String getValidPolicy() {
        return this.f;
    }

    public boolean a() {
        return !this.a.isEmpty();
    }

    public boolean isCritical() {
        return this.g;
    }

    public void b(PKIXPolicyNode pKIXPolicyNode) {
        this.a.remove(pKIXPolicyNode);
    }

    public void a(boolean z) {
        this.g = z;
    }

    public void c(PKIXPolicyNode pKIXPolicyNode) {
        this.d = pKIXPolicyNode;
    }

    public String toString() {
        return a("");
    }

    public String a(String str) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(str);
        stringBuffer.append(this.f);
        stringBuffer.append(" {\n");
        for (int i = 0; i < this.a.size(); i++) {
            stringBuffer.append(((PKIXPolicyNode) this.a.get(i)).a(str + "    "));
        }
        stringBuffer.append(str);
        stringBuffer.append("}\n");
        return stringBuffer.toString();
    }

    public Object clone() {
        return b();
    }

    public PKIXPolicyNode b() {
        Set hashSet = new HashSet();
        for (String str : this.c) {
            hashSet.add(new String(str));
        }
        Set hashSet2 = new HashSet();
        for (String str2 : this.e) {
            hashSet2.add(new String(str2));
        }
        PKIXPolicyNode pKIXPolicyNode = new PKIXPolicyNode(new ArrayList(), this.b, hashSet, null, hashSet2, new String(this.f), this.g);
        for (PKIXPolicyNode b : this.a) {
            PKIXPolicyNode b2 = b2.b();
            b2.c(pKIXPolicyNode);
            pKIXPolicyNode.a(b2);
        }
        return pKIXPolicyNode;
    }
}
