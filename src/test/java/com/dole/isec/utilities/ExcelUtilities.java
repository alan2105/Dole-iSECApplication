/**
 * 
 */
package com.dole.isec.utilities;
import java.io.FileInputStream;
import java.util.Arrays;
import java.util.Iterator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import com.dole.isec.baseFiles.BaseTest;
/**
 * @author alagappan.n
 *
 */
public class ExcelUtilities {
private static final Logger log = LogManager.getLogger(ExcelUtilities.class);
	FileInputStream fis;
	XSSFWorkbook wb;
	XSSFSheet sht;
	Row row;
	Cell cell;
	Iterator<Cell> cellIterator;

	public Object[][] getExcelDate(String excelLocation, String sheetName) {
		try {
			Object datasets[][] = null;
			fis = new FileInputStream(excelLocation);
			// Create a workbook instance
			wb = new XSSFWorkbook(fis);
			// Get the Sheet from WorkBook
			sht = wb.getSheet(sheetName);
			// Get the total active row count in sheet
			int totalRow = sht.getLastRowNum()-2;
			//log.info("Total number active rows "+totalRow);
		
			// Get the total active column count in sheet
			int totalColumn = sht.getRow(2).getLastCellNum();
			// System.out.println(totalColumn);
			datasets = new Object[totalRow][totalColumn];
			Iterator<Row> rowIterator = sht.iterator();
			int i = 0;
			while (rowIterator.hasNext()) {
				i++;
				row = rowIterator.next();
				cellIterator = row.cellIterator();
				cell = cellIterator.next();
				if (cell.getStringCellValue().contains("LoginTestData")) 
				  {
					 rowIterator.next();
					 row= rowIterator.next();
					 i=1;
				  	}
				cellIterator = row.cellIterator();
				int j = 0;
				while (cellIterator.hasNext()) {
					j++;
					cell = cellIterator.next();
					switch (cell.getCellType()) {

					case STRING:
						datasets[i - 1][j - 1] = cell.getStringCellValue();
						break;
					case BOOLEAN:
						datasets[i - 1][j - 1] = cell.getBooleanCellValue();
						break;
					case NUMERIC:
						datasets[i - 1][j - 1] = cell.getNumericCellValue();
						break;
					case BLANK:
						log.info("Getting cell is blank");
						break;
					default:
						log.info("No matching cell Type");
						break;
					}

				}
			}
			return datasets;
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return null;
	}

	public static void main(String[] args) {
		String excelLocation = BaseTest.configData("testDataFilePath");
		ExcelUtilities eu = new ExcelUtilities();
		Object[][] data = eu.getExcelDate(BaseTest.getFilePath(excelLocation), "loginData");
		System.out.println(Arrays.deepToString(data));
	}
}
