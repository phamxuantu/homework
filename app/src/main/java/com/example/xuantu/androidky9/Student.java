package com.example.xuantu.androidky9;

/**
 * Created by Xuan Tu on 28/08/2017.
 */

public class Student {
    private String name;
    private String sex;
    private String skill;

    public Student(String name, String sex, String skill) {
        this.name = name;
        this.sex = sex;
        this.skill = skill;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getSkill() {
        return skill;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }
}
