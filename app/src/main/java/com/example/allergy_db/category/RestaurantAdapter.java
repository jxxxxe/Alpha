package com.example.allergy_db.category;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.allergy_db.MeunActivity;
import com.example.allergy_db.R;

import java.util.ArrayList;

public class RestaurantAdapter extends RecyclerView.Adapter<RestaurantAdapter.CustomViewHolder> {

  private ArrayList<Restaurant> arrayList;
  private Context context;


  public RestaurantAdapter(ArrayList<Restaurant> arrayList, Context context) {
    this.arrayList = arrayList;
    this.context = context;
  }

  @NonNull
  @Override
  public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
   View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_rest,parent,false);
   CustomViewHolder holder=new CustomViewHolder(view);
    return holder;
  }

  @Override
  public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {
    Glide.with(holder.itemView)
            .load(arrayList.get(position).getImage())
            .into(holder.iv_image);
    holder.tv_name.setText(arrayList.get(position).getName());
  }

  @Override
  public int getItemCount() {
    return (arrayList!=null ? arrayList.size() : 0);
  }

  public class CustomViewHolder extends RecyclerView.ViewHolder {
    ImageView iv_image;
    TextView tv_name;

    public CustomViewHolder(@NonNull View itemView) {
      super(itemView);
      this.iv_image =itemView.findViewById(R.id.imageView4);
      this.tv_name=itemView.findViewById(R.id.text_name);

      itemView.setClickable(true);
      itemView.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
          int position=getAdapterPosition();
          if(position!=RecyclerView.NO_POSITION)
          {
            Intent intent=new Intent(view.getContext(),MeunActivity.class);
            intent.putExtra("restaurantId",arrayList.get(position).getId());
            intent.putExtra("restaurantName",arrayList.get(position).getName());
            view.getContext().startActivity(intent);
          }
        }
      });
    }
  }
}
