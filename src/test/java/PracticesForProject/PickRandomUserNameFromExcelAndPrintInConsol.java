package PracticesForProject;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class PickRandomUserNameFromExcelAndPrintInConsol {

	public static void main(String[] args) throws Throwable 
	{
		Random random = new Random();
		int ran = random.nextInt(19);
		
		
		FileInputStream file = new FileInputStream("./src/test/resources/usernameand password.xlsx");
		Workbook wb = WorkbookFactory.create(file);
		Sheet sh = wb.getSheet("Sheet1");
		int lrow = sh.getLastRowNum();
		
		DataFormatter formater = new DataFormatter();
		
		String value = formater.formatCellValue(sh.getRow(ran).getCell(0));
		String value1 = formater.formatCellValue(sh.getRow(ran).getCell(1));
		
		System.out.println(value+" "+value1);
		
		
	}

}
