package com.example.administrator.updatedemo.play;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.SurfaceTexture;
import android.media.AudioManager;
import android.net.Uri;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.Surface;
import android.view.TextureView;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.Toast;
import java.io.IOException;
import tv.danmaku.ijk.media.player.IjkMediaPlayer;

/**
 * Created by Administrator on 2017/10/28.
 */

public class SurfaceVideoView extends FrameLayout implements TextureView.SurfaceTextureListener {
    private SurfaceVideoController surfaceVideoController;
    private IjkMediaPlayer ijkMediaPlayer;
    private TextureView textureView;
    private FrameLayout mContainer;
    private SurfaceTexture mSurfaceTexture;
    private Surface surface;
    private Context context;
    private String uri;

    private int videoViewWidth=0;
    private int videoHeight=0;

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
        initView();
        initAudioManager();
        initMediaPlayer();
    }

    public void initView(){
        mContainer=new FrameLayout(context);
        mContainer.setBackgroundColor(Color.BLACK);
        LayoutParams params=new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT);
        addView(mContainer,params);
    }

    public void initAudioManager(){
        if (context instanceof Activity) {
            ((Activity) context).setVolumeControlStream(AudioManager.STREAM_MUSIC);
        }
    }

    public void initMediaPlayer(){
        try{
            ijkMediaPlayer=new IjkMediaPlayer();
            ijkMediaPlayer.setLogEnabled(true);
            ijkMediaPlayer.setOption(1, "analyzemaxduration", 100L);
            ijkMediaPlayer.setOption(1, "probesize", 10240L);
            ijkMediaPlayer.setOption(1, "flush_packets", 1L);
            ijkMediaPlayer.setOption(4, "packet-buffering", 0L);
            ijkMediaPlayer.setOption(4, "framedrop", 1L);

            ijkMediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            ijkMediaPlayer.setScreenOnWhilePlaying(true);
        }catch(Exception e){
            Log.e("Exception",e.getMessage());
        }
    }

    public void openUri(String uri){
        if(uri==null){
            Toast.makeText(context,"uri不能为空",Toast.LENGTH_SHORT).show();
            return;
        }
        this.uri=uri;
        initTexttureView();
        addTextureView();
    }

    public void initTexttureView(){
        if(textureView==null){
            textureView=new TextureView(context);
            textureView.setSurfaceTextureListener(this);
        }
    }

    public void addTextureView(){
        mContainer.removeView(textureView);
        LayoutParams params=new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT, Gravity.CENTER);
        mContainer.addView(textureView,0,params);
    }

    public void openMediaPlayer(){
        try {
            if(surface==null){
                surface=new Surface(mSurfaceTexture);
            }
            ijkMediaPlayer.setSurface(surface);
            ijkMediaPlayer.setDataSource(context, Uri.parse(uri));
            ijkMediaPlayer.prepareAsync();
            ijkMediaPlayer.start();
        } catch (IOException e) {
            Log.e("openMediaPlayer",e.getMessage());
        }
    }

    public void clearStatus(){
        if(ijkMediaPlayer!=null){
            ijkMediaPlayer.stop();
            ijkMediaPlayer.release();
            ijkMediaPlayer=null;
        }
    }

    public void setVideoController(SurfaceVideoController surfaceVideoController){
        this.surfaceVideoController=surfaceVideoController;
        surfaceVideoController.setAncherView(this);

        LayoutParams params = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        addView(surfaceVideoController,params);
    }

    public void seekTo(long msec){
        ijkMediaPlayer.seekTo(msec);
    }

    @Override
    public void onSurfaceTextureAvailable(SurfaceTexture surface, int width, int height) {
        if (mSurfaceTexture == null) {
            mSurfaceTexture = surface;
            openMediaPlayer();
        } else {
            textureView.setSurfaceTexture(mSurfaceTexture);
        }
    }

    @Override
    public void onSurfaceTextureSizeChanged(SurfaceTexture surface, int width, int height) {}

    @Override
    public boolean onSurfaceTextureDestroyed(SurfaceTexture surface) {
        return mSurfaceTexture==null;
    }

    @Override
    public void onSurfaceTextureUpdated(SurfaceTexture surface) {}


}
