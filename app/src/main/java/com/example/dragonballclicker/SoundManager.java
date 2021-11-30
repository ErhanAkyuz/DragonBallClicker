package com.example.dragonballclicker;

import android.content.Context;
import android.media.AudioAttributes;
import android.media.MediaPlayer;
import android.media.SoundPool;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Random;

public class SoundManager {


    private MediaPlayer mediaPlayer;
    private SoundPool soundPool;
    private HashMap<String, Integer> mapDeSon;
    private boolean poolIsLoaded;
    private static final float VOLUME = 0.05f;
    private static final float VOLUMEUPGRADE = 0.7f;



    public SoundManager(Context context)
    {
        Random rand = new Random();
        int nombreAleatoire = rand.nextInt(3 - 1 + 1) + 1;
        switch(nombreAleatoire){

            case 1:
                mediaPlayer = MediaPlayer.create(context, R.raw.dragon_ball_z_best_music_part_1);
                break;

            case 2:
                mediaPlayer = MediaPlayer.create(context, R.raw.all_dragon_ball_anime_openings_f);
                break;

            case 3:
                mediaPlayer = MediaPlayer.create(context, R.raw.dragon_ball_z_best_music_part_2);
                break;
            default:
                mediaPlayer = MediaPlayer.create(context, R.raw.all_dragon_ball_anime_openings_f);
                break;
        }

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

        //son utilisé lors des clicks sur la dragon ball
        mapDeSon.put("damage0", this.soundPool.load(context, R.raw.arc_btl_cmn_crouch_basa, 1));
        mapDeSon.put("damage1", this.soundPool.load(context, R.raw.arc_btl_cmn_down_hizakuzure, 1));
        mapDeSon.put("damage2", this.soundPool.load(context, R.raw.arc_btl_cmn_down_tataki, 1));
        mapDeSon.put("damage3", this.soundPool.load(context, R.raw.arc_btl_cmn_down_utsubuse, 1));
        mapDeSon.put("damage4", this.soundPool.load(context, R.raw.dragon_ball_super_punch_sound_effects_2, 1));
        mapDeSon.put("damage5", this.soundPool.load(context, R.raw.dragon_ball_super_punch_sound_effects_9, 1));
        mapDeSon.put("damage6", this.soundPool.load(context, R.raw.dragon_ball_super_punch_sound_effects_13, 1));
        mapDeSon.put("damage7", this.soundPool.load(context, R.raw.dragon_ball_super_punch_sound_effects_14, 1));
        mapDeSon.put("damage7", this.soundPool.load(context, R.raw.dragon_ball_z_heavy_punch_sound_effect, 1));

        mapDeSon.put("uprade", this.soundPool.load(context, R.raw.arc_btl_cmn_chargegod_start, 1));

    }


    public void pause(){
        mediaPlayer.pause();
    }

    public  void start(){
        mediaPlayer.start();
    }

    public void onDamage(){
        if(poolIsLoaded) {
            Random rand = new Random();
            int nombreAleatoire = rand.nextInt(7 - 0 + 1) + 0;
            soundPool.play(mapDeSon.get("damage" + nombreAleatoire), VOLUME, VOLUME, 1, 0, 1f);
        }
    }

    public void onUpradeAdded(){
        if(poolIsLoaded)
            soundPool.play(mapDeSon.get("uprade"), VOLUMEUPGRADE, VOLUMEUPGRADE, 1, 0, 1f);

    }

    /*public void playMenuSound(){
        if(poolIsLoaded)
            soundPool.play(mapDeSon.get("menu"), VOLUME, VOLUME, 1, 0, 1f);

    }

    public void onPlayerMoveUpPlaySound(){
        if(poolIsLoaded)
            soundPool.play(mapDeSon.get("jump"), VOLUME, VOLUME, 1, 0, 1f);

    }*/


}