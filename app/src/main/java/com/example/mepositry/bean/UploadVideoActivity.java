package com.example.mepositry.bean;


import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.example.mepositry.R;

import java.io.File;


public class UploadVideoActivity extends AppCompatActivity {
    private TextView textView;
    private VideoView videoview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_uploadvideo);
        textView = findViewById(R.id.textView);
        videoview = findViewById(R.id.videoinfo);
        Intent intent = getIntent();
        String data1 = intent.getStringExtra("shortVideoPath");
        Log.e("TAG", "UploadVideoActivity: " + data1);
        textView.setText(data1);
        if (data1 != null) {
            videoview.setMediaController(new MediaController(this));
            //播放完成回调
            videoview.setOnCompletionListener(new MyPlayerOnCompletionListener());
            //设置视频路径
            videoview.setVideoURI(Uri.parse(data1));
           // videoview.setVideoPath(data1);
            //开始播放视频
            videoview.start();
        }
    }

    class MyPlayerOnCompletionListener implements MediaPlayer.OnCompletionListener {
        @Override
        public void onCompletion(MediaPlayer mp) {
            Log.e("TAG", "UploadVideoActivity_videoview: 播放完成了");
        }
    }


}
