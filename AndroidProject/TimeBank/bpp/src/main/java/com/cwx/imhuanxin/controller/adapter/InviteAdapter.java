package com.cwx.imhuanxin.controller.adapter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.cwx.imhuanxin.R;
import com.cwx.imhuanxin.model.bean.InvationInfo;
import com.cwx.imhuanxin.model.bean.UserInfo;

import java.util.ArrayList;
import java.util.List;

//邀请信息列表页面的适配器
public class InviteAdapter extends BaseAdapter {
    public OnInviteListener mOnInviteListener;
    private InvationInfo invationInfo;
    private Context mContext;
    public List<InvationInfo> mInvitationInfos = new ArrayList<>();
    public InviteAdapter(Context context,OnInviteListener onInviteListener) {
        mContext = context;
        mOnInviteListener = onInviteListener;

    }

    //刷新数据的方法
    public void refresh(List<InvationInfo> invitationInfos){
        Log.e("哈哈哈哈哈哈哈哈哈哈或或或或",invitationInfos.size()+"");

        Log.e("哈哈哈哈哈哈哈哈哈哈或或",invitationInfos.toString());
        if(invitationInfos!=null && invitationInfos.size()>=0){
            mInvitationInfos.clear();
            mInvitationInfos.addAll(invitationInfos);
            notifyDataSetChanged();
        }
    }

    @Override
    public int getCount() {
        return mInvitationInfos == null ? 0 :mInvitationInfos.size();
    }

    @Override
    public Object getItem(int position) {
        return mInvitationInfos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //1.获取或创建viewHolder
        ViewHodler hodler = null;
        if(convertView==null){
            hodler=new ViewHodler();
            convertView=View.inflate(mContext, R.layout.item_invite,null);
            hodler.name = convertView.findViewById(R.id.tv_invite_name);
            hodler.reason = convertView.findViewById(R.id.tv_invite_reason);
            hodler.accept = convertView.findViewById(R.id.btn_invite_accept);
            hodler.reject = convertView.findViewById(R.id.btn_invite_reject);
            convertView.setTag(hodler);
        }else{
            hodler = (ViewHodler)convertView.getTag();
        }

        //2.获取当前item数据
        invationInfo = mInvitationInfos.get(position);
        Log.e("invationInfo",invationInfo.toString());
        //3.显示当前item数据
        UserInfo user = invationInfo.getUser();
        Log.e("user","用户"+user.toString());
        if(user!=null){//联系人
            //名称的展示
            hodler.name.setText(invationInfo.getUser().getName());
            hodler.accept.setVisibility(View.GONE);
            hodler.reject.setVisibility(View.GONE);
            //原因
            if(invationInfo.getStatus()==InvationInfo.InvitationStatus.NEW_INVITE){//新的邀请
                if(invationInfo.getReason()==null){
                    hodler.reason.setText("添加好友");
                }else{
                    hodler.reason.setText(invationInfo.getReason());
                }
                hodler.accept.setVisibility(View.VISIBLE);
                hodler.reject.setVisibility(View.VISIBLE);
            }else if(invationInfo.getStatus()== InvationInfo.InvitationStatus.INVITE_ACCCEPT){//接受邀请
                Log.e("inviteAdapter中的接受邀请",invationInfo.toString());
                if(invationInfo.getReason()==null){
                    hodler.reason.setText("接受邀请");
                }else{
                    hodler.reason.setText(invationInfo.getReason());
                }
            }else if(invationInfo.getStatus()==InvationInfo.InvitationStatus.INVITE_ACCEPT_BY_PEER){//邀请被接受
                Log.e("inviteAdapter中的邀请被接受",invationInfo.toString());
                if(invationInfo.getReason()==null){
                    hodler.reason.setText("邀请被接受");
                }else{
                    hodler.reason.setText(invationInfo.getReason());
                }
            }

            //按钮的处理
            hodler.accept.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnInviteListener.onAccept(invationInfo);
                }
            });
            hodler.reject.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnInviteListener.onReject(invationInfo);
                }
            });
        }else{//群组

        }
        //4.返回view
        return convertView;
    }
    private class ViewHodler{

        private TextView name;
        private TextView reason;
        private Button accept;
        private Button reject;

    }

    public interface  OnInviteListener{
        //联系人接受按钮的点击事件
        void onAccept(InvationInfo invationInfo);
        //联系人拒绝按钮的点击事件
        void onReject(InvationInfo invationInfo);
    }
}
