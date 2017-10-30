package com.example.administrator.updatedemo.play;

import android.app.Activity;
import android.content.Context;
import android.media.AudioManager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.Toast;

import tv.danmaku.ijk.media.player.IjkMediaPlayer;

/**
 * Created by Administrator on 2017/10/28.
 */

public class SurfaceVideoView extends SurfaceView  {
    private SurfaceVideoController surfaceVideoController;
    private IjkMediaPlayer ijkMediaPlayer;
    private SurfaceHolder mSurfaceHolder;
    private int videoViewWidth=0;
    private int videoHeight=0;
    private Context context;
    private String uri;

    public SurfaceVideoView(Context context) {
        super(context);
        initSurfaceVideoView(context);
    }

    public SurfaceVideoView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initSurfaceVideoView(context);
    }

    public SurfaceVideoView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initSurfaceVideoView(context);
    }

    public SurfaceVideoView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initSurfaceVideoView(context);
    }

    public void initSurfaceVideoView(Context context){
        this.context=context;
        this.mSurfaceHolder=getHolder();
        surfaceVideoController=new SurfaceVideoController(context);
        mSurfaceHolder.addCallback(surfaceHolderCallBack);
        setFocusable(true);
        setFocusableInTouchMode(true);
        requestFocus();
        if (context instanceof Activity) {
            ((Activity) context).setVolumeControlStream(AudioManager.STREAM_MUSIC);
        }
    }

    public void openUri(String uri){
        this.uri=uri;
        openVideo();
        requestLayout();
        invalidate();
    }

    public void openVideo(){
        if(uri==null){
            Toast.makeText(context,"uri不能为空",Toast.LENGTH_SHORT).show();
            return;
        }
        clearStatus();
        try{
            ijkMediaPlayer=new IjkMediaPlayer();
            ijkMediaPlayer.setLogEnabled(true);
            ijkMediaPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_CODEC,"skip_loop_filter",48);

            ijkMediaPlayer.setDataSource(uri.toString());

            ijkMediaPlayer.setDisplay(mSurfaceHolder);
            ijkMediaPlayer.setScreenOnWhilePlaying(true);
            ijkMediaPlayer.prepareAsync();
            ijkMediaPlayer.start();
        }catch(Exception e){
            Log.e("Exception",e.getMessage());
        }
    }

    public void clearStatus(){
        if(ijkMediaPlayer!=null){
            ijkMediaPlayer.stop();
            ijkMediaPlayer.release();
            ijkMediaPlayer=null;
        }
    }

    SurfaceHolder.Callback surfaceHolderCallBack=new SurfaceHolder.Callback() {
        @Override
        public void surfaceCreated(SurfaceHolder holder) {
            mSurfaceHolder=holder;
            if(ijkMediaPlayer!=null){
                ijkMediaPlayer.setDisplay(mSurfaceHolder);
            }

        }

        @Override
        public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
            mSurfaceHolder=holder;
            videoViewWidth=width;
            videoHeight=height;

        }

        @Override
        public void surfaceDestroyed(SurfaceHolder holder) {
            mSurfaceHolder=null;
            clearStatus();
        }
    };

    public void setVideoController(SurfaceVideoController surfaceVideoController){
        this.surfaceVideoController=surfaceVideoController;
        surfaceVideoController.setAncherView(this);
        surfaceVideoController.show();
    }

    public void seekTo(long msec){
        ijkMediaPlayer.seekTo(msec);

    }

}
