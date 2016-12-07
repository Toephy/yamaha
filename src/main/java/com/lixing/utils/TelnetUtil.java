package com.lixing.utils;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.net.telnet.TelnetClient;

public class TelnetUtil {

	public static List<String> servers = Arrays.asList("10.13.25.57:3306");
	
	public static void main(String[] args) {
		TelnetClient telnet = new TelnetClient();
		
		for (String server : servers) {
			try {
				String[] s = server.split(":");
				String ip = s[0];
				int port = Integer.parseInt(s[1]);
				telnet.connect(ip, port);
			} catch (Exception e) {
				System.out.println(server);
			} finally {
				try {
					telnet.disconnect();
				} catch (IOException e) {}
			}
		}
		
		System.out.println("done");
	}

}
