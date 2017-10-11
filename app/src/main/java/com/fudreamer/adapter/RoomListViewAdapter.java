package com.fudreamer.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.fudreamer.data.Room;

import java.util.List;

import fudreamer.com.livetalk.R;

/**
 * Created by zhangxiongzhan on 2017/10/11.
 */

public class RoomListViewAdapter extends BaseAdapter {

    private final Context mContext;
    private final List<Room> mRoomList;

    public RoomListViewAdapter(Context context, List<Room> roomList) {
        mContext = context;
        mRoomList = roomList;
    }

    @Override
    public int getCount() {
        return mRoomList.size();
    }

    @Override
    public Object getItem(int i) {
        return mRoomList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        if (view == null) {
            view = inflater.inflate(R.layout.row_room_list, null);
        }
        TextView name = (TextView) view.findViewById(R.id.name);
        TextView streamId = (TextView) view.findViewById(R.id.stream_id);
        Room room = mRoomList.get(i);
        name.setText(room.getName());
        streamId.setText(room.getStreamId());
        return view;
    }
}
