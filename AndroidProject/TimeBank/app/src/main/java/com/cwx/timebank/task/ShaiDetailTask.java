package com.cwx.timebank.task;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ListView;

import com.cwx.timebank.R;
import com.cwx.timebank.ShaiDetailAdapter;
import com.cwx.timebank.bean.ShaiReply;
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

public class ShaiDetailTask extends AsyncTask<String,Void,List> {
    private Context context;
    private ListView listView;
    private int sid;

    public  ShaiDetailTask(Context context,ListView listView){
        this.context=context;
        this.listView=listView;
    }
    @Override
    protected List doInBackground(String... strings) {
        sid=Integer.valueOf(String.valueOf(strings[0]));
        try {
            SharedPreferences sharedPreferences = context.getSharedPreferences("myServer", MODE_PRIVATE);
            String serverUrl = sharedPreferences.getString("serverUrl", "");
            String urlStr = serverUrl + "/shaireply/allres?sid="+sid;
            URL url = new URL(urlStr);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestProperty("contentType", "UTF-8");//如果给服务器端传的字符有中文，防止字符乱码问题
            InputStream is = connection.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(is);//转换流
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String res = reader.readLine();
            Log.e("lm", "Lli" + res);
            List<ShaiReply> shaiReplyList = new Gson().fromJson(res, new TypeToken<List<ShaiReply>>() {
            }.getType());
            return shaiReplyList;
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
            ShaiDetailAdapter shaiDetailAdapter=new ShaiDetailAdapter(context, R.layout.shaishai_comment_item,list);
            listView.setAdapter(shaiDetailAdapter);
        }else{
            /*Log.e("晒晒回复错误","错误");*/
        }
    }
}

