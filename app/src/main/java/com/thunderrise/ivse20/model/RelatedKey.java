package com.thunderrise.ivse20.model;

/**
 * Created by sergejkozin on 12/5/17.
 */

public class RelatedKey {

    private int mId;
    private int mLevel;
    private Key mKey;
    private int[] mAttitudeDown;

    public RelatedKey() {
        mId = -1;
        mKey = new Key();
        mAttitudeDown = null;
    }

    public RelatedKey(int id, Key key) {
        mId = id;
        mKey = key;
        mAttitudeDown = null;

    }

    public RelatedKey(int id, Key key, int[] attitudeDown) {
        mId = id;
        mKey = key;
        mAttitudeDown = attitudeDown;
    }

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public Key getKey() {
        return mKey;
    }

    public void setKey(Key key) {
        mKey = key;
    }

    public int[] getAttitudeDown() {
        return mAttitudeDown;
    }

    public void setAttitudeDown(int[] attitudeDown) {
        mAttitudeDown = attitudeDown;
    }

    public boolean doesActionExist(int action) {
        return mKey.findKey(action);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof String) {
            if (mKey.equals(obj))
                return true;
        }
        return false;
    }

    public int getLevel() {
        return mLevel;
    }

    public void setLevel(int level) {
        mLevel = level;
    }
}
