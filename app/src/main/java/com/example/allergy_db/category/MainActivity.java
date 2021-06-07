package com.example.allergy_db.category;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.allergy_db.R;
import com.example.allergy_db.setting.Allergy_set;

import java.util.ArrayList;
import java.util.Map;


public class MainActivity extends AppCompatActivity {
    private EditText editName;
    private ImageButton btnView, btn_set;
    private String[] engnames = {"Korean","Boonsik","Chicken","Pizza"
            ,"Fastfood","Jokbal","Cafe","Etc","Total"};
    private int[] Buttonarray= {R.id.Korean,R.id.Boonsik,R.id.Chicken,R.id.Pizza,
            R.id.Fastfood,R.id.Jokbal,R.id.Cafe,R.id.Etc,R.id.Total};
    private String[] korcate={"한식","분식","치킨","피자","패스트푸드","족발","카페","기타","전체"};
    private ArrayList<Category> categories =new ArrayList<>();
    Intent bintent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        editName = (EditText) findViewById(R.id.edit_name);

        btnView=(ImageButton) findViewById(R.id.btn_view);
        btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadAllFromDB(editName.getText().toString());
                editName.setText("");
            }
        });


        for(int i = 0; i< engnames.length; i++){
            Long l= (long) (i+1);
            categories.add(new Category(korcate[i], engnames[i], l));
        }

        for(int j=0; j< engnames.length; j++){
            putintent(j);
        }

        btn_set = (ImageButton) findViewById(R.id.btn_set);

        btn_set.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this , Allergy_set.class);
                startActivity(intent);  // 액티비티 이동
            }
        });

        SharedPreferences pref = getSharedPreferences("pref", Activity.MODE_PRIVATE);
        for(Map.Entry<String,?> entry: pref.getAll().entrySet())
        {

            Boolean bool=(Boolean)entry.getValue();
            if(bool){
                Allergy_set.userAllergy.add(entry.getKey());
            }

        }

    }

    private void loadAllFromDB(String name) {
        if(name.equals(""))
        {
            Toast.makeText(this, "검색어를 입력해주세요", Toast.LENGTH_SHORT).show();
            return;}
        Intent intent=new Intent(MainActivity.this, SearchResult.class);
        intent.putExtra("Name",name);
        startActivity(intent);
    }

    private void putintent(final int j){
        ((LinearLayout)findViewById(Buttonarray[j]))
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        bintent=new Intent(MainActivity.this, CategoryResult.class);
                        bintent.putExtra("categoryNum",categories.get(j).categoryId);
                        bintent.putExtra("categoryName",categories.get(j).name);
                        startActivity(bintent);
                    }
                });
    }
}
