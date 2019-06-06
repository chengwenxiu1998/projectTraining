package com.cwx.timebank.task;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ListView;

import com.cwx.timebank.DetailAdapter;

import com.cwx.timebank.R;
import com.cwx.timebank.bean.DiscussReply;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;

public class DetailTask extends AsyncTask<String,Void,List> {
    private Context context;
    private ListView listView;
    private int did;
    public DetailTask(Context context,ListView listView){
        this.context=context;
        this.listView=listView;
    }
    @Override
    protected List doInBackground(String... strings) {
        did=Integer.parseInt(strings[0]);
        URL url = null;
        try {
            SharedPreferences sharedPreferences = context.getSharedPreferences("myServer", MODE_PRIVATE);
            String serverUrl = sharedPreferences.getString("serverUrl", "");
            String urlStr = serverUrl + "/reply/alldis?uId="+did;
            url = new URL(urlStr);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestProperty("contentType", "UTF-8");//如果给服务器端传的字符有中文，防止字符乱码问题
            InputStream is = connection.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(is);//转换流
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String res = reader.readLine();
            Log.e("lm", "Lli" + res);
            List<DiscussReply> replyList = new Gson().fromJson(res, new TypeToken<List<DiscussReply>>() {
            }.getType());

            Log.e("lm", replyList.toString());
            return replyList;


        } catch (MalformedURLException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }


    }
    protected void onPostExecute(List list) {
        if(list!=null &&list.size()!=0){
            DetailAdapter detailAdapter=new DetailAdapter(context, R.layout.join_talk_item,list);
            listView.setAdapter(detailAdapter);
        }else{
            /*Log.e("晒晒错误","错误");*/
        }
    }
}

