package com.docwei.demoided;

import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MessageListItemView extends RelativeLayout {

    /**
     * Custom message unread state variable for use with a {@link android.graphics.drawable.StateListDrawable}.
     */
    private static final int[] STATE_MESSAGE_UNREAD = {R.attr.state_message_unread};
    private static final int[] STATE_MESSAGE_READ = {R.attr.state_message_read};

    private TextView messageSubject;
    private boolean messageUnread;
    private boolean messageRead;

    public MessageListItemView(Context context) {
        this(context, null);
    }

    public MessageListItemView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);

        loadViews();
    }

    public MessageListItemView(Context context, AttributeSet attributeSet, int defStyle) {
        super(context, attributeSet, defStyle);

        loadViews();
    }

    private void loadViews() {
        LayoutInflater layoutInflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        layoutInflater.inflate(R.layout.message_list_item, this, true);

        int fiveDPInPixels = convertDIPToPixels(5);
        int fiftyDPInPixels = convertDIPToPixels(50);

        setPadding(fiveDPInPixels, fiveDPInPixels, fiveDPInPixels, fiveDPInPixels);
        setLayoutParams(new AbsListView.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, fiftyDPInPixels));
        setBackgroundResource(R.drawable.message_list_item_background);

        messageSubject = (TextView) findViewById(R.id.message_subject);
    }

    public int convertDIPToPixels(int dip) {
        // In production code this method would exist in a utility library.
        // e.g see my ScreenUtils class: https://gist.github.com/2504204
        DisplayMetrics displayMetrics = getContext().getResources().getDisplayMetrics();
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dip, displayMetrics);
    }

    @Override
    protected int[] onCreateDrawableState(int extraSpace) {
        if (messageUnread) {
            final int[] drawableState = super.onCreateDrawableState(extraSpace + 1);
            mergeDrawableStates(drawableState, STATE_MESSAGE_UNREAD);
            return drawableState;
        } else if (messageRead) {
            final int[] drawableState = super.onCreateDrawableState(extraSpace + 1);

            mergeDrawableStates(drawableState, STATE_MESSAGE_READ);

            return drawableState;
        }
        return super.onCreateDrawableState(extraSpace);
    }
    public void setMessageSubject(String subject) {
        messageSubject.setText(subject);
    }

    public void setMessageUnread(boolean messageUnread) {
        if (this.messageUnread != messageUnread) {
            this.messageUnread = messageUnread;
            refreshDrawableState();
        }
    }

    public void setmessageRead(boolean messageread) {
        if (this.messageRead != messageread) {
            this.messageRead =  messageread;
            refreshDrawableState();
        }
    }

    public boolean isMessageUnread() {
        return messageUnread;
    }

    public boolean isMessageRead() {
        return messageRead;
    }
}
