package com.example.interacting;

public class List_Of_Teachers_Class {
    String teacher_name,unique_id;

    public List_Of_Teachers_Class() {
    }

    public List_Of_Teachers_Class(String teacher_name, String unique_id) {
        this.teacher_name = teacher_name;
        this.unique_id = unique_id;
    }


    public String getTeacher_name() {
        return teacher_name;
    }

    public void setTeacher_name(String teacher_name) {
        this.teacher_name = teacher_name;
    }

    public String getUnique_id() {
        return unique_id;
    }

    public void setUnique_id(String unique_id) {
        this.unique_id = unique_id;
    }
}
