package com.example.administrator.updatedemo.play;

import android.content.Context;
import android.graphics.Rect;
import android.media.AudioManager;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.PopupWindow;

import com.example.administrator.updatedemo.R;

/**
 * Created by Administrator on 2017/10/30.
 */

public class SurfaceVideoController extends FrameLayout{

    private AudioManager mAudioManager;
    private PopupWindow mWindow;
    private View mAnchor,mRoot;
    private int mAnimStyle;
    private Context context;

    public SurfaceVideoController(@NonNull Context context) {
        super(context);
        initController(context);
    }

    public SurfaceVideoController(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initController(context);
    }

    public SurfaceVideoController(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initController(context);
    }

    public SurfaceVideoController(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initController(context);
    }

    public void initController(Context context){
        this.context=context;
        mAudioManager=(AudioManager)context.getSystemService(Context.AUDIO_SERVICE);
        mWindow=new PopupWindow(context);
        mWindow.setFocusable(true);
        mWindow.setBackgroundDrawable(null);
        mWindow.setOutsideTouchable(true);
        mAnimStyle=android.R.style.Animation;
    }

    /**
     * 设置控制器
     * @param view
     */
    public void setAncherView(View view){
        this.mAnchor=view;
        removeAllViews();
        mRoot=((LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.layout_controller, this);
        mWindow.setContentView(mRoot);
        mWindow.setWidth(LayoutParams.MATCH_PARENT);
        mWindow.setHeight(LayoutParams.WRAP_CONTENT);

    }

    public void show(){
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                int[] location = new int[2];
                mAnchor.getLocationOnScreen(location);
                Rect anchorRect = new Rect(location[0], location[1], location[0] + mAnchor.getWidth(), location[1] + mAnchor.getHeight());
                mWindow.setAnimationStyle(mAnimStyle);
                mWindow.showAtLocation(mAnchor, Gravity.TOP, anchorRect.left, 0);
            }
        },200);
    }

}
