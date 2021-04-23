package com.example.mepositry.bean;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;


import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.mepositry.R;
import com.qiniu.pili.droid.shortvideo.PLBuiltinFilter;

import java.io.IOException;
import java.util.List;

/**
 * Created by admin on 2018/6/4.
 */

public class FileterAdapter extends BaseQuickAdapter<PLBuiltinFilter, BaseViewHolder> {
    public FileterAdapter(int layoutResId, @Nullable List<PLBuiltinFilter> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, PLBuiltinFilter item) {
        ImageView filterImg = helper.getView(R.id.filter_img);
        helper.setText(R.id.filter_name, item.getName());
        try {
            Bitmap bitmap = BitmapFactory.decodeStream(mContext.getAssets().open(item.getAssetFilePath()));
            filterImg.setImageBitmap(bitmap);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
