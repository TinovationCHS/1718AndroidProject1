package com.example.anjanbharadwaj.androidapp;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by anjanbharadwaj on 4/7/18.
 */

public class Subject implements Parcelable{
    public Subject(String c, String d){
        className = c;
        classDescription = d;
    }
    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getClassDescription() {
        return classDescription;
    }


    public void setClassDescription(String classDescription) {
        this.classDescription = classDescription;
    }

    public String className;
    public String classDescription;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int i) {
        dest.writeString(className);
        dest.writeString(classDescription);

    }
}
