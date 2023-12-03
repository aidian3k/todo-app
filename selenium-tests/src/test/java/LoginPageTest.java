import io.github.bonigarcia.wdm.WebDriverManager;
import org.example.login.LoginPageActWebElements;
import org.example.login.LoginPageVerifyWebElements;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.*;

class LoginPageTest {
  private WebDriver webDriver;
  private LoginPageActWebElements loginPageActWebElements;
  private LoginPageVerifyWebElements loginPageVerifyWebElements;
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
    webDriver.navigate().to(ApplicationEndpoints.BASE_PAGE_URL);
    loginPageActWebElements = new LoginPageActWebElements(webDriver);
    loginPageVerifyWebElements = new LoginPageVerifyWebElements(webDriver);
  }

  @AfterEach
  void tearDownWebDriver() {
    webDriver.quit();
  }

  @Test
  void shouldShowValidErrorMessagesWhenUserDoesNotProvideRequiredInfoWhileSigningIn() throws InterruptedException {
    // when
    loginPageActWebElements.loginEmailInput().click();
    loginPageActWebElements.loginPasswordInput().click();
    loginPageActWebElements.loginButton().click();

    // then
    final String expectedEmailErrorMessage = "Email field is required";
    final String expectedPasswordErrorMessage = "Password field is required";

    Assertions.assertAll(
      () -> assertThat(loginPageVerifyWebElements.emailErrorMessage().getText()).isEqualTo(expectedEmailErrorMessage),
      () -> assertThat(loginPageVerifyWebElements.passwordErrorMessage().getText()).isEqualTo(expectedPasswordErrorMessage)
    );
  }

  @Test
  void shouldDisplayLoginErrorMessageWhenUserProvidesWrongCredentials() {
    // given
    final String randomEmail = "mwo-test@onet.com";
    final String randomPassword = "some-random-password";

    // when
    loginPageActWebElements.loginEmailInput().sendKeys(randomEmail);
    loginPageActWebElements.loginPasswordInput().sendKeys(randomPassword);
    loginPageActWebElements.loginButton().click();

    // then
    final String expectedWrongLoginCredentialsMessage = "There was problem when logging you!";
    assertThat(loginPageVerifyWebElements.loginErrorMessage().getText())
      .isEqualTo(expectedWrongLoginCredentialsMessage);
  }

  @Test
  void shouldCorrectlyLoginUserWhenUserGivesCorrectCredentials() {
    // given
    final String randomEmail = "adrian@wp.pl";
    final String randomPassword = "adrian";

    // when
    loginPageActWebElements.loginEmailInput().sendKeys(randomEmail);
    loginPageActWebElements.loginPasswordInput().sendKeys(randomPassword);
    webDriver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
    loginPageActWebElements.loginButton().click();

    // then
    final String expectedLoggedInUserName = "Welcome back Adrian";
    assertThat(loginPageVerifyWebElements.currentUserName().getText())
      .isEqualTo(expectedLoggedInUserName);
  }
}
