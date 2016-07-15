package com.example.dailytv.utils;

import android.util.Log;

import com.example.dailytv.beans.Program;
import com.example.dailytv.beans.Store;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 荒原中的歌声 on 2016/7/6.
 */
public class ParseJson{

    public static List<ArrayList<Program>> parseZhiboJson(String json){
        List<ArrayList<Program>> list = new ArrayList<>();
        try{
            JSONObject jsonObject = new JSONObject(json);
            JSONArray classify = jsonObject.getJSONArray("classify");
            for(int i = 0; i < classify.length(); i++){
                ArrayList<Program> list0 = new ArrayList<>();
                JSONObject programArray = classify.getJSONObject(i);
                JSONArray progamList = programArray.getJSONArray("programs");
                for(int j = 0; j < progamList.length(); j++){
                    JSONObject programJson = progamList.getJSONObject(j);
                    String tvName = programJson.getString("name");
                    String url = programJson.getString("url");
                    list0.add(new Program(tvName, url));
                }
                list.add(list0);
            }
        }catch(JSONException e){
            e.printStackTrace();
        }
        Log.e("ParseJson", "list.size():" + list.size());
        return list;
    }

    public static  List<Store> parseStoreJson(String str){
        List<Store> list = new ArrayList<>();
        try{
            JSONObject object = new JSONObject(str);
            JSONArray items = object.getJSONArray("items");
            for(int i = 0; i < items.length(); i++){
                ArrayList<Program> list0 = new ArrayList<>();
                JSONObject jsonObject = items.getJSONObject(i);
                //String imgUrl = jsonObject.getString("imgUrl");
                String text = jsonObject.getString("text");
                //Store store=new Store(imgUrl,text);
                JSONArray array = jsonObject.getJSONArray("programs");
                for(int j = 0; j < array.length(); j++){
                    JSONObject programJson = array.getJSONObject(j);
                    String tvName = programJson.getString("name");
                    String url = programJson.getString("url");
                    list0.add(new Program(tvName, url));
                }
                Store store = new Store(text, list0);
                list.add(store);
            }
            return list;
        }catch(JSONException e){
            e.printStackTrace();
        }
        return null;
    }


}