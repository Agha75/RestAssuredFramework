package com.spotify.oauth2.tests;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import java.lang.reflect.Method;

public class BaseTest {
    //This will get method name and count for parallel execution
    @BeforeMethod
    public void beforeMethod(Method method){
        System.out.println("Method name "+method.getName());
        System.out.println("Method count "+ Thread.currentThread().getId());
    }
}
