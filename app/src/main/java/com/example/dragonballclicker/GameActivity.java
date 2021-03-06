package com.example.dragonballclicker;

import static java.lang.Thread.sleep;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class GameActivity extends AppCompatActivity implements Serializable {

    //Tous les attributs
    private ConstraintLayout fenetrePrincipale;
    private ImageButton dbclick;
    private ImageButton infoButton;
    private TextView nbDb;
    private TextView DbPerSec;
    private RecyclerView upRecycler;
    private UpAdapter upAdapter;
    private ObjectAnimator objectAnimatorY;
    private ObjectAnimator objectAnimatorAlpha;
    private ObjectAnimator objectAnimatorScaleY;
    private ObjectAnimator objectAnimatorsScaleX;
    private GameActivity gameActivity;
    private Toast toast;
    public SoundManager soundManager;

    private static int nbPlus;
    private boolean isResume;
    private Thread thread;
    private static long cookie;
    private static long cookiePs;
    private static List<Upgrade> upgradeList;
    private List<Toast> toastList;


    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //Remove title bar && Remove notification bar
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        Objects.requireNonNull(getSupportActionBar()).hide();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nbPlus = 1;
        gameActivity = this;
        toastList = new ArrayList<>();

        try {
            PrefConfig.reader(this);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


        if (upgradeList == null) {
            upgradeList = new ArrayList<>();
            initList();
        }

        //lien entre le XML et le code
        upRecycler = findViewById(R.id.upRecycler);
        upAdapter = new UpAdapter(upgradeList, this);
        upRecycler.setAdapter(upAdapter);
        upRecycler.setLayoutManager(new LinearLayoutManager(this));

        //lien entre le XML et le code
        dbclick = findViewById(R.id.dbClick);
        nbDb = findViewById(R.id.nbDb);
        DbPerSec = findViewById(R.id.DbPerSec);
        fenetrePrincipale = findViewById(R.id.fenetrePrincipale);
        infoButton =  findViewById(R.id.infoButton);

        //lance la musique de fond
        soundManager = new SoundManager(getApplicationContext());
        soundManager.start();

        // aller chercher le fichier de pr??f??rence dans lequel le score est stock??
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        // extraire la valeur de Score
        cookie = preferences.getLong("Score", 0);
        //cookie = preferences.getLong("Score",0);
        nbDb.setText(cookie + " Dragon ball");

        //Ajoute des point ?? chaque clique et joue un son
        dbclick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cookie = cookie + nbPlus;
                nbDb.setText(cookie + " Dragon ball");
                soundManager.onDamage();
            }
        });

        //Anime l'apparition et la disparaition du +1 ?? l'endroit o?? a cliquer l'utilisateur
        dbclick.setOnTouchListener(new View.OnTouchListener() {

            private TextView plus;

            @Override
            public boolean onTouch(View v, MotionEvent event) {

                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    plus = new TextView(getApplicationContext());
                    plus.setText(" +" + nbPlus);
                    plus.setTextSize(nbDb.getTextSize()-2);
                    plus.setX((float) (event.getX() + ((fenetrePrincipale.getWidth() - dbclick.getWidth()) / 2 ) ));
                    plus.setY(event.getY()+10);
                    plus.bringToFront();
                    fenetrePrincipale.addView(plus);
                    String msg = String.valueOf("X : " + event.getX() + "      Y : " + event.getY());
                    Log.e("Activity", msg);
                    oneAnimation(plus);
                }
                return false;
            }

            // animation du +1 lors du click sur le dragonball
            public void oneAnimation(TextView plus) {
                AnimatorSet plus1 = new AnimatorSet();

                plus1.addListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        fenetrePrincipale.removeView(plus);
                        Log.e("Suppresion","plus de plus");
                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animation) {

                    }
                });

                objectAnimatorY=ObjectAnimator.ofFloat(plus, "y", -50);
                objectAnimatorY.setDuration(1000);
                objectAnimatorY.setStartDelay(200);

                objectAnimatorAlpha=ObjectAnimator.ofFloat(plus, "alpha", 10, 0);
                objectAnimatorAlpha.setDuration(500);
                objectAnimatorAlpha.setStartDelay(200);

                objectAnimatorsScaleX=ObjectAnimator.ofFloat(plus, "scaleX", 1, 0);
                objectAnimatorsScaleX.setDuration(500);
                objectAnimatorsScaleX.setStartDelay(500);

                objectAnimatorScaleY=ObjectAnimator.ofFloat(plus, "scaleY", 1, 0);
                objectAnimatorScaleY.setDuration(500);
                objectAnimatorScaleY.setStartDelay(500);

                plus1.play(objectAnimatorY).with(objectAnimatorAlpha).with(objectAnimatorsScaleX).with(objectAnimatorScaleY);
                plus1.start();

            }
        });

        //ajoute les cookies donn??es par les upgrades toutes les secondes
        thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (isResume) {
                    cookie = cookie +  cookiePs;
                    gameActivity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            nbDb.setText(cookie + " Dragon ball");
                        }
                    });
                    try {
                        sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

    }

    @SuppressLint("UseCompatLoadingForDrawables")
    //initialise la liste des upgrades
    private void initList() {

        upgradeList.add(new Upgrade(100, getDrawable(R.drawable.songokukid), "Son Goku Enfant", 0, 1));
        upgradeList.add(new Upgrade(500, getDrawable(R.drawable.goku_pose), "Son Goku Adulte", 0, 5));
        upgradeList.add(new Upgrade(1100, getDrawable(R.drawable.goku_kx3), "Son Goku Kaioken x3", 0, 8));
        upgradeList.add(new Upgrade(12000, getDrawable(R.drawable.goku_kx30_fini), "Son Goku Kaioken x4", 0,47));
        upgradeList.add(new Upgrade(130000, getDrawable(R.drawable.goku_ssj), "Son Goku super saiyan", 0,260));
        upgradeList.add(new Upgrade(1400000, getDrawable(R.drawable.goku_ssj2), "Son Goku super saiyan 2", 0,1400));
        upgradeList.add(new Upgrade(20000000, getDrawable(R.drawable.goku_ssj3_2), "Son Goku super saiyan 3", 0,7800));
        upgradeList.add(new Upgrade(330000000, getDrawable(R.drawable.goku_ssj4), "Son Goku super saiyan 4", 0,44000));
        upgradeList.add(new Upgrade(5100000000l, getDrawable(R.drawable.goku_god), "Son Goku super saiyan God", 0,260000));
        upgradeList.add(new Upgrade(75000000000l, getDrawable(R.drawable.goku_blue2), "Son Goku super saiyan blue", 0,1600000));
        upgradeList.add(new Upgrade(100000000000l, getDrawable(R.drawable.goku_bluek), "Son Goku SSJB Kaioken x20", 0,10000000));
        upgradeList.add(new Upgrade(500000000000l, getDrawable(R.drawable.goku_ultra_instinct), "Son Goku ultra instinct", 0,65000000));
    }

    public static float getCookie() {
        return cookie;
    }

    public static List<Upgrade> getUpgradeList() {
        return upgradeList;
    }

    //affiche un toast si aucun toast n'est afficher ?? l'??cran
    public void showToast()  {
        if (toastList.size() == 0) {
            toast = Toast.makeText(getApplicationContext(), "Vous ne pas proc??der assez de Dragon Ball pour acheter cette am??lioration", Toast.LENGTH_SHORT);
            toastList.add(toast);
            toast.show();
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    toastList.clear();

                }
            }).start();
        }
    }

    public void save() {
        // aller chercher le fichier de pr??f??rence pour l'??diter
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = preferences.edit();
        // ajouter le doubler "Scrore" avec sa valeur actuelle
        editor.putLong("Score", cookie);

        try {
            PrefConfig.writer(upgradeList, getApplicationContext());
        } catch (IOException e) {
            e.printStackTrace();
        }
        //PrefConfig.writeListInPref(getApplicationContext(), upgradeList);
        //PrefConfig.createNewFile(getApplicationContext());

        // enregister le fichier de pr??f??rence
        editor.apply();
    }

    //Renvoie vers une page d'explication
    public void click(View v) {
        Intent broIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://univlemansfr-my.sharepoint.com/:b:/g/personal/erhan_akyuz_etu_univ-lemans_fr/EQQsZ8m-Ws5GlXSpbnx5VBcBjUnYuXTVbmJ7nCAY2N53Ug?e=V1OEeW"));
        startActivity(broIntent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        isResume = true;
        soundManager.start();
        thread.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        soundManager.pause();
        isResume = false;
    }

    @Override
    protected void onStop() {
        save();
        super.onStop();
    }

    public void affichageNbDb(long price) {
        cookie = cookie - price;
        nbDb.setText(cookie + " Dragon ball");
    }

    public static long getCookiePs() {
        return cookiePs;
    }

    public static void setCookiePs(long cookiePs) {
        GameActivity.cookiePs = cookiePs;
    }

    public TextView getDbPerSec() {
        return DbPerSec;
    }

    public static int getNbPlus() {
        return nbPlus;
    }

    public static void setNbPlus(int nbPlus) {
        if (nbPlus > 1)
        GameActivity.nbPlus = nbPlus;
    }

}