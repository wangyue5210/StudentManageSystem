package util;

import com.sun.org.apache.bcel.internal.generic.NEW;

public class ServiceFactory {
	public static Object getService(Object service) {
		return new TransactionInvocationHandler(service).getProxy();
	}

}
