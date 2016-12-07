package com.lixing.nio;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by Toephy on 2016.11.10 18:13
 */
public class SocketServer {

    public static final int PORT = 5656;

    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(PORT);

            while (true) {
                Socket socket = serverSocket.accept();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    class Client implements Runnable {

        private Socket client;

        public Client(Socket client) {
            this.client = client;
        }

        @Override
        public void run() {
            BufferedReader bufferedReader = null;
            try {
                if (client == null) {
                    return;
                }
                InputStream inputStream = client.getInputStream();

                bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

                StringBuffer result = new StringBuffer();
                String s;

                while ((s = bufferedReader.readLine()) != null) {
                    result.append(s);
                }
                //ByteArrayOutputStream
                System.out.println(result);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    if (bufferedReader != null) {
                        bufferedReader.close();
                    }

                    if (client != null) {
                        client.close();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
