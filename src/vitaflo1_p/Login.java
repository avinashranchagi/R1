package vitaflo1_p;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class Login 
{
	private static String email="";
	private static String password="";
	private static int passed;
	private static int failed;
	
	public static void main(String[] args) throws EncryptedDocumentException, IOException 
	{
		File file= new File("./testdata/dataexcelsheet.xlsx");
		Workbook workbook= WorkbookFactory.create(file);
		ChromeOptions co= new ChromeOptions();
		co.addArguments("--remote-allow-origins=*");
		WebDriver driver= new ChromeDriver(co);
		//WebDriver driver= new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		for(int i= 1; i<=workbook.getSheet("Sheet1").getLastRowNum();i++)
		{
			try
			{
			driver.get("https://vitaflo-webinar.com/");
			Cell getemail = workbook.getSheet("Sheet1").getRow(i).getCell(0);
			Cell getpassword = workbook.getSheet("Sheet1").getRow(i).getCell(1);
			email = getemail.getStringCellValue();
			password = getpassword.getStringCellValue();
			
			driver.findElement(By.id("Email")).sendKeys(email);
			driver.findElement(By.id("Password")).sendKeys(password);
			driver.findElement(By.id("logbtn")).click();
			
				boolean popup= driver.findElement(By.id("myModal_pop")).isDisplayed();
				
				if(popup)
				{
				driver.findElement(By.id("modalclose")).click();
				}
			
				String title = driver.getTitle();
				if(title.contains("Home Page"))
				{
				passed++;
				System.out.println("Login passed for--->"+ email);	
				driver.findElement(By.id("logout")).click();
				}
			}
			
			catch(Exception e)
			{
				System.out.println("Login failed for--->"+ email);
				failed++;
			}
		}
		System.out.println(passed);
		System.out.println(failed);
		driver.quit();
		
	}
}