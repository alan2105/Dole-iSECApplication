/**
 * 
 */
package com.dole.isec.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;


import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * @author alagappan.n
 *
 */
public class ExcelUtilis {

	/**
	 * @param args
	 */
	public String[][] readdata(String path,String sheetname) throws IOException{

		File file = new File(path);
		FileInputStream fileinputstream = new FileInputStream(file);
		XSSFWorkbook workbook = new XSSFWorkbook(fileinputstream);
		XSSFSheet sheet = workbook.getSheet(sheetname);
		XSSFRow row ;
		XSSFCell cell ;

		String[][] dataset = new String[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];

		for (int i =1;i<=sheet.getLastRowNum();i++){

			row = sheet.getRow(i);

			for(int j=0; j<row.getLastCellNum();j++){
				cell = row.getCell(j);

				dataset[i-1][j]= cell.getStringCellValue();
				//System.out.print(i-1 + " "+ j);	
				System.out.println(" ");
				System.out.print(dataset[i-1][j]);
			}


		}

		return dataset;

	}
	
	
	public static void main(String[] args) throws IOException {
		
		ExcelUtilis eu = new ExcelUtilis();
	System.out.println(eu.readdata("D:\\Automation-Projects\\Dole-iSECApplication\\resource\\Testdata1.xlsx", "RegistrationDetails"));
	}

}
