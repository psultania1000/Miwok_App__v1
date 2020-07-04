package com.example.android.miwok;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.provider.MediaStore;

public class Word {
    private String mDefaultTranslation;
    private String mMiwokTranslation;
    private Drawable icon;
    private int mAudioID;

    public Drawable getIcon() {
        return icon;
    }

    public void setIcon(Drawable icon) {
        this.icon = icon;
    }


    public Word(String mDefaultTranslation, String mMiwokTranslation, int mAudioID) {
        this.mDefaultTranslation = mDefaultTranslation;
        this.mMiwokTranslation = mMiwokTranslation;
        this.mAudioID = mAudioID;
    }

    @Override
    public String toString() {
        return "Word{" +
                "mDefaultTranslation='" + mDefaultTranslation + '\'' +
                ", mMiwokTranslation='" + mMiwokTranslation + '\'' +
                ", icon=" + icon +
                ", mAudioID=" + mAudioID +
                '}';
    }

    public int getmAudioID() {
        return mAudioID;
    }

    public void setmAudioID(int mAudioID) {
        this.mAudioID = mAudioID;
    }

    public Word(String mDefaultTranslation, String mMiwokTranslation, Drawable icon, int mAudioID) {
        this.mDefaultTranslation = mDefaultTranslation;
        this.mMiwokTranslation = mMiwokTranslation;
        this.icon = icon;
        this.mAudioID = mAudioID;
    }

    public String getmDefaultTranslation() {
        return mDefaultTranslation;
    }

    public void setmDefaultTranslation(String mDefaultTranslation) {
        this.mDefaultTranslation = mDefaultTranslation;
    }

    public String getmMiwokTranslation() {
        return mMiwokTranslation;
    }

    public void setmMiwokTranslation(String mMiwokTranslation) {
        this.mMiwokTranslation = mMiwokTranslation;
    }
}
