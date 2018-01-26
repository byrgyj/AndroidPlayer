package com.qiyi.videodemo;

import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.VideoView;

//import static com.qiyi.videodemo.R.id.videoView;

public class MainActivity extends AppCompatActivity {
    String tag = "test";
    SurfaceView surface;
    SurfaceHolder surfaceHolder;

    private MediaPlayer mp = new MediaPlayer();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i(tag, "onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        surface = (SurfaceView)this.findViewById(R.id.surfaceView);
        surfaceHolder = surface.getHolder();

        try {
            // "http://10.5.192.201/cmaf/720p_combine.mp4"
            // "http://10.10.13.181:8080/video/chinadrm/ts/1.ts"
            //  "http://10.5.192.201/cmaf/720p_combine_long.mp4"
            // "http://10.5.192.201/cmaf/720p_combine.mp4"
            mp.setDataSource("");
            surfaceHolder.addCallback(new MyCallBack());
            surfaceHolder.setKeepScreenOn(true);
//            mp.setDisplay(surfaceHolder);
            mp.setScreenOnWhilePlaying(true);
            mp.prepareAsync();
            mp.setOnPreparedListener(mOnPreparedListener);

        } catch (Exception ex){
            Log.i(tag,"exception...");
            ex.printStackTrace();
        }
    }

    private MediaPlayer.OnPreparedListener mOnPreparedListener = new MediaPlayer.OnPreparedListener() {
        @Override
        public void onPrepared(MediaPlayer mp) {
            if (surfaceHolder != null) {
                mp.setDisplay(surfaceHolder);
            }
            mp.start();
        }
    };


    private class MyCallBack implements SurfaceHolder.Callback {
        @Override
        public void surfaceCreated(SurfaceHolder holder) {
            surfaceHolder = holder;
            mp.setDisplay(holder);
        }

        @Override
        public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

        }

        @Override
        public void surfaceDestroyed(SurfaceHolder holder) {

        }
    }
}
