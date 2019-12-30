package github_exercises;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;

import io.restassured.parsing.Parser;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.RequestSpecification;

import org.json.simple.parser.ParseException;
import org.testng.annotations.*;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.BeforeEach;

import java.io.IOException;

import static github_exercises.DataManager.getJsonDefinition;
import static github_exercises.PropertiesManager.getEnvironmentProperty;
import static github_exercises.Utilities.*;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@Listeners({TestListener.class})
public class RestAssuredExamples {

    private static RequestSpecification requestSpec;

    //@BeforeEach
    @BeforeMethod
    public void setUp(){

        RestAssured.defaultParser = Parser.JSON;
        String baseURI = "https://api.github.com";

        System.out.println(getGithubUsername() + getTestToken());

        requestSpec = new RequestSpecBuilder()
                .setBaseUri(baseURI)
                .addPathParam("githubUser", getGithubUsername())
                .build();
    }

    @Test(priority = 1)
    public void methodGet_getGithubFollowers(){

        int expectedFollowersNumber = 9;

        System.out.println("requestSpec: " + requestSpec.toString());
        requestSpec
                .basePath("/users/{githubUser}/followers");

        given()
                .log().all()
                .spec(requestSpec)

        .when()
                .log().all()
                .get()

        .then()
                .log().all()
                .statusCode(200)
                .body("size()", is(expectedFollowersNumber))
                .body("url", hasItem("https://api.github.com/users/rpramoth"))
                .body("login", hasItems("FranciscoJGuz", "jhumbertoh"))
                .body("[1].login", equalTo("jane-hnatiuk"));
    }

    @Test(priority = 2)
    public void methodGet_getTopicsForRepository() throws IOException {

        requestSpec
                .pathParam("repoWithTopics", getEnvironmentProperty("repo_with_topics"))
                .basePath("/repos/{githubUser}/{repoWithTopics}/topics")
                .header("Accept", "application/vnd.github.mercy-preview+json");

        given()
                .log().all()
                .spec(requestSpec)

        .when()
                .log().all()
                .get()
        .then()
                .log().all()
                .statusCode(200)
                .body("names",notNullValue())
                .body("names", hasItem("blockchain"));

    }

    @Test(priority = 3)
    public void methodPost_createGithubRepoWithoutOAuth_expected401() throws IOException, ParseException {

        FilterableRequestSpecification filterableRequestSpecification = (FilterableRequestSpecification) requestSpec;
        filterableRequestSpecification.removeNamedPathParam("githubUser");

        requestSpec
                .basePath("/user/repos")
                .body(getJsonDefinition("create_new_repo.json"));

        given()
                .log().all()
                .spec(requestSpec)

        .when()
                .log().all()
                .post()

        .then()
                .log().all()
                .statusCode(401)
                .body("message", is("Requires authentication"))
                .body("documentation_url", is("https://developer.github.com/v3/repos/#create"));
    }

    @Test(priority = 4)
    public void methodPost_createGithubRepo() throws IOException, ParseException {

        FilterableRequestSpecification filterableRequestSpecification = (FilterableRequestSpecification) requestSpec;
        filterableRequestSpecification.removeNamedPathParam("githubUser");

        requestSpec
                .basePath("/user/repos")
                .header("Authorization", "token " + getTestToken())
                .body(getJsonDefinition("create_new_repo.json"));

        given()
                .log().all()
                .spec(requestSpec)

        .when()
                .log().all()
                .post()

        .then()
                .log().all()
                .statusCode(201)
                .body("full_name", is(getGithubUsername()+"/Stephy-says-hi-from-RestAssured"));
    }

    @Test(priority = 5)
    public void methodPut_setNewTopicsForRepository() throws IOException, ParseException {

        requestSpec
                .pathParam("repoWithTopics", getEnvironmentProperty("repo_with_topics"))
                .basePath("/repos/{githubUser}/{repoWithTopics}/topics")
                .header("Accept", "application/vnd.github.mercy-preview+json")
                .header("Authorization", "token " + getTestToken())
                .body(getJsonDefinition("new_topics_for_repo.json"));

        given()
                .log().all()
                .spec(requestSpec)

                .when()
                .log().all()
                .put()

                .then()
                .log().all()
                .statusCode(200)
                .body("names",notNullValue())
                .body("names", hasItem("bitcoin"));

    }

    @Test(priority = 6)
    public void methodDelete_createGithubRepo() throws IOException, ParseException {

        requestSpec
                .basePath("/repos/{githubUser}/Stephy-says-hi-from-RestAssured")
                .header("Authorization", "token " + getTestToken());
                //.body(getJsonDefinition("create_new_repo.json"));

        given()
                .log().all()
                .spec(requestSpec)

        .when()
                .log().all()
                .delete()

        .then()
                .log().all()
                .statusCode(204);
    }

}