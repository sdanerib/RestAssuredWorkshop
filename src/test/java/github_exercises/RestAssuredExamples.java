package github_exercises;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;

import io.restassured.parsing.Parser;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.RequestSpecification;

import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import java.io.IOException;

import static github_exercises.DataManager.getJsonDefinition;
import static github_exercises.PropertiesManager.getEnvironmentProperty;
import static github_exercises.Utilities.*;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class RestAssuredExamples {

    private static RequestSpecification requestSpec;

    @BeforeEach
    public void setUp(){

        RestAssured.defaultParser = Parser.JSON;
        String baseURI = "https://api.github.com";

        System.out.println(getGithubUsername() + getTestToken());

        requestSpec = new RequestSpecBuilder()
                .setBaseUri(baseURI)
                .addPathParam("githubUser", getGithubUsername())
                .build();
    }

    @Test
    public void methodGet_getGithubFollowers(){
        //TODO: https://developer.github.com/v3/repos/#list-all-topics-for-a-repository
    }

    @Test
    public void methodGet_getTopicsForRepository(){
        //TODO: https://developer.github.com/v3/repos/#list-all-topics-for-a-repository
    }

    @Test
    public void methodPost_createGithubRepoWithoutOAuth_expected401() throws IOException, ParseException {
       //TODO: https://developer.github.com/v3/repos/#create
    }

    @Test
    public void methodPost_createGithubRepo() throws IOException, ParseException {
        //TODO: https://developer.github.com/v3/repos/#create
    }

    @Test
    public void methodPut_setNewTopicsForRepository() throws IOException, ParseException {
        //TODO: https://developer.github.com/v3/repos/#list-all-topics-for-a-repository
    }

    @Test
    public void methodDelete_createGithubRepo() throws IOException, ParseException {
        //TODO: https://developer.github.com/v3/repos/#delete-a-repository
    }

}