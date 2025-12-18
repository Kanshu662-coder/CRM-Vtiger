package generic_utility;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class FileUtility {

	public String getdatafrompropertyfile(String key) throws IOException {
		
		FileInputStream fis = new FileInputStream("./src/test/resources/commondata.properties");
		Properties pObj= new Properties();
		pObj.load(fis);
		
		
		String value = pObj.getProperty(key);
		return value;
		}
		
//		String BROWSER = pObj.getProperty("browser");				-------->
//		String URL = pObj.getProperty("url");						-------->	insted of all these 4 lines we havewritten 1 line theats :-
//		String USERNAME = pObj.getProperty("un");					-------->				(String value = pObj.getProperty("key");)
//		String PASSWORD = pObj.getProperty("pwd");					-------->			

	
	
	public String getdatafromexcelfile (String sheetname, int rownumb, int cellnumb) throws EncryptedDocumentException, IOException {
		
		FileInputStream fis1 = new FileInputStream("./src/test/resources/Test Script Data.xlsx");
		Workbook wb = WorkbookFactory.create(fis1);
		Sheet sh = wb.getSheet(sheetname);
		String value = sh.getRow(rownumb).getCell(cellnumb).getStringCellValue();
		
		return value;		
	}
	
}
