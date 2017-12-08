package com.thunderrise.ivse20.model;

import java.util.List;
import java.util.regex.Pattern;

/**
 * Created by sergejkozin on 12/5/17.
 */

public class RelatedWord {

    private String mWord;
    private int mKeyId;
    private Type mType;
    private int[] mAttitudeDown;

    public RelatedWord() {
        mWord = "";
        mKeyId = -1;
        mType = Type.NONE;
        mAttitudeDown = null;
    }

    public RelatedWord(String word) {
        mWord = word;
        mKeyId = -1;
        mType = Type.NONE;
        mAttitudeDown = null;
    }

    public String getWord() {
        return mWord;
    }

    public void setWord(String word) {
        mWord = word;
    }

    public int getKeyId() {
        return mKeyId;
    }

    public void setKeyId(int keyId) {
        mKeyId = keyId;
    }

    public Type getType() {
        return mType;
    }

    public void setType(Type type) {
        mType = type;
    }

    public int[] getAttitudeDown() {
        return mAttitudeDown;
    }

    public void setAttitudeDown(int[] attitudeDown) {
        mAttitudeDown = attitudeDown;
    }

    public void addDataInRelatedKey(int key, Type type) {
        mKeyId = key;
        mType = type;
    }

    public boolean isTypeSet() {
        if (Type.NONE != mType)
            return true;
        return false;
    }

    public void addDataInRelatedWord(int id, Type type, int[] attitudeDown) {
        mKeyId = id;
        mType = type;
        mAttitudeDown = attitudeDown;
    }


    public boolean findAndAddIdWord(List<Word> words, Type type) {
        for (Word word : words) {
            if (mWord.equals(word.getName())) {
                mKeyId = word.getId();
                mType = type;
                return true;
            }
        }
        return false;
    }


    public void findAndAddIdAction(List<Action> actions) {
        for (Action action : actions) {
            if (mWord.equals(action.getWord())) {
                mKeyId = action.getId();
                mType = Type.ACTION;
            }
        }
    }

    public boolean findAndAddIdWordUsingPattern(List<Pattern> patterns, Type type) {
        for (Pattern pattern : patterns) {
            if (pattern.matcher(mWord).matches()) {
                mKeyId = 1;
                mType = type;
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof RelatedWord) {
            RelatedWord relatedWord = (RelatedWord) obj;
            if (mWord.equals(relatedWord.getWord())) {
                return true;
            }
        }
        return false;
    }
}
