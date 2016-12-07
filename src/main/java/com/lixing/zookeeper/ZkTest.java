package com.lixing.zookeeper;

import org.I0Itec.zkclient.ZkClient;

/**
 * Created by Toephy on 2016/6/2 17:12
 */
public class ZkTest {

    private static String host = "120.197.138.35:2181,120.197.138.35:2182,120.197.138.35:2183";

    public static void main(String[] args) {
        ZkClient zkClient = new ZkClient(host);

    }
}
