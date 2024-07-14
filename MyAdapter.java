package com.example.library;

import android.content.Context;
import android.content.Intent;
import android.icu.text.Transliterator;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {
    private Context context;
    private List<DataClass> dataList;

    public MyAdapter(Context context, List<DataClass> dataList) {
        this.context = context;
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        DataClass currentItem = dataList.get(position);

        holder.name.setText(currentItem.getDataName());
        holder.price.setText(currentItem.getDataPrice());

        Glide.with(context).load(currentItem.getImage()).into(holder.img);
        holder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra("Image",dataList.get(holder.getAdapterPosition()).getImage());
                intent.putExtra("Name",dataList.get(holder.getAdapterPosition()).getDataName());
                intent.putExtra("Price",dataList.get(holder.getAdapterPosition()).getDataPrice());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }
    public void searchDatalist(ArrayList<DataClass>searchlist){
        dataList = searchlist;
        notifyDataSetChanged();
    }
}

class MyViewHolder extends RecyclerView.ViewHolder {
    ImageView img;
    TextView name, price;
    CardView card;

    public MyViewHolder(@NonNull View itemView) {
        super(itemView);
        card = itemView.findViewById(R.id.recCard);
        img = itemView.findViewById(R.id.recImage);
        name = itemView.findViewById(R.id.rectitle);
        price = itemView.findViewById(R.id.recPrice);
    }
}

