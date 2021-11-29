package com.example.dragonballclicker;

import android.content.Context;
import android.media.AudioAttributes;
import android.media.MediaPlayer;
import android.media.SoundPool;

import java.util.HashMap;
import java.util.LinkedHashMap;

public class SoundManager {


    private Context context;
    private MediaPlayer mediaPlayer;
    private SoundPool soundPool;
    private HashMap<String, Integer> mapDeSon;
    private boolean poolIsLoaded;
    private static final float VOLUME = 0.9f;



    public SoundManager()
    {/*
        context = getAppCompatActivity().getApplicationContext();
        mediaPlayer = MediaPlayer.create(context, R.raw.cowmusic);
        mediaPlayer.setVolume(0.2f, 0.2f);
        mapDeSon = new LinkedHashMap();


        AudioAttributes audioAttributes = new AudioAttributes.Builder()
                .setUsage(AudioAttributes.USAGE_GAME)
                .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                .build();
        SoundPool.Builder builder = new SoundPool.Builder();
        builder.setAudioAttributes(audioAttributes).setMaxStreams(5);

        poolIsLoaded = false;
        soundPool = builder.build();

        this.soundPool.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() {
            @Override
            public void onLoadComplete(SoundPool soundPool, int sampleId, int status) {
                poolIsLoaded = true;
            }
        });

        mapDeSon.put("death", this.soundPool.load(context, R.raw.sfx_deathscream_alien2, 1));
        mapDeSon.put("jump", this.soundPool.load(context, R.raw.sfx_movement_jump1, 1));
        mapDeSon.put("menu", this.soundPool.load(context, R.raw.sfx_menu_move1, 1));
        mapDeSon.put("coin", this.soundPool.load(context, R.raw.sfx_coin_double1, 1));
*/
    }


    public void stop(){
        mediaPlayer.stop();
    }

    public  void start(){
        mediaPlayer.start();
    }

    public void deathSound(){
        if(poolIsLoaded)
            soundPool.play(mapDeSon.get("death"), VOLUME, VOLUME, 1, 0, 1f);
    }

    public void onScoreAdded(){
        if(poolIsLoaded)
            soundPool.play(mapDeSon.get("coin"), VOLUME, VOLUME, 1, 0, 1f);

    }

    public void playMenuSound(){
        if(poolIsLoaded)
            soundPool.play(mapDeSon.get("menu"), VOLUME, VOLUME, 1, 0, 1f);

    }

    public void onPlayerMoveUpPlaySound(){
        if(poolIsLoaded)
            soundPool.play(mapDeSon.get("jump"), VOLUME, VOLUME, 1, 0, 1f);

    }


}