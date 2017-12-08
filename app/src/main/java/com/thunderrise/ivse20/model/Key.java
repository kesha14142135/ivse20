package com.thunderrise.ivse20.model;

/**
 * Created by sergejkozin on 12/5/17.
 */

public class Key {
    private int mId;
    private String[] mWord;
    private int[] mMainActions;
    private int[] mSupportActions;

    public Key() {
        mId = -1;
        mWord = new String[]{};
        mMainActions = new int[]{};
        mSupportActions = new int[]{};
    }

    public Key(int id, String[] word, int[] mainActions, int[] supportActions) {
        mId = id;
        mWord = word;
        mMainActions = mainActions;
        mSupportActions = supportActions;
    }

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public String[] getWord() {
        return mWord;
    }

    public void setWord(String[] word) {
        mWord = word;
    }

    public int[] getMainActions() {
        return mMainActions;
    }

    public void setMainActions(int[] mainActions) {
        mMainActions = mainActions;
    }

    public int[] getSupportActions() {
        return mSupportActions;
    }

    public void setSupportActions(int[] supportActions) {
        mSupportActions = supportActions;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof String) {
            String say = (String) obj;
            for (String word : mWord)
                if (say.equals(word))
                    return true;
        }
        return false;
    }

    public boolean findKey(int key) {
        for (int action : mSupportActions) {
            if (key == action) {
                return true;
            }
        }
        return false;
    }
}
