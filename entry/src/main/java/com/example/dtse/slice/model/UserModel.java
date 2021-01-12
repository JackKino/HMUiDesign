package com.example.dtse.slice.model;





import java.util.ArrayList;

public class UserModel {

    private int mId;

    private String mName;

    private String mLastName;

    private int mAge;

    private ArrayList<String> mPhoneNumbers;

    public UserModel() {

    }

    public UserModel(int age, int id, String lastName, String name, ArrayList<String> phoneNumbers) {
        mAge = age;
        mId = id;
        mLastName = lastName;
        mName = name;
        mPhoneNumbers = phoneNumbers;
    }

    public int getAge() {
        return mAge;
    }

    public UserModel setAge(int age) {
        mAge = age;
        return this;
    }

    public int getId() {
        return mId;
    }

    public UserModel setId(int id) {
        mId = id;
        return this;
    }

    public String getLastName() {
        return mLastName;
    }

    public UserModel setLastName(String lastName) {
        mLastName = lastName;
        return this;
    }

    public String getName() {
        return mName;
    }

    public UserModel setName(String name) {
        mName = name;
        return this;
    }

    public ArrayList<String> getPhoneNumbers() {
        return mPhoneNumbers;
    }

    public UserModel setPhoneNumbers(ArrayList<String> phoneNumbers) {
        mPhoneNumbers = phoneNumbers;
        return this;
    }
}
