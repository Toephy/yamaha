package com.lixing.myJunit.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ServiceMonitorProxy<T> implements InvocationHandler {

	// 目标对象
	private Object target;
	
	// 服务名称
	private String serviceName;

	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		// 在目标对象的方法执行之前简单的打印一下
		System.out.println("before 判断服务 " + serviceName + " 是否可用，不可用直接return null");
		// 执行目标对象的方法
		Object result = method.invoke(target, args);
		// 在目标对象的方法执行之后简单的打印一下
		System.out.println("after 判断返回的数据是否成功，成功监控+1，否则-1");
		System.out.println(method.getReturnType() + " " + result.getClass());
		return result;
	}

	@SuppressWarnings("unchecked")
	public T getProxy(T target, String serviceName) {
		this.target = target;
		this.serviceName = serviceName;
		return (T)Proxy.newProxyInstance(
				Thread.currentThread().getContextClassLoader(), 
				target.getClass().getInterfaces(), 
				this);
	}
	
	public static void main(String[] args) {
		CountInterface cp = new ServiceMonitorProxy<CountInterface>().getProxy(new CountImpl(), "USER_EXPERIENCE");
		cp.add();
		cp.substract();
	}
}
