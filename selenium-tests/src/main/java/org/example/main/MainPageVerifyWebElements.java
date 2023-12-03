package org.example.main;

import lombok.Getter;
import lombok.experimental.Accessors;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

@Getter
@Accessors(fluent = true)
public class MainPageVerifyWebElements {
  public MainPageVerifyWebElements(WebDriver driver) {
    PageFactory.initElements(driver, this);
  }

  @FindBy(xpath = "//app-main-page/div/div[2]/div[2]/app-task[1]/div/div/button[1]")
  private WebElement doneToDoButton;

  @FindBy(xpath = "//app-main-page/div/div[2]/div[2]/app-task/div/p")
  List<WebElement> todosDescriptionWebElements;
}
