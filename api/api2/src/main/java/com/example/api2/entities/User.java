package com.example.api2.entities;

import java.util.Random;

public class User {
    private int id;

    public int getId() {
        return id;
    }

    public User() {
        Random rn = new Random();
        this.id=-1;
        while(this.id<0)
        this.id =rn.nextInt();
    }
}
