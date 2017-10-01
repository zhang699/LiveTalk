package fudreamer.com.livetalk;

import android.Manifest;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.github.faucamp.simplertmp.RtmpHandler;

import net.ossrs.yasea.SrsCameraView;
import net.ossrs.yasea.SrsEncodeHandler;
import net.ossrs.yasea.SrsPublisher;
import net.ossrs.yasea.SrsRecordHandler;

import java.io.IOException;
import java.net.SocketException;

public class PublisherActivity extends AppCompatActivity implements RtmpHandler.RtmpListener, SrsEncodeHandler.SrsEncodeListener, SrsRecordHandler.SrsRecordListener, TextWatcher {


    private SrsCameraView mSurfaceView;
    private SrsPublisher mPublisher;
    private Button mPublishButton;
    private EditText mRTMPUrlEditText;
    private SharedPreferences mSharedPref;
    public static final String RTMP_URL = "rtmpUrl";
    public static final String DEFAULT_RTMP_URL =  "rtmp://10.0.3.2/live/stream";
    private boolean mPublishing;
    private Button mEncodeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        setContentView(R.layout.activity_publisher);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_FULL_SENSOR);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        this.setTitle("Publisher");
        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/
        mSharedPref = getSharedPreferences(getApplication().getPackageName(), MODE_PRIVATE);
        mPublishButton = (Button) findViewById(R.id.publish);
        mEncodeButton = (Button) findViewById(R.id.soft_encoder);

        mRTMPUrlEditText = (EditText) findViewById(R.id.input_url);
        mRTMPUrlEditText.setText(mSharedPref.getString(RTMP_URL, DEFAULT_RTMP_URL));
        mRTMPUrlEditText.addTextChangedListener(this);
        mSurfaceView =  (SrsCameraView) findViewById(R.id.glsurfaceview_camera);
        mPublisher = new SrsPublisher((SrsCameraView)mSurfaceView);
        mPublisher.setEncodeHandler(new SrsEncodeHandler(this));
        mPublisher.setRtmpHandler(new RtmpHandler(this));
        mPublisher.setRecordHandler(new SrsRecordHandler(this));
        mPublisher.setPreviewResolution(640, 360);
        mPublisher.setOutputResolution(360, 640);
        mPublisher.setVideoHDMode();

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            //ask for authorisation
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, 50);
        } else {
            mPublisher.startCamera();
        }


    }

    @Override
    public void onRtmpConnecting(String msg) {

    }

    @Override
    public void onRtmpConnected(String msg) {
        Toast.makeText(this, "Connect Successfully", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onRtmpVideoStreaming() {

    }

    @Override
    public void onRtmpAudioStreaming() {

    }

    @Override
    public void onRtmpStopped() {

    }

    @Override
    public void onRtmpDisconnected() {

    }

    @Override
    public void onRtmpVideoFpsChanged(double fps) {

    }

    @Override
    public void onRtmpVideoBitrateChanged(double bitrate) {

    }

    @Override
    public void onRtmpAudioBitrateChanged(double bitrate) {

    }

    @Override
    public void onRtmpSocketException(SocketException e) {

    }

    @Override
    public void onRtmpIOException(IOException e) {

    }

    @Override
    public void onRtmpIllegalArgumentException(IllegalArgumentException e) {

    }

    @Override
    public void onRtmpIllegalStateException(IllegalStateException e) {

    }

    @Override
    public void onNetworkWeak() {
        Toast.makeText(this, "Network weak", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onNetworkResume() {

    }

    @Override
    public void onEncodeIllegalArgumentException(IllegalArgumentException e) {

    }

    @Override
    public void onRecordPause() {

    }

    @Override
    public void onRecordResume() {

    }

    @Override
    public void onRecordStarted(String msg) {

    }

    @Override
    public void onRecordFinished(String msg) {

    }

    @Override
    public void onRecordIllegalArgumentException(IllegalArgumentException e) {

    }

    @Override
    public void onRecordIOException(IOException e) {

    }

    public void publish(View view) {
        if (!mPublishing) {
            String url = mRTMPUrlEditText.getText().toString();
            if (url != null) {
                mPublisher.startPublish(url);
                mPublisher.startCamera();
                mPublishing = true;
                this.refreshControlState();
            }
        } else {
            mPublisher.stopPublish();
            mPublishing = false;
            this.refreshControlState();
        }

    }

    private void refreshControlState() {
        if (mPublishing) {
            mPublishButton.setText("Stop");
        } else {

            mPublishButton.setText("Publish");
        }
        mEncodeButton.setEnabled(!mPublishing);
        mRTMPUrlEditText.setEnabled(!mPublishing);
    }

    public void encode(View view) {
        Button encodeButton = (Button) view;
        if (encodeButton.getText().toString().contentEquals("soft encoder")) {
            mPublisher.switchToSoftEncoder();
            encodeButton.setText("hard encoder");
        } else if (encodeButton.getText().toString().contentEquals("hard encoder")) {
            mPublisher.switchToHardEncoder();
            encodeButton.setText("soft encoder");
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPublisher.stopPublish();
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void afterTextChanged(Editable editable) {
        SharedPreferences.Editor editor =  mSharedPref.edit();
        editor.putString(RTMP_URL, mRTMPUrlEditText.getText().toString());
        editor.commit();
    }
}
