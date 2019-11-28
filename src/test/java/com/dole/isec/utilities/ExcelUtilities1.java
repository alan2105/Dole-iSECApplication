/**
 * 
 */
package com.dole.isec.utilities;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.dole.isec.baseFiles.BaseTest;

/**
 * @author alagappan.n
 *
 */
public class ExcelUtilities1 {

	/**
	 * @param args
	 */
	XSSFSheet sheet;
	XSSFWorkbook workbook;
	FileInputStream fileinputstream;

	public void setupExcelFile(String path, String sheetname)
	{
		try {
			fileinputstream = new FileInputStream(path);
			workbook = new XSSFWorkbook(fileinputstream);
			sheet = workbook.getSheet(sheetname);
		}catch (FileNotFoundException e) {
			
			e.printStackTrace();
		}catch (IOException e) {
			
			e.printStackTrace();
		}
		 
	}
	public Object[][] readData(String path,String sheetname) throws IOException{
		Object[][] dataset = null;
		
		try {
		setupExcelFile(path,sheetname);
		XSSFRow row ;
		XSSFCell cell ;

		 dataset = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];

		for (int i =1;i<=sheet.getLastRowNum();i++){

			row = sheet.getRow(i);

			for(int j=0; j<row.getLastCellNum();j++){
				cell = row.getCell(j);
				 CellType type = cell.getCellType();
				if(type == CellType.STRING)
				{
				dataset[i-1][j]= cell.getStringCellValue();
				}
				else if(type == CellType.BOOLEAN)
				{
					dataset[i-1][j]= cell.getBooleanCellValue();
				}
				else if(type == CellType.NUMERIC)
				{
					dataset[i-1][j]= cell.getNumericCellValue();
				}
				else if(type == CellType.BLANK)
				{
					System.out.println("Getting cell is blank");
				}
				else
				{
					System.out.println("Cell type does not match");
				}
			}
		}
		return dataset;
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		return null;
	}

	public static void main(String[] args) throws IOException {
		
		ExcelUtilities1 eu = new ExcelUtilities1();
		Object[][] data= eu.readData(BaseTest.getFilePath(BaseTest.configData("testDataFilePath2")), "RegistrationDetails");
		System.out.println(Arrays.deepToString(data));
	}

}
