import org.junit.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.TimeUnit;



class DashboardPage extends PageBase {
	//complex xpath (eg. //div//a[@href='asd'])
    private By dashboard = By.xpath("/html/body/div[2]/div[2]/section[1]/main/section/header/h2");
	//complex xpath (eg. //div//a[@href='asd'])
    private By profileDropDown = By.xpath("/html/body/div[2]/header/div[2]/div[2]/div[2]/div[3]/div[1]/span");
	//complex xpath (eg. //div//a[@href='asd'])
    private By singoutButton = By.xpath("/html/body/div[2]/header/div[2]/div[2]/div[2]/div[3]/div[2]/div[5]/a");
	//complex xpath (eg. //div//a[@href='asd'])
    private By explorecourses = By.xpath("/html/body/div[2]/div[2]/section[1]/div/div/div/div[2]/a");

    public DashboardPage(WebDriver driver) {
        super(driver);
    }    
    
    public String dashboardTitle() {
		return this.waitAndReturnElement(dashboard).getText();
    }

    public Explorecourses visitexplorecourses() {
		this.waitAndReturnElement(explorecourses).click();
        return new Explorecourses(driver);
    }

	
    public MainPage Logout() {
		//Filling or reading drop-down
        this.waitAndReturnElement(profileDropDown).click();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        this.waitAndReturnElement(singoutButton).click();
        return new MainPage(driver);
    }
}
