package com.cwx.timebank.task;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.util.Log;

import com.cwx.timebank.bean.DiscussReply;
import com.google.gson.Gson;

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

public class DiscussReplyTask extends AsyncTask {
    private Context context;
    private int did;
    private int uid;
    private String reply;
    private String replyTime;
    public DiscussReplyTask( Context context){
        this.context = context;
    }
    @Override
    protected Object doInBackground(Object[] objects) {
        did=Integer.valueOf(String.valueOf(objects[0]));
        uid=Integer.valueOf(String.valueOf(objects[1]));
        reply=(String)objects[2];
        replyTime=(String)objects[3];
        try {
            SharedPreferences sharedPreferences = context.getSharedPreferences("myServer", MODE_PRIVATE);
            String serverUrl = sharedPreferences.getString("serverUrl", "");
            String urlStr = serverUrl + "/reply/insertI?did="+did+"&uid="+uid+"&text="+reply+"&time="+replyTime;
            URL url = new URL(urlStr);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestProperty("contentType", "UTF-8");//如果给服务器端传的字符有中文，防止字符乱码问题
            InputStream is = connection.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(is);//转换流
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String res = reader.readLine();
            return null;
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

    }
}

