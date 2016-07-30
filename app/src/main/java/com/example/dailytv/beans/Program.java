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

    public Program(String tableName, String tvName, String url){
        super(tableName);
        this.tvName = tvName;
        this.url = url;
    }

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

    @Override
    public boolean equals(Object o){
        if(this == o)
            return true;
        if(!(o instanceof Program))
            return false;
        Program program = (Program) o;
        if(tvName != null ? !tvName.equals(program.tvName) : program.tvName != null)
            return false;
        return getUrl() != null ? getUrl().equals(program.getUrl()) : program.getUrl() == null;
    }

    @Override
    public int hashCode(){
        int result = tvName != null ? tvName.hashCode() : 0;
        result = 31 * result + (getUrl() != null ? getUrl().hashCode() : 0);
        return result;
    }
}
