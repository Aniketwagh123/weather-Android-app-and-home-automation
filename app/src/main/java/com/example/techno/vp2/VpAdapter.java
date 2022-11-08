package com.example.techno.vp2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.techno.R;

import java.util.ArrayList;

public class VpAdapter extends RecyclerView.Adapter<VpAdapter.ViewHolder> {

    ArrayList<ViewpagerItem> viewpagerItemArrayList;
    public VpAdapter(ArrayList<ViewpagerItem> viewpagerItemArrayList) {
        this.viewpagerItemArrayList = viewpagerItemArrayList;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.viewpager_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ViewpagerItem viewpagerItem = viewpagerItemArrayList.get(position);
        holder.day.setText(viewpagerItem.day);
        holder.date.setText(viewpagerItem.date);
        holder.humidity.setText(viewpagerItem.humidity);
        holder.temperature.setText(viewpagerItem.temperature);


    }

    @Override
    public int getItemCount() {
        return viewpagerItemArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
//        ImageView imageView;
//        TextView tcHeading,tvDesc;
//
//        public ViewHolder(@NonNull View itemView) {
//            super(itemView);
//            imageView = itemView.findViewById(R.id.ivImage);
//            tcHeading = itemView.findViewById(R.id.tcHeading);
//            tvDesc = itemView.findViewById(R.id.tvDec);
//        }

        TextView day,date,humidity,temperature;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            day = itemView.findViewById(R.id.day);
            date = itemView.findViewById(R.id.date);
            humidity = itemView.findViewById(R.id.humidity);
            temperature = itemView.findViewById(R.id.temp);
        }




    }
}
