package com.stoldo.m223_punchclock.shared.util;


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
