package com.cwx.timebank.task;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ListView;

import com.cwx.timebank.CustomAdapter;
import com.cwx.timebank.R;
import com.cwx.timebank.bean.Discuss;
import com.cwx.timebank.bean.DiscussBean;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;

public class DisussTask extends AsyncTask<String,Void,List> {
    private Context context;
    private ListView lv;

    public DisussTask(Context context,ListView lv){
        this.context=context;
        this.lv=lv;
    }

    protected List doInBackground(String... strings) {
        URL url = null;
        try {
            SharedPreferences sharedPreferences = context.getSharedPreferences("myServer", MODE_PRIVATE);
            String serverUrl = sharedPreferences.getString("serverUrl", "");
            String urlStr = serverUrl + "/discuss/alldis";
            url = new URL(urlStr);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestProperty("contentType", "UTF-8");//如果给服务器端传的字符有中文，防止字符乱码问题
            InputStream is = connection.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(is);//转换流
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String res = reader.readLine();
            Log.e("lm", "Lli" + res);
            List<Discuss> discussList = new Gson().fromJson(res, new TypeToken<List<Discuss>>() {
            }.getType());

            Log.e("lm", discussList.toString());
            return discussList;
        } catch (MalformedURLException e1) {
            e1.printStackTrace();
            return null;
        } catch (IOException e1) {
            e1.printStackTrace();
            return null;
        }


    }


    protected void onPostExecute(List list) {
        if(list!=null &&list.size()!=0){
            CustomAdapter customAdapter=new CustomAdapter(context, R.layout.talk_item,list);
            lv.setAdapter(customAdapter);
        }else{
            /*Log.e("讨论错误","错误");*/
        }
    }
}

