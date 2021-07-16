package com.example.lab3;


import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

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
    public void testGetDay(){
        Day thisDay = new Day( "Wednesday", 11, 12.2);

        assertEquals( "Wednesday", thisDay.getDay());
    }

    @Test
    public void testGetCourseDaysInfo(){
        Course thisCourse = new Course();

        thisCourse.setDays("Monday: 10:00 AM-11:00 AM");

        Day[] dayArr = thisCourse.getCourseDayInfo();

        assertEquals("Monday", dayArr[0].getDay());
        assertEquals(10.0, 0.0, dayArr[0].getStartTime());
        assertEquals(11.0, 0.0, dayArr[0].getEndTime());
    }

    @Test
    public void testTimeStampProcessing(){
        Course thisCourse = new Course();

        double testDouble = thisCourse.timeStampProcessing("12:00 PM");

        assertEquals(24, 0.0, testDouble);

    }
    @Test
    public void testGetStartTime(){
        Day thisDay = new Day(null, 9.0, 0.0);

        double testDouble = thisDay.getStartTime();

        assertEquals(9.0, 0.0, testDouble);
    }
    @Test
    public void testGetCourseDaysNull(){
        Course thisCourse = new Course();

        thisCourse.setDays(null);

        Day[] dayArr = thisCourse.getCourseDayInfo();

        assertNull(dayArr);

    }
}












































