package com.example.dtse.slice.model;

import java.util.List;

public class UsersModel {
    private List<UserModel> userModels;

    public UsersModel(List<UserModel> userModels) {
        this.userModels = userModels;
    }

    public List<UserModel> getUserModels() {
        return userModels;
    }

    public void setUserModels(List<UserModel> userModels) {
        this.userModels = userModels;
    }
}
