package com.cuzz;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Semaphore;

/**
 * @Author: cuzz
 * @Date: 2019/2/28 15:13
 * @Description:
 */
public class BoundedHashSet<T> {
    private final Set<T> set;
    private final Semaphore sem;

    public BoundedHashSet(int bound) {
        this.set = Collections.synchronizedSet(new HashSet<T>());
        this.sem = new Semaphore(bound);
    }

    public boolean add(T o) throws InterruptedException {
        sem.acquire();
        boolean wasAdd = false;
        try {
            wasAdd = set.add(o);
            System.out.println(o);
            return wasAdd;
        } finally {
            if (!wasAdd) {
                sem.release();
            }
        }
    }

    public boolean remove(T o) {
        boolean wasRemoved = set.remove(o);
        if (wasRemoved) {
            sem.release();
        }
        return wasRemoved;
    }

    public static void main(String[] args) throws InterruptedException {
        BoundedHashSet<Integer> boundedHashSet = new BoundedHashSet<>(5);
        boundedHashSet.add(1);
        boundedHashSet.add(2);
        boundedHashSet.add(3);
        boundedHashSet.add(4);
        boundedHashSet.add(5);
        boundedHashSet.add(6);
        boundedHashSet.remove(1);
        boundedHashSet.remove(1);
    }
}
