package pageBO;

import com.aventstack.extentreports.Status;
import page.GitHubLoginPage;

public class GitHubLoginPageBO extends BasePageBO {
    private GitHubLoginPage gitHubLoginPage;

    public GitHubLoginPageBO() {
        gitHubLoginPage = new GitHubLoginPage();
    }

    public GitHubLoginPageBO loginToGit(String login, String password) {
        reportLogger.log(Status.INFO, "Login to Git Hub");
        gitHubLoginPage.loginToGit(login, password);
        softAssert.assertTrue(true, "Assertion message");
        reportLogger.log(Status.INFO, "Finish login to Git Hub");
        return this;
    }

    public GitHubLoginPageBO loginToGitWithFail(String login, String password) {
        reportLogger.log(Status.INFO, "Login to Git Hub");
        gitHubLoginPage.loginToGit(login, password);
        softAssert.assertTrue(false, "Assertion message");
        reportLogger.log(Status.INFO, "Finish login to Git Hub");
        return this;
    }
}
