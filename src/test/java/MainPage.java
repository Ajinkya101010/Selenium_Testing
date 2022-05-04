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


class MainPage extends PageBase {
	//complex xpath (eg. //div//a[@href='asd'])

    private By loginMenuButton = By.xpath("/html/body/div[1]/div[1]/div/header/div/div[4]/div[3]/nav/a[1]");
    public MainPage(WebDriver driver) {
        super(driver);
        this.driver.get("https://www.edx.org/");
    }    
    
    public LoginPage openLogin() {
        this.waitAndReturnElement(loginMenuButton).click();
        return new LoginPage(this.driver);
    }
}
