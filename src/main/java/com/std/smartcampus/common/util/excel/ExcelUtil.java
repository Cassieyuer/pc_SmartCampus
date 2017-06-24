package com.std.smartcampus.common.util.excel;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;



public class ExcelUtil {
	
	private static int rowNum; //当前正在写的行号
	private static int colNum; //当前正在写的列号

	private ExcelUtil() {}

	/**
	 * 得到列名
	 */
	private static String getHeaderName(Field field) {
		Excel excel = field.getAnnotation(Excel.class);
		String name = field.getName();
		if (excel!=null && !"".equals(excel.column())) {
			name = excel.column();
		}
		return name;
	}	

	/** 
	 * 获取sheet名称
	 */
	private static String getSheet(Class<?> clazz) {
		Excel excel = clazz.getAnnotation(Excel.class);
		String sheet = clazz.getName();
		if (!"".equals(excel.sheet())) {
			sheet = excel.sheet();
		}
		return sheet;
	}
	
	/**
	 * 读取Excel文件
	 * @return 
	 */
	public static List<List<String>> readExcel(String path, String sheetName){
		if (path.endsWith(".xls")) {
			return Excel2003.readExcel(path, sheetName);
		} else if (path.endsWith(".xlsx")) {
			return Excel2007.readExcel(path, sheetName);
		}
		return null;
	}
	
	
	/** 
	 * 
	 */
	public static void writeExcel(String path, List<?> datas) {
		rowNum=0;
		colNum=0;
		if (path.endsWith(".xls")) {
			Excel2003.writeExcel(path, datas);
		} else if (path.endsWith(".xlsx")) {
			Excel2007.writeExcel(path, datas);
		}
		
	}


	private static class Excel2003{
		private static HSSFWorkbook hWb;//Excel文件
		private static HSSFSheet hSheet;// Sheet
		private static HSSFRow hRow;// Excel一行
		private static HSSFCell hCell ;//单元格
		private static HSSFCellStyle hStyle;// 单元格样式
		/**
		 * 将数据写入Excel
		 */
		public static void writeExcel(String path, List<?> datas){
			if (datas==null || datas.isEmpty()) {
				return ;
			}
			Class<?> clazz = datas.get(0).getClass();//获取保存实体类型
			Excel excel = clazz.getDeclaredAnnotation(Excel.class);
			if (excel != null) {
				Field[] fields = clazz.getDeclaredFields();//获取所有字段
				createBaseExcel(clazz);
				writeExcelHeader(fields);
				writeExcelBody(datas, fields);
				writeExcelFile(path);
			}
		}

		/** 
		 * 写入文件
		 */
		private static void writeExcelFile(String path) {
			try(FileOutputStream fout = new FileOutputStream(path)) {
				hWb.write(fout);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		/** 
		 * 写数据
		 */
		private static void writeExcelBody(List<?> datas, Field[] fields){
			try {
				for (Object data: datas) {
					hRow = hSheet.createRow(rowNum);
					for (Field field: fields) {
						if (Assert.isCanWriteExcel(field)) {
							field.setAccessible(true);// 第四步，创建单元格，并设置值
							hRow.createCell(colNum).setCellValue(String.valueOf(field.get(data)));
							++colNum;//列号+1
						}
					}
					++rowNum;//行号+1
					colNum = 0;//列号重置
				} 
			} catch (IllegalAccessException e) {
				e.printStackTrace();//反射异常
			}
		}

		/** 
		 * 写表头
		 */
		private static void writeExcelHeader(Field[] fields) {
			for (Field field: fields) {
				if (Assert.isCanWriteExcel(field)) {
					hCell = hRow.createCell(colNum);//创建单元格
					hCell.setCellValue(getHeaderName(field));//设置单元格内容
					hCell.setCellStyle(hStyle);//设置样式
					++colNum;//列号+1
				}
			}
			++rowNum;//行号+1
			colNum = 0;//列号重置
		}
		/** 
		 * 创建Excel基本信息
		 * @param clazz 
		 */
		private static void createBaseExcel(Class<?> clazz) {
			hWb = new HSSFWorkbook();// 第一步，创建一个webbook，对应一个Excel文件
			hSheet = hWb.createSheet(getSheet(clazz));// 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
			hRow = hSheet.createRow(rowNum);// 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
			hStyle = hWb.createCellStyle();// 第四步，创建单元格，并设置值表头 设置表头居中
			hCell = hRow.createCell(colNum);//左上角单元格
			hStyle.setAlignment(HorizontalAlignment.CENTER); // 创建一个居中格式
		}

		/**
		 * 读取Excel文件
		 * @return 
		 */
		@SuppressWarnings("resource")
		public static List<List<String>> readExcel(String path, String sheetName){
			List< List<String> > result = new ArrayList<>();
			try (InputStream in = new FileInputStream(path)) {
				HSSFWorkbook wb = new HSSFWorkbook(in);
				HSSFSheet sheet = wb.getSheet(sheetName);
				int rowNum = sheet.getLastRowNum();
				for (int i = 0; i <=rowNum; i++) {
					HSSFRow row = sheet.getRow(i);
					int colNum = row.getLastCellNum();
					List<String> rowList  = new ArrayList<>();
					for (int j = 0; j < colNum; j++) {
						HSSFCell cell = row.getCell(j);
						cell.setCellType(CellType.STRING);
						String value = cell.getStringCellValue();
						rowList.add(value);
					}
					result.add(rowList);
				}
				return result;
			} catch (IOException e) {
				e.printStackTrace();
			}
			return result;
		}
	}

	private static class Excel2007{
		private static XSSFWorkbook xWb;//Excel文件
		private static XSSFSheet xSheet;// Sheet
		private static XSSFRow xRow;// Excel一行
		private static XSSFCell xCell ;//单元格
		private static XSSFCellStyle xStyle;// 单元格样式
		/**
		 * 将数据写入Excel
		 */
		public static void writeExcel(String path, List<?> datas){
			if (datas==null || datas.isEmpty()) {
				return ;
			}
			Class<?> clazz = datas.get(0).getClass();//获取保存实体类型
			Excel excel = clazz.getDeclaredAnnotation(Excel.class);
			if (excel != null) {
				Field[] fields = clazz.getDeclaredFields();//获取所有字段
				createBaseExcel(clazz);
				writeExcelHeader(fields);
				writeExcelBody(datas, fields);
				writeExcelFile(path);
			}
		}

		/** 
		 * 写入文件
		 */
		private static void writeExcelFile(String path) {
			try(FileOutputStream fout = new FileOutputStream(path)) {
				xWb.write(fout);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		/** 
		 * 写数据
		 */
		private static void writeExcelBody(List<?> datas, Field[] fields){
			try {
				for (Object data: datas) {
					xRow = xSheet.createRow(rowNum);
					for (Field field: fields) {
						if (Assert.isCanWriteExcel(field)) {
							field.setAccessible(true);// 第四步，创建单元格，并设置值
							xRow.createCell(colNum).setCellValue(String.valueOf(field.get(data)));
							++colNum;//列号+1
						}
					}
					++rowNum;//行号+1
					colNum = 0;//列号重置
				} 
			} catch (IllegalAccessException e) {
				e.printStackTrace();//反射异常
			}
		}

		/** 
		 * 写表头
		 */
		private static void writeExcelHeader(Field[] fields) {
			for (Field field: fields) {
				if (Assert.isCanWriteExcel(field)) {
					xCell = xRow.createCell(colNum);//创建单元格
					xCell.setCellValue(getHeaderName(field));//设置单元格内容
					xCell.setCellStyle(xStyle);//设置样式
					++colNum;//列号+1
				}
			}
			++rowNum;//行号+1
			colNum = 0;//列号重置
		}
		/** 
		 * 创建Excel基本信息
		 * @param clazz 
		 */
		private static void createBaseExcel(Class<?> clazz) {
			xWb = new XSSFWorkbook();// 第一步，创建一个webbook，对应一个Excel文件
			xSheet = xWb.createSheet(getSheet(clazz));// 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
			xRow = xSheet.createRow(rowNum);// 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
			xStyle = xWb.createCellStyle();// 第四步，创建单元格，并设置值表头 设置表头居中
			xCell = xRow.createCell(colNum);//左上角单元格
			xStyle.setAlignment(HorizontalAlignment.CENTER); // 创建一个居中格式
		}
		/**
		 * 读取Excel 2007+文件
		 * @return 
		 */
		@SuppressWarnings("resource")
		public static List<List<String>> readExcel(String path, String sheetName){
			List< List<String> > result = new ArrayList<>();
			try (InputStream in = new FileInputStream(path)) {
				XSSFWorkbook wb = new XSSFWorkbook(in);
				XSSFSheet sheet = wb.getSheet(sheetName);
				int rowNum = sheet.getLastRowNum();
				for (int i = 0; i <=rowNum; i++) {
					XSSFRow row = sheet.getRow(i);
					int colNum = row.getLastCellNum();
					List<String> rowList  = new ArrayList<>();
					for (int j = 0; j < colNum; j++) {
						XSSFCell cell = row.getCell(j);
						cell.setCellType(CellType.STRING);
						String value = cell.getStringCellValue();
						rowList.add(value);
					}
					result.add(rowList);
				}
				return result;
			} catch (IOException e) {
				e.printStackTrace();
			}
			return result;
		}
	}

}