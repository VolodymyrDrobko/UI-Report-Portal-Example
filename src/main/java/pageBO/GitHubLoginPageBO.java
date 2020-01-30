package pageBO;

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
        System.out.println("Login to Git Hub");
        gitHubLoginPage.loginToGit(Optional.ofNullable(login).orElse(DEFAULT_LOGIN), Optional.ofNullable(password).orElse(DEFAULT_PASSWORD));
        softAssert.assertTrue(true, "Test case must pass");
        return this;
    }

    public GitHubLoginPageBO loginToGitWithFail(String login, String password) {
        System.out.println("Login to Git Hub");
        gitHubLoginPage.loginToGit(Optional.ofNullable(login).orElse(DEFAULT_LOGIN), Optional.ofNullable(password).orElse(DEFAULT_PASSWORD));
        softAssert.assertTrue(false, "Test case must fail");
        return this;
    }
}
