package com.lixing.novelclass;

import java.util.Map;
import java.util.TreeMap;

/**
 * Created by Toephy on 2016.10.17 16:32
 */
public class NovelClassTest {

    public static void main(String[] args) {
        //Map<String, NovelClass> classMap = new TreeMap<String, NovelClass>();

        int cid = 111;
        int parentCid = -1;
        String className = "男频";
        String englishName = "nanpin";
        //classMap.put(cid+"-"+className, new NovelClass(cid, parentCid, className, englishName));
        NovelClass O1 = new NovelClass(cid, parentCid, className, englishName);
        //NovelClass O2 = new NovelClass(cid, parentCid, className, englishName);
        NovelClass O2 = O1;
        System.out.println(O1 == O2);
        System.out.println(O1.equals(O2));
        System.out.println(O1.hashCode() == O2.hashCode());
    }
}
