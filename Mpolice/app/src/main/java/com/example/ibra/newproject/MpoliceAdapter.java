package com.example.ibra.newproject;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by lucie on 12/9/15.
 */
public class MpoliceAdapter extends RecyclerView.Adapter<MpoliceAdapter.PoliceHolder> {

    @Override
    public PoliceHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(PoliceHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    //view holder class
    public static class PoliceHolder extends RecyclerView.ViewHolder{
        CardView cardV;
        TextView number_plate, description,owner, status;

        public PoliceHolder(View v) {
            super(v);

            cardV = (CardView) v.findViewById(R.id.cardView);
            number_plate = (TextView) v.findViewById(R.id.number_plate);
            description = (TextView) v.findViewById(R.id.description);
            owner = (TextView) v.findViewById(R.id.owner);

        }
    }
}
