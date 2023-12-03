package org.example.register;

import lombok.Getter;
import lombok.experimental.Accessors;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

@Accessors(fluent = true)
@Getter
public class RegisterPageActWebElements {
  public RegisterPageActWebElements(WebDriver driver) {
    PageFactory.initElements(driver, this);
  }

  @FindBy(xpath = "//app-register/div/form/mat-form-field[1]/div/div[1]/div[1]/input")
  private WebElement emailInput;

  @FindBy(xpath = "//app-register/div/form/mat-form-field[2]/div/div[1]/div[1]/input")
  private WebElement usernameInput;

  @FindBy(xpath = "//app-register/div/form/mat-form-field[3]/div/div[1]/div[1]/input")
  private WebElement passwordInput;

  @FindBy(xpath = "//app-register/div/form/mat-form-field[4]/div/div[1]/div[1]/input")
  private WebElement confirmationPasswordInput;

  @FindBy(xpath = "//app-register/div/form/button")
  private WebElement registerButton;
}
