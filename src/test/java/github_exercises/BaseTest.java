package github_exercises;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;


public class BaseTest {

    @BeforeClass
    public void classLevelSetup() {
        System.out.println("classLevelSetup");
    }

    @BeforeMethod
    public void methodLevelSetup() {
        System.out.println("methodLevelSetup");
    }

    @AfterClass
    public void teardown() {
        System.out.println("teardown");
    }
}