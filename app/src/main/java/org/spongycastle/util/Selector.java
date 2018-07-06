package org.spongycastle.util;

public interface Selector extends Cloneable {
    boolean a(Object obj);

    Object clone();
}
