package OrangeHRM.TestScript;

import java.io.IOException;
import java.lang.reflect.Method;
import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import OrangeHRM.PageObject.LoginPage;
import Util.ExcelUtils;
import Util.LogUtil;
import Util.ReportUtil;

public class LoginTest {

	WebDriver driver;
	LoginPage loginPage;
	
	@BeforeSuite
	public void beforeSuite() {
	    ReportUtil.initializeReport("C:/Users/THIS PC/eclipse-workspace/shivin_tech/TestProject_OrangeHRM/Reports/Report.html");
	}

	@BeforeMethod
	public void setup(Method method) {
		
		ReportUtil.startTest(method.getName());

		driver = new ChromeDriver();
		driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		loginPage = new LoginPage(driver);

	}

	@DataProvider(name = "loginDataProvider")
	public Object[][] getDataFromDataProvider() throws IOException {
		return ExcelUtils.getTestData(
				"C:\\Users\\THIS PC\\eclipse-workspace\\shivin_tech\\TestProject_OrangeHRM\\src\\test\\java\\Util\\TestDatas.xlsx",
				"Sheet1");
	}

	@Test(dataProvider = "loginDataProvider")
	public void loginTest(String username, String password) {
		LogUtil.logger.info("Attempting to login with Username: " + username + " and Password: " + password);
		
		loginPage.enterUsername(username);
		loginPage.enterPassword(password);
		loginPage.clickLogin();
	}

	@AfterMethod
	public void teardown(ITestResult result) {
		if (result.getStatus() == ITestResult.FAILURE) {
	        ReportUtil.test.fail(result.getThrowable());
	    }
		driver.quit();
		
		ReportUtil.endTest();

	}

}
