package com.example.whatsappandroid;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;
import com.example.whatsappandroid.api.UserAPI;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class RegisterActivity extends AppCompatActivity {

    private AppUserDB db;
    private AppUserImageDB userImageDB;
    private UserImageDao userImageDao;
    private List<UserImage> userImages;
    private UserDao UserDao;
    private List<User> users;
    private UserAPI userAPI;
    private EditText editTextUsername;
    private EditText editTextNickName;
    private EditText editTextPasswordCheck;
    private EditText editTextPassword;
    private Button BSelectImage;
    private ImageView IVPreviewImage;
    private String encodedImage;
    private final int SELECT_PICTURE = 200;


    private void cleanAllFeilds(){
        editTextUsername.setText("");
        editTextNickName.setText("");
        editTextPasswordCheck.setText("");
        editTextPassword.setText("");
    }

    void imageChooser() {

        Intent i = new Intent();
        i.setType("image/*");
        i.setAction(Intent.ACTION_GET_CONTENT);

        // pass the constant to compare it
        // with the returned requestCode
        startActivityForResult(Intent.createChooser(i, "Select Picture"), SELECT_PICTURE);
    }

    public String BitMapToString(Bitmap bitmap) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();


        bitmap.compress(Bitmap.CompressFormat.JPEG, 70, baos);
        byte[] b = baos.toByteArray();
        String temp = Base64.encodeToString(b, Base64.DEFAULT);
        return temp;
    }

    // this function is triggered when user
    // selects the image from the imageChooser
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {

            // compare the resultCode with the
            // SELECT_PICTURE constant
            if (requestCode == SELECT_PICTURE) {
                // Get the url of the image from data
                Uri selectedImageUri = data.getData();
                if (null != selectedImageUri) {
                    // update the preview image in the layout
                    IVPreviewImage.setImageURI(selectedImageUri);
                }
            }
        }
        switch (requestCode) {
            case SELECT_PICTURE:
                if (resultCode == RESULT_OK) {
                    try {
                        final Uri imageUri = data.getData();
                        String imagepath = imageUri.getPath().toString();


                        String[] filePathColumn = {MediaStore.Images.Media.DATA};

                        Cursor cursor = getContentResolver().query(imageUri,
                                filePathColumn, null, null, null);
                        cursor.moveToFirst();

                        int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                        imagepath = cursor.getString(columnIndex);
                        cursor.close();
                        //Toast.makeText(PersonalDetails.this,imagepath, Toast.LENGTH_SHORT).show();
                        final InputStream imageStream = getContentResolver().openInputStream(imageUri);
                        Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);

                        encodedImage = BitMapToString(selectedImage);

                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }

                }
        }
    }

    private void errorValidation(String Title,String messageError)
    {
        AlertDialog.Builder builder
                = new AlertDialog
                .Builder(RegisterActivity.this);
        builder.setMessage(messageError);
        builder.setTitle(Title);
        builder.setCancelable(false);
        builder
                .setNegativeButton(
                        "OK",
                        new DialogInterface
                                .OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog,
                                                int which)
                            {
                                dialog.cancel();
                            }
                        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        BSelectImage = findViewById(R.id.BSelectImage);
        IVPreviewImage = findViewById(R.id.IVPreviewImage);

        BSelectImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageChooser();
            }
        });

        db = Room.databaseBuilder(getApplicationContext(),
                AppUserDB.class, "UsersDB").allowMainThreadQueries().build();
        UserDao = db.userDao();
        users = new ArrayList<User>();
        userImageDB = Room.databaseBuilder(getApplicationContext(),
                AppUserImageDB.class, "UserImagesDB").allowMainThreadQueries().build();
        userImageDao = userImageDB.userImageDao();
        userImages = new ArrayList<UserImage>();
        userAPI = new UserAPI(users, UserDao);
        Button btnBack = findViewById(R.id.BackButton);
        btnBack.setOnClickListener(arg0 -> finish());
        Button btnLogin = findViewById(R.id.RegisterButton);
        btnLogin.setOnClickListener(v -> {

            editTextUsername = (EditText) findViewById(R.id.editTextUsername);
            String username = editTextUsername.getText().toString();

            editTextNickName = (EditText) findViewById(R.id.editTextNickname);
            String nickName = editTextNickName.getText().toString();

            editTextPasswordCheck = (EditText) findViewById(R.id.editTextPasswordCheck);
            String passwordCheck = editTextPasswordCheck.getText().toString();

            editTextPassword = (EditText) findViewById(R.id.editTextPassword);
            String password = editTextPassword.getText().toString();

            // if everything is valid
            if(username.equals("")){
               cleanAllFeilds();
               errorValidation(getString(R.string.payAttenrion),getString(R.string.UsernameRequired));
                return;
            }
            if(nickName.equals("")){
                cleanAllFeilds();
                errorValidation(getString(R.string.payAttenrion),getString(R.string.NicknameRequired));
                return;
            }
            if(password.equals("")){
                cleanAllFeilds();
                errorValidation(getString(R.string.payAttenrion),getString(R.string.PasswordRequired));
                return;
            }
            if(passwordCheck.equals("")){
                cleanAllFeilds();
                errorValidation(getString(R.string.payAttenrion),getString(R.string.ConfirmPasswordReqires));
                return;
            }
            if(username.length() < 3){
                cleanAllFeilds();
                errorValidation(getString(R.string.payAttenrion),getString(R.string.InvalidUsername));
                return;
            }

            if(!password.matches("^(?=.*[0-9])(?=.*[A-Z])[a-zA-Z0-9!@#$%^&*]{6,20}$")){
                cleanAllFeilds();
                errorValidation(getString(R.string.payAttenrion), getString(R.string.PasswordInvalid));
                return;
            }

            if(!password.equals(passwordCheck)){
                cleanAllFeilds();
                errorValidation(getString(R.string.payAttenrion),getString(R.string.PasswordsNotEqual));
                return;
            }

            User newUser = new User(username, nickName, password, encodedImage);
            UserDao.insert(newUser);
            userAPI.post(newUser);
            UserImage userImage = new UserImage(username, encodedImage);
            userImageDao.insert(userImage);
            Intent i = new Intent(this, ChatListActivity.class);
            i.putExtra("Username", username);
            i.putExtra("password",password);
            startActivity(i);
        });
    }
}