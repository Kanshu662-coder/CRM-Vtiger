package document;

import org.openqa.selenium.chrome.ChromeDriver;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

public class createDocumentationtest {

	public static void main (String[] args) throws IOException {
		
		WebDriver driver = new FirefoxDriver();
		
		FileInputStream fis = new FileInputStream("./src/test/resources/commondata.properties");
		
		Properties pObj= new Properties();
		pObj.load(fis);
		String BROWSER = pObj.getProperty("browser");
		String URL = pObj.getProperty("url");
		String USERNAME = pObj.getProperty("un");
		String PASSWORD = pObj.getProperty("pwd");
		
		
		FileInputStream fis1 = new FileInputStream("./src/test/resources/Test Script Data.xlsx");
		
		Workbook wb = WorkbookFactory.create(fis1);
			
		Sheet sh = wb.getSheet("TSD");
		
		String value1= sh.getRow(1).getCell(4).getStringCellValue();
		String value2= sh.getRow(2).getCell(3).getStringCellValue();
		String value3= sh.getRow(3).getCell(2).getStringCellValue();
		String value4= sh.getRow(4).getCell(1).getStringCellValue();
		
		System.out.println(value1);
		System.out.println(value2);
		System.out.println(value3);
		System.out.println(value4);
		
		
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		
		driver.get(URL);
		
		WebElement user = driver.findElement(By.name("user_name"));
		WebElement pass =driver.findElement(By.name("user_password"));
		
		user.sendKeys(USERNAME);
		pass.sendKeys(PASSWORD);
		
		driver.findElement(By.id("submitButton")).click();
		
		driver.findElement(By.linkText("Documents")).click();
		driver.findElement(By.cssSelector("img[alt='Create Document...']")).click();
		String actualtitle = "  day 2 test document" + Math.random();
		System.out.println("actualtitle ="+actualtitle);
		driver.findElement(By.name("notes_title")).sendKeys(actualtitle);
		
		driver.switchTo().frame(0);
		driver.findElement(By.xpath("//html[@dir='ltr']")).sendKeys("HI Team this is a test document for day 2");
			
		driver.switchTo().defaultContent();
		
				
		driver.findElement(By.cssSelector("input[value='  Save  \']")).click();
		
		
		//VERIFICATION
		String finaltitle = driver.findElement(By.id("mouseArea_Title")).getText(); 
	System.out.println("final title ="+finaltitle);
		if (finaltitle.equals(actualtitle)){
			System.out.print("documentation creation successfully:)");
			}
		else{
			System.out.print("Documentation creation failed :(");
			
			}
		driver.quit();
		
	}
}