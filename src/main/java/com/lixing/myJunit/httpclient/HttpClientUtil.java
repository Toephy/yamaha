package com.lixing.myJunit.httpclient;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

public class HttpClientUtil {
	private final static String URL = "http://192.168.101.250:8080/api/bookapp/serviceMonitor.m?cid=eef_&";
	private final static String LOCATION = "E:\\uu.txt";
	
	private final static String[] URL_ARRAYS = {//10.13.24.87  120.197.138.35
		"http://10.13.24.87:8080/api/bookapp/gid_topic_list.m?cid=eef_&gid=1&p=1&size=1",
		"http://10.13.24.87:8080/api/bookapp/topic_group_list.m?cid=eef_&gid=8141192",
		"http://10.13.24.87:8080/api/bookapp/topic_list.m?cid=eef_&topic_group_id=5067422&p=1&size=20&type=2",
		"http://10.13.24.87:8080/api/bookapp/publish_topic.m?cid=eef_&topic_group_id=1&title=%E5%91%B5%E5%91%B5&content=%E4%B8%8D%E9%94%99%E5%93%A6%EF%BC%81&session_id=123123123&nickname=%E7%BB%B4%E6%8B%89&user_image=xxxx",
		"http://10.13.24.87:8080/api/bookapp/post_list.m?cid=eef_&topic_id=1&start=1&end=20&session_id=123123123",
		"http://10.13.24.87:8080/api/bookapp/publish_post.m?cid=eef_&&topic_group_id=1&topic_id=1&content=%E4%B8%8D%E9%94%99%E5%93%A6%EF%BC%81&session_id=123123123&nickname=%E7%BB%B4%E6%8B%89&user_image=xxxx",
		"http://10.13.24.87:8080/api/bookapp/reply_post_list.m?cid=eef_&post_id=1&start=1&end=20",
		"http://10.13.24.87:8080/api/bookapp/publish_reply_post.m?cid=eef_&&topic_group_id=1&topic_id=1&post_id=1&content=%E4%B8%8D%E9%94%99%E5%93%A6%EF%BC%81&session_id=123123123&nickname=%E7%BB%B4%E6%8B%89&user_image=xxxx",
		"http://10.13.24.87:8080/api/bookapp/user_topic_list.m?cid=eef_&type=1&session_id=XEsYxcN1DZQ_3e3Odj0U&page=1&size=10",
		"http://10.13.24.87:8080/api/bookapp/user_post_list.m?cid=eef_&session_id=adfadfadf&p=1&size=1"
	};
	
	private HttpClientUtil() {
	}

	public static String getRespContent(String url) {
		HttpGet httpRequest = new HttpGet(url);
		try {
			// 取得HttpClient对象
			HttpClient httpclient = new DefaultHttpClient();
			// 请求HttpClient，取得HttpResponse
			HttpResponse httpResponse = httpclient.execute(httpRequest);
			// 请求成功
			if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				// 取得返回的字符串
				String strResult = EntityUtils.toString(httpResponse.getEntity());
				return strResult;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 读取外部链接地址
	 * @param args
	 * @throws UnsupportedEncodingException
	 */
	public static List readUrls(String location) {
		File file = new File(location);
		List urls = new ArrayList();
		try {
			FileInputStream fs = new FileInputStream(file);
			BufferedReader br = new BufferedReader(new InputStreamReader(fs, "UTF-8"));
			String url;
			while ((url = br.readLine()) != null) {
				if (StringUtils.isNotBlank(url)) {
					urls.add(url);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return urls;
	}
	
	public static void main(String[] args) {
		List urls = HttpClientUtil.readUrls(LOCATION);
		for (int i = 0; i < urls.size(); i++) {
			try {
				System.out.println("第" + (i+1) + "个");
				String url = (String)urls.get(i);
				System.out.println(url);
				for (int j = 0; j < 1; j++) {
					System.out.println(getRespContent(url));
					System.out.println("----------------------------------");
				}
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("----------------------------------");
			}
		}
	}
}
