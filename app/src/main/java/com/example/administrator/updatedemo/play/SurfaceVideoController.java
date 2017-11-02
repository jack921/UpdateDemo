package com.example.administrator.updatedemo.play;

import android.content.Context;
import android.graphics.Rect;
import android.media.AudioManager;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.example.administrator.updatedemo.R;
import com.example.administrator.updatedemo.play.Util.AbstractVideoViewController;
import com.example.administrator.updatedemo.play.Util.VideoViewUtil;

import tv.danmaku.ijk.media.player.IjkMediaPlayer;

/**
 * Created by Administrator on 2017/10/30.
 */

public class SurfaceVideoController extends AbstractVideoViewController implements View.OnClickListener, SeekBar.OnSeekBarChangeListener {
    private View mRoot;
    private Context context;

    private TextView title;
    private ImageView back;
    private SeekBar seekBar;
    private ImageView open;
    private ImageView screen;
    private TextView mTimeAll;
    private TextView mTimeImg;

    public SurfaceVideoController(@NonNull Context context) {
        super(context);
        initController(context);
    }

    public void initController(final Context context){
        this.context=context;

        mRoot=LayoutInflater.from(context).inflate(R.layout.layout_controller,this,false);
        addView(mRoot);

        title=(TextView)findViewById(R.id.av_title);
        back=(ImageView)findViewById(R.id.ic_back);
        open=(ImageView)findViewById(R.id.play_btn);
        seekBar=(SeekBar)findViewById(R.id.av_seek_bar);
        screen=(ImageView)findViewById(R.id.screen_btn);
        mTimeAll=(TextView)findViewById(R.id.time_all);
        mTimeImg=(TextView)findViewById(R.id.time_img);

        back.setOnClickListener(this);
        open.setOnClickListener(this);
        seekBar.setOnSeekBarChangeListener(this);
        screen.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.ic_back:
                break;
            case R.id.play_btn:
                break;
            case R.id.screen_btn:
                Toast.makeText(context,"screen_btn",Toast.LENGTH_SHORT).show();
                break;
        }
    }

    /**
     * 设置控制器
     * @param view
     */
    public void setVideoView(IjkMediaPlayer view){
        this.ijkMediaPlayer=view;

    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void updateTimeSeek() {
        long position=ijkMediaPlayer.getCurrentPosition();
        long duration=ijkMediaPlayer.getDuration();
        long bufferPercentage=getCurrentPosition();
        seekBar.setSecondaryProgress((int)bufferPercentage);
        int progress=(int) (100f*position/duration);
        seekBar.setProgress(progress);

        mTimeImg.setText(VideoViewUtil.formatTime(position));
        mTimeAll.setText(VideoViewUtil.formatTime(duration));
    }

    public long getCurrentPosition() {
        return ijkMediaPlayer != null ? ijkMediaPlayer.getCurrentPosition() : 0;
    }


}
