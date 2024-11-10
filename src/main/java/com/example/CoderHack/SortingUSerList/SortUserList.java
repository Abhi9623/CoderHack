package com.example.CoderHack.SortingUSerList;

import com.example.CoderHack.model.User;

import java.util.Comparator;

public class SortUserList implements Comparator<User> {

    @Override
    public int compare(User o1, User o2) {

        return Integer.compare(o2.getScore(), o1.getScore());
    }

}