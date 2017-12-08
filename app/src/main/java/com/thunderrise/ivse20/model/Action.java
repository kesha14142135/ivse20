package com.thunderrise.ivse20.model;

/**
 * Created by sergejkozin on 12/5/17.
 */

public class Action {
    private int mId;
    private boolean mIsMainWord;
    private String mWord;
    private boolean mIsAnEcommerceAction;

    public Action() {
        mId = 1;
        mWord = "";
        mIsMainWord = false;
        mIsAnEcommerceAction = false;
    }

    public Action(int id, boolean isMainWord, String word, boolean isAnEcommerceAction) {
        mId = id;
        mWord = word;
        mIsMainWord = isMainWord;
        mIsAnEcommerceAction = isAnEcommerceAction;
    }

    public boolean isMainWord() {
        return mIsMainWord;
    }

    public int getId() {
        return mId;
    }

    public String getWord() {
        return mWord;
    }

    public boolean isAnEcommerceAction() {
        return mIsAnEcommerceAction;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof String) {
            String word = (String) obj;
            if (word.equals(mWord)) {
                return true;
            }
        }
        return false;
    }
}
