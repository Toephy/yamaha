package com.lixing.GoF.proxy;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

public class CglibProxy implements MethodInterceptor {

	private Object target;

	public CglibProxy(Object target) {
		this.target = target;
	}

	@Override
	public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
		System.out.println("before calling " + method);
		Object result = proxy.invokeSuper(obj, args);
		System.out.println("after calling " + method);
		return result;
	}

	public Object getProxy() {
		Enhancer enhancer = new Enhancer();  
        enhancer.setSuperclass(this.target.getClass());  
        // 回调方法  
        enhancer.setCallback(this);  
        // 创建代理对象  
        return enhancer.create();
	}
	
	public void setTarget(Object target) {
		this.target = target;
	}

}
