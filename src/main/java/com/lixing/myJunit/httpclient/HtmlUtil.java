package com.lixing.myJunit.httpclient;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HtmlUtil {

	/**
	 * 获取指定HTML标签的指定属性的值
	 * 
	 * @param source
	 *            要匹配的源文本
	 * @param element
	 *            标签名称
	 * @param attr
	 *            标签的属性名称
	 * @return 属性值列表
	 */
	public static List<String> getAttr(String source, String element, String attr) {
		List<String> result = new ArrayList<String>();
		String reg = "<" + element + "[^<>]*?\\s" + attr + "=['\"]?(.*?)['\"]?\\s.*?>";
		Matcher m = Pattern.compile(reg).matcher(source);
		while (m.find()) {
			String r = m.group(1);
			result.add(r);
		}
		return result;
	}
	
	/**
	 * 获取指定标签内的文本内容
	 * 
	 * @param source
	 *            要匹配的源文本
	 * @param element
	 *            标签名称
	 * @return
	 */
	public static List<String> getContent(String source, String element) {
		String ss = "<" + element +".*?>([\\s\\S]*?)</" + element + ">";
		String temp = null;
		Pattern pa = Pattern.compile(ss);
		Matcher ma = null;
		ma = pa.matcher(source);
		List<String> result = new ArrayList<String>();
		while (ma.find()) {
			temp = ma.group(1);
			if (temp != null) {
				result.add(temp);
			}
		}
		return result;
	}
	
	/**
	 * 获取指定标签,指定样式的文本内容
	 * 
	 * @param source
	 *            要匹配的源文本
	 * @param element
	 *            标签名称
	 * @param css
	 *            样式名称
	 * @return
	 */
	public static List<String> getContent(String source, String element, String css) {
		String ss = "<" + element + " [ ]?" + css + ".*?>([\\s\\S]*?)</" + element + ">";
		String temp = null;
		Pattern pa = Pattern.compile(ss);
		Matcher ma = null;
		ma = pa.matcher(source);
		List<String> result = new ArrayList<String>();
		while (ma.find()) {
			temp = ma.group(1);
			if (temp != null) {
				result.add(temp);
			}
		}
		return result;
	}
	
	public static void main(String[] args) {
		String html = "<TD class=bg2></TD>";
		System.out.println(getContent(html, "TD").size());
		System.out.println(getContent(html, "TD"));
	}
}
