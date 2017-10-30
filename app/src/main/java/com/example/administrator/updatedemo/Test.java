package com.example.administrator.updatedemo;

import android.content.Context;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by Administrator on 2017/10/28.
 */

public class Test extends SurfaceView implements SurfaceHolder.Callback {
    private SurfaceHolder mSurfaceHolder;

    public Test(Context context) {
        super(context);
        mSurfaceHolder=getHolder();
        mSurfaceHolder.addCallback(this);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {

    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }

}
