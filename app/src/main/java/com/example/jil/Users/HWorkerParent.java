package com.example.jil.Users;

/**
 * Created by JIL on 03/03/16.
 */
public class HWorkerParent extends Users {
    public HWorkerParent(String username, String password) {
        super(username, password);
    }

    private HWorkerParent(String username)
    {
        super(username);
    }
/*
    public Child createNewChild()
    {

    }
    */
}
