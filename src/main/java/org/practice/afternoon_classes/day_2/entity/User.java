package org.practice.afternoon_classes.day_2.entity;

public class User {
    private String id;
    private String name;
    private int age;

    public User(String name, int age) {
        this.id = "user_" + System.currentTimeMillis();
        this.name = name;
        this.age = age;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString(){
        return "{ id: " + id + ", name: " + name + ", age: " + age + "}";
    }
}
