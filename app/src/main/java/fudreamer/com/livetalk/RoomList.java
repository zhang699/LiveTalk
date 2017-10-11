package fudreamer.com.livetalk;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.fudramer.network.NetworkRequester;
import com.fudreamer.adapter.RoomListViewAdapter;
import com.fudreamer.data.Room;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class RoomList extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
       // setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        //mRoomListView = (ListView) findViewById(R.id.listview_room);
        NetworkRequester.getInstance(this).exeuteAPIGETRequest("rooms", null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {


                JSONArray rawRoomList = null;
                List<Room>  roomList = new ArrayList<Room>();
                try {
                    rawRoomList = response.getJSONArray("data");
                    for(int i=0;i<rawRoomList.length() ; i++) {
                        JSONObject room = rawRoomList.getJSONObject(i);

                        String name = room.getString("name");
                        String streamId = room.getString("streamId");
                        roomList.add(new Room(name, streamId));

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                RoomList.this.setListAdapter(new RoomListViewAdapter(RoomList.this, roomList));
                //mRoomListView.deferNotifyDataSetChanged();

                Log.d("onResponse", ""+roomList.size());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                String body = null;
                try {
                    body = new String(error.networkResponse.data,"UTF-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                Log.d("onResponse", body);
            }
        });
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        Object item = l.getAdapter().getItem(position);
        Room room = (Room) item;
        Intent intent = new Intent(this, SubscriberActivity.class);
        intent.putExtra("roomName", room.getName());
        intent.putExtra("streamId", room.getStreamId());
        startActivity(intent);
    }
}
