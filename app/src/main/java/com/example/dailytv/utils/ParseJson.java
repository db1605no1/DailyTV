package com.example.dailytv.utils;

import android.util.Log;

import com.example.dailytv.beans.Program;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 荒原中的歌声 on 2016/7/6.
 */
public class ParseJson{

    public static List<ArrayList<Program>> parseJson(String json){
        Log.e("ParseJson", json);
        List<ArrayList<Program> >list = new ArrayList<>();
        try{
            JSONObject jsonObject = new JSONObject(json);
            JSONArray classify = jsonObject.getJSONArray("classify");
            for(int i = 0; i < classify.length(); i++){
                ArrayList<Program> list0 = new ArrayList<>();
                JSONObject programArray = classify.getJSONObject(i);
                JSONArray progamList = programArray.getJSONArray("programs");
                for(int j = 0; j < progamList.length(); j++){
                    JSONObject programJson = progamList.getJSONObject(j);
                    String name = programJson.getString("name");
                    String url = programJson.getString("url");
                    list0.add(new Program(name, url));
                }
                list.add(list0);

            }
        }catch(JSONException e){
            e.printStackTrace();
        }

    Log.e("ParseJson", "list.size():" + list.size());
        return list;

    }
}
