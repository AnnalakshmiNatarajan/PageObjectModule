package PageObjectModule;

import org.testng.annotations.*;

public class CheckingTestngMethods {

    @Test
    public void testMethod1(){
        System.out.println("Test1");
    }

    @Test
    public void testMethod2(){
        System.out.println("Test2");
    }

    @BeforeTest
    public void beforeTestMethod(){
        System.out.println("beforeTestMethod");
    }

    @BeforeMethod
    public void beforeMethod(){
        System.out.println("beforeMethod");
    }
    @BeforeSuite
    public void beforeSuite(){
        System.out.println("beforeSuite");
    }

    @BeforeGroups
    public void beforeGroup(){
        System.out.println("beforeGroups");
    }
    @BeforeClass
    public void beforeClass(){
        System.out.println("beforeClass");
    }
    @AfterTest
    public void afterTestMethod(){
        System.out.println("afterTestMethod");
    }

    @AfterMethod
    public void afterMethod(){
        System.out.println("afterMethod");
    }
    @AfterSuite
    public void afterSuite(){
        System.out.println("afterSuite");
    }

    @AfterGroups
    public void afterGroups(){
        System.out.println("afterGroups");
    }
    @AfterClass
    public void AfterClass(){
        System.out.println("AfterClass");
    }
}
