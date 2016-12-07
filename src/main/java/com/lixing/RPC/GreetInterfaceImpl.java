package com.lixing.RPC;

/**
 * Created by Toephy on 2016/6/2 18:05
 */
public class GreetInterfaceImpl implements GreetInterface {
    @Override
    public String sayHello() {
        return "hello rpc!";
    }
}
