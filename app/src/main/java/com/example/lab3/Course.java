package com.example.lab3;

public class Course {
    private String code = new String("NA");
    private String name = new String("NA");
    private String instructor = new String("NA");
    private String days = new String("NA");
    private String hours = new String("NA");
    private String description = new String("NA");
    private int studentCapacity = 0;

    public Course(String code, String name){
        this.code = code;
        this.name = name;
    }

    public Course(){}

    public Course(String code, String name, String instructor, String days, String hours, String description, int studentCapacity){
        this.code = code;
        this.name = name;
        this.instructor = instructor;
        this.days = days;
        this.hours = hours;
        this.description = description;
        this.studentCapacity = studentCapacity;
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
    public void setCode(String newCode){code = newCode;}
    public void setName(String newName){name = newName;}
    public void setInstructor(String newInstructor){instructor = newInstructor;}
    public void setDays(String newDays){days = newDays;}
    public void setHours(String newHours){hours = newHours;}
    public void setDescription(String newDescription){description = newDescription;}
    public void setStudentCapacity(int newStudentCapacity){studentCapacity = newStudentCapacity;}
}
