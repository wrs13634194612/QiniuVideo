package com.example.mepositry;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

public class IntentUtils {
    public static void startActivity(Context context, Class<?> cls, Bundle bundle, boolean isAnim, boolean isFinish) {
        Intent intent = new Intent(context, cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        context.startActivity(intent);

//        if (isAnim){
//            ((Activity) context).overridePendingTransition(R.anim.slide_in_left, R.anim.slide_in_left);
//        }
        if (isFinish) {
            ((Activity) context).finish();
        }
    }

    public static void startActivity(Context context, Class<?> cls, Bundle bundle) {
        startActivity(context, cls, bundle, true, false);
    }

    public static void startActivityForResult(Context context, Class<?> cls, Bundle bundle, int code) {
        Intent intent = new Intent(context, cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        ((Activity) context).startActivityForResult(intent, code);
    }

}
