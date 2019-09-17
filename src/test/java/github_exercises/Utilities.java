package github_exercises;

public class Utilities {

    public static String getTestToken() {
        return System.getenv("GITHUB_TOKEN_DEMO");
    }

    public static String bodyForGithubRepoCreation(){
        return "{\n" +
                "            \"name\": \"Stephy says hi from RestAssured\",\n" +
                "                \"description\": \"Created fron RestAssured Training repo\",\n" +
                "                \"homepage\": \"https://github.com\",\n" +
                "                \"private\": false,\n" +
                "                \"has_issues\": true,\n" +
                "                \"has_projects\": true,\n" +
                "                \"has_wiki\": true\n" +
                "        }";
    }


}