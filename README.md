

### Installation
- ```git clone``` the code

- Import Module ```jjdxm_ijkplayer``` from Android Studio with [jjdxm_ijkplayer repo](https://github.com/jjdxmashl/jjdxm_ijkplayer) and name as ```jjdxm_ijkplayer```

- Import Module from [ossrs](https://github.com/begeekmyfriend/yasea) and name as ```ors.yasea```

### Screenshots
<div>
<img width=256 src="https://dl2.pushbulletusercontent.com/WolTqTEF6nak2SmTrAI35xOcifZotdnm/Screenshot_2017-10-01-15-43-10-419_fudreamer.com.livetalk.png" />
<img width=256 src="https://i.imgur.com/qUldsDx.jpg" />
</div>

### How to use

- Choose your role (Publisher or Subscriber)

- Input your RTMP Server's location

```rtmp://10.0.3.2/live/stream //if inside genymotion```

- Click ```Publish``` Button if you are publisher

- Subscriber can See the RTMP stream content came from Publisher


### Use Opensources 

[jjdxm_ijkplayer](https://github.com/jjdxmashl/jjdxm_ijkplayer)

[ossrs](https://github.com/begeekmyfriend/yasea)


Test
-----
Subscriber: GenyMotion Google Nexus 5X API Level 23 Android 6.0.1

Publisher: Xiaomi Note 2 Android 6.0.0

Others
---
```java
ijkMediaPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_FORMAT, "probsize", 4096);
ijkMediaPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_FORMAT, "fflags", "nobuffer"); 
// disable the buffer can shorten the RTMP latency
```

Know Issues
---
https://github.com/yixia/VitamioBundle/issues/321
> ...maybe replace vitamio

TODO
---
- [ ] Replace Vitamio, because of continus playing make cause accumulated buffer and lead to latency issue  
- [ ] Add Chat came from subscriber
- [ ] Check Streaming Audio is fine

Useful information
---
[RTMP latency](https://github.com/Bilibili/ijkplayer/issues/2485)


