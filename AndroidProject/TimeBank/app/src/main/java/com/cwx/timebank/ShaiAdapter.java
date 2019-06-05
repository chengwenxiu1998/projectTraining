package com.cwx.timebank;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cwx.timebank.bean.Shaishai;
import com.cwx.timebank.task.ClickTask;
import com.cwx.timebank.task.ShaiReplyTask;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class ShaiAdapter extends BaseAdapter {
    private Button btnsend;
    private SharedPreferences sp;
    private Context context;
    private int itemView;
    private List<Shaishai> list;
    private ImageView imageView1;
    private List listCount=new ArrayList<>();//赞的个数的集合
    private List listComment=new ArrayList<>();//评论个数的集合

    public ShaiAdapter(Context context,int itemView,List<Shaishai> list){
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
    public View getView(final int position, View convertView, ViewGroup parent) {

        // 从Activity的Context上下文环境中获取 布局填充器（根据布局文件创建相应对象）
        if(convertView==null) {
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(itemView, null);
        }


        //CircleImageView imageView=convertView.findViewById(R.id.iv_headphoto);
        TextView textView1=convertView.findViewById(R.id.tv_petname);//昵称
        TextView textView2=convertView.findViewById(R.id.tv_time);//时间
        TextView textView3=convertView.findViewById(R.id.tv_shaicontent);//晒晒的内容
        final TextView textView4=convertView.findViewById(R.id.tv_review);//评论的个数
        final TextView  textView5=convertView.findViewById(R.id.tv_zan);//赞的个数
        //将点赞的个数放在一个list中
        for(int i=0;i<list.size();i++){
            Shaishai shaishai=(Shaishai)list.get(i);
            listCount.add(shaishai.getScount());
        }

        //将评论的个数放在一个list中
        for(int i=0;i<list.size();i++){
            Shaishai shaishai=list.get(i);
            listComment.add(shaishai.getShaiReplys().size());
        }



        final Shaishai  shaishai=(Shaishai)list.get(position);
        textView1.setText(shaishai.getUser().getuNickName());
        textView3.setText(shaishai.getStext());
        textView4.setText(""+shaishai.getShaiReplys().size());//回复的个数
        textView5.setText(""+shaishai.getScount());//赞的个数

        //当前时间
        String retime=shaishai.getStime();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String getuTime=null;
        //计算时间差
        try {
            Date date=sdf.parse(retime);
            //当前时间
            Calendar currentDate=Calendar.getInstance();
            if(shaishai.getStime()!=null) {
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
        final RelativeLayout rl_comment=convertView.findViewById(R.id.rl_comment);
        final LinearLayout linearLayout=convertView.findViewById(R.id.ll_shishai_detail);
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("WrongConstant")
            @Override
            public void onClick(View v) {
                if(rl_comment.getVisibility()==0){
                    rl_comment.setGravity(View.GONE);
                }
                Shaishai shaishaiBean1=(Shaishai) list.get(position);
                Intent intent = new Intent(context,ShaiShaiDetailActivity.class);
                intent.putExtra("netName",shaishaiBean1.getUser().getuNickName());
                intent.putExtra("content",shaishaiBean1.getStext());
                intent.putExtra("which",""+shaishaiBean1.getSid());
                context.startActivity(intent);
            }
        });
        final EditText comment_content = convertView.findViewById(R.id.comment_content);
        imageView1=convertView.findViewById(R.id.iv_see_comment);
        //点击图片，弹出输入框
        imageView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sp=context.getSharedPreferences("userInfo",Context.MODE_PRIVATE);
                if(sp.getInt("userId",0)!=0){//用户已经登录
                    rl_comment.setVisibility(View.VISIBLE);
                }else
                {
                    Intent intent=new Intent(context,LoginActivity.class);
                    context.startActivity(intent);
                }
            }
        });

        btnsend=convertView.findViewById(R.id.comment_send);
        //点击发送按钮
        btnsend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String content=comment_content.getText().toString();
                //获取晒晒是那个
                Shaishai shaishaiBean2=(Shaishai)list.get(position);
                int sid1=shaishaiBean2.getSid();
                int uid=sp.getInt("userId",0);
                Date date=new Date();
                SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String time=format.format(date);
                InputMethodManager im = (InputMethodManager)context.getApplicationContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                im.hideSoftInputFromWindow(comment_content.getWindowToken(), 0);
                rl_comment.setVisibility(View.GONE);
                int reply=shaishaiBean2.getShaiReplys().size()+1;
                textView4.setText(""+reply);
                ShaiReplyTask shaiReplyTask=new ShaiReplyTask(context);
                shaiReplyTask.execute(sid1,uid,content,time);
            }
        });


        ImageView imageView=convertView.findViewById(R.id.iv_like);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Shaishai shaishaiBean1=(Shaishai)list.get(position);
                int sid=shaishaiBean1.getSid();
                int count=Integer.valueOf(String.valueOf(listCount.get(position)))+1;
                listCount.set(position,count);
                textView5.setText(""+count);
                ClickTask clickTask=new ClickTask(context);
                clickTask.execute(sid,count);
            }
        });
        return convertView;
    }


}

