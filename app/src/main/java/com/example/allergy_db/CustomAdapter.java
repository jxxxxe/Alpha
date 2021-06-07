package com.example.allergy_db;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.allergy_db.setting.Allergy_set;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.CustomViewHolder> {

    private ArrayList<Menu> arrayList;
    private Context context;

    public CustomAdapter(ArrayList<Menu> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item,parent,false);
        CustomViewHolder holder = new CustomViewHolder(view);
        return holder;
    }

    //

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {
        Glide.with(holder.itemView)
                .load(arrayList.get(position).getProfile())
                .into(holder.iv_profile);

        holder.menu_name.setText(arrayList.get(position).getMenuName());
        holder.menu_info.setText(arrayList.get(position).getMenuInfo());

        for(String allergy:Allergy_set.userAllergy) {
            if(arrayList.get(position).getMenuInfo().contains(allergy))
            {
                holder.itemView.setBackgroundResource(R.drawable.stop_back);
                break;
            }
        }
    }

    @Override
    public int getItemCount() {
        return (arrayList !=null?arrayList.size():0);
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {

        ImageView iv_profile;
        TextView menu_name;
        TextView menu_info;

        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            this.iv_profile = itemView.findViewById(R.id.iv_profile);
            this.menu_name = itemView.findViewById(R.id.menu_name);
            this.menu_info = itemView.findViewById(R.id.menu_info);
            itemView.setClickable(true);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position=getAdapterPosition();
                    if(position!=RecyclerView.NO_POSITION)
                    {
                        Intent intent=new Intent(view.getContext(),Detail_Menu.class);
                        intent.putExtra("menu",arrayList.get(position));
                        view.getContext().startActivity(intent);
                    }
                }
            });
        }
    }
}
