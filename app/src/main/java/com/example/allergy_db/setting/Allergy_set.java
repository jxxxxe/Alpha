package com.example.allergy_db.setting;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.allergy_db.R;
import com.example.allergy_db.category.MainActivity;

import java.util.ArrayList;
import java.util.Map;

public class Allergy_set extends AppCompatActivity {
    public static ArrayList<String> userAllergy=new ArrayList<>();
    int num=22;
    int alcat=5;

    private ImageButton btn_saveSet;
    private CheckBox[] checkBox;
    private Boolean[] chkBoolean;
    private Button[] btns=new Button[alcat];
    private LinearLayout[] linears=new LinearLayout[alcat];

    String[] alNames = {"알류", "우유","메밀","땅콩","대두","밀","잣","호두","게","새우","오징어","고등어",
            "조개류","홍합","전복","굴","복숭아","토마토","돼지고기","닭고기","쇠고기","아황산류"};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.allergy_set);

        checkBox =new CheckBox[num];
        chkBoolean =new Boolean[num];

        int btnid;
        int linid;
        for(int i=0;i<alcat;i++){
            btnid=getResources().getIdentifier("btn_"+(i), "id", "com.example.allergy_db");
            linid=getResources().getIdentifier("linear_"+(i), "id", "com.example.allergy_db");
            btns[i]=(Button)findViewById(btnid);
            linears[i]=(LinearLayout)findViewById(linid);
            visibleLay(btns[i],linears[i]);
        }

        SharedPreferences pref = getSharedPreferences("pref", Activity.MODE_PRIVATE);
        // Shared Preference를 불러옵니다.

        int id;
        for(int i=0;i<num;i++){
            id=getResources().getIdentifier("chk"+(i+1), "id", "com.example.allergy_db");
            checkBox[i] = (CheckBox)findViewById(id);
        }

        //저장된 값들을 불러온다.

        for (int i=0; i<alNames.length; i++) {
            chkBoolean[i] = pref.getBoolean(alNames[i], false);
        }

        for(int i=0;i<num;i++){
            checkBox[i].setChecked(chkBoolean[i]);
        }

        btn_saveSet = (ImageButton) findViewById(R.id.btn_saveSet);
        btn_saveSet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Allergy_set.this , MainActivity.class);
                startActivity(intent);  // 액티비티 이동
            }
        });
    }

    public void onStop(){ // 어플리케이션이 화면에서 사라질때
        super.onStop();
        SharedPreferences pref = getSharedPreferences("pref", Activity.MODE_PRIVATE); // UI 상태를 저장합니다.
        SharedPreferences.Editor editor = pref.edit(); // Editor를 불러옵니다.

        // 저장할 값들을 입력합니다.
        for (int i=0; i<alNames.length; i++) {
            editor.putBoolean(alNames[i], checkBox[i].isChecked());
        }

        editor.commit(); // 저장합니다.


        for(Map.Entry<String,?> entry: pref.getAll().entrySet())
        {

            Boolean bool=(Boolean)entry.getValue();
            if(bool){
                userAllergy.add(entry.getKey());
            }

        }
    }

    private void visibleLay(Button btn,final LinearLayout linear){
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (linear.getVisibility()==View.VISIBLE)
                        linear.setVisibility(View.GONE);
                else
                    linear.setVisibility(View.VISIBLE);
            }
        });
    }
}
