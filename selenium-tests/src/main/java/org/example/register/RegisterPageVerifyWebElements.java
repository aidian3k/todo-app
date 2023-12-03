package org.example.register;

import lombok.Getter;
import lombok.experimental.Accessors;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

@Accessors(fluent = true)
@Getter
public class RegisterPageVerifyWebElements {
  public RegisterPageVerifyWebElements(WebDriver driver) {
    PageFactory.initElements(driver, this);
  }

  @FindBy(xpath = "//app-register/div/form/div[1]/mat-error")
  private WebElement emailErrorMessage;

  @FindBy(xpath = "//app-register/div/form/div[2]/mat-error")
  private WebElement usernameErrorMessage;

  @FindBy(xpath = "//app-register/div/form/div[3]/mat-error")
  private WebElement passwordErrorMessage;

  @FindBy(xpath = "//app-register/div/form/div[4]/mat-error")
  private WebElement confirmationErrorMessage;

  @FindBy(xpath = "//app-main-page/div/div[1]/h1")
  private WebElement currentUserName;
}
