package com.example.dragonballclicker;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class UpAdapter extends RecyclerView.Adapter<UpViewHolder> {

    private List<Upgrade> upgradeList;
    private GameActivity gameActivity;


    public UpAdapter(List<Upgrade> upgradeList, GameActivity act) {
        this.upgradeList = upgradeList;
        this.gameActivity = act;
    }

    @NonNull
    @Override
    public UpViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.upgrade_layout, parent, false);

        return new UpViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UpViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.getName().setText(upgradeList.get(position).getName());
        holder.getPrice().setText(String.valueOf(upgradeList.get(position).getPrice()));
        holder.getNbUp().setText(String.valueOf(upgradeList.get(position).getNbUp()));
        holder.getUpImage().setImageDrawable(upgradeList.get(position).getImage());
        holder.getDbs().setText(String.valueOf(upgradeList.get(position).getDBs() + " DB/s"));
        holder.itemView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (gameActivity.getCookie() < gameActivity.getUpgradeList().get(position).getPrice()) {
                    //Log.e("position", String.valueOf(position));
                        gameActivity.showToast();

                } else {
                    long cookiePs = gameActivity.getCookiePs();

                    upgradeList.get(position).setNbUp(upgradeList.get(position).getNbUp()+1);
                    holder.getNbUp().setText(String.valueOf(upgradeList.get(position).getNbUp()));
                    gameActivity.affichageNbDb(upgradeList.get(position).getPrice());

                    //changement de la valeur cookie par second + affichage
                    cookiePs = cookiePs + upgradeList.get(position).getDBs();
                    Log.e("Cookie par seconds", String.valueOf(cookiePs));
                    gameActivity.setCookiePs(cookiePs);
                    gameActivity.getDbPerSec().setText(cookiePs + " Dragon ball / s");

                    //modification du prix
                    long newPrice = upgradeList.get(position).getPrice();
                    newPrice = (long) (newPrice + (newPrice * 0.15));
                    upgradeList.get(position).setPrice(newPrice);
                    holder.getPrice().setText(String.valueOf(newPrice));
                    gameActivity.setNbPlus((int) (upgradeList.get(0).getNbUp()/10));


                }
            }
        });



    }

    @Override
    public int getItemCount() {
        return upgradeList.size();
    }
}
