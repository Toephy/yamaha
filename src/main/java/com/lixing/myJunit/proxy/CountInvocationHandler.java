package com.lixing.myJunit.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class CountInvocationHandler implements InvocationHandler {

	// 目标对象
	private Object target;
    
	public CountInvocationHandler(Object target) {
		this.target = target;
	}

	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		// 在目标对象的方法执行之前简单的打印一下
		System.out.println("------------------before------------------");

		// 执行目标对象的方法
		Object result = method.invoke(target, args);

		// 在目标对象的方法执行之后简单的打印一下
		System.out.println("-------------------after------------------");
		
		return result;
	}

	/**
	 * 获取目标对象的代理对象
	 * 
	 * @return 代理对象
	 */
	public Object getProxy() {
		return Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(),
                                        target.getClass().getInterfaces(),
                                        this);
	}
	
	
	public static void main(String[] args) {
		CountInterface count = new CountImpl();
		System.out.println(count.hashCode());
		CountInvocationHandler countInvocationHandler = new CountInvocationHandler(count);
		CountInterface countProxy = (CountInterface)countInvocationHandler.getProxy();
		System.out.println(countProxy.hashCode());
		countProxy.add();
		countProxy.substract();
	}
}
