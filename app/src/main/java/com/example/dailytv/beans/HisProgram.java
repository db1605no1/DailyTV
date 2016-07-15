package com.example.dailytv.beans;

/**
 * Created by 荒原中的歌声 on 2016/7/6.
 */
public class HisProgram {

    private String tvName;
    private String url;

    public HisProgram(){
    }

    public HisProgram(String tvName, String url){
        this.tvName = tvName;
        this.url = url;
    }




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
    public boolean equals(Object o){
        if(this == o)
            return true;
        if(!(o instanceof HisProgram))
            return false;
        HisProgram program = (HisProgram) o;
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
