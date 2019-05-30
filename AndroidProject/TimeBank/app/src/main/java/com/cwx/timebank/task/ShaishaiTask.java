package com.cwx.timebank.task;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ListView;

import com.cwx.timebank.R;
import com.cwx.timebank.ShaiAdapter;
import com.cwx.timebank.bean.Discuss;
import com.cwx.timebank.bean.Shaishai;
import com.cwx.timebank.bean.ShaishaiBean;
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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;

public class ShaishaiTask extends AsyncTask<String,Void,List> {
    private Context context;
    private ListView listView;

    public ShaishaiTask(Context context, ListView listView) {
        this.context = context;
        this.listView = listView;
    }

    @Override
    protected List doInBackground(String... strings) {
        URL url = null;
        try {
            SharedPreferences sharedPreferences = context.getSharedPreferences("myServer", MODE_PRIVATE);
            String serverUrl = sharedPreferences.getString("serverUrl", "");
            String urlStr = serverUrl + "/shaishai/allshai";
            url = new URL(urlStr);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestProperty("contentType", "UTF-8");//如果给服务器端传的字符有中文，防止字符乱码问题
            InputStream is = connection.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(is);//转换流
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String res = reader.readLine();
            Log.e("lm", "Lli" + res);
            List<Shaishai> shaiList = new Gson().fromJson(res, new TypeToken<List<Shaishai>>() {
            }.getType());
            return shaiList;
            } catch (MalformedURLException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }


    }

    protected void onPostExecute(List list) {
        if (list != null && list.size() != 0) {
            ShaiAdapter shaiAdapter = new ShaiAdapter(context, R.layout.list_view, list);
            listView.setAdapter(shaiAdapter);
        } else {
            Log.e("晒晒错误", "错误");
        }
    }
}
