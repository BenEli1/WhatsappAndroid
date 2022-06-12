package com.example.whatsappandroid.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.whatsappandroid.R;

import java.util.List;

public class CustomMsgAdapter extends ArrayAdapter<Message> {
    LayoutInflater inflater;

    public CustomMsgAdapter(Context ctx, List<Message> MessageArrayList) {
        super(ctx, R.layout.custom_list_item, MessageArrayList);

        this.inflater = LayoutInflater.from(ctx);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        Message msg = getItem(position);

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.custom_msg_incoming, parent, false);
        }


        TextView lastMsg = convertView.findViewById(R.id.last_massage);
        TextView time = convertView.findViewById(R.id.time);

        lastMsg.setText(msg.getContect());
        time.setText(msg.getCreated());

        return convertView;
    }
}
