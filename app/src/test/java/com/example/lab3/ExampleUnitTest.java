package com.example.lab3;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import org.junit.Test;

import static org.junit.Assert.*;

//import android.text;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {



    @Test
    public void testAdminPassword(){
        adminCredentials admin = new adminCredentials();
//        String name = adminCredentials.AdminUsername;
//        String ps = adminCredentials.AdminPassword;

        assertEquals( "admin123", admin.getPassword() );
    }

    @Test
    public void testAccountName(){
        Account thisAcc = new Account();

        thisAcc.setUsername( "Jing" );
        assertEquals( "Jing", thisAcc.getUsername());
    }
    @Test
    public void testAccountPs(){
        Account thisAcc = new Account();
        thisAcc.setPassword("PingLing1-");

        assertEquals("PingLing1-", thisAcc.getPassword());
    }

    @Test
    public void testCourseGetCode(){
        Course thisCourse = new Course();

        thisCourse.setCode("2105");

        assertEquals("2105", thisCourse.getCode());

    }
    @Test
    public void testCourseGetName(){
        Course thisCourse = new Course();

        thisCourse.setName("SEG");

        assertEquals("SEG", thisCourse.getName());

    }
    @Test
    public void testUserValidPassword(){
        assertFalse( MainActivity.validatePassword("123213213"));
    }
}