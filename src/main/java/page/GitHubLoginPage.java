package page;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class GitHubLoginPage extends BasePage {
    @FindBy(xpath = "//input[@name='login']")
    private WebElement loginInput;
    @FindBy(xpath = "//input[@name='password']")
    private WebElement passwordInput;
    @FindBy(xpath = "//input[@type='submit']")
    private WebElement submitButton;

    public void loginToGit(String login, String password) {
        loginInput.clear();
        loginInput.sendKeys(login);
        passwordInput.clear();
        passwordInput.sendKeys(password);
        submitButton.click();
    }
}
