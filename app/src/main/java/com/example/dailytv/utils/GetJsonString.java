package com.example.dailytv.utils;

import android.content.Context;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by 荒原中的歌声 on 2016/7/8.
 */
public class GetJsonString {

    public  static   String  getJsonString(Context context,String fileName){

        InputStream is = null;
        BufferedReader br = null;


        try{
            is = context.getResources().getAssets().open(fileName);
            StringBuffer sb = new StringBuffer();
            br = new BufferedReader(new InputStreamReader(is));
            String line = "";
            while((line = br.readLine()) != null){
                sb.append(line);
            }
            return sb.toString();
        }catch(IOException e){
            e.printStackTrace();
        }finally{
            if(is != null){
                try{
                    is.close();
                }catch(IOException e){
                    e.printStackTrace();
                }
            }
            if(br != null){
                try{
                    br.close();
                }catch(IOException e){
                    e.printStackTrace();
                }
            }
        }
        return null;



    }

}
