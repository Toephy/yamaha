package com.lixing.access;

import com.google.common.base.Objects;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Toephy on 2016/7/4 19:03
 */
public class CalculateApi {

    static SimpleDateFormat sdft = new SimpleDateFormat("yyyy:HH:mm:ss");
    static SimpleDateFormat sdft2 = new SimpleDateFormat("yyyy:MM:dd HH:mm:ss");

    static String filePath = "E:\\toephy2.txt";

    //static String startTime = "2016:00:00:00";
    //static String endTime = "2016:06:00:00";

    static String startTime = "2016:16:30:00";
    static String endTime = "2016:23:30:00";


    public static void main(String[] args) {
        Map<String, ApiEntry> apiEntryMap = new HashMap<String, ApiEntry>();
        try {
            long start = sdft.parse(startTime).getTime();
            long end = sdft.parse(endTime).getTime();
            File file = new File(filePath);
            BufferedReader in = new BufferedReader(new InputStreamReader((new FileInputStream(file))));
            String line;
            int i = 1;
            while ((line = in.readLine()) != null) {
                // 2016:00:03:10  14000  get_reply.m
                System.out.println(i ++);
                line = line.trim();
                String[] params = line.split("  ");
                if (params.length != 3) {
                    //System.out.println("无效的数据：" + line);
                } else {
                    try {
                        String clock = params[0];
                        if (!clock.contains("2016:"))
                            continue;

                        long now = sdft.parse(clock).getTime();

                        if (now >= start && now <= end) {
                            long time = Long.parseLong(params[1].trim());
                            String api = params[2];
                            if (!api.contains(".m"))
                                continue;
                            if (!api.endsWith(".m")) {
                                api = api.substring(0, api.indexOf(".m") + 2);
                            }

                            if (apiEntryMap.get(api) != null) {
                                ApiEntry apiEntry = apiEntryMap.get(api);
                                apiEntry.addCount(1);
                                apiEntry.addTime(time);
                                //apiEntry.calAvg();
                            } else {
                                apiEntryMap.put(api, new ApiEntry(api, time));
                            }
                            //System.out.println(apiEntryMap.get(api));
                        }
                    } catch (Exception e) {
                        //System.out.println("数据异常：" + line);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        List<ApiEntry> list = new ArrayList<ApiEntry>();
        for (String api : apiEntryMap.keySet()) {
            //System.out.println(apiEntryMap.get(api));
            ApiEntry apiEntry = apiEntryMap.get(api);
            apiEntry.calAvg();
            list.add(apiEntry);
        }

        // 按总访问次数输出
        Collections.sort(list);

        for (ApiEntry apiEntry : list) {
            System.out.println(apiEntry);
        }

        // 按平局执行时间输出
        Collections.sort(list, new Comparator<ApiEntry>() {
            @Override
            public int compare(ApiEntry o1, ApiEntry o2) {
                return o1.compareTo2(o2);
            }
        });

        for (ApiEntry apiEntry : list) {
            System.out.println(apiEntry);
        }
    }


    public static class ApiEntry implements Comparable<ApiEntry> {

        private String name;
        private int count = 1;
        private long time;
        private double avg;

        public ApiEntry(String name, long time) {
            this.name = name;
            this.time = time;
        }

        public ApiEntry(String name, int count, long time, double avg) {
            this.name = name;
            this.count = count;
            this.time = time;
            this.avg = avg;
        }

        public void addCount(int v) {
            this.count += v;
        }

        public void addTime(long t) {
            this.time += t;
        }

        public void calAvg() {
            this.avg = time / count;
        }

        //@Override
        public int compareTo2(ApiEntry o) {
            if (this.avg > o.getAvg())
                return -1;
            else if (this.avg < o.getAvg())
                return 1;
            return 0;
        }

        @Override
        public int compareTo(ApiEntry o) {
            if (this.count > o.getCount())
                return -1;
            else if (this.count < o.getCount())
                return 1;
            return 0;
        }

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public long getTime() {
            return time;
        }

        public void setTime(long time) {
            this.time = time;
        }

        public double getAvg() {
            return avg;
        }

        public void setAvg(double avg) {
            this.avg = avg;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return Objects.toStringHelper(this)
                    .add("name", name)
                    .add("count", count)
                    .add("time", time)
                    .add("avg", avg)
                    .toString();
        }
    }
}
