package com.lixing.myJunit.httpclient;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.List;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

public class ExcelUtil {
	
	public static void createExcel(String pathname, List<Label> labels) throws WriteException, IOException {
		File file = new File(pathname);
		if (!file.exists()) {
			FileUtil.createdirAndFile(file);
		}
		OutputStream os = new FileOutputStream(file);
		// 创建工作薄
		WritableWorkbook workbook = Workbook.createWorkbook(os);
		// 创建新的一页
		WritableSheet sheet = workbook.createSheet("First Sheet", 0);
		// 创建要显示的内容,创建一个单元格，第一个参数为列坐标，第二个参数为行坐标，第三个参数为内容
		for (int i = 0; i < labels.size(); i++) {
			sheet.addCell(labels.get(i));
			// System.out.println("excel写入进度: " + i + " / " + labels.size());
		}
		// 把创建的内容写入到输出流中，并关闭输出流
		workbook.write();
		workbook.close();
		os.close();
	}
	public static void main(String[] args) {
		try {
			List<Label> labels = Arrays.asList(new Label(0,0,"学校"), new Label(1,0,"专业"), new Label(2,0,"专业竞争力"));
			ExcelUtil.createExcel("E:\\test.xlsx", labels);
		} catch (WriteException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
