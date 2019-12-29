package core;

public interface Configuration {
    String URL = System.getenv("URL");
    String BROWSER = System.getenv("BROWSER");
    String LOGIN = System.getenv("LOGIN");
    String PASSWORD = System.getenv("PASSWORD");
}
