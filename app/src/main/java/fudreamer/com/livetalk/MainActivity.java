package fudreamer.com.livetalk;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void goPublisher(View view) {
        startActivity(new Intent(this, PublisherActivity.class));
    }

    public void goSubscriber(View view) {
        startActivity(new Intent(this, SubscriberActivity.class));
    }
}
