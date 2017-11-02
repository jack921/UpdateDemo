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
                    AbstractVideoViewController.this.post(new Runnable() {
                        @Override
                        public void run() {
                            updateTimeSeek();
                        }
                    });
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
