package com.cwx.timebank;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.cwx.timebank.bean.ShaiReply;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class ShaiDetailAdapter extends BaseAdapter{
    private Context context;
    private int itemView;
    private List<ShaiReply> list;
    private int rcount;
    public  ShaiDetailAdapter(Context context,int itemView,List<ShaiReply> list){
        this.context=context;
        this.itemView=itemView;
        this.list=list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView==null){
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(itemView, null);
        }


        TextView textView1=convertView.findViewById(R.id.tv_nickname);
        TextView textView2=convertView.findViewById(R.id.tv_send_time);
        TextView textView3=convertView.findViewById(R.id.tv_talk_text);

        ShaiReply shaiReply=(ShaiReply) list.get(position);
        textView1.setText(shaiReply.getUser().getuNickName());
         //时间 字符串转变成Date
        String retime=shaiReply.getRtime();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String getuTime=null;
        try {
            Date date=sdf.parse(retime);
            //当前时间
            Calendar currentDate=Calendar.getInstance();

            //计算时间差
            if(shaiReply.getRtime()!=null) {
                if (currentDate.get(Calendar.YEAR) - (date.getYear()+1900) < 1) {
                    if (currentDate.get(Calendar.MONTH) -date.getMonth() < 1) {
                        if (currentDate.get(Calendar.DAY_OF_MONTH) - date.getDate()<1) {
                            if (currentDate.get(Calendar.HOUR_OF_DAY) -date.getHours() < 1) {
                                getuTime = "发布于" + (currentDate.get(Calendar.MINUTE) - date.getMinutes()) + "分钟前";
                            } else if (currentDate.get(Calendar.HOUR_OF_DAY) - date.getHours() > 1) {
                                if (currentDate.get(Calendar.MINUTE) - date.getMinutes() > 0) {
                                    getuTime = "发布于" + (currentDate.get(Calendar.HOUR_OF_DAY) - date.getHours()) + "小时前";
                                } else {
                                    getuTime = "发布于" + (currentDate.get(Calendar.HOUR_OF_DAY) - date.getHours() - 1) + "小时前";
                                }
                            }
                        } else if (currentDate.get(Calendar.DAY_OF_MONTH) - date.getDate() < 2) {
                            getuTime = "昨天" + new SimpleDateFormat("HH:mm").format(date);
                        } else {
                            getuTime = new SimpleDateFormat("MM-dd").format(date);
                        }
                    } else {
                        getuTime = new SimpleDateFormat("MM-dd").format(date);
                    }
                } else {
                    getuTime = new SimpleDateFormat("yyyy-MM-dd").format(date);
                }
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

        textView2.setText(getuTime);
        textView3.setText(shaiReply.getRcontent());

        return convertView;
    }
}

