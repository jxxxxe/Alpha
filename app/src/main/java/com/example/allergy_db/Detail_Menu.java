package com.example.allergy_db;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

public class Detail_Menu  extends AppCompatActivity {

    Menu menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_menu);

        ImageView menu_image=(ImageView)findViewById(R.id.image);
        TextView menu_name=(TextView)findViewById(R.id.name);
        TextView menu_price=(TextView)findViewById(R.id.price);
        TextView menu_allergy=(TextView)findViewById(R.id.allergy);
        TextView cal = (TextView)findViewById(R.id.cal);
        TextView car = (TextView)findViewById(R.id.carbonhydrate);
        TextView pro = (TextView)findViewById(R.id.protein);
        TextView fat = (TextView)findViewById(R.id.fat);
        TextView na = (TextView)findViewById(R.id.na);
        TextView sug = (TextView)findViewById(R.id.sugar);

        Intent intent=getIntent();
        menu=(Menu)intent.getSerializableExtra("menu");

        Glide.with(this).load(menu.getProfile()).override(800,500).into(menu_image);
        menu_name.setText(menu.getMenuName());
        if(menu.getPrice()!=null)
            menu_price.setText(menu.getPrice().toString());
        menu_allergy.setText(menu.getMenuInfo());

        if (menu.getCal()!= null)
            cal.setText(menu.getCal());
        if (menu.getCarbonhydrate() != null)
            car.setText(menu.getCarbonhydrate());
        if (menu.getProtein() != null)
            pro.setText(menu.getProtein());
        if (menu.getFat() != null)
            fat.setText(menu.getFat());
        if (menu.getNa() != null)
            na.setText(menu.getNa());
        if (menu.getSugar() != null)
            sug.setText(menu.getSugar());

    }

}
