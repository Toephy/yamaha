package com.lixing.access;

import com.google.common.base.Objects;
import org.apache.commons.lang.StringUtils;

import java.io.*;
import java.text.NumberFormat;
import java.util.*;

/**
 * Created by Toephy on 2016/7/5 17:43
 */
public class AffectCalculate {

    static String lowfilePath = "E:\\lowsortByAvg.xml";
    static String highfilePath = "E:\\highsortByAvg.xml";

    public static void main(String[] args) {
        try {
            NumberFormat nbf= NumberFormat.getInstance();
            nbf.setMinimumFractionDigits(2);

            Map<String, CalculateApi.ApiEntry> lowMap = new HashMap<String, CalculateApi.ApiEntry>();
            Map<String, CalculateApi.ApiEntry> highMap = new HashMap<String, CalculateApi.ApiEntry>();

            int lowTotalCount = 0;
            long lowTotalTime = 0;
            int highTotalCount = 0;
            long highTotalTime = 0;

            int totalCountRise = 0;
            long totalTimeRise = 0;

            // 初始化数据
            File lowFile = new File(lowfilePath);
            BufferedReader lowIn = new BufferedReader(new InputStreamReader((new FileInputStream(lowFile))));
            String lowLine;
            while ((lowLine = lowIn.readLine()) != null) {
                CalculateApi.ApiEntry lowApiEntry = assembleApiEntry(lowLine);
                if (lowApiEntry != null) {
                    lowMap.put(lowApiEntry.getName(), lowApiEntry);
                    lowTotalCount += lowApiEntry.getCount();
                    lowTotalTime += lowApiEntry.getTime();
                }
            }

            File highFile = new File(highfilePath);
            BufferedReader highIn = new BufferedReader(new InputStreamReader((new FileInputStream(highFile))));
            String highLine;
            while ((highLine = highIn.readLine()) != null) {
                CalculateApi.ApiEntry highApiEntry = assembleApiEntry(highLine);
                if (highApiEntry != null) {
                    highMap.put(highApiEntry.getName(), highApiEntry);
                    highTotalCount += highApiEntry.getCount();
                    highTotalTime += highApiEntry.getTime();
                }
            }

            // 计算增长率
            totalCountRise = highTotalCount - lowTotalCount;
            totalTimeRise = highTotalTime - lowTotalTime;

            List<AffectEntry> affectEntryList = new ArrayList<AffectEntry>();

            for (String apiName : highMap.keySet()) {
                CalculateApi.ApiEntry highEntry = highMap.get(apiName);
                CalculateApi.ApiEntry lowEntry = lowMap.get(apiName);
                if (highEntry != null && lowEntry != null) {
                    String name = highEntry.getName();
                    int countRise = highEntry.getCount() - lowEntry.getCount();
                    long timeRise = highEntry.getTime() - lowEntry.getTime();
                    double avgRise = highEntry.getAvg() - lowEntry.getAvg();
                    if (countRise > 0 && timeRise > 0) {
                        double countRiseRate =  Double.valueOf(nbf.format((double) countRise / (double) totalCountRise));
                        double timeRiseRate = Double.valueOf(nbf.format((double) timeRise / (double) totalTimeRise));
                        AffectEntry affectEntry = new AffectEntry(name, countRise, timeRise, countRiseRate, timeRiseRate, avgRise);
                        affectEntryList.add(affectEntry);
                    }
                }
            }

            // 按访问次数增长率排序输出
            Collections.sort(affectEntryList, new Comparator<AffectEntry>() {
                @Override
                public int compare(AffectEntry o1, AffectEntry o2) {
                    return o1.compareWithCountRiseRate(o2);
                }
            });

            for (AffectEntry apiEntry : affectEntryList) {
                System.out.println(apiEntry);
            }

            // 按访问时间增长率排序输出
            Collections.sort(affectEntryList, new Comparator<AffectEntry>() {
                @Override
                public int compare(AffectEntry o1, AffectEntry o2) {
                    return o1.compareWithTimeRiseRate(o2);
                }
            });

            for (AffectEntry apiEntry : affectEntryList) {
                System.out.println(apiEntry);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static CalculateApi.ApiEntry assembleApiEntry(String line) {
        // ApiEntry{name=expenseHistoryNew.m, count=11, time=119000, avg=10818.0}
        if (StringUtils.isNotBlank(line)) {
            try {
                line = line.substring(line.indexOf("{") + 1, line.indexOf("}"));
                // name=expenseHistoryNew.m, count=11, time=119000, avg=10818.0
                String[] params = line.split(",");
                String name = params[0].substring(params[0].indexOf("=") + 1).trim();
                String count = params[1].substring(params[1].indexOf("=") + 1).trim();
                String time = params[2].substring(params[2].indexOf("=") + 1).trim();
                String avg = params[3].substring(params[3].indexOf("=") + 1).trim();

                CalculateApi.ApiEntry apiEntry = new CalculateApi.ApiEntry(name, Integer.valueOf(count), Long.valueOf(time), Double.valueOf(avg));
                return apiEntry;
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static class AffectEntry {

        private String name;
        private int countRise;
        private long timeRise;
        private double avgRise;
        private double countRiseRate;
        private double timeRiseRate;

        public AffectEntry(String name, int countRise, long timeRise, double countRiseRate, double timeRiseRate, double avgRise) {
            this.name = name;
            this.countRise = countRise;
            this.timeRise = timeRise;
            this.countRiseRate = countRiseRate;
            this.timeRiseRate = timeRiseRate;
            this.avgRise = avgRise;
        }

        public int compareWithCountRiseRate(AffectEntry o) {
            if (this.countRiseRate > o.getCountRiseRate())
                return -1;
            else if (this.countRiseRate < o.getCountRiseRate())
                return 1;
            return 0;
        }

        public int compareWithTimeRiseRate(AffectEntry o) {
            if (this.timeRiseRate > o.getTimeRiseRate())
                return -1;
            else if (this.timeRiseRate < o.getTimeRiseRate())
                return 1;
            return 0;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getCountRise() {
            return countRise;
        }

        public void setCountRise(int countRise) {
            this.countRise = countRise;
        }

        public long getTimeRise() {
            return timeRise;
        }

        public void setTimeRise(long timeRise) {
            this.timeRise = timeRise;
        }

        public double getCountRiseRate() {
            return countRiseRate;
        }

        public void setCountRiseRate(double countRiseRate) {
            this.countRiseRate = countRiseRate;
        }

        public double getTimeRiseRate() {
            return timeRiseRate;
        }

        public void setTimeRiseRate(double timeRiseRate) {
            this.timeRiseRate = timeRiseRate;
        }

        @Override
        public String toString() {
            return Objects.toStringHelper(this)
                    .add("name", name)
                    .add("countRise", countRise)
                    .add("timeRise", timeRise)
                    .add("avgRise", avgRise)
                    .add("countRiseRate", countRiseRate)
                    .add("timeRiseRate", timeRiseRate)
                    .toString();
        }
    }
}
