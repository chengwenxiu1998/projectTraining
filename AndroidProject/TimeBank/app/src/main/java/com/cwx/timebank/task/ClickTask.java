package com.cwx.timebank.task;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import static android.content.Context.MODE_PRIVATE;

public class ClickTask extends AsyncTask {
    private Context context;
    private int Sid;
    private int count;
    public ClickTask( Context context){
        this.context = context;
    }
    @Override
    protected Object doInBackground(Object[] objects) {
        Sid=Integer.valueOf(String.valueOf(objects[0]));
        count=Integer.valueOf(String.valueOf(objects[1]));
        try {
            SharedPreferences sharedPreferences = context.getSharedPreferences("myServer", MODE_PRIVATE);
            String serverUrl = sharedPreferences.getString("serverUrl", "");
            String urlStr = serverUrl + "/shaishai/updateshai?sid="+Sid+"&count="+count;
            URL url = new URL(urlStr);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestProperty("contentType", "UTF-8");//如果给服务器端传的字符有中文，防止字符乱码问题
            InputStream is = connection.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(is);//转换流
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String res = reader.readLine();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}

