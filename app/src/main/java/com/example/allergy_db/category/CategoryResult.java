package com.example.allergy_db.category;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.allergy_db.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class CategoryResult extends AppCompatActivity {

    Intent intent;
    private Long categoryNum;
    private String categoryName;

    TextView categoryText;

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<Restaurant> arrayList;
    private FirebaseDatabase database;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.category_result);

        intent = getIntent();
        categoryNum =intent.getLongExtra("categoryNum",0);
        categoryName=intent.getStringExtra("categoryName");

        categoryText=(TextView)findViewById(R.id.textView11);
        categoryText.setText(categoryName);

        recyclerView=findViewById(R.id.recyclerView2);
        recyclerView.setHasFixedSize(true);
        layoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        arrayList=new ArrayList<>();

        database= FirebaseDatabase.getInstance();

        databaseReference=database.getReference("Restaurant");
        Query query=databaseReference.orderByChild("category").equalTo(categoryNum);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                // 파이어베이스 데이터베이스의 데이터를 받아오는 곳
                arrayList.clear(); //기존 배열리스트 초기화
                for(DataSnapshot snapshot : dataSnapshot.getChildren()){ //반복문으로 데이터 List를 추출
                    Restaurant restaurant=snapshot.getValue(Restaurant.class); //만들어뒀던 Resaurant 객체에 데이터를 담는다.
                    arrayList.add(restaurant); //담은 데이터들을 배열리스트에 넣고 리사이클러뷰로 보낼 준비
                }
                adapter.notifyDataSetChanged(); //리스트 저장 및 새로고침
            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // 디비를 가져오던 중 에러 발생 시
                Log.e("SearchResult", String.valueOf(error.toException())); //에러문 출력
            }


        });

            adapter=new RestaurantAdapter(arrayList,this);
            recyclerView.setAdapter(adapter); //리사이클러뷰에 어댑터 연결
    }
    }
