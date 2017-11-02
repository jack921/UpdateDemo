package com.example.administrator.updatedemo.play.Util;

import android.content.Context;
import android.media.AudioManager;
import android.support.annotation.NonNull;
import android.widget.FrameLayout;
import java.util.Timer;
import java.util.TimerTask;
import tv.danmaku.ijk.media.player.IjkMediaPlayer;

/**
 * Created by Administrator on 2017/11/2.
 */
public abstract class AbstractVideoViewController extends FrameLayout{
    /**
     * 播放错误
     **/
    public static final int STATE_ERROR = -1;
    /**
     * 播放未开始
     **/
    public static final int STATE_IDLE = 0;
    /**
     * 播放准备中
     **/
    public static final int STATE_PREPARING = 1;
    /**
     * 播放准备就绪
     **/
    public static final int STATE_PREPARED = 2;
    /**
     * 正在播放
     **/
    public static final int STATE_PLAYING = 3;
    /**
     * 暂停播放
     **/
    public static final int STATE_PAUSED = 4;
    /**
     * 正在缓冲(播放器正在播放时，缓冲区数据不足，进行缓冲，缓冲区数据足够后恢复播放)
     **/
    public static final int STATE_BUFFERING_PLAYING = 5;
    /**
     * 正在缓冲(播放器正在播放时，缓冲区数据不足，进行缓冲，此时暂停播放器，继续缓冲，缓冲区数据足够后恢复暂停
     **/
    public static final int STATE_BUFFERING_PAUSED = 6;
    /**
     * 播放完成
     **/
    public static final int STATE_COMPLETED = 7;

    /**
     * 普通模式
     **/
    public static final int MODE_NORMAL = 10;
    /**
     * 全屏模式
     **/
    public static final int MODE_FULL_SCREEN = 11;

    public IjkMediaPlayer ijkMediaPlayer;
    public AudioManager mAudioManager;
    public Timer updateSeekbatTimer;
    public TimerTask updateSeekbatTimerTask;

    //计算播放进度条
    public abstract void updateTimeSeek();

    public AbstractVideoViewController(@NonNull Context context) {
        super(context);
        mAudioManager=(AudioManager)context.getSystemService(Context.AUDIO_SERVICE);
    }

    public void startUpdateTimeSeekBar(){
        cancelUpdateTimeSeekBar();
        if(updateSeekbatTimer==null){
            updateSeekbatTimer=new Timer();
        }
        if(updateSeekbatTimerTask==null){
            updateSeekbatTimerTask=new TimerTask() {
                @Override
                public void run() {
                    updateTimeSeek();
                }
            };
        }
        updateSeekbatTimer.schedule(updateSeekbatTimerTask,0,1000);
    }

    public void cancelUpdateTimeSeekBar(){
        if(updateSeekbatTimer!=null){
            updateSeekbatTimer.cancel();
            updateSeekbatTimer=null;
        }
        if(updateSeekbatTimerTask!=null){
            updateSeekbatTimerTask.cancel();
            updateSeekbatTimerTask=null;
        }
    }




}
