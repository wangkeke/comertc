package cc.zenking.cloud.comertc.util;

import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

public class StreamUtil {
	
	private static final String uuid = UUID.randomUUID().toString();
	private static AtomicInteger increment = new AtomicInteger();
	
	public static String getStreamCode() {
		String code = uuid + "_" + System.currentTimeMillis()+"_"+increment.incrementAndGet();
		int codeHash = code.hashCode();
		return Integer.toHexString(codeHash);
	}
	
	public static void main(String[] args) {
		System.out.println(getStreamCode());
	}
	
}
