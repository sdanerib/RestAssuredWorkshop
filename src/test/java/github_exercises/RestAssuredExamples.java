package github_exercises;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;

import io.restassured.parsing.Parser;
import io.restassured.specification.RequestSpecification;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import static github_exercises.Utilities.*;
import static github_exercises.Utilities.bodyForGithubRepoCreation;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class RestAssuredExamples {

    private static RequestSpecification requestSpec;

    @BeforeEach
    public void setUp(){

        RestAssured.defaultParser = Parser.JSON;
        String baseURI = "https://api.github.com";

        System.out.println(getTestToken());

        requestSpec = new RequestSpecBuilder()
                .setBaseUri(baseURI)
                .build();
    }

    @Test
    public void methodGet_getGithubFollowers(){

        int expectedFollowersNumber = 7;

        requestSpec
                .basePath("/users/sdanerib/followers");

        given()
                .log().all()
                .spec(requestSpec)


        .when()
                .log().all()
                .get()

        .then()
                .log().all()
                .statusCode(200)
                .body("size()", is(expectedFollowersNumber));
    }

    @Test
    public void methodPost_createGithubRepoWithoutOAuth_expected401(){

        int expectedFollowersNumber = 7;

        requestSpec
                .basePath("/user/repos")
                .body(bodyForGithubRepoCreation());

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


    @Test
    public void methodPost_createGithubRepo(){

        requestSpec
                .basePath("/user/repos")
                .header("Authorization", "token " + getTestToken())
                .body(bodyForGithubRepoCreation());

        given()
                .log().all()
                .spec(requestSpec)

        .when()
                .log().all()
                .post()

        .then()
                .log().all()
                .statusCode(201)
                .body("full_name", is("sdanerib/Stephy-says-hi-from-RestAssured"));
    }


    @Test
    public void methodDelete_createGithubRepo(){

        requestSpec
                .basePath("/repos/sdanerib/Stephy-says-hi-from-RestAssured")
                .header("Authorization", "token " + getTestToken())
                .body(bodyForGithubRepoCreation());

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