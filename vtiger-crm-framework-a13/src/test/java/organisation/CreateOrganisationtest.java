package organisation;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
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
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.Select;


public class CreateOrganisationtest {
	public static void main (String[] args) throws IOException {
		
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
		
		
		
		RemoteWebDriver driver= new FirefoxDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get(URL);
		
		WebElement username = driver.findElement(By.name("user_name"));
		WebElement pass =driver.findElement(By.name("user_password"));
		
		username.sendKeys(USERNAME);
		pass.sendKeys(PASSWORD);
		
		driver.findElement(By.id("submitButton")).click();
		
		driver.findElement(By.linkText("Organizations")).click();
		driver.findElement(By.cssSelector("img[alt='Create Organization...']")).click();
		String actualname = "technoraz" + Math.random();
		
		driver.findElement(By.name("accountname")).sendKeys(actualname);
		driver.findElement(By.id("employees")).sendKeys("500");
		driver.findElement(By.id("phone")).sendKeys("987654321");
		driver.findElement(By.name("email1")).sendKeys("akanshusaxena@gmail.com");
		
		
		WebElement dd= driver.findElement(By.name("industry"));
		Select sel = new Select(dd);
		sel.selectByValue("Engineering");
		
		
		
		driver.findElement(By.cssSelector("input[title='Save [Alt+S]']")).click();
		 // verification
		
	String finalname =	driver.findElement(By.id("dtlview_Organization Name")).getText();
		
		if(finalname.equals(actualname)) {
			System.out.println("organization is created");
		}else {
			System.out.println("organization is not created");
		}
		
		driver.quit();
			
	}

}
