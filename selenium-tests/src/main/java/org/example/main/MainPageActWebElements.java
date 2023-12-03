package org.example.main;

import lombok.Getter;
import lombok.experimental.Accessors;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

@Getter
@Accessors(fluent = true)
public class MainPageActWebElements {
  public MainPageActWebElements(WebDriver driver) {
    PageFactory.initElements(driver, this);
  }

  @FindBy(xpath = "//app-main-page/div/div[1]/button")
  private WebElement logoutButton;

  @FindBy(xpath = "//app-main-page/div/div[2]/div[1]/form/input")
  private WebElement newToDOInput;

  @FindBy(xpath = "//app-main-page/div/div[2]/div[1]/form/button")
  private WebElement newToDoButton;

  @FindBy(xpath = "//app-main-page/div/div[2]/div[2]/app-task[1]/div/div/button[1]")
  private WebElement doneToDoButton;

  @FindBy(xpath = "//app-main-page/div/div[2]/div[2]/app-task[1]/div/div/button[2]")
  private WebElement removeToDoButton;
}
