package com.example.webquiz;

public class IDGeneration {

    private static int id = 1;

    public static int generateID() {
        return id++;
    }
}