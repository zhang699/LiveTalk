

### Installation
- Clone the code
- Import Module from Android Studio with [vitamio](https://github.com/yixia/VitamioBundle/vitamio) and name as ```vitamio```

- Import Module from [ossrs](https://github.com/begeekmyfriend/yasea) and name as ```ors.yasea```

### How to use

- Choose your role (Publisher or Subscriber)

- Input your RTMP Server's location

```rtmp://10.0.3.2/live/stream //if inside genymotion```

- Click ```Publish``` Button if you are publisher

- Subscriber can See the RTMP stream content came from Publisher


### Use Opensources 

[vitamio](https://github.com/yixia/VitamioBundle)

[ossrs](https://github.com/begeekmyfriend/yasea)


Test
-----
Subscriber: GenyMotion Google Nexus 5X API Level 23 Android 6.0.1

Publisher: Xiaomi Note 2 Android 6.0.0

Others
---
```java
mVideoView.setBufferSize(1024); // makes reduce buffer can shorten the RTMP latency
```

Know Issues
---
https://github.com/yixia/VitamioBundle/issues/321
> ...maybe replace vitamio

TODO
---
- [ ] Replace Vitamio, because of latency issue  