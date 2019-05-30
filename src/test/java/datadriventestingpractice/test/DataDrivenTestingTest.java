package datadriventestingpractice.test;


import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import constants.Constant;

public class DataDrivenTestingTest {
	
public static WebDriver driver;
	
	@Before
	public void setup() {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\Admin\\Downloads\\chromedriver_win32/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
	}
	
	@After
	public void teardown() {
		driver.quit();
	}
	
	@Test
	public void testLogin() {
		
		FileInputStream file = null;
			try {
				file = new FileInputStream(Constant.FILEPATH + Constant.FILENAME);
			} catch (FileNotFoundException e){}
			
			XSSFWorkbook workbook = null;
			try {
				workbook = new XSSFWorkbook(file);
			} catch (IOException e) {}
			
					
			for (int i = 1; i < 5; i++) {
				
				driver.get("http://thedemosite.co.uk/");
				WebElement checkElement1 = driver.findElement(By.xpath("/html/body/div/center/table/tbody/tr[2]/td/div/center/table/tbody/tr/td[2]/p/small/a[3]"));
				checkElement1.click();
				WebElement checkElement2 = driver.findElement(By.name("username"));
				checkElement2.click();
				
				
				XSSFSheet sheet = workbook.getSheetAt(0);
				
				XSSFCell cell1 = sheet.getRow(i).getCell(0);
				XSSFCell cell2 = sheet.getRow(i).getCell(1);
				
				checkElement2.sendKeys(cell1.getStringCellValue());
				WebElement checkElement3 = driver.findElement(By.name("password"));
				checkElement3.sendKeys(cell2.getStringCellValue());
				checkElement3.submit();
				
				WebElement checkElement4 = driver.findElement(By.xpath("/html/body/div/center/table/tbody/tr[2]/td/div/center/table/tbody/tr/td[2]/p/small/a[4]"));
				checkElement4.click();
				WebElement checkElement5 = driver.findElement(By.name("username"));
				checkElement5.sendKeys(cell1.getStringCellValue());
				WebElement checkElement6 = driver.findElement(By.name("password"));
				checkElement6.sendKeys(cell2.getStringCellValue());
				checkElement6.submit();
				
				WebElement checkElement7 = driver.findElement(By.xpath("/html/body/table/tbody/tr/td[1]/big/blockquote/blockquote/font/center/b"));
				assertTrue(checkElement7.isDisplayed());
				
				XSSFCell cell3 = sheet.getRow(i).createCell(2);
				if (checkElement7.isDisplayed()) {
					cell3.setCellValue("Login Successful");
				} else {
					cell3.setCellValue("Login Failed");
				}
				
				try {
					FileOutputStream out = new FileOutputStream(new File(Constant.FILEPATH + Constant.FILENAME));
					workbook.write(out);
					out.close();
					System.out.println("File saved without issue");
				} catch (IOException e){
					e.printStackTrace();
				}
				
			}
			
		
		
		
	}
	
}
