/**
 * 
 */
package com.dole.isec.utilities;


import java.io.FileInputStream;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * @author alagappan.n
 *
 */
public class ExcelUtils {
	
	FileInputStream fis;
	XSSFWorkbook wb;
	XSSFSheet sht;
	Row row;
	Cell cell;
	
	public Object[][] getExcelDate(String excelLocation, String sheetName)
	{
		try
		{
			Object datasets[][] =null;
			fis = new FileInputStream(excelLocation);
			// Create a workbook instance
			wb = new XSSFWorkbook(fis);
			// Get the Sheet from WorkBook
			sht = wb.getSheet(sheetName);
			// Get the total active row count in sheet
			int totalRow = sht.getLastRowNum();
		//	System.out.println(totalRow);
			// Get the total active column count in sheet
			int totalColumn = sht.getRow(0).getLastCellNum();
			//System.out.println(totalColumn);
			datasets = new String[totalRow][totalColumn-1];
			Iterator<Row> rowIterator = sht.iterator();
			int i =0;
			while(rowIterator.hasNext())
			{
				i++;
				row = rowIterator.next();
					Iterator<Cell> cellIterator = row.cellIterator();
					int j=0;
						while(cellIterator.hasNext()) {
				cell = cellIterator.next();
				if (cell.getStringCellValue().contains("Start")) {
					i = 0;
					break;
				}
				cell = cellIterator.next();
				switch(cell.getCellType()) {
				
				case STRING:
					datasets[i-1][j++] = cell.getStringCellValue();
					break;
				case BOOLEAN:
					datasets[i-1][j++] = cell.getBooleanCellValue();
					break;
				case NUMERIC:
					datasets[i-1][j++] = cell.getNumericCellValue();
					break;
				case BLANK:
					System.out.println("Getting cell is blank");
					break;
				default:
					System.out.println("No matching cell Type");
					break;
				}
			}
		}
		return datasets;	
	}catch(Exception e){
			System.out.println(e.getMessage());
		}
	return null;
	}
	
	
	public static void main(String[] args) {
		String excelLocation = "D:\\Automation-Projects\\Dole-iSECApplication\\src\\test\\java\\com\\dole\\isec\\utilities\\testData.xlsx";
		ExcelUtils eu = new ExcelUtils();
		Object[][] data = eu.getExcelDate(excelLocation, "loginData");
		System.out.println(data.toString());
	}
}
