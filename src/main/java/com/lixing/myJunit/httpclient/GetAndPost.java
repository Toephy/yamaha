package com.lixing.myJunit.httpclient;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import jxl.write.Label;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

public class GetAndPost {

	public static final String BASIC_URL = "http://www.npc.gov.cn/";
	public static final String PRE_PERSON = "http://www.npc.gov.cn/delegate/";
	public static final String PRE_PROVINCE = "http://www.npc.gov.cn/delegate/dbmd.action?id=";
	public static final String URL = "http://www.npc.gov.cn/delegate/dbmd.action?id=a1";
	public static final String CHARSET = "UTF-8";
	
	public static void main(String[] args) {
		try {
			Map<String, String> params = new HashMap<String, String>();
			HttpClient httpClient = new DefaultHttpClient();
			String baseHtml = doPost(httpClient, URL, params);
			// System.out.println(baseHtml);
			List<String> provinces = HtmlUtil.getAttr(baseHtml, "option", "value");
			// System.out.println(provinces);
			List<Label> labels = new ArrayList<Label>();
			labels.addAll(Arrays.asList(new Label(0, 0, "照片链接"),
					new Label(1, 0, "姓名"), new Label(2, 0, "代表团"), new Label(3, 0, "性别"), new Label(4, 0, "民族"), 
					new Label(5, 0, "籍贯"), new Label(6, 0, "出生年月"), new Label(7, 0, "党派"), new Label(8, 0, "毕业院校"), 
					new Label(9, 0, "所学专业"), new Label(10, 0, "学历"), new Label(11, 0, "学位"), new Label(12, 0, "现任职务")));
			int row = 1;
			for (String province : provinces) {
				String provinceUrl = PRE_PROVINCE + province;
				String provinceHtml = doPost(httpClient, provinceUrl, params);
				List<String> persons = HtmlUtil.getAttr(provinceHtml, "a", "href");
				for (String person : persons) {
					String personUrl = PRE_PERSON + person;
					String personHtml = doPost(httpClient, personUrl, params);
					personHtml = HtmlUtil.getContent(personHtml, "DIV", "align=center").get(0);
					List<String> personInfos = HtmlUtil.getContent(personHtml, "TD");
					if (!personInfos.isEmpty()) {
						int column = 0;
						String pictureUrl = BASIC_URL + HtmlUtil.getAttr(personInfos.get(0), "IMG", "src").get(0).trim();
						labels.add(new Label(column, row, pictureUrl));
						column++;
						for (int i = 2; i < personInfos.size(); i += 2) {
							labels.add(new Label(column, row, personInfos.get(i).trim()));
							column++;
						}
					}
					row++;
					System.out.println(row);
				}
			}
			// 写Excel
			System.out.println("开始写excel...");
			ExcelUtil.createExcel("E:\\personInfos.xlsx", labels);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void doGet(HttpClient httpClient, String url) {
		HttpGet get = null;
		try {
			get = new HttpGet(url);
			HttpResponse response = httpClient.execute(get);
			if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				String strResult = EntityUtils.toString(response.getEntity());
				System.out.println(strResult);
			}
		} catch (Exception e) {
		} finally {
			if (get != null) 
				get.releaseConnection();
		}
	}

	public static String doPost(HttpClient httpClient, String url, Map<String, String> params) {
		HttpPost post = null;
		String html = "";
		try {
			post = new HttpPost(url);
			Set<String> keys = params.keySet();
			StringBuffer str = new StringBuffer();
			for (String key : keys) {
				str.append(URLEncoder.encode(key, CHARSET)).append("=").append(URLEncoder.encode(params.get(key), CHARSET)).append("&");
			}
			StringEntity entity = new StringEntity(str.toString());
			entity.setContentEncoding(CHARSET);
			entity.setContentType("application/x-www-form-urlencoded");
			post.setEntity(entity);
			HttpResponse response = httpClient.execute(post);
			if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				html = EntityUtils.toString(response.getEntity());
			}
		} catch (Exception e) {
		} finally {
			if (post != null) 
				post.releaseConnection();
		}
		return html;
	}
	
	public static String setGetParams(String url, Map<String, String> params) {
		url += "&";
		Set<String> keys = params.keySet();
		for (String key : keys) {
			url = url + key + "=" + params.get(key) + "&";
		}
		url = url.substring(0, url.length() - 1);
		System.out.println("url = " + url);
		return url;
	}
	
}
