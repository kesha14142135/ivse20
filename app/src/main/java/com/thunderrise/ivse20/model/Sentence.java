package com.thunderrise.ivse20.model;

import java.util.List;

/**
 * Created by sergejkozin on 12/5/17.
 */

public class Sentence {
    private int mId;
    private int mType;
    private int mStatus;
    private TypeSentence mTypeSentence;
    private List<RelatedWord> mWords;

    public Sentence() {
        mId = -1;
        mType = -1;
        mStatus = -1;
        mWords = null;
    }

    public Sentence(TypeSentence typeSentence, List<RelatedWord> words) {
        mId = -1;
        mType = -1;
        mStatus = -1;
        mTypeSentence = typeSentence;
        mWords = words;
    }

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public int getType() {
        return mType;
    }

    public void setType(int type) {
        mType = type;
    }

    public int getStatus() {
        return mStatus;
    }

    public void setStatus(int status) {
        mStatus = status;
    }

    public TypeSentence getTypeSentence() {
        return mTypeSentence;
    }

    public void setTypeSentence(TypeSentence typeSentence) {
        mTypeSentence = typeSentence;
    }

    public List<RelatedWord> getWords() {
        return mWords;
    }

    public void setWords(List<RelatedWord> words) {
        mWords = words;
    }
}
