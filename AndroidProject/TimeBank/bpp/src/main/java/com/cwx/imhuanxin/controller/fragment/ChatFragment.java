package com.cwx.imhuanxin.controller.fragment;

import android.content.Intent;
import android.os.Message;
import android.util.Log;

import com.baidu.platform.comapi.map.E;
import com.cwx.imhuanxin.controller.activity.ChatActivity;
import com.cwx.imhuanxin.utils.Constant;
import com.hyphenate.EMMessageListener;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMConversation;
import com.hyphenate.chat.EMMessage;
import com.hyphenate.easeui.EaseConstant;
import com.hyphenate.easeui.EaseUI;
import com.hyphenate.easeui.domain.EaseUser;
import com.hyphenate.easeui.ui.EaseConversationListFragment;

import java.util.List;


//会话列表页面
public class ChatFragment extends EaseConversationListFragment {
    @Override
    protected void initView() {
        super.initView();

        //跳转到会话详情页面
        setConversationListItemClickListener(new EaseConversationListItemClickListener() {
            @Override
            public void onListItemClicked(EMConversation conversation) {
                Intent intent = new Intent(getActivity(), ChatActivity.class);

                Log.e("getAllMessages",conversation.getAllMessages().toString());
                intent.putExtra(EaseConstant.EXTRA_USER_ID,conversation.conversationId());
                intent.putExtra(EaseConstant.EXTRA_USER_NICK,conversation.getExtField());
                Log.e("conversation","conversation.getAllMessages()——"+conversation.getAllMessages().toString());
                Log.e("conversation","conversation.getExtField()——"+conversation.getExtField());
//                Log.e("conversation","conversation.getMessage——"+conversation.getMessage(EaseConstant.EXTRA_USER_NICK,true).toString());
                //是否是群聊
                if(conversation.getType()==EMConversation.EMConversationType.GroupChat){
                    intent.putExtra(EaseConstant.EXTRA_CHAT_TYPE,EaseConstant.CHATTYPE_GROUP);
                }
                startActivity(intent);
            }
        });
        //清空集合数据
        conversationList.clear();
        //监听会话消息
        EMClient.getInstance().chatManager().addMessageListener(emMassageListener);
    }

    private EMMessageListener emMassageListener = new EMMessageListener() {
        @Override
        public void onMessageReceived(List<EMMessage> list) {
            for(int i=0;i<list.size();i++){
                getArguments().putString(EaseConstant.EXTRA_USER_NICK,list.get(i).getStringAttribute("nickname",""));
            }

            Log.e("cwx","我的消息被受到了");
            Log.e("cwx","list.size"+list.size());
            Log.e("cwx","message"+list.get(0).getStringAttribute("nickname",""));
            //设置数据
            //EaseUI.getInstance().getNotifier().onNewMesg(list);
            EaseUI.getInstance().getNotifier().notify(list);



            //刷新页面
            refresh();
        }

        @Override
        public void onCmdMessageReceived(List<EMMessage> list) {

        }

        @Override
        public void onMessageRead(List<EMMessage> list) {

        }

        @Override
        public void onMessageDelivered(List<EMMessage> list) {

        }

        @Override
        public void onMessageRecalled(List<EMMessage> list) {

        }

        @Override
        public void onMessageChanged(EMMessage emMessage, Object o) {

        }
    };
}
