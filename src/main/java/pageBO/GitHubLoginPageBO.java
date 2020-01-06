package pageBO;

import com.aventstack.extentreports.Status;
import page.GitHubLoginPage;

import java.util.Optional;

import static utils.Constants.DEFAULT_LOGIN;
import static utils.Constants.DEFAULT_PASSWORD;

public class GitHubLoginPageBO extends BasePageBO {
    private GitHubLoginPage gitHubLoginPage;

    public GitHubLoginPageBO() {
        gitHubLoginPage = new GitHubLoginPage();
    }

    public GitHubLoginPageBO loginToGit(String login, String password) {
        reportLogger.log(Status.INFO, "Login to Git Hub");
        gitHubLoginPage.loginToGit(Optional.ofNullable(login).orElse(DEFAULT_LOGIN), Optional.ofNullable(password).orElse(DEFAULT_PASSWORD));
        softAssert.assertTrue(true, "Assertion message");
        reportLogger.log(Status.INFO, "Finish login to Git Hub");
        return this;
    }

    public GitHubLoginPageBO loginToGitWithFail(String login, String password) {
        reportLogger.log(Status.INFO, "Login to Git Hub");
        gitHubLoginPage.loginToGit(Optional.ofNullable(login).orElse(DEFAULT_LOGIN), Optional.ofNullable(password).orElse(DEFAULT_PASSWORD));
        softAssert.assertTrue(false, "Assertion message");
        reportLogger.log(Status.INFO, "Finish login to Git Hub");
        return this;
    }
}
