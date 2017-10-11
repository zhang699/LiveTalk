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

import com.dou361.ijkplayer.widget.IjkVideoView;

import tv.danmaku.ijk.media.player.IMediaPlayer;
import tv.danmaku.ijk.media.player.IjkMediaPlayer;


public class SubscriberActivity  extends AppCompatActivity implements TextWatcher {

    private static final String TAG = SubscriberActivity.class.getName();

    private String pathToFileOrUrl= "rtmp://192.168.31.135/live/stream";
    private IjkVideoView mVideoView;
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
        String roomName = getIntent().getStringExtra("roomName");
        String streamId = getIntent().getStringExtra("streamId");
        setTitle(roomName);

        try {
            IjkMediaPlayer.loadLibrariesOnce(null);
            IjkMediaPlayer.native_profileBegin("libijkplayer.so");

        } catch (Throwable e) {
            Log.e(TAG, "loadLibraries error", e);
        }


        mVideoView =  (IjkVideoView) findViewById(R.id.video_view);

        mVideoView.setClickable(false);
        mPlayButton = (Button) findViewById(R.id.play);
        mStopButton = (Button) findViewById(R.id.stop);
        //pathToFileOrUrl =  mSharePref.getString(PublisherActivity.RTMP_URL, PublisherActivity.DEFAULT_RTMP_URL);
        pathToFileOrUrl =  String.format(BuildConfig.RTMP_HOST, streamId);
        mInputSteamUrl = (EditText) findViewById(R.id.input_url);
        mInputSteamUrl.setText(pathToFileOrUrl);
        mInputSteamUrl.addTextChangedListener(this);
        mInputSteamUrl.setText(pathToFileOrUrl);
        if (pathToFileOrUrl == "") {
            Toast.makeText(this, "Please set the video path for your media file", Toast.LENGTH_LONG).show();
            return;
        } else {


            Log.d(TAG, "pathToFileOrUrl"+ pathToFileOrUrl);
            //mVideoView.setVideoPath("http://www.streambox.fr/playlists/test_001/stream.m3u8");
            mVideoView.setVideoPath(pathToFileOrUrl);
            mVideoView.seekTo(0);

            mVideoView.setOnInfoListener(new IMediaPlayer.OnInfoListener() {
                @Override
                public boolean onInfo(IMediaPlayer iMediaPlayer, int i, int i1) {
                    Log.d(TAG, "info "+ i +" "+i1);
                    return false;
                }
            });
            mVideoView.setOnErrorListener(new IMediaPlayer.OnErrorListener() {
                @Override
                public boolean onError(IMediaPlayer iMediaPlayer, int i, int i1) {
                    Log.d(TAG, "error "+ i +" "+i1);
                    return false;
                }
            });
            mVideoView.setOnPreparedListener(new IMediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(IMediaPlayer iMediaPlayer) {

                    //mVideoView.setOptionForIJKMediaPlayer(IjkMediaPlayer.OPT_CATEGORY_FORMAT, "probsize", 4096);
                    mVideoView.start();
                    mIsPlaying = true;
                    SubscriberActivity.this.refreshControlState();
                }
            });


        }

    }

    public void startPlay(View view) {
        if (!TextUtils.isEmpty(pathToFileOrUrl) && !mIsPlaying) {
            mVideoView.setVideoPath(pathToFileOrUrl);
            mVideoView.seekTo(0);
            mVideoView.start();
            mIsPlaying = true;
            this.refreshControlState();

        } else if (mIsPlaying) {
            this.stopPlay(null);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();

        if (mVideoView != null) {
            mVideoView.stopPlayback();
            mVideoView.release(true);
        }
        IjkMediaPlayer.native_profileEnd();

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
            //mVideoView.setVideoLayout(VideoView.VIDEO_LAYOUT_SCALE, 0);
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
