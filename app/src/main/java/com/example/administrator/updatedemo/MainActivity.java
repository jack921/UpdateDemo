package com.example.administrator.updatedemo;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import com.example.administrator.updatedemo.play.SurfaceVideoController;
import com.example.administrator.updatedemo.play.SurfaceVideoView;
import com.example.administrator.updatedemo.weight.media.AndroidMediaController;
import com.example.administrator.updatedemo.weight.media.IjkVideoView;
import java.io.IOException;
import tv.danmaku.ijk.media.player.IMediaPlayer;
import tv.danmaku.ijk.media.player.IjkMediaPlayer;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initSurfaceVideoView();
    }

    public void initSurfaceVideoView(){
        SurfaceVideoView surfaceVideoView=findViewById(R.id.surface_video_view);
//        SurfaceVideoController surfaceVideoController=new SurfaceVideoController(this);
//        surfaceVideoView.setVideoController(surfaceVideoController);
        surfaceVideoView.openUri("http://vod.cntv.lxdns.com/flash/mp4video61/TMS/2017/08/17/63bf8bcc706a46b58ee5c821edaee661_h264818000nero_aac32-5.mp4");
    }

//    public void initMediaView(){
//        try {
//            SurfaceView surfaceView=findViewById(R.id.video_view);
//            final IjkMediaPlayer ijkMediaPlayer= new IjkMediaPlayer();
//            SurfaceHolder holder=surfaceView.getHolder();
//            ijkMediaPlayer.setDataSource(this,
//                    Uri.parse("http://vod.cntv.lxdns.com/flash/mp4video61/TMS/2017/08/17/63bf8bcc706a46b58ee5c821edaee661_h264818000nero_aac32-5.mp4"));
//            ijkMediaPlayer.setDisplay(holder);
//
//            holder.addCallback(new SurfaceHolder.Callback() {
//                @Override
//                public void surfaceCreated(SurfaceHolder holder) {}
//                @Override
//                public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
//                    ijkMediaPlayer.setDisplay(holder);
//                }
//                @Override
//                public void surfaceDestroyed(SurfaceHolder holder) {}
//            });
//            ijkMediaPlayer.prepareAsync();
//            ijkMediaPlayer.start();
//            ijkMediaPlayer.setKeepInBackground(false);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

//    public void initVideoView(){
//        SurfaceView ijkVideoView=findViewById(R.id.video_view);
//        IjkMediaPlayer.loadLibrariesOnce(null);
//        IjkMediaPlayer.native_profileBegin("libijkplayer.so");
//        ijkVideoView.setVideoURI(Uri.parse("http://vod.cntv.lxdns.com/flash/mp4video61/TMS/2017/08/17/63bf8bcc706a46b58ee5c821edaee661_h264818000nero_aac32-5.mp4"));
//        ijkVideoView.setOnPreparedListener(new IMediaPlayer.OnPreparedListener() {
//            @Override
//            public void onPrepared(IMediaPlayer mp) {
//                ijkVideoView.start();
//            }
//        });
//    }

}
