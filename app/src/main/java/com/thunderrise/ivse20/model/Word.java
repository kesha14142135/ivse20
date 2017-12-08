package com.thunderrise.ivse20.model;

/**
 * Created by sergejkozin on 12/5/17.
 */

public class Word {
    private int mId;
    private String mName;

    public Word() {

    }

    public Word(int id, String name) {
        mId = id;
        mName = name;
    }

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public boolean foundWord(String name) {
        return mName.equals(name);
    }
}
