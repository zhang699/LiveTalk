package com.fudreamer.data;

/**
 * Created by zhangxiongzhan on 2017/10/11.
 */

public class Room {

    private String mName;
    private String description;
    private String mStreamId;

    public Room(String name, String steamId) {
        mName = name;
        mStreamId = steamId;
    }


    public String getName(){
        return mName;
    }

    public String getStreamId(){
        return mStreamId;
    }
}
