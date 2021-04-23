package com.example.mepositry;


import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.mepositry.bean.UploadVideoActivity;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    ///storage/emulated/0/ShortVideo/captured_frame.jpg 截图路径
    public static int PREVIEW_SIZE_RATIO_POS = 1;
    public static int PREVIEW_SIZE_LEVEL_POS = 3;
    public static int ENCODING_MODE_LEVEL_POS = 0;
    public static int ENCODING_SIZE_LEVEL_POS = 14;
    public static int ENCODING_BITRATE_LEVEL_POS = 6;
    public static int AUDIO_CHANNEL_NUM_POS = 0;
    public int TYPE_VIDEO = 0;  //视频模式
    public int TYPE_IMAGE = 1;  //拍照模式

    private int REQUEST_VIDEO = 99;
    private String shortVideoPath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btn_play = findViewById(R.id.btn_play);
        Button iv_public = findViewById(R.id.iv_public);

        iv_public.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳入七牛云发布短视频界面
                jumpToCaptureActivity();
            }
        });

        btn_play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(), UploadVideoActivity.class);
                Bundle bundle=new Bundle();
                bundle.putString("shortVideoPath",shortVideoPath);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        getData();
    }

    protected void getStart() {
        String str1 = "";
        File  folder1 = new File(String.valueOf(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MOVIES)));
        if (folder1.exists()) {str1 = folder1.toString() + File.separator;}
    }



    public static void createTextFile(String sBody, String FileName, String Where) {
        try {
            File gpxfile = new File(Where, FileName);
            FileWriter writer = new FileWriter(gpxfile);
            writer.append(sBody);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void getData() {
        String[] permissions = new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.CAMERA,
                Manifest.permission.RECORD_AUDIO

        };
        if (PermissionsUtils.getInstance().chekPermissions(MainActivity.this, permissions, permissionsResult)) {
            //权限通过
            Log.e("TAG", "Permission_getData");
        }
    }

    //创建监听权限的接口对象
    PermissionsUtils.IPermissionsResult permissionsResult = new PermissionsUtils.IPermissionsResult() {
        @Override
        public void passPermissons() {
            Log.e("TAG", "passPermissons");
        }

        @Override
        public void forbitPermissons() {
            Log.e("TAG", "forbitPermissons");
        }
    };

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        PermissionsUtils.getInstance().onRequestPermissionsResult(this, requestCode, permissions, grantResults);
        Log.e("TAG", "onRequestPermissionsResult" + Arrays.toString(grantResults));
    }

    public void jumpToCaptureActivity() {
        Intent intent = new Intent(MainActivity.this, VideosRecordActivity.class);
        intent.putExtra(VideosRecordActivity.PREVIEW_SIZE_RATIO, PREVIEW_SIZE_RATIO_POS);
        intent.putExtra(VideosRecordActivity.PREVIEW_SIZE_LEVEL, PREVIEW_SIZE_LEVEL_POS);
        intent.putExtra(VideosRecordActivity.ENCODING_MODE, ENCODING_MODE_LEVEL_POS);
        intent.putExtra(VideosRecordActivity.ENCODING_SIZE_LEVEL, ENCODING_SIZE_LEVEL_POS);
        intent.putExtra(VideosRecordActivity.ENCODING_BITRATE_LEVEL, ENCODING_BITRATE_LEVEL_POS);
        intent.putExtra(VideosRecordActivity.AUDIO_CHANNEL_NUM, AUDIO_CHANNEL_NUM_POS);

        startActivityForResult(intent, REQUEST_VIDEO);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null) {
            if (requestCode == REQUEST_VIDEO) {
                String path = data.getStringExtra("path");
                String imagePath = data.getStringExtra("imagePath");
                int type = data.getIntExtra("type", 0);
                Log.e("TAG", "onActivityResult: " + path + "==" + imagePath + "===" + type);
                shortVideoPath = path;
            }
        }
    }
}