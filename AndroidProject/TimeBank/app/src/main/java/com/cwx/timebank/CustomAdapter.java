package com.cwx.timebank;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.cwx.timebank.bean.Discuss;
import com.cwx.timebank.bean.DiscussBean;

import java.util.List;

public class CustomAdapter extends BaseAdapter {
    private Context context;
    private int itemView;
    private List<Discuss> list;

    public CustomAdapter(Context context,int itemView,List<Discuss> list){
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

        ImageView imageView1=convertView.findViewById(R.id.lv_image1);
        CircleImageView imageView=convertView.findViewById(R.id.lv_image);
        TextView textView1=convertView.findViewById(R.id.tv_text);
        TextView textView2=convertView.findViewById(R.id.tv_text1);

        final Discuss discussBean=(Discuss)list.get(position);
        imageView1.setImageResource(R.drawable.heart);
        imageView.setImageResource(R.drawable.p5);
        textView1.setText("#"+discussBean.getTag().getTagText()+"#");
        textView2.setText(discussBean.getdTopicCoutent());
        Button btnJoinTalk = convertView.findViewById(R.id.btn_join_talk);
        btnJoinTalk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Discuss discuss=(Discuss)list.get(position);
                Intent intent = new Intent(context,JoinTalkActivity.class);
                intent.putExtra("netName",discuss.getUser().getuNickName());
                intent.putExtra("tag",discuss.getTag().getTagText());
                intent.putExtra("content",discuss.getdTopicCoutent());
                intent.putExtra("which",""+discuss.getdId());//讨论的id
                context.startActivity(intent);
            }
        });
        return convertView;
    }


}

