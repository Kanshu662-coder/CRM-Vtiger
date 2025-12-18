package contact1;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

import generic_utility.FileUtility;
import generic_utility.WebDriverUtility;

public class createcontacttest {
public static void main(String args[]) throws InterruptedException, IOException {
	
	//Version Control System - Git

//__________________GET DATA FROM PROPERTIES FILES____________________________________________	
//	FileInputStream fis = new FileInputStream("./src/test/resources/commondata.properties");
//	Properties pObj= new Properties();
//	pObj.load(fis);
	
	FileUtility futil = new FileUtility();
	
	String BROWSER = futil.getdatafrompropertyfile("browser");
	String URL = futil.getdatafrompropertyfile("url");
	String USERNAME = futil.getdatafrompropertyfile("un");
	String PASSWORD = futil.getdatafrompropertyfile("pwd");
	
//	String BROWSER = pObj.getProperty("browser");
//	String URL = pObj.getProperty("url");
//	String USERNAME = pObj.getProperty("un");
//	String PASSWORD = pObj.getProperty("pwd");

//___________________GET DATA FROM EXCEL FILE__________________________________________________	
	
	String value0 = futil.getdatafromexcelfile("details", 4, 0);
	String value1 = futil.getdatafromexcelfile("details", 4, 1);
	String value2 = futil.getdatafromexcelfile("details", 4, 2);
	String value3 = futil.getdatafromexcelfile("details", 4, 5);
	String value4 = futil.getdatafromexcelfile("details", 4, 4);
	String value5 = futil.getdatafromexcelfile("details", 4, 7);
	String value6 = futil.getdatafromexcelfile("details", 4, 8);
	String value7 = futil.getdatafromexcelfile("details", 4, 9);
	String value9 = futil.getdatafromexcelfile("details", 4, 10);
	
		System.out.println(value0);
		System.out.println(value1);
		System.out.println(value2);
		System.out.println(value3);
		System.out.println(value4);
		System.out.println(value5);
		System.out.println(value6);
		System.out.println(value7);
		System.out.println(value9);
	
////	FileInputStream fis1 = new FileInputStream("./src/test/resources/Test Script Data.xlsx");
////	Workbook wb = WorkbookFactory.create(fis1);
////	Sheet sh = wb.getSheet("TSD");
////	
////	String value0= sh.getRow(4).getCell(0).getStringCellValue();
////	String value1= sh.getRow(4).getCell(1).getStringCellValue();
////	String value2= sh.getRow(4).getCell(2).getStringCellValue();
////	String value3= sh.getRow(4).getCell(5).getStringCellValue();
////	String value4= sh.getRow(4).getCell(4).getStringCellValue();
////	String value5= sh.getRow(4).getCell(7).getStringCellValue();
////	String value6= sh.getRow(4).getCell(8).getStringCellValue();
////	String value7= sh.getRow(4).getCell(9).getStringCellValue();
////	String value9= sh.getRow(4).getCell(10).getStringCellValue();

//_____________________________THE CODE__________________________________________________________
	
	
	
	WebDriver driver=new FirefoxDriver();
	driver.manage().window().maximize();
	
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
	driver.get(URL);
	
	WebElement user=driver.findElement(By.name("user_name"));
	WebElement pass=driver.findElement(By.name("user_password"));
	user.sendKeys(USERNAME);
	pass.sendKeys(PASSWORD);
	
	WebElement loginbtn=driver.findElement(By.id("submitButton"));
	loginbtn.click();
	
	driver.findElement(By.linkText("Contacts")).click();
//_____________________________CREATE CONTACT___________________________________________________
	

	
	WebElement createcnt=driver.findElement(By.cssSelector("img[alt='Create Contact...']"));
	createcnt.click();
//_____________________________FILL THE FIELDS__________________________________________________	

	

	String FF=("Varun");
	driver.findElement(By.cssSelector("input[class='detailedViewTextBox']")).sendKeys(value0);
	String LastName= ("saxena");
	driver.findElement(By.name("lastname")).sendKeys(value1);
	
	Thread.sleep(3000);
	
	WebElement title=driver.findElement(By.id("title"));
	title.sendKeys(value2);
	
	WebElement phoneno=driver.findElement(By.id("assistantphone"));
	phoneno.sendKeys(value3);
	
	WebElement email=driver.findElement(By.id("email"));
	email.sendKeys(value4);
	
	driver.findElement(By.cssSelector("input[name='support_start_date']")).sendKeys(value5);  
	driver.findElement(By.cssSelector("input[name='support_end_date']")).sendKeys(value6);
	driver.findElement(By.cssSelector("input[name='portal']")).click();


	driver.findElement(By.cssSelector("input[name='mailingcity']")).sendKeys(value7);
	
	
	driver.findElement(By.cssSelector("input[name='mailingstate']")).sendKeys(value9);

	WebElement save=driver.findElement(By.cssSelector("input[name='button']"));
	save.click();
	
//_______________________________VERIFICATION______________________________________________________	


	String lastname=driver.findElement(By.id("dtlview_Last Name")).getText();
	
	if(LastName.equals(lastname)) {
		System.out.println("Lastname= "+LastName);
		System.out.println("lastname= "+lastname);
	System.out.println("Contact Creation successfull !!!");
	}
	
	else {
		System.out.println("Contact Creation failed");
	}
	
	
	WebElement userIcon = driver.findElement(By.xpath("//img[contains(@src,'user.PNG')]"));
//	Actions act = new Actions(driver);
//	act.moveToElement(userIcon).perform();
	driver.findElement(By.xpath("//a[text()='Sign Out']")).click();
	WebDriverUtility WDU = new WebDriverUtility(driver);
	WDU.hover(driver, userIcon);
	Thread.sleep(3000);
	driver.quit();
	
	}
}
