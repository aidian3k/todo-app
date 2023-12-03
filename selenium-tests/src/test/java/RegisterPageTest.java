import io.github.bonigarcia.wdm.WebDriverManager;
import org.assertj.core.api.Assertions;
import org.example.login.LoginPageActWebElements;
import org.example.register.RegisterPageActWebElements;
import org.example.register.RegisterPageVerifyWebElements;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.List;
import java.util.concurrent.TimeUnit;

class RegisterPageTest {
  private WebDriver webDriver;
  private RegisterPageVerifyWebElements registerPageVerifyWebElements;
  private RegisterPageActWebElements registerPageActWebElements;
  private LoginPageActWebElements loginPageActWebElements;
  private static final ChromeOptions chromeOptions = new ChromeOptions();

  @BeforeAll
  static void setUpCrudAutomatedTests() {
    WebDriverManager.chromedriver().setup();
    chromeOptions.addArguments("--headless");
    chromeOptions.addArguments("--disable-gpu");
  }
  @BeforeEach
  void setUpWebDriver() {
    webDriver = new ChromeDriver(chromeOptions);
    webDriver.navigate().to(ApplicationEndpoints.REGISTER_PAGE_URL);
    registerPageVerifyWebElements = new RegisterPageVerifyWebElements(webDriver);
    registerPageActWebElements = new RegisterPageActWebElements(webDriver);
    loginPageActWebElements = new LoginPageActWebElements(webDriver);
  }

  @AfterEach
  void tearDownWebDriver() {
    webDriver.quit();
  }

  @Test
  void shouldDisplayBasicErrorMessagesWhenUserDoesNotProvideRequiredRegisterInformation() {
    // when
    registerPageActWebElements.emailInput().click();
    registerPageActWebElements.usernameInput().click();
    registerPageActWebElements.passwordInput().click();
    registerPageActWebElements.confirmationPasswordInput().click();
    registerPageActWebElements.registerButton().click();

    // then
    List<String> attributesToCheckInRegistration = List.of("Email", "Username", "Password", "Confirmation password");
    List<WebElement> webElementsToBeChecked = List.of(
      registerPageVerifyWebElements.emailErrorMessage(),
      registerPageVerifyWebElements.usernameErrorMessage(),
      registerPageVerifyWebElements.passwordErrorMessage(),
      registerPageVerifyWebElements.confirmationErrorMessage()
    );

    for(int i = 0; i < attributesToCheckInRegistration.size(); ++i) {
      Assertions.assertThat(webElementsToBeChecked.get(i).getText())
        .isEqualTo(getRequiredAttributeErrorMessage(attributesToCheckInRegistration.get(i)));
    }
  }

  @Test
  void shouldCorrectlyRegisterAndThenLoginUserWhenUserProvideProperInfoDuringRegistration()
    throws InterruptedException {
    // given
    final String validEmail = "mwo-testing@wp.pl";
    final String validUserName = "mwo-username";
    final String validPassword = "some-password";
    final String validConfirmationPassword = "some-password";

    // when register new user
    registerPageActWebElements.emailInput().sendKeys(validEmail);
    registerPageActWebElements.usernameInput().sendKeys(validUserName);
    registerPageActWebElements.passwordInput().sendKeys(validPassword);
    registerPageActWebElements.confirmationPasswordInput().sendKeys(validConfirmationPassword);
    registerPageActWebElements.registerButton().click();

    // and trying to login on that user
    loginPageActWebElements.loginEmailInput().sendKeys(validEmail);
    loginPageActWebElements.loginPasswordInput().sendKeys(validPassword);
    webDriver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
    loginPageActWebElements.loginButton().click();

    // then user should be properly logged in
    final String expectedWelcomeMessage = String.format("Welcome back %s", validUserName);

    Assertions.assertThat(registerPageVerifyWebElements.currentUserName().getText())
      .isEqualTo(expectedWelcomeMessage);
  }

  private String getRequiredAttributeErrorMessage(String attribute) {
    return String.format("%s field is required", attribute);
  }
}
