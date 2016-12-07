package com.lixing.myJunit.hashMapTest;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Toephy on 2016/5/23 15:07
 */
public class HashMapTest {

    public static void main(String[] args) {
        ConcurrentHashMap hashMap = new ConcurrentHashMap();
        HashMap<Item, String> map = new HashMap<Item, String>();
        Item item1 = new Item("1", 1);
        Item item2 = new Item("22", 22);
        Item item3 = new Item("333", 333);
        Item item4 = new Item("4444", 4444);

        map.put(item1, item1.getName());
        map.put(item2, item2.getName());
        map.put(item3, item3.getName());
        map.put(item4, item4.getName());
        map.put(item1, item1.getName());
        System.out.println(map);
        System.out.println(map.get(item1));
        System.out.println(map.get(item3));
        System.out.println(map.get(null));
    }

    static class Item {

        private String name;
        private int height;

        public Item(String name, int height) {
            this.name = name;
            this.height = height;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getHeight() {
            return height;
        }

        public void setHeight(int height) {
            this.height = height;
        }

        @Override
        public int hashCode() {
            return name.length() % 2 == 0 ? 0 : 1;
        }
    }
}
