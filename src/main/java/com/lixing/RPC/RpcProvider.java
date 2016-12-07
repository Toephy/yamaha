package com.lixing.RPC;

/**
 * Created by Toephy on 2016/6/2 18:06
 */
public class RpcProvider {

    public static void main(String[] args) throws Exception {
        GreetInterface greetInterface = new GreetInterfaceImpl();
        RpcFramework.export(greetInterface, 1234);
    }
}
