package com.example.mepositry;

        import android.app.Application;
        import android.content.Context;

        import com.qiniu.pili.droid.shortvideo.PLShortVideoEnv;

public class ShortVideoApplication extends Application {
    private Context context;

    private static ShortVideoApplication INSTANCE;

    public static ShortVideoApplication getInstance(){
        return INSTANCE;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        // init resources needed by short video sdk
        INSTANCE = this;
        context = getApplicationContext();
        PLShortVideoEnv.init(getApplicationContext());
    }
    public Context getContext(){
        return context;
    }
}
