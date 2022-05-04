import org.junit.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.interactions.Actions;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import com.google.gson.Gson;
import java.io.FileReader;
import java.io.Reader;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;


public class Selenium_Testing {
    public WebDriver driver;
	private WebDriverWait wait;
	

    
    @Before
    public void setup() {
        WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
        driver.manage().window().maximize();
		wait = new WebDriverWait(driver, 10);
    }
	
	//complex xpath (eg. //div//a[@href='asd'])
	private By cslink = By.xpath("/html/body/div[1]/div[1]/div/header/div/div[2]/nav/ul/li[3]/div/div/div/div/ul/li[7]/a");
	//complex xpath (eg. //div//a[@href='asd'])
	private By explore = By.xpath("/html/body/div[2]/div[2]/section[1]/div/div/div/div[2]/a");
	//complex xpath (eg. //div//a[@href='asd'])
	private By explore1 = By.xpath("/html/body/div[1]/div[1]/div/main/div/div[1]/div[3]/div/div/div/div[1]/form/div/div/div/div/input");
	private final By bodyLocator = By.tagName("body");
	
	
	private WebElement waitVisibiiltyAndFindElement(By locator) {
        this.wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        return this.driver.findElement(locator);
    }
	
	
    @Test
    public void LogINOUTtest() {
        MainPage mainPage = new MainPage(this.driver);	
        //Reading the page title
        //Static Page test		
		System.out.println("Page title is : " + this.driver.getTitle());
		System.out.println("Task completed : Reading the page title");
		Assert.assertTrue(this.driver.getTitle().contains("edX | Free Online Courses by Harvard, MIT, & more | edX"));
		
		//Fill simple form and send (eg. Login)
        LoginPage loginPage = mainPage.openLogin();
		
        DashboardPage dashboardPage = loginPage.login("polmezerdi@tozya.com", "elte2022");
		//System.out.println("Task completed : Fill simple form and send (eg. Login)");
        System.out.println(dashboardPage.dashboardTitle());
		Assert.assertTrue(dashboardPage.dashboardTitle().contains("My Courses"));
		
		//Logout
		//Filling or reading drop-down
        mainPage = dashboardPage.Logout();
    }
	
	@Test
    public void hovertest() {
		//Hover test
        MainPage mainPage = new MainPage(this.driver);
		//complex xpath (eg. //div//a[@href='asd'])
		WebElement element = this.driver.findElement(By.xpath("/html/body/div[1]/div[1]/div/header/div/div[2]/nav/ul/li[3]/div/button"));
		Actions action = new Actions(this.driver);
		action.moveToElement(element).perform();
		this.driver.manage().timeouts().implicitlyWait(500, TimeUnit.SECONDS);
		WebDriverWait wait = new WebDriverWait(this.driver, 500);
		
		//Send a form
		wait.until(ExpectedConditions.visibilityOfElementLocated(cslink)).click();
		System.out.println("Task completed : HoverTest");
		
		
    }
	
	@Test
    public void UserFormSending() {
		
		//Fill input (text,radio,check...)
        MainPage mainPage = new MainPage(this.driver);
        LoginPage loginPage = mainPage.openLogin();
        DashboardPage dashboardPage = loginPage.login("polmezerdi@tozya.com", "elte2022");
		WebDriverWait wait = new WebDriverWait(this.driver, 10);
		wait.until(ExpectedConditions.visibilityOfElementLocated(explore)).click();
		WebElement searchBarElement = waitVisibiiltyAndFindElement(explore1);
		//Form sending with user
		searchBarElement.sendKeys("software testing \n");
		WebElement bodyElement = waitVisibiiltyAndFindElement(bodyLocator);
		Assert.assertTrue(bodyElement.getText().contains("Courses"));
		//History test (browser back button)
		this.driver.navigate().back();

	}
	
	@Test
    public void MultiplePage() {
		//Multiple page test from array (easily extendable static page tests)
        MainPage mainPage = new MainPage(this.driver);
        List<WebElement> links = this.driver.findElements(By.name("a"));
		System.out.println("No of links are " + links.size());
		for(int i = 0;i<links.size();i++)
		{
			WebElement E1 = links.get(i);
			String url = E1.getAttribute("href");
		}
	}
	
	
	
    
    @After
    public void close() {
        if (driver != null) {
            driver.quit();
        }
    }
}
