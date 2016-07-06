package com.example.dailytv.beans;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by 荒原中的歌声 on 2016/7/6.
 */
public class Program implements Parcelable{

    private String name;
    private String url;

    public Program(String name, String url){
        this.name = name;
        this.url = url;
    }

    protected Program(Parcel in){
        name = in.readString();
        url = in.readString();
    }

    public static final Creator<Program> CREATOR = new Creator<Program>(){
        @Override
        public Program createFromParcel(Parcel in){
            return new Program(in.readString(),in.readString());
        }

        @Override
        public Program[] newArray(int size){
            return new Program[size];
        }
    };

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getUrl(){
        return url;
    }

    public void setUrl(String url){
        this.url = url;
    }

    @Override
    public int describeContents(){
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags){
        dest.writeString(name);
        dest.writeString(url);
    }
}
