package com.example.dragonballclicker;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class UpViewHolder extends RecyclerView.ViewHolder {

    private ImageView upImage;
    private TextView price;
    private TextView nbUp;
    private TextView name;
    private TextView dbs;


    public UpViewHolder(@NonNull View itemView) {
        super(itemView);

        upImage = itemView.findViewById(R.id.upImage);
        price = itemView.findViewById(R.id.price);
        nbUp = itemView.findViewById(R.id.nbUp);
        name = itemView.findViewById(R.id.name);
        dbs = itemView.findViewById(R.id.dbs);
    }

    public TextView getDbs() {
        return dbs;
    }

    public ImageView getUpImage() {
        return upImage;
    }

    public TextView getPrice() {
        return price;
    }

    public TextView getNbUp() {
        return nbUp;
    }

    public TextView getName() {
        return name;
    }
}
