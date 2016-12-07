package com.lixing.ssh;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import net.sf.expectit.Expect;
import net.sf.expectit.ExpectBuilder;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

import static net.sf.expectit.filter.Filters.removeColors;
import static net.sf.expectit.filter.Filters.removeNonPrintable;
import static net.sf.expectit.matcher.Matchers.contains;

public class JschTest {

    public static void main(String[] args) {
        JschTest jschTest = new JschTest();

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, -1);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String format = sdf.format(calendar.getTime());
        System.out.println(format);

        File charge = new File("/Users/yangxg/logs/" + format + "/charge.log");
        File expense = new File("/Users/yangxg/logs/" + format + "/expense.log");
        File iap = new File("/Users/yangxg/logs/" + format + "/iap.log");
        if (!charge.exists()) {
            charge.getParentFile().mkdirs();
        } else {
            FileUtils.deleteQuietly(charge);
        }
        if (!expense.exists()) {
            expense.getParentFile().mkdirs();
        } else {
            FileUtils.deleteQuietly(expense);
        }
        if (!iap.exists()) {
            iap.getParentFile().mkdirs();
        } else {
            FileUtils.deleteQuietly(iap);
        }
        jschTest.downChargeLog(format, charge);
        jschTest.downExpenseLog(format, expense);
        jschTest.downIapLog(format, iap);
    }

    public void downChargeLog(String date, File file) {
        FileWriter writer = null;
        Expect expect = null;
        Session session = null;
        Channel channel = null;
        try {
            JSch jsch = new JSch();

            String user = "yangxg";
            String host = "qd-relay02.ps.easou.com";
            int port = 20755;
            String privateKey = "/Users/yangxg/java/easou/Identity";
            jsch.addIdentity(privateKey, "yangxg18f7db6".getBytes());

            session = jsch.getSession(user, host, port);

            java.util.Properties config = new java.util.Properties();
            config.put("StrictHostKeyChecking", "no");
            session.setConfig(config);
            session.connect(5000);
            System.out.println(host + "已连接...");

            channel = session.openChannel("shell");
            channel.connect();

            writer = new FileWriter(file);
            expect = new ExpectBuilder()
                    .withOutput(channel.getOutputStream())
                    .withInputs(channel.getInputStream(), channel.getExtInputStream())
                    .withEchoInput(writer)
                    .withEchoOutput(System.out)
                    .withInputFilters(removeColors(), removeNonPrintable())
                    .withExceptionOnFailure()
                    .withTimeout(500, TimeUnit.SECONDS)
                    .withBufferSize(4096 * 2)
                    .build();
            expect.expect(contains("$"));
            expect.sendLine("su - pmrd");
            expect.expect(contains("assword:"));
            expect.sendLine("pmrd82609fc1");
            expect.expect(contains("$"));
            for (int i = 1; i <= 1; i++) {
                expect.sendLine("ssh qd01-book-api-rs0" + (String.valueOf(i).length() == 1 ? "0" + i : i));
                expect.expect(contains("$"));
                expect.sendLine("su - book");
                expect.expect(contains("assword:"));
                expect.sendLine("easou_Book@@)!^");
                expect.expect(contains("$"));
                expect.sendLine("cd /data/log/devlog/vipbook/");
                expect.expect(contains("$"));
                //合并当天的 charge.log
                expect.sendLine("cat charge.log." + date + "*" + " >> charge." + date + ".log");
                expect.expect(contains("$"));
                expect.sendLine("cat charge." + date + ".log");
                expect.expect(contains("$"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (channel != null) {
                channel.disconnect();
            }
            if (session != null) {
                session.disconnect();
            }
            if (expect != null) {
                try {
                    expect.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void downExpenseLog(String date, File file) {
        FileWriter writer = null;
        Expect expect = null;
        Session session = null;
        Channel channel = null;
        try {
            JSch jsch = new JSch();

            String user = "yangxg";
            String host = "qd-relay02.ps.easou.com";
            int port = 20755;
            String privateKey = "/Users/yangxg/java/easou/Identity";
            jsch.addIdentity(privateKey, "yangxg18f7db6".getBytes());

            session = jsch.getSession(user, host, port);

            java.util.Properties config = new java.util.Properties();
            config.put("StrictHostKeyChecking", "no");
            session.setConfig(config);
            session.connect(5000);
            System.out.println(host + "已连接...");

            channel = session.openChannel("shell");
            channel.connect();
            writer = new FileWriter(file);
            expect = new ExpectBuilder()
                    .withOutput(channel.getOutputStream())
                    .withInputs(channel.getInputStream(), channel.getExtInputStream())
                    .withEchoInput(writer)
                    .withEchoOutput(System.out)
                    .withInputFilters(removeColors(), removeNonPrintable())
                    .withExceptionOnFailure()
                    .withTimeout(500, TimeUnit.SECONDS)
                    .withBufferSize(4096 * 2)
                    .build();
            expect.expect(contains("$"));
            expect.sendLine("su - pmrd");
            expect.expect(contains("assword:"));
            expect.sendLine("pmrd82609fc1");
            expect.expect(contains("$"));
            for (int i = 1; i <= 1; i++) {
                expect.sendLine("ssh qd01-book-api-rs0" + (String.valueOf(i).length() == 1 ? "0" + i : i));
                expect.expect(contains("$"));
                expect.sendLine("su - book");
                expect.expect(contains("assword:"));
                expect.sendLine("easou_Book@@)!^");
                expect.expect(contains("$"));
                expect.sendLine("cd /data/log/devlog/vipbook/");
                expect.expect(contains("$"));
                //合并当天的 expense.log
                expect.sendLine("cat expense.log." + date + "*" + " >> expense." + date + ".log");
                expect.expect(contains("$"));
                expect.sendLine("cat expense." + date + ".log");
                expect.expect(contains("$"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (channel != null) {
                channel.disconnect();
            }
            if (session != null) {
                session.disconnect();
            }
            if (expect != null) {
                try {
                    expect.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void downIapLog(String date, File file) {
        FileWriter writer = null;
        Expect expect = null;
        Session session = null;
        Channel channel = null;
        try {
            JSch jsch = new JSch();

            String user = "yangxg";
            String host = "qd-relay02.ps.easou.com";
            int port = 20755;
            String privateKey = "/Users/yangxg/java/easou/Identity";
            jsch.addIdentity(privateKey, "yangxg18f7db6".getBytes());

            session = jsch.getSession(user, host, port);

            java.util.Properties config = new java.util.Properties();
            config.put("StrictHostKeyChecking", "no");
            session.setConfig(config);
            session.connect(5000);
            System.out.println(host + "已连接...");

            channel = session.openChannel("shell");
            channel.connect();
            writer = new FileWriter(file);
            expect = new ExpectBuilder()
                    .withOutput(channel.getOutputStream())
                    .withInputs(channel.getInputStream(), channel.getExtInputStream())
                    .withEchoInput(writer)
                    .withEchoOutput(System.out)
                    .withInputFilters(removeColors(), removeNonPrintable())
                    .withExceptionOnFailure()
                    .withTimeout(500, TimeUnit.SECONDS)
                    .withBufferSize(4096 * 2)
                    .build();
            expect.expect(contains("$"));
            expect.sendLine("su - pmrd");
            expect.expect(contains("assword:"));
            expect.sendLine("pmrd82609fc1");
            expect.expect(contains("$"));
            for (int i = 1; i <= 1; i++) {
                expect.sendLine("ssh qd01-book-api-rs0" + (String.valueOf(i).length() == 1 ? "0" + i : i));
                expect.expect(contains("$"));
                expect.sendLine("su - book");
                expect.expect(contains("assword:"));
                expect.sendLine("easou_Book@@)!^");
                expect.expect(contains("$"));
                expect.sendLine("cd /data/log/devlog/vipbook/");
                expect.expect(contains("$"));
                //合并当天的 charge.log
                expect.sendLine("cat iap.log." + date);
                expect.expect(contains("$"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (channel != null) {
                channel.disconnect();
            }
            if (session != null) {
                session.disconnect();
            }
            if (expect != null) {
                try {
                    expect.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
