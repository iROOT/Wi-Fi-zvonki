package com.b.a.b.a.a;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.AbstractQueue;
import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class d<E> extends AbstractQueue<E> implements a<E>, Serializable {
    transient c<E> a;
    transient c<E> b;
    final ReentrantLock c;
    private transient int d;
    private final int e;
    private final Condition f;
    private final Condition g;

    private abstract class a implements Iterator<E> {
        c<E> a;
        E b;
        final /* synthetic */ d c;
        private c<E> d;

        abstract c<E> a();

        abstract c<E> a(c<E> cVar);

        a(d dVar) {
            this.c = dVar;
            ReentrantLock reentrantLock = dVar.c;
            reentrantLock.lock();
            try {
                this.a = a();
                this.b = this.a == null ? null : this.a.a;
            } finally {
                reentrantLock.unlock();
            }
        }

        private c<E> b(c<E> cVar) {
            while (true) {
                c<E> a = a(cVar);
                if (a == null) {
                    return null;
                }
                if (a.a != null) {
                    return a;
                }
                if (a == cVar) {
                    return a();
                }
                cVar = a;
            }
        }

        void b() {
            ReentrantLock reentrantLock = this.c.c;
            reentrantLock.lock();
            try {
                this.a = b(this.a);
                this.b = this.a == null ? null : this.a.a;
            } finally {
                reentrantLock.unlock();
            }
        }

        public boolean hasNext() {
            return this.a != null;
        }

        public E next() {
            if (this.a == null) {
                throw new NoSuchElementException();
            }
            this.d = this.a;
            E e = this.b;
            b();
            return e;
        }

        public void remove() {
            c cVar = this.d;
            if (cVar == null) {
                throw new IllegalStateException();
            }
            this.d = null;
            ReentrantLock reentrantLock = this.c.c;
            reentrantLock.lock();
            try {
                if (cVar.a != null) {
                    this.c.a(cVar);
                }
                reentrantLock.unlock();
            } catch (Throwable th) {
                reentrantLock.unlock();
            }
        }
    }

    private class b extends a {
        final /* synthetic */ d d;

        private b(d dVar) {
            this.d = dVar;
            super(dVar);
        }

        c<E> a() {
            return this.d.a;
        }

        c<E> a(c<E> cVar) {
            return cVar.c;
        }
    }

    static final class c<E> {
        E a;
        c<E> b;
        c<E> c;

        c(E e) {
            this.a = e;
        }
    }

    public d() {
        this(Integer.MAX_VALUE);
    }

    public d(int i) {
        this.c = new ReentrantLock();
        this.f = this.c.newCondition();
        this.g = this.c.newCondition();
        if (i <= 0) {
            throw new IllegalArgumentException();
        }
        this.e = i;
    }

    private boolean b(c<E> cVar) {
        if (this.d >= this.e) {
            return false;
        }
        c cVar2 = this.a;
        cVar.c = cVar2;
        this.a = cVar;
        if (this.b == null) {
            this.b = cVar;
        } else {
            cVar2.b = cVar;
        }
        this.d++;
        this.f.signal();
        return true;
    }

    private boolean c(c<E> cVar) {
        if (this.d >= this.e) {
            return false;
        }
        c cVar2 = this.b;
        cVar.b = cVar2;
        this.b = cVar;
        if (this.a == null) {
            this.a = cVar;
        } else {
            cVar2.c = cVar;
        }
        this.d++;
        this.f.signal();
        return true;
    }

    private E f() {
        c cVar = this.a;
        if (cVar == null) {
            return null;
        }
        c cVar2 = cVar.c;
        E e = cVar.a;
        cVar.a = null;
        cVar.c = cVar;
        this.a = cVar2;
        if (cVar2 == null) {
            this.b = null;
        } else {
            cVar2.b = null;
        }
        this.d--;
        this.g.signal();
        return e;
    }

    private E g() {
        c cVar = this.b;
        if (cVar == null) {
            return null;
        }
        c cVar2 = cVar.b;
        E e = cVar.a;
        cVar.a = null;
        cVar.b = cVar;
        this.b = cVar2;
        if (cVar2 == null) {
            this.a = null;
        } else {
            cVar2.c = null;
        }
        this.d--;
        this.g.signal();
        return e;
    }

    void a(c<E> cVar) {
        c cVar2 = cVar.b;
        c cVar3 = cVar.c;
        if (cVar2 == null) {
            f();
        } else if (cVar3 == null) {
            g();
        } else {
            cVar2.c = cVar3;
            cVar3.b = cVar2;
            cVar.a = null;
            this.d--;
            this.g.signal();
        }
    }

    public void a(E e) {
        if (!c((Object) e)) {
            throw new IllegalStateException("Deque full");
        }
    }

    public boolean b(E e) {
        if (e == null) {
            throw new NullPointerException();
        }
        c cVar = new c(e);
        ReentrantLock reentrantLock = this.c;
        reentrantLock.lock();
        try {
            boolean b = b(cVar);
            return b;
        } finally {
            reentrantLock.unlock();
        }
    }

    public boolean c(E e) {
        if (e == null) {
            throw new NullPointerException();
        }
        c cVar = new c(e);
        ReentrantLock reentrantLock = this.c;
        reentrantLock.lock();
        try {
            boolean c = c(cVar);
            return c;
        } finally {
            reentrantLock.unlock();
        }
    }

    public void d(E e) {
        if (e == null) {
            throw new NullPointerException();
        }
        c cVar = new c(e);
        ReentrantLock reentrantLock = this.c;
        reentrantLock.lock();
        while (!c(cVar)) {
            try {
                this.g.await();
            } finally {
                reentrantLock.unlock();
            }
        }
    }

    public boolean a(E e, long j, TimeUnit timeUnit) {
        if (e == null) {
            throw new NullPointerException();
        }
        c cVar = new c(e);
        long toNanos = timeUnit.toNanos(j);
        ReentrantLock reentrantLock = this.c;
        reentrantLock.lockInterruptibly();
        while (!c(cVar)) {
            try {
                if (toNanos <= 0) {
                    return false;
                }
                toNanos = this.g.awaitNanos(toNanos);
            } finally {
                reentrantLock.unlock();
            }
        }
        reentrantLock.unlock();
        return true;
    }

    public E a() {
        E b = b();
        if (b != null) {
            return b;
        }
        throw new NoSuchElementException();
    }

    public E b() {
        ReentrantLock reentrantLock = this.c;
        reentrantLock.lock();
        try {
            E f = f();
            return f;
        } finally {
            reentrantLock.unlock();
        }
    }

    public E c() {
        E f;
        ReentrantLock reentrantLock = this.c;
        reentrantLock.lock();
        while (true) {
            try {
                f = f();
                if (f != null) {
                    break;
                }
                this.f.await();
            } finally {
                reentrantLock.unlock();
            }
        }
        return f;
    }

    public E a(long j, TimeUnit timeUnit) {
        long toNanos = timeUnit.toNanos(j);
        ReentrantLock reentrantLock = this.c;
        reentrantLock.lockInterruptibly();
        long j2 = toNanos;
        while (true) {
            try {
                E f = f();
                if (f != null) {
                    reentrantLock.unlock();
                    return f;
                } else if (j2 <= 0) {
                    break;
                } else {
                    j2 = this.f.awaitNanos(j2);
                }
            } finally {
                reentrantLock.unlock();
            }
        }
        return null;
    }

    public E d() {
        E e = e();
        if (e != null) {
            return e;
        }
        throw new NoSuchElementException();
    }

    public E e() {
        ReentrantLock reentrantLock = this.c;
        reentrantLock.lock();
        try {
            E e = this.a == null ? null : this.a.a;
            reentrantLock.unlock();
            return e;
        } catch (Throwable th) {
            reentrantLock.unlock();
        }
    }

    public boolean e(Object obj) {
        if (obj == null) {
            return false;
        }
        ReentrantLock reentrantLock = this.c;
        reentrantLock.lock();
        try {
            for (c cVar = this.a; cVar != null; cVar = cVar.c) {
                if (obj.equals(cVar.a)) {
                    a(cVar);
                    return true;
                }
            }
            reentrantLock.unlock();
            return false;
        } finally {
            reentrantLock.unlock();
        }
    }

    public boolean add(E e) {
        a((Object) e);
        return true;
    }

    public boolean offer(E e) {
        return c((Object) e);
    }

    public void put(E e) {
        d(e);
    }

    public boolean offer(E e, long j, TimeUnit timeUnit) {
        return a(e, j, timeUnit);
    }

    public E remove() {
        return a();
    }

    public E poll() {
        return b();
    }

    public E take() {
        return c();
    }

    public E poll(long j, TimeUnit timeUnit) {
        return a(j, timeUnit);
    }

    public E element() {
        return d();
    }

    public E peek() {
        return e();
    }

    public int remainingCapacity() {
        ReentrantLock reentrantLock = this.c;
        reentrantLock.lock();
        try {
            int i = this.e - this.d;
            return i;
        } finally {
            reentrantLock.unlock();
        }
    }

    public int drainTo(Collection<? super E> collection) {
        return drainTo(collection, Integer.MAX_VALUE);
    }

    public int drainTo(Collection<? super E> collection, int i) {
        if (collection == null) {
            throw new NullPointerException();
        } else if (collection == this) {
            throw new IllegalArgumentException();
        } else {
            ReentrantLock reentrantLock = this.c;
            reentrantLock.lock();
            try {
                int min = Math.min(i, this.d);
                for (int i2 = 0; i2 < min; i2++) {
                    collection.add(this.a.a);
                    f();
                }
                return min;
            } finally {
                reentrantLock.unlock();
            }
        }
    }

    public boolean remove(Object obj) {
        return e(obj);
    }

    public int size() {
        ReentrantLock reentrantLock = this.c;
        reentrantLock.lock();
        try {
            int i = this.d;
            return i;
        } finally {
            reentrantLock.unlock();
        }
    }

    public boolean contains(Object obj) {
        if (obj == null) {
            return false;
        }
        ReentrantLock reentrantLock = this.c;
        reentrantLock.lock();
        try {
            for (c cVar = this.a; cVar != null; cVar = cVar.c) {
                if (obj.equals(cVar.a)) {
                    return true;
                }
            }
            reentrantLock.unlock();
            return false;
        } finally {
            reentrantLock.unlock();
        }
    }

    public Object[] toArray() {
        ReentrantLock reentrantLock = this.c;
        reentrantLock.lock();
        try {
            Object[] objArr = new Object[this.d];
            int i = 0;
            c cVar = this.a;
            while (cVar != null) {
                int i2 = i + 1;
                objArr[i] = cVar.a;
                cVar = cVar.c;
                i = i2;
            }
            return objArr;
        } finally {
            reentrantLock.unlock();
        }
    }

    public <T> T[] toArray(T[] tArr) {
        ReentrantLock reentrantLock = this.c;
        reentrantLock.lock();
        try {
            if (tArr.length < this.d) {
                tArr = (Object[]) Array.newInstance(tArr.getClass().getComponentType(), this.d);
            }
            int i = 0;
            c cVar = this.a;
            while (cVar != null) {
                int i2 = i + 1;
                tArr[i] = cVar.a;
                cVar = cVar.c;
                i = i2;
            }
            if (tArr.length > i) {
                tArr[i] = null;
            }
            reentrantLock.unlock();
            return tArr;
        } catch (Throwable th) {
            reentrantLock.unlock();
        }
    }

    public String toString() {
        ReentrantLock reentrantLock = this.c;
        reentrantLock.lock();
        try {
            String str;
            c cVar = this.a;
            if (cVar == null) {
                str = "[]";
            } else {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append('[');
                c cVar2 = cVar;
                while (true) {
                    Object obj = cVar2.a;
                    if (obj == this) {
                        obj = "(this Collection)";
                    }
                    stringBuilder.append(obj);
                    cVar = cVar2.c;
                    if (cVar == null) {
                        break;
                    }
                    stringBuilder.append(',').append(' ');
                    cVar2 = cVar;
                }
                str = stringBuilder.append(']').toString();
                reentrantLock.unlock();
            }
            return str;
        } finally {
            reentrantLock.unlock();
        }
    }

    public void clear() {
        ReentrantLock reentrantLock = this.c;
        reentrantLock.lock();
        try {
            c cVar = this.a;
            while (cVar != null) {
                cVar.a = null;
                c cVar2 = cVar.c;
                cVar.b = null;
                cVar.c = null;
                cVar = cVar2;
            }
            this.b = null;
            this.a = null;
            this.d = 0;
            this.g.signalAll();
        } finally {
            reentrantLock.unlock();
        }
    }

    public Iterator<E> iterator() {
        return new b();
    }
}
