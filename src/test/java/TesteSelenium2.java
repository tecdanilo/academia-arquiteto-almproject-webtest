import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;

public class TesteSelenium2 {
	private WebDriver driver;
	private StringBuffer verificationErrors = new StringBuffer();
        
	@Before
	public void setUp() throws Exception {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Test
	public void testESelenium2() throws Exception {
		driver.get("http://localhost:8080/almproject/index.jsp");

		driver.findElement(By.name("ano")).clear();
		driver.findElement(By.name("ano")).sendKeys("2001");
		driver.findElement(By.cssSelector("input[type=\"submit\"]")).click();
		assertTrue("ano não deve ser bissexto", driver.findElement(By.id("resposta")).getText().contains("não é bissexto"));		
		driver.findElement(By.linkText("Voltar")).click();
		
		driver.findElement(By.name("ano")).clear();
		driver.findElement(By.name("ano")).sendKeys("2000");
		driver.findElement(By.cssSelector("input[type=\"submit\"]")).click();
		assertFalse("Ano deve ser bissexto", driver.findElement(By.id("resposta")).getText().contains("não é bissexto"));		

		driver.findElement(By.linkText("Voltar")).click();
	}

	@After
	public void tearDown() throws Exception {
		driver.quit();
		String verificationErrorString = verificationErrors.toString();
		if (!"".equals(verificationErrorString)) {
			fail(verificationErrorString);
		}
	}

	private boolean isElementPresent(By by) {
		try {
			driver.findElement(by);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}
}


