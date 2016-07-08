package com.example.dailytv.beans;

import android.os.Parcel;
import android.os.Parcelable;

import cn.bmob.v3.BmobObject;

/**
 * Created by 荒原中的歌声 on 2016/7/6.
 */
public class Program  extends BmobObject implements Parcelable {

    private String tvName;
    private String url;

    public Program(String tvName, String url){
        this.tvName = tvName;
        this.url = url;
    }

    protected Program(Parcel in){
        tvName = in.readString();
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

    public String getTVName(){
        return tvName;
    }

    public void setTVName(String tvName){
        this.tvName = tvName;
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
        dest.writeString(tvName);
        dest.writeString(url);
    }
}
