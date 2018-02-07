### Installation

* `git clone` the code

* Import Module `jjdxm_ijkplayer` from Android Studio with [jjdxm_ijkplayer repo](https://github.com/jjdxmashl/jjdxm_ijkplayer) and name as `jjdxm_ijkplayer`

* Comment root.project.\* in jjdxm_ijkplayer/build.gradle, use your app configuration instead.

```
  defaultConfig {
        minSdkVersion 9
        targetSdkVersion 23
        /*minSdkVersion rootProject.ext.jjdxm_minSdkVersion
        targetSdkVersion rootProject.ext.targetSdkVersion
        versionCode rootProject.ext.jjdxm_versionCode
        versionName rootProject.ext.jjdxm_versionName*/
    }
```

* Import Module from [ossrs](https://github.com/begeekmyfriend/yasea) and name as `ors.yasea`

### How to use

* Setting up your local RTMP and [LiveTalkServer](https://github.com/zhang699/LiveTalkServer) location in [app/build.gradle](```https://github.com/zhang699/LiveTalk/blob/master/app/build.gradle```)

```
debug {
            buildConfigField "String", "API_HOST", "\"http://10.0.3.2:10010\""
            buildConfigField "String", "RTMP_HOST", "\"rtmp://10.0.3.2/live/%s\""
            buildConfigField "String", "API_TOKEN", "\"Ss-^3EnbsM`Mbp(#ou2})&wXYn|Pu\""
        }
```

* Create Room by clicking `+` floating button or clicking list item for join the room
* Enjoy !!!

### Screenshots

<div>

<img width=256 src="https://dl2.pushbulletusercontent.com/o40GRh2wQ3mnDutyOWXX0glcEvdja02n/Screenshot_2017-10-15-14-32-32-527_fudreamer.com.livetalk.png" />

<img width=256 src="https://dl2.pushbulletusercontent.com/vaWsdwL2BAFqRIn8DrflPlMs1POndk7f/Screenshot_2017-10-15-14-24-37-071_fudreamer.com.livetalk.png" />

<img width=256 src="https://i.imgur.com/CBk8gyW.png" />

<img width=256 src="https://i.imgur.com/vbOITL1.jpg" />
</div>

### Use Opensources

[jjdxm_ijkplayer](https://github.com/jjdxmashl/jjdxm_ijkplayer)

[ossrs](https://github.com/begeekmyfriend/yasea)

## Test

Subscriber

* GenyMotion Google Nexus 5X API Level 23 Android 6.0.1
* Redmi Note 3

Publisher

* Xiaomi Note 2 Android 6.0.0
* Redmi Note 3

## Others

```java
ijkMediaPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_FORMAT, "probsize", 4096);
ijkMediaPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_FORMAT, "fflags", "nobuffer");
// disable the buffer can shorten the RTMP latency
```

## Know Issues

https://github.com/yixia/VitamioBundle/issues/321

> ...maybe replace vitamio

## TODO

* [ ] Replace Vitamio, because of continus playing make cause accumulated buffer and lead to latency issue
* [ ] Add Chat came from subscriber
* [ ] Check Streaming Audio is fine

## Useful information

[RTMP latency](https://github.com/Bilibili/ijkplayer/issues/2485)
