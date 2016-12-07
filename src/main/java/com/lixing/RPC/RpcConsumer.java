package com.lixing.RPC;

/**
 * Created by Toephy on 2016/6/2 18:08
 */
public class RpcConsumer {
    public static void main(String[] args) throws Exception {
        GreetInterface greet = RpcFramework.refer(GreetInterface.class, "127.0.0.1", 1234);
        String s = greet.sayHello();
        System.out.println(s);
    }
}
