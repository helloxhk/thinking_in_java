package com.xhk.web.servlet.reference;

import java.lang.ref.*;
import java.util.LinkedList;

/**
 * @author xhk
 * @time 2018-12-17 10:33
 */
public class References {

	private static ReferenceQueue<VeryBig> rq = new ReferenceQueue<>();

	private static void checkQueue() {
		Reference<? extends VeryBig> inq = rq.poll();
		if (inq != null) {
			System.out.println("In queue: " + inq.get());
		}
	}

	public static void main(String[] args) {
		int size = 10;

		LinkedList<SoftReference<VeryBig>> sa = new LinkedList<>();
		for (int i = 0; i < size; i++) {
			sa.add(new SoftReference<>(new VeryBig("Soft " + i), rq));
			System.out.println("Just created: " + sa.getLast());
			checkQueue();
		}

		LinkedList<WeakReference<VeryBig>> wa = new LinkedList<>();
		for (int i = 0; i < size; i++) {
			wa.add(new WeakReference<>(new VeryBig("Weak " + i), rq));
			System.out.println("Just created: " + wa.getLast());
			checkQueue();
		}

		SoftReference<VeryBig> s = new SoftReference<>(new VeryBig("Soft"));
		WeakReference<VeryBig> w = new WeakReference<>(new VeryBig("Weak"));
		System.gc();

		LinkedList<PhantomReference<VeryBig>> pa = new LinkedList<>();
		for (int i = 0; i < size; i++) {
			pa.add(new PhantomReference<>(new VeryBig("Phantom " + i),rq));
			System.out.println("Just created: " + pa.getLast());
			checkQueue();
		}

		System.gc();
	}
}
