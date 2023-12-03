import io.github.bonigarcia.wdm.WebDriverManager;
import org.assertj.core.api.Assertions;
import org.example.login.LoginPageActWebElements;
import org.example.main.MainPageActWebElements;
import org.example.main.MainPageVerifyWebElements;
import org.example.register.RegisterPageActWebElements;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import java.util.concurrent.TimeUnit;

class MainPageTest {
  private WebDriver webDriver;
  private RegisterPageActWebElements registerPageActWebElements;
  private LoginPageActWebElements loginPageActWebElements;
  private MainPageActWebElements mainPageActWebElements;
  private MainPageVerifyWebElements mainPageVerifyWebElements;
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

    registerPageActWebElements = new RegisterPageActWebElements(webDriver);
    loginPageActWebElements = new LoginPageActWebElements(webDriver);
    mainPageActWebElements = new MainPageActWebElements(webDriver);
    mainPageVerifyWebElements = new MainPageVerifyWebElements(webDriver);

    createAnAccountAndLoginIntoTheMainPage();
  }

  @AfterEach
  void tearDownWebDriver() {
    webDriver.quit();
  }

  @Test
  void shouldCorrectlyLogoutUserWhenUserIsSignedIn() {
    // when
    mainPageActWebElements.logoutButton().click();

    // then
    final String expectedCurrentUrl = "http://localhost:4200/auth/login";

    Assertions.assertThat(expectedCurrentUrl)
      .isEqualTo(webDriver.getCurrentUrl());
  }

  @Test
  void shouldCorrectlyAddNewTaskToTheUserWhenUserWantsToAddNewTask() {
    // given some description
    final String taskDescription = "Doing the mobile project";

    // when adding new to do to the user with this description
    mainPageActWebElements.newToDOInput().sendKeys(taskDescription);
    mainPageActWebElements.newToDoButton().click();

    // then added todoShould appear on the page
    Assertions.assertThat(mainPageVerifyWebElements
      .todosDescriptionWebElements().stream().map(WebElement::getText))
      .contains(taskDescription);
  }

  @Test
  void shouldCorrectlyRemoveNewAddedTaskWhenUserWantsToDeleteTheTask() {
    // given
    final String taskDescription = "Doing the mobile project";
    mainPageActWebElements.newToDOInput().sendKeys(taskDescription);
    mainPageActWebElements.newToDoButton().click();

    // when
    mainPageActWebElements.removeToDoButton().click();

    // then
    Assertions.assertThat(mainPageVerifyWebElements
        .todosDescriptionWebElements().stream().map(WebElement::getText))
      .doesNotContain(taskDescription);
  }

  @Test
  void shouldCorrectlyMarkToDoAsDoneWhenUserMarksTheTaskDone() {
    // given added a sample task
    final String taskDescription = "Sample to do";
    mainPageActWebElements.newToDOInput().sendKeys(taskDescription);
    mainPageActWebElements.newToDoButton().click();

    // when trying to mark task as done
    mainPageActWebElements.doneToDoButton().click();

    // then the button text should be changed
    final String expectedButtonDoneText = "Done";
    Assertions.assertThat(mainPageVerifyWebElements.doneToDoButton().getText())
      .isEqualTo(expectedButtonDoneText);
  }

  private void createAnAccountAndLoginIntoTheMainPage() {
    // Register an account
    registerPageActWebElements.emailInput().sendKeys("mwo-testing@wp.pl");
    registerPageActWebElements.usernameInput().sendKeys("mwo");
    registerPageActWebElements.passwordInput().sendKeys("some-password");
    registerPageActWebElements.confirmationPasswordInput().sendKeys("some-password");
    registerPageActWebElements.registerButton().click();

    // And then login
    loginPageActWebElements.loginEmailInput().sendKeys("mwo-testing@wp.pl");
    loginPageActWebElements.loginPasswordInput().sendKeys("some-password");
    makeDriverWait();
    loginPageActWebElements.loginButton().click();
  }

  private void makeDriverWait() {
    webDriver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
  }
}
