package com.example.whatsappandroid.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.whatsappandroid.Classes.Contact;
import com.example.whatsappandroid.R;

import java.util.List;

public class CustomListAdapter extends ArrayAdapter<Contact> {
    LayoutInflater inflater;

    public CustomListAdapter(Context ctx, List<Contact> contactArrayList) {
        super(ctx, R.layout.custom_list_item, contactArrayList);

        this.inflater = LayoutInflater.from(ctx);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        Contact contact = getItem(position);

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.custom_list_item, parent, false);
        }

        ImageView imageView = convertView.findViewById(R.id.profile_image);
        TextView userName = convertView.findViewById(R.id.user_name);
        TextView lastMsg = convertView.findViewById(R.id.last_massage);
        TextView time = convertView.findViewById(R.id.time);

        imageView.setImageResource(contact.getPictureId());
        userName.setText(contact.getId());
        lastMsg.setText(contact.getLast());
        time.setText(contact.getLastdate());

        return convertView;
    }
}