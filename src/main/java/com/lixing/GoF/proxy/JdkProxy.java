package com.lixing.GoF.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class JdkProxy implements InvocationHandler {
	private Object target;
	private JdkProxy(Object target) {
		this.target = target;
	}
	
	private static JdkProxy dynamicProxy = null;
	private static Object classLock = JdkProxy.class;
	
	public static JdkProxy getInstance(Object o) {
		if (dynamicProxy == null) {
			synchronized (classLock) {
				if (dynamicProxy == null) {
					dynamicProxy = new JdkProxy(o);
					return dynamicProxy;
				}
			}
		} else {
			dynamicProxy.target = o;
		}
		return dynamicProxy;
	}
	
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		System.out.println("before calling " + method);
		Object result = method.invoke(target, args);
		System.out.println("after calling " + method);
		return result;
	}

	public static Object getProxy(Object o) {
		Class<? extends Object> cls = o.getClass();
		return Proxy.newProxyInstance(cls.getClassLoader(), cls.getInterfaces(), JdkProxy.getInstance(o));
	}
}
