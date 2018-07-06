package org.spongycastle.util;

import java.util.ArrayList;
import java.util.Collection;

public class CollectionStore implements Store {
    private Collection a;

    public CollectionStore(Collection collection) {
        this.a = new ArrayList(collection);
    }

    public Collection a(Selector selector) {
        if (selector == null) {
            return new ArrayList(this.a);
        }
        Collection arrayList = new ArrayList();
        for (Object next : this.a) {
            if (selector.a(next)) {
                arrayList.add(next);
            }
        }
        return arrayList;
    }
}
