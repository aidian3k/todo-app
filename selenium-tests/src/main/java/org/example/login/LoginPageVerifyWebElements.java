package org.example.login;

import lombok.Getter;
import lombok.experimental.Accessors;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

@Accessors(fluent = true)
@Getter
public class LoginPageVerifyWebElements {
  private WebDriver webDriver;
  public LoginPageVerifyWebElements(WebDriver webDriver) {
    PageFactory.initElements(webDriver, this);
  }

  @FindBy(xpath = "//app-login/div/form/div[2]/mat-error")
  private WebElement passwordErrorMessage;

  @FindBy(xpath = "//app-login/div/form/div[1]/mat-error")
  private WebElement emailErrorMessage;

  @FindBy(xpath = "//app-login/div/form/div[3]/mat-error")
  private WebElement loginErrorMessage;

  @FindBy(xpath = "//app-main-page/div/div[1]/h1")
  private WebElement currentUserName;
}
