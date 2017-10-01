package fudreamer.com.livetalk;

import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import io.vov.vitamio.MediaPlayer;
import io.vov.vitamio.widget.MediaController;
import io.vov.vitamio.widget.VideoView;

public class SubscriberActivity  extends AppCompatActivity implements TextWatcher {

    private static final String TAG = SubscriberActivity.class.getName();

    private String pathToFileOrUrl= "rtmp://192.168.31.135/live/stream";
    private VideoView mVideoView;
    private boolean mIsPlaying;
    private Button mStopButton;
    private Button mPlayButton;
    private EditText mInputSteamUrl;
    private SharedPreferences mSharePref;

    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        setContentView(R.layout.activity_subscriber);
        mSharePref = this.getSharedPreferences(getPackageName(), MODE_PRIVATE);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_FULL_SENSOR);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        setTitle("Subscriber");
        /*this.getWindow().addFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN
                        | WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);*/

        mVideoView = (VideoView) findViewById(R.id.surface_view);
        mVideoView.setClickable(false);
        mPlayButton = (Button) findViewById(R.id.play);
        mStopButton = (Button) findViewById(R.id.stop);
        pathToFileOrUrl =  mSharePref.getString(PublisherActivity.RTMP_URL, PublisherActivity.DEFAULT_RTMP_URL);
        mInputSteamUrl = (EditText) findViewById(R.id.input_url);
        mInputSteamUrl.setText(pathToFileOrUrl);
        mInputSteamUrl.addTextChangedListener(this);
        mInputSteamUrl.setText(pathToFileOrUrl);
        if (pathToFileOrUrl == "") {
            Toast.makeText(this, "Please set the video path for your media file", Toast.LENGTH_LONG).show();
            return;
        } else {

            /*
             * Alternatively,for streaming media you can use
             * mVideoView.setVideoURI(Uri.parse(URLstring));
             */
            mVideoView.setVideoPath(pathToFileOrUrl);
            mVideoView.setVideoLayout(VideoView.VIDEO_LAYOUT_STRETCH, 0.8f);
            //mVideoView.setVideoLayout(VideoView.VIDEO_LAYOUT_STRETCH, 0);
            mVideoView.setMediaController(new MediaController(this));
            mVideoView.requestFocus();
            //mVideoView.setBufferSize(20);
            mVideoView.setBufferSize(1024);
            mVideoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mediaPlayer) {
                    // optional need Vitamio 4.0
                    Log.w(TAG, "prepared");
                    mediaPlayer.setPlaybackSpeed(1.0f);
                    mIsPlaying = true;


                    SubscriberActivity.this.refreshControlState();
                }
            });
        }

    }

    public void startPlay(View view) {
        if (!TextUtils.isEmpty(pathToFileOrUrl) && !mIsPlaying) {
            mVideoView.setVideoPath(pathToFileOrUrl);
            mIsPlaying = true;
            this.refreshControlState();

        } else if (mIsPlaying) {
            this.stopPlay(null);
        }
    }

    void refreshControlState() {
        mPlayButton.setEnabled(!mIsPlaying);
        mStopButton.setEnabled(mIsPlaying);
    }

    public void stopPlay(View view) {
        if (mIsPlaying) {
            mVideoView.stopPlayback();
            mIsPlaying = false;
            this.refreshControlState();

        }
    }
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        //屏幕切换时，设置全屏
        if (mVideoView != null){
            mVideoView.setVideoLayout(VideoView.VIDEO_LAYOUT_SCALE, 0);
        }
        super.onConfigurationChanged(newConfig);
    }


    public void openVideo(View View) {
        mVideoView.setVideoPath(pathToFileOrUrl);
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void afterTextChanged(Editable editable) {
        mIsPlaying = false;
        pathToFileOrUrl = mInputSteamUrl.getText().toString();
        mSharePref.edit().putString(PublisherActivity.RTMP_URL, pathToFileOrUrl).commit();
        this.refreshControlState();
    }
}
