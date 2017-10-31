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
        SurfaceVideoController surfaceVideoController=new SurfaceVideoController(this);
        surfaceVideoView.setVideoController(surfaceVideoController);
//        surfaceVideoView.openUri("http://vod.cntv.lxdns.com/flash/mp4video61/TMS/2017/08/17/63bf8bcc706a46b58ee5c821edaee661_h264818000nero_aac32-5.mp4");
        surfaceVideoView.openUri("http://play.g3proxy.lecloud.com/vod/v2/MjQ5LzM3LzIwL2xldHYtdXRzLzE0L3Zlcl8wMF8yMi0xMTA3NjQxMzkwLWF2Yy00MTk4MTAtYWFjLTQ4MDAwLTUyNjExMC0zMTU1NTY1Mi00ZmJjYzFkNzA1NWMyNDc4MDc5OTYxODg1N2RjNzEwMi0xNDk4NTU3OTYxNzQ4Lm1wNA==?b=479&mmsid=65565355&tm=1499247143&key=98c7e781f1145aba07cb0d6ec06f6c12&platid=3&splatid=345&playid=0&tss=no&vtype=13&cvid=2026135183914&payff=0&pip=08cc52f8b09acd3eff8bf31688ddeced&format=0&sign=mb&dname=mobile&expect=1&tag=mobile&xformat=super");
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
