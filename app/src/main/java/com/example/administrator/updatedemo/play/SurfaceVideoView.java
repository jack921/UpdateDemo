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

import com.example.administrator.updatedemo.play.Util.VideoViewUtil;

import java.io.IOException;

import tv.danmaku.ijk.media.player.IMediaPlayer;
import tv.danmaku.ijk.media.player.IjkMediaPlayer;

/**
 * Created by Administrator on 2017/10/28.
 */
public class SurfaceVideoView extends FrameLayout implements TextureView.SurfaceTextureListener,
        IMediaPlayer.OnPreparedListener, IMediaPlayer.OnVideoSizeChangedListener,
        IMediaPlayer.OnCompletionListener, IMediaPlayer.OnErrorListener,
        IMediaPlayer.OnInfoListener, IMediaPlayer.OnBufferingUpdateListener {

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

            //设置监听
            ijkMediaPlayer.setOnPreparedListener(this);
            ijkMediaPlayer.setOnVideoSizeChangedListener(this);
            ijkMediaPlayer.setOnCompletionListener(this);
            ijkMediaPlayer.setOnErrorListener(this);
            ijkMediaPlayer.setOnInfoListener(this);
            ijkMediaPlayer.setOnBufferingUpdateListener(this);

            ijkMediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            ijkMediaPlayer.setScreenOnWhilePlaying(true);
        }catch(Exception e){
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
        surfaceVideoController.setVideoView(ijkMediaPlayer);
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

    public long getDuration(){
        return ijkMediaPlayer!=null?ijkMediaPlayer.getDuration():0;
    }

    public long getCurrentPosition(){
        return ijkMediaPlayer!=null?ijkMediaPlayer.getCurrentPosition():0;
    }

    public float getSpeed(float speed){
        if(ijkMediaPlayer instanceof IjkMediaPlayer){
            return ijkMediaPlayer.getSpeed(speed);
        }
        return 0;
    }

    public long getTcpSpeed(){
        if(ijkMediaPlayer instanceof IjkMediaPlayer){
            return ijkMediaPlayer.getTcpSpeed();
        }
        return 0;
    }


    @Override
    public void onSurfaceTextureSizeChanged(SurfaceTexture surface, int width, int height) {}

    @Override
    public boolean onSurfaceTextureDestroyed(SurfaceTexture surface) {
        return mSurfaceTexture==null;
    }

    @Override
    public void onSurfaceTextureUpdated(SurfaceTexture surface) {}


    //设置监听
    @Override
    public void onPrepared(IMediaPlayer iMediaPlayer) {

    }

    @Override
    public void onVideoSizeChanged(IMediaPlayer iMediaPlayer, int i, int i1, int i2, int i3) {

    }

    @Override
    public void onCompletion(IMediaPlayer iMediaPlayer) {

    }

    @Override
    public boolean onError(IMediaPlayer iMediaPlayer, int i, int i1) {
        return false;
    }

    @Override
    public boolean onInfo(IMediaPlayer mp, int what, int extra) {
        switch(what){
            case IjkMediaPlayer.MEDIA_INFO_VIDEO_DECODED_START:
                VideoViewUtil.showToast(context,"MEDIA_INFO_VIDEO_DECODED_START");
                break;
            case IjkMediaPlayer.MEDIA_INFO_BUFFERING_START:
                VideoViewUtil.showToast(context,"MEDIA_INFO_BUFFERING_START");
                break;
            case IMediaPlayer.MEDIA_INFO_BUFFERING_END:
                VideoViewUtil.showToast(context,"MEDIA_INFO_BUFFERING_END");
                break;
            case IMediaPlayer.MEDIA_INFO_VIDEO_ROTATION_CHANGED:
                VideoViewUtil.showToast(context,"MEDIA_INFO_VIDEO_ROTATION_CHANGED");
                break;
            case IMediaPlayer.MEDIA_INFO_NOT_SEEKABLE:
                VideoViewUtil.showToast(context,"MEDIA_INFO_NOT_SEEKABLE");
                break;
        }
        return false;
    }

    @Override
    public void onBufferingUpdate(IMediaPlayer iMediaPlayer, int i) {

    }


}
