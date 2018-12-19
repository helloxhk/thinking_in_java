package com.xhk.demo.nio;

import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;

/**
 * @author xhk
 * @time 2018-12-19 16:39
 */
public class PreferenceDemo {
	public static void main(String[] args) throws BackingStoreException {
		Preferences pref = Preferences.userNodeForPackage(PreferenceDemo.class);
		int myNumber = pref.getInt("my number", 0);
		myNumber++;
		pref.putInt("my number", myNumber);

		for (String key : pref.keys()) {
			System.out.println(key + " " + pref.get(key, null));
		}
	}
}
