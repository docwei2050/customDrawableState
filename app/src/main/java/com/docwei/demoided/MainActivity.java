package com.docwei.demoided;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;

public class MainActivity extends ListActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setListAdapter(new ExampleListAdapter());
        getListView().setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Message message= (Message) getListAdapter().getItem(position);
                message.unread=!message.isUnread();
                MessageListItemView itemView= (MessageListItemView) view;
                if(message.unread){
                    itemView.setMessageUnread(true);
                    itemView.setmessageRead(false);
                }else{
                    itemView.setmessageRead(true);
                    itemView.setMessageUnread(false);
                }
            }
        });
    }

    private static class ExampleListAdapter extends BaseAdapter {

        private Message[] messages;

        private ExampleListAdapter() {
            messages = new Message[] {
                    new Message("Gas bill overdue", true),
                    new Message("Congratulations, you've won!", true),
                    new Message("I love you!", true),
                    new Message("Please reply!", true),
                    new Message("You ignoring me?", true),
                    new Message("Not heard from you", true),
                    new Message("Electricity bill", true),
                    new Message("Gas bill", true),
                    new Message("Holiday plans", true),
                    new Message("Marketing stuff", true),
            };
        }

        @Override
        public int getCount() {
            return messages.length;
        }

        @Override
        public Object getItem(int position) {
            return messages[position];
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup viewGroup) {
            MessageListItemView messageListItemView = (MessageListItemView) convertView;

            if (messageListItemView == null) {
                messageListItemView = new MessageListItemView(viewGroup.getContext());
            }

            Message message = (Message) getItem(position);
            messageListItemView.setMessageSubject(message.subject);

            if(message.unread){
                messageListItemView.setMessageUnread(true);
                messageListItemView.setmessageRead(false);
            }else{
                messageListItemView.setmessageRead(true);
                messageListItemView.setMessageUnread(false);
            }

            return messageListItemView;
        }

    }

    private static class Message {

        private String subject;
        private boolean unread;

        private Message(String subject, boolean unread) {
            this.subject = subject;
            this.unread = unread;
        }

        public String getSubject() {
            return subject;
        }

        public void setSubject(String subject) {
            this.subject = subject;
        }

        public boolean isUnread() {
            return unread;
        }

        public void setUnread(boolean unread) {
            this.unread = unread;
        }
    }

}

