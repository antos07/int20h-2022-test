package com.api.api;

import java.util.Random;

public class User {
    int id;

    public int getId() {
        return id;
    }

    public User() {
        Random rn = new Random();
        this.id =rn.nextInt();
    }
}
