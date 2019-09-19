package github_exercises;

public class Utilities {

    public static String getGithubUsername() {
        return System.getenv("GITHUB_USERNAME");
    }
    public static String getTestToken() {
        return System.getenv("GITHUB_TOKEN_DEMO");
    }


}