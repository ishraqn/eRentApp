package com.erent.presentation;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.erent.R;
public class HomepageViewHolder extends RecyclerView.ViewHolder{

    ImageView imageView;
    TextView titleView;
    TextView rentPeriodView;
    TextView priceView;

    public HomepageViewHolder(@NonNull View itemView, RecyclerViewInterface recyclerViewInterface)
    {
        super(itemView);
        imageView = itemView.findViewById(R.id.image);
        titleView = itemView.findViewById(R.id.title);
        rentPeriodView = itemView.findViewById(R.id.rent_period);
        priceView = itemView.findViewById(R.id.price);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(recyclerViewInterface != null) {
                    int position = getAdapterPosition();

                    if(position != RecyclerView.NO_POSITION) {
                        recyclerViewInterface.onItemClick(position);
                    }
                }
            }
        });
    }
}
