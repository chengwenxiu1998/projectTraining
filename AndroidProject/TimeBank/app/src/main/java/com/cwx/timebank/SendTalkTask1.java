package com.cwx.timebank;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;

import com.cwx.timebank.bean.Discuss;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;

public class SendTalkTask1 extends AsyncTask {
    private String content;
    private int UserId;
    private Context context;
    public SendTalkTask1(Context context){
        this.context=context;
    }
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected Object doInBackground(Object[] objects) {
        content=(String)objects[0];
        UserId=(Integer)objects[1];
        URL url = null;
        try {
            SharedPreferences sharedPreferences = context.getSharedPreferences("myServer", MODE_PRIVATE);
            String serverUrl = sharedPreferences.getString("serverUrl", "");
            String urlStr = serverUrl + "/discuss/insertByUid?text="+content+"&uid="+UserId;
            url = new URL(urlStr);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestProperty("contentType", "UTF-8");//如果给服务器端传的字符有中文，防止字符乱码问题
            InputStream is = connection.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(is);//转换流
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String res = reader.readLine();
            Log.e("fabu",res);

            SharedPreferences sp = context.getSharedPreferences("isFromMy",Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sp.edit();
            editor.putBoolean("isFromQuanZi",true);
            editor.commit();
            Intent intent=new Intent();
            intent.setClass(context,MainActivity.class);
            context.startActivity(intent);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }  catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(Object o) {

        super.onPostExecute(o);
    }
}

