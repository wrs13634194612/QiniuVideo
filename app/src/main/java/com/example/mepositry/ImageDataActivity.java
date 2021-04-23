package com.example.mepositry;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;

import java.io.File;


public class ImageDataActivity extends AppCompatActivity {
    private ImageView iv_img;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_img);
        iv_img = findViewById(R.id.iv_img);
        Intent intent = getIntent();
        String data1 = intent.getStringExtra("shortImgPath");
        Log.e("TAG", "UploadVideoActivity: " + data1);
        if (data1!=null){
            iv_img.setImageURI(Uri.fromFile(new File(data1)));
        }
    }
}
