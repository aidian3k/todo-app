package org.example.login;

import lombok.Getter;
import lombok.experimental.Accessors;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

@Accessors(fluent = true)
@Getter
public class LoginPageActWebElements {
  private WebDriver webDriver;
  public LoginPageActWebElements(WebDriver webDriver) {
    PageFactory.initElements(webDriver, this);
  }

  @FindBy(xpath = "//app-login/div/form/mat-form-field[1]/div/div[1]/div[1]/input")
  private WebElement loginEmailInput;

  @FindBy(xpath = "//app-login/div/form/mat-form-field[2]/div/div[1]/div[1]/input")
  private WebElement loginPasswordInput;

  @FindBy(xpath = "//app-login/div/form/button")
  private WebElement loginButton;
}
