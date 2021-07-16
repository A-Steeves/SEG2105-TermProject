package com.example.lab3;

public class Course {
    private String code = new String("NA");
    private String name = new String("NA");
    private String instructor = new String("NA");
    private String days = null;
    private String hours = new String("NA");
    private String description = new String("NA");
    private int studentCapacity = 0;
    private int studentsEnrolled = 0;

    public Course(String code, String name){
        this.code = code;
        this.name = name;
    }

    public Course(){}

    public Course(String code, String name, String instructor, String days, String hours, String description, int studentCapacity, int studentsEnrolled){
        this.code = code;
        this.name = name;
        this.instructor = instructor;
        this.days = days;
        this.hours = hours;
        this.description = description;
        this.studentCapacity = studentCapacity;
        this.studentsEnrolled = studentsEnrolled;
    }

    public boolean timeEquals( Course otherCourse ){
        return this.days.equals(otherCourse.getDays()) && this.hours.equals(otherCourse.getHours());
    }

    public Day[] getCourseDayInfo(){
        Day[] res = new Day[5];
        // dayArr[0] != null dayArr[i].equals("NA")
        if (days != null) {
            String[] dayArr = days.split("&");
            String[] day;
            String[] daytime;
            Day currentDay;
            for (int i = 0; i < dayArr.length; i++) {
                if (!dayArr[i].equals("NA")){
                    day = dayArr[i].split(": "); // Monday: 10:30Am-11:15AM&NA
                    daytime = day[1].split("-");
                    currentDay = new Day(day[0], timeStampProcessing(daytime[0]), timeStampProcessing(daytime[1]));
                    if (currentDay.getDay().equals("Monday")) {
                        res[0] = currentDay;
                    } else if (currentDay.getDay().equals("Tuesday")) {
                        res[1] = currentDay;
                    } else if (currentDay.getDay().equals("Wednesday")) {
                        res[2] = currentDay;
                    } else if (currentDay.getDay().equals("Thursday")) {
                        res[3] = currentDay;
                    } else if (currentDay.getDay().equals("Friday")) {
                        res[4] = currentDay;
                    }
                }
            }
        }
        else {
            res = null;
        }
        return res;
    }

    public static double timeStampProcessing(String timestamp){
        // Example of Input: "10:56 AM"
        String[] dayInfo = timestamp.split(" ");
        String[] time = dayInfo[0].split(":");
        double hour = Double.parseDouble(time[0]);
        if (dayInfo[1].equals("PM")){
            hour += (double) 12.0;
        }
        return hour+(Double.parseDouble(time[1])/60.0);
    }


    public String getCode(){
        return code;
    }
    public String getName(){
        return name;
    }
    public String getInstructor(){return instructor;}
    public String getDays(){return days;}
    public String getHours(){return hours;}
    public String getDescription(){return description;};
    public int getStudent_capacity(){return studentCapacity;}
    public int getStudentsEnrolled(){return studentsEnrolled;}
    public void setCode(String newCode){code = newCode;}
    public void setName(String newName){name = newName;}
    public void setInstructor(String newInstructor){instructor = newInstructor;}
    public void setDays(String newDays){days = newDays;}
    public void setHours(String newHours){hours = newHours;}
    public void setDescription(String newDescription){description = newDescription;}
    public void setStudentCapacity(int newStudentCapacity){studentCapacity = newStudentCapacity;}
    public void setStudentsEnrolled(int studentsEnrolled){this.studentsEnrolled = studentsEnrolled;}
}
