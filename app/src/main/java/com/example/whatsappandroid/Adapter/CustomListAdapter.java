package com.example.whatsappandroid.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.whatsappandroid.CreatedClasses.Contact;
import com.example.whatsappandroid.CreatedClasses.UserImage;
import com.example.whatsappandroid.R;

import java.util.List;

public class CustomListAdapter extends ArrayAdapter<Contact> {
    LayoutInflater inflater;
    private List<UserImage> userImages;
    final private int[] profilePictures = {
            R.drawable.blue, R.drawable.gold, R.drawable.green,
            R.drawable.red, R.drawable.lightblue, R.drawable.custom_button
    };
    public CustomListAdapter(Context ctx, List<Contact> contactArrayList, List<UserImage> userImages) {
        super(ctx, R.layout.custom_list_item, contactArrayList);
        this.inflater = LayoutInflater.from(ctx);
        this.userImages = userImages;
    }

    private Bitmap convertBase64ToBitmap(String b64) {
        byte[] imageAsBytes = Base64.decode(b64.getBytes(), Base64.DEFAULT);
        return BitmapFactory.decodeByteArray(imageAsBytes, 0, imageAsBytes.length);
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
        userName.setText(contact.getId());
        lastMsg.setText(contact.getLast());
        time.setText(contact.getLastdate());

        Bitmap bitmap;

        boolean imageNotFount = true;

        for(UserImage userImage : userImages){
            if(userImage.getUsername().equals(contact.getId()) && userImage.getImage()!=null) {
                bitmap = convertBase64ToBitmap(userImage.getImage());
                imageView.setImageBitmap(bitmap);
                imageNotFount = false;
                break;
            }
        }

        if(imageNotFount) {
            imageView.setImageResource(profilePictures[1]);
        }

        return convertView;
    }
}
