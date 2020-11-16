package com.stoldo.m223_punchclock.shared.util;

/**
 * This class and or some of its attributes / fields got added / changed because they were needed.
 * */
public class CommonUtils {
	
	public static <E extends Throwable> void falseThenThrow(boolean b, E e) throws E {
		if (!b) {
			throw e;
		}
	}
	
	public static <T, E extends Throwable> T nullThenThrow(T t, E e) throws E {
		if (t != null) {
			return t;
		}
		
		throw e;
	}
}
