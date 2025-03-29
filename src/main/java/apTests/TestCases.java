
package apTests;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
//Selenium Imports
import java.util.logging.Level;
import org.apache.hc.client5.http.impl.routing.SystemDefaultRoutePlanner;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;

import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.BrowserType;
///
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class TestCases {
    RemoteWebDriver driver;

    public TestCases() throws MalformedURLException {
        System.out.println("Constructor: TestCases");

        final DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setBrowserName(BrowserType.CHROME);
        driver = new RemoteWebDriver(new URL("http://localhost:8082/wd/hub"), capabilities);


        // Set browser to maximize and wait
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

    }

    public void endTest() {
        System.out.println("End Test: TestCases");
        driver.close();
        driver.quit();

    }

    public void testCase01() throws InterruptedException {
        System.out.println("Start Test case: testCase01");
        driver.get("https://www.wikipedia.org/");

        String currentURL = driver.getCurrentUrl();
        if(currentURL.contains("wikipedia")){
            System.out.println("The current URL contains the expected title wikipedia");
            System.out.println("Testcase01 passed");
        }
        else{           
            System.out.println("Testcase01 Fail");
        }   
        System.out.println("End Test case: testCase01");
    }


    public void testCase02() throws InterruptedException {
        System.out.println("Start Test case: testCase02");
        
        WebElement header=driver.findElement(By.xpath("//*[@class='central-textlogo__image sprite svg-Wikipedia_wordmark']"));
        if (header.getText().contains("Wikipedia")){
            System.out.println("The header text contains Wikipedia");
        }
        else{
            System.out.println("Header text doesn't contain Wikipedia");
        }
        
        WebElement footertext1=driver.findElement(By.partialLinkText("Terms of Use"));
        if(footertext1.isDisplayed()){
            System.out.println("The footer link Terms of Use is displayed");
        }
        else{
            System.out.println("The footer link Terms of Use is NOT displayed");
        }

        WebElement footertext2=driver.findElement(By.partialLinkText("Privacy Policy"));
        if(footertext2.isDisplayed()){
            System.out.println("The footer link  Privacy Policy is displayed");
            System.out.println("Test case02 pass");
        }
        else{
            System.out.println("The footer link Privacy Policy is NOT displayed");
            System.out.println("Test case02 fail");
        }
  
        System.out.println("End Test case: testCase02");
    }

    public void testCase03() throws InterruptedException {
        System.out.println("Start Test case: testCase03");
        
        WebElement Searchbar=driver.findElement(By.xpath("//input[@id='searchInput']"));
        Searchbar.sendKeys("apple");
        boolean flag1=false;
        List<WebElement> Searchresult=driver.findElements(By.xpath("//*[@id='typeahead-suggestions']/div/a"));
        for(WebElement apple:Searchresult){             
            if(apple.getAttribute("href").contains("https://en.wikipedia.org/wiki/Apple_Inc.")){                                       
                apple.click(); 
                flag1=true;
                break;
            }
        }
        if(flag1){
            System.out.println("The apple inc link is clicked");
        }
        else{
            System.out.println("The apple inc link is NOT clicked");
        }  
  
        
        WebElement founder=driver.findElement(By.xpath("//*[@id='mw-content-text']/div[1]/table[1]/tbody/tr[9]/th"));
        String checkfounder=founder.getText();
        boolean flag=false;
        if(checkfounder.contains("Founders")){
            List<WebElement> founderlist=driver.findElements(By.xpath("//*[@id='mw-content-text']/div[1]/table[1]/tbody/tr[9]/td/div/ul/li"));
            for(WebElement element:founderlist){
                if(element.getText().contains("Steve Jobs"))
                {
                    flag=true;                    
                    break;
                }          
            }
            
        }
        else{
            System.out.println("Founder is not present in the page");        
        }

        if(flag){
            System.out.println("The Steve Jobs is present as a Founder");
        }
        else{
            System.out.println("The Steve Jobs is NOT present as a Founder");
        }

        System.out.println("End Test case: testCase03");
    }
    public void testCase04() throws InterruptedException {
            System.out.println("Start Test case: testCase04");
            driver.get("https://www.wikipedia.org/");
            
            WebElement Searchbar=driver.findElement(By.xpath("//input[@id='searchInput']"));
            Searchbar.sendKeys("microsoft");
            boolean flag1=false;
            List<WebElement> Searchresult=driver.findElements(By.xpath("//*[@id='typeahead-suggestions']/div/a"));
            for(WebElement microsoft:Searchresult){             
                if(microsoft.getAttribute("href").contains("https://en.wikipedia.org/wiki/Microsoft")){                                       
                    microsoft.click(); 
                    flag1=true;
                    break;
                }
            }
       
            if(flag1){
                System.out.println("Microsoft link is found and clicked");
            }
            else{
                System.out.println("Microsoft link is NOT found");
            }
                           
        List<WebElement> founder=driver.findElements(By.xpath("//*[@id='mw-content-text']/div[1]/table[1]/tbody/tr/th"));        
        boolean flag=false;
       

        for(WebElement checkfounder:founder){
            boolean flag2=false;
            if(checkfounder.getText().contains("Founders")){
                List<WebElement> foundername=driver.findElements(By.xpath("//*[@id='mw-content-text']/div[1]/table[1]/tbody/tr[8]/td/div/ul/li/a"));
                for(WebElement checkfoundername:foundername){
                    if(checkfoundername.getAttribute("title").contains("Bill Gates")){                                           
                        flag=true;
                        checkfoundername.click();
                        flag2=true;
                        break;
                    }
                }

            } 
            if(flag2) {
                break;
            }
           
        }
       
        if(flag){
            System.out.println("The Bill Gates is present as a Founder");
        }
        else{
            System.out.println("The SBill Gates is NOT present as a Founder");
        }

        String currentURL=driver.getCurrentUrl();
        if(currentURL.contains("/Bill_Gates")){
            System.out.println("Bill Gates is found in current URL");
            System.out.println("Testcase04 Pass");
        }
        else{
            System.out.println("Bill Gates is NOT found in current URL");
            System.out.println("Testcase04 Fail");
        }       
    }

    public void testCase05() throws InterruptedException {
        System.out.println("Start Test case: testCase05");
        
        driver.get("https://en.wikipedia.org/wiki/Main_Page");               
        Thread.sleep(3000);
        WebElement Mainmenu=driver.findElement(By.id("vector-main-menu-dropdown-checkbox"));
        Mainmenu.click();
        WebElement AboutWiki=driver.findElement(By.linkText("About Wikipedia"));
        AboutWiki.click();
        String currentURL=driver.getCurrentUrl();
        if(currentURL.contains("About")){
            System.out.println("The current URL contains About");   
            System.out.println("Testcase05 pass");     
        }
        else{
            System.out.println("The current URL does not contain About");                    
            System.out.println("Testcase05 Fail");     
        }
        System.out.println("End Test case: testCase05");
 } 
 
}
